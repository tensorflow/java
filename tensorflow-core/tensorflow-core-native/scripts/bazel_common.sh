#!/bin/bash
set -eu

# Set generic Bazel build options
export BAZEL_VC="${VCINSTALLDIR:-}"
if [[ -d $BAZEL_VC ]]; then
    export BUILD_FLAGS="--output_user_root=$(cygpath -w $TMP) build"
    export PYTHON_BIN_PATH=$(which python.exe)
else
    export BUILD_FLAGS="build"
    export PYTHON_BIN_PATH=$(which python3)
fi

# Add platform specific flags
if [[ "${PLATFORM:-}" == macosx-arm64 ]]; then
  BUILD_FLAGS="$BUILD_FLAGS --config=macos_arm64"
fi
BUILD_FLAGS="$BUILD_FLAGS --experimental_repo_remote_exec --python_path="$PYTHON_BIN_PATH" --output_filter=DONT_MATCH_ANYTHING --verbose_failures"

export BAZEL_BIN=$(pwd -P)/bazel-bin
export TENSORFLOW_BIN=$BAZEL_BIN/external/org_tensorflow/tensorflow

export BAZEL_SRCS=$(pwd -P)/bazel-tensorflow-core-native
export TENSORFLOW_SRCS=$BAZEL_SRCS/external/org_tensorflow/tensorflow
