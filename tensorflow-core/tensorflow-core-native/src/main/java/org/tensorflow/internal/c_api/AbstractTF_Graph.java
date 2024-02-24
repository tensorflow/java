/*
Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.internal.c_api;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteGraph;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewGraph;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Graph extends Pointer {
  protected static class DeleteDeallocator extends TF_Graph implements Pointer.Deallocator {
    DeleteDeallocator(TF_Graph s) {
      super(s);
    }

    @Override
    public void deallocate() {
      if (!isNull()) TF_DeleteGraph(this);
      setNull();
    }
  }

  public AbstractTF_Graph(Pointer p) {
    super(p);
  }

  /**
   * Calls TF_NewGraph(), and registers a deallocator.
   *
   * <p>Note {@link org.tensorflow.Graph} will call TF_DeleteGraph on close, so do not use this
   * method when constructing a reference for use inside a {@code Graph} object.
   *
   * @return TF_Graph created. Do not call TF_DeleteGraph() on it.
   */
  public static TF_Graph newGraph() {
    TF_Graph g = TF_NewGraph();
    if (g != null) {
      g.deallocator(new DeleteDeallocator(g));
    }
    return g;
  }

  /** Calls the deallocator, if registered, otherwise has no effect. */
  public void delete() {
    deallocate();
  }
}
