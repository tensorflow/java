package org.tensorflow;

import java.util.Objects;

/**
 * Represents a (possibly partial) specification for a TensorFlow device.
 *
 * <p>This is a Java port from python device_spec.py.
 *
 * @see <a href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/python/framework/device_spec.py">device_spec.py</a>.
 */
public final class DeviceSpec {
  private static final String JOB_PREFIX = "/job:";
  private static final String REPLICA_PREFIX = "/replica:";
  private static final String TASK_PREFIX = "/task:";
  private static final String DEVICE_PREFIX = "/device:";

  public enum DeviceType {
    CPU,
    GPU,
    TPU,
    CUSTOM
  }

  private final String job;

  private final Integer replica;

  private final Integer task;

  private final Integer deviceIndex;

  private final DeviceSpec.DeviceType deviceType;

  public String job() {
    return job;
  }

  public Integer replica() {
    return replica;
  }

  public Integer task() {
    return task;
  }

  public Integer deviceIndex() {
    return deviceIndex;
  }

  public DeviceSpec.DeviceType deviceType() {
    return deviceType;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof DeviceSpec) {
      DeviceSpec that = (DeviceSpec) o;
      return Objects.equals(job, that.job)
          && Objects.equals(replica, that.replica)
          && Objects.equals(task, that.task)
          && Objects.equals(deviceIndex, that.deviceIndex)
          && Objects.equals(deviceType, that.deviceType);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(job, replica, task, deviceIndex, deviceType);
  }

  @Override
  public String toString() {
    String deviceString = null;
    if (deviceType() != null) {
      String deviceSuffix = deviceIndex() == null ? "*" : deviceIndex().toString();
      deviceString = String.join(":", deviceType().toString().toUpperCase(), deviceSuffix);
    }
    return String.join(
        "",
        EmptyOrWithPrefix(job(), JOB_PREFIX),
        EmptyOrWithPrefix(replica(), REPLICA_PREFIX),
        EmptyOrWithPrefix(task(), TASK_PREFIX),
        EmptyOrWithPrefix(deviceString, DEVICE_PREFIX));
  }

  private DeviceSpec(
      String job,
      Integer replica,
      Integer task,
      Integer deviceIndex,
      DeviceSpec.DeviceType deviceType) {
    this.job = job;
    this.replica = replica;
    this.task = task;
    this.deviceIndex = deviceIndex;
    this.deviceType = deviceType;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  private static String EmptyOrWithPrefix(String s, String prefix) {
    return s == null ? "" : prefix + s;
  }

  private static String EmptyOrWithPrefix(Integer i, String prefix) {
    return i == null ? "" : prefix + i.toString();
  }

  /** A Builder class for building {@link DeviceSpec} class. */
  public static class Builder {
    private String job = null;
    private Integer replica = null;
    private Integer task = null;
    private Integer deviceIndex = null;
    private DeviceSpec.DeviceType deviceType = null;

    private Builder() {}

    public Builder job(String job) {
      this.job = job;
      return this;
    }

    public Builder replica(Integer replica) {
      this.replica = replica;
      return this;
    }

    public Builder task(Integer task) {
      this.task = task;
      return this;
    }

    public Builder deviceIndex(Integer deviceIndex) {
      this.deviceIndex = deviceIndex;
      return this;
    }

    public Builder deviceType(DeviceSpec.DeviceType deviceType) {
      this.deviceType = deviceType;
      return this;
    }

    public DeviceSpec build() {
      return new DeviceSpec(this.job, this.replica, this.task, this.deviceIndex, this.deviceType);
    }
  }
}
