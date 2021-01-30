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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_AllocateTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewTensor;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Tensor extends Pointer {

    protected static class DeleteDeallocator extends TF_Tensor implements Pointer.Deallocator {

        DeleteDeallocator(TF_Tensor s, boolean registerMemory) {
            super(s);
            // some tensors, like those from eager operations, are just views
            if (registerMemory) {
                // ideally this would be TF_TensorElementCount and sizeof would be the datatype size,
                // but datatype isn't stored anywhere and may be variably sized
                s.capacity = TF_TensorByteSize(s);
            } else {
                s.capacity = 0;
            }
        }

        DeleteDeallocator(TF_Tensor s) {
            this(s, true);
        }

        @Override
        public void deallocate() {
            if (!isNull()) {
                TF_DeleteTensor(this);
            }
            setNull();
        }
    }

    /**
     * TensorFlow crashes if we don't pass it a deallocator, so...
     */
    protected static Deallocator_Pointer_long_Pointer dummyDeallocator = new Deallocator_Pointer_long_Pointer() {
        @Override
        public void call(Pointer data, long len, Pointer arg) {
        }
    }.retainReference();

    /**
     * A reference to prevent deallocation.
     */
    protected Pointer pointer;

    public AbstractTF_Tensor(Pointer p) {
        super(p);
    }

    @Override
    public int sizeof() {
        return 1;
    }

    /**
     * Calls TF_NewTensor(), and registers a deallocator.
     *
     * @return TF_Tensor created. Do not call TF_DeleteTensor() on it.
     */
    public static TF_Tensor newTensor(int dtype, long[] dims, Pointer data) {
        TF_Tensor t = TF_NewTensor(dtype, dims, dims.length, data, data.limit(), dummyDeallocator, null);
        if (t != null) {
            t.pointer = data;
            t.deallocator(new DeleteDeallocator(t));
        }
        return t;
    }

    /**
     * Calls TF_AllocateTensor(), and registers a deallocator.
     * @return TF_Tensor created. Do not call TF_DeleteTensor() on it.
     */
    public static TF_Tensor allocateTensor(int dtype, long[] dims, long length) {
        TF_Tensor t = TF_AllocateTensor(dtype, dims, dims.length, length);
        if (t != null) {
            t.deallocator(new DeleteDeallocator(t));
        }
        return t;
    }

    /**
     * Registers a deallocator and returns this.
     */
    public TF_Tensor withDeallocator(boolean isView) {
        return (TF_Tensor) this.deallocator(new DeleteDeallocator((TF_Tensor) this, isView));
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
