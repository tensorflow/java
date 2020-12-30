/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.op

import org.tensorflow.internal.types.registry.TensorTypeRegistry
import org.tensorflow.proto.framework.DataType
import org.tensorflow.types.family.TType
import kotlin.reflect.KClass

/**
 * Converts a tensor type class to a [DataType] attribute.
 *
 * @return data type
 * @see Operands.toDataType
 */
public fun <T: TType> Class<T>.dataType(): DataType = Operands.toDataType(this)

/**
 * Converts a tensor type class to a [DataType] attribute.
 *
 * @return data type
 * @see Operands.toDataType
 */
public fun <T: TType> KClass<T>.dataType(): DataType = Operands.toDataType(this.java)

/**
 * Converts a tensor type class to a [DataType] attribute.
 *
 * @return data type
 * @see Operands.toDataType
 */
public inline fun <reified T: TType> dataType(): DataType = T::class.dataType()

/**
 * Converts a [DataType] attribute to a tensor type class.
 *
 * @return the tensor type class
 * @see TensorTypeRegistry.find
 */
public fun <T: TType> DataType.tType(): Class<T> = TensorTypeRegistry.find<T>(this).type()
