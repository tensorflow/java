package org.tensorflow.device;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.tensorflow.device.DeviceSpec.*;

/** Test for {@link DeviceSpec}. */
public class DeviceSpecTest {

  @Test
  public void EmptyDeviceSpec() {
    DeviceSpec emptySpec = builder().build();

    assertThat(emptySpec.job()).isNull();
    assertThat(emptySpec.toString()).isEmpty();
  }

  @Test
  public void JobOnlySpec() {
    DeviceSpec jobSpec = builder().job("ps").build();

    assertThat(jobSpec.toString()).isEqualTo("/job:ps");
  }

  @Test
  public void DeviceTypeOnlySpec() {
    DeviceSpec jobSpec = builder().deviceType(DeviceType.CPU).build();

    assertThat(jobSpec.toString()).isEqualTo("/device:CPU:*");
  }

  @Test
  public void DeviceTypeWithIndex() {
    DeviceSpec jobSpec = builder().deviceType(DeviceType.CPU).deviceIndex(1).build();

    assertThat(jobSpec.toString()).isEqualTo("/device:CPU:1");
  }

  @Test
  public void FullSpec() {
    DeviceSpec fullSpec =
        builder().job("ps").replica(1).task(0).deviceType(DeviceType.GPU).deviceIndex(0).build();

    assertThat(fullSpec.toString()).isEqualTo("/job:ps/replica:1/task:0/device:GPU:0");
  }
}
