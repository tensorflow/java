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

#ifndef TENSORFLOW_JAVA_GRADIENTS_H_
#define TENSORFLOW_JAVA_GRADIENTS_H_

#include "tfj_scope.h"
#include "tensorflow/c/c_api.h"

extern "C" {

/// Function to be implemented on the JVM side to be called back by the native library when it is time to attach gradient operations for the given op, graph and scope.
///
/// `grad_inputs` are the inputs available to the gradient operations. `grad_outputs` must received the address of an array of `TF_Output` allocated by the JVM, which
/// represents the outputs of the gradient operations to attach to the graph. It is important to guarantee that the JVM won't try to trigger the deallocation
/// of that pointer, since the native code will take care of that when it won't need the array anymore.
///
/// Returns the number of elements pointed by grad_outputs.
typedef int (*TFJ_GradFuncAdapter)(TFJ_GraphId graphId, TFJ_Scope* scope, TF_Operation* operation, TF_Output* grad_inputs, int grad_inputs_length, TF_Output** grad_outputs);

/// Returns true if a gradient function has already be registered for operations of type `op_type`
TF_CAPI_EXPORT extern bool TFJ_HasGradient(const char* op_type);

/// Registers a gradient function for operations of type `op_type`.
///
/// Returns true if the function has been registered successfully, false if operation failed or if gradient function is already registered to that `op_type`.
TF_CAPI_EXPORT extern bool TFJ_RegisterCustomGradient(const char* op_type, TFJ_GradFuncAdapter custom_gradient_adapter);

} /* end extern "C" */

#include "tfj_gradients_impl.cc" // include CC file in its header to compile it with JavaCPP

#endif  // TENSORFLOW_JAVA_GRADIENTS_H_
