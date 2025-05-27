/* Copyright 2024 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

#ifndef _WIN32

#include "tensorflow/c/c_api_internal.h"

using namespace tensorflow;

TFJ_GraphId TFJ_GetGraphId(const TF_Graph* g) {
    return static_cast<void*>(const_cast<Graph*>(&g->graph));
}

void TFJ_UnmapOperationName(TF_Graph* g, TF_Operation* operation) {
    g->name_map.erase(operation->node.name());
}

#else // #indef _WIN32

/* This extension is not available on Windows */

TFJ_GraphId TFJ_GetGraphId(const TF_Graph* g) { return NULL; }
void TFJ_UnmapOperationName(TF_Graph* g, TF_Operation* operation) {}

// Provide stub for missing TFE_GetServerDef symbol on Windows.
#include "tensorflow/c/c_api.h"
#include "tensorflow/c/c_api_experimental.h"
extern "C" TF_Buffer* TFE_GetServerDef(const char* text_proto, TF_Status* status) {
    if (status != nullptr) {
        TF_SetStatus(status, TF_UNIMPLEMENTED,
                     "TFE_GetServerDef is not supported on Windows build of libtensorflow.");
    }
    TF_Buffer* buf = static_cast<TF_Buffer*>(malloc(sizeof(TF_Buffer)));
    if (buf != nullptr) {
        buf->data = nullptr;
        buf->length = 0;
        buf->data_deallocator = nullptr;
    }
    return buf;
}

#endif // #indef _WIN32
