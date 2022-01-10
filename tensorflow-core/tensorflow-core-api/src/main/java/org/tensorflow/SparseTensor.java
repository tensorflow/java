package org.tensorflow;

import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * A virtual type of {@link Tensor} composed of three dense tensors (indices, values and dimensions) used to represent
 * the sparse data into a multi-dimensional dense space.
 *
 * Any tensor returned by a sparse tensor factory (e.g. {@link TInt64#sparseTensorOf(TInt64, TInt64, TInt64)}) can be casted back
 * to this interface to access directly the dense tensors it is composed of.
 *
 * @param <T> type of data stored in the tensor
 */
public interface SparseTensor<T extends TType> extends Tensor {

  /**
   * Creates a sparse tensor from {@code indices}, {@code values} and {@code denseShape} dense tensors.
   *
   * @param indices A 2-D tensor of shape {@code [N, ndims]}, that specifies the indices of the
   *     elements in the sparse tensor that contain non-default values (elements are zero-indexed).
   *     For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   *     {@code [1,3]} and {@code [2,4]} have non-default values.
   * @param values A 1-D tensor of shape {@code [N]}, which supplies the values for each
   *     element in indices. For example, given {@code indices=[[1,3], [2,4]]}, the parameter {@code
   *     values=[18, 3.8]} specifies that element {@code [1,3]} of the sparse tensor has a value of
   *     {@code 18}, and element {@code [2,4]} of the tensor has a value of {@code 3.8}.
   * @param denseShape A 1-D tensor of shape {@code [ndims]} where each the value at index {@code i}
   *     represents to total number of element in dimension {@code i} in a dense version of that tensor.
   * @return the new sparse tensor
   * @throws IllegalArgumentException if shapes of the dense tensors are not compatible
   */
  static <T extends TType> SparseTensor<T> of(TInt64 indices, T values, TInt64 denseShape) {
    if (indices.rank() != 2) {
      throw new IllegalArgumentException("Sparse indices must be a rank-2 tensor");
    }
    if (values.rank() != 1) {
      throw new IllegalArgumentException("Sparse values must be a rank-1 tensor");
    }
    if (denseShape.rank() != 1) {
      throw new IllegalArgumentException("Sparse shape must be a rank-1 tensor");
    }
    if (indices.shape().get(0) != values.shape().get(0)) {
      throw new IllegalArgumentException("Number of indices must be equal to the number of values ["
          + indices.shape().get(0) + " != " + values.shape().get(0) + "]");
    }
    if (indices.shape().get(1) != denseShape.shape().get(0)) {
      throw new IllegalArgumentException("Indices must have a coordinate for each dimensions of the tensor ["
          + indices.shape().get(1) + " != " + denseShape.shape().get(0) + "]");
    }
    // Use mapper of the values tensor as this is the one giving the type of the sparse tensor as well
    TensorMapper<T> mapper = (TensorMapper<T>) values.asRawTensor().typeInfo().mapper();
    return mapper.mapSparse(indices, values, denseShape);
  }

  @Override
  default RawTensor asRawTensor() {
    throw new UnsupportedOperationException("Sparse tensors cannot be converted to a single raw tensor");
  }

  /**
   * Returns this instance as a typed tensor.
   *
   * This method is equivalent to cast directly the {@code SparseTensor<T>} instance to {@code T}.
   *
   * @return the typed tensor
   */
  default T asTypedTensor() {
    return (T) this;
  }

  /**
   * Gets the indices of the sparsed values.
   *
   * <p>Indices are a 2-D long array of shape {@code [N, ndims]}, that specifies the indices of
   * the elements in the sparse tensor that contain nonzero values (elements are zero-indexed).
   *
   * <p>For example, {@code indices=[[1,3], [2,4]]} specifies that the elements with indexes of
   * coordinates {@code [1,3]} and {@code [2,4]} have nonzero values.
   *
   * @return the indices
   */
  TInt64 indices();

  /**
   * Gets the sparse values.
   *
   * <p>Values are a 1-D array of type {@code T} and shape {@code [N]}, that supplies the values for each
   * element in indices.
   *
   * <p>For example, given {@code indices=[[1,3], [2,4]]}, and {@code values=[18, 3.6]} specifies
   * that element {@code [1,3]} of the sparse tensor has a value of {@code 18}, and element {@code
   * [2,4]} of the sparse tensor has a value of {@code 3.6}.
   *
   * @return the values
   */
  T values();

  /**
   * Gets the sparse tensor dimensions defining the shape in that tensor in a dense space.
   *
   * <p>Dimensions A 1-D tensor of shape {@code [ndims]} where each the value at index {@code i}
   * represents to total number of element in dimension {@code i} in a dense version of that tensor.
   *
   * @return the dense shape
   */
  TInt64 denseShape();
}
