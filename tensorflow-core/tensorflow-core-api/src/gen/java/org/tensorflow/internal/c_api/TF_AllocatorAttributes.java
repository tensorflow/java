// Targeted by JavaCPP version 1.5.6: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;

// #endif

// Allocator Attributes used for tensor allocation.
@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_AllocatorAttributes extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public TF_AllocatorAttributes() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public TF_AllocatorAttributes(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TF_AllocatorAttributes(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public TF_AllocatorAttributes position(long position) {
        return (TF_AllocatorAttributes)super.position(position);
    }
    @Override public TF_AllocatorAttributes getPointer(long i) {
        return new TF_AllocatorAttributes((Pointer)this).offsetAddress(i);
    }

  public native @Cast("size_t") long struct_size(); public native TF_AllocatorAttributes struct_size(long setter);
  // Set boolean to 1 for CPU allocation, else 0.
  public native @Cast("unsigned char") byte on_host(); public native TF_AllocatorAttributes on_host(byte setter);
}
