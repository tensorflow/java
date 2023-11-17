// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/log_memory.proto

package org.tensorflow.proto;

public interface MemoryLogRawAllocationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.MemoryLogRawAllocation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Process-unique step id.
   * </pre>
   *
   * <code>int64 step_id = 1;</code>
   * @return The stepId.
   */
  long getStepId();

  /**
   * <pre>
   * Name of the operation making the allocation.
   * </pre>
   *
   * <code>string operation = 2;</code>
   * @return The operation.
   */
  java.lang.String getOperation();
  /**
   * <pre>
   * Name of the operation making the allocation.
   * </pre>
   *
   * <code>string operation = 2;</code>
   * @return The bytes for operation.
   */
  com.google.protobuf.ByteString
      getOperationBytes();

  /**
   * <pre>
   * Number of bytes in the allocation.
   * </pre>
   *
   * <code>int64 num_bytes = 3;</code>
   * @return The numBytes.
   */
  long getNumBytes();

  /**
   * <pre>
   * Address of the allocation.
   * </pre>
   *
   * <code>uint64 ptr = 4;</code>
   * @return The ptr.
   */
  long getPtr();

  /**
   * <pre>
   * Id of the tensor buffer being allocated, used to match to a
   * corresponding deallocation.
   * </pre>
   *
   * <code>int64 allocation_id = 5;</code>
   * @return The allocationId.
   */
  long getAllocationId();

  /**
   * <pre>
   * Name of the allocator used.
   * </pre>
   *
   * <code>string allocator_name = 6;</code>
   * @return The allocatorName.
   */
  java.lang.String getAllocatorName();
  /**
   * <pre>
   * Name of the allocator used.
   * </pre>
   *
   * <code>string allocator_name = 6;</code>
   * @return The bytes for allocatorName.
   */
  com.google.protobuf.ByteString
      getAllocatorNameBytes();
}
