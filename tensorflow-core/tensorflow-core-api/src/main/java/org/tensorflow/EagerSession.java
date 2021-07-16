/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextAddFunction;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetAsync;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetConfig;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetDevicePlacementPolicy;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteContext;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewContext;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.WeakPointerScope;
import org.tensorflow.internal.c_api.TFE_Context;
import org.tensorflow.internal.c_api.TFE_ContextOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.op.JavaScope;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.proto.framework.ConfigProto;

/**
 * An environment for executing TensorFlow operations eagerly.
 *
 * <p>Eager execution is an imperative programming environment that evaluates operations
 * immediately, without building graphs. Operations return concrete values instead of constructing a
 * computational graph to run later, as with {@link Graph}s and {@link Session}s.
 *
 * <p>This makes it easy to develop with TensorFlow and debug models, as it behaves more like a
 * standard programming library.
 *
 * <p>Instances of a {@code EagerSession} are thread-safe.
 */
public final class EagerSession implements ExecutionEnvironment, AutoCloseable {

  /**
   * Controls how to act when we try to run an operation on a given device but some input tensors
   * are not on that device.
   */
  public enum DevicePlacementPolicy {

    /** Running operations with input tensors on the wrong device will fail. */
    EXPLICIT(0),

    /** Copy the tensor to the right device but log a warning. */
    WARN(1),

    /**
     * Silently copy the tensor, which has a performance cost since the operation will be blocked
     * till the copy completes. This is the default placement policy.
     */
    SILENT(2),

    /** Placement policy which silently copies int32 tensors but not other dtypes. */
    SILENT_FOR_INT32(3);

    DevicePlacementPolicy(int code) {
      this.code = code;
    }

    private final int code;
  }

  public static class Options {

    /**
     * Controls how operations dispatched are actually executed.
     *
     * <p>When set to true, each operation are executed asynchronously (in which case some
     * operations might return "non-ready" outputs). When set to false, all operations are executed
     * synchronously.
     *
     * <p>Synchronous execution is used by default.
     *
     * @param value true for asynchronous execution, false for synchronous.
     */
    public Options async(boolean value) {
      async = value;
      return this;
    }

    /**
     * Controls how to act when we try to run an operation on a given device but some input tensors
     * are not on that device.
     *
     * <p>{@link DevicePlacementPolicy#SILENT} is used by default.
     *
     * @param value policy to apply
     * @see DevicePlacementPolicy
     */
    public Options devicePlacementPolicy(DevicePlacementPolicy value) {
      devicePlacementPolicy = value;
      return this;
    }

    /**
     * Configures the session based on the data found in the provided configuration.
     *
     * @param config a config protocol buffer
     * @see <a
     *     href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/core/protobuf/config.proto">config.proto</a></a>
     */
    public Options config(ConfigProto config) {
      this.config = config;
      return this;
    }

    /** Builds an eager session with the selected options. */
    public EagerSession build() {
      return new EagerSession(this);
    }

    private boolean async;
    private DevicePlacementPolicy devicePlacementPolicy;
    private ConfigProto config;

    private Options() {
      async = false;
      devicePlacementPolicy = DevicePlacementPolicy.SILENT;
      config = null;
    }
  }

  /**
   * Initializes the default eager session, which remains active for the lifetime of the
   * application.
   *
   * <p>This method is implicitly invoked on the first call to {@link #getDefault()}, but can also
   * be invoked explicitly to override default options.
   *
   * <p>Note that calling this method more than once will throw an {@code IllegalArgumentException}
   * as the default session cannot be modified once it has been created. Therefore, it is important
   * to explicitly initialize it before {@link #getDefault()} is invoked for the first time from any
   * thread.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * // Initializing default session to override default options is valid but
   * // is optional
   * EagerSession.initDefault(EagerSession.options().async(true));
   *
   * // Starting to build eager operations using default session, by calling
   * // EagerSession.getDefault() implicitly
   * Ops tf = Ops.create();
   *
   * // Initializing default session more than once or after using it is not
   * // permitted and throws an exception
   * EagerSession.initDefault(EagerSession.options().async(true));  // throws
   * }</pre>
   *
   * @param options options to use to build default session
   * @return default eager session
   * @throws IllegalStateException if the default session is already initialized
   * @see #getDefault()
   */
  public static EagerSession initDefault(Options options) {
    synchronized (EagerSession.class) {
      if (defaultSession != null) {
        throw new IllegalStateException("Default eager session is already initialized");
      }
      defaultSession = options.build();
    }
    return defaultSession;
  }

