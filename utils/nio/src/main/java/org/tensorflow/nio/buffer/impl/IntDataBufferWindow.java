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

import java.util.stream.IntStream;

import org.tensorflow.nio.buffer.IntDataBuffer;

public class IntDataBufferWindow extends DataBufferWindow<Integer, IntDataBuffer> implements IntDataBuffer {

    public IntDataBufferWindow(IntDataBuffer delegate, long start, long end) {
        super(delegate, start, end);
    }

    @Override public IntStream intStream() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override public IntDataBuffer get(int[] dst, int offset, int length) {
        return delegate.get(dst, offset, length);
    }

    @Override public IntDataBuffer put(int[] src, int offset, int length) {
        return delegate.put(src, offset, length);
    }

    @Override
    public IntDataBuffer duplicate() {
        return new IntDataBufferWindow(delegate.duplicate(), start, end);
    }

    @Override
    public IntDataBuffer slice() {
        return new IntDataBufferWindow(delegate.duplicate(), position(), limit());
    }
}
