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

import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.AttributeMetadata;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;

/** Helper type for attribute getters, so we don't clutter the operation classes too much. */
public interface OperationAttributeInspector {

  /**
   * Get the metadata of an attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the metadata of the attribute
   */
  AttributeMetadata getAttrMetadata(String name);

  /**
   * Get the value of an attribute of this operation as an {@link AttrValue} proto.
   *
   * @param name the name of the attribute
   * @return the value of the attribute as an {@link AttrValue} proto
   */
  AttrValue getAttrValueProto(String name);

  // TODO get attribute names.  Needs TF 2.7

  /**
   * Get the value of a string attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  String getAttrString(String name);

  /**
   * Get the value of a string list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  String[] getAttrStringList(String name);

  /**
   * Get the value of a int attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  long getAttrInt(String name);

  /**
   * Get the value of a int list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  long[] getAttrIntList(String name);

  /**
   * Get the value of a float attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  float getAttrFloat(String name);

  /**
   * Get the value of a float list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  float[] getAttrFloatList(String name);

  /**
   * Get the value of a boolean attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  boolean getAttrBool(String name);

  /**
   * Get the value of a boolean list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  boolean[] getAttrBoolList(String name);

  /**
   * Get the value of a data type attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  DataType getAttrType(String name);

  /**
   * Get the value of a data type list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  DataType[] getAttrTypeList(String name);

  /**
   * Get the value of a tensor attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  Tensor getAttrTensor(String name);

  /**
   * Get the value of a tensor list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  Tensor[] getAttrTensorList(String name);

  /**
   * Get the value of a shape attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  Shape getAttrShape(String name);

  /**
   * Get the value of a shape list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  Shape[] getAttrShapeList(String name);

  /**
   * Get the value of a function attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  ConcreteFunction getAttrFunction(String name);

  /**
   * Get the value of a function list attribute of this operation.
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  ConcreteFunction[] getAttrFunctionList(String name);
}
