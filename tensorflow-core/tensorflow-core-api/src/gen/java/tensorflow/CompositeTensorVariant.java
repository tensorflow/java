// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/composite_tensor_variant.proto

package tensorflow;

public final class CompositeTensorVariant {
  private CompositeTensorVariant() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CompositeTensorVariantMetadataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:tensorflow.CompositeTensorVariantMetadata)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     * @return Whether the typeSpecProto field is set.
     */
    boolean hasTypeSpecProto();
    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     * @return The typeSpecProto.
     */
    tensorflow.Struct.TypeSpecProto getTypeSpecProto();
    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     */
    tensorflow.Struct.TypeSpecProtoOrBuilder getTypeSpecProtoOrBuilder();
  }
  /**
   * <pre>
   * Metadata for CompositeTensorVariant, used when serializing as Variant.
   * We define a new message here (rather than directly using TypeSpecProto for
   * the metadata string) to retain flexibility to change the metadata encoding
   * to support additional features.
   * </pre>
   *
   * Protobuf type {@code tensorflow.CompositeTensorVariantMetadata}
   */
  public static final class CompositeTensorVariantMetadata extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:tensorflow.CompositeTensorVariantMetadata)
      CompositeTensorVariantMetadataOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CompositeTensorVariantMetadata.newBuilder() to construct.
    private CompositeTensorVariantMetadata(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CompositeTensorVariantMetadata() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new CompositeTensorVariantMetadata();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tensorflow.CompositeTensorVariant.internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tensorflow.CompositeTensorVariant.internal_static_tensorflow_CompositeTensorVariantMetadata_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.class, tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.Builder.class);
    }

    public static final int TYPE_SPEC_PROTO_FIELD_NUMBER = 1;
    private tensorflow.Struct.TypeSpecProto typeSpecProto_;
    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     * @return Whether the typeSpecProto field is set.
     */
    @java.lang.Override
    public boolean hasTypeSpecProto() {
      return typeSpecProto_ != null;
    }
    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     * @return The typeSpecProto.
     */
    @java.lang.Override
    public tensorflow.Struct.TypeSpecProto getTypeSpecProto() {
      return typeSpecProto_ == null ? tensorflow.Struct.TypeSpecProto.getDefaultInstance() : typeSpecProto_;
    }
    /**
     * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
     */
    @java.lang.Override
    public tensorflow.Struct.TypeSpecProtoOrBuilder getTypeSpecProtoOrBuilder() {
      return getTypeSpecProto();
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
      if (typeSpecProto_ != null) {
        output.writeMessage(1, getTypeSpecProto());
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (typeSpecProto_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getTypeSpecProto());
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
      if (!(obj instanceof tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata)) {
        return super.equals(obj);
      }
      tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata other = (tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata) obj;

      if (hasTypeSpecProto() != other.hasTypeSpecProto()) return false;
      if (hasTypeSpecProto()) {
        if (!getTypeSpecProto()
            .equals(other.getTypeSpecProto())) return false;
      }
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
      if (hasTypeSpecProto()) {
        hash = (37 * hash) + TYPE_SPEC_PROTO_FIELD_NUMBER;
        hash = (53 * hash) + getTypeSpecProto().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata parseFrom(
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
    public static Builder newBuilder(tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata prototype) {
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
     * Metadata for CompositeTensorVariant, used when serializing as Variant.
     * We define a new message here (rather than directly using TypeSpecProto for
     * the metadata string) to retain flexibility to change the metadata encoding
     * to support additional features.
     * </pre>
     *
     * Protobuf type {@code tensorflow.CompositeTensorVariantMetadata}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:tensorflow.CompositeTensorVariantMetadata)
        tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return tensorflow.CompositeTensorVariant.internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return tensorflow.CompositeTensorVariant.internal_static_tensorflow_CompositeTensorVariantMetadata_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.class, tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.Builder.class);
      }

      // Construct using tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (typeSpecProtoBuilder_ == null) {
          typeSpecProto_ = null;
        } else {
          typeSpecProto_ = null;
          typeSpecProtoBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return tensorflow.CompositeTensorVariant.internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor;
      }

      @java.lang.Override
      public tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata getDefaultInstanceForType() {
        return tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.getDefaultInstance();
      }

      @java.lang.Override
      public tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata build() {
        tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata buildPartial() {
        tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata result = new tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata(this);
        if (typeSpecProtoBuilder_ == null) {
          result.typeSpecProto_ = typeSpecProto_;
        } else {
          result.typeSpecProto_ = typeSpecProtoBuilder_.build();
        }
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
        if (other instanceof tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata) {
          return mergeFrom((tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata other) {
        if (other == tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata.getDefaultInstance()) return this;
        if (other.hasTypeSpecProto()) {
          mergeTypeSpecProto(other.getTypeSpecProto());
        }
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
                input.readMessage(
                    getTypeSpecProtoFieldBuilder().getBuilder(),
                    extensionRegistry);

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

      private tensorflow.Struct.TypeSpecProto typeSpecProto_;
      private com.google.protobuf.SingleFieldBuilderV3<
          tensorflow.Struct.TypeSpecProto, tensorflow.Struct.TypeSpecProto.Builder, tensorflow.Struct.TypeSpecProtoOrBuilder> typeSpecProtoBuilder_;
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       * @return Whether the typeSpecProto field is set.
       */
      public boolean hasTypeSpecProto() {
        return typeSpecProtoBuilder_ != null || typeSpecProto_ != null;
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       * @return The typeSpecProto.
       */
      public tensorflow.Struct.TypeSpecProto getTypeSpecProto() {
        if (typeSpecProtoBuilder_ == null) {
          return typeSpecProto_ == null ? tensorflow.Struct.TypeSpecProto.getDefaultInstance() : typeSpecProto_;
        } else {
          return typeSpecProtoBuilder_.getMessage();
        }
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public Builder setTypeSpecProto(tensorflow.Struct.TypeSpecProto value) {
        if (typeSpecProtoBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          typeSpecProto_ = value;
          onChanged();
        } else {
          typeSpecProtoBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public Builder setTypeSpecProto(
          tensorflow.Struct.TypeSpecProto.Builder builderForValue) {
        if (typeSpecProtoBuilder_ == null) {
          typeSpecProto_ = builderForValue.build();
          onChanged();
        } else {
          typeSpecProtoBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public Builder mergeTypeSpecProto(tensorflow.Struct.TypeSpecProto value) {
        if (typeSpecProtoBuilder_ == null) {
          if (typeSpecProto_ != null) {
            typeSpecProto_ =
              tensorflow.Struct.TypeSpecProto.newBuilder(typeSpecProto_).mergeFrom(value).buildPartial();
          } else {
            typeSpecProto_ = value;
          }
          onChanged();
        } else {
          typeSpecProtoBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public Builder clearTypeSpecProto() {
        if (typeSpecProtoBuilder_ == null) {
          typeSpecProto_ = null;
          onChanged();
        } else {
          typeSpecProto_ = null;
          typeSpecProtoBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public tensorflow.Struct.TypeSpecProto.Builder getTypeSpecProtoBuilder() {
        
        onChanged();
        return getTypeSpecProtoFieldBuilder().getBuilder();
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      public tensorflow.Struct.TypeSpecProtoOrBuilder getTypeSpecProtoOrBuilder() {
        if (typeSpecProtoBuilder_ != null) {
          return typeSpecProtoBuilder_.getMessageOrBuilder();
        } else {
          return typeSpecProto_ == null ?
              tensorflow.Struct.TypeSpecProto.getDefaultInstance() : typeSpecProto_;
        }
      }
      /**
       * <code>.tensorflow.TypeSpecProto type_spec_proto = 1;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          tensorflow.Struct.TypeSpecProto, tensorflow.Struct.TypeSpecProto.Builder, tensorflow.Struct.TypeSpecProtoOrBuilder> 
          getTypeSpecProtoFieldBuilder() {
        if (typeSpecProtoBuilder_ == null) {
          typeSpecProtoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              tensorflow.Struct.TypeSpecProto, tensorflow.Struct.TypeSpecProto.Builder, tensorflow.Struct.TypeSpecProtoOrBuilder>(
                  getTypeSpecProto(),
                  getParentForChildren(),
                  isClean());
          typeSpecProto_ = null;
        }
        return typeSpecProtoBuilder_;
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


      // @@protoc_insertion_point(builder_scope:tensorflow.CompositeTensorVariantMetadata)
    }

    // @@protoc_insertion_point(class_scope:tensorflow.CompositeTensorVariantMetadata)
    private static final tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata();
    }

    public static tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CompositeTensorVariantMetadata>
        PARSER = new com.google.protobuf.AbstractParser<CompositeTensorVariantMetadata>() {
      @java.lang.Override
      public CompositeTensorVariantMetadata parsePartialFrom(
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

    public static com.google.protobuf.Parser<CompositeTensorVariantMetadata> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CompositeTensorVariantMetadata> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public tensorflow.CompositeTensorVariant.CompositeTensorVariantMetadata getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_CompositeTensorVariantMetadata_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n7tensorflow/core/protobuf/composite_ten" +
      "sor_variant.proto\022\ntensorflow\032%tensorflo" +
      "w/core/protobuf/struct.proto\"T\n\036Composit" +
      "eTensorVariantMetadata\0222\n\017type_spec_prot" +
      "o\030\001 \001(\0132\031.tensorflow.TypeSpecProtoBWZUgi" +
      "thub.com/tensorflow/tensorflow/tensorflo" +
      "w/go/core/protobuf/for_core_protos_go_pr" +
      "otob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          tensorflow.Struct.getDescriptor(),
        });
    internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_CompositeTensorVariantMetadata_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_CompositeTensorVariantMetadata_descriptor,
        new java.lang.String[] { "TypeSpecProto", });
    tensorflow.Struct.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
