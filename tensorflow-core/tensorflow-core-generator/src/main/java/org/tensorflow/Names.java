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
package org.tensorflow;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class Names {

  public static final ClassName Operator = ClassName.get("org.tensorflow.op.annotation", "Operator");
  public static final ClassName Endpoint = ClassName.get("org.tensorflow.op.annotation", "Endpoint");

  public static final ClassName TType = ClassName.get("org.tensorflow.types.family", "TType");
  public static final ClassName TString = ClassName.get("org.tensorflow.types", "TString");
  public static final ClassName TBool = ClassName.get("org.tensorflow.types", "TBool");

  public static final ClassName TNumber = ClassName.get("org.tensorflow.types.family", "TNumber");

  public static final ClassName TFloating = ClassName.get("org.tensorflow.types.family", "TFloating");
  public static final ClassName TBfloat16 = ClassName.get("org.tensorflow.types", "TBfloat16");
  public static final ClassName TFloat16 = ClassName.get("org.tensorflow.types", "TFloat16");
  public static final ClassName TFloat32 = ClassName.get("org.tensorflow.types", "TFloat32");
  public static final ClassName TFloat64 = ClassName.get("org.tensorflow.types", "TFloat64");

  public static final ClassName TIntegral = ClassName.get("org.tensorflow.types.family", "TIntegral");
  public static final ClassName TUint8 = ClassName.get("org.tensorflow.types", "TUint8");
  public static final ClassName TInt32 = ClassName.get("org.tensorflow.types", "TInt32");
  public static final ClassName TInt64 = ClassName.get("org.tensorflow.types", "TInt64");

  public static final TypeName Op = ClassName.get("org.tensorflow.op", "Op");
  public static final ClassName RawOp = ClassName.get("org.tensorflow.op", "RawOp");
  public static final ClassName Operation = ClassName.get("org.tensorflow", "Operation");
  public static final ClassName Operands = ClassName.get("org.tensorflow.op", "Operands");
  public static final ClassName OperationBuilder = ClassName.get("org.tensorflow", "OperationBuilder");
  public static final TypeName IterableOp = ParameterizedTypeName.get(ClassName.get(Iterable.class), Op);

  public static final ClassName Operand = ClassName.get("org.tensorflow", "Operand");
  public static final ClassName Output = ClassName.get("org.tensorflow", "Output");

  public static final ClassName Shape = ClassName.get("org.tensorflow.ndarray", "Shape");
  public static final ClassName Tensor = ClassName.get("org.tensorflow", "Tensor");
  public static final ClassName ConcreteFunction = ClassName.get("org.tensorflow", "ConcreteFunction");

  public static final ClassName Scope = ClassName.get("org.tensorflow.op", "Scope");
  public static final TypeName DeviceSpec = ClassName.get("org.tensorflow", "DeviceSpec");
  public static final ClassName Ops = ClassName.get("org.tensorflow.op", "Ops");

  public static final TypeName ExecutionEnvironment =
      ClassName.get("org.tensorflow", "ExecutionEnvironment");
  public static final TypeName EagerSession = ClassName.get("org.tensorflow", "EagerSession");

  public static final TypeName String = ClassName.get(String.class);

}
