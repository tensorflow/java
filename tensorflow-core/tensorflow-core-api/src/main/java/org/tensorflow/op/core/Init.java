package org.tensorflow.op.core;

import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.Op;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;

@Operator
public final class Init extends RawOp {

  public static final String DEFAULT_NAME = "init";

  /**
   * Factory method to create an operation executing all initializers of a graph.
   *
   * <p>All initializers added to a graph via
   * {@link org.tensorflow.op.core.Init#add(Scope, Op) tf.initAdd} are grouped together as a single
   * unit of computation in the graph. This operation must then be added to any graph using one or
   * more {@link Variable variables} and executed once before running the graph so the variable
   * states are initialized properly.</p>
   *
   * <p>When the graph is built by the same process that is running the session, the initializers
   * can be invoked by executing this single endpoint. For example:</p>
   * <pre>{@code
   * try (Graph g = new Graph()) {
   *   Variable<TInt32> x = tf.variable(tf.constant(10));  // initAdd is called implicitly
   *   Variable<TInt32> y = tf.variable(tf.constant(20));  // idem
   *   Add<TInt32> z = tf.math.add(x, y);
   *
   *   try (Session s = new Session(g)) {
   *     s.run(tf.init());  // initialize all variables
   *
   *     try (TInt32 t = (TInt32)s.runner().fetch(z).run().get(0)) {
   *       assertEquals(30, t.data().getInt());
   *     }
   *   }
   * }
   * }</pre>
   *
   * <p>When the graph is built by a separate process, the initializers can be invoked by running
   * the init op by its name, which defaults to {@link org.tensorflow.op.core.Init#DEFAULT_NAME}.
   * For example:</p>
   * <pre>{@code
   * // Building the model
   * try (Graph g = new Graph()) {
   *   Variable<TInt32> x = tf.variable(tf.constant(10));  // initAdd is called implicitly
   *   Variable<TInt32> y = tf.variable(tf.constant(20));  // idem
   *   Add<TInt32> z = tf.withName("z").math.add(x, y);
   *
   *   tf.init();  // add variables initializers to the graph, as Init.DEFAULT_NAME
   *   // ...exporting graph as a saved model...
   * }
   *
   * ...
   *
   * // Running the model
   * try (SavedModelBundle model = SavedModelBundle.load("/path/to/model", "train")) {
   *   model.session().run(Init.DEFAULT_NAME);
   *
   *   try (TInt32 t = (TInt32)s.runner().fetch("z").run().get(0)) {
   *     assertEquals(30, t.data().getInt());
   *   }
   * }
   * }</pre>
   *
   * @param scope current scope
   * @return an op grouping all initializers added to the graph
   * @throws IllegalArgumentException if the execution environment in scope is not a graph
   */
  @Endpoint(name = "init")
  public static Init create(Scope scope) {
    ExecutionEnvironment exEnv = scope.env();
    if (!(exEnv instanceof Graph)) {
      throw new IllegalArgumentException("init is only supported on Graph sessions.");
    }
    Graph graph = (Graph)exEnv;
    OperationBuilder opBuilder = scope.env().opBuilder("NoOp", scope.makeOpName(DEFAULT_NAME));
    scope.withControlDependencies(graph.initializers()).applyControlDependencies(opBuilder);
    return new Init(opBuilder.build());
  }

  /**
   * Register an op as an initializer of the graph.
   *
   * <p>Registered initializers are then grouped as a single unit of computation by adding
   * and executing an {@link org.tensorflow.op.core.Init#create(Scope) init} operation from a graph
   * session.
   *
   * @param scope
   * @param initializer
   * @throws IllegalArgumentException if the execution environment in scope is not a graph
   * @see org.tensorflow.op.core.Init#create(Scope) init
   */
  @Endpoint(name = "initAdd")
  public static void add(Scope scope, Op initializer) {
    ExecutionEnvironment exEnv = scope.env();
    if (!(exEnv instanceof Graph)) {
      throw new IllegalArgumentException("initAdd is only supported on Graph sessions.");
    }
    Graph graph = (Graph) exEnv;
    graph.addInitializer(initializer);
  }

  private Init(Operation operation) {
    super(operation);
  }
}
