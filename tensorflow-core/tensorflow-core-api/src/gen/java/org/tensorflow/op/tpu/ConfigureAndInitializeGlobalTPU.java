/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * An op that sets up the centralized structures for a distributed TPU system.
 */
@OpMetadata(
    opType = ConfigureAndInitializeGlobalTPU.OP_NAME,
    inputsClass = ConfigureAndInitializeGlobalTPU.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class ConfigureAndInitializeGlobalTPU extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ConfigureAndInitializeGlobalTPU";

  private Output<TInt32> output;

  public ConfigureAndInitializeGlobalTPU(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ConfigureAndInitializeGlobalTPU operation.
   *
   * @param scope current scope
   * @param options carries optional attribute values
   * @return a new instance of ConfigureAndInitializeGlobalTPU
   */
  @Endpoint(
      describeByClass = true
  )
  public static ConfigureAndInitializeGlobalTPU create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ConfigureAndInitializeGlobalTPU");
    if (options != null) {
      for (Options opts : options) {
        if (opts.useTfrtHostRuntime != null) {
          opBuilder.setAttr("use_tfrt_host_runtime", opts.useTfrtHostRuntime);
        }
      }
    }
    return new ConfigureAndInitializeGlobalTPU(opBuilder.build());
  }

  /**
   * Sets the useTfrtHostRuntime option.
   *
   * @param useTfrtHostRuntime the useTfrtHostRuntime option
   * @return this Options instance.
   */
  public static Options useTfrtHostRuntime(Boolean useTfrtHostRuntime) {
    return new Options().useTfrtHostRuntime(useTfrtHostRuntime);
  }

  /**
   * Gets output.
   * A vector containing the global TPU id of each TPU on the host.
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.ConfigureAndInitializeGlobalTPU}
   */
  public static class Options {
    private Boolean useTfrtHostRuntime;

    private Options() {
    }

    /**
     * Sets the useTfrtHostRuntime option.
     *
     * @param useTfrtHostRuntime the useTfrtHostRuntime option
     * @return this Options instance.
     */
    public Options useTfrtHostRuntime(Boolean useTfrtHostRuntime) {
      this.useTfrtHostRuntime = useTfrtHostRuntime;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ConfigureAndInitializeGlobalTPU.class
  )
  public static class Inputs extends RawOpInputs<ConfigureAndInitializeGlobalTPU> {
    /**
     * The useTfrtHostRuntime attribute
     */
    public final boolean useTfrtHostRuntime;

    public Inputs(GraphOperation op) {
      super(new ConfigureAndInitializeGlobalTPU(op), op, Arrays.asList("use_tfrt_host_runtime"));
      int inputIndex = 0;
      useTfrtHostRuntime = op.attributes().getAttrBool("use_tfrt_host_runtime");
    }
  }
}
