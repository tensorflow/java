package org.tensorflow.internal.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;

/**
 * Tensor helper methods.
 */
public final class Tensors {

  /**
   * Prevent construction.
   */
  private Tensors() {
  }

  /**
   * @param tensor   a tensor
   * @param maxWidth the maximum width of the output ({@code null} if absent)
   * @return the String representation of the tensor
   */
  public static String toString(Tensor tensor, Integer maxWidth) {
    if (!(tensor instanceof NdArray)) {
      throw new AssertionError("Expected tensor to extend NdArray");
    }
    NdArray<?> ndArray = (NdArray<?>) tensor;
    Iterator<? extends NdArray<?>> iterator = ndArray.scalars().iterator();
    Shape shape = tensor.shape();
    if (shape.numDimensions() == 0) {
      if (!iterator.hasNext()) {
        return "";
      }
      return String.valueOf(iterator.next().getObject());
    }
    return toString(iterator, shape, 0, maxWidth);
  }

  /**
   * @param iterator  an iterator over the scalars
   * @param shape     the shape of the tensor
   * @param maxWidth  the maximum width of the output ({@code null} if absent)
   * @param dimension the current dimension being processed
   * @return the String representation of the tensor data at {@code dimension}
   */
  private static String toString(Iterator<? extends NdArray<?>> iterator, Shape shape,
      int dimension, Integer maxWidth) {
    if (dimension < shape.numDimensions() - 1) {
      StringJoiner joiner = new StringJoiner(",\n", indent(dimension) + "[\n",
          "\n" + indent(dimension) + "]");
      for (long i = 0, size = shape.size(dimension); i < size; ++i) {
        String element = toString(iterator, shape, dimension + 1, maxWidth);
        joiner.add(element);
      }
      return joiner.toString();
    }
    if (maxWidth == null) {
      StringJoiner joiner = new StringJoiner(", ", indent(dimension) + "[", "]");
      for (long i = 0, size = shape.size(dimension); i < size; ++i) {
        String element = iterator.next().getObject().toString();
        joiner.add(element);
      }
      return joiner.toString();
    }
    List<Integer> lengths = new ArrayList<>();
    StringJoiner joiner = new StringJoiner(", ", indent(dimension) + "[", "]");
    int lengthBefore = joiner.length() - "]".length();
    for (long i = 0, size = shape.size(dimension); i < size; ++i) {
      String element = iterator.next().getObject().toString();
      joiner.add(element);
      int addedLength = joiner.length() - lengthBefore;
      lengths.add(addedLength);
      lengthBefore += addedLength;
    }
    if (joiner.length() <= maxWidth) {
      return joiner.toString();
    }
    StringBuilder result = new StringBuilder(joiner.toString());
    int midPoint = (maxWidth / 2) - 1;
    int width = 0;
    int indexOfElementToRemove = lengths.size() - 1;
    int widthBeforeElementToRemove = 0;
    for (int i = 0, size = lengths.size(); i < size; ++i) {
      width += lengths.get(i);
      if (width > midPoint) {
        indexOfElementToRemove = i;
        break;
      }
      widthBeforeElementToRemove = width;
    }
    if (indexOfElementToRemove == 0) {
      // Cannot remove first element
      return joiner.toString();
    }
    result.insert(widthBeforeElementToRemove, ", ...");
    widthBeforeElementToRemove += ", ...".length();
    width = result.length();
    while (width > maxWidth) {
      if (indexOfElementToRemove == 0) {
        // Cannot remove first element
        break;
      } else if (indexOfElementToRemove == lengths.size() - 1) {
        // Cannot remove last element
        --indexOfElementToRemove;
        continue;
      }
      Integer length = lengths.remove(indexOfElementToRemove);
      result.delete(widthBeforeElementToRemove, widthBeforeElementToRemove + length);
      width = result.length();
    }
    if (result.length() < joiner.length()) {
      return result.toString();
    }
    // Do not insert ellipses if it increases the length
    return joiner.toString();
  }

  /**
   * @param level the level of indent
   * @return the indentation string
   */
  public static String indent(int level) {
    if (level <= 0) {
      return "";
    }
    StringBuilder result = new StringBuilder(level * 2);
    for (int i = 0; i < level; ++i) {
      result.append("  ");
    }
    return result.toString();
  }
}