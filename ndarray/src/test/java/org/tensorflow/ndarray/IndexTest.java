/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.index.Indices;

public class IndexTest {
  @Test
  public void testNullConversions(){
    assertTrue(Indices.slice(null, 0L).beginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Indices.slice(null, 0L).beginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Indices.slice(null, null).beginMask(),
        "Passed null for slice start but didn't set begin mask");

    assertTrue(Indices.slice(0L, null).endMask(),
        "Passed null for slice end but didn't set end mask");

    assertTrue(Indices.slice(0L, null).endMask(),
        "Passed null for slice end but didn't set end mask");

    assertTrue(Indices.slice(null, null).endMask(),
        "Passed null for slice end but didn't set end mask");
  }

  @Test
  public void testNewaxis(){
    IntNdArray matrix3d = NdArrays.ofInts(Shape.of(5, 4, 5));

    matrix3d.scalars().forEachIndexed((coords, scalar) ->
        scalar.setInt((int)coords[2])
    );

    IntNdArray slice1 = matrix3d.slice(Indices.all(), Indices.all(), Indices.all(), Indices.newAxis());

    assertEquals(Shape.of(5, 4, 5, 1), slice1.shape());
    assertEquals(0, slice1.getInt(0, 0, 0, 0));
    assertEquals(1, slice1.getInt(0, 0, 1, 0));
    assertEquals(4, slice1.getInt(0, 0, 4, 0));
    assertEquals(2, slice1.getInt(0, 1, 2, 0));

    IntNdArray slice2 = matrix3d.slice(Indices.all(), Indices.all(), Indices.newAxis(), Indices.all());

    assertEquals(Shape.of(5, 4, 1, 5), slice2.shape());
    assertEquals(0, slice2.getInt(0, 0, 0, 0));
    assertEquals(1, slice2.getInt(0, 0, 0, 1));
    assertEquals(4, slice2.getInt(0, 0, 0, 4));
    assertEquals(2, slice2.getInt(0, 1, 0, 2));

    IntNdArray slice3 = matrix3d.slice(Indices.all(), Indices.newAxis(), Indices.all(), Indices.all());

    assertEquals(Shape.of(5, 1, 4, 5), slice3.shape());
    assertEquals(0, slice3.getInt(0, 0, 0, 0));
    assertEquals(1, slice3.getInt(0, 0, 0, 1));
    assertEquals(4, slice3.getInt(0, 0, 0, 4));
    assertEquals(2, slice3.getInt(0, 0, 1, 2));

    IntNdArray slice4 = matrix3d.slice(Indices.newAxis(), Indices.all(), Indices.all(), Indices.all());

    assertEquals(Shape.of(1, 5, 4, 5), slice4.shape());
    assertEquals(0, slice4.getInt(0, 0, 0, 0));
    assertEquals(1, slice4.getInt(0, 0, 0, 1));
    assertEquals(4, slice4.getInt(0, 0, 0, 4));
    assertEquals(2, slice4.getInt(0, 0, 1, 2));

  }

  @Test
  public void testEllipsis(){
    IntNdArray matrix3d = NdArrays.ofInts(Shape.of(5, 4, 5));

    matrix3d.scalars().forEachIndexed((coords, scalar) ->
        scalar.setInt((int)coords[2])
    );

    assertEquals(
        matrix3d.slice(Indices.all(), Indices.all(), Indices.at(0)),
        matrix3d.slice(Indices.ellipsis(), Indices.at(0))
    );

    assertEquals(
        matrix3d.slice(Indices.at(0), Indices.all(), Indices.all()),
        matrix3d.slice(Indices.at(0), Indices.ellipsis())
    );

    assertEquals(
        matrix3d.slice(Indices.at(0), Indices.all(), Indices.at(0)),
        matrix3d.slice(Indices.at(0), Indices.ellipsis(), Indices.at(0))
    );

    // newaxis interacts specially with ellipsis (since it doesn't consume a dimension), test this

    assertEquals(
        matrix3d.slice(Indices.all(), Indices.all(), Indices.newAxis(), Indices.at(0)),
        matrix3d.slice(Indices.ellipsis(), Indices.newAxis(), Indices.at(0))
    );

    assertEquals(
        matrix3d.slice(Indices.newAxis(), Indices.all(), Indices.all(), Indices.at(0)),
        matrix3d.slice(Indices.newAxis(), Indices.ellipsis(), Indices.at(0))
    );

    assertEquals(
        matrix3d.slice(Indices.all(), Indices.all(), Indices.at(0), Indices.newAxis()),
        matrix3d.slice(Indices.ellipsis(), Indices.at(0), Indices.newAxis())
    );
  }

