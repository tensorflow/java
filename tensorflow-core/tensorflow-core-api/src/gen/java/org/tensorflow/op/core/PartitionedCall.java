/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import java.util.Iterator;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * returns {@code f(inputs)}, where {@code f}'s body is placed and partitioned.
 *
 * <p>Selects between {@link StatefulPartitionedCall} and {@link StatelessPartitionedCall} based on the statefulness of the function arguments.
 */
@Operator
public interface PartitionedCall extends Iterable<Operand<TType>> {
  /**
   * Factory method to create a class wrapping a new StatefulPartitionedCall operation.
   *
   * @param scope current scope
   * @param args A list of input tensors.
   * @param Tout A list of output types.
   * @param f <pre>
   *   A function that takes 'args', a list of tensors, and returns 'output',
   *   another list of tensors. Input and output types are specified by 'Tin'
   *   and 'Tout'. The function body of f will be placed and partitioned across
   *   devices, setting this op apart from the regular Call op. This op is
   *   stateful.
   * </pre>
   * @param options carries optional attribute values
   * @return a new instance of PartitionedCall
   */
  @Endpoint(
      describeByClass = true
  )
  static PartitionedCall create(Scope scope, Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f, Options... options) {
    boolean isStateful = false;
    if (f.isStateful()) {
      isStateful = true;
    }
    if (isStateful) {
      return StatefulPartitionedCall.create(scope, args, Tout, f, options);
    } else {
      return StatelessPartitionedCall.create(scope, args, Tout, f, options);
    }
  }

  /**
   * Sets the config option.
   *
   * @param config the config option
   * @return this Options instance.
   */
  static Options config(String config) {
    return new Options().config(config);
  }

  /**
   * Sets the configProto option.
   *
   * @param configProto the configProto option
   * @return this Options instance.
   */
  static Options configProto(String configProto) {
    return new Options().configProto(configProto);
  }

  /**
   * Sets the executorType option.
   *
   * @param executorType the executorType option
   * @return this Options instance.
   */
  static Options executorType(String executorType) {
    return new Options().executorType(executorType);
  }

  /**
   * Gets output.
   * A list of return values.
   * @return output.
   */
  List<Output<?>> output();

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  Iterator<Operand<TType>> iterator();

  /**
   * Optional attributes for {@link org.tensorflow.op.core.PartitionedCall}
   */
  class Options {
    String config;

    String configProto;

    String executorType;

    private Options() {
    }

    /**
     * Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     */
    public Options config(String config) {
      this.config = config;
      return this;
    }

    /**
     * Sets the configProto option.
     *
     * @param configProto the configProto option
     * @return this Options instance.
     */
    public Options configProto(String configProto) {
      this.configProto = configProto;
      return this;
    }

    /**
     * Sets the executorType option.
     *
     * @param executorType the executorType option
     * @return this Options instance.
     */
    public Options executorType(String executorType) {
      this.executorType = executorType;
      return this;
    }
  }
}
