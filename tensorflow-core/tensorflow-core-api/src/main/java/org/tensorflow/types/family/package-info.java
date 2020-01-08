/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

/**
 * Interfaces used to group tensor types in families that define a particular domain of data.
 *
 * <p>Some operations enforces that only operands of a type from a given family can be passed
 * in argument. For example, if an operation only allows numeric operands, such operands must be
 * bound to the {@link org.tensorflow.types.family.TNumber TNumber} interface.
 *
 * <p>All tensor types is bound to {@link org.tensorflow.types.family.TType TType}, which lays at
 * the root of the family hierarchy.
 */
package org.tensorflow.types.family;
