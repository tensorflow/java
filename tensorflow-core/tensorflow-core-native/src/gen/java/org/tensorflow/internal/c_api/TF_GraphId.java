// Targeted by JavaCPP version 1.5.9: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;

// #endif

/** Unique identifier of a TensorFlow graph instance */
@Namespace @Name("void") @Opaque @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_GraphId extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public TF_GraphId() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TF_GraphId(Pointer p) { super(p); }
}
