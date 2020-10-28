package org.tensorflow.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;
import org.tensorflow.types.family.TType;

public class TensorList implements AutoCloseable {

  public boolean isEmpty() {
    return tensors.isEmpty();
  }

  public int size() {
    return tensors.size();
  }

  public Iterator<? extends TType> iterator() {
    return tensors.iterator();
  }

  public ListIterator<? extends TType> listIterator() {
    return tensors.listIterator();
  }

  public Stream<? extends TType> stream() {
    return tensors.stream();
  }

  public <T extends TType> boolean add(T tensor) {
    return tensors.add(tensor);
  }

  public <T extends TType<?>> T single() {
    if (tensors.size() != 1) {
      throw new IllegalStateException("List must contain a single tensor to use non-indexed getter");
    }
    return get(0);
  }

  public <T extends TType<?>> T get(int index) {
    return (T)tensors.get(index);
  }

  public TensorMap toMap(Collection<String> tensorNames) {
    return new TensorMap(tensorNames, this);
  }

  @Override
  public void close() {
    for (TType<?> t : tensors) {
      t.tensorHandle().nativeHandle().releaseReference();
    }
  }

  private final List<TType> tensors = new ArrayList<>();
}
