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
package org.tensorflow.framework.metrics.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.types.family.TNumber;

/**
 * A class that represents a Symbolic shape.
 *
 * <p>A Symbolic shape uses symbols to identify the relationship of the shape of an operand to
 * underlying values that are not know until compute time. For example, "N" represent the number of
 * examples, while "L" represents the number of labels. When the values later become known, the
 * shape of the operand must conform the these symbolic values.
 *
 * @param <T> The data type for the Operand.
 */
public class SymbolicShape<T extends TNumber> {
  private Operand<T> operand;
  private List<String> symbols = new ArrayList<>();

  /**
   * Creates a SymbolicShape
   *
   * @param operand the Operand that needs to conform to the shape
   * @param symbols the symbolic value for each dimension of the shape.
   */
  public SymbolicShape(Operand<T> operand, String... symbols) {
    this.operand = operand;
    this.symbols.addAll(Arrays.asList(symbols));
  }

  /**
   * Gets the operand
   *
   * @return the operand
   */
  public Operand<T> getOperand() {
    return operand;
  }

  /**
   * Sets the operand
   *
   * @param operand the operand to set
   */
  public void setOperand(Operand<T> operand) {
    this.operand = operand;
  }

  /**
   * Gets the symbols associated with each dimension of the shape
   *
   * @return the symbols associated with each dimension of the shape
   */
  public List<String> getSymbols() {
    return symbols;
  }

  /**
   * Sets teh symbols associated with each dimension of the shape
   *
   * @param symbols the symbols associated with each dimension of the shape
   */
  public void setSymbols(List<String> symbols) {
    this.symbols = symbols;
  }

  /**
   * Gets the rank associated with this Symbolic Shape
   *
   * @return the rank associated with this Symbolic Shape
   */
  public int rank() {
    return this.symbols.size();
  }
}
