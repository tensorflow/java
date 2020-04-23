package org.tensorflow.exceptions;

public final class TFPermissionDeniedException extends RuntimeException {
  public TFPermissionDeniedException(String message) {
    super(message);
  }

  public TFPermissionDeniedException(String message, Throwable cause) {
    super(message, cause);
  }
}
