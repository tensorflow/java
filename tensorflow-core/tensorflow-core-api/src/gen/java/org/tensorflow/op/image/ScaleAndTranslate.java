/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.image;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 */
@Operator(group = "image")
public final class ScaleAndTranslate extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.ScaleAndTranslate}
   */
  public static class Options {
    
    /**
     * @param kernelType 
     */
    public Options kernelType(String kernelType) {
      this.kernelType = kernelType;
      return this;
    }
    
    /**
     * @param antialias 
     */
    public Options antialias(Boolean antialias) {
      this.antialias = antialias;
      return this;
    }
    
    private String kernelType;
    private Boolean antialias;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ScaleAndTranslate operation.
   * 
   * @param scope current scope
   * @param images 
   * @param size 
   * @param scale 
   * @param translation 
   * @param options carries optional attributes values
   * @return a new instance of ScaleAndTranslate
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> ScaleAndTranslate create(Scope scope, Operand<T> images, Operand<TInt32> size, Operand<TFloat32> scale, Operand<TFloat32> translation, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ScaleAndTranslate", scope.makeOpName("ScaleAndTranslate"));
    opBuilder.addInput(images.asOutput());
    opBuilder.addInput(size.asOutput());
    opBuilder.addInput(scale.asOutput());
    opBuilder.addInput(translation.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.kernelType != null) {
          opBuilder.setAttr("kernel_type", opts.kernelType);
        }
        if (opts.antialias != null) {
          opBuilder.setAttr("antialias", opts.antialias);
        }
      }
    }
    return new ScaleAndTranslate(opBuilder.build());
  }
  
  /**
   * @param kernelType 
   */
  public static Options kernelType(String kernelType) {
    return new Options().kernelType(kernelType);
  }
  
  /**
   * @param antialias 
   */
  public static Options antialias(Boolean antialias) {
    return new Options().antialias(antialias);
  }
  
  /**
   */
  public Output<TFloat32> resizedImages() {
    return resizedImages;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return resizedImages;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ScaleAndTranslate";
  
  private Output<TFloat32> resizedImages;
  
  private ScaleAndTranslate(Operation operation) {
    super(operation);
    int outputIdx = 0;
    resizedImages = operation.output(outputIdx++);
  }
}
