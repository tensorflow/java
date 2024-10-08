#!/usr/bin/env bash
# Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
# Maven Central. See RELEASE.md for explanation.

cd $(dirname "$0")
STAGING_SEQ="$1"
shift
shift
CMD="$*"

if [[ -z "${STAGING_SEQ}" ]]
then
  echo "Usage: ./release.sh <staging repository sequence number> [<cmd>]"
  exit 1
fi

# If release fails, debug with
#   ./release.sh ${STAGING_SEQ} bash
# To get a shell to poke around the maven artifacts with.
if [[ -z "${CMD}" ]]
then
  CMD="mvn clean deploy -B -e --settings ./settings.xml -Pdeploying -Preleasing -DstagingRepositoryId=orgtensorflow-${STAGING_SEQ}"
fi

export GPG_TTY=$(tty)
set -ex

if [[ ! -f settings.xml ]]
then
  cp -f ~/.m2/settings.xml .
fi

docker run \
  -e GPG_TTY="${GPG_TTY}" \
  -v ${PWD}:/tensorflow-java \
  -v ${HOME}/.gnupg:/root/.gnupg \
  -w /tensorflow-java \
  -it \
  --platform linux/amd64 \
  maven:3.8.6-jdk-11  \
  ${CMD}

echo
echo "Uploaded to the staging repository"
echo "After validating the release: "
echo "* Login to https://oss.sonatype.org/#stagingRepositories"
echo "* Find the 'org.tensorflow' staging release and click either 'Release' to release or 'Drop' to abort"