  @Test
  public void testSlice(){
    IntNdArray matrix3d = NdArrays.ofInts(Shape.of(5, 4, 5));

    matrix3d.scalars().forEachIndexed((coords, scalar) ->
        scalar.setInt((int)coords[2])
    );

    IntNdArray slice1 = matrix3d.slice(Indices.all(), Indices.sliceTo(3), Indices.all());

    assertEquals(Shape.of(5, 3, 5), slice1.shape());
    assertEquals(0, slice1.getInt(0, 0, 0));
    assertEquals(1, slice1.getInt(0, 0, 1));
    assertEquals(2, slice1.getInt(0, 1, 2));

    IntNdArray slice2 = matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(1, 4));

    assertEquals(Shape.of(5, 4, 3), slice2.shape());
    assertEquals(1, slice2.getInt(0, 0, 0));
    assertEquals(3, slice2.getInt(0, 0, 2));
    assertEquals(2, slice2.getInt(0, 1, 1));

    assertEquals(slice2, matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(1, -1)));

    assertEquals(slice2, matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(-4, -1)));

    assertEquals(Shape.of(5, 4, 0), matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(1, 4, -2)).shape());

    IntNdArray slice3 = matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(4, 1, -2));

    assertEquals(Shape.of(5, 4, 2), slice3.shape());
    assertEquals(4, slice3.getInt(0, 0, 0));
    assertEquals(2, slice3.getInt(0, 1, 1));

    assertEquals(slice3, matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(-1, 1, -2)));

    assertEquals(slice3, matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(-1, -4, -2)));

    IntNdArray slice4 = matrix3d.slice(Indices.all(), Indices.all(), Indices.slice(null, null, -1));

    assertEquals(Shape.of(5, 4, 5), slice4.shape());
    assertEquals(4, slice4.getInt(0, 0, 0));
    assertEquals(3, slice4.getInt(0, 0, 1));
    assertEquals(2, slice4.getInt(0, 1, 2));
  }

  @Test
  public void testAt(){
    IntNdArray matrix3d = NdArrays.ofInts(Shape.of(5, 4, 5));

    matrix3d.scalars().forEachIndexed((coords, scalar) ->
        scalar.setInt((int)coords[2])
    );

    IntNdArray slice1 = matrix3d.slice(Indices.all(), Indices.all(), Indices.at(0));

    assertEquals(Shape.of(5, 4), slice1.shape());
    assertEquals(0, slice1.getInt(0, 0));

    IntNdArray slice2 = matrix3d.slice(Indices.all(), Indices.all(), Indices.at(3));

    assertEquals(Shape.of(5, 4), slice2.shape());
    assertEquals(3, slice2.getInt(0, 0));

    IntNdArray slice3 = matrix3d.slice(Indices.all(), Indices.all(), Indices.at(-3));

    assertEquals(Shape.of(5, 4), slice3.shape());
    assertEquals(2, slice3.getInt(0, 0));

    IntNdArray slice4 = matrix3d.slice(Indices.all(), Indices.all(), Indices.at(-3, true));

    assertEquals(Shape.of(5, 4, 1), slice4.shape());
    assertEquals(2, slice4.getInt(0, 0, 0));
  }

}
