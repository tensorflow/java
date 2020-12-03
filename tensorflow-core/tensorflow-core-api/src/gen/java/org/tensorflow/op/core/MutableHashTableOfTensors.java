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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Creates an empty hash table.
 * <p>
 * This op creates a mutable hash table, specifying the type of its keys and
 * values. Each value must be a vector. Data can be inserted into the table using
 * the insert operations. It does not support the initialization operation.
 */
@Operator
public final class MutableHashTableOfTensors extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.MutableHashTableOfTensors}
   */
  public static class Options {
    
    /**
     * @param container If non-empty, this table is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this table is shared under the given name across
     * multiple sessions.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    /**
     * @param useNodeNameSharing 
     */
    public Options useNodeNameSharing(Boolean useNodeNameSharing) {
      this.useNodeNameSharing = useNodeNameSharing;
      return this;
    }
    
    /**
     * @param valueShape 
     */
    public Options valueShape(Shape valueShape) {
      this.valueShape = valueShape;
      return this;
    }
    
    private String container;
    private String sharedName;
    private Boolean useNodeNameSharing;
    private Shape valueShape;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new MutableHashTableOfTensors operation.
   * 
   * @param scope current scope
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attributes values
   * @return a new instance of MutableHashTableOfTensors
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TType> MutableHashTableOfTensors create(Scope scope, DataType<T> keyDtype, DataType<U> valueDtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MutableHashTableOfTensorsV2", scope.makeOpName("MutableHashTableOfTensors"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("key_dtype", keyDtype);
    opBuilder.setAttr("value_dtype", valueDtype);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.useNodeNameSharing != null) {
          opBuilder.setAttr("use_node_name_sharing", opts.useNodeNameSharing);
        }
        if (opts.valueShape != null) {
          opBuilder.setAttr("value_shape", opts.valueShape);
        }
      }
    }
    return new MutableHashTableOfTensors(opBuilder.build());
  }
  
  /**
   * @param container If non-empty, this table is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this table is shared under the given name across
   * multiple sessions.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * @param useNodeNameSharing 
   */
  public static Options useNodeNameSharing(Boolean useNodeNameSharing) {
    return new Options().useNodeNameSharing(useNodeNameSharing);
  }
  
  /**
   * @param valueShape 
   */
  public static Options valueShape(Shape valueShape) {
    return new Options().valueShape(valueShape);
  }
  
  /**
   * Handle to a table.
   */
  public Output<?> tableHandle() {
    return tableHandle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) tableHandle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MutableHashTableOfTensorsV2";
  
  private Output<?> tableHandle;
  
  private MutableHashTableOfTensors(Operation operation) {
    super(operation);
    int outputIdx = 0;
    tableHandle = operation.output(outputIdx++);
  }
}
