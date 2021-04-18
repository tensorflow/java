// Targeted by JavaCPP version 1.5.6-SNAPSHOT: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


/** Represents a tensor value produced by an Operation. */
@Name("tensorflow::Output") @NoOffset @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class NativeOutput extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public NativeOutput(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public NativeOutput(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public NativeOutput position(long position) {
        return (NativeOutput)super.position(position);
    }
    @Override public NativeOutput getPointer(long i) {
        return new NativeOutput((Pointer)this).position(position + i);
    }

  public NativeOutput() { super((Pointer)null); allocate(); }
  private native void allocate();
  public NativeOutput(Node n) { super((Pointer)null); allocate(n); }
  private native void allocate(Node n);
  public NativeOutput(Node n, @Cast("tensorflow::int32") int index) { super((Pointer)null); allocate(n, index); }
  private native void allocate(Node n, @Cast("tensorflow::int32") int index);
  public NativeOutput(@Const @ByRef NativeOperation op, @Cast("tensorflow::int32") int index) { super((Pointer)null); allocate(op, index); }
  private native void allocate(@Const @ByRef NativeOperation op, @Cast("tensorflow::int32") int index);

  public native @ByVal NativeOperation op();
  public native Node node();
  public native @Cast("tensorflow::int32") int index();
  
  public native @StdString BytePointer name();
  public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef NativeOutput other);

  
}
