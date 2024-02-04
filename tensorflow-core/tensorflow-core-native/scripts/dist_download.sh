#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/fa/44/a1698c62942d20cab378ba201a6cbfcce579418351a0c6e4ea9d66c9adf2/tensorflow_cpu-2.15.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/93/c0/a774286d0383419f558deb27096e5de9f9facd6c27df8e9f9af6fba2f77e/tensorflow-2.15.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'macosx-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/92/2d/880fcd65e4414b05088193e6f2cfb86fdf90003dd2dd0f4d1bc465348f0e/tensorflow-2.15.0-cp311-cp311-macosx_10_15_x86_64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/eb/9f/0759e2fea4a3c48f070b64811c2c57036b46353ba87263afc810b8f4188a/tensorflow_macos-2.15.0-cp311-cp311-macosx_12_0_arm64.whl'
    ;;
  'windows-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/4c/48/1a5a15517f18eaa4ff8d598b1c000300b20c1bb0e624539d702117a0c369/tensorflow_intel-2.15.0-cp311-cp311-win_amd64.whl'
    #CLIB_URL='https://storage.googleapis.com/tensorflow/libtensorflow/libtensorflow-cpu-windows-x86_64-2.15.0.zip'
    CLIB_URL='https://storage.googleapis.com/libtensorflow-nightly/prod/tensorflow/release/windows/latest/cpu/windows_cpu_libtensorflow_binaries.tar.gz'
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
#  if [ ! -f 'tensorflow_c.zip' ]; then
#    curl -L $CLIB_URL --output 'tensorflow_c.zip'
#  fi
#  yes | unzip -q -u -d tensorflow 'tensorflow_c.zip'

  # FIXME Temporary experiment to validate windows build from nightly snapshot
  if [ ! -f 'tensorflow_c.tar.gz' ]; then
    curl -L $CLIB_URL --output 'tensorflow_c.tar.gz'
  fi
  yes | tar xzvf 'tensorflow_c.tar.gz'
  yes | unzip -q -u -d tensorflow 'lib_package/libtensorflow-cpu-windows-x86_64.zip'
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