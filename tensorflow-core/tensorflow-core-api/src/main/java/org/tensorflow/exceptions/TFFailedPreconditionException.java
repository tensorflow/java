package org.tensorflow.exceptions;

import org.tensorflow.TensorFlowException;

public final class TFFailedPreconditionException extends TensorFlowException {

  public TFFailedPreconditionException(String message) {
    super(message);
  }

  public TFFailedPreconditionException(String message, Throwable cause) {
    super(message, cause);
  }
}
