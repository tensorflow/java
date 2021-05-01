/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <stdio.h>

#include "tensorflow/core/framework/op.h"
#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/core/lib/strings/str_util.h"
#include "tensorflow/core/platform/env.h"
#include "tensorflow/core/platform/init_main.h"
#include "tensorflow/core/util/command_line_flags.h"
#include "tensorflow/core/framework/api_def.pb.h"
#include "tensorflow/core/framework/op_def.pb.h"
#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/core/lib/core/errors.h"
#include "tensorflow/core/lib/io/path.h"
#include "tensorflow/core/framework/op_gen_lib.h"
#include "google/protobuf/unknown_field_set.h"

namespace tensorflow {
namespace java {

const char kUsageHeader[] =
    "\n\nExporter of operation and API defs, for use in Java op generation.\n\n"
    "This executable exports the op def and api def protos for all operations "
    "registered in the provided list of libraries. The proto will be printed "
    "to stdout in binary format. It is an OpList proto, with each OpDef having"
    " the associated ApiDef attached as unknown field 100\n\n"
    "The first argument is the location of the tensorflow binary built for TF-"
    "Java.\nFor example, `bazel-out/k8-opt/bin/external/org_tensorflow/tensorfl"
    "ow/libtensorflow_cc.so`.\n\n"
    "The second and third arguments are the binary and text output files, respectively.\n"
    "The text output file will not include ApiDefs, like tensorflow's ops.pbtxt.\n\n"
    "Finally, the rest of the arguments are used as "
    "directories of API definitions can be provided to override default\n"
    "values found in the ops definitions. Directories are ordered by priority "
    "(the last having precedence over the first).\nFor example, `bazel-tensorf"
    "low-core-api/external/org_tensorflow/tensorflow/core/api_def/base_api,src"
    "/bazel/api_def`\n\n";

void Write(OpDef* op_def, const ApiDef& api_def){
  auto *refl = op_def->GetReflection();
  refl->MutableUnknownFields(op_def)->AddLengthDelimited(100, api_def.SerializeAsString());
}

Status UpdateOpDefs(OpList* op_list, const std::vector<tensorflow::string>& api_dirs_, Env* env_) {
  ApiDefMap api_map(*op_list);
  if (!api_dirs_.empty()) {
    // Only load api files that correspond to the requested "op_list"
    for (const auto& op : op_list->op()) {
      for (const auto& api_def_dir : api_dirs_) {
        const std::string api_def_file_pattern =
            io::JoinPath(api_def_dir, "api_def_" + op.name() + ".pbtxt");
        if (env_->FileExists(api_def_file_pattern).ok()) {
          TF_CHECK_OK(api_map.LoadFile(env_, api_def_file_pattern))
              << api_def_file_pattern;
        }
      }
    }
  }
  api_map.UpdateDocs();

  for (int i = 0 ; i < op_list->op_size() ; i++) {
    OpDef *op_def = op_list->mutable_op(i);
    const ApiDef* api_def = api_map.GetApiDef(op_def->name());
  	Write(op_def, *api_def);
  }
  return Status::OK();
}

}
}

// See usage header.
// Writes an OpList proto to stdout, with each OpDef having its ApiDef in field 100
int main(int argc, char* argv[]) {
  tensorflow::port::InitMain(tensorflow::java::kUsageHeader, &argc, &argv);
  std::vector<tensorflow::string> api_dirs;

  if(argc < 4) {
      std::cerr << "Must specify <library_path> <binary_output> <text_output>" << "\n";
      std::cerr << tensorflow::java::kUsageHeader;
      return 1;
  }
  
  for(int i = 4 ; i < argc ; i++){
    api_dirs.push_back(argv[i]);
  }

  std::ofstream binary_output (argv[2], std::ios::out | std::ios::trunc | std::ios::binary);
  std::ofstream text_output  (argv[3], std::ios::out | std::ios::trunc);

  if(!binary_output.is_open()){
    std::cerr << "Error opening file " << argv[2] << "\n";
    return 1;
  }

  if(!text_output.is_open()){
    std::cerr << "Error opening file " << argv[3] << "\n";
    return 1;
  }

  tensorflow::Env* env = tensorflow::Env::Default();
  void* ops_libs_handles[1];
  TF_CHECK_OK(env->LoadDynamicLibrary(argv[1], &ops_libs_handles[0]));
  tensorflow::OpList ops;
  tensorflow::OpRegistry::Global()->Export(false, &ops);

  text_output << ops.DebugString();
  text_output.close();

  TF_CHECK_OK(tensorflow::java::UpdateOpDefs(&ops, api_dirs, env));

  ops.SerializeToOstream(&binary_output);
  binary_output.close();

  return 0;
}
