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

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Saves tensors in V2 checkpoint format.
 * <p>
 * By default, saves the named tensors in full.  If the caller wishes to save
 * specific slices of full tensors, "shape_and_slices" should be non-empty strings
 * and correspondingly well-formed.
 */
@Operator(group = "train")
public final class Save extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new Save operation.
   * 
   * @param scope current scope
   * @param prefix Must have a single element. The prefix of the V2 checkpoint to which we
   * write the tensors.
   * @param tensorNames shape {N}. The names of the tensors to be saved.
   * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be saved.
   * Empty strings indicate that they are non-partitioned tensors.
   * @param tensors `N` tensors to save.
   * @return a new instance of Save
   */
  @Endpoint(describeByClass = true)
  public static Save create(Scope scope, Operand<TString> prefix, Operand<TString> tensorNames, Operand<TString> shapeAndSlices, Iterable<Operand<?>> tensors) {
    OperationBuilder opBuilder = scope.env().opBuilder("SaveV2", scope.makeOpName("Save"));
    opBuilder.addInput(prefix.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapeAndSlices.asOutput());
    opBuilder.addInputList(Operands.asOutputs(tensors));
    opBuilder = scope.apply(opBuilder);
    return new Save(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SaveV2";
  
  private Save(Operation operation) {
    super(operation);
  }
}
