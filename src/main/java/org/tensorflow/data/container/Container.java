package org.tensorflow.data.container;

import java.util.function.Function;

public interface Container<T> extends Iterable<T> {
    <U> Container<U> map(Function<T, U> mapper);
    int size();
}
