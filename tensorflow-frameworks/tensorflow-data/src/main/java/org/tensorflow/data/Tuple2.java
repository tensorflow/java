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

  public static <T, S> Tuple2<T, S> of(T t, S s) {
    return new Tuple2<>(t, s);
  }
}
