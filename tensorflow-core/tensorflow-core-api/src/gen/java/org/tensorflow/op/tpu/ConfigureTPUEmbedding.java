/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.tpu;

import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Sets up TPUEmbedding in a distributed TPU system.
 */
public final class ConfigureTPUEmbedding extends PrimitiveOp {
  
  /**
   * Factory method to create a class wrapping a new ConfigureTPUEmbedding operation.
   * 
   * @param scope current scope
   * @param config Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
   * describes the embedding lookups of the program.
   * @return a new instance of ConfigureTPUEmbedding
   */
  public static ConfigureTPUEmbedding create(Scope scope, String config) {
    OperationBuilder opBuilder = scope.env().opBuilder("ConfigureTPUEmbedding", scope.makeOpName("ConfigureTPUEmbedding"));
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("config", config);
    return new ConfigureTPUEmbedding(opBuilder.build());
  }
  
  
  private ConfigureTPUEmbedding(Operation operation) {
    super(operation);
  }
}
