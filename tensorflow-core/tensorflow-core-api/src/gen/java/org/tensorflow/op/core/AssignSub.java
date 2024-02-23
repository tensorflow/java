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

package org.tensorflow.op.core;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Update 'ref' by subtracting 'value' from it.
 * This operation outputs &quot;ref&quot; after the update is done.
 * This makes it easier to chain operations that need to use the reset value.
 *
 * @param <T> data type for {@code output_ref} output
 */
@OpMetadata(
    opType = AssignSub.OP_NAME,
    inputsClass = AssignSub.Inputs.class
)
@Operator
public final class AssignSub<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AssignSub";

  private Output<T> outputRef;

  public AssignSub(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputRef = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AssignSub operation.
   *
   * @param scope current scope
   * @param ref Should be from a {@code Variable} node.
   * @param value The value to be subtracted to the variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AssignSub} output and operands
   * @return a new instance of AssignSub
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> AssignSub<T> create(Scope scope, Operand<T> ref, Operand<T> value,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AssignSub");
    opBuilder.addInput(ref.asOutput());
    opBuilder.addInput(value.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new AssignSub<>(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If True, the subtraction will be protected by a lock;
   * otherwise the behavior is undefined, but may exhibit less contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Gets outputRef.
   * = Same as &quot;ref&quot;.  Returned as a convenience for operations that want
   * to use the new value after the variable has been updated.
   * @return outputRef.
   */
  public Output<T> outputRef() {
    return outputRef;
  }

  @Override
  public Output<T> asOutput() {
    return outputRef;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.AssignSub}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If True, the subtraction will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AssignSub.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<AssignSub<T>> {
    /**
     * Should be from a {@code Variable} node.
     */
    public final Operand<T> ref;

    /**
     * The value to be subtracted to the variable.
     */
    public final Operand<T> value;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If True, the subtraction will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new AssignSub<>(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      ref = (Operand<T>) op.input(inputIndex++);
      value = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
