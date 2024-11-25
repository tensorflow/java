# Migrating Between TensorFlow Java Releases

TensorFlow Java is still in an alpha stage, therefore is subject to contain breaking changes between the different releases. This guide explain in detail
how to migrate your code from a previous version to a new one that includes some changes that are not backward compatible.

## Migrating to 1.0.0

TensorFlow-Java 1.0.0 requires Java 11 or later.

### Native Artifact Renaming

The native artifacts, that used to be distributed as `tensorflow-core-api`, are now distributed under `tensorflow-core-native`. If you still add
`tensorflow-core-platform` in your project, that won't affect you. But if you were adding dependencies to specific native runtimes, you need to update
them to reflect the new artifact name.

For example,
```xml
<dependency>
    <groupId>org.tensorflow</groupId>
    <artifactId>tensorflow-core-api</artifactId>
    <version>0.5.0</version>
</dependency>
<dependency>
    <groupId>org.tensorflow</groupId>
    <artifactId>tensorflow-core-api</artifactId>
    <version>0.5.0</version>
    <classifier>linux-x86_64</classifier>
</dependency>
```
will now be
```xml
<dependency>
    <groupId>org.tensorflow</groupId>
    <artifactId>tensorflow-core-api</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>org.tensorflow</groupId>
    <artifactId>tensorflow-core-native</artifactId>
    <version>1.0.0</version>
    <classifier>linux-x86_64</classifier>
</dependency>
```
### Java Module Renaming

The Java Module (jigsaw) names has been updated to drop the leading `org.`, as follow:
- `tensorflow-core-api` : `tensorflow` (was `org.tensorflow` before)
- `tensorflow-core-generator` : `tensorflow.generator` (was `org.tensorflow-generator` before)
- `tensorflow-core-native` : `tensorflow.nativelib`
- `tensorflow-framework` :  `tensorflow.framework` (was `org.tensorflow.framework` before)

### GPU Support

Previous versions of TF Java were building a `tensorflow-core-platform-gpu` artifact upon which application could depend
on to include any TensorFlow native library that GPU support enabled. Since TensorFlow has removed its support of GPU
on all platforms other than Linux, we removed our platform JAR in favour of simply adding a dependency on the
`linux-x86_64-gpu` native artifact. 
```xml
<dependency>
    <group>org.tensorflow</group>
    <artifact>tensorflow-core-native</artifact>
    <version>1.0.0</version>
    <classifier>linux-x86_64-gpu</classifier>
</dependency>
```
Please note that including this dependency won't work if your application also depends on `tensorflow-core-platform`. If
you need to support more platforms than Linux, you should include the other `tensorflow-core-native` dependencies 
separately (see the [README](README.md) file).

### Session Run Result

In versions before 0.4.0 `Session.Runner.run` and `TensorFunction.call` returned a `List<Tensor>`. In newer versions
they return a `Result` class which is `AutoCloseable` to make management of the tensor lifetime simpler. To migrate
users should wrap the `run` invocation in a try-with-resources statement rather than closing the output tensors
individually.

### Proto Definitions Moved

Some proto definitions under `org.tensorflow.proto` have been moved to a different location under the same package. You will need to reimport these
proto bindings to match the new location. Your IDE should easily be able to do this for you.

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

