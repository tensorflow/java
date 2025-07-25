// Targeted by JavaCPP version 1.5.10: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


// TF_NewAttrBuilder() returns an object that you can set attributes on as
// though it were an op. This allows querying properties of that op for
// type-checking purposes like if the op will run on a particular device type.
@Opaque @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_AttrBuilder extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public TF_AttrBuilder() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TF_AttrBuilder(Pointer p) { super(p); }
}
