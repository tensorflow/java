/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_LoadSessionFromSavedModel;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewGraph;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_SetConfig;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Session;
import org.tensorflow.internal.c_api.TF_SessionOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.MetaGraphDef;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.framework.SignatureDef;

/**
 * SavedModelBundle represents a model loaded from storage.
 *
 * <p>The model consists of a description of the computation (a {@link Graph}), a {@link Session}
 * with tensors (e.g., parameters or variables in the graph) initialized to values saved in storage,
 * and a description of the model as a <a
 * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/meta_graph.proto">MetaGraphDef
 * protocol buffer</a>.
 */
public class SavedModelBundle implements AutoCloseable {
  /** Options for loading a SavedModel. */
  public static final class Loader {
    /** Load a <code>SavedModelBundle</code> with the configured options. */
    public SavedModelBundle load() {
      return SavedModelBundle.load(exportDir, tags, configProto, runOptions);
    }

    /**
     * Sets options to use when executing model initialization operations.
     *
     * @param options A <a
     *     href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">RunOptions
     *     protocol buffer</a>.
     */
    public Loader withRunOptions(RunOptions options) {
      this.runOptions = options;
      return this;
    }

    /**
     * Set configuration of the <code>Session</code> object created when loading the model.
     *
     * @param configProto A <a
     *     href="https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto">ConfigProto
     *     protocol buffer</a>.
     */
    public Loader withConfigProto(ConfigProto configProto) {
      this.configProto = configProto;
      return this;
    }

    /**
     * Sets the set of tags that identify the specific graph in the saved model to load.
     *
     * @param tags the tags identifying the specific MetaGraphDef to load.
     */
    public Loader withTags(String... tags) {
      this.tags = tags;
      return this;
    }

    private Loader(String exportDir) {
      this.exportDir = exportDir;
    }

    private String exportDir = null;
    private String[] tags = null;
    private ConfigProto configProto = null;
    private RunOptions runOptions = null;
  }

  /**
   * SignatureToNodeName finds the node names in the {@link Graph} corresponding to the
   * input / output parameters of a <a
   * href="https://www.tensorflow.org/api_docs/python/tf/function">tf.function</a>
   */
  public static final class SignatureToNodeName {

    public SignatureToNodeName(SavedModelBundle savedModelBundle) {
      loadSignatures(savedModelBundle);
    }

    /**
     * Given a tf.function signature name, find the node names corresponding
     * to the input arguments
     *
     * @param functionSignatureName tf.function signature name
     * @return a map from input arguments to node names in the {@link Graph}
     */
    public Map<String, String> inputNameToNode(String functionSignatureName) {
      NameContainer nc = this.functionMap.get(functionSignatureName);
      return (nc == null) ? null : nc.inputNameToNode();
    }

    /**
     * Given a tf.function signature name, find the node names corresponding
     * to the output arguments
     *
     * @param functionSignatureName tf.function signature name
     * @return a map from output arguments to node names in the {@link Graph}
     */
    public Map<String, String> outputNameToNode(String functionSignatureName) {
      NameContainer nc = this.functionMap.get(functionSignatureName);
      return (nc == null) ? null : nc.outputNameToNode();
    }

    /**
     * Given a tf.function signature name, find the method name
     */
    public String methodName(String functionSignatureName) {
      NameContainer nc = this.functionMap.get(functionSignatureName);
      return (nc == null) ? null : nc.methodName();
    }

    private void loadSignatures(SavedModelBundle savedModelBundle) {
      MetaGraphDef metaGraph = savedModelBundle.metaGraphDef();
      Map<String, SignatureDef> signatureMap = metaGraph.getSignatureDefMap();

      // A saved model can contain multiple SignatureDef
      for (Map.Entry<String, SignatureDef> entry : signatureMap.entrySet()) {
        NameContainer nc = new NameContainer(entry.getValue());
        this.functionMap.put(entry.getKey(), nc);
      }
    }

    private Map<String, NameContainer> functionMap = new HashMap<>();

    private static final class NameContainer {
       NameContainer(SignatureDef sd) {
         this.inputNameToNodeName = sd.getInputsMap()
           .entrySet()
           .stream()
           .collect(Collectors.toMap(
              e -> e.getKey(),
              e -> e.getValue().getName()
          ));

         this.outputNameToNodeName = sd.getOutputsMap()
           .entrySet()
           .stream()
           .collect(Collectors.toMap(
              e -> e.getKey(),
              e -> e.getValue().getName()
          ));

         this.method = sd.getMethodName();
       }

       public Map<String, String> inputNameToNode() {
         return this.inputNameToNodeName;
       }

       public Map<String, String> outputNameToNode() {
         return this.outputNameToNodeName;
       }

       public String methodName() {
         return this.method;
       }

       private Map<String, String> inputNameToNodeName;
       private Map<String, String> outputNameToNodeName;
       private String method;
    }
  }

