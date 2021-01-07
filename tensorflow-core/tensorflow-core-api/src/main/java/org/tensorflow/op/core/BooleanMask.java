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
package org.tensorflow.op.core;

import java.util.Arrays;
import java.util.Collections;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

@Operator
public abstract class BooleanMask {

  @Endpoint(name = "booleanMask")
  public static <T extends TType> Operand<T> create(Scope scope, Operand<T> x, Operand<TBool> mask,
      Options... options) {

    //TODO naming to match python

    int axis = 0;
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          axis = opts.axis;
        }
      }
    }

    if (axis < 0) {
      axis += x.rank();
    }

    Shape maskShape = mask.shape();
    Shape tensorShape = x.shape();

    if (maskShape.numDimensions() == 0) {
      throw new IllegalArgumentException("Mask cannot be a scalar.");
    }
    if (maskShape.hasUnknownDimension()) {
      throw new IllegalArgumentException("Mask cannot have unknown number of dimensions");
    }

    Operand<TInt32> axisTensor = Constant.scalarOf(scope, axis);
    Shape requiredMaskShape = tensorShape.subShape(axis, axis + maskShape.numDimensions());
    if (!requiredMaskShape.isCompatibleWith(maskShape)) {
      throw new IllegalArgumentException(
          "Mask shape " + maskShape + " is not compatible with the required mask shape: " + requiredMaskShape + ".");
    }

    org.tensorflow.op.core.Shape<TInt32> liveShape = org.tensorflow.op.core.Shape.create(scope, x);

    Operand<TInt32> leadingSize = ReduceProd.create(scope,
        StridedSliceHelper.stridedSlice(scope,
            liveShape,
            Indices.range(axis, axis + maskShape.numDimensions())
        ),
        Constant.arrayOf(scope, 0)
    );

    Operand<T> tensor = Reshape.create(scope, x, Concat.create(
        scope,
        Arrays.asList(
            StridedSliceHelper.stridedSlice(scope, liveShape, Indices.to(axis)),
            Reshape.create(scope, leadingSize, Constant.arrayOf(scope, 1)),
            StridedSliceHelper.stridedSlice(scope, liveShape, Indices.from(axis + maskShape.numDimensions()))
        ),
        Constant.scalarOf(scope, 0)
    ));

    Operand<TBool> flatMask = Reshape.create(scope, mask, Constant.arrayOf(scope, -1));

    Operand<TInt64> indices = Squeeze.create(scope, Where.create(scope, flatMask), Squeeze.axis(Collections.singletonList(1L)));
    return Gather.create(scope, tensor, indices, axisTensor);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.BooleanMask}
   */
  public static class Options {

    /**
     * @param axis (Optional) The axis to mask from, or 0 if not set.
     */
    public Options axis(Integer axis) {
      this.axis = axis;
      return this;
    }

    /**
     * @param axis (Optional) The axis to mask from, or 0 if not set.
     */
    public Options axis(int axis) {
      this.axis = axis;
      return this;
    }

    private Integer axis;

    private Options() {
    }
  }

}
