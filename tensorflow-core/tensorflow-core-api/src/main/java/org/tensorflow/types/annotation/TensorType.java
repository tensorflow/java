/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */
package org.tensorflow.types.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.tensorflow.TensorMapper;
import org.tensorflow.proto.framework.DataType;

/**
 * Annotation for all tensor types.
 *
 * <p>Any interface extending {@link org.tensorflow.types.family.TType TType} to be registered as a
 * tensor type must be annotated with {@code @TensorType} to provide metadata required for allocating
 * and mapping tensors of this type.</p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TensorType {

  /**
   * The data type of each elements in a tensor of this type
   */
  DataType dataType();

  /**
   * The number of bytes required one element of a tensor of type, -1 for variable-length element tensors
   */
  int byteSize();

  /**
   * The class of the {@link TensorMapper} to allocate and use for mapping tensors of this type
   */
  Class<? extends TensorMapper<?>> mapperClass();
}
