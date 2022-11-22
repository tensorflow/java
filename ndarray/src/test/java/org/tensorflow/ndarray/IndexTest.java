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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
  public void testIndices(){

    // String[][] indexData = new String[5][4];
    String[][] indexData = new String[5][];
    for (int i=0 ; i < 5; i++){
      indexData[i] = new String[4];
      for (int j=0 ; j < 4; j++)
        indexData[i][j] = "("+j+", "+i+")";
    }

    NdArray<String> matrix2d = StdArrays.ndCopyOf(indexData);
    assertEquals(2, matrix2d.rank());

    /*
    |(0, 0), (1, 0), (2, 0), (3, 0)|
    |(0, 1), (1, 1), (2, 1), (3, 1)|
    |(0, 2), (1, 2), (2, 2), (3, 2)|
    |(0, 3), (1, 3), (2, 3), (3, 3)|
    |(0, 4), (1, 4), (2, 4), (3, 4)|  
    */
    assertArrayEquals(new String[]{"(0, 0)", "(1, 0)", "(2, 0)", "(3, 0)"}, indexData[0]);

    NdArray<String> same1 = matrix2d.slice(Indices.all());
    String[][] same1j = StdArrays.array2dCopyOf(same1, String.class);
    assertEquals(2, same1.rank());
    assertEquals(same1, matrix2d);

    NdArray<String> same2 = matrix2d.slice(Indices.ellipsis());
    String[][] same2j = StdArrays.array2dCopyOf(same2, String.class);
    assertEquals(2, same2.rank());
    assertEquals(matrix2d, same2);

    // All rows, column 1
    NdArray<String> same3 = matrix2d.slice(Indices.all(), Indices.at(1));
    assertEquals(1, same3.rank());
    String[] same3j = StdArrays.array1dCopyOf(same3, String.class);
    assertArrayEquals(new String[] { "(1, 0)", "(1, 1)", "(1, 2)", "(1, 3)", "(1, 4)" }, same3j);

    // row 2, all columns
    NdArray<String> same4 = matrix2d.slice(Indices.at(2), Indices.all());
    assertEquals(1, same4.rank());
    String[] same4j = StdArrays.array1dCopyOf(same4, String.class);
    assertArrayEquals(new String[] {"(0, 2)", "(1, 2)", "(2, 2)", "(3, 2)"}, same4j);
    assertEquals(NdArrays.vectorOfObjects("(0, 2)", "(1, 2)", "(2, 2)", "(3, 2)"), same4);

    // row 2, column 1
    NdArray<String> same5 = matrix2d.slice(Indices.at(2), Indices.at(1));
    assertEquals(0, same5.rank());
    assertTrue(same5.shape().isScalar());
    // Don't use an index
    String same5j = same5.getObject();
    assertEquals("(1, 2)", same5j);

    // rows 1 to 2, all columns
    NdArray<String> same6 = matrix2d.slice(Indices.slice(1,3));
    assertEquals(2, same6.rank());
    String[][] same6j = StdArrays.array2dCopyOf(same6, String.class);
    assertArrayEquals(
      new String[][]
      { 
        {"(0, 1)", "(1, 1)", "(2, 1)", "(3, 1)"},
        {"(0, 2)", "(1, 2)", "(2, 2)", "(3, 2)"}
      }, 
      same6j
    );

    // Exception in thread "main" java.nio.BufferOverflowException
    // all rows, columns 1 to 2
    NdArray<String> same7 = matrix2d.slice(Indices.all(), Indices.slice(1,3));
    assertEquals(2, same7.rank());
    assertEquals(Shape.of(5,2), same7.shape());
    assertEquals(10, same7.size());
    NdArray<String> r7_0 = same7.get(0);
    NdArray<String> r7_1 = same7.get(1);
    NdArray<String> r7_2 = same7.get(2);
    NdArray<String> r7_3 = same7.get(3);
    NdArray<String> r7_4 = same7.get(4);
    assertEquals(1, r7_0.rank());
    assertEquals(Shape.of(2), r7_0.shape());
    assertEquals(2, r7_0.size());
    // TODO: I get a (0,0) which is not what I expected
    System.out.println(r7_0.getObject());
    //assertEquals("(1,0)", r7_0.getObject());
    assertEquals( "(1, 0)", r7_0.getObject(0));
    assertEquals( "(2, 0)", r7_0.getObject(1));
    assertEquals( "(1, 1)", r7_1.getObject(0));
    assertEquals( "(2, 1)", r7_1.getObject(1));
    assertEquals( "(1, 2)", r7_2.getObject(0));
    assertEquals( "(2, 2)", r7_2.getObject(1));
    assertEquals( "(1, 3)", r7_3.getObject(0));
    assertEquals( "(2, 3)", r7_3.getObject(1));
    assertEquals( "(1, 4)", r7_4.getObject(0));
    assertEquals( "(2, 4)", r7_4.getObject(1));
    String[][] expectedr7 = new String[][]
    { 
      {"(1, 0)", "(2, 0)"},
      {"(1, 1)", "(2, 1)"},
      {"(1, 2)", "(2, 2)"},
      {"(1, 3)", "(2, 3)"},
      {"(1, 4)", "(2, 4)"}
    };
    String[][] lArray = new String[5][2];
    // String[][] lArray = new String[5][];
    // lArray[0] = new String[2];
    // lArray[1] = new String[2];
    // lArray[2] = new String[2];
    // lArray[3] = new String[2];
    // lArray[4] = new String[2];
    StdArrays.copyFrom(same7, lArray);
    assertArrayEquals( expectedr7, lArray);
    String[][] same7j = StdArrays.array2dCopyOf(same7, String.class);
    assertArrayEquals( expectedr7, same7j);

/*    
*/    
    
    assertEquals(0, 0);
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
