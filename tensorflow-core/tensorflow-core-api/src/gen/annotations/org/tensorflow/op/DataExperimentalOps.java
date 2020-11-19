// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.data.experimental.DataServiceDataset;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * An API for building {@code data.experimental} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DataExperimentalOps {
  private final Scope scope;

  private final Ops ops;

  DataExperimentalOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   *
   * @param datasetId
   * @param processingMode
   * @param address
   * @param protocol
   * @param jobName
   * @param maxOutstandingRequests
   * @param iterationCounter
   * @param outputTypes
   * @param outputShapes
   * @param options carries optional attributes values
   * @return a new instance of DataServiceDataset
   */
  public DataServiceDataset dataServiceDataset(Operand<TInt64> datasetId,
      Operand<TString> processingMode, Operand<TString> address, Operand<TString> protocol,
      Operand<TString> jobName, Operand<TInt64> maxOutstandingRequests, Operand<?> iterationCounter,
      List<DataType<?>> outputTypes, List<Shape> outputShapes,
      DataServiceDataset.Options... options) {
    return DataServiceDataset.create(scope, datasetId, processingMode, address, protocol, jobName, maxOutstandingRequests, iterationCounter, outputTypes, outputShapes, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
