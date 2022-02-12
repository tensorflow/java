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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * The op extracts fields from a serialized protocol buffers message into tensors.
 * Note: This API is designed for orthogonality rather than human-friendliness. It
 * can be used to parse input protos by hand, but it is intended for use in
 * generated code.
 * <p>The {@code decode_proto} op extracts fields from a serialized protocol buffers
 * message into tensors.  The fields in {@code field_names} are decoded and converted
 * to the corresponding {@code output_types} if possible.
 * <p>A {@code message_type} name must be provided to give context for the field names.
 * The actual message descriptor can be looked up either in the linked-in
 * descriptor pool or a filename provided by the caller using the
 * {@code descriptor_source} attribute.
 * <p>Each output tensor is a dense tensor. This means that it is padded to hold
 * the largest number of repeated elements seen in the input minibatch. (The
 * shape is also padded by one to prevent zero-sized dimensions). The actual
 * repeat counts for each example in the minibatch can be found in the {@code sizes}
 * output. In many cases the output of {@code decode_proto} is fed immediately into
 * tf.squeeze if missing values are not a concern. When using tf.squeeze, always
 * pass the squeeze dimension explicitly to avoid surprises.
 * <p>For the most part, the mapping between Proto field types and TensorFlow dtypes
 * is straightforward. However, there are a few special cases:
 * <ul>
 * <li>
 * <p>A proto field that contains a submessage or group can only be converted
 * to {@code DT_STRING} (the serialized submessage). This is to reduce the complexity
 * of the API. The resulting string can be used as input to another instance of
 * the decode_proto op.
 * </li>
 * <li>
 * <p>TensorFlow lacks support for unsigned integers. The ops represent uint64
 * types as a {@code DT_INT64} with the same twos-complement bit pattern (the obvious
 * way). Unsigned int32 values can be represented exactly by specifying type
 * {@code DT_INT64}, or using twos-complement if the caller specifies {@code DT_INT32} in
 * the {@code output_types} attribute.
 * </li>
 * <li>
 * <p>{@code map} fields are not directly decoded. They are treated as {@code repeated} fields,
 * of the appropriate entry type. The proto-compiler defines entry types for each
 * map field. The type-name is the field name, converted to &quot;CamelCase&quot; with
 * &quot;Entry&quot; appended. The {@code tf.train.Features.FeatureEntry} message is an example of
 * one of these implicit {@code Entry} types.
 * </li>
 * <li>
 * <p>{@code enum} fields should be read as int32.
 * </li>
 * </ul>
 * <p>Both binary and text proto serializations are supported, and can be
 * chosen using the {@code format} attribute.
 * <p>The {@code descriptor_source} attribute selects the source of protocol
 * descriptors to consult when looking up {@code message_type}. This may be:
 * <ul>
 * <li>
 * <p>An empty string  or &quot;local://&quot;, in which case protocol descriptors are
 * created for C++ (not Python) proto definitions linked to the binary.
 * </li>
 * <li>
 * <p>A file, in which case protocol descriptors are created from the file,
 * which is expected to contain a {@code FileDescriptorSet} serialized as a string.
 * NOTE: You can build a {@code descriptor_source} file using the {@code --descriptor_set_out}
 * and {@code --include_imports} options to the protocol compiler {@code protoc}.
 * </li>
 * <li>
 * <p>A &quot;bytes://&lt;bytes&gt;&quot;, in which protocol descriptors are created from {@code <bytes>},
 * which is expected to be a {@code FileDescriptorSet} serialized as a string.
 * </li>
 * </ul>
 */
