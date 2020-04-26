//package org.tensorflow.framework.data.impl;
//
//import org.tensorflow.Operand;
//import org.tensorflow.framework.data.Dataset;
//import org.tensorflow.op.Ops;
//import org.tensorflow.tools.Shape;
//import org.tensorflow.types.TInt64;
//import org.tensorflow.types.TString;
//
//import java.util.Collections;
//
//public class CSVDataset extends Dataset {
//  private final org.tensorflow.op.data.CSVDataset dataset;
//
//  public CSVDataset(Ops tf,
//                    Operand<TString> filenames,
//                    Operand<TString> compressionType,
//                    Operand<TInt64> bufferSize) {
//    super(tf, Collections.singletonList(TString.DTYPE),
//        Collections.singletonList(Shape.scalar()));
//
//    this.dataset = tf.data.cSVDataset(
//        filenames, compressionType, bufferSize,
//        tf.constant(""),
//        tf.constant(","),
//        tf.constant("header1"),);
//  }
//
//  @Override
//  public Operand<?> getVariant() {
//    return dataset;
//  }
//}
