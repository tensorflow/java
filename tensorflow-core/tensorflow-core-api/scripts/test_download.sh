#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/20/a0/bdbf2a11141f1c93e572364d13c42537cfe811b747a0bbb58fdd904f3960/tensorflow_text-2.15.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/8a/fe/a2f19d3d3ab834c3fa1007c970b0b86573beb929c86ca6c85cd13e86e4b2/tensorflow_text-2.15.0-cp311-cp311-macosx_10_9_x86_64.whl'
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
