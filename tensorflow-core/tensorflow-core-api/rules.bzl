load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "VERSION")

def tfjava_cc_binary(name, srcs, deps = [], **kwargs):
    tf_cc_binary(
        name = name,
        srcs = srcs + select({
            clean_dep("//tensorflow:windows"): [
                clean_dep("//tensorflow:tensorflow_cc_dll_import_lib"),
            ],
            "//conditions:default": [],
        }),
        linkopts = select({
            clean_dep("//tensorflow:macos"): [
                "-Wl,-rpath,@loaderpath/external/org_tensorflow/tensorflow"
            ],
            clean_dep("//tensorflow:windows"): [],
            "//conditions:default": ["-lm"] + [
                "-Wl,-rpath,$$ORIGIN/external/org_tensorflow/tensorflow"
            ],
        }),
        dynamic_deps = select({
            clean_dep("//tensorflow:macos"): [
                clean_dep("//tensorflow:libtensorflow_cc.%s.dylib" % VERSION),
            ],
            clean_dep("//tensorflow:windows"): [],
            "//conditions:default": [
                clean_dep("//tensorflow:libtensorflow_cc.so.%s" % VERSION),
            ],
        }),
        deps = deps,
        **kwargs
    )