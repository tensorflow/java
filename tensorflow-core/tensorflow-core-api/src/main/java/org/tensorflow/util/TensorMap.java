package org.tensorflow.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.tensorflow.types.family.TType;

public class TensorMap extends HashMap<String, TType> implements AutoCloseable {

  @Override
  public TType put(String key, TType value) {
    value.handle().retain();
    TType t = super.put(key, value);
    if (t != null) {
      t.handle().release();
    }
    return t;
  }

  @Override
  public TType remove(Object key) {
    TType t = super.remove(key);
    if (t != null) {
      t.handle().release();
    }
    return t;
  }

  @Override
  public void putAll(Map<? extends String, ? extends TType> m) {
    super.putAll(m);
    for (TType t : m.values()) {
      t.handle().retain();
    }
  }

  @Override
  public void clear() {
    for (TType t : values()) {
      t.handle().release();
    }
    super.clear();
  }

  @Override
  public void close() {
    clear();
  }

  public <T extends TType> T get(String name) {
    return (T) super.get(name);
  }

  public <T extends TType> T single() {
    if (size() != 1) {
      throw new IllegalStateException("List must contain a single tensor to use non-indexed getter");
    }
    return (T) values().iterator().next();
  }

  public TensorMap() {
    super();
  }

  public TensorMap(Collection<String> names, TensorList tensors) {
    Iterator<? extends TType> tensorIter = tensors.iterator();
    for (String name : names) {
      put(name, tensorIter.next());
    }
  }
}
