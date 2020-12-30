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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Returns the set of files matching one or more glob patterns.
 * <p>
 * Note that this routine only supports wildcard characters in the
 * basename portion of the pattern, not in the directory portion.
 * Note also that the order of filenames returned is deterministic.
 */
@Operator(group = "io")
public final class MatchingFiles extends RawOp implements Operand<TString> {
  
  /**
   * Factory method to create a class wrapping a new MatchingFiles operation.
   * 
   * @param scope current scope
   * @param pattern Shell wildcard pattern(s). Scalar or vector of type string.
   * @return a new instance of MatchingFiles
   */
  @Endpoint(describeByClass = true)
  public static MatchingFiles create(Scope scope, Operand<TString> pattern) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatchingFiles", scope.makeOpName("MatchingFiles"));
    opBuilder.addInput(pattern.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MatchingFiles(opBuilder.build());
  }
  
  /**
   * A vector of matching filenames.
   */
  public Output<TString> filenames() {
    return filenames;
  }
  
  @Override
  public Output<TString> asOutput() {
    return filenames;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatchingFiles";
  
  private Output<TString> filenames;
  
  private MatchingFiles(Operation operation) {
    super(operation);
    int outputIdx = 0;
    filenames = operation.output(outputIdx++);
  }
}
