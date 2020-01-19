/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */

package org.tensorflow.internal.c_api.presets;

import java.util.List;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 *
 * @author Samuel Audet
 */
@Properties(
    value = {
        @Platform(
            value = {"linux", "macosx", "windows"},
            compiler = "cpp11",
            include = {
                "tensorflow/c/tf_attrtype.h",
                "tensorflow/c/tf_datatype.h",
                "tensorflow/c/tf_status.h",
                "tensorflow/c/tf_tensor.h",
                "tensorflow/c/c_api.h",
//                "tensorflow/c/env.h",
                "tensorflow/c/kernels.h",
                "tensorflow/c/ops.h",
                "tensorflow/c/eager/c_api.h"
            },
            link = "tensorflow@.2",
            preload = {"iomp5", "mklml", "mklml_intel", "tensorflow_framework@.2"},
            preloadresource = "/org/bytedeco/mkldnn/",
            resource = {"LICENSE", "THIRD_PARTY_TF_JNI_LICENSES"}
        ),
        @Platform(
            value = "windows",
            preload = {
                "api-ms-win-crt-locale-l1-1-0", "api-ms-win-crt-string-l1-1-0", "api-ms-win-crt-stdio-l1-1-0", "api-ms-win-crt-math-l1-1-0",
                "api-ms-win-crt-heap-l1-1-0", "api-ms-win-crt-runtime-l1-1-0", "api-ms-win-crt-convert-l1-1-0", "api-ms-win-crt-environment-l1-1-0",
                "api-ms-win-crt-time-l1-1-0", "api-ms-win-crt-filesystem-l1-1-0", "api-ms-win-crt-utility-l1-1-0", "api-ms-win-crt-multibyte-l1-1-0",
                "api-ms-win-core-string-l1-1-0", "api-ms-win-core-errorhandling-l1-1-0", "api-ms-win-core-timezone-l1-1-0", "api-ms-win-core-file-l1-1-0",
                "api-ms-win-core-namedpipe-l1-1-0", "api-ms-win-core-handle-l1-1-0", "api-ms-win-core-file-l2-1-0", "api-ms-win-core-heap-l1-1-0",
                "api-ms-win-core-libraryloader-l1-1-0", "api-ms-win-core-synch-l1-1-0", "api-ms-win-core-processthreads-l1-1-0",
                "api-ms-win-core-processenvironment-l1-1-0", "api-ms-win-core-datetime-l1-1-0", "api-ms-win-core-localization-l1-2-0",
                "api-ms-win-core-sysinfo-l1-1-0", "api-ms-win-core-synch-l1-2-0", "api-ms-win-core-console-l1-1-0", "api-ms-win-core-debug-l1-1-0",
                "api-ms-win-core-rtlsupport-l1-1-0", "api-ms-win-core-processthreads-l1-1-1", "api-ms-win-core-file-l1-2-0", "api-ms-win-core-profile-l1-1-0",
                "api-ms-win-core-memory-l1-1-0", "api-ms-win-core-util-l1-1-0", "api-ms-win-core-interlocked-l1-1-0", "ucrtbase",
                "vcruntime140", "msvcp140", "concrt140", "vcomp140", "msvcr120", "libiomp5md", "mklml", "tensorflow_framework"
            }
        ),
        @Platform(
            value = "windows-x86",
            preloadpath = {
                "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.CRT/",
                "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.OpenMP/",
                "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x86/"
            }
        ),
        @Platform(
            value = "windows-x86_64",
            preloadpath = {
                "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.CRT/",
                "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.OpenMP/",
                "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x64/"
            }
        ),
        @Platform(
            value = {"linux", "macosx", "windows"},
            extension = {"-mkl", "-gpu", "-mkl-gpu"}
        )
    },
    target = "org.tensorflow.internal.c_api",
    global = "org.tensorflow.internal.c_api.global.tensorflow")
public class tensorflow implements LoadEnabled, InfoMapper {

