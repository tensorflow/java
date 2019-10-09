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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteImportGraphDefOptions;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewImportGraphDefOptions;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_ImportGraphDefOptions extends Pointer {
    protected static class DeleteDeallocator extends
        TF_ImportGraphDefOptions implements Pointer.Deallocator {
        DeleteDeallocator(TF_ImportGraphDefOptions s) { super(s); }
        @Override public void deallocate() { if (!isNull()) TF_DeleteImportGraphDefOptions(this); setNull(); }
    }

    public AbstractTF_ImportGraphDefOptions(Pointer p) { super(p); }

    /**
     * Calls TF_NewImportGraphDefOptions(), and registers a deallocator.
     * @return TF_ImportGraphDefOptions created. Do not call TF_DeleteImportGraphDefOptions() on it.
     */
    public static TF_ImportGraphDefOptions newImportGraphDefOptions() {
        TF_ImportGraphDefOptions o = TF_NewImportGraphDefOptions();
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
