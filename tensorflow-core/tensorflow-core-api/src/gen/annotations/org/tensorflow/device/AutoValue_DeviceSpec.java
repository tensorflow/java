package org.tensorflow.device;

import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import javax.annotation.Generated;

@Generated("com.google.auto.value.extension.memoized.processor.MemoizeExtension")
final class AutoValue_DeviceSpec extends $AutoValue_DeviceSpec {
  private transient volatile String toString;

  AutoValue_DeviceSpec(String job$, Integer replica$, Integer task$, Integer deviceIndex$,
      DeviceSpec.DeviceType deviceType$) {
    super(job$, replica$, task$, deviceIndex$, deviceType$);
  }

  @Override
  public String toString() {
    if (toString == null) {
      synchronized (this) {
        if (toString == null) {
          toString = super.toString();
          if (toString == null) {
            throw new NullPointerException("toString() cannot return null");
          }
        }
      }
    }
    return toString;
  }
}