    @Override public void init(ClassProperties properties) {
        String platform = properties.getProperty("platform");
        String extension = properties.getProperty("platform.extension");
        List<String> preloads = properties.get("platform.preload");
        List<String> resources = properties.get("platform.preloadresource");
        List<String> preloadpaths = properties.get("platform.preloadpath");

        // Only apply this at load time
        if (!Loader.isLoadLibraries()) {
            return;
        }

        // Let users enable loading of the full version of MKL
        String load = System.getProperty("org.bytedeco.openblas.load",
                      System.getProperty("org.bytedeco.mklml.load", "")).toLowerCase();

        int i = 0;
        if (load.equals("mkl") || load.equals("mkl_rt")) {
            String[] libs = {"iomp5", "libiomp5md", "mkl_core", "mkl_avx", "mkl_avx2", "mkl_avx512", "mkl_avx512_mic",
                             "mkl_def", "mkl_mc", "mkl_mc3", "mkl_intel_lp64", "mkl_intel_thread", "mkl_gnu_thread", "mkl_rt"};
            for (i = 0; i < libs.length; i++) {
                preloads.add(i, libs[i] + "#" + libs[i]);
            }
            load = "mkl_rt";
            resources.add("/org/bytedeco/mkl/");
        }

        if (load.length() > 0) {
            if (platform.startsWith("linux")) {
                preloads.add(i, load + "#mklml_intel");
            } else if (platform.startsWith("macosx")) {
                preloads.add(i, load + "#mklml");
            } else if (platform.startsWith("windows")) {
                preloads.add(i, load + "#mklml");
            }
        }

        // Only apply this at load time since we don't want to copy the CUDA libraries here
        if (!Loader.isLoadLibraries() || extension == null || !extension.endsWith("-gpu")) {
            return;
        }
        String[] libs = {"cudart", "cublasLt", "cublas", "cufft", "curand", "cusolver", "cusparse", "cudnn", "nccl", "nvinfer"};
        for (String lib : libs) {
            switch (platform) {
                case "linux-arm64":
                case "linux-ppc64le":
                case "linux-x86_64":
                case "macosx-x86_64":
                    lib += lib.equals("cudnn") ? "@.7" : lib.equals("nccl") ? "@.2" : lib.equals("nvinfer") ? "@.6" : lib.equals("cudart") ? "@.10.1" : "@.10";
                    break;
                case "windows-x86_64":
                    lib += lib.equals("cudnn") ? "64_7" : lib.equals("cudart") ? "64_101" : "64_10";
                    break;
                default:
                    continue; // no CUDA
            }
            if (!preloads.contains(lib)) {
                preloads.add(i++, lib);
            }
        }
        if (i > 0) {
            resources.add("/org/bytedeco/cuda/");
            resources.add("/org/bytedeco/tensorrt/");
        }

        String vcredistdir = System.getenv("VCToolsRedistDir");
        if (vcredistdir != null && vcredistdir.length() > 0) {
            switch (platform) {
                case "windows-x86":
                    preloadpaths.add(0, vcredistdir + "\\x86\\Microsoft.VC141.CRT");
                    preloadpaths.add(1, vcredistdir + "\\x86\\Microsoft.VC141.OpenMP");
                    break;
                case "windows-x86_64":
                    preloadpaths.add(0, vcredistdir + "\\x64\\Microsoft.VC141.CRT");
                    preloadpaths.add(1, vcredistdir + "\\x64\\Microsoft.VC141.OpenMP");
                    break;
                default:
                    // not Windows
            }
        }
    }

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("TF_CAPI_EXPORT").cppTypes().annotations())
               .put(new Info("TF_Buffer::data").javaText("public native @Const Pointer data(); public native TF_Buffer data(Pointer data);"))
               .put(new Info("TF_Status").pointerTypes("TF_Status").base("org.tensorflow.internal.c_api.AbstractTF_Status"))
               .put(new Info("TF_Buffer").pointerTypes("TF_Buffer").base("org.tensorflow.internal.c_api.AbstractTF_Buffer"))
               .put(new Info("TF_Tensor").pointerTypes("TF_Tensor").base("org.tensorflow.internal.c_api.AbstractTF_Tensor"))
               .put(new Info("TF_SessionOptions").pointerTypes("TF_SessionOptions").base("org.tensorflow.internal.c_api.AbstractTF_SessionOptions"))
               .put(new Info("TF_Graph").pointerTypes("TF_Graph").base("org.tensorflow.internal.c_api.AbstractTF_Graph"))
               .put(new Info("TF_Graph::graph").javaText("public native @MemberGetter @ByRef Graph graph();"))
               .put(new Info("TF_Graph::refiner").javaText("public native @MemberGetter @ByRef ShapeRefiner refiner();"))
               .put(new Info("TF_ImportGraphDefOptions").pointerTypes("TF_ImportGraphDefOptions").base("org.tensorflow.internal.c_api.AbstractTF_ImportGraphDefOptions"))
               .put(new Info("TF_Operation", "TFE_MonitoringCounterCell", "TFE_MonitoringSamplerCell",
                             "TFE_MonitoringCounter0", "TFE_MonitoringCounter1", "TFE_MonitoringCounter2",
                             "TFE_MonitoringIntGaugeCell", "TFE_MonitoringStringGaugeCell", "TFE_MonitoringBoolGaugeCell",
                             "TFE_MonitoringIntGauge0", "TFE_MonitoringIntGauge1", "TFE_MonitoringIntGauge2",
                             "TFE_MonitoringStringGauge0", "TFE_MonitoringStringGauge1", "TFE_MonitoringStringGauge2",
                             "TFE_MonitoringBoolGauge0", "TFE_MonitoringBoolGauge1", "TFE_MonitoringBoolGauge2",
                             "TFE_MonitoringSampler0", "TFE_MonitoringSampler1", "TFE_MonitoringSampler2").purify())
               .put(new Info("TF_Operation::node").javaText("public native @MemberGetter @ByRef Node node();"))
               .put(new Info("TFE_MonitoringCounterCell::cell").javaText("public native @MemberGetter @ByRef CounterCell cell();"))
               .put(new Info("TFE_MonitoringSamplerCell::cell").javaText("public native @MemberGetter @ByRef SamplerCell cell();"))
               .put(new Info("TFE_MonitoringIntGaugeCell::cell").javaText("public native @MemberGetter @ByRef IntGaugeCell cell();"))
               .put(new Info("TFE_MonitoringStringGaugeCell::cell").javaText("public native @MemberGetter @ByRef StringGaugeCell cell();"))
               .put(new Info("TFE_MonitoringBoolGaugeCell::cell").javaText("public native @MemberGetter @ByRef BoolGaugeCell cell();"))
               .put(new Info("TFE_Context::context").javaText("@MemberGetter public native @ByRef EagerContext context();"))
               .put(new Info("TFE_Op::operation").javaText("@MemberGetter public native @ByRef EagerOperation operation();"))
               .put(new Info("TF_ShapeInferenceContextDimValueKnown", "TFE_NewTensorHandle(const tensorflow::Tensor&, TF_Status*)").skip())
               .put(new Info("TF_Session").pointerTypes("TF_Session").base("org.tensorflow.internal.c_api.AbstractTF_Session"))
               .put(new Info("TF_WhileParams").purify());
    }
}
