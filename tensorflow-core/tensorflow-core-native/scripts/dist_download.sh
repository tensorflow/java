#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/c6/d9/f2ff325194b8e8acb6b69f303c838b0486f41b8028ec42261f2eb037a031/tensorflow_cpu-2.16.2-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/43/dd/8f03331107b76e63313d2089ddfbd13f15e51fb8ed73517cdd0ab3341928/tensorflow-2.16.2-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/6d/69/9999c2d9e8a3b08dfcfc7e9259a05fb1da5f700936091d2eb4a7985c2776/tensorflow-2.16.2-cp311-cp311-macosx_10_15_x86_64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/9d/72/6f09443493b9df2fd8a9585c9af4d9453762906a8e5735a8a5efa6e3d1e3/tensorflow-2.16.2-cp311-cp311-macosx_12_0_arm64.whl'
    ;;
  'windows-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/46/87/c3e4e9fe7c630f38a6984afdd1d4ed531ef9c74dc66b86f46f6bdd89d608/tensorflow_intel-2.16.2-cp311-cp311-win_amd64.whl'
    CLIB_URL='https://storage.googleapis.com/tensorflow/versions/2.16.2/libtensorflow-cpu-windows-x86_64.zip'
    ;;
  'linux-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/b5/01/c03e98c8e97d151d9ce075fae210f838832eb53d8aa55669d384cb72925b/tensorflow-2.16.2-cp311-cp311-manylinux_2_17_aarch64.manylinux2014_aarch64.whl'
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
    ln -fs libomp-*.so.5 libomp.so
  fi
elif [[ "$PLATFORM" =~ "macosx" ]]; then
  ln -fs libtensorflow_cc.2.dylib libtensorflow_cc.dylib
  ln -fs libtensorflow_framework.2.dylib libtensorflow_framework.dylib
elif [[ "$PLATFORM" =~ "windows" ]]; then
  # FIXME Looks like tsl headers are only present under the tensorflow folder for the windows build
  # (while it is also available at the root of the include folder for other platforms)
  cd include && ln -fs tensorflow/tsl tsl && cd -
fi
ls -l .