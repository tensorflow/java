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
    "Finally, the `--api_dirs` argument takes a list of comma-separated "
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
  tensorflow::string api_dirs_str;
  std::vector<tensorflow::Flag> flag_list = {
      tensorflow::Flag(
          "api_dirs", &api_dirs_str,
          "List of directories that contain the ops API definitions protos")};
  tensorflow::string usage = tensorflow::java::kUsageHeader;
  usage += tensorflow::Flags::Usage(
      tensorflow::string(argv[0]) + " <ops library paths...>", flag_list);
  bool parsed_flags_ok = tensorflow::Flags::Parse(&argc, argv, flag_list);
  tensorflow::port::InitMain(usage.c_str(), &argc, &argv);
  QCHECK(parsed_flags_ok && argc > 1) << usage;
  std::vector<tensorflow::string> api_dirs = tensorflow::str_util::Split(
      api_dirs_str, ",", tensorflow::str_util::SkipEmpty());

  tensorflow::Env* env = tensorflow::Env::Default();
  void* ops_libs_handles[50];
  for (int i = 1; i < argc; ++i) {
    TF_CHECK_OK(env->LoadDynamicLibrary(argv[1], &ops_libs_handles[i - 1]));
  }
  tensorflow::OpList ops;
  tensorflow::OpRegistry::Global()->Export(false, &ops);
  TF_CHECK_OK(tensorflow::java::UpdateOpDefs(&ops, api_dirs, env));

  printf("Exporting %i ops", ops.op_size());

  std::ostream & out = std::cout;
  ops.SerializeToOstream(&out);

  return 0;
}
