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
package org.tensorflow.ndarray.index;

import org.tensorflow.ndarray.impl.dimension.Dimension;

final class NewAxis implements TensorIndex {

  static final NewAxis INSTANCE = new NewAxis();

  private NewAxis(){

  }

  @Override
  public long numElements(Dimension dim) {
    return 1;
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dim) {
    return coordinate;
  }

  @Override
  public Dimension apply(Dimension dim) {
    throw new IllegalStateException();
  }

  @Override
  public boolean isNewAxis() {
    return true;
  }

  @Override
  public boolean newAxisMask() {
    return true;
  }
}
