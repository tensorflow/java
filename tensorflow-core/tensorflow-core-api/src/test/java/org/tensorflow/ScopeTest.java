/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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

import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;

public class ScopeTest {
  @Test
  public void testSeparateOps(){
    try(Graph g = new Graph()){
      Ops tf1 = Ops.create(g);
      Ops tf2 = Ops.create(g);

      tf1.constant(2);
      tf1.withName("Constant2").constant(2);
      tf1.withSubScope("Scope").constant(2);
      tf1.withSubScope("Scope").withName("Constant4").constant(2);

      tf2.constant(2);
      tf2.withName("Constant2").constant(2);
      tf2.withSubScope("Scope").constant(2);
      tf2.withSubScope("Scope").withName("Constant4").constant(2);

    }
  }
}
