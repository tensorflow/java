/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark a method of a class annotated with {@link Operator @Operator} that should
 * generate an endpoint into {@link org.tensorflow.op.Ops Ops} or one of its groups.
 *
 * <p>It is mandatory for the annotated method to be public, static and to take as its first
 * parameter an instance of {@link org.tensorflow.op.Scope}. For example:
 *
 * <pre>{@code
 * @Operator
 * public class MyOp implements Op {
 *     @Endpoint
 *     public static MyOp create(Scope scope, Operand<?> input) {
 *       ...
 *     }
 * }
 * }</pre>
 *
 * In addition, two endpoints of the same class cannot have the same signature, or the annotation
 * processor will fail to generate the {@code Ops} classes.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoint {

  /**
   * Specify an optional group within the {@code Ops} class.
   *
   * <p>This value overrides the group selected at class level with the {@link Operator @Operator}
   * annotation for this specific endpoint.
   *
   * @see Operator#group()
   */
  String group() default "";

  /**
   * Name for the wrapper method used in the {@code Ops} class.
   *
   * <p>This value overrides the name selected at class level with the {@link Operator @Operator}
   * annotation for this specific endpoint.
   *
   * @see Operator#name()
   */
  String name() default "";

  /**
   * Indicates that the class description should be copied in the documentation of this endpoint,
   * instead of the description of the annotated method.
   *
   * <p>Tags of the annotated method, like {@code @param} and {@code @return}, are copied to
   * the endpoint documentation independently from this value.
   */
  boolean describeByClass() default false;
}
