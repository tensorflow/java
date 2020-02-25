package org.tensorflow.op.core;

import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Container class for core methods which add or perform several operations
 * and return one of them.
 */
@Operator
public abstract class CoreOps {

    /**
     * This class contains static factories.
     */
    private CoreOps() {}

    /**
     * Factory method to create a new Variable with it's initializer.
     *
     * @param scope current scope
     * @param init The op to use to initialise this variable.
     * @param options carries optional attributes values
     * @return a new instance of Variable
     */
    @Endpoint(name="variableWithInit")
    public static <T extends TType> Variable<T> createVariableWithInit(Scope scope, Operand<T> init, Variable.Options... options) {
        Output<T> initOutput = init.asOutput();
        Variable<T> newVar = Variable.create(scope,initOutput.shape(),initOutput.dataType(),options);
        Assign<T> assignOp = Assign.create(scope,newVar,init);
        ExecutionEnvironment exEnv = scope.env();
        if (exEnv instanceof Graph) {
            Graph graph = (Graph) exEnv;
            graph.addInitializer(assignOp);
        }

        return newVar;
    }
}
