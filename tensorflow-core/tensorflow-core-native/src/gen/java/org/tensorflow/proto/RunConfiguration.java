// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tsl/protobuf/test_log.proto

package org.tensorflow.proto;

/**
 * <pre>
 * Run-specific items such as arguments to the test / benchmark.
 * </pre>
 *
 * Protobuf type {@code tensorflow.RunConfiguration}
 */
public final class RunConfiguration extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.RunConfiguration)
    RunConfigurationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RunConfiguration.newBuilder() to construct.
  private RunConfiguration(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RunConfiguration() {
    argument_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RunConfiguration();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 2:
        return internalGetEnvVars();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.RunConfiguration.class, org.tensorflow.proto.RunConfiguration.Builder.class);
  }

  public static final int ARGUMENT_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList argument_;
  /**
   * <code>repeated string argument = 1;</code>
   * @return A list containing the argument.
   */
  public com.google.protobuf.ProtocolStringList
      getArgumentList() {
    return argument_;
  }
  /**
   * <code>repeated string argument = 1;</code>
   * @return The count of argument.
   */
  public int getArgumentCount() {
    return argument_.size();
  }
  /**
   * <code>repeated string argument = 1;</code>
   * @param index The index of the element to return.
   * @return The argument at the given index.
   */
  public java.lang.String getArgument(int index) {
    return argument_.get(index);
  }
  /**
   * <code>repeated string argument = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the argument at the given index.
   */
  public com.google.protobuf.ByteString
      getArgumentBytes(int index) {
    return argument_.getByteString(index);
  }

  public static final int ENV_VARS_FIELD_NUMBER = 2;
  private static final class EnvVarsDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, java.lang.String> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, java.lang.String>newDefaultInstance(
                org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_EnvVarsEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  private com.google.protobuf.MapField<
      java.lang.String, java.lang.String> envVars_;
  private com.google.protobuf.MapField<java.lang.String, java.lang.String>
  internalGetEnvVars() {
    if (envVars_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          EnvVarsDefaultEntryHolder.defaultEntry);
    }
    return envVars_;
  }

  public int getEnvVarsCount() {
    return internalGetEnvVars().getMap().size();
  }
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */

  @java.lang.Override
  public boolean containsEnvVars(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    return internalGetEnvVars().getMap().containsKey(key);
  }
  /**
   * Use {@link #getEnvVarsMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, java.lang.String> getEnvVars() {
    return getEnvVarsMap();
  }
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, java.lang.String> getEnvVarsMap() {
    return internalGetEnvVars().getMap();
  }
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  @java.lang.Override

  public java.lang.String getEnvVarsOrDefault(
      java.lang.String key,
      java.lang.String defaultValue) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetEnvVars().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  @java.lang.Override

  public java.lang.String getEnvVarsOrThrow(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetEnvVars().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < argument_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, argument_.getRaw(i));
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetEnvVars(),
        EnvVarsDefaultEntryHolder.defaultEntry,
        2);
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < argument_.size(); i++) {
        dataSize += computeStringSizeNoTag(argument_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getArgumentList().size();
    }
    for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
         : internalGetEnvVars().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
      envVars__ = EnvVarsDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, envVars__);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.tensorflow.proto.RunConfiguration)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.RunConfiguration other = (org.tensorflow.proto.RunConfiguration) obj;

    if (!getArgumentList()
        .equals(other.getArgumentList())) return false;
    if (!internalGetEnvVars().equals(
        other.internalGetEnvVars())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getArgumentCount() > 0) {
      hash = (37 * hash) + ARGUMENT_FIELD_NUMBER;
      hash = (53 * hash) + getArgumentList().hashCode();
    }
    if (!internalGetEnvVars().getMap().isEmpty()) {
      hash = (37 * hash) + ENV_VARS_FIELD_NUMBER;
      hash = (53 * hash) + internalGetEnvVars().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.RunConfiguration parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.RunConfiguration parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.RunConfiguration parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.RunConfiguration parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.tensorflow.proto.RunConfiguration prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Run-specific items such as arguments to the test / benchmark.
   * </pre>
   *
   * Protobuf type {@code tensorflow.RunConfiguration}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.RunConfiguration)
      org.tensorflow.proto.RunConfigurationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetEnvVars();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMutableEnvVars();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.RunConfiguration.class, org.tensorflow.proto.RunConfiguration.Builder.class);
    }

    // Construct using org.tensorflow.proto.RunConfiguration.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      argument_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      internalGetMutableEnvVars().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_RunConfiguration_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.RunConfiguration getDefaultInstanceForType() {
      return org.tensorflow.proto.RunConfiguration.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.RunConfiguration build() {
      org.tensorflow.proto.RunConfiguration result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.RunConfiguration buildPartial() {
      org.tensorflow.proto.RunConfiguration result = new org.tensorflow.proto.RunConfiguration(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        argument_ = argument_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.argument_ = argument_;
      result.envVars_ = internalGetEnvVars();
      result.envVars_.makeImmutable();
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.tensorflow.proto.RunConfiguration) {
        return mergeFrom((org.tensorflow.proto.RunConfiguration)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.RunConfiguration other) {
      if (other == org.tensorflow.proto.RunConfiguration.getDefaultInstance()) return this;
      if (!other.argument_.isEmpty()) {
        if (argument_.isEmpty()) {
          argument_ = other.argument_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureArgumentIsMutable();
          argument_.addAll(other.argument_);
        }
        onChanged();
      }
      internalGetMutableEnvVars().mergeFrom(
          other.internalGetEnvVars());
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();
              ensureArgumentIsMutable();
              argument_.add(s);
              break;
            } // case 10
            case 18: {
              com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
              envVars__ = input.readMessage(
                  EnvVarsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableEnvVars().getMutableMap().put(
                  envVars__.getKey(), envVars__.getValue());
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList argument_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureArgumentIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        argument_ = new com.google.protobuf.LazyStringArrayList(argument_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @return A list containing the argument.
     */
    public com.google.protobuf.ProtocolStringList
        getArgumentList() {
      return argument_.getUnmodifiableView();
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @return The count of argument.
     */
    public int getArgumentCount() {
      return argument_.size();
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param index The index of the element to return.
     * @return The argument at the given index.
     */
    public java.lang.String getArgument(int index) {
      return argument_.get(index);
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the argument at the given index.
     */
    public com.google.protobuf.ByteString
        getArgumentBytes(int index) {
      return argument_.getByteString(index);
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param index The index to set the value at.
     * @param value The argument to set.
     * @return This builder for chaining.
     */
    public Builder setArgument(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureArgumentIsMutable();
      argument_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param value The argument to add.
     * @return This builder for chaining.
     */
    public Builder addArgument(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureArgumentIsMutable();
      argument_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param values The argument to add.
     * @return This builder for chaining.
     */
    public Builder addAllArgument(
        java.lang.Iterable<java.lang.String> values) {
      ensureArgumentIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, argument_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearArgument() {
      argument_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string argument = 1;</code>
     * @param value The bytes of the argument to add.
     * @return This builder for chaining.
     */
    public Builder addArgumentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureArgumentIsMutable();
      argument_.add(value);
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> envVars_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetEnvVars() {
      if (envVars_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            EnvVarsDefaultEntryHolder.defaultEntry);
      }
      return envVars_;
    }
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetMutableEnvVars() {
      onChanged();;
      if (envVars_ == null) {
        envVars_ = com.google.protobuf.MapField.newMapField(
            EnvVarsDefaultEntryHolder.defaultEntry);
      }
      if (!envVars_.isMutable()) {
        envVars_ = envVars_.copy();
      }
      return envVars_;
    }

    public int getEnvVarsCount() {
      return internalGetEnvVars().getMap().size();
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */

    @java.lang.Override
    public boolean containsEnvVars(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetEnvVars().getMap().containsKey(key);
    }
    /**
     * Use {@link #getEnvVarsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getEnvVars() {
      return getEnvVarsMap();
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getEnvVarsMap() {
      return internalGetEnvVars().getMap();
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */
    @java.lang.Override

    public java.lang.String getEnvVarsOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetEnvVars().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */
    @java.lang.Override

    public java.lang.String getEnvVarsOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetEnvVars().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearEnvVars() {
      internalGetMutableEnvVars().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */

    public Builder removeEnvVars(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      internalGetMutableEnvVars().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String>
    getMutableEnvVars() {
      return internalGetMutableEnvVars().getMutableMap();
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */
    public Builder putEnvVars(
        java.lang.String key,
        java.lang.String value) {
      if (key == null) { throw new NullPointerException("map key"); }
      if (value == null) {
  throw new NullPointerException("map value");
}

      internalGetMutableEnvVars().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <pre>
     * Environment variables used to run the test/benchmark.
     * </pre>
     *
     * <code>map&lt;string, string&gt; env_vars = 2;</code>
     */

    public Builder putAllEnvVars(
        java.util.Map<java.lang.String, java.lang.String> values) {
      internalGetMutableEnvVars().getMutableMap()
          .putAll(values);
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tensorflow.RunConfiguration)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.RunConfiguration)
  private static final org.tensorflow.proto.RunConfiguration DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.RunConfiguration();
  }

  public static org.tensorflow.proto.RunConfiguration getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RunConfiguration>
      PARSER = new com.google.protobuf.AbstractParser<RunConfiguration>() {
    @java.lang.Override
    public RunConfiguration parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<RunConfiguration> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RunConfiguration> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.RunConfiguration getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

