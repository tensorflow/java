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

/**
 * Code to generate the Java side implementations of TensorFlow's ops based on the TensorFlow op
 * definition files.
 */
module tensorflow.generator {
  requires tensorflow.nativelib;
  requires java.compiler;
  requires com.github.javaparser.core;
  requires com.google.protobuf;
  requires com.google.common;
  requires com.squareup.javapoet;
  requires org.commonmark;
  requires spring.core;

  exports org.tensorflow.generator.op;
  exports org.tensorflow.generator.op.processor;
}
