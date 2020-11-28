/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_Execute;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpAddInput;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpAddInputList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrBool;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrBoolList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrFloat;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrFloatList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrInt;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrIntList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrShape;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrShapeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrString;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrStringList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrType;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetAttrTypeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpSetDevice;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.SizeTPointer;
import org.tensorflow.internal.c_api.TFE_Context;
import org.tensorflow.internal.c_api.TFE_Op;
import org.tensorflow.internal.c_api.TFE_TensorHandle;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;

/**
 * An {@link OperationBuilder} for building {@link Operation Operations} that are executed eagerly.
 */
final class EagerOperationBuilder implements OperationBuilder {

  EagerOperationBuilder(EagerSession session, String type, String name) {
    this.session = session;
    this.type = type;
    this.name = name;
    this.opHandle = allocate(session, type);
  }

  @Override
  public EagerOperation build() {
    TFE_TensorHandle[] tensorHandles = execute(opHandle, session);
    EagerOperation operation =
        new EagerOperation(session, opHandle, tensorHandles, type, name);
    // Release our reference to the native op handle now that we transferred its
    // ownership to the EagerOperation
    session.detach(opHandle);
    return operation;
  }

  @Override
  public EagerOperationBuilder addInput(Output<?> input) {
    addInput(opHandle, (TFE_TensorHandle)input.getUnsafeNativeHandle());
    return this;
  }

  @Override
  public EagerOperationBuilder addInputList(Output<?>[] inputs) {
    TFE_TensorHandle[] inputHandles = new TFE_TensorHandle[inputs.length];
    for (int i = 0; i < inputs.length; ++i) {
      inputHandles[i] = (TFE_TensorHandle)inputs[i].getUnsafeNativeHandle();
    }
    addInputList(opHandle, inputHandles);
    return this;
  }

  @Override
  public OperationBuilder addControlInput(Operation control) {
    // No-op.  Any operations passed to this method will already be evaluated (b/c eager evaluation).
    return this;
  }

  @Override
  public EagerOperationBuilder setDevice(String device) {
    setDevice(opHandle, device);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, String value) {
    return setAttr(name, value.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public EagerOperationBuilder setAttr(String name, String[] values) {
    Charset utf8 = StandardCharsets.UTF_8;
    byte[][] objects = new byte[values.length][];
    for (int i = 0; i < values.length; ++i) {
      objects[i] = values[i].getBytes(utf8);
    }
    setAttrStringList(opHandle, name, objects);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, byte[] values) {
    setAttrString(opHandle, name, values);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, long value) {
    setAttrInt(opHandle, name, value);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, long[] values) {
    setAttrIntList(opHandle, name, values);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, float value) {
    setAttrFloat(opHandle, name, value);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, float[] values) {
    setAttrFloatList(opHandle, name, values);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, boolean value) {
    setAttrBool(opHandle, name, value);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, boolean[] values) {
    setAttrBoolList(opHandle, name, values);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, DataType<?> value) {
    setAttrType(opHandle, name, value.nativeCode());
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, DataType<?>[] values) {
    int[] c = new int[values.length];
    for (int i = 0; i < values.length; ++i) {
      c[i] = values[i].nativeCode();
    }
    setAttrTypeList(opHandle, name, c);
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, Tensor<?> value) {
    setAttrTensor(opHandle, name, value.nativeHandle());
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, Tensor<?>[] values) {
    // TODO (karllessard) could be supported by adding this attribute type in the eager C API
    throw new UnsupportedOperationException(
        "Tensor list attributes are not supported in eager mode");
  }

  @Override
  public EagerOperationBuilder setAttr(String name, Shape value) {
    setAttrShape(opHandle, name, value.asArray(), value.numDimensions());
    return this;
  }

  @Override
  public EagerOperationBuilder setAttr(String name, Shape[] values) {
    int[] numDimensions = new int[values.length];
    int totalNumDimensions = 0;
    for (int idx = 0; idx < values.length; ++idx) {
      int n = values[idx].numDimensions();
      numDimensions[idx] = n;
      if (n > 0) {
        totalNumDimensions += n;
      }
    }
    // Flatten the shapes into a single array to avoid too much overhead in the
    // native part
    long[] shapes = new long[totalNumDimensions];
    int shapeIdx = 0;
    for (Shape shape : values) {
      if (shape.numDimensions() > 0) {
        for (long dim : shape.asArray()) {
          shapes[shapeIdx++] = dim;
        }
      }
    }
    setAttrShapeList(opHandle, name, shapes, numDimensions);
    return this;
  }

  private TFE_Op opHandle;

  private final EagerSession session;
  private final String type;
  private final String name;

  /** This value should be >= to the maximum number of outputs in any op */
  private static final int MAX_OUTPUTS_PER_OP = 1000;

  private static void requireOp(TFE_Op handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Operation has already been built");
    }
  }

  private static void requireContext(TFE_Context handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Context has been deleted");
    }
  }

