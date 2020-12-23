/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Container class for core methods which add or perform several operations
 * and return one of them.
 */
@Operator
public abstract class Helpers {

    /**
     * This class contains static factories.
     */
    private Helpers() {}

    /**
     * Factory method to create a new Variable with it's initializer.
     * <p>
     * Only supported on Graph sessions as the {@link org.tensorflow.op.core.Assign} op
     * does not work in an EagerSession.
     * @param scope current scope
     * @param init The op to use to initialise this variable.
     * @param options carries optional attributes values
     * @return a new instance of Variable
     */
    @Endpoint(name = "variable")
    public static <T extends TType> Variable<T> createVariableWithInit(Scope scope, Operand<T> init, Variable.Options... options) {
        Variable<T> newVar = Variable.create(scope, init.shape(), init.type(), options);
        Assign<T> assignOp = Assign.create(scope, newVar, init);
        Init.add(scope, assignOp);
        return newVar;
    }
}
