/*
 Copyright 2026 The TensorFlow Authors. All Rights Reserved.

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Gradients;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.StatefulIf;
import org.tensorflow.op.core.StatefulPartitionedCall;
import org.tensorflow.op.core.StatelessIf;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

public class IfGradientTest {

  private static ConcreteFunction thenFn() {
    return ConcreteFunction.create(
        (Ops tf) -> {
          Placeholder<TFloat32> x = tf.placeholder(TFloat32.class);
          Operand<TFloat32> y = tf.math.mul(x, tf.constant(3.0f));
          return Signature.builder("thenBranch").input("x", x).output("y", y).build();
        });
  }

  private static ConcreteFunction elseFn() {
    return ConcreteFunction.create(
        (Ops tf) -> {
          Placeholder<TFloat32> x = tf.placeholder(TFloat32.class);
          Operand<TFloat32> y = tf.math.mul(x, tf.constant(5.0f));
          return Signature.builder("elseBranch").input("x", x).output("y", y).build();
        });
  }

  private static void assertClose(float got, float expected, float eps, String msg) {
    if (Math.abs(got - expected) > eps) {
      throw new AssertionError(msg + " (got=" + got + ", expected=" + expected + ")");
    }
  }

  private static void primeIfGradFunctions(Graph g) {

    Iterator<GraphOperation> operations = g.operations();
    while (operations.hasNext()) {
      GraphOperation op = operations.next();
      String type = op.type();
      if (!StatefulIf.OP_NAME.equals(type) && !StatelessIf.OP_NAME.equals(type)) continue;

      ConcreteFunction thenFwd = op.attributes().getAttrFunction("then_branch");
      ConcreteFunction elseFwd = op.attributes().getAttrFunction("else_branch");

      int nInputs = op.inputListLength("input");
      int nOut = op.numOutputs();

      List<Class<? extends TType>> tin = new ArrayList<>(nInputs);
      for (int i = 0; i < nInputs; i++) {
        Class<? extends TType> c = op.input(1 + i).asOutput().type();
        tin.add(c);
      }

      List<Class<? extends TType>> tout = new ArrayList<>(nOut);
      for (int i = 0; i < nOut; i++) {
        Class<? extends TType> c = op.output(i).type();
        tout.add(c);
      }

      ConcreteFunction thenGrad = buildBranchGradFn(op.name() + "/then_grad", thenFwd, tin, tout);
      ConcreteFunction elseGrad = buildBranchGradFn(op.name() + "/else_grad", elseFwd, tin, tout);

      g.attachFunction(thenGrad);
      g.attachFunction(elseGrad);
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private static ConcreteFunction buildBranchGradFn(
      String prefix,
      ConcreteFunction branchFn,
      List<Class<? extends TType>> tin,
      List<Class<? extends TType>> toutForward) {

    return ConcreteFunction.create(
        (Ops tf) -> {
          Signature.Builder sig = Signature.builder(prefix);

          List<Operand<?>> x = new ArrayList<>(tin.size());
          for (int i = 0; i < tin.size(); i++) {
            Placeholder<? extends TType> ph = tf.placeholder((Class) tin.get(i));
            x.add(ph);
            sig.input("x" + i, ph);
          }

          List<Operand<?>> dy = new ArrayList<>(toutForward.size());
          for (int i = 0; i < toutForward.size(); i++) {
            Placeholder<? extends TType> ph = tf.placeholder((Class) toutForward.get(i));
            dy.add(ph);
            sig.input("dy" + i, ph);
          }

          StatefulPartitionedCall yCall =
              StatefulPartitionedCall.create(tf.scope(), x, toutForward, branchFn);

          Operand<?> L = tf.constant(0.0f);
          for (int i = 0; i < toutForward.size(); i++) {
            Operand<?> prod = tf.math.mul((Operand) yCall.output().get(i), (Operand) dy.get(i));
            L = tf.math.add((Operand) L, (Operand) sumAll(tf, prod));
          }

          Gradients g = tf.gradients((Iterable) List.of((Operand) L), x);

          for (int i = 0; i < tin.size(); i++) {
            Operand<?> dx = g.dy(i);
            sig.output("dx" + i, dx);
          }

          return sig.build();
        });
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private static Operand<?> sumAll(Ops tf, Operand<?> v) {
    Operand<TInt32> r = tf.rank(v);
    Operand<TInt32> axes = tf.range(tf.constant(0), r, tf.constant(1));
    return tf.reduceSum((Operand) v, axes);
  }

  @Test
  public void testStatefullIfGradient() {
    TensorFlow.registerCustomGradient(
        StatefulIf.OP_NAME,
        (tf, op, gradOutputs) -> {
          OperationAttributeInspector attrs = op.attributes();
          ConcreteFunction thenBranch = attrs.getAttrFunction("then_branch");
          ConcreteFunction elseBranch = attrs.getAttrFunction("else_branch");

          if (thenBranch == null || elseBranch == null) {
            int n = 1 + op.inputListLength("input");
            List<Operand<?>> no = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
              no.add(null);
            }
            return no;
          }

          Operand<? extends TType> cond = op.input(0);
          int nInputs = op.inputListLength("input");
          List<Operand<?>> inputs = new ArrayList<>(nInputs);
          for (int i = 0; i < nInputs; i++) {
            inputs.add(op.input(1 + i));
          }

          int nOut = op.numOutputs();
          List<Class<? extends TType>> toutForward = new ArrayList<>(nOut);
          for (int i = 0; i < nOut; i++) {
            toutForward.add(op.output(i).type());
          }

          List<Class<? extends TType>> tin =
              inputs.stream().map(input -> input.asOutput().type()).collect(Collectors.toList());
          List<Operand<?>> dys = new ArrayList<>(nOut);
          for (int i = 0; i < nOut; i++) {
            Operand<?> dy = null;
            if (gradOutputs != null && i < gradOutputs.size()) {
              dy = gradOutputs.get(i);
            }
            if (dy == null) {
              dy =
                  gradOutputs == null || gradOutputs.isEmpty()
                      ? tf.onesLike((Operand) op.output(i))
                      : tf.zerosLike((Operand) op.output(i));
            }
            dys.add(dy);
          }

          List<Operand<?>> input = new ArrayList<>(nInputs + nOut);
          input.addAll(inputs);
          input.addAll(dys);

          final String thenPrefix = op.name() + "/then_grad"; // op has unique name
          final String elsePrefix = op.name() + "/else_grad";

          ConcreteFunction thenGrad = op.env().getFunctionCached(thenPrefix);
          ConcreteFunction elseGrad = op.env().getFunctionCached(elsePrefix);

          if (thenGrad == null || elseGrad == null) {
            throw new IllegalStateException("If grad functions not primed for op=" + op.name());
          }
          StatefulIf dInputsIf =
              StatefulIf.create(tf.scope(), cond, input, tin, thenGrad, elseGrad);
          List<Operand<?>> result = new ArrayList<>(1 + nInputs);
          result.add(null); // no gradient for condition
          result.addAll(dInputsIf.output());
          return result;
        });

    Graph g = new Graph();
    Ops tf = Ops.create(g);

    var x = tf.placeholder(TFloat32.class); // scalar
    var cond = tf.placeholder(TBool.class); // scalar

    try (ConcreteFunction thenBranch = thenFn();
        ConcreteFunction elseBranch = elseFn()) {

      StatefulIf ifOp =
          StatefulIf.create(
              tf.scope(),
              cond,
              List.of((Operand) x),
              List.of(TFloat32.class),
              thenBranch,
              elseBranch);

      var y = ifOp.output().get(0);

      primeIfGradFunctions(g);

      var dy_dx = g.addGradients(y, new Output[] {x.asOutput()})[0];

      try (Session session = new Session(g)) {

        try (Result r =
            session
                .runner()
                .feed(x, TFloat32.scalarOf(2.0f))
                .feed(cond, TBool.scalarOf(true))
                .fetch(y)
                .fetch(dy_dx)
                .run()) {

          float yVal = ((TFloat32) r.get(0)).getFloat();
          float gVal = ((TFloat32) r.get(1)).getFloat();

          assertClose(yVal, 6.0f, 1e-6f, "y mismatch for cond=true");
          assertClose(gVal, 3.0f, 1e-6f, "grad mismatch for cond=true");
        }

        // ---- cond=false
        try (Result r =
            session
                .runner()
                .feed(x, TFloat32.scalarOf(2.0f))
                .feed(cond, TBool.scalarOf(false))
                .fetch(y)
                .fetch(dy_dx)
                .run()) {

          float yVal = ((TFloat32) r.get(0)).getFloat();
          float gVal = ((TFloat32) r.get(1)).getFloat();
          assertClose(yVal, 10.0f, 1e-6f, "y mismatch for cond=false");
          assertClose(gVal, 5.0f, 1e-6f, "grad mismatch for cond=false");
        }
      }
      ;
    }
  }
  ;
}
