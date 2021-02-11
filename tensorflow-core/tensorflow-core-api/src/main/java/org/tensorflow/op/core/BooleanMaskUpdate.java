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
public abstract class BooleanMaskUpdate {

  /**
   * Updates a tensor at the masked values, and returns the updated tensor.  Does not mutate the input tensors.  {@code
   * updates} will be broadcasted by default
   * <p>
   * Numpy equivalent is `tensor[mask] = updates`.
   * <p>
   * In general, {@code 0 < dim(mask) = K <= dim(tensor)}, and {@code mask}'s shape must match the first K dimensions of
   * {@code tensor}'s shape.  We then have: {@code booleanMask(tensor, mask)[i, j1,...,jd] =
   * tensor[i1,...,iK,j1,...,jd]} where {@code (i1,...,iK)} is the ith {@code true} entry of {@code mask} (row-major
   * order).
   * <p>
   * The {@code axis} could be used with {@code mask} to indicate the axis to mask from (it's 0 by default). In that
   * case, {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match the first {@code axis +
   * dim(mask)} dimensions of {@code tensor}'s shape.
   * <p>
   * The shape of {@code updates} should be {@code [n, t_1, t_2, ...]} where {@code n} is the number of true values in
   * {@code mask} and {@code t_i} is the {@code i}th dimension of {@code tensor} after {@code axis} and {@code mask}.
   * {@code updates} will be broadcasted to this shape by default, which can be disabled using {@code options}.
   *
   * @param tensor The tensor to mask.
   * @param mask The mask to apply.
   * @param updates the new values
   * @param options carries optional attributes values
   * @return The masked tensor.
   */
  @Endpoint(name = "booleanMaskUpdate")
  public static <T extends TType> Operand<T> create(Scope scope, Operand<T> tensor, Operand<TBool> mask,
      Operand<T> updates,
      Options... options) {

    scope = scope.withNameAsSubScope("BooleanMaskUpdate");

    int axis = 0;
    boolean broadcast = true;
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          axis = opts.axis;
        }
        if (opts.broadcast != null) {
          broadcast = opts.broadcast;
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

    Shape requiredMaskShape = tensorShape.subShape(axis, axis + maskShape.numDimensions());
    if (!requiredMaskShape.isCompatibleWith(maskShape)) {
      throw new IllegalArgumentException(
          "Mask shape " + maskShape + " is not compatible with the required mask shape: " + requiredMaskShape + ".");
    }

    Operand<TInt32> liveShape = org.tensorflow.op.core.Shape.create(scope, tensor);

    Operand<TInt32> leadingSize = ReduceProd.create(scope,
        StridedSliceHelper.stridedSlice(scope,
            liveShape,
            Indices.sliceTo(axis + maskShape.numDimensions())
        ),
        Constant.arrayOf(scope, 0)
    );

    Operand<TInt32> innerShape = StridedSliceHelper
        .stridedSlice(scope, liveShape, Indices.sliceFrom(axis + maskShape.numDimensions()));

    Operand<T> reshaped = Reshape.create(scope, tensor, Concat.create(
        scope,
        Arrays.asList(
            Reshape.create(scope, leadingSize, Constant.arrayOf(scope, 1)),
            innerShape
        ),
        Constant.scalarOf(scope, 0)
    ));

    Operand<TInt64> indices = Where.create(scope, mask);

    if (broadcast) {
      Operand<TInt32> indicesShape = org.tensorflow.op.core.Shape.create(scope, indices);
      // this is the number of true values
      Operand<TInt32> batchShape = StridedSliceHelper.stridedSlice(scope, indicesShape, Indices.sliceTo(-1));

      Operand<TInt32> updateShape = Concat.create(
          scope,
          Arrays.asList(
              batchShape,
              innerShape
          ),
          Constant.scalarOf(scope, 0)
      );

      updates = BroadcastTo.create(scope, updates, updateShape);
    }

    Operand<T> newValue = TensorScatterNdUpdate.create(scope, reshaped, indices, updates);
    return Reshape.create(scope, newValue, liveShape);
  }

  /**
   * Used to indicate the axis to mask from. {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match
   * the first {@code axis + dim(mask)} dimensions of {@code tensor}'s shape.
   *
   * @param axis the axis to mask from.  Uses 0 if null.
   */
  public static Options axis(Integer axis) {
    return new Options().axis(axis);
  }

  /**
   * Whether to try broadcasting update.  True by default.
   */
  public static Options broadcast(Boolean broadcast) {
    return new Options().broadcast(broadcast);
  }

  /**
   * Optional attributes for {@link BooleanMaskUpdate}
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
     * @param broadcast (Optional) Whether to try broadcasting update.  True by default.
     */
    public Options broadcast(Boolean broadcast) {
      this.broadcast = broadcast;
      return this;
    }

    private Integer axis;
    private Boolean broadcast;

    private Options() {
    }
  }

}
