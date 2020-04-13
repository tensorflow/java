#!/bin/bash
# Script to build native TensorFlow libraries
set -eu

# Allows us to use ccache with Bazel on Mac
export BAZEL_USE_CPP_ONLY_TOOLCHAIN=1

export BAZEL_VC="${VCINSTALLDIR:-}"
if [[ -d $BAZEL_VC ]]; then
    # Work around compiler issues on Windows documented mainly in configure.py but also elsewhere
    export BUILD_FLAGS="--copt=//arch:AVX `#--copt=//arch:AVX2` --copt=-DWIN32_LEAN_AND_MEAN --host_copt=-DWIN32_LEAN_AND_MEAN --copt=-DNOGDI --host_copt=-DNOGDI --copt=-D_USE_MATH_DEFINES --host_copt=-D_USE_MATH_DEFINES --define=override_eigen_strong_inline=true"
    # https://software.intel.com/en-us/articles/intel-optimization-for-tensorflow-installation-guide#wind_B_S
    export PATH=$PATH:$(pwd)/bazel-tensorflow-core-api/external/mkl_windows/lib/
    export PYTHON_BIN_PATH=$(which python.exe)
else
    export BUILD_FLAGS="--copt=-msse4.1 --copt=-msse4.2 --copt=-mavx `#--copt=-mavx2 --copt=-mfma` --cxxopt=-std=c++14 --host_cxxopt=-std=c++14 --linkopt=-lstdc++ --host_linkopt=-lstdc++"
    export PYTHON_BIN_PATH=$(which python3)
fi

if [[ "${EXTENSION:-}" == *mkl* ]]; then
    export BUILD_FLAGS="$BUILD_FLAGS --config=mkl"
fi

if [[ "${EXTENSION:-}" == *gpu* ]]; then
    export BUILD_FLAGS="$BUILD_FLAGS --config=cuda"
    if [[ -z ${TF_CUDA_PATHS:-} ]] && [[ -d ${CUDA_PATH:-} ]]; then
        # Work around some issue with Bazel preventing it from detecting CUDA on Windows
        export TF_CUDA_PATHS="$CUDA_PATH"
    fi
fi

# Build C API of TensorFlow itself including a target to generate ops for Java
bazel build $BUILD_FLAGS --experimental_repo_remote_exec --python_path="$PYTHON_BIN_PATH" --config=monolithic --output_filter=DONT_MATCH_ANYTHING --verbose_failures \
    @org_tensorflow//tensorflow:tensorflow \
    @org_tensorflow//tensorflow/tools/lib_package:jnilicenses_generate \
    :java_op_gen_sources \
    :java_api_import \
    :java_proto_gen_sources

export BAZEL_BIN=$(pwd -P)/bazel-bin
export TENSORFLOW_BIN=$BAZEL_BIN/external/org_tensorflow/tensorflow

# Normalize some paths with symbolic links
TENSORFLOW_SO=($TENSORFLOW_BIN/libtensorflow.so.?.?.?)
if [[ -f $TENSORFLOW_SO ]]; then
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow.so
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow.so.2
fi
TENSORFLOW_DYLIB=($TENSORFLOW_BIN/libtensorflow.?.?.?.dylib)
if [[ -f $TENSORFLOW_DYLIB ]]; then
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow.dylib
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow.2.dylib
fi
TENSORFLOW_LIBS=($TENSORFLOW_BIN/tensorflow.dll.if.lib $TENSORFLOW_BIN/libtensorflow.dll.ifso)
for TENSORFLOW_LIB in ${TENSORFLOW_LIBS[@]}; do
    if [[ -f $TENSORFLOW_LIB ]]; then
        ln -sf $(basename $TENSORFLOW_LIB) $TENSORFLOW_BIN/tensorflow.lib
    fi
done
ls -l $TENSORFLOW_BIN

mkdir -p src/gen/java/
cd src/gen/java

# Copy only generated Java source files for ops
cp -r $BAZEL_BIN/ops/src/* .

# Copy generated Java protos from source jars
find $TENSORFLOW_BIN/core -name \*-speed-src.jar -exec jar xf {} \;
rm -rf META-INF
