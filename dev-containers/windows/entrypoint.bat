call "C:\Program Files (x86)\Microsoft Visual Studio\2019\Enterprise\VC\Auxiliary\Build\vcvarsall.bat" amd64
set "CUDA_PATH=%ProgramFiles%\NVIDIA GPU Computing Toolkit\CUDA\v11.2"
set "CUDA_PATH_V11_2=%ProgramFiles%\NVIDIA GPU Computing Toolkit\CUDA\v11.2"
set "PATH=C:\msys64\usr\bin;C:\bazel;C:\Program Files\Git\bin;%ProgramFiles%\NVIDIA GPU Computing Toolkit\CUDA\v11.2\bin;%ProgramFiles%\NVIDIA GPU Computing Toolkit\CUDA\v11.2\libnvvp;%PATH%"
echo Shorten work paths to prevent Bazel from reaching MAX_PATH limit
set "TEST_TMPDIR=C:\tmp"
set "TMPDIR=C:\tmp"
set "TEMP=C:\tmp"
set "TMP=C:\tmp"
mkdir C:\tmp
bash --version
@REM git --version
@REM cl
echo %JAVA_HOME%
call mvn -version
bazel version
call %*