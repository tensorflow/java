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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Init;
import org.tensorflow.types.family.TType;

class GraphVariable<T extends TType> extends Variable<T> {

  private final org.tensorflow.op.core.Variable<T> variable;
  private Operand<T> get;

  GraphVariable(Scope scope, Shape shape, DataType<T> dataType) {
    super(scope, shape, dataType);
    variable = org.tensorflow.op.core.Variable.create(scope, shape, dataType);
  }

  @Override
  protected Operand<T> getValue() {
    if(get == null){
      throw new IllegalStateException("Variable has not been initialized.");
    }
    return get;
  }

  @Override
  protected void doInitialize(Scope scope, Operand<T> value) {
    Assign<T> assignOp = Assign.create(scope, variable, value);
    Init.add(scope, assignOp);
    get = assignOp;
  }

  @Override
  protected void doAssign(Scope scope, Operand<T> value) {
    get = Assign.create(scope, variable, value);
  }
}
