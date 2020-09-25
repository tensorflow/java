# TensorFlow for Java using Maven

The
[TensorFlow Java API](https://github.com/tensorflow/java) is available on Maven Central and JCenter
through artifacts uploaded to
[OSS Sonatype](https://oss.sonatype.org/content/repositories/releases/org/tensorflow/) and/or
[Bintray](https://bintray.com/google/tensorflow/tensorflow). This
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

The process of releasing TensorFlow for Java is splitted in two major steps:
* Build of the native artifacts
* Build and deployment of all artifacts consolidated

While the first step should be done on different servers, each configured for building the native
artifact for a specific architecture and platform, the second step is conducted locally in
a [Docker](https://www.docker.com) container for a more hermetic process.

### Pre-requisites

-   `docker`
-   An account at [oss.sonatype.org](https://oss.sonatype.org/), that has
    permissions to update artifacts in the `org.tensorflow` group. If your
    account does not have permissions, then you'll need to ask someone who does
    to [file a ticket](https://issues.sonatype.org/) to add to the permissions
    ([sample ticket](https://issues.sonatype.org/browse/MVNCENTRAL-1637)).
-   An account at [bintray.com](https://bintray.com) that has permissions to
    update the [tensorflow repository](https://bintray.com/google/tensorflow).
    If your account does not have permissions, then you'll need to ask one of
    the [organization administrators](https://bintray.com/google) to give you
    permissions to update the `tensorflow` repository. Please keep the
    [repository option](https://bintray.com/google/tensorflow/edit?tab=general)
    to *"GPG sign uploaded files using Bintray's public/private key pair"*
    **unchecked**, otherwise it will conflict with locally signed artifacts.
-   A GPG signing key, required
    [to sign the release artifacts](http://central.sonatype.org/pages/apache-maven.html#gpg-signed-components).

### Preparing a release

#### Major or minor release

1.  Clone a clean version of the
    [TensorFlow Java GitHub repository](https://github.com/tensorflow-java)

2.  Switch to a new branch named to the version of the release (only major and minor digits), prefixed
    by the letter `r`. For example, for releasing 1.0.0, `git checkout -b `r1.0`

3.  Update the version of the Maven artifacts in the branch to match the three-digit version of the
    release. Normally, that should result in removing the suffix `-SNAPSHOT` of the actual version of
    the master branch. This could be done using the Maven version plugin: `mvn versions:use-releases`
    (it is always possible to use `mvn versions:set` to force a specific version).

4.  Commit the changes and push the new branch to the GitHub repository.

#### Patch release

1.  Clone a clean version of the
    [TensorFlow Java GitHub repository](https://github.com/tensorflow-java)

2.  Switch to a release branch to patch. For example, for releasing 1.0.1, `git checkout `r1.0`

3.  Update the version of the Maven artifacts in the branch to match the three-digit version of the
    patched release. Normally, that should result in increasing the last digit of the latest version.
    This could be done using the Maven version plugin: `mvn versions:use-next-releases`
    (it is always possible to use `mvn versions:set` to force a specific version)

4.  Commit the changes and push the branch to the GitHub repository.

### Building native artifacts

Once a release branch (i.e. a branch prefixed by `r`) is pushed on the repository, a workflow will
automatically start in GitHub Action to build the native artifacts for all supported
architures/platforms and upload them in a cloud storage for the next step (we use for actual cloud
storage is the 's3://tensorflow-java/staging' AWS S3 bucket).

There is no user action required for this step other than watching the progress of the GitHub
Action workflow and making sure that all steps have been completed successfully.

### Building and deploying all artifacts to Sonatype and Bintray

1.  Create a file with your OSSRH credentials and
    [Bintray API key](https://bintray.com/docs/usermanual/interacting/interacting_interacting.html#anchorAPIKEY)
    (or place it in your existing `~/.m2/settings.xml`):

    ```sh
    SONATYPE_USERNAME="your_sonatype.org_username_here"
    SONATYPE_PASSWORD="your_sonatype.org_password_here"
    BINTRAY_USERNAME="your_bintray_username_here"
    BINTRAY_API_KEY="your_bintray_api_key_here"
    GPG_PASSPHRASE="your_gpg_passphrase_here"
    cat >/tmp/settings.xml <<EOF
    <settings>
      <servers>
        <server>
          <id>ossrh</id>
          <username>${SONATYPE_USERNAME}</username>
          <password>${SONATYPE_PASSWORD}</password>
        </server>
        <server>
          <id>bintray</id>
          <username>${BINTRAY_USERNAME}</username>
          <password>${BINTRAY_API_KEY}</password>
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

2.  Run the `release.sh` script. This will deploy only on OSSRH by default. To enable Bintray
    deployment as well, pass the environment variable DEPLOY_BINTRAY=true.

    This script will fetch back all native artifacts previously uploaded in cloud storage by
    the GitHub Action workflow so they can be signed and deployed with all the other artifacts.

3.  If the script above succeeds then the artifacts would have been uploaded to
    the private staging repository in Sonatype, and as unpublished artifacts in
    Bintray. After verifying the release, you should finalize or abort the
    release on both sites.

4.  Visit https://oss.sonatype.org/#stagingRepositories, find the `org.tensorflow`
    release and click on either `Release` to finalize the release, or `Drop` to
    abort.

5.  Visit https://bintray.com/google/tensorflow/tensorflow, and select the
    version you just uploaded. Notice there's a message about unpublished
    artifacts. Click on either `Publish` to finalize the release, or `Discard`
    to abort.

6.  Some things of note:
    - For details, look at the [Sonatype guide](http://central.sonatype.org/pages/releasing-the-deployment.html).
    - Syncing with [Maven Central](http://repo1.maven.org/maven2/org/tensorflow/)
      can take 10 minutes to 2 hours (as per the [OSSRH
      guide](http://central.sonatype.org/pages/ossrh-guide.html#releasing-to-central)).
    - For Bintray details, refer to their guide on
      [managing uploaded content](https://bintray.com/docs/usermanual/uploads/uploads_managinguploadedcontent.html#_publishing).

### Skip deploying to a repository

Should you need, setting environment variables `DEPLOY_OSSRH=false` or
`DEPLOY_BINTRAY=false` when calling `release.sh` will skip deploying to OSSRH or
Bintray respectively. Note that snapshots are only uploaded to OSSRH, so you
cannot skip deploying to OSSRH for a `-SNAPSHOT` version.

## References

-   [Sonatype guide](http://central.sonatype.org/pages/ossrh-guide.html) for
    hosting releases.
-   [Ticket that created the `org/tensorflow` configuration](https://issues.sonatype.org/browse/OSSRH-28072) on OSSRH.
-   The [Bintray User Manual](https://bintray.com/docs/usermanual/index.html)
