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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;

/**
 * Return the index of device the op runs.
 * <p>
 * Given a list of device names, this operation returns the index of the device
 * this op runs. The length of the list is returned in two cases:
 * (1) Device does not exist in the given device list.
 * (2) It is in XLA compilation.
 */
public final class DeviceIndex extends RawOp implements Operand<TInt32> {
  
  /**
   * Factory method to create a class wrapping a new DeviceIndex operation.
   * 
   * @param scope current scope
   * @param deviceNames 
   * @return a new instance of DeviceIndex
   */
  @Endpoint(describeByClass = true)
  public static DeviceIndex create(Scope scope, List<String> deviceNames) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeviceIndex", scope.makeOpName("DeviceIndex"));
    opBuilder = scope.apply(opBuilder);
    String[] deviceNamesArray = new String[deviceNames.size()];
    for (int i = 0; i < deviceNamesArray.length; ++i) {
      deviceNamesArray[i] = deviceNames.get(i);
    }
    opBuilder.setAttr("device_names", deviceNamesArray);
    return new DeviceIndex(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt32> index() {
    return index;
  }
  
  @Override
  public Output<TInt32> asOutput() {
    return index;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "DeviceIndex";
  
  private Output<TInt32> index;
  
  private DeviceIndex(Operation operation) {
    super(operation);
    int outputIdx = 0;
    index = operation.output(outputIdx++);
  }
}
