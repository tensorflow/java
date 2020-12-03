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

package org.tensorflow.op.estimator;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;

/**
 * Create the Resource for Quantile Streams.
 */
public final class BoostedTreesCreateQuantileStreamResource extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.estimator.BoostedTreesCreateQuantileStreamResource}
   */
  public static class Options {
    
    /**
     * @param maxElements int; The maximum number of data points that can be fed to the stream.
     */
    public Options maxElements(Long maxElements) {
      this.maxElements = maxElements;
      return this;
    }
    
    private Long maxElements;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BoostedTreesCreateQuantileStreamResource operation.
   * 
   * @param scope current scope
   * @param quantileStreamResourceHandle resource; Handle to quantile stream resource.
   * @param epsilon float; The required approximation error of the stream resource.
   * @param numStreams int; The number of streams managed by the resource that shares the same epsilon.
   * @param options carries optional attributes values
   * @return a new instance of BoostedTreesCreateQuantileStreamResource
   */
  @Endpoint(describeByClass = true)
  public static BoostedTreesCreateQuantileStreamResource create(Scope scope, Operand<?> quantileStreamResourceHandle, Operand<TFloat32> epsilon, Operand<TInt64> numStreams, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BoostedTreesCreateQuantileStreamResource", scope.makeOpName("BoostedTreesCreateQuantileStreamResource"));
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(numStreams.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxElements != null) {
          opBuilder.setAttr("max_elements", opts.maxElements);
        }
      }
    }
    return new BoostedTreesCreateQuantileStreamResource(opBuilder.build());
  }
  
  /**
   * @param maxElements int; The maximum number of data points that can be fed to the stream.
   */
  public static Options maxElements(Long maxElements) {
    return new Options().maxElements(maxElements);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BoostedTreesCreateQuantileStreamResource";
  
  private BoostedTreesCreateQuantileStreamResource(Operation operation) {
    super(operation);
  }
}
