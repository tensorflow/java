#!/bin/bash
set -eu

PYTHON_WHEEL_URL="$1"
DOWNLOAD_FOLDER="$2"
DOWNLOADED_FILE="tensorflow.zip" # TODO extract name from wheel url, change .whl to .zip
OS_NAME="$3"

mkdir -p $DOWNLOAD_FOLDER

if [ ! -f $DOWNLOAD_FOLDER/$DOWNLOADED_FILE ]; then
  curl -L $PYTHON_WHEEL_URL --output-dir $DOWNLOAD_FOLDER --output $DOWNLOADED_FILE
fi

unzip -q -u -d $DOWNLOAD_FOLDER $DOWNLOAD_FOLDER/$DOWNLOADED_FILE

if [ "$OS_NAME" = "linux" ]; then
  ln -fs $DOWNLOAD_FOLDER/tensorflow/libtensorflow_cc.so.2 $DOWNLOAD_FOLDER/tensorflow/libtensorflow_cc.so
  ln -fs $DOWNLOAD_FOLDER/tensorflow/libtensorflow_framework.so.2 $DOWNLOAD_FOLDER/tensorflow/libtensorflow_framework.so
elif [ "$OS_NAME" = "macosx" ]; then
  ln -fs $DOWNLOAD_FOLDER/tensorflow/libtensorflow_cc.2.dylib $DOWNLOAD_FOLDER/tensorflow/libtensorflow_cc.dylib
  ln -fs $DOWNLOAD_FOLDER/tensorflow/libtensorflow_framework.2.dylib $DOWNLOAD_FOLDER/tensorflow/libtensorflow_framework.dylib
elif [ "$OS_NAME" = "windows" ]; then
  cat "Windows unsupported";
  return 1;
else
  cat "Invalid OS $OS_NAME";
  return 1;
fi
