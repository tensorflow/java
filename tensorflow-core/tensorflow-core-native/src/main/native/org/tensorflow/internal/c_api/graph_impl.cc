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

#include "tensorflow/c/c_api_internal.h"

extern "C" {

using namespace tensorflow;

TF_GraphId TF_GetGraphId(const TF_Graph* g) {
    return static_cast<void*>(const_cast<Graph*>(&g->graph));
}

void TF_UnmapOperationName(TF_Graph* g, TF_Operation* operation) {
    g->name_map.erase(operation->node.name());
}

} /* end extern "C" */
