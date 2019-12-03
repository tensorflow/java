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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Creates a dataset that emits the messages of one or more Kafka topics.
 */
@Operator(group = "data")
public final class KafkaDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new KafkaDataset operation.
   * 
   * @param scope current scope
   * @param topics A `tf.string` tensor containing one or more subscriptions,
   * in the format of [topic:partition:offset:length],
   * by default length is -1 for unlimited.
   * @param servers A list of bootstrap servers.
   * @param group The consumer group id.
   * @param eof If True, the kafka reader will stop on EOF.
   * @param timeout The timeout value for the Kafka Consumer to wait
   * (in millisecond).
   * @param configGlobal A `tf.string` tensor containing global configuration
   * properties in [Key=Value] format,
   * eg. ["enable.auto.commit=false", "heartbeat.interval.ms=2000"],
   * please refer to 'Global configuration properties' in librdkafka doc.
   * @param configTopic A `tf.string` tensor containing topic configuration
   * properties in [Key=Value] format, eg. ["auto.offset.reset=earliest"],
   * please refer to 'Topic configuration properties' in librdkafka doc.
   * @return a new instance of KafkaDataset
   */
  public static KafkaDataset create(Scope scope, Operand<TString> topics, Operand<TString> servers, Operand<TString> group, Operand<TBool> eof, Operand<TInt64> timeout, Operand<TString> configGlobal, Operand<TString> configTopic) {
    OperationBuilder opBuilder = scope.env().opBuilder("KafkaDataset", scope.makeOpName("KafkaDataset"));
    opBuilder.addInput(topics.asOutput());
    opBuilder.addInput(servers.asOutput());
    opBuilder.addInput(group.asOutput());
    opBuilder.addInput(eof.asOutput());
    opBuilder.addInput(timeout.asOutput());
    opBuilder.addInput(configGlobal.asOutput());
    opBuilder.addInput(configTopic.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    return new KafkaDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  private KafkaDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
