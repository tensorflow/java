#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/a9/b9/02707723d44e5d0fe5f7d27ba4237528bc654bac1b0f638efe37988584b1/tensorflow_text-2.20.1-cp313-cp313-manylinux2014_x86_64.manylinux_2_17_x86_64.whl'
    ;;
  'linux-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/17/99/b397038628a660a1446bb79c8be284443a28500d072227a77ebaeb5cd149/tensorflow_text-2.20.1-cp313-cp313-manylinux2014_aarch64.manylinux_2_17_aarch64.whl'
    ;;
  'macosx-arm64')
    TEXT_WHEEL_URL='https://files.pythonhosted.org/packages/61/b8/ccb0c3b048f268860f4c92f6bb7ab55c6af6c2fe4e26746c8dc384063915/tensorflow_text-2.20.1-cp313-cp313-macosx_11_0_arm64.whl'
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
