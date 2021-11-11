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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Creates a handle to a Variable resource.
 */
@OpMetadata(
    opType = VarHandleOp.OP_NAME,
    inputsClass = VarHandleOp.Inputs.class
)
@Operator
public final class VarHandleOp extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "VarHandleOp";

  private Output<? extends TType> resource;

  @SuppressWarnings("unchecked")
  public VarHandleOp(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    resource = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new VarHandleOp operation.
   *
   * @param scope current scope
   * @param dtype the type of this variable. Must agree with the dtypes
   * of all ops using this variable.
   * @param shape The (possibly partially specified) shape of this variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code VarHandleOp} output and operands
   * @return a new instance of VarHandleOp
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> VarHandleOp create(Scope scope, Class<T> dtype, Shape shape,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "VarHandleOp");
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.allowedDevices != null) {
          String[] allowedDevicesArray = new String[opts.allowedDevices.size()];
          for (int i = 0 ; i < allowedDevicesArray.length ; i++) {
            allowedDevicesArray[i] = opts.allowedDevices.get(i);
          }
          opBuilder.setAttr("allowed_devices", allowedDevicesArray);
        }
      }
    }
    return new VarHandleOp(opBuilder.build());
  }

  /**
   * Sets the container option.
   *
   * @param container the container this variable is placed in.
   * @return this Options instance.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }

  /**
   * Sets the sharedName option.
   *
   * @param sharedName the name by which this variable is referred to.
   * @return this Options instance.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }

  /**
   * Sets the allowedDevices option.
   *
   * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set when the
   * output ResourceHandle represents a per-replica/partitioned resource variable.
   * @return this Options instance.
   */
  public static Options allowedDevices(List<String> allowedDevices) {
    return new Options().allowedDevices(allowedDevices);
  }

  /**
   * Sets the allowedDevices option.
   *
   * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set when the
   * output ResourceHandle represents a per-replica/partitioned resource variable.
   * @return this Options instance.
   */
  public static Options allowedDevices(String... allowedDevices) {
    return new Options().allowedDevices(allowedDevices);
  }

  /**
   * Gets resource.
   *
   * @return resource.
   */
  public Output<? extends TType> resource() {
    return resource;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) resource;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.VarHandleOp}
   */
  public static class Options {
    private String container;

    private String sharedName;

    private List<String> allowedDevices;

    private Options() {
    }

    /**
     * Sets the container option.
     *
     * @param container the container this variable is placed in.
     * @return this Options instance.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }

    /**
     * Sets the sharedName option.
     *
     * @param sharedName the name by which this variable is referred to.
     * @return this Options instance.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }

    /**
     * Sets the allowedDevices option.
     *
     * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set when the
     * output ResourceHandle represents a per-replica/partitioned resource variable.
     * @return this Options instance.
     */
    public Options allowedDevices(List<String> allowedDevices) {
      this.allowedDevices = allowedDevices;
      return this;
    }

    /**
     * Sets the allowedDevices option.
     *
     * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set when the
     * output ResourceHandle represents a per-replica/partitioned resource variable.
     * @return this Options instance.
     */
    public Options allowedDevices(String... allowedDevices) {
      this.allowedDevices = Arrays.asList(allowedDevices);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = VarHandleOp.class
  )
  public static class Inputs extends RawOpInputs<VarHandleOp> {
    /**
     * the container this variable is placed in.
     */
    public final String container;

    /**
     * the name by which this variable is referred to.
     */
    public final String sharedName;

    /**
     * the type of this variable. Must agree with the dtypes
     * of all ops using this variable.
     */
    public final DataType dtype;

    /**
     * The (possibly partially specified) shape of this variable.
     */
    public final Shape shape;

    /**
     * DEPRECATED. The allowed devices containing the resource variable. Set when the
     * output ResourceHandle represents a per-replica/partitioned resource variable.
     */
    public final String[] allowedDevices;

    public Inputs(GraphOperation op) {
      super(new VarHandleOp(op), op, Arrays.asList("container", "shared_name", "dtype", "shape", "allowed_devices"));
      int inputIndex = 0;
      container = op.attributes().getAttrString("container");
      sharedName = op.attributes().getAttrString("shared_name");
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
      allowedDevices = op.attributes().getAttrStringList("allowed_devices");
    }
  }
}
