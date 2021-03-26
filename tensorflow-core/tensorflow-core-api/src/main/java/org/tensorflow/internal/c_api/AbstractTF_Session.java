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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_CloseSession;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteSession;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_LoadSessionFromSavedModel;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewSession;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public abstract class AbstractTF_Session extends Pointer {
    protected static class DeleteDeallocator extends TF_Session implements Pointer.Deallocator {
        DeleteDeallocator(TF_Session s) { super(s); }
        @Override public void deallocate() {
            if (!isNull()) {
                try (PointerScope scope = new PointerScope()) {
                    TF_Status status = TF_Status.newStatus();
                    TF_CloseSession(this, status);
                    // Result of close is ignored, delete anyway.
                    TF_DeleteSession(this, status);
                    status.throwExceptionIfNotOK();
                    setNull();
                }
            }
        }
    }

    /** References to prevent deallocation. */
    protected TF_Graph graph;
    protected TF_SessionOptions opts;
    protected TF_Buffer run_options;
    protected TF_Buffer meta_graph_def;
    protected TF_Status status;

    public AbstractTF_Session(Pointer p) { super(p); }

    /**
     * Calls TF_NewSession(), and registers a deallocator.
     * @return TF_Session created. Do not call TF_DeleteSession() on it.
     */
    public static TF_Session newSession(TF_Graph graph, TF_SessionOptions opts, TF_Status status) {
        TF_Session s = TF_NewSession(graph, opts, status);
        if (s != null) {
            s.graph = graph;
            s.opts = opts;
            s.status = status;
            s.deallocator(new DeleteDeallocator(s));
        }
        return s;
    }

    /**
     * Calls TF_LoadSessionFromSavedModel(), and registers a deallocator.
     * @return TF_Session created. Do not call TF_DeleteSession() on it.
     */
    public static TF_Session loadSessionFromSavedModel(TF_SessionOptions session_options, TF_Buffer run_options,
        String export_dir, String[] tags, TF_Graph graph, TF_Buffer meta_graph_def, TF_Status status) {
        TF_Session s = TF_LoadSessionFromSavedModel(session_options, run_options,
                new BytePointer(export_dir), new PointerPointer(tags), tags.length, graph, meta_graph_def, status);
        if (s != null) {
            s.graph = graph;
            s.opts = session_options;
            s.run_options = run_options;
            s.meta_graph_def = meta_graph_def;
            s.status = status;
            s.deallocator(new DeleteDeallocator(s));
        }
        return s;
    }

    /**
     * Calls the deallocator, if registered, otherwise has no effect.
     */
    public void delete() {
        deallocate();
    }
}
