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
==============================================================================
*/
package org.tensorflow.op.generator;

public enum AttributeType {
  STRING("String"),
  INT("Int"),
  FLOAT("Float"),
  BOOL("Bool"),
  SHAPE("Shape"),
  TENSOR("Tensor"),
  TYPE("Type");
  // TODO add Func once supported

  private final String methodName;

  private AttributeType(String methodName) {
    this.methodName = methodName;
  }

  public String getterName(boolean isList) {
    if (isList) {
      return methodName + "List";
    } else {
      return methodName;
    }
  }
}
