/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tensorflow.keras.utils;

import java.util.Arrays;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
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
 *
 * @author Jim Clarke
 * @param <T> the type of operand
 */

// TODO should shape be based on  TInt64  ???
public class ShapeOps<U extends TNumber > {
    private final Scope scope;
    private final DataType<U> dType;
    
    /**
     * Create a ShapeUtils with a DataType of TInt32
     * 
     * @param scope is a scope used to add the underlying operations
     * @return the ShapeUtils
     */
    public static ShapeOps<TInt32> create(Scope scope) {
        return create(scope, TInt32.DTYPE);
    }
    
    /**
     * 
     * @param <U> the Shape type
     * @param scope is a scope used to add the underlying operations
     * @param dType the Shape datatype
     * @return the ShapeUtils
     */
    public static <U extends TNumber> ShapeOps<U> create(Scope scope, DataType<U> dType) {
        return new ShapeOps<>(scope, dType);
    }
    
    /**
     * The constructor for ShapeUtils
     * @param scope is a scope used to add the underlying operations
     * @param dType the Shape datatype
     */
    private ShapeOps(Scope scope,  DataType<U> dType) {
        this.scope = scope;
        this.dType = dType;
    }
    
    public Scope scope() {
        return this.scope;
    }
    
    public  DataType<U> datatype() {
        return this.dType;
    }
    
    /**
     * flatten the shape to 1 dimension
     * 
     * @param <T> the type of operand
     * @param operand the operand to flatten
     * @return the reshaped operand
     */
    public <T extends TType> Operand<T> flatten(Operand<T> operand) {
        Operand<U> flatShape =  flatten(Shape.create(scope, operand, dType)); 
        return Reshape.create(scope, operand, flatShape);
    }
    
    /**
     * flatten the shape to 1 dimension
     * 
     * @param shape the TensorFlow shape
     * @return  the flattened shape
     * @see reduceDims
     */
    public Operand<U> flatten(Shape<U> shape) {
        return ExpandDims.create(scope, 
                        Cast.create(scope, size(shape), dType),
                        Cast.create(scope, Constant.scalarOf(scope, -1), dType));
        
    }
    
    
   /**
    * get the size represented by the TensorFlow shape
    * 
    * @param shape the TensorFlow shape
    * @return the size
    */
    public Operand<U> size(Shape<U> shape) {
        Slice<U> dims = Slice.create(scope, shape, 
                Cast.create(scope, Constant.arrayOf(scope, (new int[]{0})), dType),
                ExpandDims.create(scope, Cast.create(scope, Constant.scalarOf(scope, -1), dType),  Constant.scalarOf(scope, -1)));
        ReduceProd<U> total = ReduceProd.create(scope, dims, Constant.scalarOf(scope, 0));
        return total;
    }
   
    /**
     *  get the size of the specified dimension in the shape
     * 
     * @param shape the TensorFlow shape
     * @param dim the dimension 
     * @return  the size of the specified dimension
     */
    public Operand<U> size(Shape<U> shape, Operand<U> dim) {
        Slice<U> dims = Slice.create(scope, shape, 
                ExpandDims.create(scope,  dim, Cast.create(scope, Constant.scalarOf(scope, -1), dType)), 
                ExpandDims.create(scope, 
                        Cast.create(scope, Constant.scalarOf(scope, 1), dType),
                        Cast.create(scope, Constant.scalarOf(scope, -1), dType)));
        return dims;
    }
    
    /**
     * get the size of the specified dimension for the shape of the tensor
     * 
     * @param input the operand
     * @param dim the dimension
     * @return  the size of the specified dimension
     */
     public Operand<U> size(Operand input, Operand<U> dim) {
        return size(Shape.create(scope, input, dType), dim);
    }
    
     /**
      * get the number of dimensions of the shape object
      * 
      * @param shape the shape
      * @return the number of dimensions
      * @see tf.rank
      */
    public Operand<U> numDimensions(Shape<U> shape) {
        return Size.create(scope, shape, dType);
    }
    
    /**
     * reshapes the operand to the specified axis, 
     * @param <T> the type of Operand
     * @param operand the operand
     * @param axis the axis
     * @return the reshaped operand
     */
    public <T extends TType> Operand<T> reduceDims(Operand<T> operand , Operand<U> axis) {
        Shape<U> newShape = Shape.create(scope, operand, dType);
        return Reshape.create(scope, operand, reduceDims(newShape, axis));
    }
    
    /**
     * reduces the shape to the specified axis, 
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @return the reduced shape
     */
    public Operand<U> reduceDims(Shape<U> shape , Operand<U> axis) {
        Size<U> rank = Size.create(scope, shape, dType);
        axis = FloorMod.create(scope, axis, rank);
        Sub<U> remainder = Sub.create(scope, rank, axis);
        
        Operand<U> dims1 = Slice.create(scope, shape, 
                Cast.create(scope, Constant.arrayOf(scope, (new int[]{0})), dType),
                ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)));
        
        Operand<U> dims2 = Slice.create(scope, shape,
                ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)),
                ExpandDims.create(scope, Cast.create(scope, Constant.scalarOf(scope, -1), dType),  Constant.scalarOf(scope, -1)));
        
        Operand<U> prod = ReduceProd.create(scope, dims2, Constant.scalarOf(scope, 0), ReduceProd.keepDims(Boolean.TRUE));
        Concat<U> concat = Concat.create( scope, 
                Arrays.asList(dims1, prod), Constant.scalarOf(scope, 0)); 
                
        return concat;
    }
    
    
   
    /**
     * Removes dimensions of size 1 from the shape
     * @param shape
     * @return the squeezed shape
     * @see tf.squeeze
     */
    public Operand<U> squeeze(Shape<U> shape) {
         Operand<TBool> mask = NotEqual.create(scope, shape, 
                 Cast.create(scope, OnesLike.create(scope, shape), dType));
         
         Gather<U> gather = Gather.create(scope, shape, 
                 Where.create(scope, mask), 
                 Constant.scalarOf(scope, 0));
         
         return gather;
    }
}
