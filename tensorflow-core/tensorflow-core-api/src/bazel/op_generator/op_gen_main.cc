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

#include "tensorflow/core/framework/op.h"
#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/core/lib/strings/str_util.h"
#include "tensorflow/core/platform/env.h"
#include "tensorflow/core/platform/init_main.h"
#include "tensorflow/core/util/command_line_flags.h"

#include "op_generator.h"

namespace tensorflow {
namespace java {

const char kUsageHeader[] =
    "\n\nGenerator of operation wrappers in Java.\n\n"
    "This executable generates wrappers for all operations registered in the "
    "provided list of libraries. A wrapper exposes an intuitive and\n"
	"strongly-typed interface for building its underlying operation and linking "
	"it into a graph.\n\n"
    "The first argument is the location of the tensorflow binary built for TF-"
    "Java.\nFor example, `bazel-out/k8-opt/bin/external/org_tensorflow/tensorfl"
    "ow/libtensorflow_cc.so`.\n\n"
    "Operation wrappers are generated under the path specified by the "
    "'--output_dir' argument. This path can be absolute or relative to the\n"
    "current working directory and will be created if it does not exist.\n\n"
    "Note that the operations will not be available through the "
    "'org.tensorflow.op.Ops' API until the generated classes are compiled\n"
    "using an appropriate annotation processor.\n\n"
    "The '--base_package' overrides the default parent package under which "
    "the generated subpackage and classes are to be located.\n\n"
    "Finally, the `--api_dirs` argument takes a list of comma-separated "
    "directories of API definitions can be provided to override default\n"
    "values found in the ops definitions. Directories are ordered by priority "
    "(the last having precedence over the first).\nFor example, `bazel-tensorf"
    "low-core-api/external/org_tensorflow/tensorflow/core/api_def/base_api,src"
    "/bazel/api_def`\n\n";

}  // namespace java
}  // namespace tensorflow

int main(int argc, char* argv[]) {
  tensorflow::string output_dir;
  tensorflow::string base_package = "org.tensorflow.op";
  tensorflow::string api_dirs_str;
  tensorflow::string ops_libs_str;
  std::vector<tensorflow::Flag> flag_list = {
      tensorflow::Flag("output_dir", &output_dir,
                       "Root directory into which output files are generated"),
      tensorflow::Flag(
          "base_package", &base_package,
          "Package parent to the generated subpackage and classes"),
      tensorflow::Flag(
          "api_dirs", &api_dirs_str,
          "List of directories that contain the ops API definitions protos")};
  tensorflow::string usage = tensorflow::java::kUsageHeader;
  usage += tensorflow::Flags::Usage(
      tensorflow::string(argv[0]) + " <ops library paths...>", flag_list);
  bool parsed_flags_ok = tensorflow::Flags::Parse(&argc, argv, flag_list);
  tensorflow::port::InitMain(usage.c_str(), &argc, &argv);
  QCHECK(parsed_flags_ok && !output_dir.empty() && argc > 1) << usage;
  std::vector<tensorflow::string> api_dirs = tensorflow::str_util::Split(
      api_dirs_str, ",", tensorflow::str_util::SkipEmpty());

  tensorflow::Env* env = tensorflow::Env::Default();
  void* ops_libs_handles[50];
  for (int i = 1; i < argc; ++i) {
	  TF_CHECK_OK(env->LoadDynamicLibrary(argv[1], &ops_libs_handles[i - 1]));
  }
  tensorflow::java::OpGenerator generator(api_dirs);
  tensorflow::OpList ops;
  tensorflow::OpRegistry::Global()->Export(false, &ops);
  TF_CHECK_OK(generator.Run(ops, base_package, output_dir));
  return 0;
}
