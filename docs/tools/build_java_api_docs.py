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
"""Generate TensorFlow Java reference docs for TensorFlow.org."""
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import pathlib
import shutil
import tempfile
import io
import requests
import zipfile
from git import Repo

from absl import app
from absl import flags

from tensorflow_docs.api_generator import gen_java

FLAGS = flags.FLAGS

NDARRAY_VERSION = 'v1.0.0'
JAVACPP_VERSION = '1.5.11'
PROTOBUF_VERSION = 'v3.21.9'

# __file__ is the path to this file
TOOLS_DIR = pathlib.Path(__file__).resolve().parent
DOCS_DIR = TOOLS_DIR.parent
REPO_ROOT = DOCS_DIR.parent
DOC_OUTPUT_DIR = DOCS_DIR.joinpath("output")

SECTION_LABELS = {
  'org.tensorflow': 'Core',
  'org.tensorflow.ndarray': 'NdArray',
  'org.tensorflow.framework': 'Framework',
}

# These flags are required by infrastructure, not all of them are used.
flags.DEFINE_string('output_dir', f"{DOC_OUTPUT_DIR}",
                    ("Use this branch as the root version and don't"
                     ' create in version directory'))

flags.DEFINE_string('site_path', 'api_docs/',
                    'Path prefix in the _toc.yaml')

flags.DEFINE_string('code_url_prefix', None,
                    '[UNUSED] The url prefix for links to code.')

flags.DEFINE_bool(
    'search_hints', True,
    '[UNUSED] Include metadata search hints in the generated files')


def checkout_repo(repo_url: str, target_dir_name: str, version: str):
  local_repo_path = f"{REPO_ROOT}/{target_dir_name}"
  if not pathlib.Path(local_repo_path).exists():
    local_repo = Repo.clone_from(repo_url, local_repo_path)
  else:
    local_repo = Repo(local_repo_path)
  local_repo.remotes['origin'].fetch()
  local_repo.git.checkout(version)


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
  checkout_repo('https://github.com/tensorflow/java-ndarray', 'ndarray', NDARRAY_VERSION)
  checkout_repo('https://github.com/bytedeco/javacpp', 'javacpp', JAVACPP_VERSION)
  response = requests.get('https://repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.21.9/protobuf-java-3.21.9-sources.jar')
  with zipfile.ZipFile(io.BytesIO(response.content)) as z:
    z.extractall(f"{REPO_ROOT}/protobuf")
  response = requests.get('https://repo1.maven.org/maven2/org/osgi/osgi.annotation/8.1.0/osgi.annotation-8.1.0-sources.jar')
  with zipfile.ZipFile(io.BytesIO(response.content)) as z:
    z.extractall(f"{REPO_ROOT}/osgi")

  merged_source = pathlib.Path(tempfile.mkdtemp())
  (merged_source / 'java/org').mkdir(parents=True)
  shutil.copytree(REPO_ROOT/'tensorflow-core/tensorflow-core-api/src/main/java/org/tensorflow/', merged_source/'java/org/tensorflow')
  overlay(REPO_ROOT/'tensorflow-core/tensorflow-core-api/src/gen/java/org/tensorflow', merged_source/'java/org/tensorflow')
  overlay(REPO_ROOT/'tensorflow-core/tensorflow-core-api/src/gen/annotations/org/tensorflow', merged_source/'java/org/tensorflow')
  overlay(REPO_ROOT/'tensorflow-core/tensorflow-core-native/src/gen/java/org/tensorflow/', merged_source/'java/org/tensorflow/')
  overlay(REPO_ROOT/'tensorflow-core/tensorflow-core-native/src/main/java/org/tensorflow/', merged_source/'java/org/tensorflow/')
  shutil.copytree(REPO_ROOT/'tensorflow-framework/src/main/java/org/tensorflow/framework', merged_source/'java/org/tensorflow/framework')
  shutil.copytree(REPO_ROOT/'ndarray/ndarray/src/main/java/org/tensorflow/ndarray', merged_source/'java/org/tensorflow/ndarray')
  shutil.copytree(REPO_ROOT/'javacpp/src/main/java/org/bytedeco', merged_source/'java/org/bytedeco')
  shutil.copytree(REPO_ROOT/'protobuf/com/', merged_source/'java/com')
  shutil.copytree(REPO_ROOT/'osgi/org/osgi', merged_source/'java/org/osgi')

  gen_java.gen_java_docs(
      package='org.tensorflow',
      source_path=merged_source / 'java',
      output_dir=pathlib.Path(FLAGS.output_dir),
      site_path=pathlib.Path(FLAGS.site_path),
      section_labels=SECTION_LABELS,
      # Uncomment for local testing:
      script_path=pathlib.Path(TOOLS_DIR, 'run-javadoc-for-tf-local.sh'),
  )


if __name__ == '__main__':
  flags.mark_flags_as_required(['output_dir'])
  app.run(main)
