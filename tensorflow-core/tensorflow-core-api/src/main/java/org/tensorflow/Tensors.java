package org.tensorflow;

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
final class Tensors {

  /**
   * Prevent construction.
   */
  private Tensors() {
  }

  /**
   * Equivalent to {@link #toString(Tensor, Integer) toString(tensor, null)}.
   *
   * @param tensor a tensor
   * @return the String representation of the tensor
   */
  public static String toString(Tensor tensor) {
    return toString(tensor, null);
  }

  /**
   * Returns a String representation of a tensor's data. If the output is wider than {@code
   * maxWidth} characters, it is truncated and "{@code ...}" is inserted in place of the removed
   * data.
   *
   * @param tensor   a tensor
   * @param maxWidth the maximum width of the output in characters ({@code null} if unlimited). This
   *                 limit may surpassed if the first or last element are too long.
   * @return the String representation of the tensor
   */
  public static String toString(Tensor tensor, Integer maxWidth) {
    if (tensor instanceof RawTensor) {
      System.out.println("Got rawTensor: " + tensor);
      tensor = ((RawTensor) tensor).asTypedTensor();
    }
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
   * @param maxWidth  the maximum width of the output in characters ({@code null} if unlimited).
   *                  This limit may surpassed if the first or last element are too long.
   * @param dimension the current dimension being processed
   * @return the String representation of the tensor data at {@code dimension}
   */
  private static String toString(Iterator<? extends NdArray<?>> iterator, Shape shape,
      int dimension, Integer maxWidth) {
    if (dimension < shape.numDimensions() - 1) {
      StringJoiner joiner = new StringJoiner("\n", indent(dimension) + "[\n",
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
    int lengthBefore = "]".length();
    for (long i = 0, size = shape.size(dimension); i < size; ++i) {
      String element = iterator.next().getObject().toString();
      joiner.add(element);
      int addedLength = joiner.length() - lengthBefore;
      lengths.add(addedLength);
      lengthBefore += addedLength;
    }
    return truncateWidth(joiner.toString(), maxWidth, lengths);
  }

  /**
   * Truncates the width of a String if it's too long, inserting "{@code ...}" in place of the
   * removed data.
   *
   * @param input    the input to truncate
   * @param maxWidth the maximum width of the output in characters
   * @param lengths  the lengths of elements inside input
   * @return the (potentially) truncated output
   */
  private static String truncateWidth(String input, int maxWidth, List<Integer> lengths) {
    if (input.length() <= maxWidth) {
      return input;
    }
    StringBuilder output = new StringBuilder(input);
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
      return input;
    }
    output.insert(widthBeforeElementToRemove, ", ...");
    widthBeforeElementToRemove += ", ...".length();
    width = output.length();
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
      output.delete(widthBeforeElementToRemove, widthBeforeElementToRemove + length);
      width = output.length();
    }
    if (output.length() < input.length()) {
      return output.toString();
    }
    // Do not insert ellipses if it increases the length
    return input;
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