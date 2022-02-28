/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Buffer;
import org.tensorflow.internal.c_api.TF_Graph;
import org.tensorflow.internal.c_api.TF_Session;
import org.tensorflow.internal.c_api.TF_SessionOptions;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.proto.framework.CollectionDef;
import org.tensorflow.proto.framework.CollectionDef.NodeList;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.MetaGraphDef;
import org.tensorflow.proto.framework.MetaGraphDef.MetaInfoDef;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.framework.SavedModel;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.proto.util.SaverDef;

/**
 * SavedModelBundle represents a model loaded from storage.
 *
 * <p><b>All operations on a loaded bundle, and any functions from it, share the same underlying
 * session.</b> The session is initialized when loaded.
 *
 * <p>The model consists of a description of the computation (a {@link Graph}), a {@link Session}
 * with tensors (e.g., parameters or variables in the graph) initialized to values saved in storage,
 * and a description of the model as a <a
 * href="https://www.tensorflow.org/code/tensorflow/core/protobuf/meta_graph.proto">MetaGraphDef
 * protocol buffer</a>.
 */
public class SavedModelBundle implements AutoCloseable {

  public static final String DEFAULT_TAG = "serve";

  /** Signature used to track Java init ops, for our init scope. */
  private static final String JAVA_INIT_OP_SIGNATURE_KEY = "__saved_model_java_init_op_tracker";

  /**
   * Tensorflow init op tracking signature. Init ops are executed before loading variables, so this
   * does not work for us.
   */
  private static final String INIT_OP_SIGNATURE_KEY = "__saved_model_init_op";

  /**
   * A backup Tensorflow init op collection key. In TF1, init ops will be stored in collections
   * instead of signatures.
   */
  private static final String MAIN_OP_COLLECTION_KEY = "saved_model_main_op";

  /** An even more legacy init op collection key. */
  private static final String LEGACY_INIT_OP_COLLECTION_KEY = "legacy_init_op";

  /** The collection where table initializers are stored in some hub models. */
  private static final String TABLE_INITIALIZERS_COLLECTION_KEY = "table_initializer";

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
     * @return this object
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
     * @return this object
     */
    public Loader withConfigProto(ConfigProto configProto) {
      this.configProto = configProto;
      return this;
    }

    /**
     * Sets the set of tags that identify the specific graph in the saved model to load.
     *
     * <p>Has no effect if {@code tags} is null or empty
     *
     * @param tags the tags identifying the specific MetaGraphDef to load.
     * @return this object
     * @throws IllegalArgumentException if tags are invalid
     */
    public Loader withTags(String... tags) {
      validateTags(tags);
      this.tags = tags;
      return this;
    }

    private Loader(String exportDir) {
      this.exportDir = exportDir;
    }

