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

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteContext;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewContext;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTFE_Context extends Pointer {
    protected static class DeleteDeallocator extends TFE_Context implements Pointer.Deallocator {
        DeleteDeallocator(TFE_Context s) { super(s); }
        @Override public void deallocate() { if(!isNull()) TFE_DeleteContext(this); setNull(); }
    }

    /** References to prevent deallocation. */
    protected TFE_ContextOptions opts;

    public AbstractTFE_Context(Pointer p) { super(p); }

    /**
     * Calls TFE_NewContext(), and registers a deallocator.
     * @return TFE_Context created. Do not call TFE_DeleteContext() on it.
     */
    public static TFE_Context newContext(TFE_ContextOptions opts, TF_Status status) {
        TFE_Context c = TFE_NewContext(opts, status);
        if (c != null) {
            c.opts = opts;
            c.deallocator(new DeleteDeallocator(c));
        }
        return c;
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
