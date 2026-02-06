/*
Copyright 2024 The TensorFlow Authors. All Rights Reserved.

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

/** Native interop with the TensorFlow C API. */
module tensorflow.nativelib {
  requires transitive org.bytedeco.javacpp;
  requires transitive com.google.protobuf;
  requires java.logging;

  exports org.tensorflow.exceptions;
  exports org.tensorflow.internal;
  exports org.tensorflow.internal.c_api;
  exports org.tensorflow.internal.c_api.global;
  exports org.tensorflow.internal.c_api.presets to
      org.bytedeco.javacpp,
      tensorflow;
  exports org.tensorflow.proto;
  exports org.tensorflow.proto.data;
  exports org.tensorflow.proto.data.model;
  exports org.tensorflow.proto.data.experimental;
  exports org.tensorflow.proto.core;
  exports org.tensorflow.proto.core.platform;
  exports org.tensorflow.proto.profiler;
  exports org.tensorflow.proto.distributed_runtime;
  exports org.tensorflow.proto.eager;
  exports org.tensorflow.proto.error;
}
