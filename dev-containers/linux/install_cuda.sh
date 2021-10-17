#!/bin/bash
#
# /* Copyright 2021 The TensorFlow Authors. All Rights Reserved.
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
# =======================================================================
# */
#





echo "JAVA home is $(dirname $(dirname $(readlink $(readlink $(which javac)))))"

if [ "$GPU" = "true" ]; then
  echo Installing CUDA
  curl -L https://developer.download.nvidia.com/compute/cuda/11.2.2/local_installers/cuda-repo-rhel7-11-2-local-11.2.2_460.32.03-1.x86_64.rpm -o $HOME/cuda.rpm
  curl -L https://developer.download.nvidia.com/compute/redist/cudnn/v8.1.1/cudnn-11.2-linux-x64-v8.1.1.33.tgz -o $HOME/cudnn.tgz
  curl -L https://developer.download.nvidia.com/compute/redist/nccl/v2.8/nccl_2.8.4-1+cuda11.2_x86_64.txz -o $HOME/nccl.txz
  rpm -i $HOME/cuda.rpm
  pushd /var/cuda-repo-rhel7-11-2-local/; rpm -i --nodeps cuda*.rpm libc*.rpm libn*.rpm; rm *.rpm; popd
  ln -sf /usr/local/cuda/lib64/stubs/libcuda.so /usr/local/cuda/lib64/libcuda.so
  ln -sf /usr/local/cuda/lib64/stubs/libnvidia-ml.so /usr/local/cuda/lib64/libnvidia-ml.so
  tar hxvf $HOME/cudnn.tgz -C /usr/local/
  tar hxvf $HOME/nccl.txz --strip-components=1 -C /usr/local/cuda/
  mv /usr/local/cuda/lib/* /usr/local/cuda/lib64/
  echo Removing downloaded archives and unused libraries to avoid running out of disk space
  rm -f $HOME/*.rpm $HOME/*.tgz $HOME/*.txz $HOME/*.tar.*
  rm -f $(find /usr/local/cuda/ -name '*.a' -and -not -name libcudart_static.a -and -not -name libcudadevrt.a)
  rm -rf /usr/local/cuda/doc* /usr/local/cuda/libnvvp* /usr/local/cuda/nsight* /usr/local/cuda/samples*
else
  echo "Skipping CUDA install"
fi