  /**
   * Load a saved model from an export directory. The model that is being loaded should be created
   * using the <a href="https://www.tensorflow.org/api_docs/python/tf/saved_model">Saved Model
   * API</a>.
   *
   * <p>This method is a shorthand for:
   *
   * <pre>{@code
   * SavedModelBundle.loader().withTags(tags).load();
   * }</pre>
   *
   * @param exportDir the directory path containing a saved model.
   * @param tags the tags identifying the specific metagraphdef to load.
   * @return a bundle containing the graph and associated session.
   */
  public static SavedModelBundle load(String exportDir, String... tags) {
    return loader(exportDir).withTags(tags).load();
  }

  /**
   * Load a saved model.
   *
   * <p/>Returns a <code>Loader</code> object that can set configuration options before actually
   * loading the model,
   *
   * @param exportDir the directory path containing a saved model.
   */
  public static Loader loader(String exportDir) {
    return new Loader(exportDir);
  }

  /**
   * Returns the <a
   * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/meta_graph.proto">MetaGraphDef
   * protocol buffer</a> associated with the saved model.
   */
  public MetaGraphDef metaGraphDef() {
    return metaGraphDef;
  }

  /** Returns the graph that describes the computation performed by the model. */
  public Graph graph() {
    return graph;
  }

  /**
   * Returns the {@link Session} with which to perform computation using the model.
   *
   * @return the initialized session
   */
  public Session session() {
    return session;
  }

  /**
   * Returns the {@link SignatureToNodeName} translator for the model.
   *
   * @return SignatureToNodeName translator
   */
  public SignatureToNodeName getSignatureToNodeName() {
    if (this.sigToNodeName == null) {
      // no need to lock, ok to create multiple instances
      this.sigToNodeName = new SignatureToNodeName(this);
    }
    return this.sigToNodeName;
  }

  /**
   * Return a {@link TfFunction} corresponding to the function signature.
   *
   * <pre>{@code
   * TfFunction myFunction = savedModelBundle.function("myFunctionSignatureName");
   * Map<String, Tensor<?>> outputTensorMap = myFunction.call(inputTensorMap);
   * }</pre>
   *
   * @param functionSignatureName name of the {@code SignatureDef} in the saved model.
   * @return TfFunction object that can be used to make calls to the tf.function
   */
  public TfFunction function(String functionSignatureName) {
    return new TfFunction(functionSignatureName, this.getSignatureToNodeName(), this.session);
  }

  /**
   * Releases resources (the {@link Graph} and {@link Session}) associated with the saved model
   * bundle.
   */
  @Override
  public void close() {
    session.close();
    graph.close();
  }

  private final Graph graph;
  private final Session session;
  private final MetaGraphDef metaGraphDef;
  private SignatureToNodeName sigToNodeName;

  private SavedModelBundle(Graph graph, Session session, MetaGraphDef metaGraphDef) {
    this.graph = graph;
    this.session = session;
    this.metaGraphDef = metaGraphDef;
  }

  /**
   * Create a SavedModelBundle object from a handle to the C TF_Graph object and to the C TF_Session
   * object, plus the MetaGraphDef.
   *
   * <p>Invoked from the native load method. Takes ownership of the handles.
   */
  private static SavedModelBundle fromHandle(
      TF_Graph graphHandle, TF_Session sessionHandle, MetaGraphDef metaGraphDef) {
    Graph graph = new Graph(graphHandle);
    Session session = new Session(graph, sessionHandle);
    return new SavedModelBundle(graph, session, metaGraphDef);
  }

  private static SavedModelBundle load(
      String exportDir, String[] tags, ConfigProto config, RunOptions runOptions) {
    SavedModelBundle bundle = null;

    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();

      // allocate parameters for TF_LoadSessionFromSavedModel
      TF_SessionOptions opts = TF_SessionOptions.newSessionOptions();
      if (config != null) {
        BytePointer configBytes = new BytePointer(config.toByteArray());
        TF_SetConfig(opts, configBytes, configBytes.capacity(), status);
        status.throwExceptionIfNotOK();
      }
      TF_Buffer runOpts = TF_Buffer.newBufferFromString(runOptions);

      // load the session
      TF_Graph graph = TF_NewGraph();
      TF_Buffer metagraphDef = TF_Buffer.newBuffer();
      TF_Session session = TF_LoadSessionFromSavedModel(
          opts, runOpts, new BytePointer(exportDir), new PointerPointer(tags),
          tags.length, graph, metagraphDef, status);
      status.throwExceptionIfNotOK();

      // handle the result
      try {
        bundle = fromHandle(graph, session, MetaGraphDef.parseFrom(metagraphDef.dataAsByteBuffer()));
      } catch (InvalidProtocolBufferException e) {
        throw new TensorFlowException("Cannot parse MetaGraphDef protocol buffer", e);
      }
    }

    return bundle;
  }

  static {
    TensorFlow.init();
  }
}
