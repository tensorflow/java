Saved model created using Python @tf.function, tensorflow version 2.3.0.

Python code used to create saved model below:

import tensorflow as tf

class MyModel(tf.keras.Model):
  def __init__(self):
    super(MyModel, self).__init__()
    self.const_scalar = tf.constant(0.0)
    self.const_vector = tf.constant([0.0, 0.0, 0.0])
    self.const_matrix = tf.constant([[0.0, 0.0, 0.0], [0.0, 0.0, 0.0]])

  @tf.function(input_signature=[tf.TensorSpec(shape=None, dtype=tf.float32, name='request')])
  def serve(self, x):
    return self.const_scalar + x

  @tf.function(input_signature=[tf.TensorSpec(shape=None, dtype=tf.float32, name='input')])
  def get_scalar(self, x):
    return self.const_scalar + x

  @tf.function(input_signature=[tf.TensorSpec(shape=None, dtype=tf.float32, name='input')])
  def get_vector(self, x):
    return self.const_vector + x

  @tf.function(input_signature=[tf.TensorSpec(shape=None, dtype=tf.float32, name='input')])
  def get_matrix(self, x):
    return self.const_matrix + x

  @tf.function(input_signature=[
    tf.TensorSpec(shape=None, dtype=tf.float32, name='a'),
    tf.TensorSpec(shape=None, dtype=tf.float32, name='b')])
  def add(self, a, b):
    return a + b

model = MyModel()

signatures = {
  "get_const_scalar": model.get_scalar,
  "get_const_vector": model.get_vector,
  "get_const_matrix": model.get_matrix,
  "add": model.add
}

tf.saved_model.save(obj=model, export_dir='model', signatures=signatures)
