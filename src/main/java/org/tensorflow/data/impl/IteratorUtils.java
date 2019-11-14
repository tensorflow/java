package org.tensorflow.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class IteratorUtils {
    public static <T> Iterator<T> filter(Predicate<T> predicate, Iterator<T> iterator) {
        return new Iterator<>() {
            private T next;

            private void findNextIfNull() {
                if (next == null) {
                    while (iterator.hasNext() && !predicate.test(next)) {
                        next = iterator.next();
                    }
                }
            }

            @Override
            public boolean hasNext() {
                findNextIfNull();
                return Objects.nonNull(next);
            }

            @Override
            public T next() {
                if (hasNext()) return next;
                throw new NoSuchElementException("Iterator has no more elements");
            }
        };
    }

    public static <T, U> Iterator<T> map(Function<U, T> mapper, Iterator<U> iterator) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return mapper.apply(iterator.next());
            }
        };
    }

    public static <U> List<U> take(long atMost, Iterator<U> from) {
        List<U> elements = new ArrayList<>();
        while (elements.size() < atMost && from.hasNext())
            elements.add(from.next());

        return elements;
    }

    public static <U> Iterator<U> batch(Iterator<U> iterator,
                                        Function<Iterable<U>, U> batchCollector,
                                        long batchSize, boolean dropRemainder) {

        return new Iterator<U>() {
            U collected = null;

            @Override
            public boolean hasNext() {
                List<U> batch = take(batchSize, iterator);
                collected = batchCollector.apply(batch);
                return dropRemainder || batch.size() == batchSize;
            }

            @Override
            public U next() {
                if (!hasNext()) {
                    return collected;
                }

                throw new NoSuchElementException();
            }
        };
    }

    public static <T> Operand<T>[] reduceBatch(Ops tf, Iterable<Operand<T>[]> batchOps) {
        Iterator<Operand<T>[]> elements = batchOps.iterator();
        Operand<T>[] batch = batchOps.iterator().next();

        while (elements.hasNext()) {
            Operand<T>[] element = elements.next();
            for (int i = 0; i < element.length; i++) {
                batch[i] = tf.concat(List.of(batch[i], element[i]), Constant.create(tf.scope(), 0));
            }
        }

        return batch;
    }
}
