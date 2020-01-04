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
  * All artifacts that build up the core language bindings of TensorFlow for Java. 
  * Those artifacts provide the minimal support required to use the TensorFlow runtime on a JVM.
  
* `tensorflow-tools`
  * Utility libraries that do not depend on the TensorFlow runtime but are useful for machine learning purposes
  
* `tensorflow-frameworks`
  * High-level APIs built on top of the core libraries for simplifying the usage of TensorFlow in Java.
  
* `tensorflow-starters`
  * Artifacts aggregating others for simplifying dependency management with TensorFlow
  
*Note: Right now, only the `tensorflow-core` and `tensorflow-tools` components are present*
  
## Building Sources

To build all the artifacts, simply invoke the command `mvn install` at the root of this repository (or 
the Maven command of your choice). It is also possible to build artifacts with support for MKL enabled with
`mvn install -Djavacpp.platform.extension=-mkl` or CUDA with `mvn install -Djavacpp.platform.extension=-gpu`
or both with `mvn install -Djavacpp.platform.extension=-mkl-gpu`.

Note that in some cases, if a version of the TensorFlow runtime library is not found for your environment,
this process will fetch TensorFlow sources and trigger a build of all the native code (which can take
many hours on a standard laptop). In this case, you will also need to have a valid environment for building
TensorFlow, including the [bazel](https://bazel.build/) build tool and a few python dependencies. Please
read [TensorFlow documentation](https://www.tensorflow.org/install/source) for more details.

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
  <version>0.1.0-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.1.0-SNAPSHOT</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
```

On the other hand, if you plan to deploy your JAR on more platforms, you need additional
native dependencies as follows:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.1.0-SNAPSHOT</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.1.0-SNAPSHOT</version>
  <classifier>macosx-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.1.0-SNAPSHOT</version>
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
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Be aware though that the native library is quite large and including too many versions of it may
significantly increase  the size of your JAR. So it is good practice to limit your dependencies to
the platforms you are targeting. For this purpose the `-platform` artifacts include profiles that follow
the conventions established on this page:
* [Reducing the Number of Dependencies](https://github.com/bytedeco/javacpp-presets/wiki/Reducing-the-Number-of-Dependencies)

*Note: the `tensorflow-starters` artifact is not available at this moment*

## How to Contribute?

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join the group
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
