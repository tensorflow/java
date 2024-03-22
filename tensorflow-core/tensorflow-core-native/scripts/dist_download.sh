#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/9b/0b/c18d6464a19d4c9b63df8880dd3ce0c67b5145ada9092f3ac67d82726566/tensorflow_cpu-2.16.1-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/58/70/e8ac764ec80810eefcbab0cb1d21dbba6cf26719c44cd6d9a5e9f0407935/tensorflow-2.16.1-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/b8/75/ce4d8eeb1fb100726634358411bc4a8b12f889f6ce560b0973c0a5dbac39/tensorflow-2.16.1-cp311-cp311-macosx_10_15_x86_64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/f9/14/67e9b2b2379cb530c0412123a674d045eca387dfcfa7db1c0028857b0a66/tensorflow-2.16.1-cp311-cp311-macosx_12_0_arm64.whl'
    ;;
  'windows-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/e0/36/6278e4e7e69a90c00e0f82944d8f2713dd85a69d1add455d9e50446837ab/tensorflow_intel-2.16.1-cp311-cp311-win_amd64.whl'
    CLIB_URL='https://storage.googleapis.com/tensorflow/versions/2.16.1/libtensorflow-cpu-windows-x86_64.zip'
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
elif [[ "$PLATFORM" =~ "windows" ]]; then
  # FIXME Looks like tsl headers are only present under the tensorflow folder for the windows build
  # (while it is also available at the root of the include folder for other platforms)
  cd include && ln -fs tensorflow/tsl tsl && cd -
fi
ls -l .