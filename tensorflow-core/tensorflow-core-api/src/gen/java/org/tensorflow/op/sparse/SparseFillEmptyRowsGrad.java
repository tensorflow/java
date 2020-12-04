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

package org.tensorflow.op.sparse;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The gradient of SparseFillEmptyRows.
 * <p>
 * Takes vectors reverse_index_map, shaped `[N]`, and grad_values,
 * shaped `[N_full]`, where `N_full >= N` and copies data into either
 * `d_values` or `d_default_value`.  Here `d_values` is shaped `[N]` and
 * `d_default_value` is a scalar.
 * <p>
 *   d_values[j] = grad_values[reverse_index_map[j]]
 *   d_default_value = sum_{k : 0 .. N_full - 1} (
 *      grad_values[k] * 1{k not in reverse_index_map})
 * 
 * @param <T> data type for {@code dValues()} output
 */
@Operator(group = "sparse")
public final class SparseFillEmptyRowsGrad<T extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SparseFillEmptyRowsGrad operation.
   * 
   * @param scope current scope
   * @param reverseIndexMap 1-D.  The reverse index map from SparseFillEmptyRows.
   * @param gradValues 1-D.  The gradients from backprop.
   * @return a new instance of SparseFillEmptyRowsGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SparseFillEmptyRowsGrad<T> create(Scope scope, Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseFillEmptyRowsGrad", scope.makeOpName("SparseFillEmptyRowsGrad"));
    opBuilder.addInput(reverseIndexMap.asOutput());
    opBuilder.addInput(gradValues.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseFillEmptyRowsGrad<T>(opBuilder.build());
  }
  
  /**
   * 1-D.  The backprop into values.
   */
  public Output<T> dValues() {
    return dValues;
  }
  
  /**
   * 0-D.  The backprop into default_value.
   */
  public Output<T> dDefaultValue() {
    return dDefaultValue;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SparseFillEmptyRowsGrad";
  
  private Output<T> dValues;
  private Output<T> dDefaultValue;
  
  private SparseFillEmptyRowsGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    dValues = operation.output(outputIdx++);
    dDefaultValue = operation.output(outputIdx++);
  }
}
