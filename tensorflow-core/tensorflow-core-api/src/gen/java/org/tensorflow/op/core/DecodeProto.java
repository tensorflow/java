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

import java.util.Arrays;
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
import org.tensorflow.types.family.TType;

/**
 * The op extracts fields from a serialized protocol buffers message into tensors.
 * <p>
 * The `decode_proto` op extracts fields from a serialized protocol buffers
 * message into tensors.  The fields in `field_names` are decoded and converted
 * to the corresponding `output_types` if possible.
 * <p>
 * A `message_type` name must be provided to give context for the field names.
 * The actual message descriptor can be looked up either in the linked-in
 * descriptor pool or a filename provided by the caller using the
 * `descriptor_source` attribute.
 * <p>
 * Each output tensor is a dense tensor. This means that it is padded to hold
 * the largest number of repeated elements seen in the input minibatch. (The
 * shape is also padded by one to prevent zero-sized dimensions). The actual
 * repeat counts for each example in the minibatch can be found in the `sizes`
 * output. In many cases the output of `decode_proto` is fed immediately into
 * tf.squeeze if missing values are not a concern. When using tf.squeeze, always
 * pass the squeeze dimension explicitly to avoid surprises.
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
 * Both binary and text proto serializations are supported, and can be
 * chosen using the `format` attribute.
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
public final class DecodeProto extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.DecodeProto}
   */
  public static class Options {
    
    /**
     * @param descriptorSource Either the special value `local://` or a path to a file containing
     * a serialized `FileDescriptorSet`.
     */
    public Options descriptorSource(String descriptorSource) {
      this.descriptorSource = descriptorSource;
      return this;
    }
    
    /**
     * @param messageFormat Either `binary` or `text`.
     */
    public Options messageFormat(String messageFormat) {
      this.messageFormat = messageFormat;
      return this;
    }
    
    /**
     * @param sanitize Whether to sanitize the result or not.
     */
    public Options sanitize(Boolean sanitize) {
      this.sanitize = sanitize;
      return this;
    }
    
    private String descriptorSource;
    private String messageFormat;
    private Boolean sanitize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new DecodeProto operation.
   * 
   * @param scope current scope
   * @param bytes Tensor of serialized protos with shape `batch_shape`.
   * @param messageType Name of the proto message type to decode.
   * @param fieldNames List of strings containing proto field names. An extension field can be decoded
   * by using its full name, e.g. EXT_PACKAGE.EXT_FIELD_NAME.
   * @param outputTypes List of TF types to use for the respective field in field_names.
   * @param options carries optional attributes values
   * @return a new instance of DecodeProto
   */
  @Endpoint(describeByClass = true)
  public static DecodeProto create(Scope scope, Operand<TString> bytes, String messageType, List<String> fieldNames, List<Class<? extends TType>> outputTypes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("DecodeProtoV2", scope.makeOpName("DecodeProto"));
    opBuilder.addInput(bytes.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("message_type", messageType);
    String[] fieldNamesArray = new String[fieldNames.size()];
    for (int i = 0; i < fieldNamesArray.length; ++i) {
      fieldNamesArray[i] = fieldNames.get(i);
    }
    opBuilder.setAttr("field_names", fieldNamesArray);
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    if (options != null) {
      for (Options opts : options) {
        if (opts.descriptorSource != null) {
          opBuilder.setAttr("descriptor_source", opts.descriptorSource);
        }
        if (opts.messageFormat != null) {
          opBuilder.setAttr("message_format", opts.messageFormat);
        }
        if (opts.sanitize != null) {
          opBuilder.setAttr("sanitize", opts.sanitize);
        }
      }
    }
    return new DecodeProto(opBuilder.build());
  }
  
  /**
   * @param descriptorSource Either the special value `local://` or a path to a file containing
   * a serialized `FileDescriptorSet`.
   */
  public static Options descriptorSource(String descriptorSource) {
    return new Options().descriptorSource(descriptorSource);
  }
  
  /**
   * @param messageFormat Either `binary` or `text`.
   */
  public static Options messageFormat(String messageFormat) {
    return new Options().messageFormat(messageFormat);
  }
  
  /**
   * @param sanitize Whether to sanitize the result or not.
   */
  public static Options sanitize(Boolean sanitize) {
    return new Options().sanitize(sanitize);
  }
  
  /**
   * Tensor of int32 with shape `[batch_shape, len(field_names)]`.
   * Each entry is the number of values found for the corresponding field.
   * Optional fields may have 0 or 1 values.
   */
  public Output<TInt32> sizes() {
    return sizes;
  }
  
  /**
   * List of tensors containing values for the corresponding field.
   * `values[i]` has datatype `output_types[i]`
   * and shape `[batch_shape, max(sizes[...,i])]`.
   */
  public List<Output<?>> values() {
    return values;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DecodeProtoV2";
  
  private Output<TInt32> sizes;
  private List<Output<?>> values;
  
  private DecodeProto(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sizes = operation.output(outputIdx++);
    int valuesLength = operation.outputListLength("values");
    values = Arrays.asList(operation.outputList(outputIdx, valuesLength));
    outputIdx += valuesLength;
  }
}
