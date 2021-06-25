// Targeted by JavaCPP version 1.5.5: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;


// Abstract interface to a Tensor handle in either tracing or immediate
// execution mode.
@Namespace("tensorflow") @NoOffset @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class AbstractTensorHandle extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public AbstractTensorHandle(Pointer p) { super(p); }

  // Returns tensor dtype.
  public native @Cast("tensorflow::DataType") int DataType();
  // Returns tensor shape. If tensor has unknown rank, shape remains untouched.
  public native @ByVal Status Shape(
        @Cast("tensorflow::PartialTensorShape*") Pointer shape);

  // The default debug string includes a shape and dtype. Implementations are
  // free to override it with something more informative.
  public native @StdString BytePointer DebugString();

  public native int getKind();
}