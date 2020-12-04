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
import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Takes the given number of completed elements from a barrier.
 * <p>
 * This operation concatenates completed-element component tensors along
 * the 0th dimension to make a single component tensor.
 * <p>
 * Elements come out of the barrier when they are complete, and in the order
 * in which they were placed into the barrier.  The indices output provides
 * information about the batch in which each element was originally inserted
 * into the barrier.
 */
@Operator
public final class BarrierTakeMany extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.BarrierTakeMany}
   */
  public static class Options {
    
    /**
     * @param allowSmallBatch Allow to return less than num_elements items if barrier is
     * already closed.
     */
    public Options allowSmallBatch(Boolean allowSmallBatch) {
      this.allowSmallBatch = allowSmallBatch;
      return this;
    }
    
    /**
     * @param waitForIncomplete 
     */
    public Options waitForIncomplete(Boolean waitForIncomplete) {
      this.waitForIncomplete = waitForIncomplete;
      return this;
    }
    
    /**
     * @param timeoutMs If the queue is empty, this operation will block for up to
     * timeout_ms milliseconds.
     * Note: This option is not supported yet.
     */
    public Options timeoutMs(Long timeoutMs) {
      this.timeoutMs = timeoutMs;
      return this;
    }
    
    private Boolean allowSmallBatch;
    private Boolean waitForIncomplete;
    private Long timeoutMs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BarrierTakeMany operation.
   * 
   * @param scope current scope
   * @param handle The handle to a barrier.
   * @param numElements A single-element tensor containing the number of elements to
   * take.
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of BarrierTakeMany
   */
  @Endpoint(describeByClass = true)
  public static BarrierTakeMany create(Scope scope, Operand<TString> handle, Operand<TInt32> numElements, List<DataType<?>> componentTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BarrierTakeMany", scope.makeOpName("BarrierTakeMany"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(numElements.asOutput());
    opBuilder = scope.apply(opBuilder);
    DataType[] componentTypesArray = new DataType[componentTypes.size()];
    for (int i = 0; i < componentTypesArray.length; ++i) {
      componentTypesArray[i] = componentTypes.get(i);
    }
    opBuilder.setAttr("component_types", componentTypesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.allowSmallBatch != null) {
          opBuilder.setAttr("allow_small_batch", opts.allowSmallBatch);
        }
        if (opts.waitForIncomplete != null) {
          opBuilder.setAttr("wait_for_incomplete", opts.waitForIncomplete);
        }
        if (opts.timeoutMs != null) {
          opBuilder.setAttr("timeout_ms", opts.timeoutMs);
        }
      }
    }
    return new BarrierTakeMany(opBuilder.build());
  }
  
  /**
   * @param allowSmallBatch Allow to return less than num_elements items if barrier is
   * already closed.
   */
  public static Options allowSmallBatch(Boolean allowSmallBatch) {
    return new Options().allowSmallBatch(allowSmallBatch);
  }
  
  /**
   * @param waitForIncomplete 
   */
  public static Options waitForIncomplete(Boolean waitForIncomplete) {
    return new Options().waitForIncomplete(waitForIncomplete);
  }
  
  /**
   * @param timeoutMs If the queue is empty, this operation will block for up to
   * timeout_ms milliseconds.
   * Note: This option is not supported yet.
   */
  public static Options timeoutMs(Long timeoutMs) {
    return new Options().timeoutMs(timeoutMs);
  }
  
  /**
   * A one-dimensional tensor of indices, with length num_elems.
   * These indices refer to the batch in which the values were placed into the
   * barrier (starting with MIN_LONG and increasing with each BarrierInsertMany).
   */
  public Output<TInt64> indices() {
    return indices;
  }
  
  /**
   * A one-dimensional tensor of keys, with length num_elements.
   */
  public Output<TString> keys() {
    return keys;
  }
  
  /**
   * One any-dimensional tensor per component in a barrier element. All
   * values have length num_elements in the 0th dimension.
   */
  public List<Output<?>> values() {
    return values;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BarrierTakeMany";
  
  private Output<TInt64> indices;
  private Output<TString> keys;
  private List<Output<?>> values;
  
  private BarrierTakeMany(Operation operation) {
    super(operation);
    int outputIdx = 0;
    indices = operation.output(outputIdx++);
    keys = operation.output(outputIdx++);
    int valuesLength = operation.outputListLength("values");
    values = Arrays.asList(operation.outputList(outputIdx, valuesLength));
    outputIdx += valuesLength;
  }
}
