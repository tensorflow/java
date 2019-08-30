/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.nio.buffer.impl;

import java.util.stream.Stream;

import org.tensorflow.nio.buffer.DataBuffer;

@SuppressWarnings("unchecked")
public class DataBufferWindow<T, B extends DataBuffer<T>> implements DataBuffer<T> {

    public DataBufferWindow(B delegate, long start, long end) {
        this.delegate = delegate;
        this.start = start;
        this.end = end;
    }

    @Override public long capacity() {
        return end - start;
    }

    @Override public long limit() {
        return delegate.limit() - start;
    }

    @Override
    public B limit(long newLimit) {
        return (B)delegate.limit(newLimit + start);
    }

    @Override
    public B withLimit(long limit) {
        return (B)duplicate().limit(limit);
    }

    @Override public boolean hasRemaining() {
        return delegate.hasRemaining();
    }

    @Override public long remaining() {
        return delegate.remaining();
    }

    @Override public long position() {
        return delegate.position() - start;
    }

    @Override
    public B position(long newPosition) {
        return (B)delegate.position(newPosition + start);
    }

    @Override
    public B withPosition(long position) {
        return (B)duplicate().position(position);
    }

    @Override
    public B rewind() {
        return (B)delegate.position(start);
    }

    @Override public boolean isReadOnly() {
        return delegate.isReadOnly();
    }

    @Override public T get() {
        return delegate.get();
    }

    @Override public T get(long index) {
        return delegate.get(index + start);
    }

    @Override public Stream<T> stream() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public B put(T value) {
        return (B)delegate.put(value);
    }

    @Override
    public B put(long index, T value) {
        return (B)delegate.put(index + start, value);
    }

    @Override
    public B put(DataBuffer<T> src) {
        return (B)delegate.put(src);
    }

    @Override
    public DataBuffer<T> duplicate() {
        return new DataBufferWindow(delegate.duplicate(), start, end);
    }

    @Override
    public DataBuffer<T> slice() {
        return new DataBufferWindow(delegate.duplicate(), position(), limit());
    }

    protected final B delegate;
    protected final long start;
    protected final long end;
}
