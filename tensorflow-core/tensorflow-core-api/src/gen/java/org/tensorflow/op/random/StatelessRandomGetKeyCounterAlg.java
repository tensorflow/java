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

package org.tensorflow.op.random;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Picks the best algorithm based on device, and scrambles seed into key and counter.
 * This op picks the best counter-based RNG algorithm based on device, and scrambles a shape-[2] seed into a key and a counter, both needed by the counter-based algorithm. The scrambling is opaque but approximately satisfies the property that different seed results in different key/counter pair (which will in turn result in different random numbers).
 */
@OpMetadata(
    opType = StatelessRandomGetKeyCounterAlg.OP_NAME,
    inputsClass = StatelessRandomGetKeyCounterAlg.Inputs.class
)
public final class StatelessRandomGetKeyCounterAlg extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomGetKeyCounterAlg";

  private Output<? extends TType> key;

  private Output<? extends TType> counter;

  private Output<TInt32> alg;

  @SuppressWarnings("unchecked")
  public StatelessRandomGetKeyCounterAlg(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    key = operation.output(outputIdx++);
    counter = operation.output(outputIdx++);
    alg = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomGetKeyCounterAlg operation.
   *
   * @param scope current scope
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomGetKeyCounterAlg
   */
  @Endpoint(
      describeByClass = true
  )
  public static StatelessRandomGetKeyCounterAlg create(Scope scope,
      Operand<? extends TNumber> seed) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessRandomGetKeyCounterAlg");
    opBuilder.addInput(seed.asOutput());
    return new StatelessRandomGetKeyCounterAlg(opBuilder.build());
  }

  /**
   * Gets key.
   * Key for the counter-based RNG algorithm (shape uint64[1]).
   * @return key.
   */
  public Output<? extends TType> key() {
    return key;
  }

  /**
   * Gets counter.
   * Counter for the counter-based RNG algorithm. Since counter size is algorithm-dependent, this output will be right-padded with zeros to reach shape uint64[2] (the current maximal counter size among algorithms).
   * @return counter.
   */
  public Output<? extends TType> counter() {
    return counter;
  }

  /**
   * Gets alg.
   * The RNG algorithm (shape int32[]).
   * @return alg.
   */
  public Output<TInt32> alg() {
    return alg;
  }

  @OpInputsMetadata(
      outputsClass = StatelessRandomGetKeyCounterAlg.class
  )
  public static class Inputs extends RawOpInputs<StatelessRandomGetKeyCounterAlg> {
    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    public Inputs(GraphOperation op) {
      super(new StatelessRandomGetKeyCounterAlg(op), op, Arrays.asList("Tseed"));
      int inputIndex = 0;
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      Tseed = op.attributes().getAttrType("Tseed");
    }
  }
}
