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

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TString;

/**
 *  Utility to print Tensor values
 */
public class PrintUtils {

    /**
     * Print a tensor's values
     * @param tensor the tensor to print
     */
    public static void print(Tensor tensor) {
        switch(tensor.dataType().name()) {
            case "BFLOAT16":
                printTBfloat16(tensor);
                break;
            case "FLOAT16":
                 printTFloat16(tensor);
                break;
            case "FLOAT":
                 printTFloat32(tensor);
                break;
            case "DOUBLE":
                 printTFloat64(tensor);
                break;
            case "INT32":
                 printTInt32(tensor);
                break;
            case "INT64":
                 printTInt64(tensor);
                break;
            case "UINT8":
                 printTUint8(tensor);
                break;
            case "BOOL":
                 printTBool(tensor);
                break;
            case "STRING":
                 printTString(tensor);
                break;
            default:
                break;
        }
        
    }

    /**
     * Print a boolean Tensot
     * @param t the tensor to print
     */
    public static void printTBool(Tensor<TBool> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getBoolean() + ", "));
        System.out.println();
    }

    /**
     * Print a String Tensot
     * @param t the tensor to print
     */
    public static void printTString(Tensor<TString> t) {
        t.data().scalars().forEach(s -> System.out.print("\"" + s.getObject() + "\", "));
        System.out.println();
    }

    /**
     * Print a TBfloat16 Tensor
     * @param t the tensor to print
     */
    public static void printTBfloat16(Tensor<TBfloat16> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getFloat() + ", "));
        System.out.println();
    }

    /**
     * Print a uint8 Tensor
     * @param t the tensor to print
     */
    public static void printTUint8(Tensor<TUint8> t) {
        t.data().scalars().forEach(s -> System.out.print(String.format("0x%x",s.getByte()) + ", "));
        System.out.println();
    }

    /**
     * Print an int32 Tensor
     * @param t the tensor to print
     */
     public static void printTInt32(Tensor<TInt32> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getInt() + ", "));
        System.out.println();
    }

    /**
     * Print an int64 Tensor
     * @param t the tensor to print
     */
     public static void printTInt64(Tensor<TInt64> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getLong() + ", "));
        System.out.println();
    }

    /**
     * Print a Float16 tensor.
     * @param t the tensor to print
     */
    public static void printTFloat16(Tensor<TFloat16> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getFloat() + "F, "));
        System.out.println();
    }

    /**
     * Print a Float32 tensor.
     * @param t the tensor to print
     */
    public static void printTFloat32(Tensor<TFloat32> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getFloat() + "F, "));
        System.out.println();
    }
    /**
     * Print a Float64 tensor.
     * @param t the tensor to print
     */
    public static void printTFloat64(Tensor<TFloat64> t) {
        t.data().scalars().forEach(s -> System.out.print(s.getDouble() + ", "));
        System.out.println();
    }
}
