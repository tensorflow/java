package org.tensorflow.exceptions;

public final class TFUnimplementedException extends RuntimeException {
  public TFUnimplementedException(String message) {
    super(message);
  }

  public TFUnimplementedException(String message, Throwable cause) {
    super(message, cause);
  }
}
