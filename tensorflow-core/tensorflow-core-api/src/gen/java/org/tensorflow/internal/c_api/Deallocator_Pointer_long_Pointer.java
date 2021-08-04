// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


// Return a new tensor that holds the bytes data[0,len-1].
//
// The data will be deallocated by a subsequent call to TF_DeleteTensor via:
//      (*deallocator)(data, len, deallocator_arg)
// Clients must provide a custom deallocator function so they can pass in
// memory managed by something like numpy.
//
// May return NULL (and invoke the deallocator) if the provided data buffer
// (data, len) is inconsistent with a tensor of the given TF_DataType
// and the shape specified by (dima, num_dims).
@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class Deallocator_Pointer_long_Pointer extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    Deallocator_Pointer_long_Pointer(Pointer p) { super(p); }
    protected Deallocator_Pointer_long_Pointer() { allocate(); }
    private native void allocate();
    public native void call(Pointer data, @Cast("size_t") long len, Pointer arg);
}