@OpMetadata(
    opType = DecodeProto.OP_NAME,
    inputsClass = DecodeProto.Inputs.class
)
@Operator
public final class DecodeProto extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeProtoV2";

  private Output<TInt32> sizes;

  private List<Output<?>> values;

  @SuppressWarnings("unchecked")
  public DecodeProto(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sizes = operation.output(outputIdx++);
    int valuesLength = operation.outputListLength("values");
    values = Arrays.asList(operation.outputList(outputIdx, valuesLength));
    outputIdx += valuesLength;
  }

  /**
   * Factory method to create a class wrapping a new DecodeProtoV2 operation.
   *
   * @param scope current scope
   * @param bytes Tensor of serialized protos with shape {@code batch_shape}.
   * @param messageType Name of the proto message type to decode.
   * @param fieldNames List of strings containing proto field names. An extension field can be decoded
   * by using its full name, e.g. EXT_PACKAGE.EXT_FIELD_NAME.
   * @param outputTypes List of TF types to use for the respective field in field_names.
   * @param options carries optional attribute values
   * @return a new instance of DecodeProto
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeProto create(Scope scope, Operand<TString> bytes, String messageType,
      List<String> fieldNames, List<Class<? extends TType>> outputTypes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeProto");
    opBuilder.addInput(bytes.asOutput());
    opBuilder.setAttr("message_type", messageType);
    String[] fieldNamesArray = new String[fieldNames.size()];
    for (int i = 0 ; i < fieldNamesArray.length ; i++) {
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
   * Sets the descriptorSource option.
   *
   * @param descriptorSource Either the special value {@code local://} or a path to a file containing
   * a serialized {@code FileDescriptorSet}.
   * @return this Options instance.
   */
  public static Options descriptorSource(String descriptorSource) {
    return new Options().descriptorSource(descriptorSource);
  }

  /**
   * Sets the messageFormat option.
   *
   * @param messageFormat Either {@code binary} or {@code text}.
   * @return this Options instance.
   */
  public static Options messageFormat(String messageFormat) {
    return new Options().messageFormat(messageFormat);
  }

  /**
   * Sets the sanitize option.
   *
   * @param sanitize Whether to sanitize the result or not.
   * @return this Options instance.
   */
  public static Options sanitize(Boolean sanitize) {
    return new Options().sanitize(sanitize);
  }

  /**
   * Gets sizes.
   * Tensor of int32 with shape {@code [batch_shape, len(field_names)]}.
   * Each entry is the number of values found for the corresponding field.
   * Optional fields may have 0 or 1 values.
   * @return sizes.
   */
  public Output<TInt32> sizes() {
    return sizes;
  }

  /**
   * Gets values.
   * List of tensors containing values for the corresponding field.
   * {@code values[i]} has datatype {@code output_types[i]}
   * and shape {@code [batch_shape, max(sizes[...,i])]}.
   * @return values.
   */
  public List<Output<?>> values() {
    return values;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.DecodeProto}
   */
  public static class Options {
    private String descriptorSource;

    private String messageFormat;

    private Boolean sanitize;

    private Options() {
    }

    /**
     * Sets the descriptorSource option.
     *
     * @param descriptorSource Either the special value {@code local://} or a path to a file containing
     * a serialized {@code FileDescriptorSet}.
     * @return this Options instance.
     */
    public Options descriptorSource(String descriptorSource) {
      this.descriptorSource = descriptorSource;
      return this;
    }

    /**
     * Sets the messageFormat option.
     *
     * @param messageFormat Either {@code binary} or {@code text}.
     * @return this Options instance.
     */
    public Options messageFormat(String messageFormat) {
      this.messageFormat = messageFormat;
      return this;
    }

    /**
     * Sets the sanitize option.
     *
     * @param sanitize Whether to sanitize the result or not.
     * @return this Options instance.
     */
    public Options sanitize(Boolean sanitize) {
      this.sanitize = sanitize;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeProto.class
  )
  public static class Inputs extends RawOpInputs<DecodeProto> {
    /**
     * Tensor of serialized protos with shape {@code batch_shape}.
     */
    public final Operand<TString> bytes;

    /**
     * Name of the proto message type to decode.
     */
    public final String messageType;

    /**
     * List of strings containing proto field names. An extension field can be decoded
     * by using its full name, e.g. EXT_PACKAGE.EXT_FIELD_NAME.
     */
    public final String[] fieldNames;

    /**
     * List of TF types to use for the respective field in field_names.
     */
    public final DataType[] outputTypes;

    /**
     * Either the special value `local://` or a path to a file containing
     * a serialized `FileDescriptorSet`.
     */
    public final String descriptorSource;

    /**
     * Either `binary` or `text`.
     */
    public final String messageFormat;

    /**
     * Whether to sanitize the result or not.
     */
    public final boolean sanitize;

    public Inputs(GraphOperation op) {
      super(new DecodeProto(op), op, Arrays.asList("message_type", "field_names", "output_types", "descriptor_source", "message_format", "sanitize"));
      int inputIndex = 0;
      bytes = (Operand<TString>) op.input(inputIndex++);
      messageType = op.attributes().getAttrString("message_type");
      fieldNames = op.attributes().getAttrStringList("field_names");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      descriptorSource = op.attributes().getAttrString("descriptor_source");
      messageFormat = op.attributes().getAttrString("message_format");
      sanitize = op.attributes().getAttrBool("sanitize");
    }
  }
}
