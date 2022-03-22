// Targeted by JavaCPP version 1.5.7: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


/** GradFunc is the signature for all gradient functions in GradOpRegistry.
 *  Implementations should add operations to compute the gradient outputs of
 *  'op' (returned in 'grad_outputs') using 'scope' and 'grad_inputs'. */
@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class GradFunc extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    GradFunc(Pointer p) { super(p); }
    protected GradFunc() { allocate(); }
    private native void allocate();
    public native @ByVal NativeStatus call(@Const @ByRef TF_Scope scope, @Const @ByRef NativeOperation op,
                           @Const @ByRef NativeOutputVector grad_inputs,
                           NativeOutputVector grad_outputs);
}
