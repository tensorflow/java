# Building and Contributing to TensorFlow Java

## Contributing

### Formatting

Java sources should be formatted according to the [Google style guide](https://google.github.io/styleguide/javaguide.html). It can be included
in [IntelliJ](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) and
[Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml).
[Google's C++ style guide](https://google.github.io/styleguide/cppguide.html) should also be used for C++ code.

### Dependencies

For dependencies, we can use anything compliant with [this list](https://opensource.google/docs/thirdparty/licenses/#notice), but we want to keep the core libraries as dependency free as possible.

## Building

To build all the artifacts locally, simply invoke the command `mvn install` at the root of this repository (or the Maven command of your choice). It is also
possible to build artifacts with support for CUDA® by adding the `-Djavacpp.platform.extension=-gpu` argument to the Maven command.

### JDK 16+

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

By default, the build will attempt to download the existing TensorFlow binaries from the web for the platform it is running on (so you need to have an active internet connection).
If such binaries are not available for your platform, you will need to build the TensorFlow runtime library from sources, by appending the `-Dnative.build` argument to your Maven
command. This requires a valid environment for building TensorFlow, including the [bazel](https://bazel.build/) build tool and a few Python dependencies
(please read [TensorFlow documentation](https://www.tensorflow.org/install/source) for more details). Note that building from sources can take multiple hours on a regular laptop.

To build for GPU, pass `-Djavacpp.platform.extension=-gpu` to maven. If you want to use TensorFlow Java with unsupported GPUs, set the environment variable `TF_CUDA_COMPUTE_CAPABILITIES`, or 
configure it in a bazel rc file (i.e. `build --action_env TF_CUDA_COMPUTE_CAPABILITIES="6.1"`). 

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

## Upgrading TensorFlow Version

To upgrade the version of TensorFlow that is embedded within TensorFlow Java, please follow carefully these steps.

### Upgrading TensorFlow Native Library

1. Download locally the archive of the tensorflow release at https://github.com/tensorflow/tensorflow/archive/refs/tags/vX.X.X.tar.gz
2. Compute the SHA sum using the shell command `sha256sum <tensorflow-x.x.x.tar.gz>`
3. Update `urls`, `sha256` and `strip_prefix` fields of the `org_tensorflow` archive rule in Bazel [workspace](https://github.com/tensorflow/java/blob/master/tensorflow-core/tensorflow-core-native/WORKSPACE#L19)
4. Extract the archive in a temporary folder
5. Copy the content of `tensorflow-x.x.x/.bazelrc` file to `tensorflow-core/tensorflow-core-native/tensorflow.bazelrc` under TensorFlow Java source tree
6. Copy the content of `tensorflow-x.x.x/WORKSPACE` after the "###### Copy content of..." notice if `tensorflow-core/tensorflow-core-native/WORKSPACE`, read notice for more details
7. Copy the content of `tensorflow-x.x.x/.bazelversion` file to `tensorflow-core/tensorflow-core-native/.bazelversion`
8. Validate that options in `tensorflow-core/tensorflow-core-native/.bazelrc` are still accurate or update them accordingly
9. Update URLs of existing TensorFlow binaries in the `tensorflow-core/tensorflow-core-native/scripts/dist_download` script

#### Patching TensorFlow Sources

In order to build the TensorFlow native library to work with TensorFlow Java, we sometimes need to apply some patches to the TensorFlow sources. These
patches are found in `tensorflow-core/tensorflow-core-native/external`.

- If you have an error like "Error in fail: Error applying patch //external:xxx.patch:", verify why the patch is failing by looking at the TensorFlow source code.
  Chances are that this code has changed and the patch needs to be updated.
- To create a new patch or update one, you can make a copy of the TensorFlow source file to change, make your change and generate a patch using `git diff <file> <file-updated>`
- If more than one file needs to be added to the patch, it's easier to clone the [TensorFlow repository](https://github.com/tensorflow/tensorflow), apply the changes and use `git diff` at the root of the tree

### Generating Java Bindings

After upgrading the TensorFlow library, you need to regenerate all Java bindings that depends on the native code. That includes Java protos, C API bindings (JavaCPP) and
operator classes. You can trigger the regeneration of these bindings with the Maven command `mvn clean install -Pgenerating`.

This will also trigger a small Bazel build of the TensorFlow sources to regenerate the Java protos, so make sure your [environment](CONTRIBUTING.md#native-builds) is setup properly.

#### Operations Classification

When generating the operator classes, the build process might prompt you to provide information about the new operations found in the targeted TensorFlow version. This will generate a new API definition
under the [tensorflow-core/tensorflow-core-api/api](https://github.com/tensorflow/java/tree/master/tensorflow-core/tensorflow-core-api/api) folder. The required 
information is:
* The visibility for this op
  * VISIBLE to force the creation of a Java class that will be also exposed by the `*Ops` API classes.
  * HIDDEN for creating a Java class that won't be exposed by the `*Ops` API classes.
  * SKIP for not creating a Java class for this operation
  * DEFAULT to rely on the visibility settings set in TensorFlow sources
* The name group for this operator
  * This name is used to place this operator under the right subpackage and `*Ops` API.
  * For example, the group `nn` will place the operator `Conv` under the `org.tensorflow.op.nn` package and in the `NnOps` API class.
  * When no group is specified, the operator will go under the `org.tensorflow.op.core` package and in the `Ops` API class.
* The name for this op
  * By default is the name found in TensorFlow registry but can be useful in some cases to rename it in case it clashes with Java keywords (e.g. `Switch`-> `SwitchCond`)
  * Can also be used to remove the suffix of an operation that has multiple versions (e.g. `RestoreV2` -> `Restore`)

The actual classification process is a bit arbitrary and based on the good judgement of the developer. The reason is that most ops in Python
are being wrapped by a higher-level API and therefore are left unclassified, while in Java they are exposed and can be used directly by
the users. 

Please review the location of the new generated operators after the build is complete and make necessary adjustments to the API definitions protos 
manually if some of them seems to be in the "wrong" place, making sure to repeat this process until satisfaction.

#### New Operation Version

Some operations might be just an upgrade of another existing operations. For instance, there are many version of the `BatchMatMul` kernel (V1, V2, V3...).
When you see that a new op is just an upgrade from another other one, make sure that the latest version has a valid endpoint and that all other
previous versions of this operation are marked as `VISIBILITY: SKIP`.

### Java Protos Classification

TensorFlow Java distributes a large number proto definitions found in the TensorFlow native library as Java classes. Again, new protos might not
be classified properly since they may be lacking the `option java_*` statements at the beginning of their definition. The build script will attempt
to mitigate this omission by generating the proto bindings under the same package as the `package` statement (if also present), and under the root package
`org.tensorflow.proto`.

#### Custom Operators

Code generation for `Ops` and related classes is done during `tensorflow-core-api`'s `compile` phase, using the annotation processor in
`tensorflow-core-generator`. If you change or add any operator classes (annotated with `org.tensorflow.op.annotation.Operator`), endpoint methods (
annotated with `org.tensorflow.op.annotation.Endpoint`), or change the annotation processor, be sure to re-run a
`mvn clean install -Pgenerating` in `tensorflow-core-api`.

## Known Issues

### Missing Gradients

In some cases, a op supported by Tensorflow Java will not have a gradient defined, resulting in errors like this:
```
org.tensorflow.exceptions.TensorFlowException: No gradient defined for op: ReadVariableOp. Please see https://www.tensorflow.org/code/tensorflow/cc/gradients/README.md for instructions on how to add C++ gradients.
	at org.tensorflow.internal.c_api.AbstractTF_Status.throwExceptionIfNotOK(AbstractTF_Status.java:101)
	at org.tensorflow.Graph.addGradients(Graph.java:708)
	at org.tensorflow.Graph.addGradients(Graph.java:291)
```
The description in the [linked file](https://www.tensorflow.org/code/tensorflow/cc/gradients/README.md) are accurate for adding C++ Graph gradients, which are used by our `Graph`.  Examples of doing that are [tensorflow/tensorflow#46115](https://github.com/tensorflow/tensorflow/pull/46115) and [tensorflow/tensorflow#47774](https://github.com/tensorflow/tensorflow/pull/47774).  

You can also code and register the missing gradients in Java, using the TensorFlow Java custom gradient registration capabilities. Check at the JavaDoc of `tensorflow-core-api` for more details.
