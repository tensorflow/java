#!/bin/bash
set -e

DOWNLOAD_FOLDER="$1"

case ${PLATFORM:-} in
  'linux-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/aa/1d/032a9d40762895e51cad06f382135c14d16487a0ad9dcc65aae5bd89c968/tensorflow_cpu-2.18.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-x86_64-gpu')
    WHEEL_URL='https://files.pythonhosted.org/packages/84/76/c55967ac9968ddaede25a4dce37aba37e9030656f02c12676151ce1b6f22/tensorflow-2.18.0-cp311-cp311-manylinux_2_17_x86_64.manylinux2014_x86_64.whl'
    ;;
  'linux-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/56/e4/55aaac2b15af4dad079e5af329a79d961e5206589d0e02b1e8da221472ed/tensorflow-2.18.0-cp312-cp312-manylinux_2_17_aarch64.manylinux2014_aarch64.whl'
    ;;
  'macosx-arm64')
    WHEEL_URL='https://files.pythonhosted.org/packages/26/08/556c4159675c1a30e077ec2a942eeeb81b457cc35c247a5b4a59a1274f05/tensorflow-2.18.0-cp311-cp311-macosx_12_0_arm64.whl'
    ;;
  'windows-x86_64')
    WHEEL_URL='https://files.pythonhosted.org/packages/76/ad/fa6c508a15ff79cb5409294c293388e0999b7d480f84b65e4287277434fe/tensorflow_intel-2.18.0-cp311-cp311-win_amd64.whl'
    CLIB_URL='https://storage.googleapis.com/tensorflow/versions/2.18.0/libtensorflow-cpu-windows-x86_64.zip'
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
    ln -fs libomp-e9212f90.so.5 libomp-e9212f90.so
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
