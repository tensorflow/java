/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

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

/** Represents a type of elements in a {@link Tensor} */
public enum DataType {
  FLOAT(1, 4),
  DOUBLE(2, 8),
  INT32(3, 4),
  UINT8(4, 1),
  STRING(7, -1),
  INT64(9, 8),
  BOOL(10, 1),
  BFLOAT16(14, 2),
  HALF(19, 2);

  final int number;
  final int byteSize;

  private DataType(int number, int byteSize) {
    this.number = number;
    this.byteSize = byteSize;
  }
}
