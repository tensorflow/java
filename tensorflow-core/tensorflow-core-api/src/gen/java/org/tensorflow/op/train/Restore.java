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

package org.tensorflow.op.train;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Restores tensors from a V2 checkpoint.
 * For backward compatibility with the V1 format, this Op currently allows
 * restoring from a V1 checkpoint as well:
 * <ul>
 * <li>This Op first attempts to find the V2 index file pointed to by &quot;prefix&quot;, and
 * if found proceed to read it as a V2 checkpoint;</li>
 * <li>Otherwise the V1 read path is invoked.
 * Relying on this behavior is not recommended, as the ability to fall back to read
 * V1 might be deprecated and eventually removed.</li>
 * </ul>
 * <p>By default, restores the named tensors in full.  If the caller wishes to restore
 * specific slices of stored tensors, &quot;shape_and_slices&quot; should be non-empty
 * strings and correspondingly well-formed.
 * <p>Callers must ensure all the named tensors are indeed stored in the checkpoint.
 */
@OpMetadata(
    opType = Restore.OP_NAME,
    inputsClass = Restore.Inputs.class
)
@Operator(
    group = "train"
)
public final class Restore extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RestoreV2";

  private List<Output<?>> tensors;

  @SuppressWarnings("unchecked")
  public Restore(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int tensorsLength = operation.outputListLength("tensors");
    tensors = Arrays.asList(operation.outputList(outputIdx, tensorsLength));
    outputIdx += tensorsLength;
  }

  /**
   * Factory method to create a class wrapping a new RestoreV2 operation.
   *
   * @param scope current scope
   * @param prefix Must have a single element.  The prefix of a V2 checkpoint.
   * @param tensorNames shape {N}.  The names of the tensors to be restored.
   * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be restored.
   * Empty strings indicate that they are non-partitioned tensors.
   * @param dtypes shape {N}.  The list of expected dtype for the tensors.  Must match
   * those stored in the checkpoint.
   * @return a new instance of Restore
   */
  @Endpoint(
      describeByClass = true
  )
  public static Restore create(Scope scope, Operand<TString> prefix, Operand<TString> tensorNames,
      Operand<TString> shapeAndSlices, List<Class<? extends TType>> dtypes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Restore");
    opBuilder.addInput(prefix.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapeAndSlices.asOutput());
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    return new Restore(opBuilder.build());
  }

  /**
   * Gets tensors.
   * shape {N}.  The restored tensors, whose shapes are read from the
   * checkpoint directly.
   * @return tensors.
   */
  public List<Output<?>> tensors() {
    return tensors;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) tensors.iterator();
  }

  @OpInputsMetadata(
      outputsClass = Restore.class
  )
  public static class Inputs extends RawOpInputs<Restore> {
    /**
     * Must have a single element.  The prefix of a V2 checkpoint.
     */
    public final Operand<TString> prefix;

    /**
     * shape {N}.  The names of the tensors to be restored.
     */
    public final Operand<TString> tensorNames;

    /**
     * shape {N}.  The slice specs of the tensors to be restored.
     * Empty strings indicate that they are non-partitioned tensors.
     */
    public final Operand<TString> shapeAndSlices;

    /**
     * shape {N}.  The list of expected dtype for the tensors.  Must match
     * those stored in the checkpoint.
     */
    public final DataType[] dtypes;

    public Inputs(GraphOperation op) {
      super(new Restore(op), op, Arrays.asList("dtypes"));
      int inputIndex = 0;
      prefix = (Operand<TString>) op.input(inputIndex++);
      tensorNames = (Operand<TString>) op.input(inputIndex++);
      shapeAndSlices = (Operand<TString>) op.input(inputIndex++);
      dtypes = op.attributes().getAttrTypeList("dtypes");
    }
  }
}
