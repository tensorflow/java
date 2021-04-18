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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteBuffer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteLibraryHandle;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GetAllOpList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GetOpList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_LoadLibrary;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_Version;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.GradFunc;
import org.tensorflow.internal.c_api.GradOpRegistry;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Library;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.op.CustomGradient;
import org.tensorflow.op.RawCustomGradient;
import org.tensorflow.op.RawGradientAdapter;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.TypedGradientAdapter;
import org.tensorflow.op.math.Add;
import org.tensorflow.proto.framework.OpList;

/**
 * Static utility methods describing the TensorFlow runtime.
 */
public final class TensorFlow {

  /**
   * Returns the version of the underlying TensorFlow runtime.
   */
  public static String version() {
    return TF_Version().getString();
  }

  /**
   * All the TensorFlow operations available in this address space.
   *
   * @return A <a href="https://www.tensorflow.org/code/tensorflow/core/framework/op_def.proto">OpList</a>
   * protocol buffer, which lists all the available TensorFlow operations.
   */
  public static OpList registeredOpList() {
    TF_Buffer buf = TF_GetAllOpList();
    try {
      return OpList.parseFrom(buf.dataAsByteBuffer());
    } catch (InvalidProtocolBufferException e) {
      throw new TensorFlowException("Cannot parse OpList protocol buffer", e);
    } finally {
      TF_DeleteBuffer(buf);
    }
  }

  private static Set<String> statefulOps;

  public static synchronized boolean isOpStateful(String opType) {
    if (statefulOps == null) {
      statefulOps =
          registeredOpList().getOpList().stream()
              .filter(x -> x.getIsStateful())
              .map(x -> x.getName())
              .collect(Collectors.toSet());
    }

    return statefulOps.contains(opType);
  }

  /**
   * Load the dynamic library in filename and register the operations and kernels present in that
   * library.
   *
   * @param filename Path of the dynamic library containing operations and kernels to load.
   * @return A <a href="https://www.tensorflow.org/code/tensorflow/core/framework/op_def.proto">OpList</a>
   * protocol buffer message defining the operations defined in the library.
   * @throws UnsatisfiedLinkError if filename cannot be loaded.
   */
  public static OpList loadLibrary(String filename) {
    TF_Library h = null;
    try {
      h = libraryLoad(filename);
    } catch (RuntimeException e) {
      throw new UnsatisfiedLinkError(e.getMessage());
    }
    try {
      return libraryOpList(h);
    } finally {
      libraryDelete(h);
    }
  }

  private static TF_Library libraryLoad(String filename) {
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_Library h = TF_LoadLibrary(filename, status);
      status.throwExceptionIfNotOK();
      return h;
    }
  }

  private static void libraryDelete(TF_Library handle) {
    if (handle != null && !handle.isNull()) {
      TF_DeleteLibraryHandle(handle);
    }
  }

  private static OpList libraryOpList(TF_Library handle) {
    TF_Buffer buf = TF_GetOpList(handle);
    try {
      return OpList.parseFrom(buf.dataAsByteBuffer());
    } catch (InvalidProtocolBufferException e) {
      throw new TensorFlowException("Cannot parse OpList protocol buffer", e);
    }
  }

  private TensorFlow() {
  }

  /**
   * Load the TensorFlow runtime C library.
   */
  static {
    try {
      NativeLibrary.load();
    } catch (Exception e) {
      /*
       * This code is called during static initialization of this and of other classes.
       * If this fails then a NoClassDefFoundError is thrown however this does not
       * include a cause. Printing the exception manually here ensures that the
       * necessary information to fix the problem is available.
       */
      System.err.println("Failed to load TensorFlow native library");
      e.printStackTrace();
      throw e;
    }
  }

  // to keep them from getting GC'd
  private static Set<GradFunc> gradientFuncs = new HashSet<>();

  /**
   * Register a custom gradient function for ops of {@code opType} type.
   * <p>
   * Note that this only works with graph gradients, and will eventually be deprecated in favor of
   * unified gradient support once it is fully supported by tensorflow core.
   *
   * @param opType   the type of op to register the gradient for. Should usually be an {@code
   *                 OP_NAME} field, i.e. {@link Add#OP_NAME}.
   * @param gradient the gradient function to use
   */
  public static void registerCustomGradient(String opType, RawCustomGradient gradient) {
    GradFunc g = new RawGradientAdapter(gradient);
    GradOpRegistry.Global().Register(opType, g);
    gradientFuncs.add(g);
  }

  /**
   * Register a custom gradient function for ops of {@code opClass} type. The actual op type is
   * detected from the class's {@code OP_NAME} field.
   *
   * @param opClass  the class of op to register the gradient for.
   * @param gradient the gradient function to use
   */
  public static <T extends RawOp> void registerCustomGradient(Class<T> opClass,
      CustomGradient<T> gradient) {
    try {
      String opName = (String) opClass.getDeclaredField("OP_NAME").get(null);
      GradFunc g = new TypedGradientAdapter<>(gradient, opClass);
      GradOpRegistry.Global().Register(opName, g);
      gradientFuncs.add(g);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      throw new IllegalArgumentException(
          "Could not get OP_NAME field for class " + opClass
              + ", ensure it is a generated op class", e);
    }
  }
}
