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
    
* `tensorflow-core-kotlin`
  * Kotlin API bindings for `tensorflow-core`.  These are thin wrappers around the core APIs
    to make them more idiomatic for use in Kotlin, such as using parameters with default values
    operation builders instead of an `Options` vararg.
  
* `tensorflow-framework`
  * Primary API for building and training neural networks with TensorFlow
  * Intended audience: neural network developers
  * For more information: [tensorflow-framework/README.md](tensorflow-framework/README.md)

*Note: The NdArray Library module has now its own [repository](https://github.com/tensorflow/java-ndarray) and has been moved out of TensorFlow Java.*

## Communication

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join the group
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
There is also a [sig-jvm Gitter channel](https://gitter.im/tensorflow/sig-jvm).

## Building Sources

See [CONTRIBUTING.md](CONTRIBUTING.md#building).

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
  <version>0.3.1</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.3.1</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
```

On the other hand, if you plan to deploy your JAR on more platforms, you need additional
native dependencies as follows:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.3.1</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.3.1</version>
  <classifier>linux-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.3.1</version>
  <classifier>macosx-x86_64${javacpp.platform.extension}</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.3.1</version>
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
  <version>0.3.1</version>
</dependency>
```

Be aware though that the native library is quite large and including too many versions of it may
significantly increase  the size of your JAR. So it is good practice to limit your dependencies to
the platforms you are targeting. For this purpose the `-platform` artifacts include profiles that follow
the conventions established on this page:
* [Reducing the Number of Dependencies](https://github.com/bytedeco/javacpp-presets/wiki/Reducing-the-Number-of-Dependencies)

### Kotlin API

Since the Kotlin API is just a wrapper of the Java API, it uses the Java platform artifacts instead of providing its own.
To use, follow the instructions above for the Java API, but add `tensorflow-core-kotlin-api`, 
replacing `tensorflow-core-api` if you have explicitly included it.

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
        <version>0.4.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## TensorFlow Version Support

This table shows the mapping between different version of TensorFlow for Java and the core runtime libraries.

| TensorFlow Java Version  | TensorFlow Version |
| ------------- | ------------- |
| 0.2.0  | 2.3.1  |
| 0.3.0  | 2.4.1  |
| 0.3.1  | 2.4.1  |
| 0.4.0-SNAPSHOT | 2.5.0

## How to Contribute?

Contributions are welcome, guidelines are located in [CONTRIBUTING.md](CONTRIBUTING.md).
