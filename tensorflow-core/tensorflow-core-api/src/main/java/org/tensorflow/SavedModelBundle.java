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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Session;
import org.tensorflow.internal.c_api.TF_SessionOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.MetaGraphDef;
import org.tensorflow.proto.framework.MetaGraphDef.MetaInfoDef;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.framework.SavedModel;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.framework.TensorShapeProto;
import org.tensorflow.proto.framework.TensorShapeProto.Dim;

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

  public static final String DEFAULT_SIGNATURE_NAME = "serving_default";

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

    public Exporter withTags(String... tags) {
      this.tags.addAll(Arrays.asList(tags));
      return this;
    }

    public Exporter withFunction(FunctionGraph functionGraph) {
    }

    public Exporter withSignature(SignatureDef signatureDef) {
      return withSignature(DEFAULT_SIGNATURE_NAME, signatureDef);
    }

    public Exporter withSignature(String signatureName, SignatureDef signature) {
      metaGraphDefBuilder.putSignatureDef(signatureName, signature);
      return this;
    }

    public void export(Session session) throws IOException {
      Graph graph = session.graph();
      if (tags.isEmpty()) {
        tags.add("serve");
      }
      // It is imperative to retrieve the graphDef after the saverDef, as the former might add
      // new ops to the graph.
      MetaGraphDef metaGraphDef = metaGraphDefBuilder
          .setSaverDef(graph.saverDef())
          .setGraphDef(graph.toGraphDef())
          .setMetaInfoDef(MetaInfoDef.newBuilder().addAllTags(tags))
          .build();

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
    private final MetaGraphDef.Builder metaGraphDefBuilder = MetaGraphDef.newBuilder();
    private final List<String> tags = new ArrayList<>();
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
    SignatureDef signature = metaGraphDef.getSignatureDefMap().get(functionSignatureName);
    if (signature == null) {
      throw new IllegalArgumentException(
          String.format("Function with signature [%s] not found", functionSignatureName));
    }
    return new FunctionGraph(session, signature);
  }

  /**
   * Return the {@link FunctionGraph} corresponding to the default function signature of this model.
   *
   * @param functionSignatureName name of the {@code SignatureDef} in the saved model.
   * @return TfFunction object that can be used to make calls to the tf.function
   * @throws IllegalArgumentException if no function with the default signature name can be found in
   *                                  this saved model.
   */
  public FunctionGraph function() {
    return function(DEFAULT_SIGNATURE_NAME);
  }

  /**
   * Releases resources (the {@link Graph} and {@link Session}) associated with the saved model
   * bundle.
   */
  @Override
  public void close() {
    functions.forEach((s, f) -> f.close());
  }

  private final MetaGraphDef metaGraphDef;
  private final Map<String, FunctionGraph> functions;

  private SavedModelBundle(MetaGraphDef metaGraphDef, Map<String, FunctionGraph> functions) {
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

    // For each signature, we will create a separate function. To support cases where multiple
    // signatures are attached to the same graph, each function instance will retain a reference
    // to the underlying resource, so that they are freed only when the last function is released.
    final Map<String, FunctionGraph> functions = new HashMap<>(metaGraphDef.getSignatureDefCount());
    metaGraphDef.getSignatureDefMap().forEach((signatureName, signatureDef) -> {
      graphHandle.retainReference();
      sessionHandle.retainReference();
      functions.put(signatureName, new FunctionGraph(session, signatureDef));
    });
    return new SavedModelBundle(metaGraphDef, functions);
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