  /**
   * Returns the default eager session
   *
   * <p>Once initialized, the default eager session remains active for the whole life of the
   * application, as opposed to sessions obtained from {@link #create()} or {@link Options#build()}
   * which should be closed after their usage.
   *
   * <p>The default set of {@link Options} is used to initialize the session on the first call. To
   * override this behavior, it is possible to invoke {@link #initDefault(Options)} with a different
   * set of options prior to this first call.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * // Starting to build eager operations using default session, by calling
   * // EagerSession.getDefault() implicitly
   * Ops tf = Ops.create();
   *
   * // Starting to build eager operations using default session, by calling
   * // EagerSession.getDefault() explicitly
   * Ops tf = Ops.create(EagerSession.getDefault());
   * }</pre>
   *
   * @return default eager session
   * @see #initDefault
   */
  public static EagerSession getDefault() {
    if (defaultSession == null) {
      synchronized (EagerSession.class) {
        if (defaultSession == null) {
          defaultSession = options().build();
        }
      }
    }
    return defaultSession;
  }

  /**
   * Returns an {@code EagerSession} configured with default options.
   *
   * <p><b>WARNING:</b>Instances of {@code EagerSession} returned by this method must be explicitly
   * freed by invoking {@link #close()} when they are no longer needed. This could be achieve using
   * the `try-with-resources` technique.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * try (EagerSession session = EagerSession.create()) {
   *   Ops tf = Ops.create(session);
   *   // build execute operations eagerly...
   * }
   * }</pre>
   */
  public static EagerSession create() {
    return options().build();
  }

  /**
   * Returns an object that configures and builds a {@code EagerSession} with custom options.
   *
   * <p><b>WARNING:</b>Instances of {@code EagerSession} returned by this method must be explicitly
   * freed by invoking {@link #close()} when they are no longer needed. This could be achieve using
   * the `try-with-resources` technique.
   *
   * <p>Example usage:
   *
   * <pre>{@code
   * try (EagerSession session = EagerSession.options().async(true).build()) {
   *   Ops tf = Ops.create(session);
   *   // build execute operations eagerly and asynchronously...
   * }
   * }</pre>
   */
  public static EagerSession.Options options() {
    return new Options();
  }

  @Override
  public void close() {
    if (this == defaultSession) {
      throw new IllegalStateException("Default eager session cannot be closed");
    }
    doClose();
  }

  // Cleanup default session context for unit tests
  static void closeDefaultForTest() {
    synchronized (EagerSession.class) {
      if (defaultSession != null) {
        defaultSession.doClose();
        defaultSession = null;
      }
    }
  }

  @Override
  public OperationBuilder opBuilder(String type, String name) {
    checkSession();
    if (!isOpEnabled(type)) {
      throw new IllegalArgumentException("Op " + type + " is not valid in eager mode.");
    }
    return new EagerOperationBuilder(this, type, name);
  }

