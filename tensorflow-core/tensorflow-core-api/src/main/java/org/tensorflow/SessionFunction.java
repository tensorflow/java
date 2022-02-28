/* Copyright 2021-2022 The TensorFlow Authors. All Rights Reserved.

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

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A callable function backed by a session. All calls of this function will be ran on the same
 * session.
 *
 * <p>Does no resource management, the session and all returned tensors are the caller's
 * responsibility.
 *
 * <p>Does not initialize the session, since it may be shared.
 */
public class SessionFunction implements TensorFunction {

  private final Signature signature;
  private final Session session;

  public SessionFunction(Signature signature, Session session) {
    this.signature = signature;
    this.session = session;

    signature
        .getInputs()
        .forEach(
            (name, description) -> {
              TensorFunction.validateDescription(description, session.graph(), name, "Input");
            });

    signature
        .getInputs()
        .forEach(
            (name, description) -> {
              TensorFunction.validateDescription(description, session.graph(), name, "Output");
            });
  }

  public static SessionFunction create(Signature signature, Session session) {
    return new SessionFunction(signature, session);
  }

  /**
   * Save this function using {@link SavedModelBundle}.
   *
   * <p>This is identical to calling {@code
   * SavedModelBundle.exporter(exportDir).withFunction(this).export()}.
   *
   * @param exportDir the directory path containing a saved model.
   * @throws IOException if saved model or variable state cannot be written on disk
   */
  public void save(String exportDir) throws IOException {
    SavedModelBundle.exporter(exportDir).withFunction(this).export();
  }

  @Override
  public Signature signature() {
    return signature;
  }

  public Session session() {
    return session;
  }

  /**
   * Get a new function with the same signature, but backed by a new session.
   *
   * @param session the new backing session.
   */
  public SessionFunction withNewSession(Session session) {
    return new SessionFunction(signature, session);
  }

  @Override
  public Result call(Map<String, Tensor> arguments) {
    Session.Runner runner = session.runner();
    signature
        .getInputs()
        .forEach(
            (argName, operand) -> {
              if (!arguments.containsKey(argName)) {
                throw new IllegalArgumentException(
                    "No argument found for parameter \"" + argName + "\"");
              }
              Tensor value = arguments.get(argName);

              if (value == null) {
                throw new IllegalArgumentException(
                    "Can't pass null as an argument to a function.  Argument \""
                        + argName
                        + "\" was null.");
              }

              runner.feed(operand.name, value);
            });

    signature.getOutputs().values().forEach(x -> runner.fetch(x.name));

    Result results = runner.run();

    // Unpack the result object and rebuild it with the expected names.
    LinkedHashMap<String, Tensor> outputs = new LinkedHashMap<>(results.size());
    int i = 0;
    for (String outputName : signature.outputNames()) {
      outputs.put(outputName, results.get(i));
      i++;
    }

    return new Result(outputs);
  }
}
