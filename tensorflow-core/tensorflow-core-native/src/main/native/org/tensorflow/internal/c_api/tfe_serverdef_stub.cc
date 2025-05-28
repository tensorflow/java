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

#ifdef _WIN32

#include "tensorflow/c/c_api.h"
#include "tensorflow/c/c_api_experimental.h"

// On Windows, the TensorFlow C library currently does not export TFE_GetServerDef.
// Provide a local stub so that the JNI native library can link successfully.
// The stub simply sets the status to TF_UNIMPLEMENTED and returns an empty
// TF_Buffer.
extern "C" TF_Buffer* TFE_GetServerDef(const char* text_proto, TF_Status* status) {
    if (status != nullptr) {
        TF_SetStatus(status, TF_UNIMPLEMENTED,
                     "TFE_GetServerDef is not available in the Windows build of libtensorflow.");
    }
    // Allocate an empty TF_Buffer on the heap so that callers can still call
    // TF_DeleteBuffer() on the returned pointer safely.
    TF_Buffer* buf = static_cast<TF_Buffer*>(malloc(sizeof(TF_Buffer)));
    if (buf != nullptr) {
        buf->data = nullptr;
        buf->length = 0;
        buf->data_deallocator = nullptr;
    }
    return buf;
}

#endif // _WIN32