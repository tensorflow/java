/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionSetAttrValueProto;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionToFunctionDef;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphToFunction;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_OperationGetAttrValueProto;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.Graph.Reference;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Function;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Placeholder;
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
 * <p>A function can also invoke a
 * <a href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
 * defined in a {@link SavedModelBundle}.
 *
 * <pre>{@code
 * ConcreteFunction myFunction = savedModelBundle.function("myFunctionSignatureName");
 * Map<String, Tensor> outputTensorMap = myFunction.call(inputTensorMap);
 * }</pre>
 */
public class ConcreteFunction implements AutoCloseable {


  /**
   * Creates a function by building a new graph.
   *
   * <p>The {@code functionBuilder} must initialize the function graph from the provided
   * {@link Ops} instance and return a valid signature that will be used to feed the input tensors and fetch the output
   * tensors on execution.
   *
   * <p>The function will be the owner of the new graph and its resulting session. Therefore,
   * the function must be enclosed properly with a try-with-resources block to guarantee that all native resources will
   * be freed once the function is discarded. For example:
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
   * <p>The function will keep the ownership of the session used to run the graph but not
   * the graph itself, meaning that the lifetime of the latter can extend beyond the scope of the function. For
   * example:
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
   * <p>The function will not own the session nor its graph, meaning that their lifetime
   * can extend beyond the scope of the function. Therefore the function does not need to be closed after its usage. For
   * example:
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

  /**
   * Returns the signature of this function
   */
  public Signature signature() {
    return signature;
  }

  /**
   * Get the name of the function.
   */
  public String getNativeFunctionName() {
    try (PointerScope scope = new PointerScope()) {
      return TF_FunctionName(nativeHandle()).getString();
    }
  }

  public static final String CALL_OP = "PartitionedCall";
  public static final String STATEFUL_CALL_OP = "StatefulPartitionedCall";

  public Map<String, Operand<?>> call(Scope scope,
      Map<String, Operand<?>> arguments) {
    List<Operand<?>> inputList = new ArrayList<>();

    for (String inputName : signature().inputNames()) {
      Operand<?> input = arguments.get(inputName);
      if (input == null) {
        throw new IllegalArgumentException(
            "Function " + signature().methodName() + " has parameter \"" + inputName
                + "\", but no argument was passed for it.");
      }
      inputList.add(input);
    }

    scope.env().attachFunction(this);
    String name = getNativeFunctionName();

    String displayName = Scope.isValidOpName(name) ? name : "FunctionCall";

    OperationBuilder opBuilder = scope.env()
        .opBuilder(stateful ? STATEFUL_CALL_OP : CALL_OP, scope.makeOpName(displayName));

    opBuilder.addInputList(inputList.stream().map(Operand::asOutput).toArray(Output[]::new));

    opBuilder.setFunctionName("f", name);
    opBuilder.setAttr("Tin", inputList.stream().map(x -> x.asOutput().dataType()).toArray(DataType[]::new));
    opBuilder.setAttr("Tout", signature().getOutputs().values().stream().map(x -> x.dataType).toArray(DataType[]::new));

    opBuilder = scope.apply(opBuilder);
    Operation op = opBuilder.build();

    int numOutputs1 = op.numOutputs();
    List<Operand<?>> outputList = new ArrayList<>(signature().outputNames().size());

    for (int i = 0; i < numOutputs1; i++) {
      outputList.add(op.output(i));
    }

    Map<String, Operand<?>> namedOutputs = new LinkedHashMap<>(signature().outputNames().size());

    List<String> outputNames = new ArrayList<>(signature().outputNames());
    for (int i = 0; i < outputNames.size(); i++) {
      String outputName = outputNames.get(i);

      if (i > outputList.size()) {
        throw new IllegalStateException("Somehow, not all required outputs were returned from the function");
      }

      Operand<?> output = outputList.get(i);
      namedOutputs.put(outputName, output);
    }

    return Collections.unmodifiableMap(namedOutputs);
  }

  /**
   * Calls the function in an execution environment, adding it's graph as a function if it isn't already present. Only
   * works for functions with a single input and output.
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

    Map<String, Operand<?>> inputMap = new LinkedHashMap<>();
    inputMap.put(inputName, argument);

    return call(scope, inputMap).get(outputName);
  }

  /**
   * Invokes a function using the default eager session.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param arguments list of tensors to pass in input to the function, mapped by their signature name
   * @return output tensors resulting from the execution of the function, mapped by their signature name
   */
  public Map<String, Tensor> call(Map<String, Tensor> arguments)
      throws IllegalArgumentException {
    //FIXME need to manage input/output operand lifetimes
    Ops tf = Ops.create();
    Map<String, Operand<?>> inputs = new LinkedHashMap<>(arguments.size());

    for (String inputName : arguments.keySet()) {
      Tensor argument = arguments.get(inputName);
      inputs.put(inputName, tf.constantOf((TType) argument));
    }
    Map<String, Operand<?>> outputs = tf.call(this, inputs);
    Map<String, Tensor> tensorOutputs = new LinkedHashMap<>(outputs.size());
    for (String outputName : outputs.keySet()) {
      tensorOutputs.put(outputName, outputs.get(outputName).asTensor());
    }
    return tensorOutputs;
  }

