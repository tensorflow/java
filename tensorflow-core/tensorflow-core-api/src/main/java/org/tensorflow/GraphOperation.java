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
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationInputListLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationNumOutputs;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOpType;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputListLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationOutputType;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.ndarray.Shape;
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

  TF_Operation getUnsafeNativeHandle() {
    return unsafeNativeHandle;
  }

  private final Graph graph;

  private final TF_Operation unsafeNativeHandle;

  private static void requireHandle(Pointer handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Graph this Operation was a part of");
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
      if (numDims < 0) return null;
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
}
