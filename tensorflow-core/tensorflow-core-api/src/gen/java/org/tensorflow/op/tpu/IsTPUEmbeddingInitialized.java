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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TBool;

/**
 * Whether TPU Embedding is initialized in a distributed TPU system.
 */
@OpMetadata(
    opType = IsTPUEmbeddingInitialized.OP_NAME,
    inputsClass = IsTPUEmbeddingInitialized.Inputs.class
)
public final class IsTPUEmbeddingInitialized extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsTPUEmbeddingInitialized";

  private Output<TBool> isTpuEmbeddingInitialized;

  public IsTPUEmbeddingInitialized(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    isTpuEmbeddingInitialized = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsTPUEmbeddingInitialized operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of IsTPUEmbeddingInitialized
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsTPUEmbeddingInitialized create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsTPUEmbeddingInitialized");
    if (options != null) {
      for (Options opts : options) {
        if (opts.config != null) {
          opBuilder.setAttr("config", opts.config);
        }
      }
    }
    return new IsTPUEmbeddingInitialized(opBuilder.build());
  }

  /**
   * Sets the config option.
   *
   * @param config the config option
   * @return this Options instance.
   */
  public static Options config(String config) {
    return new Options().config(config);
  }

  /**
   * Gets isTpuEmbeddingInitialized.
   *
   * @return isTpuEmbeddingInitialized.
   */
  public Output<TBool> isTpuEmbeddingInitialized() {
    return isTpuEmbeddingInitialized;
  }

  @Override
  public Output<TBool> asOutput() {
    return isTpuEmbeddingInitialized;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.IsTPUEmbeddingInitialized}
   */
  public static class Options {
    private String config;

    private Options() {
    }

    /**
     * Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     */
    public Options config(String config) {
      this.config = config;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = IsTPUEmbeddingInitialized.class
  )
  public static class Inputs extends RawOpInputs<IsTPUEmbeddingInitialized> {
    /**
     * The config attribute
     */
    public final String config;

    public Inputs(GraphOperation op) {
      super(new IsTPUEmbeddingInitialized(op), op, Arrays.asList("config"));
      int inputIndex = 0;
      config = op.attributes().getAttrString("config");
    }
  }
}
