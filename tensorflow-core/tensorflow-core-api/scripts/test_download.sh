#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/f3/73/3a906feb0d71d9353c6fb2363d4052856cc6eff5a78a097b1a6002d4e908/tensorflow_text-2.18.1-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/8a/9a/ebba9f6274f8b51e5fe1ac2411b8b6bf680a32d10bd6e9c54be1faeec062/tensorflow_text-2.18.1-cp311-cp311-manylinux_2_17_aarch64.manylinux2014_aarch64.whl'
    ;;
  'macosx-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/18/b6/8ad233edb0732847db1da538cea941dcccc42f59304ff6fb449676e6dd5a/tensorflow_text-2.18.1-cp311-cp311-macosx_11_0_arm64.whl'
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
