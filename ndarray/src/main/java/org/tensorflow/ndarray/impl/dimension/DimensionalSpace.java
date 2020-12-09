/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */

package org.tensorflow.ndarray.impl.dimension;

import java.util.Arrays;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.ndarray.Shape;

public class DimensionalSpace {

  public static DimensionalSpace create(Shape shape) {
    Dimension[] dimensions = new Dimension[shape.numDimensions()];

    // Start from the last dimension, where all elements are continuous
    for (int i = dimensions.length - 1, elementSize = 1; i >= 0; --i) {
      dimensions[i] = new Axis(shape.size(i), elementSize);
      elementSize *= dimensions[i].numElements();
    }
    return new DimensionalSpace(dimensions, shape);
  }

  public RelativeDimensionalSpace mapTo(Index[] indices) {
    if (dimensions == null || indices.length > dimensions.length) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int dimIdx = 0;
    int indexIdx = 0;
    int newDimIdx = 0;
    int segmentationIdx = -1;
    long initialOffset = 0;

    int newAxes = 0;
    boolean seenEllipsis = false;
    for(Index idx : indices){
      if(idx.isNewAxis()){
        newAxes += 1;
      }
      if(idx.isEllipsis()){
        if(seenEllipsis){
          throw new IllegalArgumentException("Only one ellipsis allowed");
        } else {
          seenEllipsis = true;
        }
      }
    }
    int newLength = dimensions.length + newAxes;

    Dimension[] newDimensions = new Dimension[newLength];
    while (indexIdx < indices.length) {

      if (indices[dimIdx].isPoint()) {
        // When an index targets a single point in a given dimension, calculate the offset of this
        // point and cumulate the offset of any subsequent point as well
        long offset = 0;
        do {
          offset += indices[indexIdx].mapCoordinate(0, dimensions[dimIdx]);
          dimIdx++;
        } while (++indexIdx < indices.length && indices[indexIdx].isPoint());

        // If this is the first index, then the offset is the position of the whole dimension
        // space within the original one. If not, then we apply the offset to the last vectorial
        // dimension
        if (newDimIdx == 0) {
          initialOffset = offset;
        } else {
          long reducedSize = dimensions[dimIdx - 1].elementSize();
          newDimensions[newDimIdx - 1] = new ReducedDimension(newDimensions[newDimIdx - 1], offset, reducedSize);
          segmentationIdx = newDimIdx - 1;
        }

      } else if(indices[indexIdx].isNewAxis()) {
        long newSize;
        if(dimIdx == 0){
          // includes everything.  Should really include future reduction (at()) but that doesn't seem to cause issues
          //   elsewhere
          newSize = dimensions[0].numElements() * dimensions[0].elementSize();
        } else {
          newSize = dimensions[dimIdx - 1].elementSize();
        }

        newDimensions[newDimIdx] = new Axis(1, newSize);
        segmentationIdx = newDimIdx; // is this correct?
        ++newDimIdx;
        ++indexIdx;
      } else if(indices[indexIdx].isEllipsis()){
        int remainingDimensions = dimensions.length - dimIdx;
        int requiredDimensions = 0;
        for(int i = indexIdx + 1 ; i < indices.length ; i++){
          if(!indices[i].isNewAxis()){
            requiredDimensions++;
          }
        }
        // while the number of dimensions left < the number of indices that consume axes
        while(remainingDimensions > requiredDimensions){
          Dimension dim = dimensions[dimIdx++];
          if (dim.isSegmented()) {
            segmentationIdx = newDimIdx;
          }
          newDimensions[newDimIdx++] = dim;
          remainingDimensions--;
        }
        indexIdx++;
      } else {
        // Map any other index to the appropriate dimension of this space
        Dimension newDimension = indices[indexIdx].apply(dimensions[dimIdx++]);
        newDimensions[newDimIdx] = newDimension;
        if (newDimension.isSegmented()) {
          segmentationIdx = newDimIdx;
        }
        ++newDimIdx;
        ++indexIdx;
      }
    }

    // When the number of indices provided is smaller than the number of dimensions in this space,
    // we copy the remaining dimensions directly to the new space as well.
    for (; dimIdx < dimensions.length; ++dimIdx, ++newDimIdx) {
      Dimension dim = dimensions[dimIdx];
      newDimensions[newDimIdx] = dim;
      if (dim.isSegmented()) {
        segmentationIdx = newDimIdx;
      }
    }
    return new RelativeDimensionalSpace(Arrays.copyOf(newDimensions, newDimIdx), segmentationIdx, initialOffset);
  }

  public DimensionalSpace from(int dimensionStart) {
    if (dimensionStart > dimensions.length) {
      throw new IndexOutOfBoundsException();
    }
    Dimension[] newDimensions = Arrays.copyOfRange(dimensions, dimensionStart, dimensions.length);
    if (segmentationIdx > dimensionStart) {
      return new DimensionalSpace(newDimensions, segmentationIdx - dimensionStart);
    }
    return new DimensionalSpace(newDimensions);
  }

  public Shape shape() {
    if (shape == null) {
      shape = toShape(dimensions);
    }
    return shape;
  }

  public int numDimensions() {
    return dimensions.length;
  }

  public long numElements(int i) {
    return dimensions[i].numElements();
  }

  public long physicalSize() {
    return dimensions.length > 0 ? dimensions[0].physicalSize() : 1;  // dimensions.length == 0 for scalars
  }

  public Dimension get(int i) {
    return dimensions[i];
  }

  public boolean isSegmented() {
    return segmentationIdx >= 0;
  }

  public int segmentationIdx() {
    return segmentationIdx;
  }

  public long positionOf(long[] coords) {
    long position = 0L;
    for (int i = 0; i < coords.length; ++i) {
      position += dimensions[i].positionOf(coords[i]);
    }
    return position;
  }

  /** Succinct description of the shape meant for debugging. */
  @Override
  public String toString() {
    return Arrays.toString(dimensions);
  }

  DimensionalSpace(Dimension[] dimensions, int segmentationIdx) {
    this.dimensions = dimensions;
    this.segmentationIdx = segmentationIdx;
  }

  private DimensionalSpace(Dimension[] dimensions) {
    this(dimensions, -1);
  }

  private DimensionalSpace(Dimension[] dimensions, Shape shape) {
    this(dimensions);
    this.shape = shape;
  }

  private final Dimension[] dimensions;
  private final int segmentationIdx;
  private Shape shape;

  private static Shape toShape(Dimension[] dimensions) {
    long[] shapeDimSizes = new long[dimensions.length];
    int i = 0;
    for (Dimension dimension : dimensions) {
      shapeDimSizes[i++] = dimension.numElements();
    }
    return Shape.of(shapeDimSizes);
  }
}
