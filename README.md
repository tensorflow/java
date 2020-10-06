# TensorFlow for Java

***!!! IMPORTANT NOTICE !!! This repository is UNDER CONSTRUCTION and does not yet host the code of the 
offical TensorFlow Java artifacts!***

***Please refer to the [TensorFlow Java module](https://github.com/tensorflow/tensorflow/tree/master/tensorflow/java) 
of the main repository for the actual code.***

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
  * Complete but fairly primitive API for building and training neural networks with TensorFlow
  * Intended audience: expert neural network developers who prefer to make explicit, detailed decisions 
    about their models and training algorithms
    
* `tensorflow-keras` (early WIP; only defined in `dev` profile)
  * Partially covers the framework API to allow simpler definition of models and training algorithms
  * Intended to be familiar if you know the Python Keras API, but prioritizes clean, idiomatic Java 
    over fidelity to Python
  * Provides defaults based on common best practices
  * Allows developers to selectively be more explicit by overriding defaults or dipping into the framework API
  * Intended audience: neural network developers across the spectrum from beginner to expert who prefer to
    rely mostly on best-practice defaults and then selectively fine-tune
  
* `ndarray`
  * Generic utility library for n-dimensional data I/O operations
  * Used by TensorFlow but does not depend on TensorFlow
  * Intended audience: any developer who needs a Java n-dimensional array implementation, whether or not they
    use it with TensorFlow
  
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

Artifacts resulting from this repository are actually only available as snapshots and to retrieve them, you need
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
        <version>0.2.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## TensorFlow Version Support

This table shows the mapping between different version of TensorFlow for Java and the core runtime libraries.

| TensorFlow Java Version  | TensorFlow Version |
| ------------- | ------------- |
| 0.1.0-SNAPSHOT  | 2.2.0  |
| 0.2.0-SNAPSHOT  | 2.3.1  |
| 0.2.0  | 2.3.1  |

## How to Contribute?

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join the group
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
