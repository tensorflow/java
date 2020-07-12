package org.tensorflow.device;

import com.google.auto.value.AutoValue;
import com.google.auto.value.extension.memoized.Memoized;
import com.google.common.base.Joiner;

import javax.annotation.Nullable;

/**
 * Represents a (possibly partial) specification for a TensorFlow device.
 *
 * <p>This is a Java port from python device_spec.py.
 *
 * @see <a
 *     href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/python/framework/device_spec.py">device_spec.py</a>.
 */
@AutoValue
public abstract class DeviceSpec {
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

  @Nullable
  public abstract String job();

  @Nullable
  public abstract Integer replica();

  @Nullable
  public abstract Integer task();

  @Nullable
  public abstract Integer deviceIndex();

  @Nullable
  public abstract DeviceType deviceType();

  @Memoized
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

  static Builder builder() {
    return new AutoValue_DeviceSpec.Builder();
  }

  // Builder
  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder job(String job);

    abstract Builder replica(Integer replica);

    abstract Builder task(Integer task);

    abstract Builder deviceIndex(Integer deviceIndex);

    abstract Builder deviceType(DeviceType deviceType);

    abstract DeviceSpec build();
  }

  @Nullable
  private static String NullOrWithPrefix(@Nullable String s, String prefix) {
    return s == null ? null : prefix + s;
  }

  @Nullable
  private static String NullOrWithPrefix(@Nullable Integer i, String prefix) {
    return i == null ? null : prefix + i.toString();
  }
}
