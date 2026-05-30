package org.tensorflow.ndarray.buffer.layout;

/**
 * Specifies the data format in tensor.
 * 
 * With the default format "NHWC", the data is stored in the order of:
 *     [batch, height, width, channels].
 * 
 * Alternatively, the format could be "NCHW", the data storage order of:
 *     [batch, channels, height, width].
 * 
 * Additional format NCHW_VECT_C is specified by 
 * https://docs.nvidia.com/deeplearning/cudnn/api/index.html#cudnnTensorFormat_
 * although not sure if it is used or needed in tf
 * 
 *  Even More formats are specified in https://docs.nvidia.com/deeplearning/cudnn/developer-guide/index.html#WXWZ-tensor-descriptor
 *  CHWN   4d tensor description
 *  NCDHW  5d tensor description
 *  NDHWC
 *  CDHWN
 * 
 * https://docs.nvidia.com/deeplearning/cudnn/api/index.html#cudnnTensorFormat_t
 * 
 // https://github.com/tensorflow/java/blob/f85623ed366d903cfddb75177725dc276f359b15/tensorflow-core/tensorflow-core-api/src/gen/java/org/tensorflow/op/nn/MaxPoolGradGrad.java

 */
public enum TensorFormat {
    NCHW, 
    NHWC;
}
