package org.tensorflow.exceptions;

import org.tensorflow.TensorFlowException;

public final class TFInvalidArgumentException extends TensorFlowException {
  public TFInvalidArgumentException(String message) {
    super(message);
  }

  public TFInvalidArgumentException(String message, Throwable cause) {
    super(message, cause);
  }
}
