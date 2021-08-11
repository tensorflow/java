# Releasing TensorFlow Java

The
[TensorFlow Java API](https://github.com/tensorflow/java) is available on Maven Central and JCenter
through artifacts uploaded to
[OSS Sonatype](https://oss.sonatype.org/content/repositories/releases/org/tensorflow/). This
document describes the process of updating the release artifacts. It does _not_ describe how to use
the artifacts, for which the reader is referred to the
[TensorFlow for Java installation instructions](https://github.com/tensorflow/java/blob/master/README.md).

## Background

TensorFlow source (which is primarily in C++) is built using
[bazel](https://bazel.build) and not [maven](https://maven.apache.org/). TensorFlow Java
wraps over this native code and thus depends on platform (OS, architecture) specific native code.

Hence, the process for building and uploading release artifacts is not a single
`mvn deploy` command.

## Release process overview

The process of releasing TensorFlow Java is split in two major steps:
* Building and deploying the native artifacts
* Building and deploying all artifacts consolidated

The first step is executed on different build servers, each responsible to build the native
artifact for a specific architecture and platform. The second step is conducted locally in
a [Docker](https://www.docker.com) container for a hermetic release process.

It is important to note that any change pushed to a release branch (i.e. a branch prefixed
by `r`) will start a new release workflow. Therefore, these changes should always increment the
version number.

### Pre-requisites

-   `docker`
-   An account at [oss.sonatype.org](https://oss.sonatype.org/), that has
    permissions to update artifacts in the `org.tensorflow` group. If your
    account does not have permissions, then you'll need to ask someone who does
    to [file a ticket](https://issues.sonatype.org/) to add to the permissions
    ([sample ticket](https://issues.sonatype.org/browse/MVNCENTRAL-1637)).
-   A GPG signing key, required
    [to sign the release artifacts](http://central.sonatype.org/pages/apache-maven.html#gpg-signed-components).

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
4.  In the [README.md](https://github.com/tensorflow/java/blob/master/README.md) file,
    update the ['Using Maven Artifacts'](https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts)
    section and the ['TensorFlow Version Support'](https://github.com/tensorflow/java/blob/master/README.md#tensorflow-version-support)
    table to reflect the new version being released.

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
5.  In the [README.md](https://github.com/tensorflow/java/blob/master/README.md) file,
    update the ['Using Maven Artifacts'](https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts)
    section and the ['TensorFlow Version Support'](https://github.com/tensorflow/java/blob/master/README.md#tensorflow-version-support)
    table to reflect the new version being released.
    Also update all references to the previous version in the [installation instructions](https://github.com/tensorflow/java/blob/master/docs/install.md)
    for the new one.

6.  Commit the changes and push the branch to the GitHub repository
    ```
    git add .
    git commit -m "Releasing 1.0.1"
    git push
    ```

### Building native artifacts

Any change pushed to a release branch will trigger a new release workflow. GitHub Actions builds the native artifacts 
for all supported architures/platforms and deploy them temporarily on OSSRH for staging.

There is no user action required for this step other than watching the progress of the GitHub
Actions workflow and making sure that all steps have been completed successfully.

#### Build native artifacts manually

Some platforms cannot be build successfully on GitHub Actions, due to some limits to their resources
(e.g. max 6 hours for a job). For this reasons, we need to build manually some of our artifacts on
private servers. 

To do so, follow the same steps as the [CI build](https://github.com/tensorflow/java/blob/master/.github/workflows/ci.yml)
for the same platform and make sure to checkout the release branch and to provide your Sonatype credentials
for temporary staging.

### Performing the release

1.  At the root of your TensorFlow Java copy, create a Maven settings.xml file with your OSSRH credentials and
    your GPG key passphrase:
    ```sh
    SONATYPE_USERNAME="your_sonatype.org_username_here"
    SONATYPE_PASSWORD="your_sonatype.org_password_here"
    GPG_PASSPHRASE="your_gpg_passphrase_here"
    cat > settings.xml <<EOF
    <settings>
      <servers>
        <server>
          <id>ossrh</id>
          <username>${SONATYPE_USERNAME}</username>
          <password>${SONATYPE_PASSWORD}</password>
        </server>
        <server>
          <id>ossrh-staging</id>
          <username>${SONATYPE_USERNAME}</username>
          <password>${SONATYPE_PASSWORD}</password>
        </server>
      </servers>
      <profiles>
        <profile>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
          <properties>
            <gpg.executable>gpg2</gpg.executable>
            <gpg.passphrase>${GPG_PASSPHRASE}</gpg.passphrase>
          </properties>
        </profile>
      <profiles>
    </settings>
    EOF
    ```
2.  Execute the `release.sh` script. This will deploy artifacts on OSS Sonatype. All native artifacts
    previously temporarily staged by GitHub Actions will be fetched, signed and redeployed as well.

    The script takes in paramater the sequence number of the staging repository created in OSSRH
    by the GitHub Actions workflow. You can retrieve this ID by looking in the staging repositories
    in OSSRH console directly, or check at the output of the step `Create Staging Repository` of the
    `prepare` job in the workflow execution, where the ID is printed.
    ```
    # Staging repository created: orgtensorflow-1100
    sh release.sh 1100
    ```
3.  If the script above succeeds then the artifacts would have been uploaded to
    the private staging repository in Sonatype. After verifying the release, you should finalize or
    abort the release. Visit https://oss.sonatype.org/#stagingRepositories, find the `orgtensorflow-*`
    of your release and click `Close` and `Release` to finalize the release. You always have the option
    to `Drop` it to abort and restart if something went wrong.

4.  Go to GitHub and create a release tag on the release branch with a summary of what the version includes.

Some things of note:
    - For details, look at the [Sonatype guide](http://central.sonatype.org/pages/releasing-the-deployment.html).
    - Syncing with [Maven Central](http://repo1.maven.org/maven2/org/tensorflow/)
      can take 10 minutes to 2 hours (as per the [OSSRH
      guide](http://central.sonatype.org/pages/ossrh-guide.html#releasing-to-central)).

### Finishing a release

#### Major or minor release

1. Checkout the master branch and merge back changes from the released branch
   ```
   git checkout master
   git merge r1.0
   ```
2. In your local copy, checkout the master branch and increase the next snapshot version.
   ```
   mvn versions:set -DnewVersion=1.1.0-SNAPSHOT
   ```
3.  In the [README.md](https://github.com/tensorflow/java/blob/master/README.md) file,
    update the ['Using Maven Artifacts'](https://github.com/tensorflow/java/blob/master/README.md#using-maven-artifacts)
    section and the ['TensorFlow Version Support'](https://github.com/tensorflow/java/blob/master/README.md#tensorflow-version-support)
    table to reflect the new snapshot version.

4. Commit your changes and push the master branch to the GitHub repository
   ```
   git add .
   git commit -m "Increase version for next iteration"
   git push
   ```

#### Patch release

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

-   [Sonatype guide](http://central.sonatype.org/pages/ossrh-guide.html) for
    hosting releases.
-   [Ticket that created the `org/tensorflow` configuration](https://issues.sonatype.org/browse/OSSRH-28072) on OSSRH.
