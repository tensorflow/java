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

package org.tensorflow.op.data;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Creates a dataset that emits the lines of one or more text files.
 */
public final class TextLineDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new TextLineDataset operation.
   * 
   * @param scope current scope
   * @param filenames A scalar or a vector containing the name(s) of the file(s) to be
   * read.
   * @param compressionType A scalar containing either (i) the empty string (no
   * compression), (ii) "ZLIB", or (iii) "GZIP".
   * @param bufferSize A scalar containing the number of bytes to buffer.
   * @return a new instance of TextLineDataset
   */
  public static TextLineDataset create(Scope scope, Operand<TString> filenames, Operand<TString> compressionType, Operand<TInt64> bufferSize) {
    OperationBuilder opBuilder = scope.env().opBuilder("TextLineDataset", scope.makeOpName("TextLineDataset"));
    opBuilder.addInput(filenames.asOutput());
    opBuilder.addInput(compressionType.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new TextLineDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  private TextLineDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
