load("@org_tensorflow//tensorflow/core/platform:build_config_root.bzl", "if_static")
load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "VERSION_MAJOR")

def tfjava_cc_binary(name, srcs, deps = [], **kwargs):
    tf_cc_binary(
        name = name,
        srcs = srcs + if_static(
            extra_deps = [],
            macos = [
                clean_dep("//tensorflow:libtensorflow_cc.%s.dylib" % VERSION_MAJOR),
            ],
            otherwise = [
                clean_dep("//tensorflow:libtensorflow_cc.so.%s" % VERSION_MAJOR),
            ],
        ),
        deps = deps + if_static(
            extra_deps = [],
            otherwise = [
                ":libtensorflow_cc_import_lib"
            ],
        ),
    )
