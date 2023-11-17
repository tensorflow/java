package org.tensorflow.exceptions;

public final class TFResourceExhaustedException extends TensorFlowException {
  public TFResourceExhaustedException(String message) {
    super(message);
  }

  public TFResourceExhaustedException(String message, Throwable cause) {
    super(message, cause);
  }
}