  /**
   * Invokes a function with a single input and output using the default eager session.
   *
   * <p>Caller is responsible for closing all Tensors.
   *
   * @param tensor input tensor
   * @return output tensor
   * @throws IllegalArgumentException if there are multiple input or output parameters defined in the function
   */
  public Tensor call(Tensor tensor) throws IllegalArgumentException {
    Ops tf = Ops.create();
    Operand<?> argument = tf.constantOf((TType) tensor);
    Operand<?> output = call(tf, argument);
    return output.asTensor();
  }

  /**
   * Calls the function in an execution environment, adding it's graph as a function if it isn't already present. The
   * inputs and outputs are keyed by the names set in the {@code Signature}.
   *
   * @param tf the scope to call the function in
   * @param arguments the arguments to the call
   * @return the outputs of the function
   */
  public Map<String, Operand<?>> call(Ops tf, Map<String, Operand<?>> arguments) {
    return tf.call(this, arguments);
  }

  /**
   * Calls the function in an execution environment, adding it's graph as a function if it isn't already present. Only
   * works for functions with a single input and output.
   *
   * @param tf the scope to call the function in
   * @param argument the argument to the call
   * @return the output of the function
   */
  public Operand<?> call(Ops tf, Operand<?> argument) {
    return tf.call(this, argument);
  }

  /**
   * Export this function as a saved model.
   *
   * <p>This method is convenient shortcut equivalent to
   * {@code SavedModel.exporter(exportDir).withFunction(this).export()}
   *
   * @param exportDir directory where to export the saved model
   * @throws IOException if saved model or variable state cannot be written on disk
   */
  public void save(String exportDir) throws IOException {
    SavedModelBundle.exporter(exportDir).withFunction(this).export();
  }

