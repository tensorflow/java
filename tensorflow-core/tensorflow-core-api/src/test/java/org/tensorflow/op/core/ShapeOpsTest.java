/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tensorflow.keras.utils;

import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/***
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
****/


import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Shape;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/**
 *
 * @author jbclarke
 */
@RunWith(JUnit4.class)
public class ShapeOpsTest {
    
    public ShapeOpsTest() {
    }
    

    /**
     * Test of create method, of class ShapeOps.
     */
    @Test
    public void testCreate_Scope() {
        try (Graph g = new Graph();
                Session sess = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps stf = ShapeOps.create(scope);
            assertEquals(scope, stf.scope());
            assertEquals(TInt32.DTYPE, stf.datatype());
        }
    }

    /**
     * Test of create method, of class ShapeOps.
     */
    @Test
    public void testCreate_Scope_DataType() {
        System.out.println("create DataType");
        try (Graph g = new Graph();
                Session sess = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps stf = ShapeOps.create(scope, TInt64.DTYPE);
            assertEquals(scope, stf.scope());
            assertEquals(TInt64.DTYPE, stf.datatype());
        }
    }

    /**
     * Test of flatten method, of class ShapeOps.
     */
    @Test
    public void testFlatten_Operand() {
        System.out.println("flatten operand");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope, TInt64.DTYPE);
            Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
            Shape<TInt64> expResult = Shape.create(scope, operand, TInt64.DTYPE);
            Operand<TFloat32> reshaped = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
            Operand actual = instance.flatten(reshaped);
            Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.DTYPE);

            AtomicInteger index = new AtomicInteger();
            try (Tensor<TInt64> result1 = session.runner().fetch(tfshape.asOutput()).run().get(0).expect(TInt64.DTYPE);
                    Tensor<TInt64> result2 = session.runner().fetch(expResult.asOutput()).run().get(0).expect(TInt64.DTYPE)) {
                result1.data().scalars().forEach(s -> assertEquals(
                        result2.data().getLong(index.getAndIncrement()), s.getLong()));
            }
        }
    }

    /**
     * Test of flatten method, of class ShapeOps.
     */
    @Test
    public void testFlatten_Shape() {
        System.out.println("flatten shape");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope, TInt64.DTYPE);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Shape<TInt64> expShape = Shape.create(scope, operand, TInt64.DTYPE);
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,2,1} ));
            Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.DTYPE);
            Operand<TInt64> flattened = instance.flatten(tfshape);
            
            
            AtomicInteger index = new AtomicInteger();
            try (Tensor<TInt64> result1 = session.runner().fetch(flattened.asOutput()).run().get(0).expect(TInt64.DTYPE);
                    Tensor<TInt64> result2 = session.runner().fetch(expShape.asOutput()).run().get(0).expect(TInt64.DTYPE)) {
                result1.data().scalars().forEach(s -> assertEquals(
                        result2.data().getLong(index.getAndIncrement()), s.getLong()));
            }
        }
    }

    /**
     * Test of size method, of class ShapeOps.
     */
    @Test
    public void testSize_Shape() {
        System.out.println("size");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope, TInt64.DTYPE);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,2,1} ));
            Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.DTYPE);
            Operand<TInt64> size = instance.size(tfshape);
            
            
            AtomicInteger index = new AtomicInteger();
            try (Tensor<TInt64> result1 = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt64.DTYPE)) {
                result1.data().scalars().forEach(s -> assertEquals(8, s.getLong()));
            }
        }
    }

    /**
     * Test of size method, of class ShapeOps.
     */
    @Test
    public void testSize_Shape_Operand() {
        System.out.println("size");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,2,1} ));
            Shape<TInt32> tfshape = Shape.create(scope, actual);
            
            Operand<TInt32> size = instance.size(tfshape, Constant.scalarOf(scope, 0));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(4, s.getInt()));
            }
            
            size = instance.size(tfshape, Constant.scalarOf(scope, 1));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(2, s.getInt()));
            }
            
            size = instance.size(tfshape, Constant.scalarOf(scope, 2));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(1, s.getInt()));
            }
        }
    }

    /**
     * Test of size method, of class ShapeOps.
     */
    @Test
    public void testSize_Operand_Operand() {
        System.out.println("size");
         try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,2,1} ));
            
            Operand<TInt32> size = instance.size(actual, Constant.scalarOf(scope, 0));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(4, s.getInt()));
            }
            
            size = instance.size(actual, Constant.scalarOf(scope, 1));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(2, s.getInt()));
            }
            
            size = instance.size(actual, Constant.scalarOf(scope, 2));
            try (Tensor<TInt32> result = session.runner().fetch(size.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(1, s.getInt()));
            }
        }
    }

    /**
     * Test of numDimensions method, of class ShapeOps.
     */
    @Test
    public void testNumDimensions() {
        System.out.println("numDimensions");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,2,1} ));
            Shape<TInt32> tfshape = Shape.create(scope, actual);
            
            Operand<TInt32> nDims = instance.numDimensions(tfshape);
            try (Tensor<TInt32> result = session.runner().fetch(nDims.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> assertEquals(3, s.getInt()));
            }
            
        }
    }

    /**
     * Test of reduceDims method, of class ShapeOps.
     */
    @Test
    public void testReduceDims_Operand_Operand() {
        System.out.println("reduceDims");
        /**
        ShapeOps instance = null;
        Operand expResult = null;
        Operand result = instance.reduceDims(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        * * **/
    }

    /**
     * Test of reduceDims method, of class ShapeOps.
     */
    @Test
    public void testReduceDims_Shape_Operand() {
        System.out.println("reduceDims shape Operand");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 2,2,2} ));
            Shape<TInt32> tfshape = Shape.create(scope, actual);
            
            Operand<TInt32> reduced = instance.reduceDims(tfshape, Constant.scalarOf(scope, 0));
            AtomicInteger index = new AtomicInteger();
            int[] expected = { 8 };
            try (Tensor<TInt32> result = session.runner().fetch(reduced.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> {
                    assertEquals(expected[index.getAndIncrement()], s.getInt());
                        });
            }
            assertEquals(expected.length, index.get());
            
        }
    }

    /**
     * Test of squeeze method, of class ShapeOps.
     */
    @Test
    public void testSqueeze() {
        System.out.println("squeeze");
        try (Graph g = new Graph();
                Session session = new Session(g)) {
            Scope scope = new Scope(g);
            ShapeOps instance = ShapeOps.create(scope);
            Operand operand = Constant.arrayOf(scope, new float[] {1, 2, 3, 4, 5, 6, 7, 8} );
            Operand actual = Reshape.create(scope, operand, Constant.vectorOf(scope, new long[] { 4,1,2,1} ));
            Shape<TInt32> tfshape = Shape.create(scope, actual);
            
            Operand<TInt32> squeezed = instance.squeeze(tfshape);
            AtomicInteger index = new AtomicInteger();
            int[] expected = { 4, 2};
            try (Tensor<TInt32> result = session.runner().fetch(squeezed.asOutput()).run().get(0).expect(TInt32.DTYPE)) {
                result.data().scalars().forEach(s -> {
                    assertEquals(expected[index.getAndIncrement()], s.getInt());
                        });
            }
            assertEquals(expected.length, index.get());
            
        }
    }
    
}
