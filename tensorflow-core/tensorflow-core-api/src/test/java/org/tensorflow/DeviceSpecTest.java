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

import static com.google.common.truth.Truth.assertThat;
import static org.tensorflow.DeviceSpec.DeviceType;

/** Tests for {@link DeviceSpec}. */
public class DeviceSpecTest {

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
