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

#include "tensorflow/core/framework/op_gen_lib.h"
#include "tensorflow/core/framework/op.h"
#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/core/lib/io/path.h"
#include "tensorflow/core/lib/strings/str_util.h"
#include "tensorflow/core/platform/env.h"
#include "tensorflow/core/platform/file_system.h"
#include "tensorflow/core/platform/init_main.h"
#include "tensorflow/core/util/command_line_flags.h"
#include "tensorflow/tools/api/lib/api_objects.pb.h"

namespace tensorflow {
namespace java {

const char kUsageHeader[] =
    "\n\nImports API defs from an existing package to generate those for the "
    "Java client.\n\n";

string SnakeToCamelCase(const string& str, bool upper = false) {
  string result;
  bool cap = upper;
  for (string::const_iterator it = str.begin(); it != str.end(); ++it) {
    const char c = *it;
    if (c == '_') {
      cap = true;
    } else if (cap) {
      result += toupper(c);
      cap = false;
    } else {
      result += c;
    }
  }
  return result;
}

string CamelToSnakeCase(const string& str) {
  if (islower(str.at(0))) {
    return str; // consider the string as already in snake case
  }
  string result;
  bool prev_lower = false;
  for (string::const_iterator it = str.begin(); it != str.end(); ++it) {
    const char c = *it;
    if (isupper(c) || isdigit(c)) {
      if (it != str.begin() && (prev_lower || ((it + 1) != str.end() && islower(*(it + 1))))) {
        result += '_';
      }
      result += tolower(c);
      prev_lower = false;
    } else {
      result += c;
      prev_lower = true;
    }
  }
  //LOG(INFO) << str << " became " << result;
  return result;
}

void ImportApiDef(const ApiDef* input_api_def, const string& output_dir, Env* env) {
  ApiDefs output_api_defs = ApiDefs();
  ApiDef* output_api_def = output_api_defs.add_op();
  const std::string& graph_op_name = input_api_def->graph_op_name();
  output_api_def->set_graph_op_name(graph_op_name);
  //output_api_def->set_visibility(ApiDef::VISIBLE);
  // only output the first endpoint, ignore the others
  //for (const auto& input_endpoint : input_api_def->endpoint()) {
  const auto& input_endpoint = input_api_def->endpoint(0);
    if (!input_endpoint.deprecated()) {
      std::vector<std::string> name_tokens = str_util::Split(input_endpoint.name(), ".");
      if (name_tokens.size() > 1) {
        std::string package = str_util::Lowercase(SnakeToCamelCase(name_tokens.at(0), false));
        std::string name = SnakeToCamelCase(name_tokens.at(1), true);
        ApiDef_Endpoint* output_endpoint = output_api_def->add_endpoint();
        output_endpoint->set_name(package + "." + name);
      } else if (name_tokens.at(0) != graph_op_name){
        std::string name = SnakeToCamelCase(name_tokens.at(0), true);
        ApiDef_Endpoint* output_endpoint = output_api_def->add_endpoint();
        output_endpoint->set_name(name);
      }
    }
  //}
  std::unique_ptr<WritableFile> output_file;
  std::string output_file_name = "api_def_" + graph_op_name + ".pbtxt";
  TF_CHECK_OK(env->NewWritableFile(io::JoinPath(output_dir, output_file_name), &output_file));
  output_file->Append(output_api_defs.DebugString());
  output_file->Close();
}

void ImportApiDef(const string& name, string group, const string& output_dir, Env* env) {
  ApiDefs output_api_defs = ApiDefs();
  ApiDef* output_api_def = output_api_defs.add_op();
  const std::string& graph_op_name = name;
  output_api_def->set_graph_op_name(graph_op_name);
  //output_api_def->set_visibility(ApiDef::VISIBLE);
  if (!group.empty()) {
    std::string package = str_util::Lowercase(SnakeToCamelCase(group, false));
    ApiDef_Endpoint* output_endpoint = output_api_def->add_endpoint();
    output_endpoint->set_name(package + "." + name);
  } //else {
    //ApiDef_Endpoint* output_endpoint = output_api_def->add_endpoint();
    //output_endpoint->set_name(name);
  //}
  std::unique_ptr<WritableFile> output_file;
  std::string output_file_name = "api_def_" + graph_op_name + ".pbtxt";
  TF_CHECK_OK(env->NewWritableFile(io::JoinPath(output_dir, output_file_name), &output_file));
  output_file->Append(output_api_defs.DebugString());
  output_file->Close();
}

}  // namespace java
}  // namespace tensorflow

