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

# By default we deploy to both ossrh and bintray. These two
# environment variables can be set to skip either repository.
DEPLOY_OSSRH="${DEPLOY_OSSRH:-true}"
DEPLOY_BINTRAY="${DEPLOY_BINTRAY:-false}"
DEPLOY_LOCAL="${DEPLOY_LOCAL:-false}"

IN_CONTAINER="${IN_CONTAINER:-false}"

if [[ "${DEPLOY_BINTRAY}" != "true" && "${DEPLOY_OSSRH}" != "true" && "${DEPLOY_LOCAL}" != "true" ]]; then
  echo "Must deploy to at least one of Bintray, OSSRH or local" >&2
  exit 2
fi

clean() {
  echo "Cleaning working directory..."
  # Clean up any existing artifacts
  mvn clean $MVN_OPTIONS -q
}

# Try to build and deploy.
# If successfully deployed, clean.
# If deployment fails, debug with
#   ./release.sh ${TF_VERSION} ${SETTINGS_XML} bash
# To get a shell to poke around the maven artifacts with.
build_and_deploy_artifacts() {
  # Deploy artifacts to local maven repository if requested
  if [[ "${DEPLOY_LOCAL}" == "true" ]]; then
    mvn install $MVN_OPTIONS
  fi
  # Deploy artifacts to ossrh if requested.
  if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
    mvn deploy $MVN_OPTIONS -Possrh
  fi
  # Deploy artifacts to bintray if requested.
  if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
    mvn deploy $MVN_OPTIONS -Pbintray
  fi
  # Clean up when everything works
  # clean
}

MVN_OPTIONS="-B -e -U --settings settings.xml -Pdeploy"

# Clean the working directory before doing anything else
clean

echo "Parsing Maven configuration..."
TF_VERSION=`mvn exec:exec $MVN_OPTIONS -q -N -Dexec.executable='echo' -Dexec.args="\\${project.version}"`
if [ -z "${TF_VERSION}" ]; then
  echo "Fail to extract TF_VERSION from POM"
  exit 1
fi
echo
echo "Deploying TensorFlow Java, version ${TF_VERSION}, on:"
if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
  echo "* OSSRH"
fi
if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
  echo "* Bintray"
fi
if [[ "${DEPLOY_LOCAL}" == "true" ]]; then
  echo "* Local"
fi
echo

if ! [[ $TF_VERSION = *-SNAPSHOT ]]; then
  if ! $IN_CONTAINER; then
    echo "Release must be executed from a Docker container, please make sure to invoke release.sh"
    exit 1
  fi
  MVN_OPTIONS="$MVN_OPTIONS -Prelease"
  # gnupg2 is required for signing releases
  apt-get -qq update
  apt-get -qq install -y gnupg2
fi

echo "Downloading native artifacts from Maven repository..."
for p in `find tensorflow-core -name tensorflow-core-platform* -type d -exec basename {} \;`; do
  mvn dependency:get $MVN_OPTIONS -N -U -q -Possrh -DgroupId=org.tensorflow -DartifactId=$p -Dversion=$TF_VERSION
done

mkdir -p tensorflow-core/tensorflow-core-api/target
for f in $HOME/.m2/repository/org/tensorflow/tensorflow-core-api/$TF_VERSION/tensorflow-core-api-$TF_VERSION-*.jar; do
  echo "Found native artifact: $f"
  cp $f tensorflow-core/tensorflow-core-api/target/
  if [[ $f =~ tensorflow-core-api-$TF_VERSION-(.*).jar ]]; then
    [[ -n $FILES ]] && FILES=$FILES,$f || FILES=$f
    [[ -n $TYPES ]] && TYPES=$TYPES,jar || TYPES=jar
    [[ -n $CLASSIFIERS ]] && CLASSIFIERS=$CLASSIFIERS,${BASH_REMATCH[1]} || CLASSIFIERS=${BASH_REMATCH[1]}
  fi
done

MVN_OPTIONS="$MVN_OPTIONS -Dtensorflow.core.api.files=$FILES -Dtensorflow.core.api.types=$TYPES -Dtensorflow.core.api.classifiers=$CLASSIFIERS"

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
