// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/op_def.proto

package org.tensorflow.proto.framework;

public interface OpDefOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.OpDef)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Op names starting with an underscore are reserved for internal use.
   * Names should be CamelCase and match the regexp "[A-Z][a-zA-Z0-9&gt;_]*".
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * Op names starting with an underscore are reserved for internal use.
   * Names should be CamelCase and match the regexp "[A-Z][a-zA-Z0-9&gt;_]*".
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Description of the input(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef input_arg = 2;</code>
   */
  java.util.List<org.tensorflow.proto.framework.OpDef.ArgDef> 
      getInputArgList();
  /**
   * <pre>
   * Description of the input(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef input_arg = 2;</code>
   */
  org.tensorflow.proto.framework.OpDef.ArgDef getInputArg(int index);
  /**
   * <pre>
   * Description of the input(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef input_arg = 2;</code>
   */
  int getInputArgCount();
  /**
   * <pre>
   * Description of the input(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef input_arg = 2;</code>
   */
  java.util.List<? extends org.tensorflow.proto.framework.OpDef.ArgDefOrBuilder> 
      getInputArgOrBuilderList();
  /**
   * <pre>
   * Description of the input(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef input_arg = 2;</code>
   */
  org.tensorflow.proto.framework.OpDef.ArgDefOrBuilder getInputArgOrBuilder(
      int index);

  /**
   * <pre>
   * Description of the output(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef output_arg = 3;</code>
   */
  java.util.List<org.tensorflow.proto.framework.OpDef.ArgDef> 
      getOutputArgList();
  /**
   * <pre>
   * Description of the output(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef output_arg = 3;</code>
   */
  org.tensorflow.proto.framework.OpDef.ArgDef getOutputArg(int index);
  /**
   * <pre>
   * Description of the output(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef output_arg = 3;</code>
   */
  int getOutputArgCount();
  /**
   * <pre>
   * Description of the output(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef output_arg = 3;</code>
   */
  java.util.List<? extends org.tensorflow.proto.framework.OpDef.ArgDefOrBuilder> 
      getOutputArgOrBuilderList();
  /**
   * <pre>
   * Description of the output(s).
   * </pre>
   *
   * <code>repeated .tensorflow.OpDef.ArgDef output_arg = 3;</code>
   */
  org.tensorflow.proto.framework.OpDef.ArgDefOrBuilder getOutputArgOrBuilder(
      int index);

  /**
   * <pre>
   * Named control outputs for this operation. Useful only for composite
   * operations (i.e. functions) which want to name different control outputs.
   * </pre>
   *
   * <code>repeated string control_output = 20;</code>
   * @return A list containing the controlOutput.
   */
  java.util.List<java.lang.String>
      getControlOutputList();
  /**
   * <pre>
   * Named control outputs for this operation. Useful only for composite
   * operations (i.e. functions) which want to name different control outputs.
   * </pre>
   *
   * <code>repeated string control_output = 20;</code>
   * @return The count of controlOutput.
   */
  int getControlOutputCount();
  /**
   * <pre>
   * Named control outputs for this operation. Useful only for composite
   * operations (i.e. functions) which want to name different control outputs.
   * </pre>
   *
   * <code>repeated string control_output = 20;</code>
   * @param index The index of the element to return.
   * @return The controlOutput at the given index.
   */
  java.lang.String getControlOutput(int index);
  /**
   * <pre>
   * Named control outputs for this operation. Useful only for composite
   * operations (i.e. functions) which want to name different control outputs.
   * </pre>
   *
   * <code>repeated string control_output = 20;</code>
   * @param index The index of the value to return.
   * @return The bytes of the controlOutput at the given index.
   */
  com.google.protobuf.ByteString
      getControlOutputBytes(int index);

  /**
   * <code>repeated .tensorflow.OpDef.AttrDef attr = 4;</code>
   */
  java.util.List<org.tensorflow.proto.framework.OpDef.AttrDef> 
      getAttrList();
  /**
   * <code>repeated .tensorflow.OpDef.AttrDef attr = 4;</code>
   */
  org.tensorflow.proto.framework.OpDef.AttrDef getAttr(int index);
  /**
   * <code>repeated .tensorflow.OpDef.AttrDef attr = 4;</code>
   */
  int getAttrCount();
  /**
   * <code>repeated .tensorflow.OpDef.AttrDef attr = 4;</code>
   */
  java.util.List<? extends org.tensorflow.proto.framework.OpDef.AttrDefOrBuilder> 
      getAttrOrBuilderList();
  /**
   * <code>repeated .tensorflow.OpDef.AttrDef attr = 4;</code>
   */
  org.tensorflow.proto.framework.OpDef.AttrDefOrBuilder getAttrOrBuilder(
      int index);

  /**
   * <pre>
   * Optional deprecation based on GraphDef versions.
   * </pre>
   *
   * <code>.tensorflow.OpDeprecation deprecation = 8;</code>
   * @return Whether the deprecation field is set.
   */
  boolean hasDeprecation();
  /**
   * <pre>
   * Optional deprecation based on GraphDef versions.
   * </pre>
   *
   * <code>.tensorflow.OpDeprecation deprecation = 8;</code>
   * @return The deprecation.
   */
  org.tensorflow.proto.framework.OpDeprecation getDeprecation();
  /**
   * <pre>
   * Optional deprecation based on GraphDef versions.
   * </pre>
   *
   * <code>.tensorflow.OpDeprecation deprecation = 8;</code>
   */
  org.tensorflow.proto.framework.OpDeprecationOrBuilder getDeprecationOrBuilder();

  /**
   * <pre>
   * One-line human-readable description of what the Op does.
   * </pre>
   *
   * <code>string summary = 5;</code>
   * @return The summary.
   */
  java.lang.String getSummary();
  /**
   * <pre>
   * One-line human-readable description of what the Op does.
   * </pre>
   *
   * <code>string summary = 5;</code>
   * @return The bytes for summary.
   */
  com.google.protobuf.ByteString
      getSummaryBytes();

  /**
   * <pre>
   * Additional, longer human-readable description of what the Op does.
   * </pre>
   *
   * <code>string description = 6;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <pre>
   * Additional, longer human-readable description of what the Op does.
   * </pre>
   *
   * <code>string description = 6;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <pre>
   * True if the operation is commutative ("op(a,b) == op(b,a)" for all inputs)
   * </pre>
   *
   * <code>bool is_commutative = 18;</code>
   * @return The isCommutative.
   */
  boolean getIsCommutative();

  /**
   * <pre>
   * If is_aggregate is true, then this operation accepts N &gt;= 2
   * inputs and produces 1 output all of the same type.  Should be
   * associative and commutative, and produce output with the same
   * shape as the input.  The optimizer may replace an aggregate op
   * taking input from multiple devices with a tree of aggregate ops
   * that aggregate locally within each device (and possibly within
   * groups of nearby devices) before communicating.
   * TODO(josh11b): Implement that optimization.
   * </pre>
   *
   * <code>bool is_aggregate = 16;</code>
   * @return The isAggregate.
   */
  boolean getIsAggregate();

  /**
   * <pre>
   * Ops are marked as stateful if their behavior depends on some state beyond
   * their input tensors (e.g. variable reading op) or if they have
   * a side-effect (e.g. printing or asserting ops). Equivalently, stateless ops
   * must always produce the same output for the same input and have
   * no side-effects.
   * By default Ops may be moved between devices.  Stateful ops should
   * either not be moved, or should only be moved if that state can also
   * be moved (e.g. via some sort of save / restore).
   * Stateful ops are guaranteed to never be optimized away by Common
   * Subexpression Elimination (CSE).
   * </pre>
   *
   * <code>bool is_stateful = 17;</code>
   * @return The isStateful.
   */
  boolean getIsStateful();

  /**
   * <pre>
   * By default, all inputs to an Op must be initialized Tensors.  Ops
   * that may initialize tensors for the first time should set this
   * field to true, to allow the Op to take an uninitialized Tensor as
   * input.
   * </pre>
   *
   * <code>bool allows_uninitialized_input = 19;</code>
   * @return The allowsUninitializedInput.
   */
  boolean getAllowsUninitializedInput();

  /**
   * <pre>
   * Indicates whether the op implementation uses distributed communication.
   * If True, the op is allowed to return errors for network disconnection and
   * trigger TF network failure handling logics.
   * </pre>
   *
   * <code>bool is_distributed_communication = 21;</code>
   * @return The isDistributedCommunication.
   */
  boolean getIsDistributedCommunication();
}
