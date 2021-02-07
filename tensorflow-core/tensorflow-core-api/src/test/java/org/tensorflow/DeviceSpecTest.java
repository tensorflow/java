/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow;

import org.junit.jupiter.api.Test;
import org.tensorflow.exceptions.TFInvalidArgumentException;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.types.TInt32;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tensorflow.DeviceSpec.DeviceType;

/** Tests for {@link DeviceSpec}. */
public class DeviceSpecTest {
  @Test
  public void withDeviceMethod() {
    ConfigProto config = ConfigProto.newBuilder(ConfigProto.getDefaultInstance())
            .setLogDevicePlacement(true)
            .build();

    try (Graph g = new Graph(); Session session = new Session(g, config)) {
      Ops tf = Ops.create(g).withSubScope("testScope");

      Constant<TInt32> aOps = tf.constant(-1);

      DeviceSpec deviceSpec = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      Output<TInt32> absOps = tf
              .withName("absWithDevice")
              .withDevice(deviceSpec)
              .math
              .abs(aOps)
              .asOutput();

      try (Session.Result t = session.runner().fetch(absOps).run()) {
        assertEquals(1, ((TInt32)t.get(0)).getInt());
      }
    }
  }

  @Test
  public void withEmptyDeviceSpec() {
    ConfigProto config = ConfigProto.newBuilder(ConfigProto.getDefaultInstance())
            .setLogDevicePlacement(true)
            .build();

    try (Graph g = new Graph(); Session session = new Session(g, config)) {
      Ops tf = Ops.create(g).withSubScope("testScope");

      Constant<TInt32> aOps = tf.constant(-1);

      DeviceSpec deviceSpec = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      Output<TInt32> absOps = tf
              .withName("absWithDevice")
              .withDevice(deviceSpec)
              .math
              .abs(aOps)
              .asOutput();

      try (Session.Result t = session.runner().fetch(absOps).run()) {
        assertEquals(1, ((TInt32)t.get(0)).getInt());
      }
    }
  }

  @Test
  public void withTwoScopes() {
    ConfigProto config = ConfigProto.newBuilder(ConfigProto.getDefaultInstance())
            .setLogDevicePlacement(true)
            .build();

    try (Graph g = new Graph(); Session session = new Session(g, config)) {
      DeviceSpec deviceSpec1 = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      DeviceSpec deviceSpec2 = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      Ops tf1 = Ops.create(g).withSubScope("testScope1").withDevice(deviceSpec1);
      Ops tf2 = Ops.create(g).withSubScope("testScope2").withDevice(deviceSpec2);

      Constant<TInt32> aOps = tf1.constant(-1);
      Constant<TInt32> bOps = tf2.constant(10);

      Output<TInt32> absOps = tf1
              .withName("absWithDevice")
              .math
              .abs(aOps)
              .asOutput();

      Output<TInt32> mulOps = tf2
              .withName("mulWithDevice")
              .math
              .mul(absOps, bOps)
              .asOutput();

      try (Session.Result t = session.runner().fetch(mulOps).run()) {
        assertEquals(10, ((TInt32)t.get(0)).getInt());
      }
    }
  }

  @Test
  public void withIncorrectDeviceSpec() {
    ConfigProto config = ConfigProto.newBuilder(ConfigProto.getDefaultInstance())
            .setLogDevicePlacement(true)
            .build();

    try (Graph g = new Graph(); Session session = new Session(g, config)) {
      DeviceSpec correctDeviceSpec = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      // Incorrect device spec, it will never be executed
      DeviceSpec incorrectDeviceSpec = DeviceSpec.newBuilder()
              .job("UNKNOWN")
              .replica(1)
              .task(1000)
              .deviceType(DeviceType.TPU)
              .build();

      Ops tf = Ops.create(g);

      Constant<TInt32> aOps = tf.constant(-1);
      Constant<TInt32> bOps = tf.constant(10);

      Output<TInt32> absOps = tf
              .withName("absWithDevice")
              .withDevice(incorrectDeviceSpec)
              .math
              .abs(aOps)
              .asOutput();

      Output<TInt32> mulOps = tf
              .withName("mulWithDevice")
              .withDevice(correctDeviceSpec)
              .math
              .mul(absOps, bOps)
              .asOutput();

      try (Session.Result t = session.runner().fetch(mulOps).run()) {
        fail();
      } catch (TFInvalidArgumentException e) {
        // ok
      }
    }
  }

  @Test
  public void withDeviceSpecInScope() {
    ConfigProto config = ConfigProto.newBuilder(ConfigProto.getDefaultInstance())
            .setLogDevicePlacement(true)
            .build();

    try (Graph g = new Graph(); Session session = new Session(g, config)) {
      DeviceSpec deviceSpec = DeviceSpec.newBuilder()
              .job("localhost")
              .replica(0)
              .task(0)
              .deviceType(DeviceSpec.DeviceType.CPU)
              .build();

      Ops tf = Ops.create(g).withSubScope("testScope").withDevice(deviceSpec);

      Constant<TInt32> aOps = tf.constant(-1);

      Output<TInt32> absOps = tf
              .withName("absWithDevice")
              .math
              .abs(aOps)
              .asOutput();

      try (Session.Result t = session.runner().fetch(absOps).run()) {
        assertEquals(1, ((TInt32)t.get(0)).getInt());
      }
    }
  }

  @Test
  public void emptyDeviceSpec() {
    DeviceSpec emptySpec = DeviceSpec.newBuilder().build();

    assertThat(emptySpec.job()).isNull();
    assertThat(emptySpec.toString()).isEmpty();
  }

  @Test
  public void jobOnlySpec() {
    DeviceSpec jobSpec = DeviceSpec.newBuilder().job("ps").build();

    assertThat(jobSpec.toString()).isEqualTo("/job:ps");
  }

  @Test
  public void deviceTypeOnlySpec() {
    DeviceSpec jobSpec = DeviceSpec.newBuilder().deviceType(DeviceType.CPU).build();

    assertThat(jobSpec.toString()).isEqualTo("/device:CPU:*");
  }

  @Test
  public void deviceTypeWithIndex() {
    DeviceSpec jobSpec = DeviceSpec.newBuilder().deviceType(DeviceType.CPU).deviceIndex(1).build();

    assertThat(jobSpec.toString()).isEqualTo("/device:CPU:1");
  }

  @Test
  public void fullSpec() {
    DeviceSpec fullSpec =
        DeviceSpec.newBuilder()
            .job("ps")
            .replica(1)
            .task(0)
            .deviceType(DeviceType.GPU)
            .deviceIndex(0)
            .build();

    assertThat(fullSpec.toString()).isEqualTo("/job:ps/replica:1/task:0/device:GPU:0");
  }

  @Test
  public void emptySpecEquals() {
    DeviceSpec emptySpec = DeviceSpec.newBuilder().build();

    assertThat(emptySpec).isEqualTo(DeviceSpec.newBuilder().build());
  }

  @Test
  public void fullSpecEquals() {
    DeviceSpec fullSpec =
        DeviceSpec.newBuilder()
            .job("ps")
            .replica(1)
            .task(0)
            .deviceType(DeviceType.GPU)
            .deviceIndex(0)
            .build();

    assertThat(fullSpec)
        .isEqualTo(
            DeviceSpec.newBuilder()
                .job("ps")
                .replica(1)
                .task(0)
                .deviceType(DeviceType.GPU)
                .deviceIndex(0)
                .build());
  }
}
