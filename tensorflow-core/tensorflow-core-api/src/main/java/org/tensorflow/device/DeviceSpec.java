package org.tensorflow.device;

import com.google.common.base.Joiner;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Represents a (possibly partial) specification for a TensorFlow device.
 *
 * <p>This is a Java port from python device_spec.py.
 *
 * @see <a
 *     href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/python/framework/device_spec.py">device_spec.py</a>.
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

  @Nullable private final String job;

  @Nullable private final Integer replica;

  @Nullable private final Integer task;

  @Nullable private final Integer deviceIndex;

  @Nullable private final DeviceSpec.DeviceType deviceType;

  @Nullable
  public String job() {
    return job;
  }

  @Nullable
  public Integer replica() {
    return replica;
  }

  @Nullable
  public Integer task() {
    return task;
  }

  @Nullable
  public Integer deviceIndex() {
    return deviceIndex;
  }

  @Nullable
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
    Joiner specJoiner = Joiner.on("").skipNulls();
    String deviceString = null;
    if (deviceType() != null) {
      String deviceSuffix = deviceIndex() == null ? "*" : deviceIndex().toString();
      deviceString = Joiner.on(":").join(deviceType().toString().toUpperCase(), deviceSuffix);
    }
    return specJoiner.join(
        NullOrWithPrefix(job(), JOB_PREFIX),
        NullOrWithPrefix(replica(), REPLICA_PREFIX),
        NullOrWithPrefix(task(), TASK_PREFIX),
        NullOrWithPrefix(deviceString, DEVICE_PREFIX));
  }

  private DeviceSpec(
      @Nullable String job,
      @Nullable Integer replica,
      @Nullable Integer task,
      @Nullable Integer deviceIndex,
      @Nullable DeviceSpec.DeviceType deviceType) {
    this.job = job;
    this.replica = replica;
    this.task = task;
    this.deviceIndex = deviceIndex;
    this.deviceType = deviceType;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Nullable
  private static String NullOrWithPrefix(@Nullable String s, String prefix) {
    return s == null ? null : prefix + s;
  }

  @Nullable
  private static String NullOrWithPrefix(@Nullable Integer i, String prefix) {
    return i == null ? null : prefix + i.toString();
  }

  /** A Builder class for building {@link DeviceSpec} class. */
  static class Builder {
    private String job = null;
    private Integer replica = null;
    private Integer task = null;
    private Integer deviceIndex = null;
    private DeviceSpec.DeviceType deviceType = null;

    private Builder() {}

    Builder job(String job) {
      this.job = job;
      return this;
    }

    Builder replica(Integer replica) {
      this.replica = replica;
      return this;
    }

    Builder task(Integer task) {
      this.task = task;
      return this;
    }

    Builder deviceIndex(Integer deviceIndex) {
      this.deviceIndex = deviceIndex;
      return this;
    }

    Builder deviceType(DeviceSpec.DeviceType deviceType) {
      this.deviceType = deviceType;
      return this;
    }

    public DeviceSpec build() {
      return new DeviceSpec(this.job, this.replica, this.task, this.deviceIndex, this.deviceType);
    }
  }
}
