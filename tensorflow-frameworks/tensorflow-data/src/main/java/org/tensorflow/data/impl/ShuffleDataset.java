// package org.tensorflow.data.impl;

// import org.tensorflow.DataType;
// import org.tensorflow.Operand;
// import org.tensorflow.data.Dataset;
// import org.tensorflow.op.Ops;
// import org.tensorflow.op.core.Constant;
// import org.tensorflow.tools.Shape;
// import org.tensorflow.types.TInt64;

// import java.util.List;

// public class ShuffleDataset extends Dataset {
//   private org.tensorflow.op.data.ShuffleDataset shuffleDataset;

//   public ShuffleDataset(Ops tf, Operand<?> variant, Constant<TInt64> bufferSize,
//                         Constant<TInt64> seed, Constant<TInt64> seed2,
//                         List<DataType<?>> outputTypes, List<Shape> outputShapes) {
//     super(tf, outputTypes, outputShapes);
//     this.shuffleDataset = tf.data.shuffleDataset(variant, bufferSize, seed, seed2, outputTypes, outputShapes);
//   }

//   @Override
//   public Operand<?> getVariant() {
//     return shuffleDataset;
//   }
// }
