/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.
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

#include "absl/types/span.h"

using namespace absl;

template<typename T> class SpanAdapter {
public:
    SpanAdapter(T const * ptr, typename Span<T>::size_type size, void* owner) : ptr((T*)ptr), size(size), owner(owner),
        arr2(ptr ? Span<T>((T*)ptr, size) : Span<T>()), arr(arr2) { }
    SpanAdapter(const Span<T>& arr) : ptr(0), size(0), owner(0), arr2(arr), arr(arr2) { }
    SpanAdapter(      Span<T>& arr) : ptr(0), size(0), owner(0), arr(arr) { }
    SpanAdapter(const Span<T>* arr) : ptr(0), size(0), owner(0), arr(*(Span<T>*)arr) { }
    void assign(T* ptr, typename Span<T>::size_type size, void* owner) {
        this->ptr = ptr;
        this->size = size;
        this->owner = owner;
        arr.set(ptr, size);
    }
    static void deallocate(void* owner) { free(owner); }
    operator T*()             { size = arr.size(); return (T*)arr.data(); }
    operator Span<T>&() { return arr; }
    operator Span<T>*() { return ptr ? &arr : 0; }
    T* ptr;
    typename Span<T>::size_type size;
    void* owner;
    Span<T> arr2;
    Span<T>& arr;
};

