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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_AddControlInput;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_AddInput;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_AddInputList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FinishOperation;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewOperation;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrBool;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrBoolList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrFloat;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrFloatList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrInt;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrIntList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrShape;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrShapeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrString;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrStringList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrTensorList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrType;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetAttrTypeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetDevice;

import java.nio.charset.Charset;
import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.SizeTPointer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_OperationDescription;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;

/** An {@link OperationBuilder} for adding {@link GraphOperation}s to a {@link Graph}. */
public final class GraphOperationBuilder implements OperationBuilder {

  GraphOperationBuilder(Graph graph, String type, String name) {
    this.graph = graph;
    Graph.Reference r = graph.ref();
    try {
      this.unsafeNativeHandle = allocate(r.nativeHandle(), type, name);
    } finally {
      r.close();
    }
  }

  /**
   * Add the {@link GraphOperation} being built to the {@link Graph}.
   *
   * <p>The OperationBuilder is not usable after build() returns.
   */
  @Override
  public GraphOperation build() {
    Graph.Reference r = graph.ref();
    try {
      GraphOperation op = new GraphOperation(graph, finish(unsafeNativeHandle));
      unsafeNativeHandle = null;
      return op;
    } finally {
      r.close();
    }
  }

  @Override
  public GraphOperationBuilder addControlInput(Operation control) {
    if (!(control instanceof GraphOperation)) {
      throw new IllegalArgumentException(
          "Only GraphOperation instances can be used as control inputs");
    }
    Graph.Reference r = graph.ref();
    try {
      addControlInput(unsafeNativeHandle, ((GraphOperation) control).getUnsafeNativeHandle());
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder addInput(Output<?> input) {
    Graph.Reference r = graph.ref();
    try {
      addInput(unsafeNativeHandle, (TF_Operation)input.getUnsafeNativeHandle(), input.index());
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder addInputList(Output<?>[] inputs) {
    Graph.Reference r = graph.ref();
    try {
      TF_Operation[] opHandles = new TF_Operation[inputs.length];
      int[] indices = new int[inputs.length];
      for (int i = 0; i < inputs.length; ++i) {
        opHandles[i] = (TF_Operation)inputs[i].getUnsafeNativeHandle();
        indices[i] = inputs[i].index();
      }
      addInputList(unsafeNativeHandle, opHandles, indices);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setDevice(String device) {
    Graph.Reference r = graph.ref();
    try {
      setDevice(unsafeNativeHandle, device);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, String value) {
    setAttr(name, value.getBytes(Charset.forName("UTF-8")));
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, byte[] value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrString(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, long value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrInt(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, long[] value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrIntList(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, float value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrFloat(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, float[] value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrFloatList(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, boolean value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrBool(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, boolean[] value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrBoolList(unsafeNativeHandle, name, value);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, DataType value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrType(unsafeNativeHandle, name, value.getNumber());
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, DataType[] value) {
    int[] ctypes = new int[value.length];
    for (int i = 0; i < value.length; ++i) {
      ctypes[i] = value[i].getNumber();
    }
    Graph.Reference r = graph.ref();
    try {
      setAttrTypeList(unsafeNativeHandle, name, ctypes);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, Tensor value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrTensor(unsafeNativeHandle, name, value.asRawTensor().nativeHandle());
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, Tensor[] value) {
    TF_Tensor[] handles = new TF_Tensor[value.length];
    int idx = 0;
    for (Tensor t : value) {
      handles[idx++] = t.asRawTensor().nativeHandle();
    }
    Graph.Reference r = graph.ref();
    try {
      setAttrTensorList(unsafeNativeHandle, name, handles);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, Shape value) {
    Graph.Reference r = graph.ref();
    try {
      setAttrShape(unsafeNativeHandle, name, value.asArray(), value.numDimensions());
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, Shape[] value) {
    int[] numDimensions = new int[value.length];
    int totalNumDimensions = 0;
    for (int idx = 0; idx < value.length; ++idx) {
      int n = value[idx].numDimensions();
      numDimensions[idx] = n;
      if (n > 0) {
        totalNumDimensions += n;
      }
    }
    // Flatten the shapes into a single array to avoid too much overhead in the
    // native part
    long[] shapes = new long[totalNumDimensions];
    int shapeIdx = 0;
    for (Shape shape : value) {
      if (shape.numDimensions() > 0) {
        for (long dim : shape.asArray()) {
          shapes[shapeIdx++] = dim;
        }
      }
    }
    Graph.Reference r = graph.ref();
    try {
      setAttrShapeList(unsafeNativeHandle, name, shapes, numDimensions);
    } finally {
      r.close();
    }
    return this;
  }

  @Override
  public GraphOperationBuilder setAttr(String name, String[] value) {
    Charset utf8 = Charset.forName("UTF-8");
    byte[][] objects = new byte[value.length][];
    for (int i = 0; i < value.length; ++i) {
      objects[i] = value[i].getBytes(utf8);
    }
    Graph.Reference r = graph.ref();
    try {
      setAttrStringList(unsafeNativeHandle, name, objects);
    } finally {
      r.close();
    }
    return this;
  }

  private TF_OperationDescription unsafeNativeHandle;
  private Graph graph;

  private static void requireHandle(Pointer handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Operation has already been built");
    }
  }

  private static void resolveOutput(TF_Operation opHandle, int index, TF_Output out) {
    if (opHandle == null || opHandle.isNull()) {
      throw new IllegalStateException("close() was called on the Graph");
    }
    out.oper(opHandle);
    out.index(index);
  }

  private static void requireTensor(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Tensor");
    }
  }

  private static TF_OperationDescription allocate(TF_Graph graphHandle, String type, String name) {
    if (graphHandle == null || graphHandle.isNull()) {
      throw new IllegalStateException("close() has been called on the Graph");
    }
    return TF_NewOperation(graphHandle, type, name);
  }

  private static TF_Operation finish(TF_OperationDescription handle) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_Operation op = TF_FinishOperation(handle, status);
      status.throwExceptionIfNotOK();
      return op;
    }
  }

  private static void addInput(TF_OperationDescription handle, TF_Operation opHandle, int index) {
    try (PointerScope scope = new PointerScope()) {
      TF_Output out = new TF_Output();
      resolveOutput(opHandle, index, out);
      requireHandle(handle);
      TF_AddInput(handle, out);
    }
  }

  private static void addInputList(TF_OperationDescription handle, TF_Operation[] opHandles, int[] indices) {
    requireHandle(handle);
    if (indices.length != opHandles.length) {
      throw new IllegalArgumentException("mismatch in number of Operations ("
          + opHandles.length + ") and output indices (" + indices.length + ") provided");
    }

    try (PointerScope scope = new PointerScope()) {
      TF_Output o = new TF_Output(indices.length);
      for (int i = 0; i < indices.length; ++i) {
        resolveOutput(opHandles[i], indices[i], o.position(i));
      }
      TF_AddInputList(handle, o.position(0), indices.length);
    }
  }

  private static void addControlInput(TF_OperationDescription handle, TF_Operation opHandle) {
    if (opHandle == null || opHandle.isNull()) {
      throw new IllegalStateException("control input is not valid, "
          + "perhaps the Graph containing it has been closed()?");
    }
    requireHandle(handle);
    TF_AddControlInput(handle, opHandle);
  }

  private static void setDevice(TF_OperationDescription handle, String device) {
    requireHandle(handle);
    TF_SetDevice(handle, device);
  }

  // The names of all the setAttr* family functions below correspond to the C library types, not the
  // Java library types. Roughly, setAttrFoo calls the TensorFlow C library function: TF_SetAttrFoo.

  private static void setAttrString(TF_OperationDescription handle, String name, byte[] value) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_SetAttrString(handle, name, new BytePointer(value), value.length);
    }
  }

  private static void setAttrInt(TF_OperationDescription handle, String name, long value) {
    requireHandle(handle);
    TF_SetAttrInt(handle, name, value);
  }

  private static void setAttrIntList(TF_OperationDescription handle, String name, long[] value) {
    requireHandle(handle);
    TF_SetAttrIntList(handle, name, value, value.length);
  }

  private static void setAttrFloat(TF_OperationDescription handle, String name, float value) {
    requireHandle(handle);
    TF_SetAttrFloat(handle, name, value);
  }

  private static void setAttrFloatList(TF_OperationDescription handle, String name, float[] value) {
    requireHandle(handle);
    TF_SetAttrFloatList(handle, name, value, value.length);
  }

  private static void setAttrBool(TF_OperationDescription handle, String name, boolean value) {
    requireHandle(handle);
    TF_SetAttrBool(handle, name, (byte)(value ? 1 : 0));
  }

  private static void setAttrBoolList(TF_OperationDescription handle, String name, boolean[] value) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_SetAttrBoolList(handle, name, new BytePointer(new BooleanPointer(value)), value.length);
    }
  }

  private static void setAttrType(TF_OperationDescription handle, String name, int type) {
    requireHandle(handle);
    TF_SetAttrType(handle, name, type);
  }

  private static void setAttrTypeList(TF_OperationDescription handle, String name, int[] type) {
    requireHandle(handle);
    TF_SetAttrTypeList(handle, name, type, type.length);
  }

  private static void setAttrTensor(TF_OperationDescription handle, String name, TF_Tensor tensorHandle) {
    requireHandle(handle);
    requireTensor(tensorHandle);

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_SetAttrTensor(handle, name, tensorHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setAttrTensorList(TF_OperationDescription handle, String name, TF_Tensor[] tensorHandles) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      PointerPointer<TF_Tensor> tensors = new PointerPointer<TF_Tensor>(tensorHandles.length);
      for (int i = 0; i < tensorHandles.length; ++i) {
        requireTensor(tensorHandles[i]);
        tensors.put(i, tensorHandles[i]);
      }

      TF_Status status = TF_Status.newStatus();
      TF_SetAttrTensorList(handle, new BytePointer(name), tensors.position(0), tensorHandles.length, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void setAttrShape(TF_OperationDescription handle, String name, long[] shape, int numDims) {
    requireHandle(handle);

    // num_dims and env->GetArrayLength(shape) are assumed to be consistent.
    // i.e., either num_dims < 0 or num_dims == env->GetArrayLength(shape).
    TF_SetAttrShape(handle, name, shape, numDims);
  }

  private static void setAttrShapeList(TF_OperationDescription handle, String name, long[] shapes, int[] numDims) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      LongPointer shapesPointer = new LongPointer(shapes);
      PointerPointer<LongPointer> shapesPointers = new PointerPointer<LongPointer>(numDims.length);
      for (int i = 0; i < numDims.length; i++) {
        shapesPointers.put(i, shapesPointer);
        shapesPointer.position(shapesPointer.position() + numDims[i] * 8);
      }
      TF_SetAttrShapeList(handle, new BytePointer(name), shapesPointers, new IntPointer(numDims), numDims.length);
    }
  }

  private static void setAttrStringList(TF_OperationDescription handle, String name, byte[][] value) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      PointerPointer<BytePointer> valuePointers = new PointerPointer<BytePointer>(value.length);
      SizeTPointer lengths = new SizeTPointer(value.length);

      for (int i = 0; i < value.length; ++i) {
        valuePointers.put(i, new BytePointer(value[i]));
        lengths.put(i, value[i].length);
      }
      TF_SetAttrStringList(handle, new BytePointer(name), valuePointers, lengths, value.length);
    }
  }
}
