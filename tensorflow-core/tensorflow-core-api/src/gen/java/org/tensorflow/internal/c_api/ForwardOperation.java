// Targeted by JavaCPP version 1.5.5: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


// Metadata from the forward operation that is made available to the
// gradient registerer to instantiate a GradientFunction.
@Namespace("tensorflow::gradients") @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class ForwardOperation extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public ForwardOperation() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ForwardOperation(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ForwardOperation(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public ForwardOperation position(long position) {
        return (ForwardOperation)super.position(position);
    }
    @Override public ForwardOperation getPointer(long i) {
        return new ForwardOperation((Pointer)this).position(position + i);
    }

  @MemberGetter public native @StdString BytePointer op_name();
  @MemberGetter public native @Cast("tensorflow::AbstractTensorHandle**") @StdVector PointerPointer inputs();
  @MemberGetter public native @Cast("tensorflow::AbstractTensorHandle**") @StdVector PointerPointer outputs();
  @MemberGetter public native @Cast("tensorflow::int64*") @StdVector LongPointer skip_input_indices();
  @MemberGetter public native @ByRef @Cast("tensorflow::AttrBuilder*") Pointer attrs();
}