  private static void requireTensor(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Tensor");
    }
  }

  private static void requireTensorHandle(TFE_TensorHandle handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Tensor handle has been deleted");
    }
  }

  private static TFE_Op allocate(EagerSession session, String type) {
    requireContext(session.nativeHandle());
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_Op op = TFE_Op.newOp(session.nativeHandle(), type, status);
      status.throwExceptionIfNotOK();
      session.attach(op);
      return op;
    }
  }

  private static TFE_TensorHandle[] execute(TFE_Op opHandle, EagerSession session) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      IntPointer numRetvals = new IntPointer(1).put(MAX_OUTPUTS_PER_OP);
      PointerPointer<TFE_TensorHandle> retvals = new PointerPointer<TFE_TensorHandle>(MAX_OUTPUTS_PER_OP);
      TF_Status status = TF_Status.newStatus();
      TFE_Execute(opHandle, retvals, numRetvals, status);
      status.throwExceptionIfNotOK();

      TFE_TensorHandle[] rethandles = new TFE_TensorHandle[numRetvals.get()];
      for (int i = 0; i < rethandles.length; ++i) {
        rethandles[i] = retvals.get(TFE_TensorHandle.class, i).withDeallocator();
        session.attach(rethandles[i]);
      }
      return rethandles;
    }
  }

  private static void addInput(TFE_Op opHandle, TFE_TensorHandle tensorHandle) {
    requireOp(opHandle);
    requireTensorHandle(tensorHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_OpAddInput(opHandle, tensorHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void addInputList(TFE_Op opHandle, TFE_TensorHandle[] tensorHandles) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      PointerPointer<TFE_TensorHandle> tensorPointers = new PointerPointer<TFE_TensorHandle>(tensorHandles.length);
      for (int i = 0; i < tensorHandles.length; ++i) {
        requireTensorHandle(tensorHandles[i]);
        tensorPointers.put(i, tensorHandles[i]);
      }
      TF_Status status = TF_Status.newStatus();
      TFE_OpAddInputList(opHandle, tensorPointers, tensorHandles.length, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setDevice(TFE_Op opHandle, String device) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_OpSetDevice(opHandle, device, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setAttrString(TFE_Op opHandle, String name, byte[] value) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      TFE_OpSetAttrString(opHandle, name, new BytePointer(value), value.length);
    }
  }

  private static void setAttrStringList(TFE_Op opHandle, String name, byte[][] value) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      PointerPointer<BytePointer> valuePointers = new PointerPointer<BytePointer>(value.length);
      SizeTPointer lengths = new SizeTPointer(value.length);

      for (int i = 0; i < value.length; ++i) {
        valuePointers.put(i, new BytePointer(value[i]));
        lengths.put(i, value[i].length);
      }
      TFE_OpSetAttrStringList(opHandle, name, valuePointers, lengths, value.length);
    }
  }

  private static void setAttrInt(TFE_Op opHandle, String name, long value) {
    requireOp(opHandle);
    TFE_OpSetAttrInt(opHandle, name, value);
  }

  private static void setAttrIntList(TFE_Op opHandle, String name, long[] values) {
    requireOp(opHandle);
    TFE_OpSetAttrIntList(opHandle, name, values, values.length);
  }

  private static void setAttrFloat(TFE_Op opHandle, String name, float value) {
    requireOp(opHandle);
    TFE_OpSetAttrFloat(opHandle, name, value);
  }

  private static void setAttrFloatList(TFE_Op opHandle, String name, float[] values) {
    requireOp(opHandle);
    TFE_OpSetAttrFloatList(opHandle, name, values, values.length);
  }

  private static void setAttrBool(TFE_Op opHandle, String name, boolean value) {
    requireOp(opHandle);
    TFE_OpSetAttrBool(opHandle, name, (byte)(value ? 1 : 0));
  }

  private static void setAttrBoolList(TFE_Op opHandle, String name, boolean[] values) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      TFE_OpSetAttrBoolList(opHandle, name, new BytePointer(new BooleanPointer(values)), values.length);
    }
  }

  private static void setAttrType(TFE_Op opHandle, String name, int type) {
    requireOp(opHandle);
    TFE_OpSetAttrType(opHandle, name, type);
  }

  private static void setAttrTypeList(TFE_Op opHandle, String name, int[] types) {
    requireOp(opHandle);
    TFE_OpSetAttrTypeList(opHandle, name, types, types.length);
  }

  private static void setAttrTensor(TFE_Op opHandle, String name, TF_Tensor tensorHandle) {
    requireOp(opHandle);
    requireTensor(tensorHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_OpSetAttrTensor(opHandle, name, tensorHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setAttrShape(TFE_Op opHandle, String name, long[] shape, int numDims) {
    requireOp(opHandle);
    // num_dims and env->GetArrayLength(shape) are assumed to be consistent.
    // i.e., either num_dims < 0 or num_dims == env->GetArrayLength(shape).
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TFE_OpSetAttrShape(opHandle, name, shape, numDims, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setAttrShapeList(TFE_Op opHandle, String name, long[] shapes, int[] numDims) {
    requireOp(opHandle);
    try (PointerScope scope = new PointerScope()) {
      LongPointer shapesPointer = new LongPointer(shapes);
      PointerPointer<LongPointer> shapesPointers = new PointerPointer<LongPointer>(numDims.length);
      for (int i = 0; i < numDims.length; i++) {
        shapesPointers.put(i, shapesPointer);
        shapesPointer.position(shapesPointer.position() + numDims[i] * 8);
      }
      TF_Status status = TF_Status.newStatus();
      TFE_OpSetAttrShapeList(opHandle, new BytePointer(name), shapesPointers, new IntPointer(numDims),
                            numDims.length, status);
    }
  }
}
