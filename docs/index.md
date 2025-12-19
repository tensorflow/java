# TensorFlow for Java

TensorFlow Java can run on any JVM for building, training and running machine learning models. It comes with 
a series of utilities and frameworks that help achieve most of the tasks common to data scientists 
and developers working in this domain. Java and other JVM languages, such as Scala or Kotlin, are 
frequently used in small-to-large enterprises all over the world, which makes TensorFlow a strategic 
choice for adopting machine learning at a large scale.

## The Repository

In the early days, the Java language bindings for TensorFlow were hosted in the
[main TensorFlow repository](https://github.com/tensorflow/tensorflow)
and released only when a new version of the core library was ready to be distributed, which happens only
a few times a year. Now, all Java-related code has been moved to this repository so that it can evolve and 
be released independently from official TensorFlow releases. In addition, most of the build tasks have been
migrated from Bazel to Maven, which is more familiar for most Java developers.

The following describes the layout of the repository and its different artifacts:

### [tensorflow-core](https://github.com/tensorflow/java/tree/master/tensorflow-core)
  * **Intended audience**: developers who wants to deploy a TensorFlow model on a JVM for inference. Also for projects
    that provide their own APIs or frameworks on top of TensorFlow and just want a thin layer to access the TensorFlow runtime from the JVM.
  * All artifacts that make up the core language bindings of TensorFlow for Java.

### [tensorflow-framework](https://github.com/tensorflow/java/tree/master/tensorflow-framework)
  * **Intended audience**: neural network developers.
  * Primary API for building and training neural networks with TensorFlow.

### [ndarray](https://github.com/tensorflow/java-ndarray)
  * **Intended audience**: any developer who needs a Java n-dimensional array implementation, whether or not they use it with TensorFlow.
  * Generic utility library for n-dimensional data I/O operations.
  * Used by TensorFlow but does not depend on TensorFlow.

## Communication

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily contact the group
by posting to the [TensorFlow Forum](https://discuss.tensorflow.org), adding the `sig_jvm` tag, or by writing to us on
the [sig-jvm Gitter channel](https://gitter.im/tensorflow/sig-jvm). You can also simply send pull requests
and raise issues to this repository.



