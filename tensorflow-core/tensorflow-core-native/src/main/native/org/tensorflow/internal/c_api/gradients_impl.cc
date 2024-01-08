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

#include <stdio.h>
#include <stdlib.h>

#include "graph.h"
#include "tsl/platform/errors.h"
#include "tensorflow/c/c_api.h"
#include "tensorflow/c/tf_status.h"
#include "tensorflow/cc/framework/grad_op_registry.h"

namespace tensorflow {
    namespace java {
        using namespace tsl;
        using namespace std;

        unordered_map<string, TF_GradFuncAdapter> g_grad_func_adapters;

        template <typename T, typename U> T* struct_cast(U* ptr) {
            return static_cast<T*>(static_cast<void*>(ptr));
        }

        Status CustomGradFunc(const Scope& scope,
                              const Operation& op,
                              const vector<Output>& grad_inputs,
                              vector<Output>* grad_outputs)
        {
            auto found_adapter = g_grad_func_adapters.find(op.node()->name());
            if (found_adapter == g_grad_func_adapters.end()) {
                return errors::NotFound("No gradient adapter found for operation ", op.node()->name());
            }
            int num_inputs = grad_inputs.size();
            TF_Output* inputs = (TF_Output*)malloc(num_inputs * sizeof(TF_Output));
            for (int i = 0; i < num_inputs; ++i) {
                Output grad_input = grad_inputs[i];
                inputs[i].oper = struct_cast<TF_Operation>(grad_input.node());
                inputs[i].index = grad_input.index();
            }
            TF_Output* outputs = NULL;
            LOG(INFO) << "Calling custom gradient function for operation " << op.node()->name();
            int num_outputs = found_adapter->second(
                static_cast<TF_GraphId>(scope.graph()),
                struct_cast<TF_Scope>(const_cast<Scope*>(&scope)),
                struct_cast<TF_Operation>(op.node()),
                inputs,
                num_inputs,
                &outputs
            );
            for (int i = 0; i < num_outputs; ++i) {
                TF_Output output = outputs[i];
                grad_outputs->push_back(Output(struct_cast<Node>(output.oper), output.index));
            }
            free(inputs);
            free(outputs); // outputs are allocated from Java but must be freed here
            return OkStatus();
        }
    }
}

extern "C" {

using namespace tensorflow::ops;
using namespace tensorflow::java;

bool TF_HasGradient(const char* op_type) {
    GradFunc dummy;
    tsl::Status status = GradOpRegistry::Global()->Lookup(op_type, &dummy);
    return status.ok();
}

bool TF_RegisterCustomGradient(const char* op_type, TF_GradFuncAdapter grad_func_adapter) {
    if (TF_HasGradient(op_type)) {
        LOG(WARNING) << "Registering gradient function for operation " << op_type
                     << ", which has already an existing gradient function";
    } else {
        LOG(INFO) << "Registering gradient function for operation " << op_type;
    }
    bool registered = GradOpRegistry::Global()->Register(op_type, CustomGradFunc);
    if (registered) {
        g_grad_func_adapters.insert({op_type, grad_func_adapter});
    }
    return registered;
}

} /* end extern "C" */
