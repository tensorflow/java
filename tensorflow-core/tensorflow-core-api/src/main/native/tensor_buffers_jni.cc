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

#include "src/main/native/tensor_buffers_jni.h"

#include "tensorflow/c/c_api.h"
#include "src/main/native/exception_jni.h"

namespace {

TF_Tensor* requireHandle(JNIEnv* env, jlong handle) {
  if (handle == 0) {
    throwException(env, kNullPointerException, "close() was called on the Tensor");
    return nullptr;
  }
  return reinterpret_cast<TF_Tensor*>(handle);
}

} // namespace

JNIEXPORT jobject JNICALL Java_org_tensorflow_internal_buffer_TensorBuffers_directBuffer(
    JNIEnv* env, jclass clazz, jlong handle) {

  TF_Tensor* t = requireHandle(env, handle);
  if (t == nullptr) return nullptr;
  void* data = TF_TensorData(t);
  const size_t sz = TF_TensorByteSize(t);

  return env->NewDirectByteBuffer(data, static_cast<jlong>(sz));
}
