// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/verifier_config.proto

package org.tensorflow.proto;

public interface VerifierConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.VerifierConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Deadline for completion of all verification i.e. all the Toggle ON
   * verifiers must complete execution within this time.
   * </pre>
   *
   * <code>int64 verification_timeout_in_ms = 1;</code>
   * @return The verificationTimeoutInMs.
   */
  long getVerificationTimeoutInMs();

  /**
   * <pre>
   * Perform structural validation on a tensorflow graph. Default is OFF.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig.Toggle structure_verifier = 2;</code>
   * @return The enum numeric value on the wire for structureVerifier.
   */
  int getStructureVerifierValue();
  /**
   * <pre>
   * Perform structural validation on a tensorflow graph. Default is OFF.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig.Toggle structure_verifier = 2;</code>
   * @return The structureVerifier.
   */
  org.tensorflow.proto.VerifierConfig.Toggle getStructureVerifier();
}
