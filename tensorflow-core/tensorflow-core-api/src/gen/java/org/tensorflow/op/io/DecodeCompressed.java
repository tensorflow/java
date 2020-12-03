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

package org.tensorflow.op.io;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Decompress strings.
 * <p>
 * This op decompresses each element of the `bytes` input `Tensor`, which
 * is assumed to be compressed using the given `compression_type`.
 * <p>
 * The `output` is a string `Tensor` of the same shape as `bytes`,
 * each element containing the decompressed data from the corresponding
 * element in `bytes`.
 */
@Operator(group = "io")
public final class DecodeCompressed extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.io.DecodeCompressed}
   */
  public static class Options {
    
    /**
     * @param compressionType A scalar containing either (i) the empty string (no
     * compression), (ii) "ZLIB", or (iii) "GZIP".
     */
    public Options compressionType(String compressionType) {
      this.compressionType = compressionType;
      return this;
    }
    
    private String compressionType;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DecodeCompressed operation.
   * 
   * @param scope current scope
   * @param bytes A Tensor of string which is compressed.
   * @param options carries optional attributes values
   * @return a new instance of DecodeCompressed
   */
  @Endpoint(describeByClass = true)
  public static DecodeCompressed create(Scope scope, Operand<TString> bytes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodeCompressed", scope.makeOpName("DecodeCompressed"));
    opBuilder.addInput(bytes.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.compressionType != null) {
          opBuilder.setAttr("compression_type", opts.compressionType);
        }
      }
    }
    return new DecodeCompressed(opBuilder.build());
  }
  
  /**
   * @param compressionType A scalar containing either (i) the empty string (no
   * compression), (ii) "ZLIB", or (iii) "GZIP".
   */
  public static Options compressionType(String compressionType) {
    return new Options().compressionType(compressionType);
  }
  
  /**
   * A Tensor with the same shape as input `bytes`, uncompressed
   * from bytes.
   */
  public Output<TString> output() {
    return output;
  }
  
  @Override
  public Output<TString> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DecodeCompressed";
  
  private Output<TString> output;
  
  private DecodeCompressed(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
