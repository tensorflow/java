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

#include <vector>

#include "tensorflow/c/c_api_internal.h"
#include "tensorflow/cc/framework/scope.h"
#include "tensorflow/cc/framework/ops.h"

extern "C" {

using namespace tensorflow;

struct TF_Scope {
    Scope s;
};

TF_Scope TF_NewRootScope() {
    Scope new_scope = Scope::NewRootScope();
    return {new_scope};
}

TF_Scope TF_NewSubScope(const TF_Scope* scope, const char* child_scope_name) {
    Scope new_scope = scope->s.NewSubScope(child_scope_name);
    return {new_scope};
}

TF_Scope TF_NewScopeWithControlDependencies(const TF_Scope* scope, TF_Operation* control_deps, int control_deps_size) {
    std::vector<Operation> ops;
    for (int i = 0; i < control_deps_size; ++i) {
        ops.push_back(Operation(&control_deps[i].node));
    }
    Scope new_scope = scope->s.WithControlDependencies(ops);
    return {new_scope};
}

TF_Scope TF_NewScopeWithDevice(const TF_Scope* scope, const char* device) {
    Scope new_scope = scope->s.WithDevice(device);
    return {new_scope};
}

std::string TF_GetUniqueNameForOp(const TF_Scope* scope, const char* default_name) {
    return scope->s.GetUniqueNameForOp(default_name);
}

} /* end extern "C" */
