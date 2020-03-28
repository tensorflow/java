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
   * <p>All initializers added to a graph via {@link #add(Scope, Op) tf.initAdd} are grouped
   * together as a single unit of computation in the graph.
   *
   * <p>This op must be added to all graphs using one or more {@link Variable variables} and must be
   * run prior to training using {@link org.tensorflow.Session#runInit() Session.runInit()} in order
   * to initialize the variables state.</p>
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
   * <p>The op will be registered to be invoked when the {@link #create(Scope) init} op is executed
   * by a graph session prior to training using {@link org.tensorflow.Session#runInit() Session.runInit()}
   *
   * @param scope
   * @param initializer
   * @throws IllegalArgumentException if the execution environment in scope is not a graph
   * @see #create(Scope)
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
