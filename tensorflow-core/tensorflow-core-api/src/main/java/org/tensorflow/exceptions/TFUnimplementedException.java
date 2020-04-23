package org.tensorflow.exceptions;

import org.tensorflow.TensorFlowException;

public final class TFUnimplementedException extends TensorFlowException {
  public TFUnimplementedException(String message) {
    super(message);
  }

  public TFUnimplementedException(String message, Throwable cause) {
    super(message, cause);
  }
}
