package org.tensorflow.data;

import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.op.data.MakeIterator;

import java.util.List;

public class OneShotIterator {
  private MakeIterator makeIterator;
  private List<Output<?>> components;

  public OneShotIterator(MakeIterator makeIterator, List<Output<?>> components) {
    this.makeIterator = makeIterator;
    this.components = components;
  }

  public Operation getMakeIteratorOp() {
    return makeIterator.op();
  }

  public List<Output<?>> getComponents() {
    return components;
  }
}
