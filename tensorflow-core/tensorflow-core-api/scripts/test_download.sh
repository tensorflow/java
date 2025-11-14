#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/5a/e2/3efb758e284f2701429e1afc90293494fa3be7eac93fdc96de6378b21831/tensorflow_text-2.19.0-cp312-cp312-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/21/23/70683698d751e08cf1ab70d6f31a39034a2a9a494c1ec42d301cba3d8287/tensorflow_text-2.19.0-cp312-cp312-manylinux_2_17_aarch64.manylinux2014_aarch64.whl'
    ;;
  'macosx-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/aa/d7/417877fddb215d8a99ae636946f96b19f6e2ceedcd7eb49be985baae3662/tensorflow_text-2.19.0-cp312-cp312-macosx_11_0_arm64.whl'
    ;;
  *)
    echo "TensorFlow Text distribution for ${PLATFORM} is not supported for download"
    exit 0;
esac

mkdir -p "$DOWNLOAD_FOLDER"
cd "$DOWNLOAD_FOLDER"

if [[ -n "$TEXT_WHEEL_URL" ]]; then
  echo "Downloading $TEXT_WHEEL_URL"
  if [ ! -f 'tensorflow-text.whl' ]; then
    curl -L $TEXT_WHEEL_URL --output 'tensorflow-text.whl'
  fi
  yes | unzip -q -u 'tensorflow-text.whl' # use 'yes' because for some reasons -u does not work on Windows
fi

ls -l .
