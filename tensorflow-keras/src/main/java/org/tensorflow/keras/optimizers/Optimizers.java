/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.keras.optimizers;

import org.tensorflow.framework.optimizers.Optimizer;
import org.tensorflow.op.Ops;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.function.Supplier;

/**
 * Functions to get an Optimizer based on String name, an Optimizer class, or lambda function.
 *
 * <p>Example:
 *
 * <pre>
 *     Adam instance = Optimizers.get(tf, "adam");
 *     Ftrl instance = Optimizers.get(tf, ltf -> new Ftrl(ltf, 0.1f);
 * </pre>
 */
public class Optimizers {

  static Map<String, Function<Ops, Optimizer>> map =
      new HashMap<String, Function<Ops, Optimizer>>() {
        {
          put("adadelta", AdaDelta::new);
          put("adagrad", AdaGrad::new);
          put("adagrad-da", AdaGradDA::new);
          put("adam", Adam::new);
          put("adamax", Adamax::new);
          put("ftrl", Ftrl::new);
          put("nadam", Nadam::new);
          put("rmsprop", RMSProp::new);
          put("sgd", SGD::new);
        }
      };

  /**
   * Get an Optimizer
   *
   * @param optimizerFunction either a String that identifies the Optimizer, an Optimizer class, or
   *     an Optimizer object.
   * @return the Optimizer object or null if not found.
   */
  public static Optimizer get(Ops tf, Object optimizerFunction) {
    return get(tf, optimizerFunction, null);
  }

  /**
   * Get an Optimizer
   *
   * @param func a lamda function that returns the Optimizer
   * @return the Intializer object
   */
  public static Optimizer get(Ops tf, Function<Ops, Optimizer> func) {
    return func.apply(tf);
  }

  /**
   * Get an Optimizer
   *
   * @param optimizerFunction either a String that identifies the Optimizer, an Optimizer class, or
   *     * an Optimizer object.
   * @param custom_functions a map of Optimizer lambdas that will be queried if the Optimizer is
   *     not found in the standard keys
   * @return the Optimizer object
   */
  public static Optimizer get(
      Ops tf, Object optimizerFunction, Map<String, Function<Ops, Optimizer>> custom_functions) {
    if (optimizerFunction != null) {
      if (optimizerFunction instanceof String) {
        String s =
            optimizerFunction
                .toString(); // do this for Java 8 rather than Pattern Matching for instanceof
        Function<Ops, Optimizer> function = map.get(s);
        if (function == null && custom_functions != null) {
          function = custom_functions.get(s);
        }
        return function != null ? function.apply(tf) : null;
      } else if (optimizerFunction instanceof Class) {
        // do this for Java 8 rather than Pattern Matching for instanceof
        Class<OptimizerInterface> c = (Class<OptimizerInterface>) optimizerFunction;
        try {
          Constructor<OptimizerInterface> ctor = c.getConstructor(Ops.class);
          return (Optimizer) ctor.newInstance(tf);
        } catch (NoSuchMethodException
            | InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException ex) {
          Logger.getLogger(Optimizers.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else if (optimizerFunction instanceof Optimizer) {
        return (Optimizer) optimizerFunction;
      } else if (optimizerFunction instanceof Function) {
        return ((Function<Ops, Optimizer>) optimizerFunction).apply(tf);
      } else if (optimizerFunction instanceof Supplier) {
        return ((Supplier<Optimizer>) optimizerFunction).get();
      }
    } else {
      return null;
    }

    throw new IllegalArgumentException(
        "optimizerFunction must be a symbolic name, Optimizer, Function<Ops, Optimizer>, Supplier<Optimizer> or a Class object");
  }
}
