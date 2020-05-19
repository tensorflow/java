package org.tensorflow.framework.datasets;

import java.util.Objects;

public class Version {
  int MAJOR;
  int MINOR;
  int PATCH;

  public Version(int major, int minor, int patch) {
    this.MAJOR = major;
    this.MINOR = minor;
    this.PATCH = patch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Version version = (Version) o;
    return MAJOR == version.MAJOR &&
        MINOR == version.MINOR &&
        PATCH == version.PATCH;
  }

  @Override
  public int hashCode() {
    return Objects.hash(MAJOR, MINOR, PATCH);
  }

  @Override
  public String toString() {
    return MAJOR + "." + MINOR + "." + PATCH;
  }
}
