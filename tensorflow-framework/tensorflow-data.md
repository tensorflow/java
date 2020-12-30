Tensorflow Data (Java)
==

TensorFlow Data provides abstractions for loading data of various 
formats, and preparing it for use with machine learning models.
. This package provides a simple API for configuring and iterating over
datasets in both "graph" and "eager" mode.

Usage
--

The `Dataset` class represents a sequence of elements which can be iterated over and
transformed. Each element is a list of "output" operands, represented by the type `List<Operand<?>>`. 

Note: An `Output` is a symbolic handle to a tensor produced by a TensorFlow op. In graph
mode, `Output` objects will not have a concrete `Tensor` value unless all dependent operations
are run in a `Session` (this is done "in-real-time" in eager mode).

### Construction

Datasets can be constructed either directly from a data source (e.g. a list of tensors representing the components of the dataset), or as a transformation on an existing dataset.

#### From Data Source

To construct a dataset from a list of tensor components, use 
`Dataset.fromTensorSlices( ... )`. For example, say we are working
with a standard feature/label dataset which has 4 elements.

```java
FloatNdArray features = StdArrays.ndCopyOf(
        new float[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {10, 11, 12}
});

FloatNdArray labels = NdArrays.vectorOf(0, 1, 1, 0);
```

A dataset can be constructed from a list of the constant `Operand`s generated
from this dataset, and a list of classes corresponding to the tensor type of each component:

Note: Each of the input components must share the same first "batch" dimension.

```java
Ops tf = // ... TensorFlow Ops accessor (either graph or eager)
Dataset dataset = Dataset.fromTensorSlices(
    Arrays.asList(tf.constant(features), tf.constant(labels)),
    Arrays.asList(TInt32.class, TInt32.class)
);
```


Other data sources are also possible, using `tf.data` ops; these include TFRecord files, CSV files, and more.

#### Transformations on Existing Datasets

Once a dataset has been created from a data source it can be transformed by calling
methods on the `Dataset` object. For example, to group elements in the above dataset into batches of size `2`, use `Dataset.batch(int batchSize)`:

```java
dataset = dataset.batch(2)
```

Each dataset transformation alters both the values and shapes of the original
 elements, and returns a *new* `Dataset` object.

In this case, the original dataset had 4 elements of shape `[features: (3,) labels: (1,)]`.
Once the `.batch` transformation is applied, the new dataset has 2 elements (batches) of shape `[features: (2, 3), labels: (2, 1)]`.

Similar transformations include `.skip`, `.take`, `.map`, `.filter`, etc.


### Iterating over Dataset Elements

The primary use of a dataset is for iteration over its elements.
Each row (or batch) element is represented as a list of tensor components, with
type `List<Operand<?>>`. The tensor components of this element can be accessed using `List.get(int index)`.

#### Using DatastetIterator
The `DatasetIterator` class provides abstractions for creating and using
iterators in graph and eager mode. These will be explained here; however
end-users should only interact with `DatasetIterator` objects through the methods
provided in the `Dataset` class (examples to follow).

To construct an iterator for a dataset of a specific structure, use
the static method `DatasetIterator.fromStructure(Ops tf, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes)`. This creates a `DatasetIterator` object
which can be used with any dataset of a matching structure.

Once a `DatasetIterator` is created, it can be initialized on a `Dataset` intsance using `DatasetIterator.makeInitializer(Dataset dataset)`. This will initialize (or re-initialize) the iterator to start at the beginning
of this dataset.

The `DatasetIterator.getNext()` method can be used to retrieve dataset elements.
In eager mode, each call to `getNext()` will return the next dataset element as
as `List<Operand<?>>`. In graph mode, this method should be called just once
to retrieve the components. These can be fed into additional operations as
a computation Graph is built. On successive `session.run` operations, the
successive dataset elements will be automatically passed through the graph.


#### Eager Mode: Iterable
The `Dataset` class implements the `Iterable` interface, so in
eager mode, iteration over dataset elements is possible using a standard for-each loop (this is a wrapper around `DatasetIterator` constructs).

Using the same example dataset from above, dataset elements can be extracted and
used as follows:
```java
// Use default EagerSession
Ops tf = Ops.create()

// Dataset of (features, labels) from above
Dataset dataset = Dataset.fromTensorSlices(tf, ... );

// batch dataset elements into batches of size 2
dataset = dataset.batch(2);

Optmizer optimizer = ... // TF Optimizer

for (List<Operand<?>> batch : dataset) {
    Operand<?> featureBatch = element.get(0);
    Operand<?> labelBatch = element.get(1);

    // Perform batch-wise computations on featureBatch and labelBatch
    // e.g. computing model losses, running optimizers.

    Operand<TFloat32> loss = myModelLoss(featureBatch, labelBatch);

    optimizer.minimize(loss);
    
    ...
}   

```

#### Graph Mode: OneShotIterator

The above code will not work in graph mode, which requires the use of
 `Session` instances
to run the computations. In graph mode, datasets can be iterated over using the `DatasetIterator` abstraction, and a while loop.

Once the iterator is initialized, repeated calls to `Session.run` will populate the components with new values, until all elements have
been retrieved. After this, `Session.run` will result in a `TFOutOfRangeException`.

Note that the make-iterator operation can be re-run to re-initialize
the iterator, to iterate over the dataset a second time.

```java
try (Graph graph = new Graph()) {
    // Graph mode Ops accessor
    Ops tf = Ops.create(graph)

    // Dataset of (features, labels) from above
    Dataset dataset = Dataset.fromTensorSlices(tf, ... );

    // batch dataset elements into batches of size 2
    dataset = dataset.batch(2); 

    DatasetIterator iterator = dataset.makeInitializeableIterator();
    List<Operand<?>> batch = iterator.getNext();

    Operand<?> features = batch.get(0);
    Operand<?> labels = batch.get(1);

    // Run additional computations on `features` and `labels`,
    // e.g. computing model losses, instantiating Optimizers

    Optimizer optimizer = ... // TF Optimizer 
    Operand<TFloat32> loss = myModelLoss(features, labels);
    
    Op trainOp = optimizer.minimize(loss)

    // instantiate graph-mode session
    try (Session session = new Session(graph)) {
        // Run graph initializers
        session.run(tf.init());

        // Iterate over dataset elements
        while (true) {
            session.run(iterator.getInitializer());
            try {
                // Run training ops / fetch loss
                List<Tensor<?>> outputs = session.runner()
                    .addTarget(trainOp)
                    .fetch(loss)
                    .run();

                ...

            } catch (TFOutOfRangeException e) {
                // Finished iterating
                break;
            }
        }
    }
}
```


#### Applying a `map()` Function

In both graph and eager mode, the `Dataset` object provides
methods to allow mapping over dataset elements. `Dataset.map`, 
`Dataset.mapOneComponent`, and `Dataset.mapAllComponents`.
See the javadoc for a detailed description of each method. 

As an example: Say we have a `Dataset` with two components. The first component
is a 2D image (tensor of pixel values in the range `[0-255]`), and the second component is 
a classification label for that image. Often, it is helpful to normalize
the pixel values to the range `[0, 1]` by dividing by 255.

To do this, we'll use `Dataset.mapOneComponent`.

```java
Ops tf = // Get Ops instance
Dataset dataset = // image, label dataset;
dataset = dataset.mapOneComponent(0, component -> tf.math.div(component, tf.constant(255.0)));


for (List<Operand<?>> components : dataset) {
  // Fetch the normalized image tensor
  Operand<?> image = components.get(0);
  
  // The label tensor remains unchanged.
  Operand<?> label = components.get(1);
}
```
