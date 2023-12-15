// Targeted by JavaCPP version 1.5.9: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


// --------------------------------------------------------------------------
// In-process TensorFlow server functionality, for use in distributed training.
// A Server instance encapsulates a set of devices and a Session target that
// can participate in distributed training. A server belongs to a cluster
// (specified by a ClusterSpec), and corresponds to a particular task in a
// named job. The server can communicate with any other server in the same
// cluster.

// In-process TensorFlow server.
@Opaque @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class TF_Server extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public TF_Server() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TF_Server(Pointer p) { super(p); }
}