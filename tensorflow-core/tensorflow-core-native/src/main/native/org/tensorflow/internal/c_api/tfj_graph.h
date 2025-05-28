/* Copyright 2025 The TensorFlow Authors. All Rights Reserved.

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

#ifndef TENSORFLOW_JAVA_GRAPH_H_
#define TENSORFLOW_JAVA_GRAPH_H_

#include "tensorflow/c/c_api.h"

/// Unique identifier of a TensorFlow graph instance
typedef void* TFJ_GraphId;

/// Returns the unique identifier of the graph `g`
TF_CAPI_EXPORT extern TFJ_GraphId TFJ_GetGraphId(const TF_Graph* g);

/// Remove an operation from the name map of the graph `g`, so that it cannot be reversely looked up by name.
/// This is particularly useful for preventing custom gradient operations to pollute the graph namespace.
TF_CAPI_EXPORT extern void TFJ_UnmapOperationName(TF_Graph* g, TF_Operation* operation);

#include "tfj_graph_impl.cc" // include CC file in its header to compile it with JavaCPP

#ifdef _WIN32
// Ensure the Windows-specific stub for TFE_GetServerDef is linked.
#include "tfe_serverdef_stub.cc"
#endif

#endif  // TENSORFLOW_JAVA_GRAPH_H_
