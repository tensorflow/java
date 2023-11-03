load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "VERSION_MAJOR")
load("@rules_java//java:defs.bzl", "java_proto_library")
load("rules.bzl", "tfjava_cc_binary")

cc_import(
    name = "libtensorflow_cc_import_lib",
    shared_library = select({
        clean_dep("//tensorflow:macos"): "@org_tensorflow//tensorflow:libtensorflow_cc.dylib",
        "//conditions:default": "@org_tensorflow//tensorflow:libtensorflow_cc.so.%s" % VERSION_MAJOR,
    }),
)

tfjava_cc_binary(
    name = "java_op_exporter",
    srcs = [
        "src/bazel/op_generator/op_export_main.cc",
    ],
    deps = [
        clean_dep("//tensorflow/core:framework"),
        clean_dep("//tensorflow/core:lib"),
        clean_dep("//tensorflow/core:op_gen_lib"),
        clean_dep("//tensorflow/core:protos_all_cc"),
    ],
)

# cc_binary(
#    name = "java_op_exporter",
#    srcs = [
#        "src/bazel/op_generator/op_export_main.cc",
#    ],
#    includes = ["target/wheels/tensorflow/include"],
#    linkopts = [
#        "-L./target/wheels/tensorflow",
#        "-ltensorflow_cc"
#    ]
# )

filegroup(
    name = "java_api_def",
    srcs = glob(["src/bazel/api_def/*"])
)

tfjava_cc_binary(
    name = "java_api_import",
    srcs = [
        "src/bazel/api_def/import/api_import.cc",
    ],
    deps = [
        clean_dep("//tensorflow/core:op_gen_lib"),
        clean_dep("//tensorflow/tools/api/lib:api_objects_proto_cc"),
    ],
)

java_proto_library(
    name = "java_proto_gen_sources",
    deps = [
        clean_dep("//tensorflow/tsl/protobuf:protos_all"),
        clean_dep("//tensorflow/tsl/profiler/protobuf:protos_all"),
        clean_dep("//tensorflow/core:protos_all")
    ]
)

filegroup(
    name = "custom_ops_test",
    srcs = select({
        # FIXME(karllessard) Disable custom ops test on Windows since TF is still monolithic on this platform
        clean_dep("//tensorflow:windows"): [],
        "//conditions:default": [":libcustom_ops_test.so"],
    })
)

tf_cc_binary(
	name = "libcustom_ops_test.so",
	srcs = ["src/bazel/test/my_test_op.cc"],
	linkshared = 1,
	linkopts = ["-lm"],
	deps = [
		clean_dep("//tensorflow/core:framework"),
	]
)
