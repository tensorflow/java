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

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteOp;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewOp;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTFE_Op extends Pointer {
    protected static class DeleteDeallocator extends TFE_Op implements Pointer.Deallocator {
        DeleteDeallocator(TFE_Op s) { super(s); }
        @Override public void deallocate() { if (!isNull()) TFE_DeleteOp(this); setNull(); }
    }

    /** A reference to prevent deallocation. */
    protected TFE_Context context;

    public AbstractTFE_Op(Pointer p) { super(p); }

    /**
     * Calls TFE_NewOp(), and registers a deallocator.
     * @return TFE_Op created. Do not call TFE_DeleteOp() on it.
     */
    public static TFE_Op newOp(TFE_Context ctx, String op_or_function_name, TF_Status status) {
        TFE_Op o = TFE_NewOp(ctx, op_or_function_name, status);
        if (o != null) {
            o.context = ctx;
            o.deallocator(new DeleteDeallocator(o));
        }
        return o;
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