  @Override
  public void attachFunction(ConcreteFunction function) {
    checkSession();
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_ContextAddFunction(nativeHandle, function.nativeHandle(), status);
      status.throwExceptionIfNotOK();

      function
          .getDependencies()
          .forEach(
              fn -> {
                TF_Status status2 = TF_Status.newStatus();
                TFE_ContextAddFunction(nativeHandle, fn, status2);
                status2.throwExceptionIfNotOK();
              });
    }
  }

  @Override
  public Types environmentType() {
    return Types.EAGER;
  }

  @Override
  public boolean isOpEnabled(String opType) {
    switch (opType) {
      case Variable.OP_NAME:
      case Placeholder.OP_NAME:
      case Assign.OP_NAME:
        return false;
      default:
        return true;
    }
  }

  @Override
  public void checkInput(Op input) {
    if (!input.env().isEager()) {
      throw new IllegalArgumentException("Can't use graph operation " + input + " in eager mode.");
    }
  }

  @Override
  public Scope baseScope() {
    return baseScope;
  }

  TFE_Context nativeHandle() {
    checkSession();
    return nativeHandle;
  }

  /**
   * Attach the list of native resources to this eager session scope.
   *
   * <p>When the eager session is closed (i.e. by calling {@link #close()} explicitly or implicitly
   * via try-with-resources), all native resources attached to the session will be released as well,
   * unless so other references are {@link Pointer#retainReference() retaining} them.
   *
   * <p>Attached resources can still be garbage collected though if their associated {@link Pointer}
   * is no longer reachable in Java, independently of their reference count. Therefore, it is
   * assumed that these resources are not required by the native library once the Java client no
   * longer needs them.
   *
   * <p>Attaching a resource already attached to this session will have no effect.
   *
   * @param resources resources to attach to the session
   */
  void attach(Pointer... resources) {
    checkSession();
    for (Pointer r : resources) {
      nativeResources.attach(r);
    }
  }

  /**
   * Detach a list of resources from this eager session scope.
   *
   * <p>Detached native resources will prevent them to be automatically released when the session is
   * closed.
   *
   * <p>Note though that this method will decrement the reference count of each resources being
   * detached, which may automatically released them if that count reaches 0. Therefore, invoking
   * {@link Pointer#retainReference()} prior to this call on any resource that must remain valid
   * after being detached might be required.
   *
   * <p>Detaching a resource that is not attached to this session will have no effect.
   *
   * @param resources resources to detach from the session
   */
  void detach(Pointer... resources) {
    checkSession();
    for (Pointer r : resources) {
      nativeResources.detach(r);
    }
  }

  private static volatile EagerSession defaultSession = null;

  private final WeakPointerScope nativeResources;
  private TFE_Context nativeHandle;

  private final Scope baseScope = new JavaScope(this);

  private EagerSession(Options options) {
    this.nativeResources = new WeakPointerScope();
    this.nativeHandle = allocate(options.async, options.devicePlacementPolicy.code, options.config);
  }

  private void checkSession() {
    if (nativeHandle == null || nativeHandle.isNull()) {
      throw new IllegalStateException("Eager session has been closed");
    }
  }

  private synchronized void doClose() {
    if (nativeHandle != null && !nativeHandle.isNull()) {
      nativeResources.close();
      delete(nativeHandle);
      nativeHandle = null;
    }
  }

  private static TFE_Context allocate(
      boolean async, int devicePlacementPolicy, ConfigProto config) {
    try (PointerScope scope = new PointerScope()) {
      TFE_ContextOptions opts = TFE_ContextOptions.newContextOptions();
      TF_Status status = TF_Status.newStatus();
      if (config != null) {
        BytePointer configBytes = new BytePointer(config.toByteArray());
        TFE_ContextOptionsSetConfig(opts, configBytes, configBytes.capacity(), status);
        status.throwExceptionIfNotOK();
      }
      TFE_ContextOptionsSetAsync(opts, (byte) (async ? 1 : 0));
      TFE_ContextOptionsSetDevicePlacementPolicy(opts, devicePlacementPolicy);
      TFE_Context context = TFE_NewContext(opts, status);
      status.throwExceptionIfNotOK();
      return context.retainReference();
    }
  }

  private static void delete(TFE_Context handle) {
    if (handle == null || handle.isNull()) return;
    TFE_DeleteContext(handle);
  }

  static {
    try {
      // Ensure that TensorFlow native library and classes are ready to be used
      Class.forName("org.tensorflow.TensorFlow");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