  public boolean isStateful() {
    return stateful;
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
   * FIXME: This causes native errors when I use it (Linux GPU, 6.1 CC), but I'm leaving it because how to enable XLA
   * JIT is extremely non-obvious.
   *
   * Causes {@code OP_REQUIRES failed at xla_ops.cc:363 : Not found: could not find registered platform with id:
   * 0x7f75af03e6e8} (it's a warning, but the resulting TF_Status fails).
   */
  private void makeJit() {
    try (PointerScope scope = new PointerScope()) {
      byte[] bytes = AttrValue.newBuilder().setB(true).build().toByteArray();
      BytePointer trueValue = new BytePointer(bytes);

      TF_Status status1 = TF_Status.newStatus();
      TF_FunctionSetAttrValueProto(nativeHandle(), "_XlaMustCompile", trueValue, bytes.length, status1);
      status1.throwExceptionIfNotOK();

      TF_Status status2 = TF_Status.newStatus();
      TF_FunctionSetAttrValueProto(nativeHandle(), "_noinline", trueValue, bytes.length, status2);
      status2.throwExceptionIfNotOK();
    }
  }

  TF_Function nativeHandle() {
    if (nativeHandle.isNull()) {
      throw new IllegalStateException("Function has been closed");
    }
    return nativeHandle;
  }

  /**
   * Get the native handle of the function's gradient, so that it can be attached to a Graph.  Not implemented yet.
   *
   * TODO implement
   */
  TF_Function gradNativeHandle() {
    return null;
  }

  private final Signature signature;
  private final TF_Function nativeHandle;
  private final PointerScope scope;
  private final boolean stateful;

  ConcreteFunction(Signature signature, TF_Function nativeHandle, boolean stateful) {
    this.signature = signature;
    try (PointerScope scope = new PointerScope()) {
      scope.extend();
      this.nativeHandle = nativeHandle.withDeallocator();
      scope.attach(nativeHandle);
      this.scope = scope;
    }
    this.stateful = stateful;
  }

  /**
   * Detects the signature from the handle
   */
  static ConcreteFunction fromNativeHandle(TF_Function function) {

    FunctionDef funcDef = null;
    try (PointerScope scope = new PointerScope()) {
      TF_Buffer funcDefBuffer = TF_Buffer.newBuffer();
      TF_Status status2 = TF_Status.newStatus();
      TF_FunctionToFunctionDef(function, funcDefBuffer, status2);
      status2.throwExceptionIfNotOK();
      try {
        funcDef = FunctionDef.parseFrom(funcDefBuffer.dataAsByteBuffer());
      } catch (InvalidProtocolBufferException e) {
        throw new IllegalStateException("Failed to parse FunctionDef proto", e);
      }
    }

    Signature.Builder builder = Signature.builder().methodName(funcDef.getSignature().getName())
        .key(TF_FunctionName(function).getString());

    for (ArgDef input : funcDef.getSignature().getInputArgList()) {
      TensorInfo info = TensorInfo.newBuilder()
          .setDtype(input.getType())
          .setTensorShape(TensorShapeProto.newBuilder().setUnknownRank(true).build())
          .setName(input.getName())
          .build();

      builder.input(input.getName(), info);
    }

    for (ArgDef outputDef : funcDef.getSignature().getOutputArgList()) {
      TensorInfo info = TensorInfo.newBuilder()
          .setDtype(outputDef.getType())
          .setTensorShape(TensorShapeProto.newBuilder().setUnknownRank(true).build())
          .setName(outputDef.getName())
          .build();

      builder.output(outputDef.getName(), info);
    }

    return new ConcreteFunction(
        builder.build(),
        function,
        funcDef.getNodeDefList().stream().anyMatch(x -> TensorFlow.isOpStateful(x.getOp()))
    );
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

  /**
   * Returns the function name if {@code op} is a function call op, or null otherwise.
   */
  static String findFunctionCall(GraphOperation op) {
    if (op.type().equals(STATEFUL_CALL_OP) || op.type().equals(CALL_OP)) {
      try (PointerScope scope = new PointerScope()) {
        TF_Status status = TF_Status.newStatus();
        TF_Buffer buff = TF_Buffer.newBuffer();
        TF_OperationGetAttrValueProto(op.getUnsafeNativeHandle(), "f", buff, status);
        status.throwExceptionIfNotOK();
        AttrValue def = AttrValue.parseFrom(buff.dataAsByteBuffer());

        return def.getFunc().getName();
      } catch (InvalidProtocolBufferException e) {
        return null;
      }
    }

    return null;
  }

  private static ConcreteFunction buildFromGraph(Graph graph, Signature signature) {
    try (PointerScope scope = new PointerScope();
        Reference ref = graph.ref()) {
      TF_Status status = TF_Status.newStatus();

      List<Operand<?>> inputs = signature.getInputs().values().stream()
          .map((x) -> graph.outputOrError(x.name))
          .collect(Collectors.toList());

      List<Operand<?>> outputs = signature.getOutputs().values().stream()
          .map((x) -> graph.outputOrError(x.name))
          .collect(Collectors.toList());

      List<GraphOperation> ops = new ArrayList<>(
          graph.completeSubgraph(new HashSet<>(inputs), new HashSet<>(outputs),
              null, Collections.singleton(Placeholder.OP_NAME)));

      inputs.forEach(input -> ops.remove(input.op()));

      // Python sometimes has NoOps as outputs
      Ops tf = Ops.create(graph).withSubScope("functionControlOutputs");
      for (int i = 0; i < outputs.size(); i++) {
        Operand<?> output = outputs.get(i);
        if (output.op().numOutputs() < 1) {
          Operand<TBool> realOutput = tf
              .withControlDependencies(Collections.singletonList(output))
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

      TF_Function handle = TF_GraphToFunction(
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
          new BytePointer(signature.methodName() != null ? signature.methodName() : "Method " + signature.key()),
          status
      );

      status.throwExceptionIfNotOK();
      return new ConcreteFunction(signature, handle, ops.stream().anyMatch(x -> TensorFlow.isOpStateful(x.type())));
    }
  }

  ConcreteFunction withNewSignature(Signature signature) {
    if (this.signature.getInputs().size() != signature.getInputs().size()) {
      throw new IllegalArgumentException(
          "New signature must have the same number of inputs.  Expected " + this.signature.getInputs().size() + ", got "
              + signature.getInputs().size());
    }

    if (this.signature.getOutputs().size() != signature.getOutputs().size()) {
      throw new IllegalArgumentException(
          "New signature must have the same number of inputs.  Expected " + this.signature.getInputs().size() + ", got "
              + signature.getInputs().size());
    }

    List<DataType> inputs = this.signature.getInputs().values().stream().map(x -> x.dataType)
        .collect(Collectors.toList());
    List<DataType> newInputs = signature.getInputs().values().stream().map(x -> x.dataType)
        .collect(Collectors.toList());

    if (!inputs.equals(newInputs)) {
      throw new IllegalArgumentException(
          "Data types of the new signature's inputs (in order) must match.  Expected " + inputs + ", got " + newInputs);
    }

    List<DataType> outputs = this.signature.getOutputs().values().stream().map(x -> x.dataType)
        .collect(Collectors.toList());
    List<DataType> newOutputs = signature.getOutputs().values().stream().map(x -> x.dataType)
        .collect(Collectors.toList());

    if (!outputs.equals(newOutputs)) {
      throw new IllegalArgumentException(
          "Data types of the new signature's outputs (in order) must match.  Expected " + outputs + ", got "
              + newOutputs);
    }

    return new ConcreteFunction(signature, nativeHandle, stateful);
  }
}