/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==============================================================================
 */
package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionName;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_FunctionSetAttrValueProto;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_GraphToFunction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.Graph.Reference;
import org.tensorflow.internal.c_api.TF_Function;
import org.tensorflow.internal.c_api.TF_Operation;
import org.tensorflow.internal.c_api.TF_Output;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.op.Ops;
import org.tensorflow.proto.framework.AttrValue;
import org.tensorflow.proto.framework.TensorInfo;

/**
 * A Function created from a TensorFlow {@link Graph}. Callable in Graph or Eager mode via the {@link
 * Ops#callFunction(GraphFunction, Operand[])} ops or the {@link #call(Ops, Operand[])} methods.
 *
 * <p>By default, resolves arguments and returns outputs by position.
 * Names can be specified for the inputs and outputs (and used when calling) using {@link NamedGraphFunction} and {@link
 * #create(Graph, String, boolean, Map, Map, String)}.
 *
 * Functions can also be created from {@link ConcreteFunction}s using {@link #create(ConcreteFunction, boolean)}.
 */
public class GraphFunction implements AutoCloseable {

  private final TF_Function nativeHandle;
  private final PointerScope scope;
  private final int numInputs;
  private final int numOutputs;

  GraphFunction(TF_Function nativeHandle, int numInputs, int numOutputs) {
    this.numInputs = numInputs;
    this.numOutputs = numOutputs;
    scope = new PointerScope();
    this.nativeHandle = nativeHandle;
    scope.attach(nativeHandle);
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

  private static TF_Output resolveToOutput(List<Operand<?>> operands) {
    TF_Output handles = new TF_Output(operands.size());
    for (int i = 0; i < operands.size(); i++) {
      Operand<?> input = operands.get(i);
      //TODO body.checkInput(input)
      TF_Operation handle = outputHandle(input);
      handles.position(i).oper(handle).index(input.asOutput().index());
    }
    handles.position(0);
    return handles;
  }

  /**
   * Create a {@link GraphFunction} with positional argument and output resolution. Includes all ops in {@code body},
   * {@code inputs} and {@code outputs} must be the inputs and outputs of {@code body}.
   *
   * @param body the graph to use as the function body
   * @param name the name of the function
   * @param appendHash whether to append the has of the signature to the name
   * @param inputs the inputs to the function
   * @param outputs the outputs to the function
   * @param description a human-readable description of the function.  May be null.
   */
  public static GraphFunction create(Graph body, String name, boolean appendHash, List<Operand<?>> inputs,
      List<Operand<?>> outputs, String description) {
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();

      TF_Function handle = TF_GraphToFunction(
          body.ref().nativeHandle(),
          name,
          (byte) (appendHash ? 1 : 0),
          -1,
          null,
          inputs.size(),
          resolveToOutput(inputs),
          outputs.size(),
          resolveToOutput(outputs),
          (byte[]) null,
          null,
          description,
          status
      );

      status.throwExceptionIfNotOK();

      return new GraphFunction(handle.withDeallocator(), inputs.size(), outputs.size());
    }
  }


  /**
   * Create a {@link NamedGraphFunction} with name-based argument and output resolution. Includes all ops in {@code
   * body}, {@code inputs} and {@code outputs} must be the inputs and outputs of {@code body}.
   *
   * @param body the graph to use as the function body
   * @param name the name of the function
   * @param appendHash whether to append the has of the signature to the name
   * @param inputs the inputs to the function and their names
   * @param outputs the outputs to the function and their names
   * @param description a human-readable description of the function.  May be null.
   */
  public static NamedGraphFunction create(Graph body, String name, boolean appendHash, Map<String, Operand<?>> inputs,
      Map<String, Operand<?>> outputs, String description) {
    List<Operand<?>> inputList = new ArrayList<>();
    List<String> inputNames = new ArrayList<>();
    for (Entry<String, Operand<?>> input : inputs.entrySet()) {
      inputNames.add(input.getKey());
      inputList.add(input.getValue());
    }

    List<Operand<?>> outputList = new ArrayList<>();
    List<String> outputNames = new ArrayList<>();
    for (Entry<String, Operand<?>> output : outputs.entrySet()) {
      outputNames.add(output.getKey());
      outputList.add(output.getValue());
    }

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();

      //TODO can't get output names working, likely due to passing it wrong

//      PointerPointer<CharPointer> outputNamesPointer = new PointerPointer<>(outputNames.size());
//      outputNamesPointer.putString(outputNames.toArray(new String[0]));

      try (Reference ref = body.ref()) {
        TF_Function handle = TF_GraphToFunction(
            ref.nativeHandle(),
            name,
            (byte) (appendHash ? 1 : 0),
            -1,
            null,
            inputList.size(),
            resolveToOutput(inputList),
            outputList.size(),
            resolveToOutput(outputList),
            (byte[]) null,
            null,
            description,
            status
        );

        status.throwExceptionIfNotOK();

        return new NamedGraphFunction(handle.withDeallocator(), inputNames, outputNames);
      }
    }
  }

  /**
   * Create a {@link NamedGraphFunction} from a {@link ConcreteFunction}.
   *
   * @param function the {@code ConcreteFunction} to create the function from
   * @param appendHash whether to append a hash of the signature to the name
   */
  public static NamedGraphFunction create(ConcreteFunction function, boolean appendHash) {
    Graph graph = function.graph();

    Map<String, Operand<?>> inputs = new LinkedHashMap<>();
    for (Entry<String, TensorInfo> input : function.signature().asSignatureDef().getInputsMap().entrySet()) {
      inputs.put(input.getKey(), graph.output(input.getValue().getName()));
    }

    Map<String, Operand<?>> outputs = new LinkedHashMap<>();
    for (Entry<String, TensorInfo> output : function.signature().asSignatureDef().getOutputsMap().entrySet()) {
      outputs.put(output.getKey(), graph.output(output.getValue().getName()));
    }

    String description = function.signature().methodName();
    if (description == null) {
      description = function.signature().key();
    }

    return create(graph, function.signature().key(), appendHash, inputs, outputs, description);
  }

  TF_Function nativeHandle() {
    if (nativeHandle.isNull()) {
      throw new IllegalStateException("Function has been closed");
    }
    return nativeHandle;
  }

  /**
   * Get the name of the function.
   */
  public String getName() {
    try (PointerScope scope = new PointerScope()) {
      return TF_FunctionName(nativeHandle()).getString();
    }
  }

  /**
   * Close the function
   */
  @Override
  public void close() {
    scope.close();
  }

  /**
   * Get the native handle of the function's gradient, so that it can be attached to a Graph.  Not implemented yet.
   *
   * TODO implement
   */
  TF_Function gradNativeHandle() {
    return null;
  }

  /**
   * Get the number of inputs to the function
   */
  public int getNumInputs() {
    return numInputs;
  }

  /**
   * Get the number of outputs to the function
   */
  public int getNumOutputs() {
    return numOutputs;
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public List<Operand<?>> call(Ops tf, List<Operand<?>> arguments) {
    return tf.callFunction(this, arguments);
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public List<Operand<?>> call(Ops tf, Operand<?>... arguments) {
    return tf.callFunction(this, arguments);
  }
}
