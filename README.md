# TensorFlow for Java

***!!! IMPORTANT NOTICE !!! This repository is UNDER CONSTRUCTION and does not host the code of the 
released TensorFlow Java artifacts (yet)!***

***Please refer to the [TensorFlow Java module](https://github.com/tensorflow/tensorflow/tree/master/tensorflow/java) 
of the main repository for the official code.***

## Welcome to the Java world of TensorFlow!

TensorFlow can run on any JVM for building, training or running machine learning models. It comes with 
a series of utilities and frameworks that help achieving most of the tasks common to data scientists 
and developers working in this domain. Java and other JVM languages, such as Scala or Kotlin, are 
frequently used in small-to-large enterprises all over the world, which makes TensorFlow a strategic 
choice for adopting machine learning on a large scale.

## This Repository

In the early days, the Java language bindings for TensorFlow were hosted in the [main repository](https://github.com/tensorflow/tensorflow)
and released only when a new version of the core library was ready to be distributed, which happens only
a few times a year. Now, all Java-related code has moved to this repository so that it can evolved and 
be released independently from official TensorFlow releases to deliver faster new features and improvements
added to those libraries. In addition, most of the build tasks has been migrated from Bazel to Maven, which
is more familiar for most of the Java developers.

The following describes the layout of the repository and its different artifacts:

* `core`
  * All artifacts that builds up the core language bindings of TensorFlow for Java. 
  * Those artifacts provides the minimal support required to use the TensorFlow runtime on a JVM.
  
* `utils`
  * Utility libraries that does not depend on the TensorFlow runtime but are useful for machine learning purposes
  
* `frameworks`
  * High-level APIs built on top of the core libraries for simplifying the usage of TensorFlow in Java.
  
* `starters`
  * Artifacts aggregating others for simplifying dependency management with TensorFlow
  
*Note: Right now, only the `core` component is present*
  
## Building Sources

To build all the artifacts, simply invoke the command `mvn install` at the root of this repository (or 
the Maven command of your choice).

Note that in some cases, if a version of the TensorFlow runtime library is not found for your environment,
this process will fetch TensorFlow sources and trigger a build of all the native code (which can take
many hours on a standard laptop!). In this case, you will also need to have a valid environment for building
TensorFlow, including the [bazel](https://bazel.build/) build tool and a few python dependencies. Please
read [TensorFlow documentation](https://www.tensorflow.org/install) for more details.

## Using Maven Artifacts

To include TensorFlow in your Maven application, you need at least to add a dependency on both
`tensorflow-core` and `tensorflow-core-native` artifacts. The later could be included multiple times
for different targeted systems by their classifiers.

For example, for building a JAR that uses TensorFlow and is targeted to be deployed only on Linux
systems, you should add the following dependencies:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-native</artifactId>
  <version>2.0.0-SNAPSHOT</version>
  <classifier>linux-x86_64</classifier>
</dependency>
```

On the other hand, if you target to deploy your JAR on any supported platform, you need additional
native dependencies as follow:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-native</artifactId>
  <version>2.0.0-SNAPSHOT</version>
  <classifier>linux-x86_64</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-native</artifactId>
  <version>2.0.0-SNAPSHOT</version>
  <classifier>windows-x86_64</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-native</artifactId>
  <version>2.0.0-SNAPSHOT</version>
  <classifier>darwin-x86_64</classifier>
</dependency>
```

In some cases, pre-configured starter artifacts can help to include automatically all versions of
the native library for a given configuration. For example, the `tensorflow` artifacts includes
transitively all the artifacts above as a single dependency:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
```

Be aware though that the native library are quite large and including too many version of it may result
increase significantly the size of your JAR. So it is a good practice to limit the dependencies to
the platforms that are actually targeted.

*Note: the `tensorflow` starter artifact is not available at this moment*

## How to Contribute?

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join it
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
