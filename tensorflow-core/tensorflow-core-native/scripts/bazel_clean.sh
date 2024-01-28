#!/bin/bash
set -eu
source $(pwd -P)/scripts/bazel_common.sh
${BAZEL_CMD:=bazel} clean
