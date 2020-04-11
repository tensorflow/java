package org.tensorflow.data;

import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.List;

/**
 * Represents the state of an iteration through
 * a tf.data Datset. DatasetIterator is not
 * a java.util.Iterator. In eager mode, `Dataset`
 * can be used as an Iterable, returning dataset
 * elements each iteration.
 *
 * <p>
 * Example: Iteration in graph mode.
 *
 * <pre>{@code
 *  // Create input tensors
 *  Operand<?> XTensor = tf.constant( ... );
 *  Operand<?> yTensor = tf.constant( ... );
 *
 *
 *  Dataset dataset = Dataset
 *          .fromTensorSlices(XTensor, yTensor);
 *          .batch(BATCH_SIZE);
 *
 *  DatasetIterator iterator = dataset.makeIterator();
 *  List<Output<?>> components = iterator.getNext();
 *  Operand<?> XBatch = components.get(0);
 *  Operand<?> yBatch = components.get(1);
 *
 *  // Build a TensorFlow graph that does something on each element.
 *  loss = computeModelLoss(X, y);
 *
 *  optimizer = ... // create an optimizer
 *  trainOp = optimizer.minimize(loss);
 *
 *  try (Session session = new Session(graph) {
 *      try {
 *          session.run(trainOp);
 *          ...
 *      } catch (IndexOutOfBoundsException e) {
 *          System.out.println("finished iterating.");
 *          break;
 *      }
 *  }
 *
 * }</pre>
 * <p>
 * Example: Iteration in eager mode.
 *
 * <pre>{@code
 *  // Create input tensors
 *  Operand<?> XTensor = tf.constant( ... );
 *  Operand<?> yTensor = tf.constant( ... );
 *
 *  int BATCH_SIZE = ...
 *
 *  Dataset dataset = Dataset
 *          .fromTensorSlices(XTensor, yTensor)
 *          .batch(BATCH_SIZE);
 *  DatasetIterator iterator = dataset.makeIterator();
 *
 *  Optimizer optimizer = ... // create an optimizer
 *
 *  for (List<Output<?>> components : dataset) {
 *      Operand<?> XBatch = components.get(0);
 *      Operand<?> yBatch = components.get(1);
 *
 *      loss = computeModelLoss(X, y);
 *      trainOp = optimizer.minimize(loss);
 *  }
 * }</pre>
 */
public class DatasetIterator {
  public static final String EMPTY_SHARED_NAME = "";

  private Ops tf;

  private Operand<?> iteratorResource;
  private Op initializer;

  private List<DataType<?>> outputTypes;
  private List<Shape> outputShapes;

  /**
   * @param tf               Ops accessor corresponding to the same `ExecutionEnvironment`
   *                         as the `iteratorResource`.
   * @param iteratorResource An Operand representing the iterator
   *                         (e.g. constructed from `tf.data.iterator` or
   *                         `tf.data.anonymousIterator`)
   * @param initializer      An `Op` that should be run to initialize this iterator
   * @param outputTypes      A list of `DataType` objects corresponding to the
   *                         types of each component of a dataset element.
   * @param outputShapes     A list of `Shape` objects corresponding to the
   *                         shapes of each componenet of a dataset element.
   */
  private DatasetIterator(Ops tf, Operand<?> iteratorResource,
                          Op initializer,
                          List<DataType<?>> outputTypes,
                          List<Shape> outputShapes) {

    this.tf = tf;
    this.iteratorResource = iteratorResource;
    this.initializer = initializer;
    this.outputTypes = outputTypes;
    this.outputShapes = outputShapes;
  }

  private DatasetIterator(Ops tf, Operand<?> iteratorResource,
                          List<DataType<?>> outputTypes,
                          List<Shape> outputShapes) {
    this.tf = tf;
    this.iteratorResource = iteratorResource;
    this.outputTypes = outputTypes;
    this.outputShapes = outputShapes;
  }

