/*
Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/

package org.tensorflow.internal.c_api;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteStatus;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FAILED_PRECONDITION;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GetCode;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_INVALID_ARGUMENT;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_Message;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewStatus;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OK;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OUT_OF_RANGE;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_PERMISSION_DENIED;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_RESOURCE_EXHAUSTED;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_UNAUTHENTICATED;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_UNIMPLEMENTED;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.tensorflow.exceptions.TFFailedPreconditionException;
import org.tensorflow.exceptions.TFInvalidArgumentException;
import org.tensorflow.exceptions.TFOutOfRangeException;
import org.tensorflow.exceptions.TFPermissionDeniedException;
import org.tensorflow.exceptions.TFResourceExhaustedException;
import org.tensorflow.exceptions.TFUnauthenticatedException;
import org.tensorflow.exceptions.TFUnimplementedException;
import org.tensorflow.exceptions.TensorFlowException;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Status extends Pointer {
  protected static class DeleteDeallocator extends TF_Status implements Pointer.Deallocator {
    DeleteDeallocator(TF_Status s) {
      super(s);
    }

    @Override
    public void deallocate() {
      if (!isNull()) TF_DeleteStatus(this);
      setNull();
    }
  }

  public AbstractTF_Status(Pointer p) {
    super(p);
  }

  /**
   * Calls TF_NewStatus(), and registers a deallocator.
   *
   * @return TF_Status created. Do not call TF_DeleteStatus() on it.
   */
  public static TF_Status newStatus() {
    TF_Status s = TF_NewStatus();
    if (s != null) {
      s.deallocator(new DeleteDeallocator(s));
    }
    return s;
  }

  /** Calls the deallocator, if registered, otherwise has no effect. */
  public void delete() {
    deallocate();
  }

  /** Map TF_Code to unchecked exception, and throw if not TF_OK. */
  public void throwExceptionIfNotOK() {
    TF_Status s = (TF_Status) this;
    switch (TF_GetCode(s)) {
      case TF_OK:
        break;
      case TF_INVALID_ARGUMENT:
        throw new TFInvalidArgumentException(TF_Message(s).getString());
      case TF_UNAUTHENTICATED:
        throw new TFUnauthenticatedException(TF_Message(s).getString());
      case TF_PERMISSION_DENIED:
        throw new TFPermissionDeniedException(TF_Message(s).getString());
      case TF_RESOURCE_EXHAUSTED:
        throw new TFResourceExhaustedException(TF_Message(s).getString());
      case TF_FAILED_PRECONDITION:
        throw new TFFailedPreconditionException(TF_Message(s).getString());
      case TF_OUT_OF_RANGE:
        throw new TFOutOfRangeException(TF_Message(s).getString());
      case TF_UNIMPLEMENTED:
        throw new TFUnimplementedException(TF_Message(s).getString());
      default:
        throw new TensorFlowException(TF_Message(s).getString());
    }
  }
}
