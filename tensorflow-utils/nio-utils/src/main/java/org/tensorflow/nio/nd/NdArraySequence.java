package org.tensorflow.nio.nd;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Iterates through a sequence of elements of an N-dimensional array.
 *
 * @param <T> data type of the array being iterated
 */
public interface NdArraySequence<T extends NdArray<?>> {

  /**
   * Visit each elements of this iteration.
   *
   * @param consumer method to invoke for each elements
   */
  void forEach(Consumer<T> consumer);

  /**
   * Visit each elements of this iteration and their respective coordinates.
   *
   * <p><i>Important: the consumer method should not keep a reference to the coordinates
   * as they might are mutable and reuse during the iteration to improve performance.</i>
   *
   * @param consumer method to invoke for each elements
   */
  void forEachIndexed(BiConsumer<long[], T> consumer);
}