  /**
   * Returns a list of `Operand<?>` representing the components of the
   * next dataset element.
   * <p>
   * In graph mode, call this method once, and use its result as input
   * to another computation. Then in the training loop, on successive calls
   * to session.run(), successive dataset elements will be retrieved through
   * these components.
   * <p>
   * In eager mode, each time this method is called, the next dataset
   * element will be returned. (This is done automatically by iterating
   * through `Dataset` as a Java `Iterable`).
   *
   * @return A `List<Operand<?>>` representing dataset element components.
   */
  public List<Output<?>> getNext() {
    return tf.data.iteratorGetNext(getIteratorResource(),
        getOutputTypes(), getOutputShapes()).components();
  }

  /**
   * Returns a list of `Operand<?>` representing the components of the
   * next dataset element.
   * <p>
   * In graph mode, call this method once, and use its result as input
   * to another computation. Then in the training loop, on successive calls
   * to session.run(), successive dataset elements will be retrieved through
   * these components.
   * <p>
   * In eager mode, each time this method is called, the next dataset
   * element will be returned. (This is done automatically by iterating
   * through `Dataset` as a Java `Iterable`).
   *
   * @return A `List<Operand<?>>` representing dataset element components.
   */
  public DatasetOptional getNextAsOptional() {
    Operand<?> optionalVariant = tf.data.iteratorGetNextAsOptional(
        getIteratorResource(),
        getOutputTypes(),
        getOutputShapes()).optional();
    return new DatasetOptional(tf, optionalVariant, outputTypes,
        outputShapes);
  }

  /**
   * Creates and returns a TF `Op` that can be run to initialize
   * this iterator on a dataset. The dataset must have a structure
   * (outputTypes, outputShapes) that match this iterator, and
   * share the same ExecutionEnvironment as this iterator.
   * <p>
   * When this `Op` is run, this iterator will be "re-initialized" at
   * the first element of the input dataset.
   * <p>
   * In eager mode, the op will be run automatically as part of
   * a call to `makeIterator`.
   *
   * @param dataset An `org.tensorflow.data.Dataset` to initialize this
   *                iterator on.
   * @return A TF `Op` that can be used to initialize this iterator on the
   * dataset.
   * @throws IllegalArgumentException if the dataset's ExecutionEnvironment or
   *                                  structure doesn't match this iterator.
   */
  public Op makeInitializer(Dataset dataset) {
    if (tf.scope().env() != dataset.tf.scope().env()) {
      throw new IllegalArgumentException("Dataset must share the same" +
          "ExecutionEnvironment as this iterator.");
    }

    if (!dataset.getOutputShapes().equals(getOutputShapes())
        || !dataset.getOutputTypes().equals(getOutputTypes())) {

      throw new IllegalArgumentException("Dataset structure (types, " +
          "output shapes) must match this iterator.");
    }

    this.initializer = tf.data.makeIterator(
        dataset.getVariant(), getIteratorResource());
    return this.initializer;
  }

  /**
   * Creates a new iterator from a "structure" defined by `outputShapes`
   * and `outputTypes`.
   *
   * @param tf           Ops accessor
   * @param outputTypes  A list of `DataType` objects repesenting the types
   *                     of each component of a dataset element.
   * @param outputShapes A list of Shape objects representing the shape
   *                     of each component of a dataset element.
   * @return A new DatasetIterator
   */
  public static DatasetIterator fromStructure(Ops tf,
                                              List<DataType<?>> outputTypes,
                                              List<Shape> outputShapes) {
    Operand<?> iteratorResource = tf.scope().env() instanceof Graph
        ? tf.data.iterator(EMPTY_SHARED_NAME, "", outputTypes, outputShapes)
        : tf.data.anonymousIterator(outputTypes, outputShapes).handle();

    return new DatasetIterator(tf, iteratorResource, outputTypes, outputShapes);
  }

  public Operand<?> getIteratorResource() {
    return iteratorResource;
  }

  public Op getInitializer() {
    return initializer;
  }

  public List<DataType<?>> getOutputTypes() {
    return outputTypes;
  }

  public List<Shape> getOutputShapes() {
    return outputShapes;
  }
}
