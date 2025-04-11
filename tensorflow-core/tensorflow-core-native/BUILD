load("@rules_java//java:defs.bzl", "java_proto_library")

java_proto_library(
    name = "java_proto_gen_sources",
    deps = [
        "@org_tensorflow//tensorflow/core:protos_all",
        "@local_xla//xla/tsl/protobuf:bfc_memory_map_proto",
        "@local_xla//xla/tsl/protobuf:test_log_proto",
        "@local_tsl//tsl/protobuf:protos_all"
    ]
)
