#!/bin/bash
# Script to build native TensorFlow libraries
set -eu

# Allows us to use ccache with Bazel
export BAZEL_USE_CPP_ONLY_TOOLCHAIN=1
export BAZEL_VC="C:/Program Files (x86)/Microsoft Visual Studio/2017/Community/VC/"
export PYTHON_BIN_PATH=$(which python.exe)
if [[ ! -f $PYTHON_BIN_PATH ]]; then
    export PYTHON_BIN_PATH=$(which python3)
fi

# Build TensorFlow itself
bazel build --python_path="$PYTHON_BIN_PATH" --config=mkl --output_filter=DONT_MATCH_ANYTHING --verbose_failures @org_tensorflow//tensorflow:tensorflow @org_tensorflow//tensorflow/java:tensorflow

# Normalize some paths with symbolic links
TENSORFLOW_SO=(bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.so.?.?.?)
if [[ -f $TENSORFLOW_SO ]]; then
    ln -sf $(basename $TENSORFLOW_SO) bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.so
    ln -sf $(basename $TENSORFLOW_SO) bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.so.2
fi
TENSORFLOW_DYLIB=(bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.?.?.?.dylib)
if [[ -f $TENSORFLOW_DYLIB ]]; then
    ln -sf $(basename $TENSORFLOW_DYLIB) bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.dylib
    ln -sf $(basename $TENSORFLOW_DYLIB) bazel-bin/external/org_tensorflow/tensorflow/libtensorflow.2.dylib
fi

# Copy generated Java source files
mkdir -p src/gen/java/
cp -r bazel-genfiles/external/org_tensorflow/tensorflow/java/ops/src/main/java/* src/gen/java/
cp -r bazel-genfiles/external/org_tensorflow/tensorflow/java/_javac/tensorflow/libtensorflow_sourcegenfiles/* src/gen/java/
