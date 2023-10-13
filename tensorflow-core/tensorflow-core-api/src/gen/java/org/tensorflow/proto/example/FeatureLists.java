// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/example/feature.proto

package org.tensorflow.proto.example;

/**
 * Protobuf type {@code tensorflow.FeatureLists}
 */
public final class FeatureLists extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.FeatureLists)
    FeatureListsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FeatureLists.newBuilder() to construct.
  private FeatureLists(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FeatureLists() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FeatureLists();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetFeatureList();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.example.FeatureLists.class, org.tensorflow.proto.example.FeatureLists.Builder.class);
  }

  public static final int FEATURE_LIST_FIELD_NUMBER = 1;
  private static final class FeatureListDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, org.tensorflow.proto.example.FeatureList> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, org.tensorflow.proto.example.FeatureList>newDefaultInstance(
                org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_FeatureListEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                org.tensorflow.proto.example.FeatureList.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, org.tensorflow.proto.example.FeatureList> featureList_;
  private com.google.protobuf.MapField<java.lang.String, org.tensorflow.proto.example.FeatureList>
  internalGetFeatureList() {
    if (featureList_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          FeatureListDefaultEntryHolder.defaultEntry);
    }
    return featureList_;
  }

  public int getFeatureListCount() {
    return internalGetFeatureList().getMap().size();
  }
  /**
   * <pre>
   * Map from feature name to feature list.
   * </pre>
   *
   * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
   */

  @java.lang.Override
  public boolean containsFeatureList(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    return internalGetFeatureList().getMap().containsKey(key);
  }
  /**
   * Use {@link #getFeatureListMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> getFeatureList() {
    return getFeatureListMap();
  }
  /**
   * <pre>
   * Map from feature name to feature list.
   * </pre>
   *
   * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> getFeatureListMap() {
    return internalGetFeatureList().getMap();
  }
  /**
   * <pre>
   * Map from feature name to feature list.
   * </pre>
   *
   * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
   */
  @java.lang.Override

  public org.tensorflow.proto.example.FeatureList getFeatureListOrDefault(
      java.lang.String key,
      org.tensorflow.proto.example.FeatureList defaultValue) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> map =
        internalGetFeatureList().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <pre>
   * Map from feature name to feature list.
   * </pre>
   *
   * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
   */
  @java.lang.Override

  public org.tensorflow.proto.example.FeatureList getFeatureListOrThrow(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> map =
        internalGetFeatureList().getMap();
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
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetFeatureList(),
        FeatureListDefaultEntryHolder.defaultEntry,
        1);
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<java.lang.String, org.tensorflow.proto.example.FeatureList> entry
         : internalGetFeatureList().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, org.tensorflow.proto.example.FeatureList>
      featureList__ = FeatureListDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, featureList__);
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
    if (!(obj instanceof org.tensorflow.proto.example.FeatureLists)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.example.FeatureLists other = (org.tensorflow.proto.example.FeatureLists) obj;

    if (!internalGetFeatureList().equals(
        other.internalGetFeatureList())) return false;
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
    if (!internalGetFeatureList().getMap().isEmpty()) {
      hash = (37 * hash) + FEATURE_LIST_FIELD_NUMBER;
      hash = (53 * hash) + internalGetFeatureList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.example.FeatureLists parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.example.FeatureLists parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.example.FeatureLists parseFrom(
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
  public static Builder newBuilder(org.tensorflow.proto.example.FeatureLists prototype) {
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
   * Protobuf type {@code tensorflow.FeatureLists}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.FeatureLists)
      org.tensorflow.proto.example.FeatureListsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetFeatureList();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableFeatureList();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.example.FeatureLists.class, org.tensorflow.proto.example.FeatureLists.Builder.class);
    }

    // Construct using org.tensorflow.proto.example.FeatureLists.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      internalGetMutableFeatureList().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.example.FeatureProtos.internal_static_tensorflow_FeatureLists_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.example.FeatureLists getDefaultInstanceForType() {
      return org.tensorflow.proto.example.FeatureLists.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.example.FeatureLists build() {
      org.tensorflow.proto.example.FeatureLists result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.example.FeatureLists buildPartial() {
      org.tensorflow.proto.example.FeatureLists result = new org.tensorflow.proto.example.FeatureLists(this);
      int from_bitField0_ = bitField0_;
      result.featureList_ = internalGetFeatureList();
      result.featureList_.makeImmutable();
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
      if (other instanceof org.tensorflow.proto.example.FeatureLists) {
        return mergeFrom((org.tensorflow.proto.example.FeatureLists)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.example.FeatureLists other) {
      if (other == org.tensorflow.proto.example.FeatureLists.getDefaultInstance()) return this;
      internalGetMutableFeatureList().mergeFrom(
          other.internalGetFeatureList());
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
              com.google.protobuf.MapEntry<java.lang.String, org.tensorflow.proto.example.FeatureList>
              featureList__ = input.readMessage(
                  FeatureListDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableFeatureList().getMutableMap().put(
                  featureList__.getKey(), featureList__.getValue());
              break;
            } // case 10
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

    private com.google.protobuf.MapField<
        java.lang.String, org.tensorflow.proto.example.FeatureList> featureList_;
    private com.google.protobuf.MapField<java.lang.String, org.tensorflow.proto.example.FeatureList>
    internalGetFeatureList() {
      if (featureList_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            FeatureListDefaultEntryHolder.defaultEntry);
      }
      return featureList_;
    }
    private com.google.protobuf.MapField<java.lang.String, org.tensorflow.proto.example.FeatureList>
    internalGetMutableFeatureList() {
      onChanged();;
      if (featureList_ == null) {
        featureList_ = com.google.protobuf.MapField.newMapField(
            FeatureListDefaultEntryHolder.defaultEntry);
      }
      if (!featureList_.isMutable()) {
        featureList_ = featureList_.copy();
      }
      return featureList_;
    }

    public int getFeatureListCount() {
      return internalGetFeatureList().getMap().size();
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */

    @java.lang.Override
    public boolean containsFeatureList(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetFeatureList().getMap().containsKey(key);
    }
    /**
     * Use {@link #getFeatureListMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> getFeatureList() {
      return getFeatureListMap();
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> getFeatureListMap() {
      return internalGetFeatureList().getMap();
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */
    @java.lang.Override

    public org.tensorflow.proto.example.FeatureList getFeatureListOrDefault(
        java.lang.String key,
        org.tensorflow.proto.example.FeatureList defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> map =
          internalGetFeatureList().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */
    @java.lang.Override

    public org.tensorflow.proto.example.FeatureList getFeatureListOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> map =
          internalGetFeatureList().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearFeatureList() {
      internalGetMutableFeatureList().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */

    public Builder removeFeatureList(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      internalGetMutableFeatureList().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList>
    getMutableFeatureList() {
      return internalGetMutableFeatureList().getMutableMap();
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */
    public Builder putFeatureList(
        java.lang.String key,
        org.tensorflow.proto.example.FeatureList value) {
      if (key == null) { throw new NullPointerException("map key"); }
      if (value == null) {
  throw new NullPointerException("map value");
}

      internalGetMutableFeatureList().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <pre>
     * Map from feature name to feature list.
     * </pre>
     *
     * <code>map&lt;string, .tensorflow.FeatureList&gt; feature_list = 1;</code>
     */

    public Builder putAllFeatureList(
        java.util.Map<java.lang.String, org.tensorflow.proto.example.FeatureList> values) {
      internalGetMutableFeatureList().getMutableMap()
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


    // @@protoc_insertion_point(builder_scope:tensorflow.FeatureLists)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.FeatureLists)
  private static final org.tensorflow.proto.example.FeatureLists DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.example.FeatureLists();
  }

  public static org.tensorflow.proto.example.FeatureLists getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FeatureLists>
      PARSER = new com.google.protobuf.AbstractParser<FeatureLists>() {
    @java.lang.Override
    public FeatureLists parsePartialFrom(
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

  public static com.google.protobuf.Parser<FeatureLists> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FeatureLists> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.example.FeatureLists getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

