# Releasing TensorFlow Java

The
[TensorFlow Java API](https://github.com/tensorflow/java) is available on Maven Central 
through artifacts uploaded to
[Maven Central](https://central.sonatype.com/). This
document describes the process of updating the release artifacts. It does _not_ describe how to use
the artifacts, for which the reader is referred to the
[TensorFlow for Java installation instructions](https://github.com/tensorflow/java/blob/master/README.md).

## Background

TensorFlow source (which is primarily in C++) is built using
[bazel](https://bazel.build) and not [maven](https://maven.apache.org/). TensorFlow Java
wraps over this native code and thus depends on platform (OS, architecture) specific native code.

Hence, the process for building and uploading release artifacts is not a single `mvn deploy` command.

## Release process overview

The process of releasing TensorFlow Java is split in two major steps:
* Building and deploying the native artifacts
* Building and deploying all artifacts consolidated

The first step is executed on different build servers, each responsible to build the native
artifact for a specific architecture and platform. The second step retrieves all native artifacts 
built in the first step to consolidate them on a single server, and run a Maven `deploy` from there. 

It is important to note that any change pushed to a release branch (i.e. a branch prefixed
by `r`) will start a new release workflow. Therefore, these changes should always increment the
version number.

### Pre-requisites

-   An account at [Maven Central](https://central.sonatype.com/), that has
    permissions to update artifacts in the `org.tensorflow` group. If your
    account does not have permissions, then you'll need to ask someone who does
    to [file a ticket](mailto:central-support@sonatype.com) to add to the permissions.
- 
    Add your account username and token to the GitHub repository secrets as `CI_DEPLOY_USERNAME` and `CI_DEPLOY_PASSWORD` 
    respectively.
 
-   A [GPG signing key](http://central.sonatype.org/pages/apache-maven.html#gpg-signed-components) for signing the artifacts.
 
    The public key must be registered with a key server supported by Maven Central (e.g. https://keyserver.ubuntu.com/) and
    the private key + passphrase should be stored in the GitHub repository secrets as `MAVEN_GPG_PRIVATE_KEY` and 
    `MAVEN_GPG_PASSPHRASE` respectively. See [GitHub documentation](https://github.com/actions/setup-java/blob/main/docs/advanced-usage.md#Publishing-using-Apache-Maven)
    for more details.


### Preparing a release

#### Major or minor release

1.  Get a clean version of the source code by cloning the
    [TensorFlow Java GitHub repository](https://github.com/tensorflow/java)
    ```
    git clone https://github.com/tensorflow/java
    ```
2.  Create a new branch for the release named `r<MajorVersion>.<MinorVersion>`
    ```
    git checkout -b r1.0
    ```
3.  Update the version of the Maven artifacts to the full version of the release
    ```
    mvn versions:set -DnewVersion=1.0.0
    ```
4.  Update the TensorFlow Java version to reflect the new release at the following locations:
    - https://github.com/tensorflow/java/blob/master/docs/docs/install.md?plain=1#L69
    - https://github.com/tensorflow/java/blob/master/docs/docs/install.md?plain=1#L175
    - https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts
    - https://github.com/tensorflow/java/blob/master/README.md#tensorflow-java-version-support

5.  Commit the changes and push the new branch to the GitHub repository
    ```
    git add .
    git commit -m "Releasing 1.0.0"
    git push --set-upstream origin r1.0
    ```

#### Patch release

1.  Get a clean version of the source code by cloning the
    [TensorFlow Java GitHub repository](https://github.com/tensorflow/java)
    ```
    git clone https://github.com/tensorflow/java
    ```
2.  Switch to the release branch of the version to patch
    ```
    git checkout r1.0
    ```
3.  Patch the code with your changes. For example, changes could be merged from another branch you
    were working on or be applied directly to this branch when the required changes are minimal.

4.  Update the version of the Maven artifacts to the full version of the release
    ```
    mvn versions:set -DnewVersion=1.0.1
    ```
5.  Update the TensorFlow Java version to reflect the new release at the following locations:
    - https://github.com/tensorflow/java/blob/master/docs/docs/install.md?plain=1#L69
    - https://github.com/tensorflow/java/blob/master/docs/docs/install.md?plain=1#L175
    - https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts
    - https://github.com/tensorflow/java/blob/master/README.md#tensorflow-java-version-support

6.  Commit the changes and push the branch to the GitHub repository
    ```
    git add .
    git commit -m "Releasing 1.0.1"
    git push
    ```

### Performing the Release

#### Release Workflow

Pushing on a release branch will trigger a GitHub Actions workflow that builds the native artifacts for all supported architectures/platforms 
and deploys them temporarily as GitHub Workflow artifacts.

After this step, all native artifacts are retrieved and consolidated into the same target folder on single server in GitHub Action. 
A final Maven `deploy` command on that server will take care of publishing all TensorFlow Java artifacts (include the native ones) to Maven 
Central for staging.

#### Publishing Approval

After all artifacts have been published, you should log to [Maven Central](https://central.sonatype.com/), go to `Publish -> Deployments`, 
and either drop the staging repository (if something is wrong) or publish it (if everything looks good). If you drop it, you will need to 
retrigger the [release CI](#release-workflow).

### Finishing a Release

#### Release Notes

Go to GitHub and automatically generate release notes for the new version by going to `Releases -> Draft a new release` 
and selecting the tag for the release. You can edit the release notes as needed.

#### Bumping Next Snapshot Version

##### After a Major or Minor Release

1. Checkout the master branch and merge back changes from the released branch
   ```
   git checkout master
   git merge r1.0
   ```
2. In your local copy, checkout the master branch and increase the next snapshot version.
   ```
   mvn versions:set -DnewVersion=1.3.0-SNAPSHOT
   ```
3. Update the TensorFlow Java version to reflect the new snapshot at the following locations:
    - https://github.com/tensorflow/java/blob/master/docs/docs/install.md?plain=1#L112
    - https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts
    - https://github.com/tensorflow/java/blob/master/README.md#tensorflow-java-version-support

4. Commit your changes and push the master branch to the GitHub repository
   ```
   git add .
   git commit -m "Increase version for next iteration"
   git push
   ```

##### After a Patch Release

1. Checkout the master branch and merge back changes from the released branch
   ```
   git checkout master
   git merge r1.0
   ```
2. Commit the changes and push the master branch to the GitHub repository
   ```
   git add .
   git commit -m "Merge release 1.0.1"
   git push
   ```

## References

-   [Maven Central guide](https://central.sonatype.org/register/central-portal/) for hosting releases.
