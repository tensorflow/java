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
module org.tensorflow.framework {
  requires org.tensorflow;

  exports org.tensorflow.framework.activations;
  exports org.tensorflow.framework.constraints;
  exports org.tensorflow.framework.data;
  exports org.tensorflow.framework.initializers;
  exports org.tensorflow.framework.losses;
  exports org.tensorflow.framework.metrics;
  exports org.tensorflow.framework.metrics.exceptions;
  exports org.tensorflow.framework.optimizers;
  exports org.tensorflow.framework.regularizers;
  exports org.tensorflow.framework.utils;
  exports org.tensorflow.framework.op;
  exports org.tensorflow.framework.op.linalg;
  exports org.tensorflow.framework.op.math;
  exports org.tensorflow.framework.op.nn;
  exports org.tensorflow.framework.op.sets;
}
