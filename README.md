# TensorFlow for Java

## Welcome to the Java world of TensorFlow!

TensorFlow can run on any JVM for building, training and running machine learning models. It comes with 
a series of utilities and frameworks that help achieve most of the tasks common to data scientists 
and developers working in this domain. Java and other JVM languages, such as Scala or Kotlin, are 
frequently used in small-to-large enterprises all over the world, which makes TensorFlow a strategic 
choice for adopting machine learning at a large scale.

## This Repository

In the early days, the Java language bindings for TensorFlow were hosted in the [main repository](https://github.com/tensorflow/tensorflow)
and released only when a new version of the core library was ready to be distributed, which happens only
a few times a year. Now, all Java-related code has been moved to this repository so that it can evolve and 
be released independently from official TensorFlow releases. In addition, most of the build tasks have been
migrated from Bazel to Maven, which is more familiar for most Java developers.

The following describes the layout of the repository and its different artifacts:

* `tensorflow-core`
  * All artifacts that build up the core language bindings of TensorFlow for Java
  * Intended audience: projects that provide their own APIs or frameworks on top of 
    TensorFlow and just want a thin layer to access the TensorFlow runtime from the JVM 
    
* `tensorflow-framework`
  * Primary API for building and training neural networks with TensorFlow
  * Intended audience: neural network developers
     
* `ndarray`
  * Generic utility library for n-dimensional data I/O operations
  * Used by TensorFlow but does not depend on TensorFlow
  * Intended audience: any developer who needs a Java n-dimensional array implementation, whether or not they
    use it with TensorFlow
    
More specifically, here are the goals for the framework API:

* If either you know how to implement a model in the Python Keras API, or you are reimplementing an 
  existing Python Keras model in Java, you should be able to cleanly and naturally follow the same 
  high-level structure in the framework API.

* Also, given some familiarity with patterns followed throughout the framework API, you should be 
  able to easily translate every detail of a Python Keras implementation into the framework API.

* However, the framework API is not intended to literally mimic the Python Keras API. Rather, it 
  should expose the same capabilities in an API that feels natural and idiomatic to a Java 
  programmer who does not know Keras. If we ever find ourselves unable to reconcile this goal with 
  easy translation from Python Keras, we may split out a Keras layer.

* Also, the framework API should support fine control over all aspects of modeling, training, and 
  inference. Unlike with Python Keras, we want this to feel like staying in the same API rather 
  than diving into a separate layer. But here again, if we are ever unable to reconcile this goal 
  with easy translation from Python Keras, we may split the framework API into two layers.
  
## Building Sources

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

## Using Maven Artifacts

To include TensorFlow in your Maven application, you first need to add a dependency on either the
`tensorflow-core` or `tensorflow-core-platform` artifacts. The former could be included multiple times
for different targeted systems by their classifiers, while the later includes them as dependencies for
`linux-x86_64`, `macosx-x86_64`, and `windows-x86_64`, with more to come in the future. There are also
`tensorflow-core-platform-mkl`, `tensorflow-core-platform-gpu`, and `tensorflow-core-platform-mkl-gpu`
artifacts that depend on artifacts with MKL and/or CUDA support enabled.

For example, for building a JAR that uses TensorFlow and is targeted to be deployed only on Linux
systems, you should add the following dependencies:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
```

On the other hand, if you plan to deploy your JAR on more platforms, you need additional
native dependencies as follows:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
  <classifier>macosx-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.2.0</version>
  <classifier>windows-x86_64${javacpp.platform.extension}</classifier>
</dependency>
```

In some cases, pre-configured starter artifacts can help to automatically include all versions of
the native library for a given configuration. For example, the `tensorflow-core-platform`,
`tensorflow-core-platform-mkl`, `tensorflow-core-platform-gpu`, or `tensorflow-core-platform-mkl-gpu`
artifact includes transitively all the artifacts above as a single dependency:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-platform${javacpp.platform.extension}</artifactId>
  <version>0.2.0</version>
</dependency>
```

Be aware though that the native library is quite large and including too many versions of it may
significantly increase  the size of your JAR. So it is good practice to limit your dependencies to
the platforms you are targeting. For this purpose the `-platform` artifacts include profiles that follow
the conventions established on this page:
* [Reducing the Number of Dependencies](https://github.com/bytedeco/javacpp-presets/wiki/Reducing-the-Number-of-Dependencies)

### Snapshots

Snapshots of TensorFlow Java artifacts are automatically distributed after each update in the code. To use them, you need
to add Sonatype OSS repository in your pom.xml, like the following

```xml
<repositories>
    <repository>
        <id>tensorflow-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
<dependencies>
    <!-- Example of dependency, see section above for more options -->
    <dependency>
        <groupId>org.tensorflow</groupId>
        <artifactId>tensorflow-core-platform</artifactId>
        <version>0.3.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## TensorFlow Version Support

This table shows the mapping between different version of TensorFlow for Java and the core runtime libraries.

| TensorFlow Java Version  | TensorFlow Version |
| ------------- | ------------- |
| 0.2.0  | 2.3.1  |
| 0.3.0-SNAPSHOT  | 2.3.1  |

## How to Contribute?

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join the group
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
