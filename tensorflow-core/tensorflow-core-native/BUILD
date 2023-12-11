load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "if_windows")
load("@rules_java//java:defs.bzl", "java_proto_library")

java_proto_library(
    name = "java_proto_gen_sources",
    deps = [
        clean_dep("//tensorflow/core:protos_all"),
        "@local_tsl//tsl/protobuf:protos_all"
    ]
)

# cc_import(
#     name = "libtensorflow_import",
#     includes = [
#         "external/org_tensorflow",
#         "bazel-out/darwin_arm64-opt/bin/external/org_tensorflow",
#         "external/com_google_protobuf/src/",
#         "external/local_tsl"
#     ],
#     interface_library = if_windows(
#         "tensorflow.lib",
#         otherwise = None
#     ),
#     shared_library = select({
#         clean_dep("//tensorflow:macos"): "libtensorflow_cc.dylib",
#         clean_dep("//tensorflow:windows"): "tensorflow.dll",
#         "//conditions:default": "libtensorflow_cc.so"
#     })
# )
#
# cc_binary(
#     name = "java_api_import",
#     srcs = [
#         "src/tools/native/api_import.cc",
#     ],
#     deps = [
#         ":libtensorflow_import"
#     ]
# )
#
# filegroup(
#     name = "custom_ops_test",
#     srcs = select({
#         # FIXME(karllessard) Disable custom ops test on Windows since TF is still monolithic on this platform
#         clean_dep("//tensorflow:windows"): [],
#         "//conditions:default": [":libcustom_ops_test.so"],
#     })
# )
#
# tf_cc_binary(
# 	name = "libcustom_ops_test.so",
# 	srcs = ["src/test/native/my_test_op.cc"],
# 	linkshared = 1,
# 	linkopts = ["-lm"],
# 	deps = [
#         ":libtensorflow_import"
# 	]
# )
