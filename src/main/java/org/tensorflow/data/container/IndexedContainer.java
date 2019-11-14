package org.tensorflow.data.container;

import java.util.function.BiFunction;

public interface IndexedContainer<K, V> extends Container<V> {
    <T> IndexedContainer<K, T> map(BiFunction<K, V, T> mapper);
    V get(K key);
}
