#!/bin/bash
# Script to build native TensorFlow libraries
set -eu
source $(pwd -P)/scripts/bazel_common.sh

echo "Building TensorFlow with Bazel"
# Build C/C++ API of TensorFlow itself including a target to generate ops for Java
${BAZEL_CMD:=bazel} --bazelrc=tensorflow.bazelrc $BUILD_FLAGS ${BUILD_USER_FLAGS:-} \
    @org_tensorflow//tensorflow:tensorflow_cc

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
