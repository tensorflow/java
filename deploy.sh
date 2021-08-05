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
# This script is intended to be run inside a docker container to provide a
# hermetic process. See release.sh for the expected invocation.

set -e

IN_CONTAINER="${IN_CONTAINER:-false}"

MVN_OPTIONS="-B -e --settings ${PWD}/settings.xml -Pdeploying"

mvn_property() {
  local property="$1"
  mvn exec:exec $MVN_OPTIONS -q -N -Dexec.executable='echo' -Dexec.args="\${${property}}"
}

#
# Clean the working directory before doing anything
#
echo "Cleaning working directory..."
mvn clean $MVN_OPTIONS -q -U

#
# Extract deployed version from POM
#
echo "Parsing Maven configuration..."
TF_VERSION=`mvn_property "project.version"`
if [ -z "${TF_VERSION}" ]; then
  echo "Fail to extract TF_VERSION from POM"
  exit 1
fi

#
# Validate the environment based on if we are deploying a snapshot or a release version
#
echo
if [[ $TF_VERSION = *-SNAPSHOT ]]; then
  echo "===> Deploying TensorFlow Java, version ${TF_VERSION} <==="
  echo
  DEPLOY_FILE_GOAL=deploy:deploy-file
  DEPLOY_REPOSITORY_URL=`mvn_property "project.distributionManagement.snapshotRepository.url"`

else
  echo "===> Releasing TensorFlow Java, version ${TF_VERSION} <==="
  echo
  if [[ "${IN_CONTAINER}" != "true" ]]; then
    echo "Release must be executed from a Docker container, please make sure to invoke release.sh"
    exit 1
  fi
  if [[ -z "${STAGING_SEQ}" ]]; then
    echo "Staging sequence is required for release"
    exit 1
  fi
  DEPLOY_FILE_GOAL=gpg:sign-and-deploy-file
  DEPLOY_REPOSITORY_URL=`mvn_property "project.distributionManagement.repository.url"`

  MVN_OPTIONS="$MVN_OPTIONS -Preleasing -DstagingRepositoryId=orgtensorflow-${STAGING_SEQ}"

  apt-get -qq update && apt-get -qq install -y gnupg2
fi

#
# Copy tensorflow-core-api dependencies for each supported platforms to our local maven tree,
# so retrieve the native artifacts that have been build and uploaded by the build servers
#
echo "Downloading native artifacts from Maven repository..."
for p in `find tensorflow-core -name tensorflow-core-platform* -type d -exec basename {} \;`; do
  if [[ $p =~ tensorflow-core-platform(.*) ]]; then
    # Remember each of our platform extension, we will it that when deploying the artifacts
    # Note: Disable (temporarily?) MKL platforms for now, as their build is often broken
    PLATFORM_EXT=${BASH_REMATCH[1]}
    if [[ $PLATFORM_EXT != -mkl* ]]; then                                                                                            
        if [[ -n $PLATFORM_EXT ]]; then
          [[ -n $PLATFORM_EXTS ]] && PLATFORM_EXTS="$PLATFORM_EXTS $PLATFORM_EXT" || PLATFORM_EXTS=$PLATFORM_EXT
        fi
        mvn dependency:copy-dependencies $MVN_OPTIONS -q \
            -Djavacpp.platform.extension=$PLATFORM_EXT -DincludeArtifactIds=tensorflow-core-api \
            -DoutputDirectory=../../tensorflow-core/tensorflow-core-api/target -pl tensorflow-core/$p
    fi
  fi
done

#
# Feed the FILES,TYPES and CLASSIFIERS variables for the maven-deploy-plugin with our native artifacts
# so that tensorflow-core-api can be deployed as a bundle
#
for f in tensorflow-core/tensorflow-core-api/target/tensorflow-core-api-$TF_VERSION-*.jar; do
  echo "Found native artifact: $f"
  if [[ $f =~ tensorflow-core-api-$TF_VERSION-(.*).jar ]]; then
    [[ -n $NATIVE_FILES ]] && NATIVE_FILES=$NATIVE_FILES,$f || NATIVE_FILES=$f
    [[ -n $NATIVE_FILE_TYPES ]] && NATIVE_FILE_TYPES=$NATIVE_FILE_TYPES,jar || NATIVE_FILE_TYPES=jar
    [[ -n $NATIVE_CLASSIFIERS ]] && NATIVE_CLASSIFIERS=$NATIVE_CLASSIFIERS,${BASH_REMATCH[1]} || NATIVE_CLASSIFIERS=${BASH_REMATCH[1]}
  fi
done

#
# Build and deploy the artifacts on OSSRH
# We need to do it manually for all non-default platforms, as they are not automatically included as
# modules in the POM and depends on the javacpp.platform.extension property.
# Note that the tensorflow-core-api, which needs special care, won't be deployed yet, see below.
#
mvn deploy $MVN_OPTIONS
for p in $PLATFORM_EXTS; do
  mvn deploy $MVN_OPTIONS -Djavacpp.platform.extension=$p -pl tensorflow-core/tensorflow-core-platform$p
done

# Now deploy manually the tensorflow-core-api with all its native artifacts.
mvn $DEPLOY_FILE_GOAL $MVN_OPTIONS -pl tensorflow-core/tensorflow-core-api \
    -DgroupId=org.tensorflow -DartifactId=tensorflow-core-api -Dversion=$TF_VERSION -Dpackaging=jar \
    -Dfile=target/tensorflow-core-api-$TF_VERSION.jar \
    -Dfiles=$NATIVE_FILES -Dtypes=$NATIVE_FILE_TYPES -Dclassifiers=$NATIVE_CLASSIFIERS \
    -Dsources=target/tensorflow-core-api-$TF_VERSION-sources.jar \
    -Djavadoc=target/tensorflow-core-api-$TF_VERSION-javadoc.jar \
    -DpomFile=pom.xml \
    -DrepositoryId=ossrh -Durl=$DEPLOY_REPOSITORY_URL

echo
if [[ $TF_VERSION = *-SNAPSHOT ]]; then
  echo "Uploaded to snapshot repository"
else
  echo "Uploaded to the staging repository"
  echo "After validating the release: "
  echo "* Login to https://oss.sonatype.org/#stagingRepositories"
  echo "* Find the 'org.tensorflow' staging release and click either 'Release' to release or 'Drop' to abort"
fi
echo
