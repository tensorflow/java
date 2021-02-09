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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

/**
 * A Function created from a TensorFlow {@link Graph}. Callable in Graph or Eager mode via the {@link
 * Ops#callFunction(DefinedFunction, Operand[])} ops or the {@link #call(Ops, Operand[])} methods.
 *
 * <p>By default, resolves arguments and returns outputs by position.
 * Names can be specified for the inputs and outputs (and used when calling) using {@link NamedGraphFunction} and {@link
 * #create(Graph, String, boolean, Map, Map, String)}.
 *
 * Functions can also be created from {@link ConcreteFunction}s using {@link #create(ConcreteFunction, boolean)}.
 */
public class DefinedFunction implements AutoCloseable {

  private final TF_Function nativeHandle;
  private final PointerScope scope;
  private final int numInputs;
  private final int numOutputs;

  DefinedFunction(TF_Function nativeHandle, int numInputs, int numOutputs) {
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
   * Create a {@link DefinedFunction} with positional argument and output resolution. Includes all ops in {@code body},
   * {@code inputs} and {@code outputs} must be the inputs and outputs of {@code body}.
   *
   * @param body the graph to use as the function body
   * @param name the name of the function
   * @param appendHash whether to append the has of the signature to the name
   * @param inputs the inputs to the function
   * @param outputs the outputs to the function
   * @param description a human-readable description of the function.  May be null.
   */
  public static DefinedFunction create(Graph body, String name, boolean appendHash, List<Operand<?>> inputs,
      List<Operand<?>> outputs, String description) {
    try (PointerScope scope = new PointerScope();
        Reference ref = body.ref()) {
      TF_Status status = TF_Status.newStatus();

      TF_Function handle = TF_GraphToFunction(
          ref.nativeHandle(),
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

      return new DefinedFunction(handle.withDeallocator(), inputs.size(), outputs.size());
    }
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
    return tf.call(this, arguments);
  }

  /**
   * Call this function in the provided scope, with the given arguments.
   */
  public List<Operand<?>> call(Ops tf, Operand<?>... arguments) {
    return call(tf, Arrays.asList(arguments));
  }
}
