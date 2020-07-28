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
public class TypeUtils {
    
    //TODO
    public static boolean isComplex(DataType dtype) {
        return false;
    }
    
    public static boolean isFloating(DataType dtype) {
        switch(dtype.name()) {
            case "BFLOAT16":
            case "FLOAT16":
            case "FLOAT":
            case "DOUBLE":
                return true;
            default: return false;
        }
    }
    public static boolean isInteger(DataType dtype) {
        switch(dtype.name()) {
            case "INT32":
            case "INT64":
            case "UINT8":
                return true;
            default: return false;
        }
    }
    
    public static boolean isNumeric(DataType dtype) {
        return isFloating(dtype) || isInteger(dtype);
    }
    
    public static boolean isBoolean(DataType dtype) {
        return dtype.name().equals("BOOL");
    }
    
    public static boolean isString(DataType dtype) {
        return dtype.name().equals("STRING");
    }
    
    public static DataType of(String name) {
        switch(name) {
            case "BFLOAT16":
                return TBfloat16.DTYPE;
            case "FLOAT16":
                return TFloat16.DTYPE;
            case "FLOAT":
                return TFloat32.DTYPE;
            case "DOUBLE":
                return TFloat64.DTYPE;
            case "UINT8":
                return TUint8.DTYPE;
            case "INT32":
                return TInt32.DTYPE;
            case "INT64":
                return TInt64.DTYPE;
            case "BOOL":
                return TBool.DTYPE;
            case "STRING":
                return TString.DTYPE;
            default:
                throw new IllegalArgumentException(String.format("%s is an unknown DataType", name));
                
        }
    }
    
}
