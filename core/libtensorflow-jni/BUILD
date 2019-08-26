load("@org_tensorflow//tensorflow:tensorflow.bzl", "tf_copts")

LINKER_VERSION_SCRIPT = ":config/version_script.lds"
LINKER_EXPORTED_SYMBOLS = ":config/exported_symbols.lds"

cc_binary(
    name = "libtensorflow_jni.so",
    # Set linker options to strip out anything except the JNI
    # symbols from the library. This reduces the size of the library
    # considerably (~50% as of January 2017).
    linkopts = select({
        "@org_tensorflow//tensorflow:debug": [],  # Disable all custom linker options in debug mode
        "//conditions:default": [
            "-z defs",
            "-s",
            "-Wl,--version-script,$(location {})".format(LINKER_VERSION_SCRIPT),
        ],
    }),
    linkshared = 1,
    linkstatic = 1,
    copts = tf_copts(),
    deps = [
        "@org_tensorflow//tensorflow/core/distributed_runtime/rpc:grpc_server_lib",
        "//src/main/native",
        LINKER_VERSION_SCRIPT,
        LINKER_EXPORTED_SYMBOLS,
    ],
)

cc_binary(
    name = "libtensorflow_jni.dylib",
    # Set linker options to strip out anything except the JNI
    # symbols from the library. This reduces the size of the library
    # considerably (~50% as of January 2017).
    linkopts = select({
        "@org_tensorflow//tensorflow:debug": [],  # Disable all custom linker options in debug mode
        "//conditions:default": [
            "-Wl,-exported_symbols_list,$(location {})".format(LINKER_EXPORTED_SYMBOLS),
        ],
    }),
    linkshared = 1,
    linkstatic = 1,
    copts = tf_copts(),
    deps = [
        "@org_tensorflow//tensorflow/core/distributed_runtime/rpc:grpc_server_lib",
        "//src/main/native",
        LINKER_VERSION_SCRIPT,
        LINKER_EXPORTED_SYMBOLS,
    ],
)

cc_binary(
    name = "tensorflow_jni.dll",
    # Set linker options to strip out anything except the JNI
    # symbols from the library. This reduces the size of the library
    # considerably (~50% as of January 2017).
    linkopts = [],
    linkshared = 1,
    linkstatic = 1,
    copts = tf_copts(),
    deps = [
        "@org_tensorflow//tensorflow/core/distributed_runtime/rpc:grpc_server_lib",
        "//src/main/native",
        LINKER_VERSION_SCRIPT,
        LINKER_EXPORTED_SYMBOLS,
    ],
)
