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
# This script is intended to be run inside a docker container to provide a
# hermetic process. See release.sh for the expected invocation.

set -e

STAGING_S3_BUCKET="s3://tensorflow-java/staging/"

# By default we deploy to both ossrh and bintray. These two
# environment variables can be set to skip either repository.
DEPLOY_BINTRAY="${DEPLOY_BINTRAY:-true}"
DEPLOY_OSSRH="${DEPLOY_OSSRH:-true}"
DEPLOY_LOCAL="${DEPLOY_LOCAL:-false}"

if [[ "${DEPLOY_BINTRAY}" != "true" && "${DEPLOY_OSSRH}" != "true" && "${DEPLOY_LOCAL}" != "true" ]]; then
  echo "Must deploy to at least one of Bintray, OSSRH or local" >&2
  exit 2
fi

clean() {
  echo "Cleaning working directory"
  # Clean up any existing artifacts
  mvn -q clean -Prelease
}

# Fetch a property from pom files for a given profile.
# Arguments:
#   property - name of the property to be retrieved.
#   profile - name of the selected profile.
# Output:
#   Echo property value to stdout
mvn_property() {
  local prop="$1"
  local profile="$2"
  mvn -q --non-recursive exec:exec -P "${profile}" -Dexec.executable='echo' -Dexec.args="\${${prop}}"
}

# Clean the working directory before doing anything else
clean

# Extract the TensorFlow version we are releasing from pom
TF_VERSION=`mvn_property "project.version"`
echo
echo "Starting release of TensorFlow Java, version ${TF_VERSION}"

# Download native artifacts that were built and uploaded by the build system for each supported
# platform.
# The goal is to drop these artifacts under the `tensorflow-core-api` build directory so that they
# can get signed and deployed with all the other artifacts.
download_native_artifacts() {
  echo "Downloading native artifacts from ${STAGING_S3_BUCKET}"
  aws s3 cp "${STAGING_S3_BUCKET}" "${DIR}/tensorflow-core/tensorflow-core-api/target/" \
      --recursive --include "*-${TF_VERSION}*.jar" --no-sign-request
}

# Try to build and deploy.
# If successfully deployed, clean.
# If deployment fails, debug with
#   ./release.sh ${TF_VERSION} ${SETTINGS_XML} bash
# To get a shell to poke around the maven artifacts with.
build_and_deploy_artifacts() {
  # Deploy artifacts to local maven repository if requested
  if [[ "${DEPLOY_LOCAL}" == "true" ]]; then
    mvn install -Prelease
  fi
  # Deploy artifacts to ossrh if requested.
  if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
    mvn deploy -Prelease -Possrh
  fi
  # Deploy artifacts to bintray if requested.
  if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
    mvn deploy -Prelease -Pbintray
  fi
  # Clean up when everything works
  # clean
}

if [ -z "${TF_VERSION}" ]
then
  echo "Must set the TF_VERSION environment variable"
  exit 1
fi

echo "Deploying on"
if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
  echo "* OSSRH"
fi
if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
  echo "* Bintray"
fi
if [[ "${DEPLOY_LOCAL}" == "true" ]]; then
  echo "* Local"
fi

DIR="$(realpath $(dirname $0))"
cd "${DIR}"

# The meat of the script.
# Comment lines out appropriately if debugging/tinkering with the release
# process.
# gnupg2 is required for signing
apt-get -qq update
apt-get -qq install -y gnupg2
apt-get -qq install -y awscli

# Download native artifacts already assembled and uploaded by the build servers
download_native_artifacts

# Build other artifacts and push them all to the repositories
build_and_deploy_artifacts

echo "Uploaded to the staging repository"
echo "After validating the release: "
if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
  echo "* Login to https://oss.sonatype.org/#stagingRepositories"
  echo "* Find the 'org.tensorflow' staging release and click either 'Release' to release or 'Drop' to abort"
fi
if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
  echo "* Login to https://bintray.com/google/tensorflow/tensorflow"
  echo "* Either 'Publish' unpublished items to release, or 'Discard' to abort"
fi
