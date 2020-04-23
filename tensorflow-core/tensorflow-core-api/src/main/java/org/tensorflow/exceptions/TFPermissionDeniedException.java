package org.tensorflow.exceptions;

import org.tensorflow.TensorFlowException;

public final class TFPermissionDeniedException extends TensorFlowException {
  public TFPermissionDeniedException(String message) {
    super(message);
  }

  public TFPermissionDeniedException(String message, Throwable cause) {
    super(message, cause);
  }
}
