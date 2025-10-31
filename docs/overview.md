TensorFlow-Java provides a Java API for TensorFlow, wrapped around TensorFlow's C API.
It allows the training and deployment of models using TensorFlow, with a focus on serving models trained using
other TensorFlow APIs.

It is split into 4 modules:
- tensorflow - The core TF-Java module which provides functionality for loading, saving and deploying TensorFlow models, along with Java definitions of all TensorFlow operators.
- tensorflow.framework - Higher level framework functionality for optimizers, losses, metrics etc.</li>
- tensorflow.generator - Java side op generation code from TensorFlow's op definition protobufs.</li>
- tensorflow.native - The low level Java - C API binding.</li>

TensorFlow-Java is developed in the open on [GitHub](https://github.com/tensorflow/java) by members of the TensorFlow community.
