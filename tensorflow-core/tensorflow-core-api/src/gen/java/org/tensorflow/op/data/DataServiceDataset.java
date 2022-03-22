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

package org.tensorflow.op.data;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Creates a dataset that reads data from the tf.data service.
 */
@OpMetadata(
    opType = DataServiceDataset.OP_NAME,
    inputsClass = DataServiceDataset.Inputs.class
)
@Operator(
    group = "data"
)
public final class DataServiceDataset extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DataServiceDatasetV3";

  private Output<? extends TType> handle;

  @SuppressWarnings("unchecked")
  public DataServiceDataset(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DataServiceDatasetV3 operation.
   *
   * @param scope current scope
   * @param datasetId The datasetId value
   * @param processingMode The processingMode value
   * @param address The address value
   * @param protocol The protocol value
   * @param jobName The jobName value
   * @param consumerIndex The consumerIndex value
   * @param numConsumers The numConsumers value
   * @param maxOutstandingRequests The maxOutstandingRequests value
   * @param iterationCounter The iterationCounter value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param uncompressFn The value of the uncompressFn attribute
   * @param options carries optional attribute values
   * @return a new instance of DataServiceDataset
   */
  @Endpoint(
      describeByClass = true
  )
  public static DataServiceDataset create(Scope scope, Operand<TInt64> datasetId,
      Operand<TString> processingMode, Operand<TString> address, Operand<TString> protocol,
      Operand<TString> jobName, Operand<TInt64> consumerIndex, Operand<TInt64> numConsumers,
      Operand<TInt64> maxOutstandingRequests, Operand<? extends TType> iterationCounter,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ConcreteFunction uncompressFn, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DataServiceDataset");
    opBuilder.addInput(datasetId.asOutput());
    opBuilder.addInput(processingMode.asOutput());
    opBuilder.addInput(address.asOutput());
    opBuilder.addInput(protocol.asOutput());
    opBuilder.addInput(jobName.asOutput());
    opBuilder.addInput(consumerIndex.asOutput());
    opBuilder.addInput(numConsumers.asOutput());
    opBuilder.addInput(maxOutstandingRequests.asOutput());
    opBuilder.addInput(iterationCounter.asOutput());
    opBuilder.setAttr("output_types", Operands.toDataTypes(outputTypes));
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0 ; i < outputShapesArray.length ; i++) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    opBuilder.setAttr("uncompress_fn", uncompressFn);
    if (options != null) {
      for (Options opts : options) {
        if (opts.taskRefreshIntervalHintMs != null) {
          opBuilder.setAttr("task_refresh_interval_hint_ms", opts.taskRefreshIntervalHintMs);
        }
        if (opts.dataTransferProtocol != null) {
          opBuilder.setAttr("data_transfer_protocol", opts.dataTransferProtocol);
        }
        if (opts.targetWorkers != null) {
          opBuilder.setAttr("target_workers", opts.targetWorkers);
        }
        if (opts.uncompress != null) {
          opBuilder.setAttr("uncompress", opts.uncompress);
        }
      }
    }
    return new DataServiceDataset(opBuilder.build());
  }

  /**
   * Sets the taskRefreshIntervalHintMs option.
   *
   * @param taskRefreshIntervalHintMs the taskRefreshIntervalHintMs option
   * @return this Options instance.
   */
  public static Options taskRefreshIntervalHintMs(Long taskRefreshIntervalHintMs) {
    return new Options().taskRefreshIntervalHintMs(taskRefreshIntervalHintMs);
  }

  /**
   * Sets the dataTransferProtocol option.
   *
   * @param dataTransferProtocol the dataTransferProtocol option
   * @return this Options instance.
   */
  public static Options dataTransferProtocol(String dataTransferProtocol) {
    return new Options().dataTransferProtocol(dataTransferProtocol);
  }

  /**
   * Sets the targetWorkers option.
   *
   * @param targetWorkers the targetWorkers option
   * @return this Options instance.
   */
  public static Options targetWorkers(String targetWorkers) {
    return new Options().targetWorkers(targetWorkers);
  }

  /**
   * Sets the uncompress option.
   *
   * @param uncompress the uncompress option
   * @return this Options instance.
   */
  public static Options uncompress(Boolean uncompress) {
    return new Options().uncompress(uncompress);
  }

  /**
   * Gets handle.
   *
   * @return handle.
   */
  public Output<? extends TType> handle() {
    return handle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) handle;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.data.DataServiceDataset}
   */
  public static class Options {
    private Long taskRefreshIntervalHintMs;

    private String dataTransferProtocol;

    private String targetWorkers;

    private Boolean uncompress;

    private Options() {
    }

    /**
     * Sets the taskRefreshIntervalHintMs option.
     *
     * @param taskRefreshIntervalHintMs the taskRefreshIntervalHintMs option
     * @return this Options instance.
     */
    public Options taskRefreshIntervalHintMs(Long taskRefreshIntervalHintMs) {
      this.taskRefreshIntervalHintMs = taskRefreshIntervalHintMs;
      return this;
    }

    /**
     * Sets the dataTransferProtocol option.
     *
     * @param dataTransferProtocol the dataTransferProtocol option
     * @return this Options instance.
     */
    public Options dataTransferProtocol(String dataTransferProtocol) {
      this.dataTransferProtocol = dataTransferProtocol;
      return this;
    }

    /**
     * Sets the targetWorkers option.
     *
     * @param targetWorkers the targetWorkers option
     * @return this Options instance.
     */
    public Options targetWorkers(String targetWorkers) {
      this.targetWorkers = targetWorkers;
      return this;
    }

    /**
     * Sets the uncompress option.
     *
     * @param uncompress the uncompress option
     * @return this Options instance.
     */
    public Options uncompress(Boolean uncompress) {
      this.uncompress = uncompress;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DataServiceDataset.class
  )
  public static class Inputs extends RawOpInputs<DataServiceDataset> {
    /**
     * The datasetId input
     */
    public final Operand<TInt64> datasetId;

    /**
     * The processingMode input
     */
    public final Operand<TString> processingMode;

    /**
     * The address input
     */
    public final Operand<TString> address;

    /**
     * The protocol input
     */
    public final Operand<TString> protocol;

    /**
     * The jobName input
     */
    public final Operand<TString> jobName;

    /**
     * The consumerIndex input
     */
    public final Operand<TInt64> consumerIndex;

    /**
     * The numConsumers input
     */
    public final Operand<TInt64> numConsumers;

    /**
     * The maxOutstandingRequests input
     */
    public final Operand<TInt64> maxOutstandingRequests;

    /**
     * The iterationCounter input
     */
    public final Operand<? extends TType> iterationCounter;

    /**
     * The taskRefreshIntervalHintMs attribute
     */
    public final long taskRefreshIntervalHintMs;

    /**
     * The outputTypes attribute
     */
    public final DataType[] outputTypes;

    /**
     * The outputShapes attribute
     */
    public final Shape[] outputShapes;

    /**
     * The dataTransferProtocol attribute
     */
    public final String dataTransferProtocol;

    /**
     * The targetWorkers attribute
     */
    public final String targetWorkers;

    /**
     * The uncompress attribute
     */
    public final boolean uncompress;

    public Inputs(GraphOperation op) {
      super(new DataServiceDataset(op), op, Arrays.asList("task_refresh_interval_hint_ms", "output_types", "output_shapes", "data_transfer_protocol", "target_workers", "uncompress"));
      int inputIndex = 0;
      datasetId = (Operand<TInt64>) op.input(inputIndex++);
      processingMode = (Operand<TString>) op.input(inputIndex++);
      address = (Operand<TString>) op.input(inputIndex++);
      protocol = (Operand<TString>) op.input(inputIndex++);
      jobName = (Operand<TString>) op.input(inputIndex++);
      consumerIndex = (Operand<TInt64>) op.input(inputIndex++);
      numConsumers = (Operand<TInt64>) op.input(inputIndex++);
      maxOutstandingRequests = (Operand<TInt64>) op.input(inputIndex++);
      iterationCounter = (Operand<? extends TType>) op.input(inputIndex++);
      taskRefreshIntervalHintMs = op.attributes().getAttrInt("task_refresh_interval_hint_ms");
      outputTypes = op.attributes().getAttrTypeList("output_types");
      outputShapes = op.attributes().getAttrShapeList("output_shapes");
      dataTransferProtocol = op.attributes().getAttrString("data_transfer_protocol");
      targetWorkers = op.attributes().getAttrString("target_workers");
      uncompress = op.attributes().getAttrBool("uncompress");
    }
  }
}
