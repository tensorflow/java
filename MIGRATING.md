# Migrating Between TensorFlow Java Releases

TensorFlow Java is still in an alpha stage, therefore is subject to contain breaking changes between the different releases. This guide explain in detail
how to migrate your code from a previous version to a new one that includes some changes that are not backward compatible.

## Migrating to 0.3.0

### Non-parameterized Typed Tensors

In previous versions, the `Tensor` class was parameterized with its tensor type interface, which is part of the `TType` family. To access directly the memory
tensor from the JVM, an explicit conversion between `Tensor` and its tensor type was required by calling `tensor.data()`. 

In 0.3.0, tensors are always typed, making this generic parameter and explicit mapping obsolete. As soon as you get a handle to a tensor, you are able to
access directly its memory for reading (or writing for most tensor types) and no convertion is required. Any instances of a class in the `TType` family
can also now be manipulated directly as a `Tensor` (e.g. to be passed to a session for inference).

Steps:
1. Replace a parameterized `Tensor` by its parameter (e.g. `Tensor<TFloat32>` -> `TFloat32`)
2. Replace instance of `Tensor<?>` with unknown parameter by `Tensor`
3. Remove any invocation to `Tensor.data()` (e.g. `tensor.data().getFloat()` -> `tensor.getFloat()`)
4. Replace any invocation to `Operand.data()` by `Operand.asTensor()`

### Use of Java Type System instead of DataType 

In previous versions, the `DataType` class was used to carry information about the type of a `Tensor`, that can then be converted back to a tensor of that
type (see previous section). Since there were a exact parity between interfaces of the `TType` family and an instance of `DataType`, the latter has been dropped
in 0.3.0 to leverage instead the standard type system in Java, for a better idiomatic experience.

Steps:
1. Replace all accesses to the `DTYPE` field of a `TType` interface by its class (e.g. `TFloat32.DTYPE` -> `TFloat32.class`)
2. Use Java type system for checking tensor types at runtime (e.g. using `instanceof` or `isAssignableFrom`)
3. Replace any invocation to `Tensor.expect()` by an explicit cast (e.g. `tensor.expect(TFloat32.DTYPE)` -> `(TFloat32)tensor`)

### Example

0.2.0:
```
Session session = ...;

try (Tensor<TFloat32> tensor = TFloat32.tensorOf(Shape.of(1, 2))) {
    TFloat32 tensorData = tensor.data();
    tensorData.setFloat(10.0f, 0);
    tensorData.setFloat(20.0f, 1);
    
    try (Tensor<?> result = session.runner().feed("x", tensor).fetch("y").run().get(0)) {
        if (result.dataType() == TFloat32.DTYPE) {
            Tensor<TFloat32> typedResult = result.expect(TFloat32.DTYPE);
            TFloat32 resultData = typedResult.data();
            System.out.println("Result is " + resultData.getFloat());
        }
    }
}
```

0.3.0:
```
Session session = ...;

try (TFloat32 tensor = TFloat32.tensorOf(Shape.of(1, 2))) {
    tensor.setFloat(10.0f, 0);
    tensor.setFloat(20.0f, 1);
    
    try (Tensor result = session.runner().feed("x", tensor).fetch("y").run().get(0)) {
        if (result instanceof TFloat32) {
            TFloat32 typedResult = (TFloat32)result;
            System.out.println("Result is " + typedResult.getFloat());
        }
    }
}
```

