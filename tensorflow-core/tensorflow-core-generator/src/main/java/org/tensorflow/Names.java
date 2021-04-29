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
import java.util.Arrays;

public class Names {

  public static final String TensorflowPackage = "org.tensorflow";
  public static final String OpPackage = TensorflowPackage + ".op";
  public static final String TypesPackage = TensorflowPackage + ".types";

  public static final ClassName Operator = ClassName.get(OpPackage + ".annotation", "Operator");
  public static final ClassName Endpoint = ClassName.get(OpPackage + ".annotation", "Endpoint");

  public static final ClassName TType = ClassName.get(TypesPackage + ".family", "TType");
  public static final ClassName TString = ClassName.get(TypesPackage, "TString");
  public static final ClassName TBool = ClassName.get(TypesPackage, "TBool");

  public static final ClassName TNumber = ClassName.get(TypesPackage + ".family", "TNumber");

  public static final ClassName TFloating = ClassName.get(TypesPackage + ".family", "TFloating");
  public static final ClassName TBfloat16 = ClassName.get(TypesPackage, "TBfloat16");
  public static final ClassName TFloat16 = ClassName.get(TypesPackage, "TFloat16");
  public static final ClassName TFloat32 = ClassName.get(TypesPackage, "TFloat32");
  public static final ClassName TFloat64 = ClassName.get(TypesPackage, "TFloat64");

  public static final ClassName TIntegral = ClassName.get(TypesPackage + ".family", "TIntegral");
  public static final ClassName TUint8 = ClassName.get(TypesPackage, "TUint8");
  public static final ClassName TInt32 = ClassName.get(TypesPackage, "TInt32");
  public static final ClassName TInt64 = ClassName.get(TypesPackage, "TInt64");

  public static final TypeName Op = ClassName.get(OpPackage, "Op");
  public static final ClassName RawOp = ClassName.get(OpPackage, "RawOp");
  public static final ClassName RawOpInputs = ClassName.get(OpPackage, "RawOpInputs");
  public static final ClassName Operation = ClassName.get(TensorflowPackage, "Operation");
  public static final ClassName GraphOperation = ClassName.get(TensorflowPackage, "GraphOperation");
  public static final ClassName Operands = ClassName.get(OpPackage, "Operands");
  public static final ClassName OperationBuilder = ClassName.get(TensorflowPackage, "OperationBuilder");
  public static final TypeName IterableOp = ParameterizedTypeName.get(ClassName.get(Iterable.class), Op);

  public static final ClassName Operand = ClassName.get(TensorflowPackage, "Operand");
  public static final ClassName Output = ClassName.get(TensorflowPackage, "Output");

  public static final ClassName Shape = ClassName.get(TensorflowPackage + ".ndarray", "Shape");
  public static final ClassName Tensor = ClassName.get(TensorflowPackage, "Tensor");
  public static final ClassName ConcreteFunction = ClassName.get(TensorflowPackage, "ConcreteFunction");

  public static final ClassName Scope = ClassName.get(OpPackage, "Scope");
  public static final TypeName DeviceSpec = ClassName.get(TensorflowPackage, "DeviceSpec");
  public static final ClassName Ops = ClassName.get(OpPackage, "Ops");

  public static final TypeName ExecutionEnvironment = ClassName.get(TensorflowPackage, "ExecutionEnvironment");
  public static final TypeName EagerSession = ClassName.get(TensorflowPackage, "EagerSession");

  public static final TypeName String = ClassName.get(String.class);
  public static final TypeName Arrays = ClassName.get(Arrays.class);

}
