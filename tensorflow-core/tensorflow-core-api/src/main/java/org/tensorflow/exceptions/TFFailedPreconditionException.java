package org.tensorflow.exceptions;

public final class TFFailedPreconditionException extends RuntimeException {
  public TFFailedPreconditionException(String message) {
    super(message);
  }
  public TFFailedPreconditionException(String message, Throwable cause) {
    super(message, cause);
  }
}
