package org.tensorflow.exceptions;

public final class TFOutOfRangeException extends TensorFlowException {
  public TFOutOfRangeException(String message) {
    super(message);
  }

  public TFOutOfRangeException(String message, Throwable cause) {
    super(message, cause);
  }
}
