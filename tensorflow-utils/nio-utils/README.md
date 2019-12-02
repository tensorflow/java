# TensorFlow NIO Utility Library

## Introduction

TensorFlow NIO utility library helps manipulating large N-dimensional data records in the JVM 
runtime environment by providing an intuitive API on top of linear memory segments, such as standard 
arrays, JDK NIO buffers or native memory.

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
runtime libraries, thus can used easily by any other projects who wants to benefit from its 
features. For example, an instance of `NdArray` could easily be passed from one library to another
for sharing data.

To import TensorFlow NIO in your project, simply add the following dependency:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>nio-utils</artifactId>
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

In TensorFlow NIO library, data is stored in a `DataBuffer`, which maps a contiguous segment of
memory with 64-bits indexation and supports generic parametrization while still allowing direct
access to primitive types.

```java
// Allocate a buffer of 4K int values
IntDataBuffer bufferA = bufferOfInts(4096L);
assertEquals(4096L, bufferA.size());

// Write an int array at the beginning of the buffer
bufferA.write(new int[] { 1, 2, 3 });
assertEquals(3, bufferA.getInt(2));

// Slice buffer after its first value
IntDataBuffer bufferB = bufferA.offset(1);
assertEquals(4095L, bufferB.size());
assertEquals(2, bufferB.getInt(0));

// Resize a buffer to 10 elements
IntDataBuffer bufferC = bufferA.narrow(10);
assertEquals(10L, bufferB.size());
assertEquals(2, bufferB.getInt(0));
```

### ND Arrays

TensorFlow NIO provides an interface called `NdArray` for wrapping up a `DataBuffer` to expose 
an API for traversing, reading or writing data in a multi-dimensional space.

```java
// Allocating a 3D matrix of 2x3x2
IntNdArray matrix3d = ndArrayOfInts(shape(2, 3, 2));
assertEquals(3, matrix3d.rank());

// Initializing 3D matrix data with vectors from the first dimension (index 0)
matrix3d.elements(0).forEach(matrix -> {
    assertEquals(2, matrix.rank());
    assertEquals(shape(3, 2), matrix.shape());
    matrix
      .set(vector(1, 2), 0)
      .set(vector(3, 4), 1)
      .set(vector(5, 6), 2);
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

  // Initialize tensor memory with zeros and take a snapshot
  tensorData.scalars().forEach(scalar -> scalar.setValue(valueOf(0)));
  Constant<T> x = tf.constant(tensor);

  // Initialize the same tensor memory with ones and take a snapshot
  tensorData.scalars().forEach(scalar -> scalar.setValue(valueOf(1)));
  Constant<T> y = tf.constant(tensor);

  // Subtract y from x and validate the result
  Sub<T> sub = tf.math.sub(x, y);
  sub.tensorData().scalars().forEach(scalar ->
      assertEquals(valueOf(-1), scalar.getValue())
  );
}
```