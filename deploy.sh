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
IN_CONTAINER="${IN_CONTAINER:-false}"

if [[ "${DEPLOY_BINTRAY}" != "true" && "${DEPLOY_OSSRH}" != "true" ]]; then
  echo "Must deploy to at least one of Bintray, OSSRH or local" >&2
  exit 2
fi

MVN_OPTIONS="-B -e --settings ${PWD}/settings.xml -Pdeploying"

#
# Clean the working directory before doing anything
#
echo "Cleaning working directory..."
mvn clean $MVN_OPTIONS -q -U

#
# Extract deployed version from POM
#
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
echo

#
# Validate the environment based on if we are deploying a snapshot or a release version
#
NATIVE_ARTIFACTS_REPO=ossrh
if ! [[ $TF_VERSION = *-SNAPSHOT ]]; then
  if [[ "${IN_CONTAINER}" != "true" ]]; then
    echo "Release must be executed from a Docker container, please make sure to invoke release.sh"
    exit 1
  fi
 if [[ -z "${STAGING_SEQ}" ]]; then
    echo "Staging sequence is required for release"
    exit 1
  fi

  # In release mode, fetch the native artifacts from the staging repository created by the CI build
  MVN_OPTIONS="$MVN_OPTIONS -Preleasing -DstagingRepositoryId=orgtensorflow-${STAGING_SEQ}"
  NATIVE_ARTIFACTS_REPO=ossrh-staging

  # gnupg2 is required for signing releases
  apt-get -qq update
  apt-get -qq install -y gnupg2
fi

#
# Copy tensorflow-core-api dependencies for each supported platforms to our local maven tree,
# so retrieve the native artifacts that have been build and uploaded by the build servers
#
echo "Downloading native artifacts from Maven repository..."
for p in `find tensorflow-core -name tensorflow-core-platform* -type d -exec basename {} \;`; do
  if [[ $p =~ tensorflow-core-platform(.*) ]]; then
    # Remember each of our platform extension, we will it that when deploying the artifacts
    PLATFORM_EXT=${BASH_REMATCH[1]}
    if [[ -n $PLATFORM_EXT ]]; then
      [[ -n $PLATFORM_EXTS ]] && PLATFORM_EXTS="$PLATFORM_EXTS $PLATFORM_EXT" || PLATFORM_EXTS=$PLATFORM_EXT
    fi
    mvn dependency:copy-dependencies $MVN_OPTIONS -q -P$NATIVE_ARTIFACTS_REPO \
        -Djavacpp.platform.extension=$PLATFORM_EXT -DincludeArtifactIds=tensorflow-core-api \
        -DoutputDirectory=../../tensorflow-core/tensorflow-core-api/target -pl tensorflow-core/$p
  fi
done

#
# Feed the FILES,TYPES and CLASSIFIERS variables for the maven-deploy-plugin with our native artifacts
# so that tensorflow-core-api can be deployed as a bundle
#
for f in tensorflow-core/tensorflow-core-api/target/tensorflow-core-api-$TF_VERSION-*.jar; do
  echo "Found native artifact: $f"
  if [[ $f =~ tensorflow-core-api-$TF_VERSION-(.*).jar ]]; then
    [[ -n $FILES ]] && FILES=$FILES,$f || FILES=$f
    [[ -n $TYPES ]] && TYPES=$TYPES,jar || TYPES=jar
    [[ -n $CLASSIFIERS ]] && CLASSIFIERS=$CLASSIFIERS,${BASH_REMATCH[1]} || CLASSIFIERS=${BASH_REMATCH[1]}
  fi
done

MVN_DEPLOY_OPTIONS="$MVN_OPTIONS -Dtensorflow.core.api.files=$FILES -Dtensorflow.core.api.types=$TYPES -Dtensorflow.core.api.classifiers=$CLASSIFIERS"

#
# Build and deploy the artifacts on OSSRH and/or Bintray
# We need to do it manually for all non-default platforms, as they are not automatically included as
# modules in the POM and depends on the javacpp.platform.extension property
#
mvn_all_platforms() {
  local options="$1"
  mvn $options
  for p in $PLATFORM_EXTS; do
    mvn $options -Djavacpp.platform.extension=$p -pl tensorflow-core/tensorflow-core-platform$p
  done
}

if [[ "${DEPLOY_OSSRH}" == "true" ]]; then
  mvn_all_platforms "deploy $MVN_DEPLOY_OPTIONS -Possrh"
fi
# Deploy artifacts to bintray if requested.
if [[ "${DEPLOY_BINTRAY}" == "true" ]]; then
  mvn_all_platforms "deploy $MVN_DEPLOY_OPTIONS -Pbintray"
fi

echo
if [[ $TF_VERSION = *-SNAPSHOT ]]; then
  echo "Uploaded to snapshot repository"
else
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
fi
echo
