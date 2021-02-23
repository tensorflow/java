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

  /**
   * Apply boolean mask to tensor.  Returns the flat array of each element corresponding to a {@code true} in the mask.
   * <p>
   * Numpy equivalent is {@code tensor[mask]}.
   * <p>
   * In general, {@code 0 < dim(mask) = K <= dim(tensor)}, and {@code mask}'s shape must match
   * the first K dimensions of {@code tensor}'s shape.  We then have:
   *   {@code booleanMask(tensor, mask)[i, j1,...,jd] = tensor[i1,...,iK,j1,...,jd]}
   * where {@code (i1,...,iK)} is the ith {@code true} entry of {@code mask} (row-major order).
   * <p>
   * The {@code axis} could be used with {@code mask} to indicate the axis to mask from (it's 0 by default).
   * In that case, {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match
   * the first {@code axis + dim(mask)} dimensions of {@code tensor}'s shape.
   *
   * @param scope
   * @param tensor The tensor to mask.
   * @param mask The mask to apply.
   * @param options carries optional attributes values
   * @return The masked tensor.
   */
  @Endpoint(name = "booleanMask")
  public static <T extends TType> Operand<T> create(Scope scope, Operand<T> tensor, Operand<TBool> mask,
      Options... options) {

    scope = scope.withNameAsSubScope("BooleanMask");

    int axis = 0;
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          axis = opts.axis;
        }
      }
    }

    if (axis < 0) {
      axis += tensor.rank();
    }

    Shape maskShape = mask.shape();
    Shape tensorShape = tensor.shape();

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

    org.tensorflow.op.core.Shape<TInt32> liveShape = org.tensorflow.op.core.Shape.create(scope, tensor);

    Operand<TInt32> leadingSize = ReduceProd.create(scope,
        StridedSliceHelper.stridedSlice(scope,
            liveShape,
            Indices.range(axis, axis + maskShape.numDimensions())
        ),
        Constant.arrayOf(scope, 0)
    );

    Operand<T> flattened = Reshape.create(scope, tensor, Concat.create(
        scope,
        Arrays.asList(
            StridedSliceHelper.stridedSlice(scope, liveShape, Indices.sliceTo(axis)),
            Reshape.create(scope, leadingSize, Constant.arrayOf(scope, 1)),
            StridedSliceHelper.stridedSlice(scope, liveShape, Indices.sliceFrom(axis + maskShape.numDimensions()))
        ),
        Constant.scalarOf(scope, 0)
    ));

    Operand<TBool> flatMask = Reshape.create(scope, mask, Constant.arrayOf(scope, -1));

    Operand<TInt64> indices = Squeeze.create(scope, Where.create(scope, flatMask), Squeeze.axis(Collections.singletonList(1L)));
    return Gather.create(scope, flattened, indices, axisTensor);
  }

  /**
   * Used to indicate the axis to mask from.
   * {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match
   * the first {@code axis + dim(mask)} dimensions of {@code tensor}'s shape.
   * @param axis the axis to mask from.  Uses 0 if null.
   */
  public static Options axis(Integer axis){
    return new Options().axis(axis);
  }


  /**
   * Used to indicate the axis to mask from.
   * {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match
   * the first {@code axis + dim(mask)} dimensions of {@code tensor}'s shape.
   * @param axis the axis to mask from.
   */
  public static Options axis(int axis){
    return new Options().axis(axis);
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

    private Integer axis;

    private Options() {
    }
  }

}
