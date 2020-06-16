/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.ExpandDims;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Shape;
import org.tensorflow.op.core.Size;
import org.tensorflow.op.core.Slice;
import org.tensorflow.op.math.FloorMod;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Gather;
import org.tensorflow.op.core.OnesLike;
import org.tensorflow.op.core.ReduceProd;
import org.tensorflow.op.core.Where;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.NotEqual;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An operator working with org.tensorflow.op.core.Shape tensors
 */
@Operator
public abstract class ShapeOps {

    /**
     * flatten the shape to 1 dimension
     *
     * @param <T> the type of operand
     * @param scope current scope
     * @param operand the operand to flatten
     * @return the reshaped operand
     */
    @Endpoint(name = "flatten")
    public static <T extends TType> Operand<T> flatten(Scope scope, Operand<T> operand) {
        return flatten(scope, operand, TInt32.DTYPE);
    }

    /**
     * flatten the shape to 1 dimension
     *
     * @param <T> the type of operand
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param operand the operand to flatten
     * @param dType the shape datatype.
     * @return the reshaped operand
     */
    @Endpoint(name = "flatten")
    public static <T extends TType, U extends TNumber> Operand<T> flatten(Scope scope, Operand<T> operand, DataType<U> dType) {
        Operand<U> flatShape = flatten(scope, Shape.create(scope, operand, dType), dType);
        return Reshape.create(scope, operand, flatShape);
    }

    /**
     * flatten the shape to 1 dimension
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the flattened shape
     * @see reduceDims
     */
    @Endpoint(name = "flatten")
    public static Operand<TInt32> flatten(Scope scope, Shape<TInt32> shape) {
        return flatten(scope, shape, TInt32.DTYPE);
    }

    /**
     * flatten the shape to 1 dimension
     *
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dType the shape datatype.
     * @return the flattened shape
     * @see reduceDims
     */
    @Endpoint(name = "flatten")
    public static <U extends TNumber> Operand<U> flatten(Scope scope, Shape<U> shape, DataType<U> dType) {
        return ExpandDims.create(scope,
                size(scope, shape, dType),
                Cast.create(scope, Constant.scalarOf(scope, -1), TInt32.DTYPE));
    }

    /**
     * get the size represented by the TensorFlow shape
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the size
     */
    @Endpoint(name = "size")
    public static Operand<TInt32> size(Scope scope, Shape<TInt32> shape) {
        return size(scope, shape, TInt32.DTYPE);
    }

    /**
     * get the size represented by the TensorFlow shape
     *
     * @param <U> the shape datatype. the type of the shape
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dType the shape datatype.
     * @return the size
     */
    @Endpoint(name = "size")
    public static <U extends TNumber> Operand<U> size(Scope scope, Shape<U> shape, DataType<U> dType) {
        Slice<U> dims = Slice.create(scope, shape,
                Cast.create(scope, Constant.arrayOf(scope, (new int[]{0})), dType),
                ExpandDims.create(scope, Cast.create(scope, Constant.scalarOf(scope, -1), dType), Constant.scalarOf(scope, -1)));
        ReduceProd<U> total = ReduceProd.create(scope, dims, Constant.scalarOf(scope, 0));
        return total;
    }

    @Endpoint(name = "size")
    public static Operand<TInt32> size(Scope scope, Shape<TInt32> shape, Operand<TInt32> dim) {
        return size(scope, shape, dim, TInt32.DTYPE);

    }

    /**
     * get the size of the specified dimension in the shape
     *
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dim the dimension
     * @param dType the shape datatype.
     * @return the size of the specified dimension
     */
    @Endpoint(name = "size")
    public static <U extends TNumber> Operand<U> size(Scope scope, Shape<U> shape, Operand<U> dim, DataType<U> dType) {
        Slice<U> dims = Slice.create(scope, shape,
                ExpandDims.create(scope, dim, Cast.create(scope, Constant.scalarOf(scope, -1), dType)),
                ExpandDims.create(scope,
                        Cast.create(scope, Constant.scalarOf(scope, 1), dType),
                        Cast.create(scope, Constant.scalarOf(scope, -1), dType)));
        return dims;
    }

    /**
     * get the size of the specified dimension for the shape of the tensor
     *
     * @param scope current scope
     * @param input the operand
     * @param dim the dimension
     * @return the size of the specified dimension
     */
    @Endpoint(name = "size")
    public static Operand<TInt32> size(Scope scope, Operand input, Operand<TInt32> dim) {
        return size(scope, input, dim, TInt32.DTYPE);
    }

