/*
Copyright 2022 The TensorFlow Authors. All Rights Reserved.

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

/** Core module implementing the TensorFlow Java API and operator definitions. */
module tensorflow {
  requires transitive org.tensorflow.ndarray;
  requires transitive tensorflow.nativelib;
  requires java.logging;

  exports org.tensorflow;
  exports org.tensorflow.types;
  exports org.tensorflow.types.annotation;
  exports org.tensorflow.types.family;
  exports org.tensorflow.op;
  exports org.tensorflow.op.annotation;
  exports org.tensorflow.op.core;
  exports org.tensorflow.op.audio;
  exports org.tensorflow.op.bitwise;
  exports org.tensorflow.op.cluster;
  exports org.tensorflow.op.collective;
  exports org.tensorflow.op.data;
  exports org.tensorflow.op.data.experimental;
  exports org.tensorflow.op.debugging;
  exports org.tensorflow.op.dtypes;
  exports org.tensorflow.op.image;
  exports org.tensorflow.op.io;
  exports org.tensorflow.op.linalg;
  exports org.tensorflow.op.linalg.sparse;
  exports org.tensorflow.op.math;
  exports org.tensorflow.op.math.special;
  exports org.tensorflow.op.nn;
  exports org.tensorflow.op.quantization;
  exports org.tensorflow.op.ragged;
  exports org.tensorflow.op.random;
  exports org.tensorflow.op.signal;
  exports org.tensorflow.op.sparse;
  exports org.tensorflow.op.strings;
  exports org.tensorflow.op.summary;
  exports org.tensorflow.op.tpu;
  exports org.tensorflow.op.train;
  exports org.tensorflow.op.xla;
}
