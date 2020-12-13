package org.tensorflow;

import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.types.family.TType;

/**
 * Maps the native memory of a {@link RawTensor} to a n-dimensional typed data space
 * accessible from the JVM.
 *
 * <p>Usage of this class is reserved for internal purposes only.
 *
 * @param <T> tensor type mapped by this object
 * @see {@link TType}
 */
public abstract class TensorMapper<T extends TType> {

  /**
   * Maps the provided dense raw {@code tensor} as a tensor of type {@code T}.
   *
   * @param tensor the dense tensor to map, in its raw nature
   * @return an instance of {@code T}
   */
  protected abstract T mapDense(RawTensor tensor);

  /**
   * Helper for retrieving the native handle of a raw tensor
   *
   * @param tensor a raw tensor
   * @return the native handle of that tensor
   * @throws IllegalStateException if the tensor has been released
   */
  protected static TF_Tensor nativeHandle(RawTensor tensor) {
    return tensor.nativeHandle();
  }
}
