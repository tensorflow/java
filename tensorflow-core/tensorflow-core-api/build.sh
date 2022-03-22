#!/bin/bash
# Script to build native TensorFlow libraries
set -eu

# Allows us to use ccache with Bazel on Mac
export BAZEL_USE_CPP_ONLY_TOOLCHAIN=1

export BAZEL_VC="${VCINSTALLDIR:-}"
if [[ -d $BAZEL_VC ]]; then
    export BAZEL_BUILD="--output_user_root=$(cygpath -w $TMP) build"
    export BUILD_FLAGS="--copt=//arch:AVX `#--copt=//arch:AVX2` --define=override_eigen_strong_inline=true"
    export PYTHON_BIN_PATH=$(which python.exe)
else
    export BAZEL_BUILD="build"
    export BUILD_FLAGS="--copt=-msse4.1 --copt=-msse4.2 --copt=-mavx `#--copt=-mavx2 --copt=-mfma` --linkopt=-lstdc++ --host_linkopt=-lstdc++"
    export PYTHON_BIN_PATH=$(which python3)
fi

if [[ "${EXTENSION:-}" == *mkl* ]]; then
    export BUILD_FLAGS="$BUILD_FLAGS --config=mkl"
fi

if [[ "${EXTENSION:-}" == *gpu* ]]; then
    export BUILD_FLAGS="$BUILD_FLAGS --config=cuda"
    export TF_CUDA_COMPUTE_CAPABILITIES="${TF_CUDA_COMPUTE_CAPABILITIES:-"sm_35,sm_50,sm_60,sm_70,sm_75,compute_80"}"
    if [[ -z ${TF_CUDA_PATHS:-} ]] && [[ -d ${CUDA_PATH:-} ]]; then
        # Work around some issue with Bazel preventing it from detecting CUDA on Windows
        export TF_CUDA_PATHS="$CUDA_PATH"
    fi
fi

BUILD_FLAGS="$BUILD_FLAGS --experimental_repo_remote_exec --python_path="$PYTHON_BIN_PATH" --output_filter=DONT_MATCH_ANYTHING --verbose_failures"

# Always allow distinct host configuration since we rely on the host JVM for a few things (this was disabled by default on windows)
BUILD_FLAGS="$BUILD_FLAGS --distinct_host_configuration=true"

# Build C/C++ API of TensorFlow itself including a target to generate ops for Java
bazel --bazelrc=tensorflow.bazelrc $BAZEL_BUILD $BUILD_FLAGS ${BUILD_USER_FLAGS:-} \
    @org_tensorflow//tensorflow:tensorflow_cc \
    @org_tensorflow//tensorflow/tools/lib_package:jnilicenses_generate \
    :java_proto_gen_sources \
    :java_op_exporter \
    :java_api_import \
    :custom_ops_test

export BAZEL_SRCS=$(pwd -P)/bazel-tensorflow-core-api
export BAZEL_BIN=$(pwd -P)/bazel-bin
export TENSORFLOW_BIN=$BAZEL_BIN/external/org_tensorflow/tensorflow

# Normalize some paths with symbolic links
TENSORFLOW_SO=($TENSORFLOW_BIN/libtensorflow_cc.so.?.?.?)
if [[ -f $TENSORFLOW_SO ]]; then
    export TENSORFLOW_LIB=$TENSORFLOW_SO
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow_cc.so
    ln -sf $(basename $TENSORFLOW_SO) $TENSORFLOW_BIN/libtensorflow_cc.so.2
fi
TENSORFLOW_DYLIB=($TENSORFLOW_BIN/libtensorflow_cc.?.?.?.dylib)
if [[ -f $TENSORFLOW_DYLIB ]]; then
    export TENSORFLOW_LIB=$TENSORFLOW_DYLIB
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow_cc.dylib
    ln -sf $(basename $TENSORFLOW_DYLIB) $TENSORFLOW_BIN/libtensorflow_cc.2.dylib
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

GEN_SRCS_DIR=src/gen/java
mkdir -p $GEN_SRCS_DIR

GEN_RESOURCE_DIR=src/gen/resources
mkdir -p $GEN_RESOURCE_DIR

if [[ -z "${SKIP_EXPORT:-}" ]]; then
  # Export op defs
  echo "Exporting Ops"
  $BAZEL_BIN/java_op_exporter \
      $TENSORFLOW_LIB \
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
