# NdArray Java Library

## Introduction

NdArray is a library exposing utilities for manipulating data in a n-dimensional space in Java. 
Unlike other Java artifacts distributed by TensorFlow, this library does not depend on the TensorFlow
runtime, therefore is very lightweight and can be used by any kind of Java project.

To import the NdArray library in your project, simply add the following dependency:
```xml
<dependency>
  <groupId>org.tensorflow</groupId>
  <artifactId>ndarray</artifactId>
  <version>0.2.0-SNAPSHOT</version>
</dependency>
```

### Data Buffers

Instances of `DataBuffer` map contiguous segments of memory with 64-bits indexing and supports 
generic parametrization while still allowing direct access to primitive types. Such segments 
could be standard Java arrays, JDK NIO buffers or native memory. In addition, it can serialize and 
deserialize data of any type (and not only primitive types, as with `java.util.nio`).

```java
// Allocate a buffer of 4K int values
IntDataBuffer bufferA = DataBuffers.ofInts(4096L);
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

Instances of `NdArray` are used to view memory segments stored in a `DataBuffer` as a 
multidimensional arrays and to provide an API for traversing, reading, writing and slicing
their data. The goal of these tools is to replace the usage of standard multidimensional Java arrays 
(e.g. `new int[][][]`) since those results in slow performances, from the non-contiguous 
storage of their data and the multiple dereferences required to access their values. 

```java
// Allocating a 3D matrix of 2x3x2
IntNdArray matrix3d = NdArrays.ofInts(Shape.of(2, 3, 2));
assertEquals(3, matrix3d.rank());

// Initializing 3D matrix data with vectors from the first dimension (index 0)
matrix3d.elements(0).forEach(matrix -> {
    assertEquals(2, matrix.rank());
    assertEquals(Shape.of(3, 2), matrix.shape());
    matrix
      .set(NdArrays.vectorOf(1, 2), 0)
      .set(NdArrays.vectorOf(3, 4), 1)
      .set(NdArrays.vectorOf(5, 6), 2);
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
assertEquals(Shape.of(2, 2), slice.shape());
assertEquals(7, slice.getInt(0, 0));  // (0, 1, 0) in the original matrix
assertEquals(3, slice.getInt(1, 0));  // (1, 1, 0) in the original matrix
```

## Integration with TensorFlow

The NdArray library is independent of the TensorFlow runtime library, making it a good choice for
manipulating multi-dimensional data structures from anywhere. But as an example, here
is how it is actually being used by the [TensorFlow Core API](https://github.com/tensorflow/java/tree/master/tensorflow-core/tensorflow-core-api):

```java
// Allocate a tensor of 32-bits integer of the shape (2, 3, 2)
Tensor<TInt32> tensor = TInt32.ofShape(2, 3, 2);

// Access tensor memory directly
IntNdArray tensorData = tensor.data();
assertEquals(3, tensorData.rank());
assertEquals(12, tensorData.size());

try (EagerSession session = EagerSession.create()) {
  Ops tf = Ops.create(session);

  // Initialize tensor memory with zeros and take a snapshot
  tensorData.scalars().forEach(scalar -> scalar.setInt(0));
  Constant<T> x = tf.constant(tensor);

  // Initialize the same tensor memory with ones and take a snapshot
  tensorData.scalars().forEach(scalar -> scalar.setInt(1));
  Constant<T> y = tf.constant(tensor);

  // Subtract y from x and validate the result
  Sub<T> sub = tf.math.sub(x, y);
  sub.data().scalars().forEach(scalar ->
      assertEquals(-1, scalar.getInt())
  );
}
```
