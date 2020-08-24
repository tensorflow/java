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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
import org.tensorflow.proto.framework.MetaGraphDef.MetaInfoDef;
import org.tensorflow.proto.framework.MetaGraphDefOrBuilder;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.framework.SavedModel;
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

  public static final String DEFAULT_TAG = "serve";

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

  /** Options for exporting a SavedModel. */
  public static final class Exporter {

    /**
     * Sets the set of tags that identify the specific graph in the saved model to save.
     *
     * <p/>Note that only one graph per model can be saved right now using this API.
     *
     * @param tags the tags identifying the specific MetaGraphDef to save.
     * @return this object
     */
    public Exporter withTags(String... tags) {
      this.tags.addAll(Arrays.asList(tags));
      return this;
    }

    /**
     * Save a function with this model.
     *
     * <p/>The function carries a signature (i.e. a list of user-friendly input and outputs names to
     * a graph) and a valid session to a graph to be saved in the model.
     *
     * <p/><i>Note:Eventually, TensorFlow for Java will support the export of functions objects like the
     * Python API does. But right now, only session-centric models are supported (i.e. models that
     * has a single main graph and one or more signatures), like TensorFlow 1.x and estimators do.
     *
     * <p/>Still, the actual Java API is "function-ready", meaning that the signatures exposed by the
     * main graph are provided as `FunctionGraph` objects. Only functions based on the same graph
     * can then be saved within a single model, or an exception will be thrown.</i>
     *
     * @param function a function carrying a signature and a valid session to graph to be saved
     * @return this object
     * @throws IllegalArgumentException if a function with the same name has already been added to the model
     */
    public Exporter function(FunctionGraph function) {
      if (functions.containsKey(function.name())) {
        throw new IllegalArgumentException("Function \"" + function.name() + "\" was already added to the model");
      }
      functions.put(function.name(), function);
      if (session == null) {
        session = function.session();
      } else if (session != function.session()) {
        throw new UnsupportedOperationException("Saving multiple functions with different graphs/sessions is not supported yet.");
      }
      metaGraphDefBuilder.putSignatureDef(function.name(), function.signatureDef());
      return this;
    }

    /**
     * Save the model into the export directory.
     *
     * @throws IOException if saved model or variable state can be written on disk
     */
    public void export() throws IOException {
      if (functions.isEmpty() || session == null) {
        throw new IllegalStateException("Model should contain at least one valid function");
      }
      if (tags.isEmpty()) {
        tags.add(DEFAULT_TAG);
      }
      // It is imperative to retrieve the graphDef after the saverDef, as the former might add
      // new ops to the graph.
      Graph graph = session.graph();
      MetaGraphDef.Builder metaGraphDef = metaGraphDefBuilder
          .setSaverDef(graph.saverDef())
          .setGraphDef(graph.toGraphDef())
          .setMetaInfoDef(MetaInfoDef.newBuilder().addAllTags(tags));
      functions.forEach((k, f) -> metaGraphDef.putSignatureDef(k, f.signatureDef()));

      // Make sure saved model directories exist
      Path variableDir = Paths.get(exportDir, "variables");
      variableDir.toFile().mkdirs();

      // Save variables state, using the "variables-*" prefix
      session.save(variableDir.resolve("variables").toString());

      // Save graph
      SavedModel savedModelDef = SavedModel.newBuilder().addMetaGraphs(metaGraphDef).build();
      try (OutputStream file =
          new FileOutputStream(Paths.get(exportDir, "saved_model.pb").toString())) {
        savedModelDef.writeTo(file);
      }
    }

    Exporter(String exportDir) {
      this.exportDir = exportDir;
    }

    private final String exportDir;
    private final List<String> tags = new ArrayList<>();
    private final MetaGraphDef.Builder metaGraphDefBuilder = MetaGraphDef.newBuilder();
    private final Map<String, FunctionGraph> functions = new HashMap<>();
    private Session session;
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
   * Export a saved model.
   *
   * <p/>Returns a <code>Exporter</code> object for setting configuration options before actually
   * saving the model.
   *
   * @param exportDir the directory path containing a saved model.
   */
  public static Exporter exporter(String exportDir) {
    return new Exporter(exportDir);
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
   * Return a {@link FunctionGraph} corresponding to the function signature.
   *
   * <pre>{@code
   * FunctionGraph myFunction = savedModelBundle.function("myFunctionSignatureName");
   * Map<String, Tensor<?>> outputTensorMap = myFunction.call(session, inputTensorMap);
   * }</pre>
   *
   * @param functionSignatureName name of the {@code SignatureDef} in the saved model.
   * @return TfFunction object that can be used to make calls to the tf.function
   * @throws IllegalArgumentException if {@code functionSignatureName} is not found in this
   *                                  saved model.
   */
  public FunctionGraph function(String functionSignatureName) {
    FunctionGraph function = functions.get(functionSignatureName);
    if (function == null) {
      throw new IllegalArgumentException(
          String.format("Function with signature [%s] not found", functionSignatureName));
    }
    return function;
  }

  /**
   * Invokes the default function directly from this model.
   *
   * <p/>The default function selection is done based on the first of the following conditions that
   * is true:
   * <ul>
   *   <li>The function is the only signature available attached to the main graph of this saved model</li>
   *   <li>The function is mapped to the default signature name, which is "serving_default"</li>
   * </ul>
   *
   * <p>Caller is responsible for closing all returned Tensors.
   *
   * @param arguments list of input tensors, mapped by their signature name
   * @return list of output tensors, mapped by the signature name
   * @throws IllegalArgumentException if no function can be selected by default
   */
  public Map<String, Tensor<?>> call(Map<String, Tensor<?>> arguments) {
    FunctionGraph function = null;
    if (functions.size() == 1) {
      function = functions.values().iterator().next();
    } else {
      function = functions.get(FunctionGraph.DEFAULT_NAME);
    }
    if (function == null) {
      throw new IllegalArgumentException("Cannot elect a default function for this model");
    }
    return function.call(arguments);
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
  private final Map<String, FunctionGraph> functions;

  private SavedModelBundle(Graph graph, Session session, MetaGraphDef metaGraphDef, Map<String, FunctionGraph> functions) {
    this.graph = graph;
    this.session = session;
    this.metaGraphDef = metaGraphDef;
    this.functions = functions;
  }

  /**
   * Create a SavedModelBundle object from a handle to the C TF_Graph object and to the C TF_Session
   * object, plus the MetaGraphDef.
   *
   * <p>Invoked from the native load method. Takes ownership of the handles.
   */
  private static SavedModelBundle fromHandle(
      final TF_Graph graphHandle, final TF_Session sessionHandle, MetaGraphDef metaGraphDef) {

    final Graph graph = new Graph(graphHandle, metaGraphDef.getSaverDef());
    final Session session = new Session(graph, sessionHandle);

    // For each signature definition, create a distinct function based on the main graph/session
    final Map<String, FunctionGraph> functions = new HashMap<>(metaGraphDef.getSignatureDefCount());
    metaGraphDef.getSignatureDefMap().forEach((signatureName, signatureDef) -> {
      functions.put(signatureName, new FunctionGraph(signatureName, signatureDef, session));
    });
    return new SavedModelBundle(graph, session, metaGraphDef, functions);
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
