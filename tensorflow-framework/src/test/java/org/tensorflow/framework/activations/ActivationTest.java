/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.activations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ActivationTest {

  /** Test of Activation create method */
  @Test
  public void testCreateActivation() {
    assertTrue(Activation.create("elu") instanceof ELU);
    assertTrue(Activation.create("exponential") instanceof Exponential);
    assertTrue(Activation.create("gelu") instanceof GELU);
    assertTrue(Activation.create("hard_sigmoid") instanceof HardSigmoid);
    assertTrue(Activation.create("linear") instanceof Linear);
    assertTrue(Activation.create("relu") instanceof ReLU);
    assertTrue(Activation.create("selu") instanceof SELU);
    assertTrue(Activation.create("sigmoid") instanceof Sigmoid);
    assertTrue(Activation.create("softmax") instanceof Softmax);
    assertTrue(Activation.create("softplus") instanceof Softplus);
    assertTrue(Activation.create("softsign") instanceof Softsign);
    assertTrue(Activation.create("swish") instanceof Swish);
    assertTrue(Activation.create("tanh") instanceof Tanh);
  }

  /** Test of Activation create method */
  @Test
  public void testCreateActivationConfig() {

    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "elu"))
            instanceof ELU);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "exponential"))
            instanceof Exponential);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "gelu"))
            instanceof GELU);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "hard_sigmoid"))
            instanceof HardSigmoid);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "linear"))
            instanceof Linear);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "relu"))
            instanceof ReLU);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "selu"))
            instanceof SELU);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "sigmoid"))
            instanceof Sigmoid);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "softmax"))
            instanceof Softmax);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "softplus"))
            instanceof Softplus);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "softsign"))
            instanceof Softsign);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "swish"))
            instanceof Swish);
    assertTrue(
        Activation.create(Collections.singletonMap(AbstractActivation.NAME_KEY, "tanh"))
            instanceof Tanh);
  }

  /** Test of Activation create method */
  @Test
  public void testCreateUnknownActivation() {
    assertThrows(IllegalArgumentException.class, () -> Activation.create("bogus"));
  }
}
