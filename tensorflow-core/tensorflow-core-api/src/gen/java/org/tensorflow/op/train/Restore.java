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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Restores tensors from a V2 checkpoint.
 * <p>
 * For backward compatibility with the V1 format, this Op currently allows
 * restoring from a V1 checkpoint as well:
 *   - This Op first attempts to find the V2 index file pointed to by "prefix", and
 *     if found proceed to read it as a V2 checkpoint;
 *   - Otherwise the V1 read path is invoked.
 * Relying on this behavior is not recommended, as the ability to fall back to read
 * V1 might be deprecated and eventually removed.
 * <p>
 * By default, restores the named tensors in full.  If the caller wishes to restore
 * specific slices of stored tensors, "shape_and_slices" should be non-empty
 * strings and correspondingly well-formed.
 * <p>
 * Callers must ensure all the named tensors are indeed stored in the checkpoint.
 */
@Operator(group = "train")
public final class Restore extends RawOp implements Iterable<Operand<TType>> {
  
  /**
   * Factory method to create a class wrapping a new Restore operation.
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
  @Endpoint(describeByClass = true)
  public static Restore create(Scope scope, Operand<TString> prefix, Operand<TString> tensorNames, Operand<TString> shapeAndSlices, List<Class<? extends TType>> dtypes) {
    OperationBuilder opBuilder = scope.env().opBuilder("RestoreV2", scope.makeOpName("Restore"));
    opBuilder.addInput(prefix.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapeAndSlices.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtypes", Operands.toDataTypes(dtypes));
    return new Restore(opBuilder.build());
  }
  
  /**
   * shape {N}.  The restored tensors, whose shapes are read from the
   * checkpoint directly.
   */
  public List<Output<?>> tensors() {
    return tensors;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) tensors.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RestoreV2";
  
  private List<Output<?>> tensors;
  
  private Restore(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int tensorsLength = operation.outputListLength("tensors");
    tensors = Arrays.asList(operation.outputList(outputIdx, tensorsLength));
    outputIdx += tensorsLength;
  }
}
