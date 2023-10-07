#!/bin/bash
if [[ "${BAZEL_RUN:-true}" == "true" ]]; then
  ${BAZEL_CMD:=bazel} clean
fi
rm -rf src/gen