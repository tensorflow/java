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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;

/**
 * Utility class that handles sybmolic shapes so that the shapes can be resolved during runtime and
 * stay consistent across operands.
 *
 * <p>The same symbols accros variaous shapes should have the same dimension values.
 *
 * <p><code></code>shape1 = Shape("N","L")</code>
 *
 * <p><code>shape2 = Shape("L", "T")</code>
 *
 * <p>For exmple, for Symbol "L" <code>
 * shape1.size(2) == shape2.size(1)</code>
 *
 * <p>Example:
 *
 * <pre>
 * List<SymbolicShape> symbols = new ArrayList<>(); symbols.add(new SymbolicShape(yTrue, "N", "L"));
 * if (this.isMultiLabel()) { symbols.add(new SymbolicShape(this.truePositives, "T", "L"));
 * symbols.add(new SymbolicShape(this.falsePositives, "T", "L")); symbols.add(new
 * SymbolicShape(this.trueNegatives, "T", "L")); symbols.add(new SymbolicShape(this.falseNegatives,
 * "T", "L")); } if (this.getLabelWeights() != null) { symbols.add(new
 * SymbolicShape(this.getLabelWeights(), "L", "")); }
 * updateOperations.addAll(Metrics.assert_shapes(tf, symbols, "Number of labels is not
 * consistent."));
 * </pre>
 */
public class SymbolicShape {

  private Operand operand;
  private List<String> symbols = new ArrayList<>();

  /**
   * Create a SymbolicShape for an Operand.
   *
   * @param operand the Operand
   * @param symbols the symbols
   */
  public SymbolicShape(Operand operand, String... symbols) {
    this.operand = operand;
    this.symbols.addAll(Arrays.asList(symbols));
  }

  /** @return the operand */
  public Operand getOperand() {
    return operand;
  }

  /** @param operand the operand to set */
  public void setOperand(Operand operand) {
    this.operand = operand;
  }

  /** @return the symbols */
  public List<String> getSymbols() {
    return symbols;
  }

  /** @param symbols the symbols to set */
  public void setSymbols(List<String> symbols) {
    this.symbols = symbols;
  }

  /**
   * Get the rank of the symbol list
   *
   * @return the rank of the symbol list
   */
  public int rank() {
    return this.symbols.size();
  }
}
