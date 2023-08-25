#!/bin/bash
# Script to build native TensorFlow libraries
set -eu

# Set generic Bazel build options
export BAZEL_USE_CPP_ONLY_TOOLCHAIN=1
export BAZEL_VC="${VCINSTALLDIR:-}"
if [[ -d $BAZEL_VC ]]; then
    export BAZEL_BUILD="--output_user_root=$(cygpath -w $TMP) build"
    export PYTHON_BIN_PATH=$(which python.exe)
else
    export BAZEL_BUILD="build"
    export PYTHON_BIN_PATH=$(which python3)
fi

# Add platform specific flags
if [[ "${PLATFORM:-}" == macosx-arm64 ]]; then
  BUILD_FLAGS="$BUILD_FLAGS --config=macos_arm64"
fi
BUILD_FLAGS="$BUILD_FLAGS --experimental_repo_remote_exec --python_path="$PYTHON_BIN_PATH" --output_filter=DONT_MATCH_ANYTHING --verbose_failures --distinct_host_configuration=true"

# Build C/C++ API of TensorFlow itself including a target to generate ops for Java
${BAZEL_CMD:=bazel} --bazelrc=tensorflow.bazelrc $BAZEL_BUILD $BUILD_FLAGS ${BUILD_USER_FLAGS:-} \
    @org_tensorflow//tensorflow/tools/lib_package:jnilicenses_generate \
    :java_proto_gen_sources \
    :java_op_exporter \
    :java_api_import \
    :custom_ops_test

export BAZEL_SRCS=$(pwd -P)/bazel-tensorflow-core-api
export BAZEL_BIN=$(pwd -P)/bazel-bin
export TENSORFLOW_BIN=$BAZEL_BIN/external/org_tensorflow/tensorflow

GEN_SRCS_DIR=src/gen/java
mkdir -p $GEN_SRCS_DIR

GEN_RESOURCE_DIR=src/gen/resources
mkdir -p $GEN_RESOURCE_DIR

if [[ -z "${SKIP_EXPORT:-}" ]]; then
  # Export op defs
  echo "Exporting Ops"
  $BAZEL_BIN/java_op_exporter \
      $GEN_RESOURCE_DIR/ops.pb \
      $GEN_RESOURCE_DIR/ops.pbtxt \
      $BAZEL_SRCS/external/org_tensorflow/tensorflow/core/api_def/base_api \
      src/bazel/api_def
else
  echo "Skipping Op export"
fi

# Copy generated Java protos from source jars
cd $GEN_SRCS_DIR
find $TENSORFLOW_BIN/core -name \*-speed-src.jar -exec jar xf {} \;
rm -rf META-INF
