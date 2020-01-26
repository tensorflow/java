package org.tensorflow.utils;

public class Tuple2<T, S> {
  private T first;
  private S second;

  public Tuple2(T t, S s) {
    this.first = t;
    this.second = s;
  }

  public T first() {
    return first;
  }

  public S second() {
    return second;
  }
}
