/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteBuffer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteLibraryHandle;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GetAllOpList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GetOpList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_LoadLibrary;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_Version;

import com.google.protobuf.InvalidProtocolBufferException;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.framework.OpList;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Library;
import org.tensorflow.internal.c_api.TF_Status;

/** Static utility methods describing the TensorFlow runtime. */
public final class TensorFlow {
  /** Returns the version of the underlying TensorFlow runtime. */
  public static String version() {
    return TF_Version().getString();
  }

  /**
   * All the TensorFlow operations available in this address space.
   *
   * @return A <a
   *     href="https://www.tensorflow.org/code/tensorflow/core/framework/op_def.proto">OpList</a>
   *     protocol buffer, which lists all the available TensorFlow operations.
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

  /**
   * Load the dynamic library in filename and register the operations and kernels present in that
   * library.
   *
   * @param filename Path of the dynamic library containing operations and kernels to load.
   * @return A <a
   *     href="https://www.tensorflow.org/code/tensorflow/core/framework/op_def.proto">OpList</a>
   *     protocol buffer message defining the operations defined in the library.
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

  private TensorFlow() {}

  /** Load the TensorFlow runtime C library. */
  static void init() {
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

  static {
    init();
  }
}
