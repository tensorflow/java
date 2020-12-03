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

package org.tensorflow.op.summary;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 */
public final class WriteAudioSummary extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.summary.WriteAudioSummary}
   */
  public static class Options {
    
    /**
     * @param maxOutputs 
     */
    public Options maxOutputs(Long maxOutputs) {
      this.maxOutputs = maxOutputs;
      return this;
    }
    
    private Long maxOutputs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new WriteAudioSummary operation.
   * 
   * @param scope current scope
   * @param writer 
   * @param step 
   * @param tag 
   * @param tensor 
   * @param sampleRate 
   * @param options carries optional attributes values
   * @return a new instance of WriteAudioSummary
   */
  @Endpoint(describeByClass = true)
  public static WriteAudioSummary create(Scope scope, Operand<?> writer, Operand<TInt64> step, Operand<TString> tag, Operand<TFloat32> tensor, Operand<TFloat32> sampleRate, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("WriteAudioSummary", scope.makeOpName("WriteAudioSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(sampleRate.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxOutputs != null) {
          opBuilder.setAttr("max_outputs", opts.maxOutputs);
        }
      }
    }
    return new WriteAudioSummary(opBuilder.build());
  }
  
  /**
   * @param maxOutputs 
   */
  public static Options maxOutputs(Long maxOutputs) {
    return new Options().maxOutputs(maxOutputs);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "WriteAudioSummary";
  
  private WriteAudioSummary(Operation operation) {
    super(operation);
  }
}
