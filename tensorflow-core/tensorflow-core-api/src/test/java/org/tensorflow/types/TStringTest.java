/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;

public class TStringTest {

  @Test
  public void createScalar() {
    Tensor<TString> tensor = TString.scalarOf("Pretty vacant");
    assertNotNull(tensor);

    TString data = tensor.data();
    assertNotNull(data);
    assertEquals(Shape.scalar(), data.shape());
    assertEquals("Pretty vacant", data.getObject());
  }

  @Test
  public void createVector() {
    Tensor<TString> tensor = TString.vectorOf("Pretty", "vacant");
    assertNotNull(tensor);

    TString data = tensor.data();
    assertNotNull(data);
    assertEquals(Shape.of(2), data.shape());
    assertEquals("Pretty", data.getObject(0));
    assertEquals("vacant", data.getObject(1));
  }

  @Test
  public void createCopy() {
    NdArray<String> strings = NdArrays.ofObjects(String.class, Shape.of(2, 2))
        .setObject("Pretty", 0, 0)
        .setObject("vacant", 0, 1)
        .setObject("New", 1, 0)
        .setObject("York", 1, 1);

    Tensor<TString> tensor = TString.tensorOf(strings);
    assertNotNull(tensor);

    TString data = tensor.data();
    assertNotNull(data);
    strings.scalars().forEachIndexed((idx, s) ->
        assertEquals(s.getObject(), data.getObject(idx))
    );
  }

  @Test
  public void defaultCharsetIsUtf8() {
    Tensor<TString> tensor = TString.tensorOf(NdArrays.scalarOfObject(BABY_CHICK));
    byte[] bytes = tensor.data().asBytes().getObject();
    assertArrayEquals(new byte[] { (byte)0xF0, (byte)0x9F, (byte)0x90, (byte)0xA5 }, bytes);
    assertEquals(BABY_CHICK, tensor.data().getObject());
  }

  @Test
  public void usingDifferentCharset() {
    Tensor<TString> tensor = TString.tensorOf(StandardCharsets.UTF_16LE, NdArrays.scalarOfObject(BABY_CHICK));
    byte[] bytes = tensor.data().asBytes().getObject();
    assertArrayEquals(new byte[] { (byte)0x3D, (byte)0xD8, (byte)0x25, (byte)0xDC }, bytes);
    assertEquals(BABY_CHICK, tensor.data().using(StandardCharsets.UTF_16LE).getObject());
  }

  @Test
  public void initializingTensorWithRawBytes() {
    String[] strings = new String[] { "TensorFlow", "For", "Java", "Rocks", "!" };
    NdArray<byte[]> bytes = NdArrays.ofObjects(byte[].class, Shape.of(strings.length));
    for (int i = 0; i < strings.length; ++i) {
      bytes.setObject(strings[i].getBytes(), i);
    }
    Tensor<TString> tensor = TString.tensorOfBytes(bytes);
    assertNotNull(tensor);
    assertEquals(bytes.shape(), tensor.shape());

    NdArray<byte[]> tensorBytes = tensor.data().asBytes();
    for (int i = 0; i < strings.length; ++i) {
      assertArrayEquals(bytes.getObject(i), tensorBytes.getObject(i));
    }
  }

  private static final String BABY_CHICK = "\uD83D\uDC25";	  
}
