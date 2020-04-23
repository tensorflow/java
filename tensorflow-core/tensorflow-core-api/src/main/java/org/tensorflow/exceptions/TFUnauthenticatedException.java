package org.tensorflow.exceptions;

import org.tensorflow.TensorFlowException;

public final class TFUnauthenticatedException extends TensorFlowException {
  public TFUnauthenticatedException(String message) {
    super(message);
  }

  public TFUnauthenticatedException(String message, Throwable cause) {
    super(message, cause);
  }
}
