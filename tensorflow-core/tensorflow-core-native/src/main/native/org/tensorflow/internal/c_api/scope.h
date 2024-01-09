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

#ifndef TENSORFLOW_JAVA_SCOPE_H_
#define TENSORFLOW_JAVA_SCOPE_H_

#include "tensorflow/c/c_api.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef struct TF_Scope TF_Scope;

// The following functions are for users making graphs. They return brand new
// scopes, or scopes derived from an existing scope object.

/// Return a new scope.
/// This creates a new graph and all operations constructed in this graph
/// should use the returned object as the "root" scope.
TF_CAPI_EXPORT extern TF_Scope TF_NewRootScope();

/// Return a new scope. Ops created with this scope will have
/// `name/child_scope_name` as the prefix. The actual name will be unique
/// in the current scope. All other properties are inherited from the current
/// scope. If `child_scope_name` is empty, the `/` is elided.
TF_CAPI_EXPORT extern TF_Scope TF_NewSubScope(const TF_Scope* scope, const char* child_scope_name);

/// Return a new scope. All ops created within the returned scope will have as
/// control dependencies the union of operations in the control_deps vector
/// and the control dependencies of the current scope.
TF_CAPI_EXPORT extern TF_Scope TF_NewScopeWithControlDependencies(const TF_Scope* scope, TF_Operation* control_deps, int control_deps_size);

/// Return a new scope. All ops created within the returned scope will have
/// the device field set to 'device'.
TF_CAPI_EXPORT extern TF_Scope TF_NewScopeWithDevice(const TF_Scope* scope, const char* device);

/// Return a unique name, using default_name if an op name has not been specified.
/// Note: returns C++ std string to prevent buffer to be freed up before consuming the characters
TF_CAPI_EXPORT extern std::string TF_GetUniqueNameForOp(const TF_Scope* scope, const char* default_name);

#ifdef __cplusplus
} /* end extern "C" */
#include "scope_impl.cc" // include CC file in its header to compile it with JavaCPP
#endif

#endif  // TENSORFLOW_JAVA_SCOPE_H_
