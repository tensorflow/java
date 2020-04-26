package org.tensorflow.framework.datasets;

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
  public String toString() {
    return MAJOR + "." + MINOR + "." + PATCH;
  }
}
