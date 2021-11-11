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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Create the Resource for Quantile Streams.
 */
@OpMetadata(
    opType = BoostedTreesCreateQuantileStreamResource.OP_NAME,
    inputsClass = BoostedTreesCreateQuantileStreamResource.Inputs.class
)
public final class BoostedTreesCreateQuantileStreamResource extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BoostedTreesCreateQuantileStreamResource";

  public BoostedTreesCreateQuantileStreamResource(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new BoostedTreesCreateQuantileStreamResource operation.
   *
   * @param scope current scope
   * @param quantileStreamResourceHandle resource; Handle to quantile stream resource.
   * @param epsilon float; The required approximation error of the stream resource.
   * @param numStreams int; The number of streams managed by the resource that shares the same epsilon.
   * @param options carries optional attribute values
   * @return a new instance of BoostedTreesCreateQuantileStreamResource
   */
  @Endpoint(
      describeByClass = true
  )
  public static BoostedTreesCreateQuantileStreamResource create(Scope scope,
      Operand<? extends TType> quantileStreamResourceHandle, Operand<TFloat32> epsilon,
      Operand<TInt64> numStreams, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BoostedTreesCreateQuantileStreamResource");
    opBuilder.addInput(quantileStreamResourceHandle.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(numStreams.asOutput());
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
   * Sets the maxElements option.
   *
   * @param maxElements int; The maximum number of data points that can be fed to the stream.
   * @return this Options instance.
   */
  public static Options maxElements(Long maxElements) {
    return new Options().maxElements(maxElements);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.estimator.BoostedTreesCreateQuantileStreamResource}
   */
  public static class Options {
    private Long maxElements;

    private Options() {
    }

    /**
     * Sets the maxElements option.
     *
     * @param maxElements int; The maximum number of data points that can be fed to the stream.
     * @return this Options instance.
     */
    public Options maxElements(Long maxElements) {
      this.maxElements = maxElements;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BoostedTreesCreateQuantileStreamResource.class
  )
  public static class Inputs extends RawOpInputs<BoostedTreesCreateQuantileStreamResource> {
    /**
     * resource; Handle to quantile stream resource.
     */
    public final Operand<? extends TType> quantileStreamResourceHandle;

    /**
     * float; The required approximation error of the stream resource.
     */
    public final Operand<TFloat32> epsilon;

    /**
     * int; The number of streams managed by the resource that shares the same epsilon.
     */
    public final Operand<TInt64> numStreams;

    /**
     * int; The maximum number of data points that can be fed to the stream.
     */
    public final long maxElements;

    public Inputs(GraphOperation op) {
      super(new BoostedTreesCreateQuantileStreamResource(op), op, Arrays.asList("max_elements"));
      int inputIndex = 0;
      quantileStreamResourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
      epsilon = (Operand<TFloat32>) op.input(inputIndex++);
      numStreams = (Operand<TInt64>) op.input(inputIndex++);
      maxElements = op.attributes().getAttrInt("max_elements");
    }
  }
}
