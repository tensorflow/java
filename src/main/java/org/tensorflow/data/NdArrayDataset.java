package org.tensorflow.data;

import org.tensorflow.nio.nd.NdArray;

import static org.tensorflow.nio.StaticApi.range;

public class NioArrayDataset<T> implements ArrayDataset<NdArray<T>> {
    private NdArray<T>[] dataArrays;

    private long batchSize = 1;
    private boolean dropRemainder = false;

    @SafeVarargs
    public NioArrayDataset(NdArray<T> firstArray, NdArray<T>... arrays) {
        this.dataArrays = (NdArray<T>[]) new NdArray[arrays.length + 1];
        this.dataArrays[0] = firstArray;
        System.arraycopy(arrays, 0, this.dataArrays, 1, arrays.length);
    }

    @Override
    public Dataset<NdArray<T>, NdArray<T>[]> batch(long batchSize, boolean dropLastBatch) {
        this.batchSize = batchSize;
        this.dropRemainder = dropLastBatch;
        return this;
    }

    public NdArray<T>[] getBatch(long batchIndex) {
        return getSlice(batchIndex * batchSize, batchSize);
    }

    public NdArray<T>[] getSlice(long start, long size) {
        NdArray<T>[] slices = (NdArray<T>[]) new NdArray[this.dataArrays.length];
        for (int i = 0; i < slices.length; i++) {
            slices[i] = dataArrays[i].slice(range(start, start + size));
        }
        return slices;
    }
}
