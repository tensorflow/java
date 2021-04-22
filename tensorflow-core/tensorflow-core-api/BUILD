load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_copts", "tf_cc_binary")
load("@rules_java//java:defs.bzl", "java_proto_library")

tf_cc_binary(
    name = "java_op_exporter",
    linkopts = select({
        "@org_tensorflow//tensorflow:windows": [],
        "//conditions:default": ["-lm"],
    }),
    deps = [
        ":java_op_export_lib",
    ],
)

cc_library(
    name = "java_op_export_lib",
    srcs = [
        "src/bazel/op_generator/op_export_main.cc",
    ],
    hdrs = [
    ],
    copts = tf_copts(),
    deps = [
        "@org_tensorflow//tensorflow/core:framework",
        "@org_tensorflow//tensorflow/core:lib",
        "@org_tensorflow//tensorflow/core:op_gen_lib",
        "@org_tensorflow//tensorflow/core:protos_all_cc",
    ],
)

filegroup(
    name = "java_api_def",
    srcs = glob(["src/bazel/api_def/*"])
)

tf_cc_binary(
    name = "java_api_import",
    srcs = [
        "src/bazel/api_def/import/api_import.cc",
    ],
    linkopts = select({
        "@org_tensorflow//tensorflow:windows": [],
        "//conditions:default": ["-lm"],
    }),
    deps = [
        "@org_tensorflow//tensorflow/core:op_gen_lib",
        "@org_tensorflow//tensorflow/tools/api/lib:api_objects_proto_cc",
    ],
)

java_proto_library(
    name = "java_proto_gen_sources",
    deps = ["@org_tensorflow//tensorflow/core:protos_all"]
)

filegroup(
    name = "custom_ops_test",
    srcs = select({
        # FIXME(karllessard) Disable custom ops test on Windows since TF is still monolithic on this platform
        "@org_tensorflow//tensorflow:windows": [],
        "//conditions:default": [":libcustom_ops_test.so"],
    })
)

tf_cc_binary(
	name = "libcustom_ops_test.so",
	srcs = ["src/bazel/test/my_test_op.cc"],
	linkshared = 1,
	linkopts = ["-lm"],
	deps = [
		"@org_tensorflow//tensorflow/core:framework",
	]
)
