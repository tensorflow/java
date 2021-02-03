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

import org.tensorflow.Operand;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolicShape<T extends TNumber> {
  private Operand<T> operand;
  private List<String> symbols = new ArrayList<>();

  public SymbolicShape(Operand<T> operand, String... symbols) {
    this.operand = operand;
    this.symbols.addAll(Arrays.asList(symbols));
  }

  /** @return the operand */
  public Operand<T> getOperand() {
    return operand;
  }

  /** @param operand the operand to set */
  public void setOperand(Operand<T> operand) {
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

  public int rank() {
    return this.symbols.size();
  }
}
