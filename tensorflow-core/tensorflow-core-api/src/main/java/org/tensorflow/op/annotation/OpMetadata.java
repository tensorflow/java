/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.op.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.tensorflow.op.RawOpInputs;

/**
 * An annotation to provide metadata about an op. Should only be used by users on custom ops, will
 * be generated for non-custom ops.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpMetadata {

  /** The type of the op in the TF runtime. */
  String opType();

  /** The typesafe inputs class (which should be annotated with {@link OpInputsMetadata}). */
  @SuppressWarnings("rawtypes")
  Class<? extends RawOpInputs> inputsClass();
}
