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

import org.tensorflow.internal.c_api.TF_AttrMetadata;

/**
 * Metadata of an op's attribute.
 *
 * @see org.tensorflow.internal.c_api.TF_AttrMetadata
 */
public class AttributeMetadata {

  /**
   * Whether this attribute is a list.
   */
  public final boolean isList;

  /**
   * The size of the list if this attribute is a list, undefined otherwise.
   */
  public final long listSize;
  /**
   * The type of this attribute, or the type of the list values if it is a list.
   *
   * <p>See {@code tensorflow/c/tf_attrtype.h}.
   */
  public final int type;

  /**
   * The total size of this attribute. Exact meaning depends on the type.
   */
  public final long totalSize;

  public AttributeMetadata(boolean isList, long listSize, int type, long totalSize) {
    this.isList = isList;
    this.listSize = listSize;
    this.type = type;
    this.totalSize = totalSize;
  }

  public AttributeMetadata(TF_AttrMetadata nativeMetadata) {
    this(
        nativeMetadata.is_list() == 1,
        nativeMetadata.list_size(),
        nativeMetadata.type(),
        nativeMetadata.total_size());
  }
}
