#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/ac/21/48cd18bfd214042ecb7392e01e016d3dd60b4f1ef13f10954ab472cd3c13/tensorflow_cpu-2.21.0-cp313-cp313-manylinux_2_27_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/86/6c/10d075ffc09754c7f10e749ba3c9d46dd809fb007990c7f788128044180c/tensorflow-2.21.0-cp313-cp313-manylinux_2_27_x86_64.whl'
    ;;
  'linux-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/72/72/343b86b4c9bfe28e81f749439f908c1e26aeac73d9f12b8dcdb996eb8ecb/tensorflow-2.21.0-cp313-cp313-manylinux_2_27_aarch64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/40/09/268b45a61be2bce136dabf3a3cd7099c8a984ae398198f71920b4c60c502/tensorflow-2.21.0-cp313-cp313-macosx_12_0_arm64.whl'
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
elif [[ "$PLATFORM" =~ "macosx" ]]; then
  ln -fs libtensorflow_cc.2.dylib libtensorflow_cc.dylib
  ln -fs libtensorflow_framework.2.dylib libtensorflow_framework.dylib
fi
ls -l .
