/* Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionSetAttrValueProto;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphToFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.Graph.Reference;
import org.tensorflow.internal.c_api.TF_Function;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.PartitionedCall;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.PlaceholderWithDefault;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.FunctionDef;
import org.tensorflow.proto.framework.OpDef.ArgDef;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.framework.TensorShapeProto;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * A graph that can be invoked as a single function, with an input and output signature.
 *
 * <p>A function can also invoke a <a
 * href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a> defined in a {@link
 * SavedModelBundle}.
 *
 * <pre>{@code
 * ConcreteFunction myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor> outputTensorMap = myFunction.call(inputTensorMap);
 * }</pre>
 */
public final class ConcreteFunction implements AutoCloseable, TensorFunction {

  /**
   * Creates a function by building a new graph.
   *
   * <p>The {@code functionBuilder} must initialize the function graph from the provided {@link Ops}
   * instance and return a valid signature that will be used to feed the input tensors and fetch the
   * output tensors on execution.
   *
   * <p>The function will be the owner of the new graph and its resulting session. Therefore, the
   * function must be enclosed properly with a try-with-resources block to guarantee that all native
   * resources will be freed once the function is discarded. For example:
   *
   * <pre>{@code
   * public class MyModel {
   *
   *   public static Signature addTwo(Ops tf) {
   *     Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *     Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *     return Signature.builder("addTwo").input("x", input).output("y", output).build();
   *   }
   *
   *   public static void main(String args[]) {
   *     try (ConcreteFunction function = ConcreteFunction.create(MyModel::addTwo);
   *         TFloat32 x = TFloat32.scalarOf(2.0f)) {
   *       assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *     }
   *   }
   * }
   * }</pre>
   *
   * @param functionBuilder function builder
   * @return the new function
   */
  public static ConcreteFunction create(Function<Ops, Signature> functionBuilder) {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);
      Signature signature = functionBuilder.apply(tf);
      return buildFromGraph(graph, signature);
    }
  }

  /**
   * Create a function from a signature and an existing graph.
   *
   * <p>The function will keep the ownership of the session used to run the graph but not the graph
   * itself, meaning that the lifetime of the latter can extend beyond the scope of the function.
   * For example:
   *
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *   Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *   Signature signature = Signature.builder().input("x", input).output("y", output).build();
   *
   *   try (ConcreteFunction f = ConcreteFunction.create(signature, g);
   *       TFloat32 x = TFloat32.scalarOf(2.0f)) {
   *     assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *   }
   *   // Graph g is still valid at this point
   * }
   * }</pre>
   *
   * @param signature signature of the function to create
   * @param graph a valid and initialized graph
   * @return a new function
   */
  public static ConcreteFunction create(Signature signature, Graph graph) {
    return buildFromGraph(graph, signature);
  }

  /**
   * Create a function from a signature and a valid graph session.
   *
   * <p>The function will not own the session nor its graph, meaning that their lifetime can extend
   * beyond the scope of the function. Therefore the function does not need to be closed after its
   * usage. For example:
   *
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
   *   Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
   *   Signature signature = Signature.builder().input("x", input).output("y", output).build();
   *
   *   try (Session s = new Session(g)) {
   *     // Auto-closing the function just as an example but this is not required since it has
   *     // no effect
   *     try (ConcreteFunction f = ConcreteFunction.create(signature, s);
   *         TFloat32 t = TFloat32.scalarOf(2.0f)) {
   *       assertEquals(4.0f, ((TFloat32)function.call(x)).getFloat());
   *     }
   *     // Session s is still valid at this point
   *   }
   *   // Graph g is still valid at this point
   * }
   * }</pre>
   *
   * @param signature signature of the function to create
   * @param session a valid session to an initialized graph
   * @return a new function
   */
  public static ConcreteFunction create(Signature signature, Session session) {
    return buildFromGraph(session.graph(), signature);
  }

  /** Returns the signature of this function */
  @Override
  public Signature signature() {
    return signature;
  }

  /**
   * Get the name of the function definition. This is what it will show up under in the graph and
   * any exported GraphDefs, and should be used for anything using tensorflow core directly.
   */
  public String getDefinedName() {
    return nativeFunction.getName();
  }

  /** Get the {@link FunctionDef} proto. */
  public FunctionDef getFunctionDef() {
    return nativeFunction.getFunctionDef();
  }

  /** Get whether the function is stateful. */
  public boolean isStateful() {
    return nativeFunction.isStateful();
  }

  Set<TF_Function> getDependencies() {
    return dependencies;
  }

  @Override
  public void close() {
    scope.close();
  }

  @Override
  public String toString() {
    return signature.toString();
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. The inputs and outputs are keyed by the names set in the {@code Signature}.
   *
   * @param scope the scope to call the function in
   * @param arguments the arguments to the call
   * @return the outputs of the function
   */
  public Map<String, Operand<?>> call(Scope scope, Map<String, Operand<?>> arguments) {
    List<Operand<?>> inputList = new ArrayList<>(signature.inputNames().size());

    for (String inputName : signature.inputNames()) {
      if (!arguments.containsKey(inputName)) {
        throw new IllegalArgumentException(
            "Function "
                + signature.methodName()
                + " has parameter \""
                + inputName
                + "\", but no argument was passed for it.");
      }

      Operand<?> input = arguments.get(inputName);
      if (input == null) {
        throw new IllegalArgumentException(
            "Can't pass null as an argument to a function.  Argument \""
                + inputName
                + "\" was null.");
      }
      inputList.add(input);
    }

    List<Output<?>> outputList =
        PartitionedCall.create(scope, inputList, outputTypes, this).output();

    if (signature.outputNames().size() == 0) {
      return Collections.emptyMap();
    }
    if (signature.outputNames().size() == 1) {
      return Collections.singletonMap(signature.outputNames().iterator().next(), outputList.get(0));
    }
    if (outputList.size() < signature.outputNames().size()) {
      throw new IllegalStateException(
          "Somehow, not all required outputs were returned from the function"
              + "(expected: "
              + signature.outputNames().size()
              + ", returned: "
              + outputList.size()
              + ")");
    }
    Map<String, Operand<?>> namedOutputs = new LinkedHashMap<>(signature.outputNames().size());
    Iterator<String> outputNames = signature.outputNames().iterator();
    for (int i = 0; outputNames.hasNext(); i++) {
      String outputName = outputNames.next();
      Operand<?> output = outputList.get(i);
      namedOutputs.put(outputName, output);
    }
    return Collections.unmodifiableMap(namedOutputs);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. Only works for functions with a single input and output.
   *
   * @param scope the scope to call the function in
   * @param argument the argument to the call
   * @return the output of the function
   */
  public Operand<?> call(Scope scope, Operand<?> argument) {
    final SignatureDef signatureDef = signature.asSignatureDef();

    if (signatureDef.getInputsCount() != 1) {
      throw new IllegalArgumentException(
          String.format("Function [%s] requires multiple inputs", signatureDef.getMethodName()));
    }
    String inputName = signatureDef.getInputsMap().keySet().iterator().next();

    if (signatureDef.getOutputsCount() != 1) {
      throw new IllegalArgumentException(
          String.format("Function [%s] has multiple outputs", signatureDef.getMethodName()));
    }
    String outputName = signatureDef.getOutputsMap().keySet().iterator().next();

    return call(scope, Collections.singletonMap(inputName, argument)).get(outputName);
  }

  @Override
  public Result call(Map<String, Tensor> arguments) {
    // FIXME need to manage input operand lifetimes
    Ops tf = Ops.create();
    Map<String, Operand<?>> inputs = new LinkedHashMap<>(arguments.size());

    for (String inputName : arguments.keySet()) {
      Tensor argument = arguments.get(inputName);
      inputs.put(inputName, tf.constantOf((TType) argument));
    }
    Map<String, Operand<?>> outputs = tf.call(this, inputs);
    LinkedHashMap<String, Tensor> tensorOutputs = new LinkedHashMap<>(outputs.size());
    for (String outputName : outputs.keySet()) {
      tensorOutputs.put(outputName, outputs.get(outputName).asTensor());
    }
    return new Result(tensorOutputs);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. The inputs and outputs are keyed by the names set in the {@code Signature}.
   *
   * @param tf the scope to call the function in
   * @param arguments the arguments to the call
   * @return the outputs of the function
   */
  public Map<String, Operand<?>> call(Ops tf, Map<String, Operand<?>> arguments) {
    return tf.call(this, arguments);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   * already present. Only works for functions with a single input and output.
   *
   * @param tf the scope to call the function in
   * @param argument the argument to the call
   * @return the output of the function
   */
  public Operand<?> call(Ops tf, Operand<?> argument) {
    return tf.call(this, argument);
  }

  TF_Function nativeHandle() {
    if (nativeFunction.getNativeHandle().isNull()) {
      throw new IllegalStateException("Function has been closed");
    }
    return nativeFunction.getNativeHandle();
  }

  /** All native functions should have deallocators registered */
  ConcreteFunction(
      Signature signature,
      NativeFunction nativeFunction,
      Collection<NativeFunction> availableFunctions) {
    this(signature, nativeFunction, nativeFunction.getAllDependencies(availableFunctions));
  }

  /**
   * Detects the signature from the handle. Does not close passed functions. All passed functions
   * should have deallocators.
   */
  static ConcreteFunction fromNativeHandle(
      NativeFunction nativeFunction, Collection<NativeFunction> availableFunctions) {

    Signature.Builder builder =
        Signature.builder()
            .methodName(nativeFunction.getFunctionDef().getSignature().getName())
            .key(nativeFunction.getName());

    for (ArgDef input : nativeFunction.getFunctionDef().getSignature().getInputArgList()) {
      TensorInfo info =
          TensorInfo.newBuilder()
              .setDtype(input.getType())
              .setTensorShape(TensorShapeProto.newBuilder().setUnknownRank(true).build())
              .setName(input.getName())
              .build();

      builder.input(input.getName(), info);
    }

    for (ArgDef outputDef : nativeFunction.getFunctionDef().getSignature().getOutputArgList()) {
      TensorInfo info =
          TensorInfo.newBuilder()
              .setDtype(outputDef.getType())
              .setTensorShape(TensorShapeProto.newBuilder().setUnknownRank(true).build())
              .setName(outputDef.getName())
              .build();

      builder.output(outputDef.getName(), info);
    }

    return new ConcreteFunction(builder.build(), nativeFunction, availableFunctions);
  }

  private final Signature signature;
  private final NativeFunction nativeFunction;
  private final PointerScope scope;
  private final Set<TF_Function> dependencies;
  private final List<Class<? extends TType>> outputTypes;

  /** All native functions should have deallocators registered */
  private ConcreteFunction(
      Signature signature, NativeFunction nativeFunction, Set<TF_Function> dependencies) {
    this.signature = signature;
    this.nativeFunction = nativeFunction;
    this.dependencies = Collections.unmodifiableSet(dependencies);

    if (signature.getInputs().size()
        != nativeFunction.getFunctionDef().getSignature().getInputArgCount()) {
      throw new IllegalArgumentException(
          "Signature must have the same number of inputs as the native function.  Expected "
              + nativeFunction.getFunctionDef().getSignature().getInputArgCount()
              + ", got "
              + this.signature.getInputs().size());
    }

    if (signature.getOutputs().size()
        != nativeFunction.getFunctionDef().getSignature().getOutputArgCount()) {
      throw new IllegalArgumentException(
          "New signature must have the same number of outputs as the native function.  Expected "
              + nativeFunction.getFunctionDef().getSignature().getOutputArgCount()
              + ", got "
              + this.signature.getOutputs().size());
    }

    List<DataType> inputs =
        signature.getInputs().values().stream().map(x -> x.dataType).collect(Collectors.toList());
    List<DataType> nativeInputs =
        nativeFunction.getFunctionDef().getSignature().getInputArgList().stream()
            .map(ArgDef::getType)
            .collect(Collectors.toList());

    if (!dataTypesMatch(inputs, nativeInputs)) {
      throw new IllegalArgumentException(
          "Data types of the signature's inputs must match the native function's (in order).  Expected "
              + nativeInputs
              + ", got "
              + inputs);
    }

    List<DataType> outputs =
        signature.getOutputs().values().stream().map(x -> x.dataType).collect(Collectors.toList());
    List<DataType> nativeOutputs =
        nativeFunction.getFunctionDef().getSignature().getOutputArgList().stream()
            .map(ArgDef::getType)
            .collect(Collectors.toList());

    if (!dataTypesMatch(outputs, nativeOutputs)) {
      throw new IllegalArgumentException(
          "Data types of the signature's outputs must match the native function's (in order).  Expected "
              + nativeOutputs
              + ", got "
              + outputs);
    }

    outputTypes =
        outputs.stream().map(x -> TensorTypeRegistry.find(x).type()).collect(Collectors.toList());

    try (PointerScope scope = new PointerScope()) {
      this.scope = scope;
      scope.extend();
      scope.attach(this.nativeFunction.getNativeHandle());
      this.dependencies.forEach(scope::attach);
    }
  }

  /**
   * FIXME: This causes native errors when I use it (Linux GPU, 6.1 CC), but I'm leaving it because
   * how to enable XLA JIT is extremely non-obvious.
   *
   * <p>See https://github.com/tensorflow/java/issues/347
   *
   * <p>Causes {@code OP_REQUIRES failed at xla_ops.cc:363 : Not found: could not find registered
   * platform with id: 0x7f75af03e6e8} (it's a warning, but the resulting TF_Status fails).
   */
  private void makeJit() {
    try (PointerScope scope = new PointerScope()) {
      byte[] bytes = AttrValue.newBuilder().setB(true).build().toByteArray();
      BytePointer trueValue = new BytePointer(bytes);

      TF_Status status1 = TF_Status.newStatus();
      TF_FunctionSetAttrValueProto(
          nativeHandle(), "_XlaMustCompile", trueValue, bytes.length, status1);
      status1.throwExceptionIfNotOK();

      TF_Status status2 = TF_Status.newStatus();
      TF_FunctionSetAttrValueProto(nativeHandle(), "_noinline", trueValue, bytes.length, status2);
      status2.throwExceptionIfNotOK();
    }
  }

  private static boolean dataTypesMatch(List<DataType> a, List<DataType> b) {
    if (a.size() != b.size()) {
      return false;
    }

    for (int i = 0; i < a.size(); i++) {
      DataType aType = a.get(i);
      DataType bType = b.get(i);

      if (aType != DataType.DT_INVALID && bType != DataType.DT_INVALID && !a.equals(b)) {
        return false;
      }
    }

    return true;
  }

  private static TF_Operation outputHandle(Operand<?> operand) {
    if (operand == null) {
      throw new NullPointerException("Can't get output handle for null operand");
    }

    Pointer handle = operand.asOutput().getUnsafeNativeHandle();
    if (handle.isNull()) {
      throw new NullPointerException("Native handle of operand is null, has it been closed?");
    }

    if (!(handle instanceof TF_Operation)) {
      throw new IllegalArgumentException("Operand was not a graph operand");
    }

    return (TF_Operation) handle;
  }

  private static TF_Output resolveToOutput(Graph graph, List<Operand<?>> operands) {
    TF_Output handles = new TF_Output(operands.size());
    for (int i = 0; i < operands.size(); i++) {
      Operand<?> input = operands.get(i);
      graph.checkInput(input);
      TF_Operation handle = outputHandle(input);

      handles.position(i).oper(handle).index(input.asOutput().index());
    }
    handles.position(0);
    return handles;
  }

  private static ConcreteFunction buildFromGraph(Graph graph, Signature signature) {
    try (PointerScope scope = new PointerScope();
        Reference ref = graph.ref()) {
      TF_Status status = TF_Status.newStatus();

      List<Operand<?>> inputs =
          signature.getInputs().entrySet().stream()
              .map(
                  (x) ->
                      TensorFunction.validateDescription(x.getValue(), graph, x.getKey(), "Input"))
              .collect(Collectors.toList());

      List<Operand<?>> outputs =
          signature.getOutputs().entrySet().stream()
              .map(
                  (x) ->
                      TensorFunction.validateDescription(x.getValue(), graph, x.getKey(), "Output"))
              .collect(Collectors.toList());

      List<GraphOperation> ops =
          new ArrayList<>(graph.completeSubgraph(new HashSet<>(inputs), new HashSet<>(outputs)));

      inputs.forEach(input -> ops.remove((GraphOperation) input.op()));

      ops.forEach(
          x -> {
            if (x.type().equals(Placeholder.OP_NAME)
                || x.type().equals(PlaceholderWithDefault.OP_NAME)) {
              throw new IllegalArgumentException(
                  "Can't calculate outputs ("
                      + outputs
                      + ") from inputs ("
                      + inputs
                      + "), "
                      + "they also depend on \""
                      + x
                      + "\"");
            }
          });

      // Python sometimes has NoOps as outputs
      Ops tf = Ops.create(graph).withSubScope("functionControlOutputs");
      for (int i = 0; i < outputs.size(); i++) {
        Operand<?> output = outputs.get(i);
        if (output.op().numOutputs() < 1) {
          Operand<TBool> realOutput =
              tf.withControlDependencies(Collections.singletonList(output))
                  .withName(output.op().name() + "_control")
                  .constant(false);
          ops.add((GraphOperation) realOutput.op());
          outputs.set(i, realOutput);
        }
      }

      PointerPointer<TF_Operation> operations = new PointerPointer<>(ops.size());
      for (int i = 0; i < ops.size(); i++) {
        operations.put(i, ops.get(i).getUnsafeNativeHandle());
      }

      TF_Function handle =
          TF_GraphToFunction(
              ref.nativeHandle(),
              new BytePointer(signature.key()),
              (byte) 1,
              ops.size(),
              operations,
              inputs.size(),
              resolveToOutput(graph, inputs),
              outputs.size(),
              resolveToOutput(graph, outputs),
              null,
              null,
              new BytePointer(
                  signature.methodName() != null
                      ? signature.methodName()
                      : "Method " + signature.key()),
              status);

      handle.withDeallocator();
      status.throwExceptionIfNotOK();
      return new ConcreteFunction(
          signature, new NativeFunction(handle), graph.getNativeFunctions(scope));
    }
  }
}
