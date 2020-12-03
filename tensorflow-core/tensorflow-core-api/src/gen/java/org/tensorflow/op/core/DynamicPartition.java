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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Partitions `data` into `num_partitions` tensors using indices from `partitions`.
 * <p>
 * For each index tuple `js` of size `partitions.ndim`, the slice `data[js, ...]`
 * becomes part of `outputs[partitions[js]]`.  The slices with `partitions[js] = i`
 * are placed in `outputs[i]` in lexicographic order of `js`, and the first
 * dimension of `outputs[i]` is the number of entries in `partitions` equal to `i`.
 * In detail,
 * <pre>{@code
 *     outputs[i].shape = [sum(partitions == i)] + data.shape[partitions.ndim:]
 * 
 *     outputs[i] = pack([data[js, ...] for js if partitions[js] == i])
 * }</pre>
 * `data.shape` must start with `partitions.shape`.
 * <p>
 * For example:
 * <pre>{@code
 *     # Scalar partitions.
 *     partitions = 1
 *     num_partitions = 2
 *     data = [10, 20]
 *     outputs[0] = []  # Empty with shape [0, 2]
 *     outputs[1] = [[10, 20]]
 * 
 *     # Vector partitions.
 *     partitions = [0, 0, 1, 1, 0]
 *     num_partitions = 2
 *     data = [10, 20, 30, 40, 50]
 *     outputs[0] = [10, 20, 50]
 *     outputs[1] = [30, 40]
 * }</pre>
 * See `dynamic_stitch` for an example on how to merge partitions back.
 * <p>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/DynamicPartition.png" alt>
 * </div>
 * 
 * @param <T> data type for {@code outputs()} output
 */
@Operator
public final class DynamicPartition<T extends TType> extends RawOp implements Iterable<Operand<T>> {
  
  /**
   * Factory method to create a class wrapping a new DynamicPartition operation.
   * 
   * @param scope current scope
   * @param data 
   * @param partitions Any shape.  Indices in the range `[0, num_partitions)`.
   * @param numPartitions The number of partitions to output.
   * @return a new instance of DynamicPartition
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> DynamicPartition<T> create(Scope scope, Operand<T> data, Operand<TInt32> partitions, Long numPartitions) {
    OperationBuilder opBuilder = scope.env().opBuilder("DynamicPartition", scope.makeOpName("DynamicPartition"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(partitions.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_partitions", numPartitions);
    return new DynamicPartition<T>(opBuilder.build());
  }
  
  /**
   */
  public List<Output<T>> outputs() {
    return outputs;
  }
  
  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<T>> iterator() {
    return (Iterator) outputs.iterator();
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DynamicPartition";
  
  private List<Output<T>> outputs;
  
  @SuppressWarnings("unchecked")
  private DynamicPartition(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputsLength = operation.outputListLength("outputs");
    outputs = Arrays.asList((Output<T>[])operation.outputList(outputIdx, outputsLength));
    outputIdx += outputsLength;
  }
}
