#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/c5/ef/5b8270e5665923bda4222f56382d9fbcb7fd6efd5fb8557ad0776848cdff/tensorflow_text-2.16.1-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/ed/5d/b55f48cdf98a164d293f660748c2501ea828e148250a4cadbb5b0d988735/tensorflow_text-2.16.1-cp311-cp311-macosx_10_9_x86_64.whl'
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
