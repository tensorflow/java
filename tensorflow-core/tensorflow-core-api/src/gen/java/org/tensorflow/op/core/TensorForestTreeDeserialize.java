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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Deserializes a proto into the tree handle
 */
public final class TensorForestTreeDeserialize extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorForestTreeDeserialize";

  private TensorForestTreeDeserialize(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new TensorForestTreeDeserialize operation.
   *
   * @param scope current scope
   * @param treeHandle Handle to the tree resource to be restored.
   * @param treeConfig Serialied proto string of the boosted_trees.Tree proto.
   * @return a new instance of TensorForestTreeDeserialize
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorForestTreeDeserialize create(Scope scope, Operand<? extends TType> treeHandle,
      Operand<TString> treeConfig) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorForestTreeDeserialize", scope.makeOpName("TensorForestTreeDeserialize"));
    opBuilder.addInput(treeHandle.asOutput());
    opBuilder.addInput(treeConfig.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TensorForestTreeDeserialize(opBuilder.build());
  }
}
