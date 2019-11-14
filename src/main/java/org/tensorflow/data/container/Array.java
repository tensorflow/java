package org.tensorflow.data.container;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Array<T> implements IndexedContainer<Integer, T> {
    private T[] array;

    public Array(T... elements) {
        this.array = elements;
    }

    public Array(T first, T... remaining) {
        this.array = (T[]) new Object[remaining.length + 1];
        this.array[0] = first;
        System.arraycopy(remaining, 0, array, 1, remaining.length);
    }

    @Override
    public <U> Array<U> map(Function<T, U> mapper) {
       return map((t, u) -> mapper.apply(u));
    }

    @Override
    public <U> Array<U> map(BiFunction<Integer, T, U> mapper) {
        U[] transformed = (U[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            transformed[i] = mapper.apply(i, array[i]);
        }
        return new Array<>(transformed);
    }

    public T get(Integer i) {
        return array[i];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return Spliterators.iterator(Arrays.spliterator(array));
    }
}
