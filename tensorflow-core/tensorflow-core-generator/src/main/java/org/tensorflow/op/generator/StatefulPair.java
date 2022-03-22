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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

final class StatefulPair {
  public final FullOpDef statefulOp;
  public final FullOpDef statelessOp;
  public final String selectorClassName;
  public final String statefulClassName;
  public final String statelessClassName;

  public StatefulPair(FullOpDef statefulOp, FullOpDef statelessOp) {
    this.statefulOp = statefulOp;
    this.statelessOp = statelessOp;

    this.selectorClassName = statefulOp.className.replace("stateful", "").replace("Stateful", "");

    if (statefulOp.className.toLowerCase().contains("stateful")) {
      statefulClassName = statefulOp.className;
    } else {
      statefulClassName = "Stateful" + statefulOp.className;
    }

    if (statelessOp.className.toLowerCase().contains("stateless")) {
      statelessClassName = statelessOp.className;
    } else {
      statelessClassName = "Stateless" + statelessOp.className;
    }
  }

  public static List<StatefulPair> extractStatefulPairs(List<FullOpDef> ops) {
    List<StatefulPair> pairs = new ArrayList<>(10);
    for (FullOpDef stateful : ops) {
      if (!stateful.isStateful()) {
        continue;
      }

      for (FullOpDef stateless : ops) {
        if (stateful.isStateVariant(stateless) && !stateful.equals(stateless)) {
          if (stateful.opDef.getName().toLowerCase().contains("stateful")
              || stateless.opDef.getName().toLowerCase().contains("stateless")) {
            pairs.add(new StatefulPair(stateful, stateless));
          }
        }
      }
    }
    for (StatefulPair pair : pairs) {
      ops.remove(pair.statefulOp);
      ops.remove(pair.statelessOp);
    }
    return pairs;
  }

  public String getPackageName() {
    // guaranteed to be the same in FullOpDef.isStateVariant
    return statefulOp.packageName;
  }

  public String getGroup() {
    // guaranteed to be the same in FullOpDef.isStateVariant
    return statefulOp.group;
  }

  public boolean hasOptionalAttrs() {
    return statefulOp.opDef.getAttrList().stream()
        .anyMatch(attr -> attr.hasDefaultValue() && !attr.getType().contains("type"));
  }

  public List<TypeSpec> buildOpClasses() {

    TypeSpec.Builder selector = TypeSpec.interfaceBuilder(selectorClassName);
    try {
      new ClassGenerator(
              selector,
              statefulOp.opDef,
              statefulOp.apiDef,
              statefulOp.basePackage,
              getPackageName(),
              getGroup(),
              selectorClassName,
              statefulOp.endpoint,
              this)
          .buildClass();
    } catch (Exception e) {
      throw new IllegalStateException(
          "Failed to generate statefulness selector class for ops "
              + statefulOp.opDef.getName()
              + " and "
              + statelessOp.opDef.getName(),
          e);
    }

    TypeSpec stateful = statefulOp.buildOpClass(statefulClassName, this);
    TypeSpec stateless = statelessOp.buildOpClass(statelessClassName, this);

    return Arrays.asList(stateful, stateless, selector.build());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StatefulPair that = (StatefulPair) o;

    if (!statefulOp.equals(that.statefulOp)) {
      return false;
    }
    if (!statelessOp.equals(that.statelessOp)) {
      return false;
    }
    if (!selectorClassName.equals(that.selectorClassName)) {
      return false;
    }
    if (!statefulClassName.equals(that.statefulClassName)) {
      return false;
    }
    return statelessClassName.equals(that.statelessClassName);
  }

  @Override
  public int hashCode() {
    int result = statefulOp.hashCode();
    result = 31 * result + statelessOp.hashCode();
    result = 31 * result + selectorClassName.hashCode();
    result = 31 * result + statefulClassName.hashCode();
    result = 31 * result + statelessClassName.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", StatefulPair.class.getSimpleName() + "(", ")")
        .add("statefulOp=" + statefulOp)
        .add("statelessOp=" + statelessOp)
        .add("selectorClassName='" + selectorClassName + "'")
        .add("statefulClassName='" + statefulClassName + "'")
        .add("statelessClassName='" + statelessClassName + "'")
        .toString();
  }
}
