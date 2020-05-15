load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_copts", "tf_cc_binary")
load("@rules_java//java:defs.bzl", "java_proto_library")

tf_cc_binary(
    name = "java_op_generator",
    linkopts = select({
        "@org_tensorflow//tensorflow:windows": [],
        "//conditions:default": ["-lm"],
    }),
    deps = [
        ":java_op_gen_lib",
    ],
)

cc_library(
    name = "java_op_gen_lib",
    srcs = [
        "src/bazel/op_generator/op_gen_main.cc",
        "src/bazel/op_generator/op_generator.cc",
        "src/bazel/op_generator/op_specs.cc",
        "src/bazel/op_generator/source_writer.cc",
    ],
    hdrs = [
        "src/bazel/op_generator/java_defs.h",
        "src/bazel/op_generator/op_generator.h",
        "src/bazel/op_generator/op_specs.h",
        "src/bazel/op_generator/source_writer.h",
    ],
    copts = tf_copts(),
    deps = [
        "@org_tensorflow//tensorflow/core:framework",
        "@org_tensorflow//tensorflow/core:lib",
        "@org_tensorflow//tensorflow/core:op_gen_lib",
        "@org_tensorflow//tensorflow/core:protos_all_cc",
        "@com_googlesource_code_re2//:re2",
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

tf_cc_binary(
    name = "libcustom_op_test.so",
    srcs = ["src/bazel/test/my_test_op.cc"],
    linkshared = 1,
    linkopts = select({
        "@org_tensorflow//tensorflow:windows": [],
        "//conditions:default": ["-lm"],
    }),
    deps = [
        "@org_tensorflow//tensorflow/core:framework",
    ]
)
