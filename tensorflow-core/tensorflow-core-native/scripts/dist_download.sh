#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/1a/9e/594164db23e3e262da1a0e8983258811eff56e5af6b7b6da5eccccb8d4c7/tensorflow_cpu-2.20.0-cp313-cp313-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/43/fb/8be8547c128613d82a2b006004026d86ed0bd672e913029a98153af4ffab/tensorflow-2.20.0-cp313-cp313-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/ea/4c/c1aa90c5cc92e9f7f9c78421e121ef25bae7d378f8d1d4cbad46c6308836/tensorflow-2.20.0-cp313-cp313-manylinux_2_17_aarch64.manylinux2014_aarch64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/04/82/af283f402f8d1e9315644a331a5f0f326264c5d1de08262f3de5a5ade422/tensorflow-2.20.0-cp313-cp313-macosx_12_0_arm64.whl'
    ;;
  *)
    echo "TensorFlow distribution for ${PLATFORM} is not supported for download"
    exit 1;
esac

mkdir -p "$DOWNLOAD_FOLDER"
cd "$DOWNLOAD_FOLDER"

if [[ -n "$WHEEL_URL" ]]; then
  echo "Downloading $WHEEL_URL"
  if [ ! -f 'tensorflow.whl' ]; then
    curl -L $WHEEL_URL --output 'tensorflow.whl'
  fi
  yes | unzip -q -u 'tensorflow.whl' # use 'yes' because for some reasons -u does not work on Windows
  if [[ "$PLATFORM" == "linux-arm64" ]]; then
    cp $DOWNLOAD_FOLDER/tensorflow.libs/*  $DOWNLOAD_FOLDER/tensorflow/
  fi
fi

if [[ -n "$CLIB_URL" ]]; then
  echo "Downloading $CLIB_URL"
  if [ ! -f 'tensorflow_c.zip' ]; then
    curl -L $CLIB_URL --output 'tensorflow_c.zip'
  fi
  yes | unzip -q -u -d tensorflow 'tensorflow_c.zip'
fi

cd tensorflow
if [[ "$PLATFORM" =~ "linux" ]]; then
  ln -fs libtensorflow_cc.so.2 libtensorflow_cc.so
  ln -fs libtensorflow_framework.so.2 libtensorflow_framework.so
  if [[ "$PLATFORM" == "linux-arm64" ]]; then
    cp ../tensorflow.libs/libomp-f1025659.so.5 libomp-f1025659.so.5
    ln -fs libomp-f1025659.so.5 libomp-f1025659.so
  fi
elif [[ "$PLATFORM" =~ "macosx" ]]; then
  ln -fs libtensorflow_cc.2.dylib libtensorflow_cc.dylib
  ln -fs libtensorflow_framework.2.dylib libtensorflow_framework.dylib
fi
ls -l .
