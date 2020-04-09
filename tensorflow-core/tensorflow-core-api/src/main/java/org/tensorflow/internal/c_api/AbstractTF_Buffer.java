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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteBuffer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewBuffer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewBufferFromString;

import com.google.protobuf.Message;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Buffer extends Pointer {
    protected static class DeleteDeallocator extends TF_Buffer implements Pointer.Deallocator {
        DeleteDeallocator(TF_Buffer s) { super(s); }
        @Override public void deallocate() { if (!isNull()) TF_DeleteBuffer(this); setNull(); }
    }

    public AbstractTF_Buffer(Pointer p) { super(p); }

    /**
     * Calls TF_NewBuffer(), and registers a deallocator.
     * @return TF_Buffer created. Do not call TF_DeleteBuffer() on it.
     */
    public static TF_Buffer newBuffer() {
        TF_Buffer b = TF_NewBuffer();
        if (b != null) {
            b.deallocator(new DeleteDeallocator(b));
        }
        return b;
    }

    /** Returns {@code newBufferFromString(new BytePointer(proto.toByteArray())), or null if proto is null or empty. */
    public static TF_Buffer newBufferFromString(Message proto) {
        if (proto == null) {
            return null;
        }
        return newBufferFromString(new BytePointer(proto.toByteArray()));
    }

    /**
     * Calls TF_NewBufferFromString(), and registers a deallocator.
     * @return TF_Buffer created, or null if proto is null or empty. Do not call TF_DeleteBuffer() on it.
     */
    public static TF_Buffer newBufferFromString(Pointer proto) {
        if (proto == null || proto.isNull() || proto.limit() == 0) {
            return null;
        }
        TF_Buffer b = TF_NewBufferFromString(proto, proto.limit());
        if (b != null) {
            b.deallocator(new DeleteDeallocator(b));
        }
        return b;
    }

    /**
     * Returns a copy of the data in a Java array
     * @throws IndexOutOfBoundsException if too large.
     */
    public byte[] copyData() {
        long length = ((TF_Buffer)this).length();
        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("TF_Buffer is too large to serialize into a byte[] array");
        }
        byte[] data = new byte[(int)length];
        new BytePointer(((TF_Buffer)this).data()).get(data);
        return data;
    }

    /**
     * Returns the data of this buffer as a {@link java.nio.ByteBuffer}
     * @throws IndexOutOfBoundsException if too large.
     */
    public ByteBuffer dataAsByteBuffer() {
        long length = ((TF_Buffer)this).length();
        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("TF_Buffer is too large to accessed via a ByteBuffer interface");
        }
        return ((TF_Buffer)this).data().capacity(length).asByteBuffer();
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
