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
=======================================================================*/
package org.tensorflow.framework.op.math;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/** Axes Operations */
public class Axes {

  /**
   * Creates an Operand that has all axes contained in the Operand's shape.
   *
   * @param scope The TensorFlow scope
   * @param op the Operand
   * @return an Operand that has all axes contained in the Operand's shape..
   */
  public static Operand<TInt32> allAxes(Scope scope, Operand<? extends TType> op) {
    int rank = op.shape().numDimensions();
    if (rank != Shape.UNKNOWN_SIZE) {
      int[] axes = new int[rank];
      for (int i = 0; i < rank; i++) {
        axes[i] = i;
      }
      return Constant.vectorOf(scope, axes);
    } else {
      return Range.create(
          scope, Constant.scalarOf(scope, 0), Rank.create(scope, op), Constant.scalarOf(scope, 1));
    }
  }
}
