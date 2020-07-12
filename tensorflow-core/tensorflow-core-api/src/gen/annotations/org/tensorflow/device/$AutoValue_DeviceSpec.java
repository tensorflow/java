package org.tensorflow.device;

import javax.annotation.Generated;
import javax.annotation.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
abstract class $AutoValue_DeviceSpec extends DeviceSpec {

  private final String job;

  private final Integer replica;

  private final Integer task;

  private final Integer deviceIndex;

  private final DeviceSpec.DeviceType deviceType;

  $AutoValue_DeviceSpec(
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

  @Nullable
  @Override
  public String job() {
    return job;
  }

  @Nullable
  @Override
  public Integer replica() {
    return replica;
  }

  @Nullable
  @Override
  public Integer task() {
    return task;
  }

  @Nullable
  @Override
  public Integer deviceIndex() {
    return deviceIndex;
  }

  @Nullable
  @Override
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
      return (this.job == null ? that.job() == null : this.job.equals(that.job()))
          && (this.replica == null ? that.replica() == null : this.replica.equals(that.replica()))
          && (this.task == null ? that.task() == null : this.task.equals(that.task()))
          && (this.deviceIndex == null ? that.deviceIndex() == null : this.deviceIndex.equals(that.deviceIndex()))
          && (this.deviceType == null ? that.deviceType() == null : this.deviceType.equals(that.deviceType()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (job == null) ? 0 : job.hashCode();
    h$ *= 1000003;
    h$ ^= (replica == null) ? 0 : replica.hashCode();
    h$ *= 1000003;
    h$ ^= (task == null) ? 0 : task.hashCode();
    h$ *= 1000003;
    h$ ^= (deviceIndex == null) ? 0 : deviceIndex.hashCode();
    h$ *= 1000003;
    h$ ^= (deviceType == null) ? 0 : deviceType.hashCode();
    return h$;
  }

  static class Builder extends DeviceSpec.Builder {
    private String job;
    private Integer replica;
    private Integer task;
    private Integer deviceIndex;
    private DeviceSpec.DeviceType deviceType;
    Builder() {
    }
    @Override
    DeviceSpec.Builder job(String job) {
      this.job = job;
      return this;
    }
    @Override
    DeviceSpec.Builder replica(Integer replica) {
      this.replica = replica;
      return this;
    }
    @Override
    DeviceSpec.Builder task(Integer task) {
      this.task = task;
      return this;
    }
    @Override
    DeviceSpec.Builder deviceIndex(Integer deviceIndex) {
      this.deviceIndex = deviceIndex;
      return this;
    }
    @Override
    DeviceSpec.Builder deviceType(DeviceSpec.DeviceType deviceType) {
      this.deviceType = deviceType;
      return this;
    }
    @Override
    DeviceSpec build() {
      return new AutoValue_DeviceSpec(
          this.job,
          this.replica,
          this.task,
          this.deviceIndex,
          this.deviceType);
    }
  }

}
