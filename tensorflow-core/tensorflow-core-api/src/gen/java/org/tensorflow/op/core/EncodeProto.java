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

package org.tensorflow.op.core;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * The op serializes protobuf messages provided in the input tensors.
 * <p>
 * The types of the tensors in `values` must match the schema for the fields
 * specified in `field_names`. All the tensors in `values` must have a common
 * shape prefix, <i>batch_shape</i>.
 * <p>
 * The `sizes` tensor specifies repeat counts for each field.  The repeat count
 * (last dimension) of a each tensor in `values` must be greater than or equal
 * to corresponding repeat count in `sizes`.
 * <p>
 * A `message_type` name must be provided to give context for the field names.
 * The actual message descriptor can be looked up either in the linked-in
 * descriptor pool or a filename provided by the caller using the
 * `descriptor_source` attribute.
 * <p>
 * For the most part, the mapping between Proto field types and TensorFlow dtypes
 * is straightforward. However, there are a few special cases:
 * <p>
 * - A proto field that contains a submessage or group can only be converted
 * to `DT_STRING` (the serialized submessage). This is to reduce the complexity
 * of the API. The resulting string can be used as input to another instance of
 * the decode_proto op.
 * <p>
 * - TensorFlow lacks support for unsigned integers. The ops represent uint64
 * types as a `DT_INT64` with the same twos-complement bit pattern (the obvious
 * way). Unsigned int32 values can be represented exactly by specifying type
 * `DT_INT64`, or using twos-complement if the caller specifies `DT_INT32` in
 * the `output_types` attribute.
 * <p>
 * The `descriptor_source` attribute selects the source of protocol
 * descriptors to consult when looking up `message_type`. This may be:
 * <p>
 * - An empty string  or "local://", in which case protocol descriptors are
 * created for C++ (not Python) proto definitions linked to the binary.
 * <p>
 * - A file, in which case protocol descriptors are created from the file,
 * which is expected to contain a `FileDescriptorSet` serialized as a string.
 * NOTE: You can build a `descriptor_source` file using the `--descriptor_set_out`
 * and `--include_imports` options to the protocol compiler `protoc`.
 * <p>
 * - A "bytes://<bytes>", in which protocol descriptors are created from `<bytes>`,
 * which is expected to be a `FileDescriptorSet` serialized as a string.
 */
public final class EncodeProto extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.EncodeProto}
   */
  public static class Options {
    
    /**
     * @param descriptorSource 
     */
    public Options descriptorSource(String descriptorSource) {
      this.descriptorSource = descriptorSource;
      return this;
    }
    
    private String descriptorSource;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new EncodeProto operation.
   * 
   * @param scope current scope
   * @param sizes Tensor of int32 with shape `[batch_shape, len(field_names)]`.
   * @param values List of tensors containing values for the corresponding field.
   * @param fieldNames List of strings containing proto field names.
   * @param messageType Name of the proto message type to decode.
   * @param options carries optional attributes values
   * @return a new instance of EncodeProto
   */
  @Endpoint(describeByClass = true)
  public static EncodeProto create(Scope scope, Operand<TInt32> sizes, Iterable<Operand<?>> values, List<String> fieldNames, String messageType, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("EncodeProto", scope.makeOpName("EncodeProto"));
    opBuilder.addInput(sizes.asOutput());
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder = scope.apply(opBuilder);
    String[] fieldNamesArray = new String[fieldNames.size()];
    for (int i = 0; i < fieldNamesArray.length; ++i) {
      fieldNamesArray[i] = fieldNames.get(i);
    }
    opBuilder.setAttr("field_names", fieldNamesArray);
    opBuilder.setAttr("message_type", messageType);
    if (options != null) {
      for (Options opts : options) {
        if (opts.descriptorSource != null) {
          opBuilder.setAttr("descriptor_source", opts.descriptorSource);
        }
      }
    }
    return new EncodeProto(opBuilder.build());
  }
  
  /**
   * @param descriptorSource 
   */
  public static Options descriptorSource(String descriptorSource) {
    return new Options().descriptorSource(descriptorSource);
  }
  
  /**
   * Tensor of serialized protos with shape `batch_shape`.
   */
  public Output<TString> bytes() {
    return bytes;
  }
  
  @Override
  public Output<TString> asOutput() {
    return bytes;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "EncodeProto";
  
  private Output<TString> bytes;
  
  private EncodeProto(Operation operation) {
    super(operation);
    int outputIdx = 0;
    bytes = operation.output(outputIdx++);
  }
}
