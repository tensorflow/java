#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/c3/e6/cfd784298ffb759a4235721cac2ac20f7ff758bf687069cfbaebb06c5804/tensorflow_text-2.20.0-cp312-cp312-manylinux2014_x86_64.manylinux_2_17_x86_64.whl'
    ;;
  'linux-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/f5/ca/796cfd97ae6693d3c84c37575a6d481be5f1ef36c920d1a73c884f31797b/tensorflow_text-2.20.0-cp312-cp312-manylinux2014_aarch64.manylinux_2_17_aarch64.whl'
    ;;
  'macosx-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/98/e4/e3c72d0a73caeba90cf5b31e69d44e9a08d614e0e829484d813f3b63e037/tensorflow_text-2.20.0-cp312-cp312-macosx_11_0_arm64.whl'
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
