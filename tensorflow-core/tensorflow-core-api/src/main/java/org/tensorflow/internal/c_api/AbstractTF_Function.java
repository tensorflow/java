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
package org.tensorflow.internal.c_api;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteFunction;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Function extends Pointer {

  protected static class DeleteDeallocator extends TF_Function implements Deallocator {

    DeleteDeallocator(TF_Function s) {
      super(s);
    }

    @Override
    public void deallocate() {
      if (!isNull()) {
        TF_DeleteFunction(this);
      }
      setNull();
    }
  }

  public AbstractTF_Function(Pointer p) {
    super(p);
  }

  public TF_Function withDeallocator() {
    return this.deallocator(new DeleteDeallocator((TF_Function) this));
  }

  /** Calls the deallocator, if registered, otherwise has no effect. */
  public void delete() {
    deallocate();
  }
}