    /**
     * get the size of the specified dimension for the shape of the tensor
     *
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param input the operand
     * @param dim the dimension
     * @param dType the shape datatype.
     * @return the size of the specified dimension
     */
    @Endpoint(name = "size")
    public static <U extends TNumber> Operand<U> size(Scope scope, Operand input,
            Operand<U> dim, DataType<U> dType) {
        return size(scope, Shape.create(scope, input, dType), dim, dType);
    }

    /**
     * get the number of dimensions of the shape object
     *
     * @param scope current scope
     * @param shape the shape
     * @return the number of dimensions
     * @see tf.rank
     */
    @Endpoint(name = "numDimensions")
    public static Operand<TInt32> numDimensions(Scope scope, Shape<TInt32> shape) {
        return Size.create(scope, shape, TInt32.DTYPE);
    }

    /**
     * get the number of dimensions of the shape object
     *
     * @param <U> the shape datatype.
     * @param scope the curren scope
     * @param shape the shape
     * @param dType the shape datatype.
     * @return the number of dimensions
     * @see tf.rank
     */
    @Endpoint(name = "numDimensions")
    public static <U extends TNumber> Operand<U> numDimensions(Scope scope,
            Shape<U> shape, DataType<U> dType) {
        return Size.create(scope, shape, dType);
    }

    /**
     * reshapes the operand to the specified axis,
     *
     * @param <T> the type of Operand
     * @param scope current scope
     * @param operand the operand
     * @param axis the axis
     * @return the reshaped operand
     */
    @Endpoint(name = "reduceDims")
    public static <T extends TType> Operand<T> reduceDims(Scope scope,
            Operand<T> operand, Operand<TInt32> axis) {
        return reduceDims(scope, operand, axis, TInt32.DTYPE);
    }

    /**
     * reshapes the operand to the specified axis,
     *
     * @param <T> the type of Operand
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param operand the operand
     * @param axis the axis
     * @param dType the shape datatype.
     * @return the reshaped operand
     */
    @Endpoint(name = "reduceDims")
    public static <T extends TType, U extends TNumber> Operand<T> reduceDims(
            Scope scope, Operand<T> operand, Operand<U> axis, DataType<U> dType) {
        Shape<U> newShape = Shape.create(scope, operand, dType);
        return Reshape.create(scope, operand, reduceDims(scope, newShape, axis, dType));
    }

    /**
     * reduces the shape to the specified axis,
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @return the reduced shape
     */
    @Endpoint(name = "reduceDims")
    public static Operand<TInt32> reduceDims(Scope scope,
            Shape<TInt32> shape, Operand<TInt32> axis) {
        return reduceDims(scope, shape, axis, TInt32.DTYPE);
    }

    /**
     * reduces the shape to the specified axis,
     *
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @param dType the shape datatype.
     * @return the reduced shape
     */
    @Endpoint(name = "reduceDims")
    public static <U extends TNumber> Operand<U> reduceDims(Scope scope,
            Shape<U> shape, Operand<U> axis, DataType<U> dType) {
        Size<U> rank = Size.create(scope, shape, dType);
        axis = FloorMod.create(scope, axis, rank);
        Sub<U> remainder = Sub.create(scope, rank, axis);

        Operand<U> dims1 = Slice.create(scope, shape,
                Cast.create(scope, Constant.arrayOf(scope, (new int[]{0})), dType),
                ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)));

        Operand<U> dims2 = Slice.create(scope, shape,
                ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)),
                ExpandDims.create(scope, Cast.create(scope, Constant.scalarOf(scope, -1), dType), Constant.scalarOf(scope, -1)));

        Operand<U> prod = ReduceProd.create(scope, dims2, Constant.scalarOf(scope, 0), ReduceProd.keepDims(Boolean.TRUE));

        Concat<U> concat = Concat.create(scope,
                Arrays.asList(dims1, prod), Constant.scalarOf(scope, 0));

        return concat;
    }

    /**
     * Removes dimensions of size 1 from the shape
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the squeezed shape
     * @see tf.squeeze
     */
    @Endpoint(name = "squeeze")
    public static Operand<TInt32> squeeze(Scope scope, Shape<TInt32> shape) {
        return squeeze(scope, shape, TInt32.DTYPE);
    }

    /**
     * Removes dimensions of size 1 from the shape
     *
     * @param <U> the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dType the shape datatype.
     * @return the squeezed shape
     *
     * @see tf.squeeze
     */
    @Endpoint(name = "squeeze")
    public static <U extends TNumber> Operand<U> squeeze(Scope scope,
            Shape<U> shape, DataType<U> dType) {
        Operand<TBool> mask = NotEqual.create(scope, shape,
                Cast.create(scope, OnesLike.create(scope, shape), dType));

        Gather<U> gather = Gather.create(scope, shape,
                Where.create(scope, mask),
                Constant.scalarOf(scope, 0));

        return gather;
    }
}
