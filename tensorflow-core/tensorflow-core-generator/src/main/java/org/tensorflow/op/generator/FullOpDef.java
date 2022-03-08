/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.op.generator;

import com.squareup.javapoet.TypeSpec;
import java.util.StringJoiner;
import org.tensorflow.proto.framework.ApiDef;
import org.tensorflow.proto.framework.ApiDef.Endpoint;
import org.tensorflow.proto.framework.OpDef;

final class FullOpDef {
  public final OpDef opDef;
  public final ApiDef apiDef;
  public final String basePackage;
  public final String packageName;
  public final String group;
  public final String className;
  public final Endpoint endpoint;

  public FullOpDef(
      OpDef opDef,
      ApiDef apiDef,
      String basePackage,
      String packageName,
      String group,
      String className,
      Endpoint endpoint) {
    this.group = group;
    this.endpoint = endpoint;
    if (opDef == null) {
      throw new IllegalArgumentException("Can't have a null OpDef");
    }
    if (apiDef == null) {
      throw new IllegalArgumentException("Can't have a null ApiDef");
    }
    this.opDef = opDef;
    this.apiDef = apiDef;
    this.basePackage = basePackage;
    this.packageName = packageName;
    this.className = className;
  }

  public boolean isStateful() {
    return opDef.getIsStateful();
  }

  public boolean isStateVariant(FullOpDef other) {
    if (this.equals(other)) return false;

    if (this.isStateful() == other.isStateful()) return false;

    OpDef copy =
        opDef.toBuilder().setName(other.opDef.getName()).setIsStateful(other.isStateful()).build();
    return copy.equals(other.opDef)
        && packageName.equals(other.packageName)
        && group.equals(other.group);
  }

  public TypeSpec buildOpClass() {
    return buildOpClass(className, null);
  }

  TypeSpec buildOpClass(String className, StatefulPair pair) {
    TypeSpec.Builder cls = TypeSpec.classBuilder(className);
    try {
      new ClassGenerator(
              cls, opDef, apiDef, basePackage, packageName, group, className, endpoint, pair)
          .buildClass();
    } catch (Exception e) {
      throw new IllegalStateException("Failed to generate class for op " + opDef.getName(), e);
    }
    return cls.build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FullOpDef fullOpDef = (FullOpDef) o;

    if (!opDef.equals(fullOpDef.opDef)) {
      return false;
    }
    if (!apiDef.equals(fullOpDef.apiDef)) {
      return false;
    }
    if (!basePackage.equals(fullOpDef.basePackage)) {
      return false;
    }
    if (!packageName.equals(fullOpDef.packageName)) {
      return false;
    }
    if (!group.equals(fullOpDef.group)) {
      return false;
    }
    if (!className.equals(fullOpDef.className)) {
      return false;
    }
    return endpoint.equals(fullOpDef.endpoint);
  }

  @Override
  public int hashCode() {
    int result = opDef.hashCode();
    result = 31 * result + apiDef.hashCode();
    result = 31 * result + basePackage.hashCode();
    result = 31 * result + packageName.hashCode();
    result = 31 * result + group.hashCode();
    result = 31 * result + className.hashCode();
    result = 31 * result + endpoint.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FullOpDef.class.getSimpleName() + "(", ")")
        .add("opDef=" + opDef)
        .add("apiDef=" + apiDef)
        .add("basePackage='" + basePackage + "'")
        .add("packageName='" + packageName + "'")
        .add("group='" + group + "'")
        .add("className='" + className + "'")
        .add("endpoint=" + endpoint)
        .toString();
  }
}