    private String exportDir = null;
    private String[] tags = {DEFAULT_TAG};
    private ConfigProto configProto = null;
    private RunOptions runOptions = null;
  }

  /** Options for exporting a SavedModel. */
  public static final class Exporter {

    /**
     * Sets the set of tags that identify the specific graph in the saved model to save.
     *
     * <p>Note that only one graph per model can be saved right now using this API.
     *
     * @param tags the tags identifying the specific MetaGraphDef to save.
     * @return this object
     * @throws IllegalArgumentException if tags are invalid
     */
    public Exporter withTags(String... tags) {
      validateTags(tags);
      this.tags = tags;
      return this;
    }

    /**
     * Set the session to export, without adding any signatures. This enables the use of {@link
     * #withSignature(Signature)}
     *
     * @throws IllegalStateException if the session is already set to a different session
     */
    public Exporter withSession(Session session) {
      if (this.session != null && this.session != session) {
        throw new IllegalStateException(
            "This exporter already has a session that differs from the passed session");
      }
      this.session = session;
      return this;
    }

    /**
     * Save a function of this model.
     *
     * <p>The function carries a signature (i.e. a list of user-friendly input and outputs names to
     * a graph) and a valid session to a graph to be saved in the model.
     *
     * <p><i>Note:Eventually, TensorFlow for Java will support the export of functions objects like
     * the Python API does but right now, only session-centric models are supported (i.e. models
     * that has a single main graph and one or more signatures). These models are compatible with
     * those exported by TensorFlow 1.x or by TensorFlow 2.x estimators. <br>
     * Therefore, all functions exported in a model should share the same session at the moment or
     * an exception will be thrown.</i> This applies to sessions set via {@link
     * #withSession(Session)} as well, the exporter can only even have one session.
     *
     * @param function a function carrying a signature and a valid session to the graph to be saved
     * @return this object
     * @throws IllegalArgumentException if a function with the same name has already been added to
     *     the model
     * @throws UnsupportedOperationException if the session is already set to a different session
     */
    public Exporter withFunction(SessionFunction function) {
      Signature signature = function.signature();
      if (functions.containsKey(signature.key())) {
        throw new IllegalArgumentException(
            "Function \"" + signature.key() + "\" was already added to the model");
      }
      if (session != null && session != function.session()) {
        throw new UnsupportedOperationException(
            "This exporter already has a session that differs from the passed function's session");
      }

      session = function.session();
      functions.put(signature.key(), function);
      metaGraphDefBuilder.putSignatureDef(signature.key(), signature.asSignatureDef());
      return this;
    }

    /**
     * Save multiple functions. Wrapper around {@link #withFunction(SessionFunction)}. All functions
     * must have the same session.
     *
     * @param functions the functions to export
     * @return this object
     * @throws IllegalArgumentException if a function with the same name has already been added to
     *     the model
     * @throws UnsupportedOperationException if the session is already set to a different session
     * @see #withFunction(SessionFunction)
     */
    public Exporter withFunctions(SessionFunction... functions) {
      for (SessionFunction f : functions) {
        withFunction(f);
      }
      return this;
    }

    /**
     * Add a signature to the model. This wraps the signature in a {@link SessionFunction} using the
     * exporter's already-set session. As such, <b>either {@link #withSession(Session)} or {@link
     * #withFunction(SessionFunction)} must be called before this method</b>.
     *
     * @throws IllegalStateException if no session has been set
     * @return this
     */
    public Exporter withSignature(Signature signature) {
      if (session == null) {
        throw new IllegalStateException(
            "Session has not been set yet, you must call withSession or withFunction first.");
      }
      return withFunction(session.function(signature));
    }

    /**
     * Add multiple signatures to the model. Wraps {@link #withSignature(Signature)}
     *
     * <p><b>Either {@link #withSession(Session)} or {@link * #withFunction(SessionFunction)} must
     * be called before this method</b>, and the session set there will be used for these
     * signatures.
     *
     * @throws IllegalStateException if no session has been set
     * @return this
     * @see #withSession(Session)
     */
    public Exporter withSignatures(Signature... signatures) {
      for (Signature s : signatures) {
        withSignature(s);
      }
      return this;
    }

    /**
     * Save the model into the export directory.
     *
     * @throws IOException if saved model or variable state cannot be written on disk
     */
    public void export() throws IOException {
      if (functions.isEmpty()) {
        throw new IllegalStateException("Model should contain at least one valid function");
      }
      Graph graph = session.graph();

      // It is imperative to retrieve the graphDef after the saverDef, as the former might add
      // new ops to the graph for saving and restoring the variables.
      SaverDef saverDef = graph.saverDef();

      GraphOperation initOp = null;
      if (!functions.containsKey(JAVA_INIT_OP_SIGNATURE_KEY)) {
        initOp = graph.addInitOp(true);
      }

      MetaGraphDef.Builder metaGraphDef =
          metaGraphDefBuilder
              .setSaverDef(saverDef)
              .setGraphDef(graph.toGraphDef())
              .setMetaInfoDef(MetaInfoDef.newBuilder().addAllTags(Arrays.asList(tags)));
      functions.forEach((k, f) -> metaGraphDef.putSignatureDef(k, f.signature().asSignatureDef()));

      if (!functions.containsKey(JAVA_INIT_OP_SIGNATURE_KEY)) {

        metaGraphDef.putSignatureDef(
            JAVA_INIT_OP_SIGNATURE_KEY,
            SignatureDef.newBuilder()
                .putOutputs(
                    JAVA_INIT_OP_SIGNATURE_KEY,
                    TensorInfo.newBuilder().setName(initOp.name() + ":0").build())
                .build());
      }

      // Make sure saved model directories exist
      Path variableDir = Paths.get(exportDir, "variables");
      variableDir.toFile().mkdirs();

      // Save the variables state
      session.save(variableDir.resolve("variables").toString());

      // Save the graph
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
    private String[] tags = {DEFAULT_TAG};
    private final MetaGraphDef.Builder metaGraphDefBuilder = MetaGraphDef.newBuilder();
    private Session session;
    private final Map<String, SessionFunction> functions = new LinkedHashMap<>();
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
    Loader loader = loader(exportDir);
    if (tags != null && tags.length > 0) {
      loader.withTags(tags);
    }
    return loader.load();
  }

  /**
   * Load a saved model.
   *
   * <p>Returns a <code>Loader</code> object that can set configuration options before actually
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
   * <p>Returns a <code>Exporter</code> object for setting configuration options before actually
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

  /** Return the signature of all functions available in this saved model. */
  public List<Signature> signatures() {
    // the init signatures aren't actual functions, just markers
    return functions.values().stream()
        .map(SessionFunction::signature)
        .filter(
            signature ->
                !signature.key().equals(INIT_OP_SIGNATURE_KEY)
                    && !signature.key().equals(JAVA_INIT_OP_SIGNATURE_KEY))
        .collect(Collectors.toList());
  }

  /**
   * Return a {@link ConcreteFunction} corresponding to the function signature.
   *
   * <pre>{@code
   * ConcreteFunction myFunction = savedModelBundle.function("mySignatureKey");
   * Map<String, Tensor> outputTensorMap = myFunction.call(session, inputTensorMap);
   * }</pre>
   *
   * <b>All functions use the bundle's underlying session.</b>
   *
   * @param signatureKey name of the {@code SignatureDef} in the saved model.
   * @return object that can be used to make calls to a function
   * @throws IllegalArgumentException if {@code signatureKey} is not found in this saved model.
   */
  public SessionFunction function(String signatureKey) {
    SessionFunction function = functions.get(signatureKey);
    if (function == null) {
      throw new IllegalArgumentException(
          String.format("Function with signature [%s] not found", signatureKey));
    }
    return function;
  }

  /**
   * Get all functions in the bundle.
   *
   * <p><b>All functions use the bundle's underlying session.</b>
   */
  public List<SessionFunction> functions() {
    return new ArrayList<>(functions.values());
  }

  /**
   * Invokes the default function directly from this model.
   *
   * <p>The default function selection is done based on the first of the following conditions that
   * is true:
   *
   * <ul>
   *   <li>The function is the only signature available attached to the main graph of this saved
   *       model
   *   <li>The function is mapped to the default signature name, which is "serving_default"
   * </ul>
   *
   * <p>Caller is responsible for closing all returned Tensors.
   *
   * <p><b>This uses the model's underlying session</b>
   *
   * @param arguments list of input tensors, mapped by their signature name
   * @return list of output tensors, mapped by the signature name
   * @throws IllegalArgumentException if no function can be selected by default
   */
  public Result call(Map<String, Tensor> arguments) {
    SessionFunction function = null;
    if (functions.size() == 1) {
      function = functions.values().iterator().next();
    } else {
      function = functions.get(Signature.DEFAULT_KEY);
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
  private final Map<String, SessionFunction> functions;

  private SavedModelBundle(
      Graph graph, Session session, MetaGraphDef metaGraphDef, Map<String, Signature> signatures) {
    this.graph = graph;
    this.session = session;
    this.metaGraphDef = metaGraphDef;
    this.functions =
        signatures.entrySet().stream()
            .collect(
                Collectors.toMap(Entry::getKey, e -> new SessionFunction(e.getValue(), session)));
  }

  private static GraphOperation findInitOp(
      Graph graph, Map<String, Signature> signatures, Map<String, CollectionDef> collections) {

    Signature initSig = signatures.get(INIT_OP_SIGNATURE_KEY);
    if (initSig != null) {
      return (GraphOperation)
          graph.outputOrThrow(initSig.getOutputs().get(INIT_OP_SIGNATURE_KEY).name).op();
    }

    CollectionDef initCollection;
    if (collections.containsKey(MAIN_OP_COLLECTION_KEY)) {
      initCollection = collections.get(MAIN_OP_COLLECTION_KEY);
    } else {
      initCollection = collections.get(LEGACY_INIT_OP_COLLECTION_KEY);
    }

    if (initCollection != null) {
      NodeList nodes = initCollection.getNodeList();
      if (nodes.getValueCount() != 1) {
        throw new IllegalArgumentException("Expected exactly one main op in saved model.");
      }
      return (GraphOperation) graph.outputOrThrow(nodes.getValue(0)).op();
    }
    return null;
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

    // Create a separate function for each signature of the main graph.
    // Note that the saved model will remain the owner of the graph and the session, meaning
    // that the functions do not need to be closed by the user and if it does, it should have
    // no effect.
    final Map<String, Signature> functions = new HashMap<>(metaGraphDef.getSignatureDefCount());

    metaGraphDef
        .getSignatureDefMap()
        .forEach(
            (signatureName, signatureDef) -> {
              if (!functions.containsKey(signatureName)) {
                Signature signature = new Signature(signatureName, signatureDef);
                functions.put(signatureName, signature);
              }
            });

    GraphOperation initOp = findInitOp(graph, functions, metaGraphDef.getCollectionDefMap());
    if (initOp != null) {
      graph.registerInitOp(initOp);
    }

    // java init ops are marked as ran, since the variable restore will restore any state
    // they mutated.
    // Technically, init ops should be ran first, then variable restore, but that is not possible
    // since TF_Session.loadSessionFromSavedModel does it in reverse order, so we just mark them as
    // ran.
    if (functions.containsKey(JAVA_INIT_OP_SIGNATURE_KEY)) {
      String initOpName =
          functions
              .get(JAVA_INIT_OP_SIGNATURE_KEY)
              .getOutputs()
              .get(JAVA_INIT_OP_SIGNATURE_KEY)
              .name;
      graph.registerInitOp(graph.outputOrThrow(initOpName).op());
    }

    session.setInitialized();

    if (metaGraphDef.containsCollectionDef(TABLE_INITIALIZERS_COLLECTION_KEY)) {
      metaGraphDef
          .getCollectionDefMap()
          .get(TABLE_INITIALIZERS_COLLECTION_KEY)
          .getNodeList()
          .getValueList()
          .forEach(
              node -> {
                graph.registerInitOp(graph.operationOrThrow(node));
              });
    }

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
      TF_Session session =
          TF_Session.loadSessionFromSavedModel(
              opts, runOpts, exportDir, tags, graph, metagraphDef, status);
      status.throwExceptionIfNotOK();

      // handle the result
      try {
        bundle =
            fromHandle(graph, session, MetaGraphDef.parseFrom(metagraphDef.dataAsByteBuffer()));
        // Only retain the references if the metagraphdef parses correctly,
        // otherwise allow the pointer scope to clean them up
        graph.retainReference();
        session.retainReference();
      } catch (InvalidProtocolBufferException e) {
        throw new TensorFlowException("Cannot parse MetaGraphDef protocol buffer", e);
      }
    }
    bundle.session.initialize();

    return bundle;
  }

  private static void validateTags(String[] tags) {
    if (tags == null || Arrays.stream(tags).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Invalid tags: " + Arrays.toString(tags));
    }
  }

  static {
    try {
      // Ensure that TensorFlow native library and classes are ready to be used
      Class.forName("org.tensorflow.TensorFlow");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
