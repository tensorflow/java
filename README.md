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
    TensorFlow and just want a thin layer to access the TensorFlow native library from the JVM
    
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

There are two options for adding TensorFlow Java as a dependency to your Maven project: with individual dependencies 
for each targeted platforms or with a single dependency that target them all.

### Individual dependencies

With this option, you must first add an unclassified dependency to `tensorflow-core-api` and then add one or multiple
native dependencies to this same artifact with a classifier targeting a specific platform. This option is preferred as 
it minimize the size of your application by only including the TensorFlow builds you need, at the cost of being more 
restrictive. 

While TensorFlow Java can be compiled for [multiple platforms](https://github.com/tensorflow/java/blob/dc64755ee948c71f1321be27478828a51f1f3cf7/tensorflow-core/pom.xml#L54),
only binaries for the followings are being **supported and distributed** by this project:

- `linux-x86_64`: Linux platforms on Intel chips
- `linux-x86_64-gpu`: Linux platforms on Intel chips with Cuda GPU support
- `macosx-x86_64`: MacOS X platforms on Intel chips
- `windows-x86_64`: Windows platforms on Intel chips
- `windows-x86_64-gpu`: Windows platforms on Intel chips with Cuda GPU support

*Note: No binaries are distributed to run TensorFlow Java on machines with Apple Silicon chips (`macosx-arm64`), these 
should be build from sources. See [here](CONTRIBUTING.md#apple-silicon) for more details.*

For example, for building a JAR that uses TensorFlow and is targeted to be deployed only on Linux
systems with no GPU support, you should add the following dependencies:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
  <classifier>linux-x86_64</classifier>
</dependency>
```

On the other hand, if you plan to deploy your JAR on more platforms, you need additional
native dependencies as follows:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
  <classifier>linux-x86_64-gpu</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
  <classifier>macosx-x86_64</classifier>
</dependency>
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-api</artifactId>
  <version>0.5.1</version>
  <classifier>windows-x86_64-gpu</classifier>
</dependency>
```

Only one dependency can be added per platform, meaning that you cannot add native dependencies to both `linux-x86_64` and 
`linux-x86_64-gpu` within the same project.

### Single dependency

In some cases, it might be preferable to add a single dependency that includes transitively all the artifacts 
required to run TensorFlow Java on any [supported platforms](README.md#individual-dependencies)

- `tensorflow-core-platform`: Includes TenSupports for `linux-x86_64`, `macosx-x86_64` and `windows-x86_64`
- `tensorflow-core-platform-gpu`: Supports `linux-x86_64-gpu` and `windows-x86_64-gpu`

For example, to run TensorFlow Java on any platform for which a binary is being distributed by this project, you can 
simply add this dependency to your application:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-platform</artifactId>
  <version>0.5.1</version>
</dependency>
```
or this dependency if you want to run it only on platforms with GPU support:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-core-platform-gpu</artifactId>
  <version>0.5.1</version>
</dependency>
```

Be aware though that the builds of TensorFlow are quite voluminous and including too many native dependencies may
significantly increase the size of your application. So it is good practice to limit your dependencies to
the platforms you are targeting. For this purpose these artifacts include profiles that follow
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
        <version>0.6.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## TensorFlow/Java Version Support

This table shows the mapping between TensorFlow, TensorFlow Java and minimum supported Java versions.

| TensorFlow Java Version  | TensorFlow Version | Minimum Java Version |
| ------------- | ------------- | --------------- |
| 0.2.0  | 2.3.1  | 8 |
| 0.3.0  | 2.4.1  | 8 |
| 0.3.1  | 2.4.1  | 8 |
| 0.3.2  | 2.4.1  | 8 |
| 0.3.3  | 2.4.1  | 8 |
| 0.4.0  | 2.7.0  | 8 |
| 0.4.1  | 2.7.1  | 8 |
| 0.4.2  | 2.7.4  | 8 |
| 0.5.0  | 2.10.1 | 11 |
| 0.5.1  | 2.10.1 | 11 |

## How to Contribute?

Contributions are welcome, guidelines are located in [CONTRIBUTING.md](CONTRIBUTING.md).

## Code and Usage Examples

Please look at this repository: https://github.com/tensorflow/java-models
