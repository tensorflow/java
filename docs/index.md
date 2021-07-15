# TensorFlow for Java

<table class="tfo-notebook-buttons" align="left">
    <td>
    <a target="_blank" href="https://www.tensorflow.org/jvm"><img src="https://www.tensorflow.org/images/tf_logo_32px.png" />View on TensorFlow.org</a>
  </td>
  <td>
    <a target="_blank" href="https://github.com/tensorflow/java"><img src="https://www.tensorflow.org/images/GitHub-Mark-32px.png" />View GitHub repository</a>
  </td>
</table>

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

* [tensorflow-core](https://github.com/tensorflow/java/tree/master/tensorflow-core)
  * All artifacts that build up the core language bindings of TensorFlow for Java
  * Intended audience: projects that provide their own APIs or frameworks on top of 
    TensorFlow and just want a thin layer to access the TensorFlow runtime from the JVM 

* [tensorflow-framework](https://github.com/tensorflow/java/tree/master/tensorflow-framework)
  * Primary API for building and training neural networks with TensorFlow
  * Intended audience: neural network developers

* [ndarray](https://github.com/tensorflow/java-ndarray)
  * Generic utility library for n-dimensional data I/O operations
  * Used by TensorFlow but does not depend on TensorFlow
  * Intended audience: any developer who needs a Java n-dimensional array implementation, whether or not they
    use it with TensorFlow


## Communication

This repository is maintained by TensorFlow JVM Special Interest Group (SIG). You can easily join the group
by subscribing to the [jvm@tensorflow.org](https://groups.google.com/a/tensorflow.org/forum/#!forum/jvm)
mailing list, or you can simply send pull requests and raise issues to this repository.
There is also a [sig-jvm Gitter channel](https://gitter.im/tensorflow/sig-jvm).


