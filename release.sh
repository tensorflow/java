#!/usr/bin/env bash
# Copyright 2017 The TensorFlow Authors. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ==============================================================================
#
# Script to upload release artifacts for the TensorFlow Java library to
# Maven Central. See RELEASE.md for an explanation.

cd $(dirname "$0")
SETTINGS_XML="$1"
shift
shift
CMD="$*"

if [[ -z "${SETTINGS_XML}" ]]
then
  SETTINGS_XML="$HOME/.m2/settings.xml"
fi

if [[ ! -f "${SETTINGS_XML}" ]]
then
  echo "No settings.xml (containing credentials for upload) found"
  exit 1
fi

if [[ -z "${CMD}" ]]
then
  CMD="bash deploy.sh"
fi

export GPG_TTY=$(tty)
set -ex

docker run \
  -e IN_CONTAINER="true" \
  -e DEPLOY_OSSRH="${DEPLOY_OSSRH:-true}" \
  -e DEPLOY_BINTRAY="${DEPLOY_BINTRAY:-false}" \
  -e DEPLOY_LOCAL="${DEPLOY_LOCAL:-false}" \
  -e GPG_TTY="${GPG_TTY}" \
  -v ${PWD}:/tensorflow-java \
  -v "${SETTINGS_XML}":/root/.m2/settings.xml \
  -v ${HOME}/.gnupg:/root/.gnupg \
  -w /tensorflow-java \
  -it \
  maven:3.6.3-jdk-8  \
  ${CMD}
