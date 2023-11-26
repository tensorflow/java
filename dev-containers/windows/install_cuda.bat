if "%GPU%" == "true" (
  echo Installing CUDA
  curl.exe -L https://developer.download.nvidia.com/compute/cuda/11.2.2/local_installers/cuda_11.2.2_461.33_win10.exe -o cuda.exe
  curl.exe -L https://developer.download.nvidia.com/compute/redist/cudnn/v8.1.1/cudnn-11.2-windows-x64-v8.1.1.33.zip -o cudnn.zip
  cuda.exe -s
  mkdir cuda
  unzip.exe cudnn.zip
  cp.exe -a cuda/include cuda/lib cuda/bin "C:/Program Files/NVIDIA GPU Computing Toolkit/CUDA/v11.2/"
)