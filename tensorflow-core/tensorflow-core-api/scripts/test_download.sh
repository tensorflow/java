#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/43/dd/8f03331107b76e63313d2089ddfbd13f15e51fb8ed73517cdd0ab3341928/tensorflow-2.16.2-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/6d/69/9999c2d9e8a3b08dfcfc7e9259a05fb1da5f700936091d2eb4a7985c2776/tensorflow-2.16.2-cp311-cp311-macosx_10_15_x86_64.whl'
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
