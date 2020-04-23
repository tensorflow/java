package org.tensorflow.exceptions;

public final class TFUnauthenticatedException extends RuntimeException {
  public TFUnauthenticatedException(String message) {
    super(message);
  }

  public TFUnauthenticatedException(String message, Throwable cause) {
    super(message, cause);
  }
}
