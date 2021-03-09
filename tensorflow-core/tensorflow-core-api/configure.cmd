@echo off

bazel fetch @org_tensorflow//-

for /f %%i in ('bazel info output_base') do set VAR=%%i

CALL %VAR%\\external\\org_tensorflow\\configure.cmd

MOVE %VAR%\\external\\org_tensorflow\\.tf_configure.bazelrc .