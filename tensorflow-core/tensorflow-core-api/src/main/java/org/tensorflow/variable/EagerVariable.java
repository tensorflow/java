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
package org.tensorflow.variable;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

class EagerVariable<T extends TType> extends MutableVariable<T> {

  private Operand<T> value = null;

  EagerVariable(Scope scope, Shape shape, DataType dtype) {
    super(scope, shape, dtype);
  }

  @Override
  protected Operand<T> getValue() {
    if(value == null){
      throw new IllegalStateException("Value has not been initialized.");
    }
    return value;
  }

  @Override
  protected void doInitialize(Scope scope, Operand<T> value) {
    this.value = value;
  }

  @Override
  protected void doAssign(Scope scope, Operand<T> value) {
    this.value = value;
  }
}
