/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetAsync;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetConfig;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_ContextOptionsSetDevicePlacementPolicy;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteContext;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewContext;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TFE_Context;
import org.tensorflow.internal.c_api.TFE_ContextOptions;
import org.tensorflow.internal.c_api.TF_Status;
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

  /**
   * Controls how TensorFlow resources are cleaned up when they are no longer needed.
   *
   * <p>All resources allocated during an {@code EagerSession} are deleted when the session is
   * closed. To prevent out-of-memory errors, it is also strongly suggest to cleanup those resources
   * during the session. For example, executing n operations in a loop of m iterations will allocate
   * a minimum of n*m resources while in most cases, only resources of the last iteration are still
   * being used.
   *
   * <p>{@code EagerSession} instances can be notified in different ways when TensorFlow objects are
   * no longer being referred, so they can proceed to the cleanup of any resources they owned.
   */
  public enum ResourceCleanupStrategy {

    /**
     * Monitor and delete unused resources from a new thread running in background.
     *
     * <p>This is the most reliable approach to cleanup TensorFlow resources, at the cost of
     * starting and running an additional thread dedicated to this task. Each {@code EagerSession}
     * instance has its own thread, which is stopped only when the session is closed.
     *
     * <p>This strategy is used by default.
     */
    IN_BACKGROUND,

    /**
     * Monitor and delete unused resources from existing threads, before or after they complete
     * another task.
     *
     * <p>Unused resources are released when a call to the TensorFlow library reaches a safe point
     * for cleanup. This is done synchronously and might block for a short period of time the thread
     * who triggered that call.
     *
     * <p>This strategy should be used only if, for some reasons, no additional thread should be
     * allocated for cleanup. Otherwise, {@link #IN_BACKGROUND} should be preferred.
     */
    ON_SAFE_POINTS,

    /**
     * Only delete resources when the session is closed.
     *
     * <p>All resources allocated during the session will remained in memory until the session is
     * explicitly closed (or via the traditional `try-with-resource` technique). No extra task for
     * resource cleanup will be attempted.
     *
     * <p>This strategy can lead up to out-of-memory errors and its usage is not recommended, unless
     * the scope of the session is limited to execute only a small amount of operations.
     */
    ON_SESSION_CLOSE,
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
     * Controls how TensorFlow resources are cleaned up when no longer needed.
     *
     * <p>{@link ResourceCleanupStrategy#IN_BACKGROUND} is used by default.
     *
     * @param value strategy to use
     * @see ResourceCleanupStrategy
     */
    public Options resourceCleanupStrategy(ResourceCleanupStrategy value) {
      resourceCleanupStrategy = value;
      return this;
    }

    /**
     * Configures the session based on the data found in the provided configuration.
     *
     * @param value a config protocol buffer
     * @see <a
     *     href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/core/protobuf/config.proto"/>
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
    private ResourceCleanupStrategy resourceCleanupStrategy;
    private ConfigProto config;

    private Options() {
      async = false;
      devicePlacementPolicy = DevicePlacementPolicy.SILENT;
      resourceCleanupStrategy = ResourceCleanupStrategy.IN_BACKGROUND;
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
    if (resourceCleanupStrategy == ResourceCleanupStrategy.ON_SAFE_POINTS) {
      nativeRefs.close();
    }
    checkSession();
    return new EagerOperationBuilder(this, type, name);
  }

  TFE_Context nativeHandle() {
    checkSession();
    return nativeHandle;
  }

  ResourceCleanupStrategy resourceCleanupStrategy() {
    return resourceCleanupStrategy;
  }

  void attach(Pointer... resources) {
    checkSession();
    for (Pointer r : resources) {
      nativeResources.attach(r);
    }
  }

  void detach(Pointer... resources) {
    checkSession();
    for (Pointer r : resources) {
      if (resourceCleanupStrategy == ResourceCleanupStrategy.ON_SAFE_POINTS) {
          nativeRefs.attach(r);
      }
      nativeResources.detach(r);
    }
  }

  private static volatile EagerSession defaultSession = null;

  private final PointerScope nativeRefs;
  private final PointerScope nativeResources;
  private final ResourceCleanupStrategy resourceCleanupStrategy;
  private TFE_Context nativeHandle;

  private EagerSession(Options options) {
    this.nativeRefs = new PointerScope();
    this.nativeResources = new PointerScope();
    this.nativeHandle = allocate(options.async, options.devicePlacementPolicy.code, options.config);
    this.resourceCleanupStrategy = options.resourceCleanupStrategy;

    nativeRefs.close(); // remove from stack
    nativeResources.close(); // remove from stack
  }

  private void checkSession() {
    if (nativeHandle == null || nativeHandle.isNull()) {
      throw new IllegalStateException("Eager session has been closed");
    }
  }

  private synchronized void doClose() {
    if (nativeHandle != null && !nativeHandle.isNull()) {
      nativeRefs.close();
      nativeResources.close();
      delete(nativeHandle);
      nativeHandle = null;
    }
  }

  private static TFE_Context allocate(boolean async, int devicePlacementPolicy, ConfigProto config) {
    try (PointerScope scope = new PointerScope()) {
      TFE_ContextOptions opts = TFE_ContextOptions.newContextOptions();
      TF_Status status = TF_Status.newStatus();
      if (config != null) {
        BytePointer configBytes = new BytePointer(config.toByteArray());
        TFE_ContextOptionsSetConfig(opts, configBytes, configBytes.capacity(), status);
        status.throwExceptionIfNotOK();
      }
      TFE_ContextOptionsSetAsync(opts, (byte)(async ? 1 : 0));
      TFE_ContextOptionsSetDevicePlacementPolicy(opts, devicePlacementPolicy);
      TFE_Context context = TFE_NewContext(opts, status);
      status.throwExceptionIfNotOK();
      return context;
    }
  }

  private static void delete(TFE_Context handle) {
    if (handle == null || handle.isNull()) return;
    TFE_DeleteContext(handle);
  }

  static {
    TensorFlow.init();
  }
}
