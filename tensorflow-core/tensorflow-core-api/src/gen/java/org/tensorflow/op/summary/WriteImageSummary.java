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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 */
public final class WriteImageSummary extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.summary.WriteImageSummary}
   */
  public static class Options {
    
    /**
     * @param maxImages 
     */
    public Options maxImages(Long maxImages) {
      this.maxImages = maxImages;
      return this;
    }
    
    private Long maxImages;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new WriteImageSummary operation.
   * 
   * @param scope current scope
   * @param writer 
   * @param step 
   * @param tag 
   * @param tensor 
   * @param badColor 
   * @param options carries optional attributes values
   * @return a new instance of WriteImageSummary
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> WriteImageSummary create(Scope scope, Operand<?> writer, Operand<TInt64> step, Operand<TString> tag, Operand<T> tensor, Operand<TUint8> badColor, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("WriteImageSummary", scope.makeOpName("WriteImageSummary"));
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(badColor.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxImages != null) {
          opBuilder.setAttr("max_images", opts.maxImages);
        }
      }
    }
    return new WriteImageSummary(opBuilder.build());
  }
  
  /**
   * @param maxImages 
   */
  public static Options maxImages(Long maxImages) {
    return new Options().maxImages(maxImages);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "WriteImageSummary";
  
  private WriteImageSummary(Operation operation) {
    super(operation);
  }
}
