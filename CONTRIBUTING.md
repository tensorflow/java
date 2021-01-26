# Building and contributing to TensorFlow Java

## Building

To build all the artifacts, simply invoke the command `mvn install` at the root of this repository (or
the Maven command of your choice). It is also possible to build artifacts with support for MKL enabled with
`mvn install -Djavacpp.platform.extension=-mkl` or CUDA with `mvn install -Djavacpp.platform.extension=-gpu`
or both with `mvn install -Djavacpp.platform.extension=-mkl-gpu`.

When building this project for the first time in a given workspace, the script will attempt to download
the [TensorFlow runtime library sources](https://github.com/tensorflow/tensorflow) and build of all the native code
for your platform. This requires a valid environment for building TensorFlow, including the [bazel](https://bazel.build/)
build tool and a few Python dependencies (please read [TensorFlow documentation](https://www.tensorflow.org/install/source)
for more details).

This step can take multiple hours on a regular laptop. It is possible though to skip completely the native build if you are
working on a version that already has pre-compiled native artifacts for your platform [available on Sonatype OSS Nexus repository](#Snapshots).
You just need to activate the `dev` profile in your Maven command to use those artifacts instead of building them from scratch
(e.g. `mvn install -Pdev`).

Note that modifying any source files under `tensorflow-core` may impact the low-level TensorFlow bindings, in which case a
complete build could be required to reflect the changes.


## Running Tests

`ndarray` can be tested using the maven `test` target.  `tensorflow-core` and `tensorflow-framework`, however, 
should be tested using the `integration-test` target, due to the need to include native binaries.
It will **not** be ran when using the `test` target of parent projects, but will be ran by `install` or `integration-test`.

## Contributing

### Formatting

Java sources should be formatted according to the [Google style guide](https://google.github.io/styleguide/javaguide.html).
It can be included in [IntelliJ](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) and 
[Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml).
[Google's C++ style guide](https://google.github.io/styleguide/cppguide.html) should also be used for C++ code.

### Code generation

Code generation for `Ops` and related classes is done during `tensorflow-core-api`'s `install`, using the annotation processor in 
`tensorflow-core-generator`. If you change or add any operator classes (annotated with `org.tensorflow.op.annotation.Operator`), 
endpoint methods (annotated with `org.tensorflow.op.annotation.Endpoint`), or change the annotation processor, be sure to re-run a 
full `mvn install` in `tensorflow-core-api`.

### Working with Bazel generation

`tensorflow-core-api` uses Bazel-built C++ code generation to generate most of the `@Operator` classes.  To get it to build, you will likely need to 
clone the [tensorflow](https://github.com/tensorflow/tensorflow) project, run its configuration script (`./configure`), and copy the resulting 
`.tf_configure.bazelrc` to `tensorflow-core-api`.

To run the code generation, use the `//:java_op_generator` target.  The resulting binary has good help text (viewable in 
[op_gen_main.cc](tensorflow-core/tensorflow-core-api/src/bazel/op_generator/op_gen_main.cc#L31-L48)).
Genrally, it should be called with arguments that are something like `bazel-out/k8-opt/bin/external/org_tensorflow/tensorflow/libtensorflow_cc.so 
--output_dir=src/gen/java --api_dirs=bazel-tensorflow-core-api/external/org_tensorflow/tensorflow/core/api_def/base_api,src/bazel/api_def` 
(from `tensorflow-core-api`).
