# TensorFlow NIO Utility Library

## Introduction

TensorFlow NIO utility library helps manipulating large N-dimensional data records in the JVM 
runtime environment by providing an intuitive API on top of linear memory segments commonly
used, such as standard arrays or JDK NIO buffers.

The need of additional utilities to handle those data structures became obvious when users observed 
slow performances by using standard multidimensional arrays in Java, resulting from the 
non-contiguous storage of the data and the multiple dereferences required to access the values. 
Providing such data in a contiguous JDK NIO buffer can improve the performances but at the cost of 
difficult manipulation of the data and still being limited by the use of 32-bits indices.

TensorFlow NIO solves these problems by:

* Supporting 64-bits indexation, theoretically allowing the storage and manipulation of data records 
up to 8216 PB
* Providing an API (`NdArray`) for manipulating linear memory segments as multidimensional data
structures
* Serializing and deserializing data of any type (i.e. not only primitive types, as with the JDK NIO
utilities)

It is important to note that the TensorFlow NIO library does not depend on any other TensorFlow
runtime libraries, thus can used easily by any other projects who wants to benifits from its 
features. For example, an instance of `NdArray` could easily be passed from one library to another
for sharing data.

To import TensorFlow NIO in your project, simply add the following dependency:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>tensorflow-utils-nio</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

## Usage

Note: For convenience, the helper interface `StaticApi` is providing access to most of the important
features of this library in a more readable fashion. It is suggested to import static helpers of
this interface in every class working with TensorFlow NIO as follow.
```java
import static org.tensorflow.nio.StaticApi.*;
```
All examples of this README page are based on those imports. 

### Data Buffers

In TensorFlow NIO library, data is stored in a `DataBuffer`, which can be seen as an extension to
the JDK NIO `Buffer` objects but with the addition of 64-bits indexation support and usage of
generic parametrization useful when the actual type of the buffer is unknown.

```java
// Allocate a buffer of 4K int values
IntDataBuffer bufferA = bufferOfInts(4096L);
assertEquals(4096L, bufferA.size());

// Write an int array at the beginning of the buffer
bufferA.put(new int[] { 1, 2, 3 });
assertEquals(3L, bufferA.position());
assertEquals(3, bufferA.getInt(2));

// Slice buffer after first value
IntDataBuffer bufferB = bufferA.position(1).slice();
assertEquals(4095L, bufferB.size());
assertEquals(0L, bufferB.position());
assertEquals(2, bufferB.getInt(0));

// Wrap an int array into a data buffer
IntDataBuffer bufferC = bufferOf(new int[] { 10, 20, 30, 40 }, false);
assertEquals(4L, bufferC.size());
assertEquals(40, bufferC.getInt(3));

// Join buffers together
IntDataBuffer bufferBC = DataBuffers.join(bufferB, bufferC);
assertEquals(4099L, bufferBC.size());
assertEquals(2, bufferBC.getInt(0));
assertEquals(40, bufferBC.getInt(bufferBC.size() - 1));
```

### ND Arrays

TensorFlow NIO provides an interface called `NdArray` for wrapping up a `DataBuffer` (physically
or logically contiguous) to expose utilities for traversing, reading or writing data in a 
multi-dimensional space.

```java
// Allocating a 3D matrix of 2x3x2
IntNdArray matrix3d = ndArrayOfInts(shape(2, 3, 2));
assertEquals(3, matrix3d.rank());

// Initializing 3D matrix data with vectors from the first dimension (index 0)
matrix3d.elements(0).forEach(matrix -> {
    assertEquals(2, matrix.rank());
    assertEquals(shape(3, 2), matrix.shape());
    matrix.set(vector(1, 2), 0).set(vector(3, 4), 1).set(vector(5, 6), 2);
});

// Visit all scalars of 3D matrix, printing their coordinates and value
matrix3d.scalars().forEachIdx((coords, scalar) ->
    System.out.println("Scalar at " + Arrays.toString(coords) + " has value " + scalar.getInt())
);

// Retrieving the second vector of the first matrix
IntNdArray vector = matrix3d.get(0, 1);
assertEquals(1, vector.rank());

// Rewriting the values of the vector using a primitive array
vector.write(new int[] { 7, 8 });
assertEquals(7, matrix3d.getInt(0, 1, 0));
assertEquals(8, matrix3d.getInt(0, 1, 1));

// Slicing the 3D matrix so we only keep the second element of the second dimension
IntNdArray slice = matrix3d.slice(all(), at(1));
assertEquals(2, slice.rank());
assertEquals(shape(2, 2), slice.shape());
assertEquals(7, slice.getInt(0, 0));  // (0, 1, 0) in the original matrix
assertEquals(3, slice.getInt(1, 0));  // (1, 1, 0) in the original matrix
```

## Integration with TensorFlow

Like previously stated, TensorFlow NIO is independent of any other TensorFlow runtime library, 
making it a good choice for manipulating multi-dimensional data structures from anywhere. But here
is how it is actually used by the [TensorFlow Core API](https://github.com/tensorflow/java/tree/master/tensorflow-core/tensorflow-core-api)
to map and manipulate tensor data.

```java
// Allocate a tensor of 32-bits integer of the shape (2, 3, 2)
Tensor<TInt32> tensor = TInt32.tensorOfShape(2, 3, 2);

// Access tensor memory directly
IntNdArray tensorData = tensor.data();
assertEquals(3, tensorData.rank());
assertEquals(12, tensorData.size());

try (EagerSession session = EagerSession.create()) {
  Ops tf = Ops.create(session);

  // Initialize tensor memory with zeros
  tensorData.write(bufferOfInts(tensorData.size()));
  tensorData.scalars().forEach(scalar ->
      assertEquals(0, scalar.getInt())
  );
  Constant<TInt32> x = tf.constant(tensor);  // take snapshot of `tensor` with all zeros

  // Initialize tensor memory with all ones
  int[] ones = new int[(int)tensorData.size()];
  Arrays.fill(ones, 1);
  tensorData.write(ones);
  tensorData.scalars().forEach(scalar ->
      assertEquals(1, scalar.getInt())
  );
  Constant<TInt32> y = tf.constant(tensor);  // take snapshot of `tensor` with all ones

  // Subtract y from x and validate the result
  Sub<TInt32> sub = tf.math.sub(x, y);
  sub.tensorData().scalars().forEach(scalar ->
      assertEquals(-1, scalar.getInt())
   );
}
```