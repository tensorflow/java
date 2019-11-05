package org.tensorflow.sandbox.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * An immutable pair of things.
 *
 * @param <T1> The type of the first object.
 * @param <T2> The type of the second object.
 */
public class Pair<T1, T2> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final T1 a;

    private final T2 b;

    public Pair(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }

    public T1 getA() {
        return a;
    }

    public T2 getB() {
        return b;
    }

    @Override
    public int hashCode() {
        return a.hashCode() ^ b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        return Objects.equals(this.b, other.b);
    }

    @Override
    public String toString() {
        return "Pair{" + "a=" + a + ", b=" + b + '}';
    }
}
