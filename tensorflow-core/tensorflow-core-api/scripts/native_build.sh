#!/bin/bash
# Script to build native TensorFlow libraries
set -eu

BUILD_FLAGS=""

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

# Add platform specific flags
if [[ "${PLATFORM:-}" == linux-arm64 ]]; then
  BUILD_FLAGS="--config=mkl_aarch64"
fi

if [[ "${EXTENSION:-}" == *mkl* ]]; then
    BUILD_FLAGS="$BUILD_FLAGS --config=mkl"
fi

if [[ "${EXTENSION:-}" == *gpu* ]]; then
    BUILD_FLAGS="$BUILD_FLAGS --config=cuda"
    export TF_CUDA_COMPUTE_CAPABILITIES="${TF_CUDA_COMPUTE_CAPABILITIES:-"sm_35,sm_50,sm_60,sm_70,sm_75,compute_80"}"
    if [[ -z ${TF_CUDA_PATHS:-} ]] && [[ -d ${CUDA_PATH:-} ]]; then
        # Work around some issue with Bazel preventing it from detecting CUDA on Windows
        export TF_CUDA_PATHS="$CUDA_PATH"
    fi
fi

export BAZEL_SRCS=$(pwd -P)/bazel-tensorflow-core-api
export BAZEL_BIN=$(pwd -P)/bazel-bin
export TENSORFLOW_BIN=$BAZEL_BIN/external/org_tensorflow/tensorflow

# Normalize some paths with symbolic links
echo "Normalizing paths"
TENSORFLOW_SO=($TENSORFLOW_BIN/libtensorflow_cc.so.?.??.?)
TENSORFLOW_FRMK_SO=($TENSORFLOW_BIN/libtensorflow_framework.so.?.??.?)
if [[ -f $TENSORFLOW_SO ]]; then
    export TENSORFLOW_LIB=$TENSORFLOW_SO
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow_cc.so
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow_cc.so.2
    ln -sf $(basename $TENSORFLOW_FRMK_SO) $TENSORFLOW_BIN/libtensorflow_framework.so
    ln -sf $(basename $TENSORFLOW_FRMK_SO) $TENSORFLOW_BIN/libtensorflow_framework.so.2
fi
TENSORFLOW_DYLIB=($TENSORFLOW_BIN/libtensorflow_cc.?.??.?.dylib)
TENSORFLOW_FRMK_DYLIB=($TENSORFLOW_BIN/libtensorflow_framework.?.??.?.dylib)
if [[ -f $TENSORFLOW_DYLIB ]]; then
    export TENSORFLOW_LIB=$TENSORFLOW_DYLIB
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow_cc.dylib
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow_cc.2.dylib
    ln -sf $(basename $TENSORFLOW_FRMK_DYLIB) $TENSORFLOW_BIN/libtensorflow_framework.dylib
    ln -sf $(basename $TENSORFLOW_FRMK_DYLIB) $TENSORFLOW_BIN/libtensorflow_framework.2.dylib
fi
TENSORFLOW_DLLS=($TENSORFLOW_BIN/tensorflow_cc.dll.if.lib $TENSORFLOW_BIN/libtensorflow_cc.dll.ifso)
for TENSORFLOW_DLL in ${TENSORFLOW_DLLS[@]}; do
    if [[ -f $TENSORFLOW_DLL ]]; then
        export TENSORFLOW_LIB=$TENSORFLOW_BIN/tensorflow_cc.dll
        ln -sf $(basename $TENSORFLOW_DLL) $TENSORFLOW_BIN/tensorflow_cc.lib
    fi
done
echo "Listing $TENSORFLOW_BIN:" && ls -l $TENSORFLOW_BIN

if [[ -x /usr/bin/install_name_tool ]] && [[ -e $BAZEL_BIN/external/llvm_openmp/libiomp5.dylib ]]; then
   # Fix library with correct rpath on Mac
   chmod +w $BAZEL_BIN/external/llvm_openmp/libiomp5.dylib $TENSORFLOW_BIN/libtensorflow_cc.2.dylib $TENSORFLOW_BIN/libtensorflow_framework.2.dylib
   UGLYPATH=$(otool -L $TENSORFLOW_BIN/libtensorflow_cc.2.dylib | grep @loader_path | cut -f1 -d ' ')
   echo $UGLYPATH
   install_name_tool -add_rpath @loader_path/. -id @rpath/libiomp5.dylib $BAZEL_BIN/external/llvm_openmp/libiomp5.dylib
   install_name_tool -change $UGLYPATH @rpath/libiomp5.dylib $TENSORFLOW_BIN/libtensorflow_cc.2.dylib
   install_name_tool -change $UGLYPATH @rpath/libiomp5.dylib $TENSORFLOW_BIN/libtensorflow_framework.2.dylib
fi

echo "Rebuilding generated source directories"
GEN_SRCS_DIR=src/gen/java
rm -rf $GEN_SRCS_DIR
mkdir -p $GEN_SRCS_DIR

GEN_RESOURCE_DIR=src/gen/resources
rm -rf $GEN_RESOURCE_DIR
mkdir -p $GEN_RESOURCE_DIR

# Copy generated Java protos from source jars
echo "Copying generated protos"
cd $GEN_SRCS_DIR
find $TENSORFLOW_BIN -name \*-speed-src.jar -exec jar xf {} \;
rm -rf META-INF

# Export op defs
echo "Exporting Ops"
$BAZEL_BIN/java_op_exporter \
    $GEN_RESOURCE_DIR/ops.pb \
    $GEN_RESOURCE_DIR/ops.pbtxt \
    $BAZEL_SRCS/external/org_tensorflow/tensorflow/core/api_def/base_api \
    src/bazel/api_def
