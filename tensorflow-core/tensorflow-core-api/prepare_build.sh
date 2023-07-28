#!/bin/bash
set -eu

PYTHON_WHEEL_URL="$1"
TARGET_FOLDER="$2"
DOWNLOAD_FOLDER="downloads"
DOWNLOADED_FILE="tensorflow.zip" # TODO extract name from wheel url, change .whl to .zip

mkdir -p $TARGET_FOLDER
mkdir -p $DOWNLOAD_FOLDER

if [ ! -f $DOWNLOAD_FOLDER/$DOWNLOADED_FILE ]; then
  curl -L $PYTHON_WHEEL_URL --output-dir $DOWNLOAD_FOLDER --output $DOWNLOADED_FILE
fi

unzip -u -d $DOWNLOAD_FOLDER $DOWNLOAD_FOLDER/$DOWNLOADED_FILE

# TODO Per OS logic
mv -f $DOWNLOAD_FOLDER/tensorflow/libtensorflow_cc.2.dylib $DOWNLOAD_FOLDER/tensorflow/libtensorflow_framework.2.dylib $TARGET_FOLDER
ln -s $TARGET_FOLDER/libtensorflow_cc.2.dylib $TARGET_FOLDER/libtensorflow_cc.dylib
ln -s $TARGET_FOLDER/libtensorflow_framework.2.dylib $TARGET_FOLDER/libtensorflow_framework.dylib
