load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_cc_binary", "clean_dep", "VERSION")

def tfjava_cc_binary(name, srcs, deps):
    tf_cc_binary(
        name = name,
        srcs = srcs,
        linkopts = select({
            clean_dep("//tensorflow:macos"): [
                "-Wl,-rpath,@loaderpath/external/org_tensorflow/tensorflow"
            ],
            clean_dep("//tensorflow:windows"): [],
            "//conditions:default": [
                "-Wl,-rpath,$$ORIGIN/external/org_tensorflow/tensorflow"
            ],
        }),
        dynamic_deps = select({
            clean_dep("//tensorflow:macos"): [
                clean_dep("//tensorflow:libtensorflow_cc.%s.dylib" % VERSION),
            ],
            clean_dep("//tensorflow:windows"): [
                clean_dep("//tensorflow:tensorflow.dll"),
            ],
            "//conditions:default": [
                clean_dep("//tensorflow:libtensorflow_cc.so.%s" % VERSION),
            ],
        }),
        deps = deps
    )
