/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.keras.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.tensorflow.DataType;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

/**
 *
 * @author Jim Clarke
 */
public class TypeUtilTest {

    public TypeUtilTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of isFloating method, of class TypeUtils.
     */
    @Test
    public void testIsFloating() {
        DataType dtype = TFloat32.DTYPE;
        boolean expResult = true;
        boolean result = TypeUtils.isFloating(dtype);
        assertEquals(expResult, result);

        dtype = TInt32.DTYPE;
        expResult = false;
        result = TypeUtils.isFloating(dtype);
        assertEquals(expResult, result);

        dtype = TString.DTYPE;
        expResult = false;
        result = TypeUtils.isFloating(dtype);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInteger method, of class TypeUtils.
     */
    @Test
    public void testIsInteger() {
        DataType dtype = TFloat32.DTYPE;
        boolean expResult = false;
        boolean result = TypeUtils.isInteger(dtype);
        assertEquals(expResult, result);

        dtype = TInt32.DTYPE;
        expResult = true;
        result = TypeUtils.isInteger(dtype);
        assertEquals(expResult, result);

        dtype = TString.DTYPE;
        expResult = false;
        result = TypeUtils.isInteger(dtype);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNumeric method, of class TypeUtils.
     */
    @Test
    public void testIsNumeric() {
        DataType dtype = TFloat16.DTYPE;
        boolean expResult = true;
        boolean result = TypeUtils.isNumeric(dtype);
        assertEquals(expResult, result);

        dtype = TInt64.DTYPE;
        expResult = true;
        result = TypeUtils.isNumeric(dtype);
        assertEquals(expResult, result);

        dtype = TUint8.DTYPE;
        expResult = true;
        result = TypeUtils.isNumeric(dtype);
        assertEquals(expResult, result);

        dtype = TBool.DTYPE;
        expResult = false;
        result = TypeUtils.isNumeric(dtype);
        assertEquals(expResult, result);

        dtype = TString.DTYPE;
        expResult = false;
        result = TypeUtils.isNumeric(dtype);
        assertEquals(expResult, result);
    }

    /**
     * Test of isBoolean method, of class TypeUtils.
     */
    @Test
    public void testIsBoolean() {
        DataType dtype = TFloat16.DTYPE;
        boolean expResult = false;
        boolean result = TypeUtils.isBoolean(dtype);
        assertEquals(expResult, result);

        dtype = TInt64.DTYPE;
        expResult = false;
        result = TypeUtils.isBoolean(dtype);
        assertEquals(expResult, result);

        dtype = TBool.DTYPE;
        expResult = true;
        result = TypeUtils.isBoolean(dtype);
        assertEquals(expResult, result);
    }

    /**
     * Test of isString method, of class TypeUtils.
     */
    @Test
    public void testIsString() {
        DataType dtype = TFloat16.DTYPE;
        boolean expResult = false;
        boolean result = TypeUtils.isString(dtype);
        assertEquals(expResult, result);

        dtype = TBool.DTYPE;
        expResult = false;
        result = TypeUtils.isString(dtype);
        assertEquals(expResult, result);

        dtype = TString.DTYPE;
        expResult = true;
        result = TypeUtils.isString(dtype);
        assertEquals(expResult, result);
    }

    /**
     * Test of of method, of class TypeUtils.
     */
    @Test
    public void testOf() {
        String name = "BFLOAT16";
        DataType expResult = TBfloat16.DTYPE;
        DataType result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "FLOAT16";
        expResult = TFloat16.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "FLOAT";
        expResult = TFloat32.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "DOUBLE";
        expResult = TFloat64.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "UINT8";
        expResult = TUint8.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "INT32";
        expResult = TInt32.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "INT64";
        expResult = TInt64.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "BOOL";
        expResult = TBool.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "STRING";
        expResult = TString.DTYPE;
        result = TypeUtils.of(name);
        assertEquals(expResult, result);

        name = "FOO";
        try {
            TypeUtils.of(name);
            fail("IllegalArgumentException not thrown.");
        } catch (IllegalArgumentException expected) {

        }

    }

}
