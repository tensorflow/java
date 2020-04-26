package org.tensorflow.exceptions;

public final class TFPermissionDeniedException extends TensorFlowException {
  public TFPermissionDeniedException(String message) {
    super(message);
  }

  public TFPermissionDeniedException(String message, Throwable cause) {
    super(message, cause);
  }
}
