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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphGetTensorNumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphGetTensorShape;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationAllInputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrBool;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrBoolList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrFloat;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrFloatList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrInt;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrIntList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrMetadata;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrShape;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrShapeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrString;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrStringList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrTensorList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrType;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrTypeList;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrValueProto;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetControlInputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetControlOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationInputListLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationNumControlInputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationNumControlOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationNumInputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationNumOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOpType;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputConsumers;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputListLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputNumConsumers;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputType;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.SizeTPointer;
import org.tensorflow.internal.c_api.TF_AttrMetadata;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Input;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;

/**
 * Implementation for an {@link Operation} added as a node to a {@link Graph}.
 *
 * <p>GraphOperation instances are valid only as long as the {@link Graph} they are a part of is
 * valid. Thus, if {@link Graph#close()} has been invoked, then methods on the GraphOperation
 * instance may fail with an {@code IllegalStateException}.
 *
 * <p>GraphOperation instances are immutable and thread-safe.
 */
public final class GraphOperation extends AbstractOperation {

  // Create an GraphOperation instance referring to an operation in g, with the given handle to the
  // C
  // TF_Operation object.  The handle is valid only as long as g has not been closed, hence it is
  // called unsafeHandle.  Graph.ref() is used to safely use the unsafeHandle.
  GraphOperation(Graph g, TF_Operation unsafeNativeHandle) {
    this.graph = g;
    this.unsafeNativeHandle = unsafeNativeHandle;
  }

  @Override
  public String name() {
    Graph.Reference r = graph.ref();
    try {
      return name(getUnsafeNativeHandle());
    } finally {
      r.close();
    }
  }

  @Override
  public String type() {
    Graph.Reference r = graph.ref();
    try {
      return type(getUnsafeNativeHandle());
    } finally {
      r.close();
    }
  }

  @Override
  public Graph env() {
    try (Graph.Reference r = graph.ref()) {
      return graph;
    }
  }

  @Override
  public int numOutputs() {
    Graph.Reference r = graph.ref();
    try {
      return numOutputs(getUnsafeNativeHandle());
    } finally {
      r.close();
    }
  }

  @Override
  public int outputListLength(final String name) {
    Graph.Reference r = graph.ref();
    try {
      return outputListLength(getUnsafeNativeHandle(), name);
    } finally {
      r.close();
    }
  }

