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
package org.tensorflow.op;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.tensorflow.GraphOperation;
import org.tensorflow.proto.framework.AttrValue;

/** A base class for operation input accessors. */
public abstract class RawOpInputs<T extends RawOp> {

  /** The outputs of this operation. */
  public T getOutputs() {
    return outputs;
  }

  /** Get the names of this op's attributes */
  public Set<String> attributeNames() {
    return attributeNames;
  }

  /**
   * Get the value of an attribute as an {@link AttrValue} proto. The type-safe accessors should be
   * prefered when possible.
   *
   * @param name the name of the attribute
   * @return the value of the attribute, as an {@link AttrValue} proto
   */
  public AttrValue attributeValue(String name) {
    return op.getAttrValueProto(name);
  }

  /** Get all attribute value protos */
  public Map<String, AttrValue> attributeValues() {
    Map<String, AttrValue> values = new LinkedHashMap<>(attributeNames.size());
    for (String name : attributeNames) {
      values.put(name, attributeValue(name));
    }
    return values;
  }

  /**
   * Get the metadata for an attribute
   *
   * @param name the name of the attribute
   * @return the attribute's metadata
   */
  public AttributeMetadata attributeMetadata(String name) {
    return op.getAttrMetadata(name);
  }

  @Override
  public final int hashCode() {
    return op.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    // Note: we consider that all objects wrapping the same operation are equal, no matter their
    // implementation
    if (!(obj instanceof RawOpInputs)) {
      return false;
    }
    return op.equals(((RawOpInputs) obj).op);
  }

  @Override
  public final String toString() {
    return String.format("Inputs of <%s '%s'>", op.type(), op.name());
  }

  protected RawOpInputs(T outputs, GraphOperation op, Collection<String> attributeNames) {
    this.outputs = outputs;
    this.op = op;
    this.attributeNames = Collections.unmodifiableSet(new LinkedHashSet<>(attributeNames));
  }

  // don't expose, this will be converted to a ForwardOperation w/ new gradient support
  private final GraphOperation op;
  private final T outputs;
  private final Set<String> attributeNames;
}
