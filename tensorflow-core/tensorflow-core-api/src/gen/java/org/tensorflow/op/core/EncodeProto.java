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

/**
 * The op serializes protobuf messages provided in the input tensors.
 * The types of the tensors in {@code values} must match the schema for the fields
 * specified in {@code field_names}. All the tensors in {@code values} must have a common
 * shape prefix, <em>batch_shape</em>.
 * <p>The {@code sizes} tensor specifies repeat counts for each field.  The repeat count
 * (last dimension) of a each tensor in {@code values} must be greater than or equal
 * to corresponding repeat count in {@code sizes}.
 * <p>A {@code message_type} name must be provided to give context for the field names.
 * The actual message descriptor can be looked up either in the linked-in
 * descriptor pool or a filename provided by the caller using the
 * {@code descriptor_source} attribute.
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
 * </ul>
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
    opType = EncodeProto.OP_NAME,
    inputsClass = EncodeProto.Inputs.class
)
@Operator
public final class EncodeProto extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodeProto";

  private Output<TString> bytes;

  public EncodeProto(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    bytes = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodeProto operation.
   *
   * @param scope current scope
   * @param sizes Tensor of int32 with shape {@code [batch_shape, len(field_names)]}.
   * @param values List of tensors containing values for the corresponding field.
   * @param fieldNames List of strings containing proto field names.
   * @param messageType Name of the proto message type to decode.
   * @param options carries optional attribute values
   * @return a new instance of EncodeProto
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodeProto create(Scope scope, Operand<TInt32> sizes, Iterable<Operand<?>> values,
      List<String> fieldNames, String messageType, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodeProto");
    opBuilder.addInput(sizes.asOutput());
    opBuilder.addInputList(Operands.asOutputs(values));
    String[] fieldNamesArray = new String[fieldNames.size()];
    for (int i = 0 ; i < fieldNamesArray.length ; i++) {
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
   * Sets the descriptorSource option.
   *
   * @param descriptorSource the descriptorSource option
   * @return this Options instance.
   */
  public static Options descriptorSource(String descriptorSource) {
    return new Options().descriptorSource(descriptorSource);
  }

  /**
   * Gets bytes.
   * Tensor of serialized protos with shape {@code batch_shape}.
   * @return bytes.
   */
  public Output<TString> bytes() {
    return bytes;
  }

  @Override
  public Output<TString> asOutput() {
    return bytes;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.EncodeProto}
   */
  public static class Options {
    private String descriptorSource;

    private Options() {
    }

    /**
     * Sets the descriptorSource option.
     *
     * @param descriptorSource the descriptorSource option
     * @return this Options instance.
     */
    public Options descriptorSource(String descriptorSource) {
      this.descriptorSource = descriptorSource;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EncodeProto.class
  )
  public static class Inputs extends RawOpInputs<EncodeProto> {
    /**
     * Tensor of int32 with shape {@code [batch_shape, len(field_names)]}.
     */
    public final Operand<TInt32> sizes;

    /**
     * List of tensors containing values for the corresponding field.
     */
    public final Iterable<Operand<?>> values;

    /**
     * List of strings containing proto field names.
     */
    public final String[] fieldNames;

    /**
     * Name of the proto message type to decode.
     */
    public final String messageType;

    /**
     * The descriptorSource attribute
     */
    public final String descriptorSource;

    /**
     * The input types.
     */
    public final DataType[] TinputTypes;

    public Inputs(GraphOperation op) {
      super(new EncodeProto(op), op, Arrays.asList("field_names", "message_type", "descriptor_source", "Tinput_types"));
      int inputIndex = 0;
      sizes = (Operand<TInt32>) op.input(inputIndex++);
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      fieldNames = op.attributes().getAttrStringList("field_names");
      messageType = op.attributes().getAttrString("message_type");
      descriptorSource = op.attributes().getAttrString("descriptor_source");
      TinputTypes = op.attributes().getAttrTypeList("Tinput_types");
    }
  }
}
