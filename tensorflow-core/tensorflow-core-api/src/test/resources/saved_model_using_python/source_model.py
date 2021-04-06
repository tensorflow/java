# Copyright 2020 The TensorFlow Authors. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ==============================================================================

# Saved model created using Python @tf.function, tensorflow version 2.3.0.
#
# Python code used to create saved model below
#
# WARNING: This script is just attached to the test code base for reference and is not used nor
# executed by this project to generate the saved model, which has been added manually.

import tensorflow as tf


class MyModel(tf.keras.Model):
  def __init__(self):
    super(MyModel, self).__init__()
    self.const_scalar = tf.constant(0.0)
    self.const_vector = tf.constant([0.0, 0.0, 0.0])
    self.const_matrix = tf.constant([[0.0, 0.0, 0.0], [0.0, 0.0, 0.0]])
    self.variable = tf.Variable(2.0)

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

  #TF functions always require an input
  @tf.function(input_signature=[
    tf.TensorSpec(shape=None, dtype=tf.float32, name='dummy')
  ])
  def get_variable(self, dummy):
    return self.variable


model = MyModel()

signatures = {
  "get_const_scalar": model.get_scalar,
  "get_const_vector": model.get_vector,
  "get_const_matrix": model.get_matrix,
  "add": model.add,
  "get_variable": model.get_variable
}

tf.saved_model.save(obj=model, export_dir='model', signatures=signatures)