using namespace std;
using namespace tensorflow;

int main(int argc, char* argv[]) {
  string java_api_dir = "";
  string tf_src_dir = "";
  string tf_lib_path = "";
  std::vector<Flag> flag_list = {
      Flag(
          "java_api_dir", &java_api_dir,
          "Root directory where generated Java API definitions are exported"),
      Flag(
          "tf_src_dir", &tf_src_dir,
          "Root directory of TensorFlow sources"),
      Flag(
          "tf_lib_path", &tf_lib_path,
          "Path to TensorFlow shared library")};
  string usage = java::kUsageHeader;
  usage += Flags::Usage(argv[0], flag_list);
  bool parsed_flags_ok = Flags::Parse(&argc, argv, flag_list);
  port::InitMain(usage.c_str(), &argc, &argv);
  QCHECK(parsed_flags_ok && !java_api_dir.empty()
		  && !tf_src_dir.empty() && !tf_lib_path.empty()) << usage;

  Env* env = Env::Default();
  void* tf_lib_handle;
  TF_CHECK_OK(env->LoadDynamicLibrary(tf_lib_path.c_str(), &tf_lib_handle));  // This registers all TF ops
  OpList op_defs;
  OpRegistry::Global()->Export(false, &op_defs);
  ApiDefMap python_api_map(op_defs);

  // Load Python API defs
  string base_api_dir = tf_src_dir + "/tensorflow/core/api_def/base_api";
  string python_api_dir = tf_src_dir + "/tensorflow/core/api_def/python_api";
  vector<string> api_files;
  TF_CHECK_OK(env->GetChildren(base_api_dir, &api_files));
  LOG(INFO) << "Loading " << api_files.size() << " Base API definition files";
  for (const auto& filename : api_files) {
      TF_CHECK_OK(python_api_map.LoadFile(env, base_api_dir + "/" + filename)) << filename;
  }
  TF_CHECK_OK(env->GetChildren(python_api_dir, &api_files));
  LOG(INFO) << "Loading " << api_files.size() << " Python API definition files";
  for (const auto& filename : api_files) {
      TF_CHECK_OK(python_api_map.LoadFile(env, python_api_dir + "/" + filename)) << filename;
  }
  python_api_map.UpdateDocs();

  // Load golden API member names with their module path
  string golden_api_dir = tf_src_dir + "/tensorflow/tools/api/golden/v1";
  vector<pair<string, string>> golden_api_names;
  vector<string> golden_api_files;
  TF_CHECK_OK(env->GetChildren(golden_api_dir, &golden_api_files));
  LOG(INFO) << "Loading " << golden_api_files.size() << " Python API golden files";
  for (const auto& filename : golden_api_files) {
    // Skip the raw_ops API, as it contains all op endpoints
    if (filename == "tensorflow.raw_ops.pbtxt") {
      continue;
    }
    string contents;
    TF_CHECK_OK(ReadFileToString(env, golden_api_dir + "/" + filename, &contents));
    third_party::tensorflow::tools::api::TFAPIObject object;
    google::protobuf::TextFormat::ParseFromString(contents, &object);
    if (object.has_tf_module()) {
      string group = object.path();
      if (group == "tensorflow") {
        group = "";
      } else {
        StringPiece g = group;
        if (str_util::ConsumePrefix(&g, "tensorflow.")) {
          group = string(g.data());
        }
      }
      for (const auto& member : object.tf_module().member()) {
        golden_api_names.push_back(make_pair(member.name(), group));
      }
      for (const auto& member_method : object.tf_module().member_method()) {
        golden_api_names.push_back(make_pair(member_method.name(), group));
      }
    }
  }

  // Go through the whole list of registered ops and generate a Java API definition for those that
  // are missing
  int unresolved_count = 0;
  for (const auto& op_def : op_defs.op()) {
    if (env->FileExists(java_api_dir + "/api_def_" + op_def.name() + ".pbtxt") == Status::OK()) {
      // LOG(INFO) << "Java API for " << op_def.name() << " already defined, skipping";
      continue;
    }
    // Try to find this ops as a visible endpoint in the Python API definitions first
    auto python_api_def = python_api_map.GetApiDef(op_def.name());
    if (python_api_def != nullptr && python_api_def->visibility() == ApiDef::VISIBLE) {
      cout << endl << "Found: Op " << op_def.name() << " is visible in python API as " << python_api_def->endpoint(0).name() << endl;
      java::ImportApiDef(python_api_def, java_api_dir, env);
    } else {
      vector<pair<string, string>> matches;
      vector<pair<string, string>> choices_left;
      for (const auto& it : golden_api_names) {
        if (it.first == op_def.name() ||
            java::CamelToSnakeCase(it.first) == java::CamelToSnakeCase(op_def.name())) {
          matches.push_back(it);
        } else {
          choices_left.push_back(it);
        }
      }
      if (matches.size() == 1) {
        cout << endl << "Found: Op " << op_def.name() << " matches a single endpoint in golden Python API as "
          << matches.at(0).second << endl;
        java::ImportApiDef(op_def.name(), matches.at(0).second, java_api_dir, env);
      } else {
        int perfect_match_count = matches.size();
        string sc_op_name = java::CamelToSnakeCase(op_def.name());
        vector<pair<string, string>> partial_choices_left;
        for (const auto& it : choices_left) {
          if (str_util::StrContains(it.first, op_def.name()) ||
              str_util::StrContains(op_def.name(), it.first) ||
              str_util::StrContains(it.first, sc_op_name) ||
              str_util::StrContains(sc_op_name, it.first)) {
            matches.push_back(it);
          } else {
            partial_choices_left.push_back(it);
          }
        }
        int complete_match_count = matches.size() - perfect_match_count;
        bool has_complete_matches = complete_match_count > 0;
        vector<string> op_name_words = str_util::Split(sc_op_name, "_");
        sort(op_name_words.begin(), op_name_words.end());
        for (const auto& it : partial_choices_left) {
          string sc_golden_name = isupper(it.first.at(0)) ? java::CamelToSnakeCase(it.first) : it.first;
          vector<string> golden_name_words = str_util::Split(sc_golden_name, "_");
          sort(golden_name_words.begin(), golden_name_words.end());
          vector<string> common_words;
          set_intersection(op_name_words.begin(), op_name_words.end(), golden_name_words.begin(), golden_name_words.end(), back_inserter(common_words));
          if (!common_words.empty()) {
            matches.push_back(it);
          }
        }
        bool has_partial_matches = (complete_match_count < matches.size());
        bool selected = false;
        if (!matches.empty()) {
          int choice = 0;
          cout << endl << "Pick up your choice:" << endl << endl << op_def.name() << " = " << endl;
          if (perfect_match_count > 0) {
            for (int i = 0; i < perfect_match_count; ++i) {
              cout << "    (" << (i+1) << ") " << matches[i].first << " [" << matches[i].second << "]" << endl;
            }
            cout << endl << "0 for " << ((has_complete_matches || has_partial_matches) ? "more...: " : "none: ");
            cin >> choice;
          }
          if (choice == 0 && complete_match_count > 0) {
            for (int i = perfect_match_count; i < complete_match_count; ++i) {
              cout << "    (" << (i+1) << ") " << matches[i].first << " [" << matches[i].second << "]" << endl;
            }
            cout << endl << "0 for " << (has_partial_matches ? "more...: " : "none: ");
            cin >> choice;
          }
          if (choice == 0 && has_partial_matches) {
            for (int i = complete_match_count; i < matches.size(); ++i) {
              cout << "    (" << (i+1) << ") " << matches[i].first << " [" << matches[i].second << "]" << endl;
            }
            cout << endl << "0 for none: ";
            cin >> choice;
          }
          if (choice > 0) {
            java::ImportApiDef(op_def.name(), matches[choice - 1].second, java_api_dir, env);
            selected = true;
          }
        }
        if (!selected) {
          cout << endl << "Pick up a custom group for " << op_def.name() << " (0 to skip): ";
          string group;
          cin >> group;
          if (group != "0") {
            if (group == "core") {
              group = "";
            }
            java::ImportApiDef(op_def.name(), group, java_api_dir, env);
            selected = true;
          }
        }
        if (!selected) {
          LOG(ERROR) << "Not found : " << op_def.name();
          ++unresolved_count;
        }
      }
    }
  }
  if (unresolved_count > 0) {
    LOG(ERROR) << "Unresolved count = " << unresolved_count;
  } else {
    LOG(INFO) << "All resolved!";
  }
  return 0;
}
