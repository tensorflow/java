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

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that contains a dictionary of Symbols. The map is updated with symbols and their
 * dimension values. Used in creating matching shapes that depend on other shapes' dimensions.
 */
public class SymbolicShapeDict {

  /** Dictionary containing the sybmols and their associated dimensions sizes. */
  private final Map<String, Long> map = new HashMap<>();

  /**
   * Add a symbol with its associated dimensions size.
   *
   * @param symbol the symbol name
   * @param size the shape dimension to associate with the symbol
   */
  public void put(String symbol, Long size) {
    this.map.put(symbol, size);
  }

  /**
   * Get the dimention size for a symbol
   *
   * @param symbol the symbol name
   * @return the dimension size for the symbol or null if not found.
   */
  public Long get(String symbol) {
    return this.map.get(symbol);
  }

  /**
   * Determine if the symbol is contained in the dictionary.
   *
   * @param symbol the symbol name
   * @return true, if the dictionary contains the symbol.
   */
  public boolean contains(String symbol) {
    return map.containsKey(symbol);
  }
}
