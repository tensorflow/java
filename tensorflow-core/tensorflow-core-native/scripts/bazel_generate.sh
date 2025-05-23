#!/bin/bash
# Script to build native TensorFlow libraries
set -eu
source $(pwd -P)/scripts/bazel_common.sh

echo "Generate sources with Bazel"
# Build C/C++ API of TensorFlow itself including a target to generate ops for Java
${BAZEL_CMD:=bazel} --bazelrc=tensorflow.bazelrc $BUILD_FLAGS ${BUILD_USER_FLAGS:-} \
    @org_tensorflow//tensorflow/tools/lib_package:jnilicenses_generate \
    :java_proto_gen_sources

echo "Rebuilding generated source directories"
GEN_SRCS_DIR=src/gen/java
rm -rf $GEN_SRCS_DIR
mkdir -p $GEN_SRCS_DIR

GEN_RESOURCE_DIR=src/gen/resources
rm -rf $GEN_RESOURCE_DIR
mkdir -p $GEN_RESOURCE_DIR/org/tensorflow/

# Copy ops definitions to Java resources
echo "Copying TF ops definitions"
cp -f $TENSORFLOW_SRCS/core/ops/ops.pbtxt $GEN_RESOURCE_DIR/org/tensorflow
cp -rf $TENSORFLOW_SRCS/core/api_def/base_api $GEN_RESOURCE_DIR/org/tensorflow/

# Copy generated Java protos from source jars
echo "Extracting TF/TSL/XLA proto Java sources"
cd $GEN_SRCS_DIR
find $TENSORFLOW_BIN $BAZEL_BIN/external/local_tsl/tsl $BAZEL_BIN/external/local_xla/xla -name \*-speed-src.jar -exec jar xf {} \;
rm -rf META-INF
