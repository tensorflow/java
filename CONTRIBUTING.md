# Building and Contributing to TensorFlow Java

## Building

To build all the artifacts, simply invoke the command `mvn install` at the root of this repository (or the Maven command of your choice). It is also
possible to build artifacts with support for MKL enabled with
`mvn install -Djavacpp.platform.extension=-mkl` or CUDA with `mvn install -Djavacpp.platform.extension=-gpu`
or both with `mvn install -Djavacpp.platform.extension=-mkl-gpu`.

When building this project for the first time in a given workspace, the script will attempt to download
the [TensorFlow runtime library sources](https://github.com/tensorflow/tensorflow) and build of all the native code for your platform. This requires a
valid environment for building TensorFlow, including the [bazel](https://bazel.build/)
build tool and a few Python dependencies (please read [TensorFlow documentation](https://www.tensorflow.org/install/source)
for more details).

This step can take multiple hours on a regular laptop. It is possible though to skip completely the native build if you are working on a version that
already has pre-compiled native artifacts for your platform [available on Sonatype OSS Nexus repository](#Snapshots). You just need to activate
the `dev` profile in your Maven command to use those artifacts instead of building them from scratch
(e.g. `mvn install -Pdev`).

Modifying the native op generation code (not the annotation processor) or the JavaCPP configuration (not the abstract Pointers) will require a
complete build could be required to reflect the changes, otherwise `-Pdev` should be fine.

## JDK 16+

If you're using JDK 16+, you need to add some exports for the formatter plugin:

```
--add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED
```

This can be done in `.mvn/jvm.config` or `MAVEN_OPTS`.

### Native Builds

In some cases, like when adding GPU support or re-generating op classes, you will need to re-build the native library. 99% of this is building
TensorFlow, which by default is configured for the [CI](.github/workflows/ci.yml). The build configuration can be customized using the same methods as
TensorFlow, so if you're building locally, you may need to clone the [tensorflow](https://github.com/tensorflow/tensorflow) project, run its
configuration script (`./configure`), and copy the resulting
`.tf_configure.bazelrc` to `tensorflow-core-api`. This overrides the default options, and you can add to it manually (i.e. adding `build --copt="-g"`
to build with debugging info).

The `tensorflow-core/tensorflow-core-api/.bazelversion` file must be kept in sync with `@org_tensorflow/.bazel_version`. 
This allows using [Bazelisk](https://github.com/bazelbuild/bazelisk) which runs the bazel version given in .bazelversion instead of having to 
physically reinstall a specific `bazel` version each time the TensorFlow version changes.


### GPU Support

Currently, due to build time constraints, the GPU binaries only support compute capacities 3.5 and 7.0.  
To use with un-supported GPUs, you have to build it yourself, after changing the value [here](tensorflow-core/tensorflow-core-api/build.sh#L27),
setting the environment variable `TF_CUDA_COMPUTE_CAPABILITIES`, or configuring it in a bazel rc file (
i.e. `build --action_env TF_CUDA_COMPUTE_CAPABILITIES="6.1"`). While this is far from ideal, we are working on getting more build resources, and for
now this is the best option.

To build for GPU, pass `-Djavacpp.platform.extension=-gpu` to maven. By default, the CI options are used for the bazel build, see the above section
for more info. If you add `bazelrc` files, make sure the `TF_CUDA_COMPUTE_CAPABILITIES` value in them matches the value set elsewhere, as it will take
precedence if present.

## Running Tests

`ndarray` can be tested using the maven `test` target.  `tensorflow-core` and `tensorflow-framework`, however, should be tested using
the `integration-test` target, due to the need to include native binaries. It will **not** be ran when using the `test` target of parent projects, but
will be ran by `install` or `integration-test`. If you see a `no jnitensorflow in java.library.path` error from tests it is likely because you're
running the wrong test target.

### Native Crashes

Occasionally tests will fail with a message like:

```
Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.0:test(default-test)on project tensorflow-core-api:There are test failures.

    Please refer to C:\mpicbg\workspace\tensorflow\java\tensorflow-core\tensorflow-core-api\target\surefire-reports for the individual test results.
    Please refer to dump files(if any exist)[date]-jvmRun[N].dump,[date].dumpstream and[date]-jvmRun[N].dumpstream.
    The forked VM terminated without properly saying goodbye.VM crash or System.exit called?
    Command was cmd.exe/X/C"C:\Users\me\.jdks\adopt-openj9-1.8.0_275\jre\bin\java -jar C:\Users\me\AppData\Local\Temp\surefire236563113746082396\surefirebooter5751859365434514212.jar C:\Users\me\AppData\Local\Temp\surefire236563113746082396 2020-12-18T13-57-26_766-jvmRun1 surefire2445852067572510918tmp surefire_05950149004635894208tmp"
    Error occurred in starting fork,check output in log
    Process Exit Code:-1
    Crashed tests:
    org.tensorflow.TensorFlowTest
    org.apache.maven.surefire.booter.SurefireBooterForkException:The forked VM terminated without properly saying goodbye.VM crash or System.exit called?
    Command was cmd.exe/X/C"C:\Users\me\.jdks\adopt-openj9-1.8.0_275\jre\bin\java -jar C:\Users\me\AppData\Local\Temp\surefire236563113746082396\surefirebooter5751859365434514212.jar C:\Users\me\AppData\Local\Temp\surefire236563113746082396 2020-12-18T13-57-26_766-jvmRun1 surefire2445852067572510918tmp surefire_05950149004635894208tmp"
    Error occurred in starting fork,check output in log
    Process Exit Code:-1
    Crashed tests:
    org.tensorflow.TensorFlowTest
    at org.apache.maven.plugin.surefire.booterclient.ForkStarter.fork(ForkStarter.java:671)
    at org.apache.maven.plugin.surefire.booterclient.ForkStarter.fork(ForkStarter.java:533)
    at org.apache.maven.plugin.surefire.booterclient.ForkStarter.run(ForkStarter.java:278)
    at org.apache.maven.plugin.surefire.booterclient.ForkStarter.run(ForkStarter.java:244)
```

This is because the native code crashed (i.e. because of a segfault), and it should have created a dump file somewhere in the project that you can use
to tell what caused the issue.

## Contributing

### Formatting

Java sources should be formatted according to the [Google style guide](https://google.github.io/styleguide/javaguide.html). It can be included
in [IntelliJ](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) and
[Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml).
[Google's C++ style guide](https://google.github.io/styleguide/cppguide.html) should also be used for C++ code.

### Dependencies

For dependencies, we can use anything compliant with [this list](https://opensource.google/docs/thirdparty/licenses/#notice), but we want to keep the core libraries as dependency free as possible.

### Code generation

Code generation for `Ops` and related classes is done during `tensorflow-core-api` and `tensorflow-core-kotlin`'s `compile` phase, 
using the annotation processors in `tensorflow-core-generator` and `tensorflow-kotlin-generator`, respectively. If you change or add any 
operator classes (annotated with `org.tensorflow.op.annotation.Operator`), endpoint methods (annotated with `org.tensorflow.op.annotation.Endpoint`), 
or change the annotation processor, be sure to re-run a `mvn compile` in `tensorflow-core-api` **and** `tensorflow-core-kotlin` 
(`-Pdev` is fine for this, it just needs to run the annotation processor).

### Working with Bazel generation

`tensorflow-core-api` uses Bazel-built C++ code generation to generate most of the `@Operator` classes. See [Native Builds](#native-builds) for
instructions on configuring the bazel build. To run the code generation, use the `//:java_op_generator` target. The resulting binary has good help
text (viewable in
[op_gen_main.cc](tensorflow-core/tensorflow-core-api/src/bazel/op_generator/op_gen_main.cc#L31-L48)). Generally, it should be called with arguments
that are something like:

```
bazel-out/k8-opt/bin/external/org_tensorflow/tensorflow/libtensorflow_cc.so --output_dir=src/gen/java --api_dirs=bazel-tensorflow-core-api/external/org_tensorflow/tensorflow/core/api_def/base_api,src/bazel/api_def
```

(called in `tensorflow-core-api`).

### Kotlin API

The Kotlin api should be kept to a thin wrapper of the Java API, using extension functions and codegen wherever possible.
We do not want to get into a situation where we are maintaining two separate but related APIs.

The codegen (`tensorflow-core-kotlin-generator`) is an annotation processor that reads the `@Operator` classes from the `tensorflow-core-api` Java sources.
If you add operators or re-generate them from the native library, be sure to re-run a `mvn install` in `tensorflow-core-kotlin-api`.

#### Formatting

[ktfmt](https://github.com/facebookincubator/ktfmt) is used to format the Kotlin files.  This is
checked and done via maven in the same way as Java formatting.  To do the formatting via IntelliJ see
ktfmt's repo.

## Adding Gradients

In some cases, a op supported by Tensorflow Java will not have a gradient defined, resulting in errors like this:
```
org.tensorflow.exceptions.TensorFlowException: No gradient defined for op: ReadVariableOp. Please see https://www.tensorflow.org/code/tensorflow/cc/gradients/README.md for instructions on how to add C++ gradients.
	at org.tensorflow.internal.c_api.AbstractTF_Status.throwExceptionIfNotOK(AbstractTF_Status.java:101)
	at org.tensorflow.Graph.addGradients(Graph.java:708)
	at org.tensorflow.Graph.addGradients(Graph.java:291)
```
The description in the [linked file](https://www.tensorflow.org/code/tensorflow/cc/gradients/README.md) are accurate for adding C++ Graph gradients, which are used by our `Graph`.  Eexamples of doing that are [tensorflow/tensorflow#46115](https://github.com/tensorflow/tensorflow/pull/46115) and [tensorflow/tensorflow#47774](https://github.com/tensorflow/tensorflow/pull/47774).  However, Tensorflow Core is in the process of migrating gradient definitions to [`c/experimental/gradients`](https://github.com/tensorflow/tensorflow/tree/master/tensorflow/c/experimental/gradients), which will be what our eager mode uses once it has gradient support. Anyone adding gradients is strongly encouraged to add one there as well, and eventually it should replace the legacy `cc/gradients` gradients.
