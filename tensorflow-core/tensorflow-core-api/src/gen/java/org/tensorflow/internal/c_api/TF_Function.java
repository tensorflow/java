// Targeted by JavaCPP version 1.5.4: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;


// TF_Function is a grouping of operations with defined inputs and outputs.
// Once created and added to graphs, functions can be invoked by creating an
// operation whose operation type matches the function name.
@Opaque
@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_Function extends org.tensorflow.internal.c_api.AbstractTF_Function {

    /**
     * Empty constructor. Calls {@code super((Pointer)null)}.
     */
    public TF_Function() {
        super((Pointer) null);
    }

    /**
     * Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}.
     */
    public TF_Function(Pointer p) {
        super(p);
    }
}
