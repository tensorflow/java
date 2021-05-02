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
package org.tensorflow.framework.regularizers;

import org.tensorflow.framework.utils.ND;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.StdArrays;

public class CommonTest {

  protected float regularizeL1L2(float[][] w, float l1, float l2) {
    return regularizeL1(w, l1) + regularizeL2(w, l2);
  }

  protected float regularizeL1(float[][] w, float l1) {
    FloatNdArray fa = StdArrays.ndCopyOf(w);
    fa = ND.abs(fa);
    FloatNdArray sum = ND.sum(fa);
    FloatNdArray mul = ND.mul(sum, l1);
    return mul.getFloat();
  }

  protected float regularizeL2(float[][] w, float l2) {
    FloatNdArray fa = StdArrays.ndCopyOf(w);
    fa = ND.square(fa);
    FloatNdArray sum = ND.sum(fa);
    FloatNdArray mul = ND.mul(sum, l2);
    return mul.getFloat();
  }

  protected double regularizeL1L2(double[][] w, float l1, float l2) {
    return regularizeL1(w, l1) + regularizeL2(w, l2);
  }

  protected double regularizeL1(double[][] w, float l1) {
    DoubleNdArray fa = StdArrays.ndCopyOf(w);
    fa = ND.abs(fa);
    DoubleNdArray sum = ND.sum(fa);
    DoubleNdArray mul = ND.mul(sum, l1);
    return mul.getDouble();
  }

  protected double regularizeL2(double[][] w, float l2) {
    DoubleNdArray fa = StdArrays.ndCopyOf(w);
    fa = ND.square(fa);
    DoubleNdArray sum = ND.sum(fa);
    DoubleNdArray mul = ND.mul(sum, l2);
    return mul.getDouble();
  }
}
