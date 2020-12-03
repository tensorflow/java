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

package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Unpacks a given dimension of a rank-`R` tensor into `num` rank-`(R-1)` tensors.
 * <p>
 * Unpacks `num` tensors from `value` by chipping it along the `axis` dimension.
 * For example, given a tensor of shape `(A, B, C, D)`;
 * <p>
 * If `axis == 0` then the i'th tensor in `output` is the slice `value[i, :, :, :]`
 *   and each tensor in `output` will have shape `(B, C, D)`. (Note that the
 *   dimension unpacked along is gone, unlike `split`).
 * <p>
 * If `axis == 1` then the i'th tensor in `output` is the slice `value[:, i, :, :]`
 *   and each tensor in `output` will have shape `(A, C, D)`.
 * Etc.
 * <p>
 * This is the opposite of `pack`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Unstack<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Unstack}
   */
  public static class Options {
    
    /**
     * @param axis Dimension along which to unpack.  Negative values wrap around, so the
     * valid range is `[-R, R)`.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
    
    private Long axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Unstack operation.
   * 
   * @param scope current scope
   * @param value 1-D or higher, with `axis` dimension size equal to `num`.
   * @param num 
   * @param options carries optional attributes values
   * @return a new instance of Unstack
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Unstack<T> create(Scope scope, Operand<T> value, Long num, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Unpack", scope.makeOpName("Unstack"));
    opBuilder.addInput(value.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num", num);
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Unstack<T>(opBuilder.build());
  }
  
  /**
   * @param axis Dimension along which to unpack.  Negative values wrap around, so the
   * valid range is `[-R, R)`.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }
  
  /**
   * The list of tensors unpacked from `value`.
   */
  public List<Output<T>> output() {
    return output;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) output.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Unpack";
  
  private List<Output<T>> output;
  
  @SuppressWarnings("unchecked")
  private Unstack(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputLength = operation.outputListLength("output");
    output = Arrays.asList((Output<T>[])operation.outputList(outputIdx, outputLength));
    outputIdx += outputLength;
  }
}
