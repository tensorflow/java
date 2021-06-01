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

import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The SaveDataset operation
 */
public final class SaveDataset extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SaveDataset";

  private SaveDataset(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new SaveDataset operation.
   *
   * @param scope current scope
   * @param inputDataset the inputDataset value
   * @param path the path value
   * @param shardFuncOtherArgs the shardFuncOtherArgs value
   * @param shardFunc the value of the shardFunc property
   * @param options carries optional attribute values
   * @return a new instance of SaveDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static SaveDataset create(Scope scope, Operand<? extends TType> inputDataset,
      Operand<TString> path, Iterable<Operand<?>> shardFuncOtherArgs, ConcreteFunction shardFunc,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("SaveDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(path.asOutput());
    opBuilder.addInputList(Operands.asOutputs(shardFuncOtherArgs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("shard_func", shardFunc);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
        if (opts.useShardFunc != null) {
          opBuilder.setAttr("use_shard_func", opts.useShardFunc);
        }
      }
    }
    return new SaveDataset(opBuilder.build());
  }

  /**
   * Sets the compression option.
   *
   * @param compression the compression option
   * @return this Options instance.
   */
  public static Options compression(String compression) {
    return new Options().compression(compression);
  }

  /**
   * Sets the useShardFunc option.
   *
   * @param useShardFunc the useShardFunc option
   * @return this Options instance.
   */
  public static Options useShardFunc(Boolean useShardFunc) {
    return new Options().useShardFunc(useShardFunc);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.SaveDataset}
   */
  public static class Options {
    private String compression;

    private Boolean useShardFunc;

    private Options() {
    }

    /**
     * Sets the compression option.
     *
     * @param compression the compression option
     * @return this Options instance.
     */
    public Options compression(String compression) {
      this.compression = compression;
      return this;
    }

    /**
     * Sets the useShardFunc option.
     *
     * @param useShardFunc the useShardFunc option
     * @return this Options instance.
     */
    public Options useShardFunc(Boolean useShardFunc) {
      this.useShardFunc = useShardFunc;
      return this;
    }
  }
}
