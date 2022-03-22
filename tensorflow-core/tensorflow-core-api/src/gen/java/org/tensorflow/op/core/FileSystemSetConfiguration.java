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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TString;

/**
 * Set configuration of the file system.
 */
@OpMetadata(
    opType = FileSystemSetConfiguration.OP_NAME,
    inputsClass = FileSystemSetConfiguration.Inputs.class
)
public final class FileSystemSetConfiguration extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "FileSystemSetConfiguration";

  public FileSystemSetConfiguration(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new FileSystemSetConfiguration operation.
   *
   * @param scope current scope
   * @param scheme File system scheme.
   * @param key The name of the configuration option.
   * @param value The value of the configuration option.
   * @return a new instance of FileSystemSetConfiguration
   */
  @Endpoint(
      describeByClass = true
  )
  public static FileSystemSetConfiguration create(Scope scope, Operand<TString> scheme,
      Operand<TString> key, Operand<TString> value) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "FileSystemSetConfiguration");
    opBuilder.addInput(scheme.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(value.asOutput());
    return new FileSystemSetConfiguration(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = FileSystemSetConfiguration.class
  )
  public static class Inputs extends RawOpInputs<FileSystemSetConfiguration> {
    /**
     * File system scheme.
     */
    public final Operand<TString> scheme;

    /**
     * The name of the configuration option.
     */
    public final Operand<TString> key;

    /**
     * The value of the configuration option.
     */
    public final Operand<TString> value;

    public Inputs(GraphOperation op) {
      super(new FileSystemSetConfiguration(op), op, Arrays.asList());
      int inputIndex = 0;
      scheme = (Operand<TString>) op.input(inputIndex++);
      key = (Operand<TString>) op.input(inputIndex++);
      value = (Operand<TString>) op.input(inputIndex++);
    }
  }
}
