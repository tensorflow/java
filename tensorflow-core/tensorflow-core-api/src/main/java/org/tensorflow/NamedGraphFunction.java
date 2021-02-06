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
 ==============================================================================
 */
package org.tensorflow;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.tensorflow.internal.c_api.TF_Function;
import org.tensorflow.op.Ops;

/**
 * A {@link GraphFunction} with name-based input and output resolution.
 */
public class NamedGraphFunction extends GraphFunction {

  private final List<String> inputNames;
  private final List<String> outputNames;

  NamedGraphFunction(TF_Function nativeHandle, List<String> inputNames, List<String> outputNames) {
    super(nativeHandle, inputNames.size(), outputNames.size());
    this.inputNames = Collections.unmodifiableList(inputNames);
    this.outputNames = Collections.unmodifiableList(outputNames);
  }

  /**
   * Get the output names of the function.  Outputs will be returned from the native function in this order.
   */
  public List<String> getOutputNames() {
    return outputNames;
  }

  /**
   * Get the input names of the function.  Arguments will be passed to the native function in this order.
   */
  public List<String> getInputNames() {
    return inputNames;
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public Map<String, Operand<?>> call(Ops tf, Map<String, Operand<?>> arguments) {
    return callNamed(tf, arguments);
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public Map<String, Operand<?>> callNamed(Ops tf, Map<String, Operand<?>> arguments) {
    return tf.callNamedFunction(this, arguments);
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public Map<String, Operand<?>> callNamed(Ops tf, List<Operand<?>> arguments) {
    return tf.callNamedFunction(this, arguments);
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public Map<String, Operand<?>> callNamed(Ops tf, Operand<?>... arguments) {
    return tf.callNamedFunction(this, arguments);
  }
}