  @Override
  public int hashCode() {
    return Long.valueOf(getUnsafeNativeHandle().address()).hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof GraphOperation)) {
      return false;
    }
    GraphOperation that = (GraphOperation) o;
    if (graph != that.graph) {
      return false;
    }

    // The graph object is known to be identical here, so this one
    // reference is sufficient to validate the use of native pointers
    // in both objects.
    Graph.Reference r = graph.ref();
    try {
      return getUnsafeNativeHandle().equals(that.getUnsafeNativeHandle());
    } finally {
      r.close();
    }
  }

  @Override
  public int inputListLength(final String name) {
    Graph.Reference r = graph.ref();
    try {
      return inputListLength(getUnsafeNativeHandle(), name);
    } finally {
      r.close();
    }
  }

  @Override
  TF_Operation getUnsafeNativeHandle(int outputIdx) {
    return getUnsafeNativeHandle();
  }

  @Override
  Shape shape(int outputIdx) {
    Graph.Reference r = graph.ref();
    try {
      long[] shape = shape(r.nativeHandle(), getUnsafeNativeHandle(), outputIdx);
      return shape == null ? Shape.unknown() : Shape.of(shape);
    } finally {
      r.close();
    }
  }

  @Override
  DataType dtype(int outputIdx) {
    Graph.Reference r = graph.ref();
    try {
      return DataType.forNumber(dtype(r.nativeHandle(), getUnsafeNativeHandle(), outputIdx));
    } finally {
      r.close();
    }
  }

  @Override
  Tensor tensor(int outputIdx) {
    throw new IllegalStateException("Graph tensors must be fetched by running a session");
  }

  /**
   * Get the number of inputs to the op, not including control inputs.
   */
  public int numInputs() {
    return TF_OperationNumInputs(getUnsafeNativeHandle());
  }

  /**
   * Get the op's inputs, not including control inputs.
   */
  public List<Operand<?>> inputs() {
    try (PointerScope scope = new PointerScope()) {
      int numInputs = numInputs();
      TF_Output handles = new TF_Output(numInputs);

      TF_OperationAllInputs(getUnsafeNativeHandle(), handles, numInputs);

      List<Operand<?>> operands = new ArrayList<>(numInputs);
      for (int i = 0; i < numInputs; i++) {
        TF_Output atPos = handles.position(i);
        TF_Operation op = atPos.oper();
        int index = atPos.index();
        String opName = TF_OperationName(op).getString();
        operands.add(graph.operation(opName).output(index));
      }
      return operands;
    }
  }

  /**
   * Get the number of ops that use this op's designated output as an input, not including control
   * dependencies.
   *
   * @param index the output to look for usages of
   */
  public int numConsumers(int index) {
    try (PointerScope scope = new PointerScope()) {
      TF_Output output = new TF_Output().oper(getUnsafeNativeHandle()).index(index);
      return TF_OperationOutputNumConsumers(output);
    }
  }

  /**
   * Get the ops that use this op's designated output as an input, not including control
   * dependencies.
   *
   * @param index the output to look for usages of
   */
  public Set<GraphOperation> consumers(int index) {
    try (PointerScope scope = new PointerScope()) {
      TF_Output output = new TF_Output().oper(getUnsafeNativeHandle()).index(index);
      int numConsumers = numConsumers(index);
      TF_Input handles = new TF_Input(numConsumers);

      TF_OperationOutputConsumers(output, handles, numConsumers);

      Set<GraphOperation> operands = new LinkedHashSet<>(numConsumers);
      for (int i = 0; i < numConsumers; i++) {
        TF_Input atPos = handles.position(i);
        TF_Operation op = atPos.oper();
        String opName = TF_OperationName(op).getString();
        operands.add(graph.operation(opName));
      }
      return operands;
    }
  }

  /**
   * Get the number of ops that use any of this op's outputs as an input, not including control
   * dependencies.
   */
  public int numConsumers() {
    int all = 0;
    for (int i = 0; i < numOutputs(); i++) {
      all += numConsumers(i);
    }
    return all;
  }


  /**
   * Get the ops that use any of this op's outputs as an input, not including control dependencies.
   */
  public Set<GraphOperation> consumers() {
    Set<GraphOperation> all = new LinkedHashSet<>();
    for (int i = 0; i < numOutputs(); i++) {
      all.addAll(consumers(i));
    }
    return all;
  }

  /**
   * Get the number of control inputs for this op.
   */
  public int numControlInputs() {
    try (PointerScope scope = new PointerScope()) {
      return TF_OperationNumControlInputs(getUnsafeNativeHandle());
    }
  }

  /**
   * Get the control inputs of this op.
   */
  public Set<GraphOperation> controlInputs() {
    try (PointerScope scope = new PointerScope()) {
      int numInputs = numControlInputs();
      PointerPointer<TF_Operation> handles = new PointerPointer<>(numInputs);

      TF_OperationGetControlInputs(getUnsafeNativeHandle(), handles, numInputs);

      Set<GraphOperation> operands = new LinkedHashSet<>(numInputs);
      for (int i = 0; i < numInputs; i++) {
        TF_Operation op = handles.get(TF_Operation.class, i);
        String opName = TF_OperationName(op).getString();
        operands.add(graph.operation(opName));
      }
      return operands;
    }
  }

  /**
   * Get the number of ops with this op as a control dependency.
   */
  public int numControlConsumers() {
    try (PointerScope scope = new PointerScope()) {
      return TF_OperationNumControlOutputs(getUnsafeNativeHandle());
    }
  }

  /**
   * Get the ops with this op as a control dependency.
   */
  public Set<GraphOperation> controlConsumers() {
    try (PointerScope scope = new PointerScope()) {
      int numConsumers = numControlConsumers();
      PointerPointer<TF_Operation> handles = new PointerPointer<>(numConsumers);

      TF_OperationGetControlOutputs(getUnsafeNativeHandle(), handles, numConsumers);

      Set<GraphOperation> operands = new LinkedHashSet<>(numConsumers);
      for (int i = 0; i < numConsumers; i++) {
        TF_Operation op = handles.get(TF_Operation.class, i);
        String opName = TF_OperationName(op).getString();
        operands.add(graph.operation(opName));
      }
      return operands;
    }
  }

  /**
   * Get the value of a string attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public String getAttrString(String name) {
    return getAttrString(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a string list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public String[] getAttrStringList(String name) {
    return getAttrStringList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a int attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public long getAttrInt(String name) {
    return getAttrInt(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a int list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public long[] getAttrIntList(String name) {
    return getAttrIntList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a float attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public float getAttrFloat(String name) {
    return getAttrFloat(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a float list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public float[] getAttrFloatList(String name) {
    return getAttrFloatList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a boolean attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public boolean getAttrBool(String name) {
    return getAttrBool(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a boolean list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public boolean[] getAttrBoolList(String name) {
    return getAttrBoolList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a data type attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public DataType getAttrType(String name) {
    return getAttrType(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a data type list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public DataType[] getAttrTypeList(String name) {
    return getAttrTypeList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a tensor attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public Tensor getAttrTensor(String name) {
    return getAttrTensor(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a tensor list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public Tensor[] getAttrTensorList(String name) {
    return getAttrTensorList(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a shape attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public Shape getAttrShape(String name) {
    return getAttrShape(unsafeNativeHandle, name);
  }

  /**
   * Get the value of a shape list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public Shape[] getAttrShapeList(String name) {
    return getAttrShapeList(unsafeNativeHandle, name);
  }


  /**
   * Get the value of an attribute of this operation as an {@link AttrValue} proto.
   *
   * @param name the name of the attribute
   * @return the value of the attribute as an {@link AttrValue} proto
   */
  public AttrValue getAttrValueProto(String name) {
    return getAttrValueProto(unsafeNativeHandle, name);
  }

  TF_Operation getUnsafeNativeHandle() {
    return unsafeNativeHandle;
  }

  private final Graph graph;

  private final TF_Operation unsafeNativeHandle;

  private static void requireHandle(Pointer handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException(
          "close() has been called on the Graph this Operation was a part of");
    }
  }

  private static String name(TF_Operation handle) {
    requireHandle(handle);
    return TF_OperationName(handle).getString();
  }

  private static String type(TF_Operation handle) {
    requireHandle(handle);
    return TF_OperationOpType(handle).getString();
  }

  private static int numOutputs(TF_Operation handle) {
    requireHandle(handle);
    return TF_OperationNumOutputs(handle);
  }

  private static int outputListLength(TF_Operation handle, String name) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      int result = TF_OperationOutputListLength(handle, name, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  private static int inputListLength(TF_Operation handle, String name) {
    requireHandle(handle);

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      int result = TF_OperationInputListLength(handle, name, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  private static long[] shape(TF_Graph graphHandle, TF_Operation opHandle, int outputIndex) {
    requireHandle(graphHandle);
    requireHandle(opHandle);

    int numOutputs = TF_OperationNumOutputs(opHandle);
    if (outputIndex < 0 || outputIndex >= numOutputs) {
      throw new IndexOutOfBoundsException("invalid output index (" + outputIndex
          + ") for an operation that has " + numOutputs + " outputs");
    }

    try (PointerScope scope = new PointerScope()) {
      TF_Output output = new TF_Output().oper(opHandle).index(outputIndex);
      TF_Status status = TF_Status.newStatus();
      int numDims = TF_GraphGetTensorNumDims(graphHandle, output, status);
      status.throwExceptionIfNotOK();
      if (numDims < 0) {
        return null;
      }
      long[] dims = new long[numDims];
      TF_GraphGetTensorShape(graphHandle, output, dims, numDims, status);
      status.throwExceptionIfNotOK();
      return dims;
    }
  }

  private static int dtype(TF_Graph graphHandle, TF_Operation opHandle, int outputIndex) {
    requireHandle(graphHandle);
    requireHandle(opHandle);

    int numOutputs = TF_OperationNumOutputs(opHandle);
    if (outputIndex < 0 || outputIndex >= numOutputs) {
      throw new IndexOutOfBoundsException("invalid output index (" + outputIndex
          + ") for an operation that has " + numOutputs + " outputs");
    }

    try (PointerScope scope = new PointerScope()) {
      return TF_OperationOutputType(new TF_Output().oper(opHandle).index(outputIndex));
    }
  }

  private static TF_AttrMetadata getAttrMetadata(TF_Operation handle, String name) {
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_AttrMetadata r = TF_OperationGetAttrMetadata(handle, name, status);
      status.throwExceptionIfNotOK();
      return r;
    }
  }

  private static String getAttrString(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).total_size();
      BytePointer result = new BytePointer(size);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrString(handle, name, result, size, status);
      status.throwExceptionIfNotOK();
      return result.getString();
    }
  }

  private static String[] getAttrStringList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_AttrMetadata metadata = getAttrMetadata(handle, name);
      int listSize = (int) metadata.list_size();
      int totalSize = (int) metadata.total_size();

      PointerPointer<BytePointer> values = new PointerPointer<>(listSize);
      SizeTPointer lengths = new SizeTPointer(listSize);
      BytePointer storage = new BytePointer(totalSize);

      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrStringList(handle, name, values, lengths, listSize, storage, totalSize,
          status);
      status.throwExceptionIfNotOK();

      String[] results = new String[listSize];

      for (int i = 0; i < results.length; i++) {
        int length = (int) lengths.get(i);

        if (length == 0) {
          results[i] = "";
          continue;
        }

        results[i] = values.getString(i);
      }

      return results;
    }
  }

  private static long getAttrInt(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long[] result = new long[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrInt(handle, name, result, status);
      status.throwExceptionIfNotOK();
      return result[0];
    }
  }

  private static long[] getAttrIntList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).list_size();
      long[] result = new long[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrIntList(handle, name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  private static float getAttrFloat(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      float[] result = new float[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrFloat(handle, name, result, status);
      status.throwExceptionIfNotOK();
      return result[0];
    }
  }

  private static float[] getAttrFloatList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).list_size();
      float[] result = new float[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrFloatList(handle, name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  private static boolean getAttrBool(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      byte[] result = new byte[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrBool(handle, name, result, status);
      status.throwExceptionIfNotOK();
      return result[0] == 1;
    }
  }

  private static boolean[] getAttrBoolList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).list_size();
      byte[] byteResults = new byte[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrBoolList(handle, name, byteResults, byteResults.length, status);
      status.throwExceptionIfNotOK();

      boolean[] results = new boolean[byteResults.length];

      for (int i = 0; i < results.length; i++) {
        results[i] = byteResults[i] == 1;
      }

      return results;
    }
  }

  private static DataType getAttrType(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      int[] result = new int[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrType(handle, name, result, status);
      status.throwExceptionIfNotOK();
      return DataType.forNumber(result[0]);
    }
  }

  private static DataType[] getAttrTypeList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).list_size();
      int[] typeInts = new int[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTypeList(handle, name, typeInts, typeInts.length, status);
      status.throwExceptionIfNotOK();

      DataType[] results = new DataType[typeInts.length];

      for (int i = 0; i < results.length; i++) {
        results[i] = DataType.forNumber(typeInts[i]);
      }

      return results;
    }
  }

  private static Tensor getAttrTensor(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      PointerPointer<TF_Tensor> result = new PointerPointer<>(1);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTensor(handle, new BytePointer(name), result, status);
      status.throwExceptionIfNotOK();
      return RawTensor.fromHandle(result.get(TF_Tensor.class, 0).withDeallocator());
    }
  }

  private static Tensor[] getAttrTensorList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).list_size();
      PointerPointer<TF_Tensor> pointers = new PointerPointer<>(size);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTensorList(handle, new BytePointer(name), pointers, (int) size, status);
      status.throwExceptionIfNotOK();

      Tensor[] results = new Tensor[(int) size];
      for (int i = 0; i < results.length; i++) {
        results[i] = RawTensor.fromHandle(pointers.get(TF_Tensor.class, i).withDeallocator());
      }

      return results;
    }
  }

  private static Shape getAttrShape(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(handle, name).total_size();

      if (size == -1) {
        return Shape.unknown();
      }

      long[] result = new long[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrShape(handle, name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return Shape.of(result);
    }
  }

  //TODO test
  private static Shape[] getAttrShapeList(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_AttrMetadata metadata = getAttrMetadata(handle, name);
      int listSize = (int) metadata.list_size();
      int totalSize = (int) metadata.total_size();

      long[] dimPointers = new long[listSize];
      int[] numDims = new int[listSize];
      long[] storage = new long[totalSize];

      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrShapeList(handle, name, dimPointers, numDims, listSize, storage, totalSize,
          status);
      status.throwExceptionIfNotOK();

      long minDimOffset = Long.MAX_VALUE;
      for (int i = 0; i < listSize; i++) {
        if (dimPointers[i] < minDimOffset) {
          minDimOffset = dimPointers[i];
        }
      }

      int[] dims = new int[listSize];
      for (int i = 0; i < listSize; i++) {
        dims[i] = (int) ((dimPointers[i] - minDimOffset) / 8);
      }

      Shape[] results = new Shape[listSize];

      for (int i = 0; i < results.length; i++) {
        int length = numDims[i];

        if (length == -1) {
          results[i] = Shape.unknown();
          continue;
        }

        int start = dims[i];
        long[] shape = Arrays.copyOfRange(storage, start, start + length);
        results[i] = Shape.of(shape);
      }

      return results;
    }
  }

  private static AttrValue getAttrValueProto(TF_Operation handle, String name) {
    requireHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Buffer buffer = TF_Buffer.newBuffer();
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrValueProto(handle, name, buffer, status);
      status.throwExceptionIfNotOK();
      return AttrValue.parseFrom(buffer.dataAsByteBuffer());
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalStateException("Invalud protobuf for attribute " + name, e);
    }
  }
}
