# Lint as: python3
# Copyright 2020 The TensorFlow Authors. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ==============================================================================
"""Generate TensorFlow Lite Java reference docs for TensorFlow.org."""
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import pathlib
import shutil
import tempfile

from absl import app
from absl import flags

from tensorflow_docs.api_generator import gen_java

FLAGS = flags.FLAGS

# These flags are required by infrastructure, not all of them are used.
flags.DEFINE_string('output_dir', '/tmp/java_api/',
                    ("Use this branch as the root version and don't"
                     ' create in version directory'))

flags.DEFINE_string('site_path', 'java/api_docs/java',
                    'Path prefix in the _toc.yaml')

flags.DEFINE_string('code_url_prefix', None,
                    '[UNUSED] The url prefix for links to code.')

flags.DEFINE_bool(
    'search_hints', True,
    '[UNUSED] Include metadata search hints in the generated files')

# __file__ is the path to this file
TOOLS_DIR = pathlib.Path(__file__).resolve().parent
REPO_ROOT = TOOLS_DIR.parent

def overlay(from_root, to_root):
  for from_path in pathlib.Path(from_root).rglob('*'):
    relpath = from_path.relative_to(from_root)
    to_path = to_root/relpath
    if from_path.is_file():
      assert not to_path.exists()
      shutil.copyfile(from_path, to_path)
    else:
      to_path.mkdir(exist_ok=True)

def main(unused_argv):
  merged_source = pathlib.Path(tempfile.mkdtemp())
  (merged_source / 'java/org').mkdir(parents=True)

  shutil.copytree(REPO_ROOT/'tensorflow-core/tensorflow-core-api/src/main/java/org/tensorflow/',
                  merged_source/'java/org/tensorflow')
  overlay(REPO_ROOT/'tensorflow-core/tensorflow-core-api/src/gen/java/org/tensorflow',
            merged_source/'java/org/tensorflow')
  shutil.copytree(REPO_ROOT/'tensorflow-framework/src/main/java/org/tensorflow/framework',
                  merged_source/'java/org/tensorflow/framework')
  shutil.copytree(REPO_ROOT/'ndarray/src/main/java/org/tensorflow/ndarray',
                  merged_source/'java/org/tensorflow/ndarray')

  gen_java.gen_java_docs(
      package='org.tensorflow',
      source_path=merged_source / 'java',
      output_dir=pathlib.Path(FLAGS.output_dir),
      site_path=pathlib.Path(FLAGS.site_path))


if __name__ == '__main__':
  flags.mark_flags_as_required(['output_dir'])
  app.run(main)
