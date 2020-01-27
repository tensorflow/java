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

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteContextOptions;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_NewContextOptions;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTFE_ContextOptions extends Pointer {
    protected static class DeleteDeallocator extends
        TFE_ContextOptions implements Pointer.Deallocator {
        DeleteDeallocator(TFE_ContextOptions s) { super(s); }
        @Override public void deallocate() { if (!isNull()) TFE_DeleteContextOptions(this); setNull(); }
    }

    public AbstractTFE_ContextOptions(Pointer p) { super(p); }

    /**
     * Calls TFE_NewContextOptions(), and registers a deallocator.
     * @return TFE_ContextOptions created. Do not call TFE_DeleteContextOptions() on it.
     */
    public static TFE_ContextOptions newContextOptions() {
        TFE_ContextOptions o = TFE_NewContextOptions();
        if (o != null) {
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
