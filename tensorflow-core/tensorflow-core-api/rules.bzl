load("@org_tensorflow//tensorflow/core/platform:build_config_root.bzl", "if_static")
load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "VERSION", "VERSION_MAJOR")

def tfjava_cc_binary(name, srcs, deps = [], **kwargs):
    tf_cc_binary(
        name = name,
        srcs = srcs + if_static(
            extra_deps = [],
            macos = [
                clean_dep("//tensorflow:libtensorflow_cc.%s.dylib" % VERSION_MAJOR),
            ],
            otherwise = [
                clean_dep("//tensorflow:libtensorflow_cc.so%s" % VERSION_MAJOR),
            ],
        ),
#         linkopts = select({
#             clean_dep("//tensorflow:macos"): [
#                 "-Wl,-rpath,@loaderpath/external/org_tensorflow/tensorflow"
#             ],
#             clean_dep("//tensorflow:windows"): [],
#             "//conditions:default": ["-lm"] + [
#                 "-Wl,-rpath,$$ORIGIN/external/org_tensorflow/tensorflow"
#             ],
#         }),
#         dynamic_deps = select({
#             clean_dep("//tensorflow:macos"): [
#                 clean_dep("//tensorflow:libtensorflow_cc.%s.dylib" % VERSION),
#             ],
#             clean_dep("//tensorflow:windows"): [],
#             "//conditions:default": [
#                 clean_dep("//tensorflow:libtensorflow_cc.so.%s" % VERSION),
#             ],
#         }),
        deps = deps + if_static(
            extra_deps = [],
            otherwise = [
                ":libtensorflow_cc_import_lib"
            ],
        ),
    )
