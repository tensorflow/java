/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================

*/
package org.tensorflow;

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

import com.google.protobuf.InvalidProtocolBufferException;
import java.nio.charset.StandardCharsets;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.SizeTPointer;
import org.tensorflow.internal.c_api.TF_AttrMetadata;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.AttributeMetadata;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;

class GraphOperationAttributeInspector implements OperationAttributeInspector {
  private final GraphOperation op;

  GraphOperationAttributeInspector(GraphOperation op) {
    this.op = op;
  }

  @Override
  public AttributeMetadata getAttrMetadata(String name) {
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_AttrMetadata r = TF_OperationGetAttrMetadata(op.getCheckedNativeHandle(), name, status);
      status.throwExceptionIfNotOK();
      return new AttributeMetadata(r);
    }
  }

  @Override
  public AttrValue getAttrValueProto(String name) {
    try (PointerScope scope = new PointerScope()) {
      TF_Buffer buffer = TF_Buffer.newBuffer();
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrValueProto(op.getCheckedNativeHandle(), name, buffer, status);
      status.throwExceptionIfNotOK();
      return AttrValue.parseFrom(buffer.dataAsByteBuffer());
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalStateException("Invalid protobuf for attribute " + name, e);
    }
  }

  @Override
  public String getAttrString(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).totalSize;
      BytePointer result = new BytePointer(size);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrString(op.getCheckedNativeHandle(), name, result, size, status);
      status.throwExceptionIfNotOK();
      return result.getString();
    }
  }

  @Override
  public String[] getAttrStringList(String name) {
    try (PointerScope scope = new PointerScope()) {
      AttributeMetadata metadata = getAttrMetadata(name);
      int listSize = (int) metadata.listSize;
      long totalSize = metadata.totalSize;

      PointerPointer<BytePointer> values = new PointerPointer<>(listSize);
      SizeTPointer lengths = new SizeTPointer(listSize);
      BytePointer storage = new BytePointer(totalSize);

      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrStringList(
          op.getCheckedNativeHandle(),
          new BytePointer(name),
          values,
          lengths,
          listSize,
          storage,
          totalSize,
          status);
      status.throwExceptionIfNotOK();

      String[] results = new String[listSize];

      for (int i = 0; i < results.length; i++) {
        int length = (int) lengths.get(i);

        if (length == 0) {
          results[i] = "";
          continue;
        }

        byte[] bytes = new byte[length];
        BytePointer p = values.get(BytePointer.class, i);
        p.get(bytes);

        results[i] = new String(bytes, StandardCharsets.UTF_8);
      }

      return results;
    }
  }

  @Override
  public long getAttrInt(String name) {
    try (PointerScope scope = new PointerScope()) {
      long[] result = new long[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrInt(op.getCheckedNativeHandle(), name, result, status);
      status.throwExceptionIfNotOK();
      return result[0];
    }
  }

  @Override
  public long[] getAttrIntList(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).listSize;
      long[] result = new long[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrIntList(op.getCheckedNativeHandle(), name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  @Override
  public float getAttrFloat(String name) {
    try (PointerScope scope = new PointerScope()) {
      float[] result = new float[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrFloat(op.getCheckedNativeHandle(), name, result, status);
      status.throwExceptionIfNotOK();
      return result[0];
    }
  }

  @Override
  public float[] getAttrFloatList(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).listSize;
      float[] result = new float[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrFloatList(
          op.getCheckedNativeHandle(), name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return result;
    }
  }

  @Override
  public boolean getAttrBool(String name) {
    try (PointerScope scope = new PointerScope()) {
      byte[] result = new byte[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrBool(op.getCheckedNativeHandle(), name, result, status);
      status.throwExceptionIfNotOK();
      return result[0] == 1;
    }
  }

  @Override
  public boolean[] getAttrBoolList(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).listSize;
      byte[] byteResults = new byte[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrBoolList(
          op.getCheckedNativeHandle(), name, byteResults, byteResults.length, status);
      status.throwExceptionIfNotOK();

      boolean[] results = new boolean[byteResults.length];

      for (int i = 0; i < results.length; i++) {
        results[i] = byteResults[i] == 1;
      }

      return results;
    }
  }

  @Override
  public DataType getAttrType(String name) {
    try (PointerScope scope = new PointerScope()) {
      int[] result = new int[1];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrType(op.getCheckedNativeHandle(), name, result, status);
      status.throwExceptionIfNotOK();
      return DataType.forNumber(result[0]);
    }
  }

  @Override
  public DataType[] getAttrTypeList(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).listSize;
      int[] typeInts = new int[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTypeList(
          op.getCheckedNativeHandle(), name, typeInts, typeInts.length, status);
      status.throwExceptionIfNotOK();

      DataType[] results = new DataType[typeInts.length];

      for (int i = 0; i < results.length; i++) {
        results[i] = DataType.forNumber(typeInts[i]);
      }

      return results;
    }
  }

  @Override
  public Tensor getAttrTensor(String name) {
    try (PointerScope scope = new PointerScope()) {
      PointerPointer<TF_Tensor> result = new PointerPointer<>(1);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTensor(op.getCheckedNativeHandle(), new BytePointer(name), result, status);
      status.throwExceptionIfNotOK();
      return RawTensor.fromHandle(result.get(TF_Tensor.class, 0).withDeallocator());
    }
  }

  @Override
  public Tensor[] getAttrTensorList(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).listSize;
      PointerPointer<TF_Tensor> pointers = new PointerPointer<>(size);
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrTensorList(
          op.getCheckedNativeHandle(), new BytePointer(name), pointers, (int) size, status);
      status.throwExceptionIfNotOK();

      Tensor[] results = new Tensor[(int) size];
      for (int i = 0; i < results.length; i++) {
        results[i] = RawTensor.fromHandle(pointers.get(TF_Tensor.class, i).withDeallocator());
      }

      return results;
    }
  }

  @Override
  public Shape getAttrShape(String name) {
    try (PointerScope scope = new PointerScope()) {
      long size = getAttrMetadata(name).totalSize;

      if (size == -1) {
        return Shape.unknown();
      }

      long[] result = new long[(int) size];
      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrShape(op.getCheckedNativeHandle(), name, result, result.length, status);
      status.throwExceptionIfNotOK();
      return Shape.of(result);
    }
  }

  @Override
  public Shape[] getAttrShapeList(String name) {
    try (PointerScope scope = new PointerScope()) {
      AttributeMetadata metadata = getAttrMetadata(name);
      int listSize = (int) metadata.listSize;
      int totalSize = (int) metadata.totalSize;

      PointerPointer<LongPointer> dimPointers = new PointerPointer<>(listSize);
      IntPointer numDims = new IntPointer(listSize);
      LongPointer storage = new LongPointer(totalSize);

      TF_Status status = TF_Status.newStatus();
      TF_OperationGetAttrShapeList(
          op.getCheckedNativeHandle(),
          new BytePointer(name),
          dimPointers,
          numDims,
          listSize,
          storage,
          totalSize,
          status);
      status.throwExceptionIfNotOK();

      Shape[] results = new Shape[listSize];

      for (int i = 0; i < results.length; i++) {
        int length = numDims.get(i);

        if (length == -1) {
          results[i] = Shape.unknown();
          continue;
        }

        long[] shape = new long[length];
        dimPointers.get(LongPointer.class, i).get(shape);
        results[i] = Shape.of(shape);
      }

      return results;
    }
  }

  @Override
  public ConcreteFunction getAttrFunction(String name) {
    AttrValue proto = getAttrValueProto(name);
    if (!proto.hasFunc())
      throw new IllegalArgumentException("Attribute \"" + name + "\" is not a function.");
    return op.graph().getFunction(proto.getFunc().getName());
  }

  @Override
  public ConcreteFunction[] getAttrFunctionList(String name) {
    AttrValue proto = getAttrValueProto(name);
    if (!proto.hasList())
      throw new IllegalArgumentException("Attribute \"" + name + "\" is not a list.");

    AttrValue.ListValue list = proto.getList();

    int size = list.getFuncCount();
    if (size < 0) {
      throw new IllegalArgumentException("Attribute \"" + name + "\" is not a list of functions.");
    } else if (size == 0) {
      return new ConcreteFunction[0];
    } else {
      ConcreteFunction[] functions = new ConcreteFunction[size];
      for (int i = 0; i < size; i++) {
        functions[i] = op.graph().getFunction(list.getFunc(i).getName());
      }
      return functions;
    }
  }
}
