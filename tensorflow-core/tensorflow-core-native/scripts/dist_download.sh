#!/bin/bash
set -eu

DOWNLOAD_FOLDER="$1"
DOWNLOADED_FILE="tensorflow.zip" # TODO extract name from wheel url, change .whl to .zip

case ${PLATFORM:-} in
  'linux-x86_64')
    DOWNLOAD_URL='https://files.pythonhosted.org/packages/57/bf/03debeeaeca996543478db89c8729a40dcb97a6be811804f4a08c8e1c75a/tensorflow_cpu-2.14.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    DOWNLOAD_URL='https://files.pythonhosted.org/packages/09/63/25e76075081ea98ec48f23929cefee58be0b42212e38074a9ec5c19e838c/tensorflow-2.14.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    DOWNLOAD_URL='https://files.pythonhosted.org/packages/ad/e2/ff29a5e514ec25470b59e5d0134f08f75bd28d6ec6941a22bea6b78cb2fb/tensorflow_cpu-2.14.0-cp311-cp311-macosx_10_15_x86_64.whl'
    ;;
  'macosx-arm64')
    DOWNLOAD_URL='https://files.pythonhosted.org/packages/d3/4b/ae9037ea22ba94eb2cf267e991384c3444f3e6142fa49923352b4ab73e14/tensorflow_macos-2.14.0-cp311-cp311-macosx_12_0_arm64.whl'
    ;;
  'windows-x86_64')
    DOWNLOAD_URL='https://storage.googleapis.com/tensorflow/libtensorflow/libtensorflow-cpu-windows-x86_64-2.14.0.zip'
    ;;
  *)
    echo "TensorFlow distribution for ${PLATFORM} is not supported for download"
    exit 1;
esac

mkdir -p "$DOWNLOAD_FOLDER"
cd "$DOWNLOAD_FOLDER"

if [ ! -f $DOWNLOADED_FILE ]; then
  curl -L $DOWNLOAD_URL --output $DOWNLOADED_FILE
fi
unzip -q -u $DOWNLOADED_FILE

mkdir -p tensorflow
cd tensorflow
if [[ "$PLATFORM" =~ "linux" ]]; then
  ln -fs libtensorflow_cc.so.2 libtensorflow_cc.so
  ln -fs libtensorflow_framework.so.2 libtensorflow_framework.so
elif [[ "$PLATFORM" =~ "macosx" ]]; then
  ln -fs libtensorflow_cc.2.dylib libtensorflow_cc.dylib
  ln -fs libtensorflow_framework.2.dylib libtensorflow_framework.dylib
elif [[ "$PLATFORM" =~ "windows" ]]; then
  ln -fs ../lib/tensorflow.dll tensorflow.dll
fi
ls -l .