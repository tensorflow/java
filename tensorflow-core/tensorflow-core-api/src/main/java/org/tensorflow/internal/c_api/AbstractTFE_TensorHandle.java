/*
 Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteTensorHandle;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewTensorHandle;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTFE_TensorHandle extends Pointer {
    protected static class DeleteDeallocator extends TFE_TensorHandle implements Pointer.Deallocator {
        DeleteDeallocator(TFE_TensorHandle s) { super(s); }
        @Override public void deallocate() { if (!isNull()) TFE_DeleteTensorHandle(this); setNull(); }
    }

    /** A reference to prevent deallocation. */
    protected TF_Tensor tensor;

    public AbstractTFE_TensorHandle(Pointer p) { super(p); }

    /**
     * Calls TFE_NewTensorHandle(), and registers a deallocator.
     * @return TFE_TensorHandle created. Do not call TFE_DeleteTensorHandle() on it.
     */
    public static TFE_TensorHandle newTensor(TF_Tensor t, TF_Status status) {
        TFE_TensorHandle th = TFE_NewTensorHandle(t, status);
        if (th != null) {
            th.tensor = t;
            th.deallocator(new DeleteDeallocator(th));
        }
        return th;
    }

    /** Registers a deallocator and returns this. */
    public TFE_TensorHandle withDeallocator() {
        return (TFE_TensorHandle)this.deallocator(new DeleteDeallocator((TFE_TensorHandle)this));
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
