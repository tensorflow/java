package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TType;

import java.util.List;

public class Node<T extends TType> {
  /** The Layer that takes input tensors and turns them into output tensors */
  private Layer<T> outboundLayer;

  /** The layers from which input tensors originate */
  private List<Layer<T>> inboundLayers;

  /**
   * A list of integers, the same length as `inboundLayers`. `nodeIndices[i]` is the origin of
   * inputTensors[i]
   */
  private List<Integer> nodeIndices;

  private Layer<T> layer;
  private List<Operand<T>> outputs;

  public Node(Layer<T> layer, List<Operand<T>> outputs) {
    this.layer = layer;
    this.outputs = outputs;
  }
}
