/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.xla;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Temporary op for experimenting with jax2tf.
 * DO NOT USE THIS OP. It has no backwards compatibility guarantees. It is also
 * very likely to change. This op will be used only in jax2tf under an
 * experimental flag.
 * <p>This is an experimental op to allow a smooth evolution of jax2tf towards
 * emitting and serializing StableHLO directly from JAX.
 * <p>The serialized module must return a tuple if and only if the Sout is an empty
 * list or a list with more than 1 elements. The length of Tout and Sout must
 * match. This op always returns a tuple of results, even if the module returns
 * a single result.
 * <p>The handling of dynamic shapes is work-in-progress. At the moment, the
 * JAX lowering for dynamic shapes will prepend one dimension parameter to the
 * serialized module for each dimension whose value must be passed in.
 * The &quot;args&quot; correspond to the non-dimension arguments. During compilation
 * we compute the values of the dimension arguments based on the static shapes of
 * the &quot;args&quot;. In order to do this, we encode for each dimension argument a
 * specification of how to compute its value, as a string, in the form
 * &quot;&lt;arg_idx&gt;.&lt;axis_idx&gt;&quot;.
 * E.g., the specification &quot;2.1&quot; denotes the value args[2].shape[1].
 */
@OpMetadata(
    opType = XlaCallModule.OP_NAME,
    inputsClass = XlaCallModule.Inputs.class
)
public final class XlaCallModule extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaCallModule";

  private List<Output<?>> output;

  @SuppressWarnings("unchecked")
  public XlaCallModule(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList(operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaCallModule operation.
   *
   * @param scope current scope
   * @param args A list of {@code Tensor} with possibly different types to be passed as arguments
   * to the HLO module. These are all non-dimension arguments. The dimension
   * arguments are computed at JIT time.
   * @param version Changes when we change the semantics of the op, to support backwards
   * compatibility. Version 1 carries an MHLO text or bytecode {@code module}. From
   * version 2, the op carries a StableHLO text or bytecode {@code module}.
   * @param module A serialized computation, a text or bytecode representation of
   * an mlir.Module.
   * @param Sout List of output tensor shapes.
   * @param Tout List of output tensor data types.
   * @param dimArgsSpec the specification for the dimension arguments, one for each
   * dimension argument. In absence of dynamic shapes this list is empty.
   * @return a new instance of XlaCallModule
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaCallModule create(Scope scope, Iterable<Operand<?>> args, Long version,
      String module, List<Shape> Sout, List<Class<? extends TType>> Tout,
      List<String> dimArgsSpec) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaCallModule");
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.setAttr("version", version);
    opBuilder.setAttr("module", module);
    Shape[] SoutArray = new Shape[Sout.size()];
    for (int i = 0 ; i < SoutArray.length ; i++) {
      SoutArray[i] = Sout.get(i);
    }
    opBuilder.setAttr("Sout", SoutArray);
    opBuilder.setAttr("Tout", Operands.toDataTypes(Tout));
    String[] dimArgsSpecArray = new String[dimArgsSpec.size()];
    for (int i = 0 ; i < dimArgsSpecArray.length ; i++) {
      dimArgsSpecArray[i] = dimArgsSpec.get(i);
    }
    opBuilder.setAttr("dim_args_spec", dimArgsSpecArray);
    return new XlaCallModule(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public List<Output<?>> output() {
    return output;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) output.iterator();
  }

  @OpInputsMetadata(
      outputsClass = XlaCallModule.class
  )
  public static class Inputs extends RawOpInputs<XlaCallModule> {
    /**
     * A list of {@code Tensor} with possibly different types to be passed as arguments
     * to the HLO module. These are all non-dimension arguments. The dimension
     * arguments are computed at JIT time.
     */
    public final Iterable<Operand<?>> args;

    /**
     * Changes when we change the semantics of the op, to support backwards
     * compatibility. Version 1 carries an MHLO text or bytecode `module`. From
     * version 2, the op carries a StableHLO text or bytecode `module`.
     */
    public final long version;

    /**
     * A serialized computation, a text or bytecode representation of
     * an mlir.Module.
     */
    public final String module;

    /**
     * List of output tensor shapes.
     */
    public final Shape[] Sout;

    /**
     * List of output tensor data types.
     */
    public final DataType[] Tout;

    /**
     * The Tin attribute
     */
    public final DataType[] Tin;

    /**
     * the specification for the dimension arguments, one for each
     * dimension argument. In absence of dynamic shapes this list is empty.
     */
    public final String[] dimArgsSpec;

    public Inputs(GraphOperation op) {
      super(new XlaCallModule(op), op, Arrays.asList("version", "module", "Sout", "Tout", "Tin", "dim_args_spec"));
      int inputIndex = 0;
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      version = op.attributes().getAttrInt("version");
      module = op.attributes().getAttrString("module");
      Sout = op.attributes().getAttrShapeList("Sout");
      Tout = op.attributes().getAttrTypeList("Tout");
      Tin = op.attributes().getAttrTypeList("Tin");
      dimArgsSpec = op.attributes().getAttrStringList("dim_args_spec");
    }
  }
}
