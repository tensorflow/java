// Targeted by JavaCPP version 1.5.4: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_TString extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public TF_TString() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public TF_TString(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TF_TString(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public TF_TString position(long position) {
        return (TF_TString)super.position(position);
    }
    @Override public TF_TString getPointer(long i) {
        return new TF_TString(this).position(position + i);
    }
  // NOLINT
    // small conflicts with '#define small char' in RpcNdr.h for MSVC, so we use
    // smll instead.
    @Name("u.smll") public native @ByRef TF_TString_Small u_smll(); public native TF_TString u_smll(TF_TString_Small setter);
    @Name("u.large") public native @ByRef TF_TString_Large u_large(); public native TF_TString u_large(TF_TString_Large setter);
    @Name("u.offset") public native @ByRef TF_TString_Offset u_offset(); public native TF_TString u_offset(TF_TString_Offset setter);
    @Name("u.view") public native @ByRef TF_TString_View u_view(); public native TF_TString u_view(TF_TString_View setter);
    @Name("u.raw") public native @ByRef TF_TString_Raw u_raw(); public native TF_TString u_raw(TF_TString_Raw setter);
}
