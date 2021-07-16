/*
Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.Adapter;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.NoException;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/** @author Samuel Audet */
@Properties(
    value = {
      @Platform(
          value = {"linux", "macosx", "windows"},
          compiler = "cpp11",
          include = {
            "tensorflow/core/platform/ctstring_internal.h",
            "tensorflow/core/platform/ctstring.h",
            "tensorflow/core/util/port.h",
            "tensorflow/c/tf_attrtype.h",
            "tensorflow/c/c_api_macros.h",
            "tensorflow/c/tf_datatype.h",
            "tensorflow/c/tf_status.h",
            "tensorflow/c/tf_tensor.h",
            "tensorflow/c/tf_tstring.h",
            "tensorflow/c/c_api.h",
            //                "tensorflow/c/env.h",
            "tensorflow/c/kernels.h",
            "tensorflow/c/ops.h",
            "tensorflow_adapters.h",
            "tensorflow/c/eager/c_api.h",
            "tensorflow/cc/framework/scope.h",
            "tensorflow/cc/framework/grad_op_registry.h",
            "tensorflow/core/platform/status.h",
            "tensorflow/core/graph/graph.h",
            "tensorflow/c/tf_status_helper.h",
            "tensorflow/cc/framework/ops.h",
            "tensorflow/c/c_api_internal.h",
          },
          link = "tensorflow_cc@.2",
          preload = {"iomp5", "mklml", "mklml_intel", "tensorflow_framework@.2"},
          preloadresource = "/org/bytedeco/mkldnn/",
          resource = {"LICENSE", "THIRD_PARTY_TF_JNI_LICENSES"}),
      @Platform(
          value = "windows",
          preload = {
            "api-ms-win-crt-locale-l1-1-0",
            "api-ms-win-crt-string-l1-1-0",
            "api-ms-win-crt-stdio-l1-1-0",
            "api-ms-win-crt-math-l1-1-0",
            "api-ms-win-crt-heap-l1-1-0",
            "api-ms-win-crt-runtime-l1-1-0",
            "api-ms-win-crt-convert-l1-1-0",
            "api-ms-win-crt-environment-l1-1-0",
            "api-ms-win-crt-time-l1-1-0",
            "api-ms-win-crt-filesystem-l1-1-0",
            "api-ms-win-crt-utility-l1-1-0",
            "api-ms-win-crt-multibyte-l1-1-0",
            "api-ms-win-core-string-l1-1-0",
            "api-ms-win-core-errorhandling-l1-1-0",
            "api-ms-win-core-timezone-l1-1-0",
            "api-ms-win-core-file-l1-1-0",
            "api-ms-win-core-namedpipe-l1-1-0",
            "api-ms-win-core-handle-l1-1-0",
            "api-ms-win-core-file-l2-1-0",
            "api-ms-win-core-heap-l1-1-0",
            "api-ms-win-core-libraryloader-l1-1-0",
            "api-ms-win-core-synch-l1-1-0",
            "api-ms-win-core-processthreads-l1-1-0",
            "api-ms-win-core-processenvironment-l1-1-0",
            "api-ms-win-core-datetime-l1-1-0",
            "api-ms-win-core-localization-l1-2-0",
            "api-ms-win-core-sysinfo-l1-1-0",
            "api-ms-win-core-synch-l1-2-0",
            "api-ms-win-core-console-l1-1-0",
            "api-ms-win-core-debug-l1-1-0",
            "api-ms-win-core-rtlsupport-l1-1-0",
            "api-ms-win-core-processthreads-l1-1-1",
            "api-ms-win-core-file-l1-2-0",
            "api-ms-win-core-profile-l1-1-0",
            "api-ms-win-core-memory-l1-1-0",
            "api-ms-win-core-util-l1-1-0",
            "api-ms-win-core-interlocked-l1-1-0",
            "ucrtbase",
            "vcruntime140",
            "vcruntime140_1",
            "msvcp140",
            "concrt140",
            "vcomp140",
            "msvcr120",
            "libiomp5md",
            "mklml",
            "tensorflow_framework"
          }),
      @Platform(
          value = "windows-x86",
          preloadpath = {
            "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.CRT/",
            "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x86/Microsoft.VC140.OpenMP/",
            "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x86/"
          }),
      @Platform(
          value = "windows-x86_64",
          preloadpath = {
            "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.CRT/",
            "C:/Program Files (x86)/Microsoft Visual Studio 14.0/VC/redist/x64/Microsoft.VC140.OpenMP/",
            "C:/Program Files (x86)/Windows Kits/10/Redist/ucrt/DLLs/x64/"
          }),
      @Platform(
          value = {"linux", "macosx", "windows"},
          extension = {"-mkl", "-gpu", "-mkl-gpu"})
    },
    target = "org.tensorflow.internal.c_api",
    global = "org.tensorflow.internal.c_api.global.tensorflow")
@NoException
public class tensorflow implements LoadEnabled, InfoMapper {

  @Override
  public void init(ClassProperties properties) {
    String platform = properties.getProperty("platform");
    String extension = properties.getProperty("platform.extension");
    List<String> preloads = properties.get("platform.preload");
    List<String> resources = properties.get("platform.preloadresource");
    List<String> preloadpaths = properties.get("platform.preloadpath");

    String vcredistdir = System.getenv("VCToolsRedistDir");
    if (vcredistdir != null && vcredistdir.length() > 0) {
      switch (platform) {
        case "windows-x86":
          preloadpaths.add(0, vcredistdir + "\\x86\\Microsoft.VC142.CRT");
          preloadpaths.add(1, vcredistdir + "\\x86\\Microsoft.VC142.OpenMP");
          preloadpaths.add(2, vcredistdir + "\\x86\\Microsoft.VC141.CRT");
          preloadpaths.add(3, vcredistdir + "\\x86\\Microsoft.VC141.OpenMP");
          break;
        case "windows-x86_64":
          preloadpaths.add(0, vcredistdir + "\\x64\\Microsoft.VC142.CRT");
          preloadpaths.add(1, vcredistdir + "\\x64\\Microsoft.VC142.OpenMP");
          preloadpaths.add(2, vcredistdir + "\\x64\\Microsoft.VC141.CRT");
          preloadpaths.add(3, vcredistdir + "\\x64\\Microsoft.VC141.OpenMP");
          break;
        default:
          // not Windows
      }
    }

    // Only apply this at load time
    if (!Loader.isLoadLibraries()) {
      return;
    }

    // Let users enable loading of the full version of MKL
    String load =
        System.getProperty(
                "org.bytedeco.openblas.load", System.getProperty("org.bytedeco.mklml.load", ""))
            .toLowerCase();

    int i = 0;
    if (load.equals("mkl") || load.equals("mkl_rt")) {
      String[] libs = {
        "iomp5",
        "libiomp5md",
        "mkl_core",
        "mkl_avx",
        "mkl_avx2",
        "mkl_avx512",
        "mkl_avx512_mic",
        "mkl_def",
        "mkl_mc",
        "mkl_mc3",
        "mkl_intel_lp64",
        "mkl_intel_thread",
        "mkl_gnu_thread",
        "mkl_rt"
      };
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
    String[] libs = {
      "cudart",
      "cublasLt",
      "cublas",
      "cufft",
      "curand",
      "cusolver",
      "cusparse",
      "cudnn",
      "nccl",
      "nvrtc",
      "myelin",
      "nvinfer",
      "cudnn_ops_infer",
      "cudnn_ops_train",
      "cudnn_adv_infer",
      "cudnn_adv_train",
      "cudnn_cnn_infer",
      "cudnn_cnn_train"
    };
    for (String lib : libs) {
      if (platform.startsWith("linux")) {
        lib +=
            lib.startsWith("cudnn")
                ? "@.8"
                : lib.equals("nccl")
                    ? "@.2"
                    : lib.equals("myelin")
                        ? "@.1"
                        : lib.equals("nvinfer")
                            ? "@.7"
                            : lib.equals("cufft") || lib.equals("curand") || lib.equals("cusolver")
                                ? "@.10"
                                : lib.equals("cudart")
                                    ? "@.11.0"
                                    : lib.equals("nvrtc") ? "@.11.0" : "@.11";
      } else if (platform.startsWith("windows")) {
        lib +=
            lib.startsWith("cudnn")
                ? "64_8"
                : lib.equals("nccl")
                    ? "64_2"
                    : lib.equals("myelin")
                        ? "64_1"
                        : lib.equals("nvinfer")
                            ? "64_7"
                            : lib.equals("cufft") || lib.equals("curand") || lib.equals("cusolver")
                                ? "64_10"
                                : lib.equals("cudart")
                                    ? "64_110"
                                    : lib.equals("nvrtc") ? "64_110_0" : "64_11";
      } else {
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
  }

  @Override
  public void map(InfoMap infoMap) {
    infoMap
        .put(
            new Info("c_api_internal.h")
                .linePatterns(
                    "struct TF_OperationDescription \\{",
                    "\\};",
                    "struct TF_Graph \\{",
                    "\\};",
                    "struct TF_Operation \\{",
                    "\\};",
                    "// Exposed helper functions",
                    "// End Exposed helper functions"))
        .put(
            new Info("graph.h")
                .linePatterns(
                    "class Node \\{", "// Stores debug information associated with the Node."))
        .put(new Info("Node").cppTypes("tensorflow::Node").purify())
        .put(
            new Info(
                    "tensorflow::NodeDef",
                    "tensorflow::OpDef",
                    "tensorflow::AttrSlice",
                    "tensorflow::Edge",
                    "tensorflow::EdgeSet",
                    "tensorflow::WhileContext",
                    "tensorflow::NodeProperties",
                    "protobuf::RepeatedPtrField",
                    "gtl::iterator_range<NeighborIter>",
                    "tensorflow::DataType",
                    "tensorflow::DataTypeVector",
                    "tensorflow::Node::set_original_node_names",
                    "tensorflow::Node::AddAttr",
                    "tensorflow::Node::ClearAttr",
                    "tensorflow::Node::input_node")
                .skip())
        .put(
            new Info("c_api.cc")
                .linePatterns(
                    "// Helper functions -+",
                    "// Shape functions -+",
                    "static TF_OperationDescription\\* TF_NewOperationLocked\\(TF_Graph\\* graph,",
                    "\\}",
                    "static TF_Operation\\* TF_FinishOperationLocked\\(TF_OperationDescription\\* desc,",
                    "\\}"))
        .put(new Info("OutputTensor", "TensorId", "tensorflow::AttrValue").skip())
        .put(
            new Info("TF_CAPI_EXPORT", "TF_Bool", "TF_GUARDED_BY", "TF_MUST_USE_RESULT")
                .cppTypes()
                .annotations())
        .put(new Info("TF_DISALLOW_COPY_AND_ASSIGN").skip())
        .put(
            new Info("TF_Buffer::data")
                .javaText(
                    "public native @Const Pointer data(); public native TF_Buffer data(Pointer data);"))
        .put(
            new Info("TF_Status")
                .pointerTypes("TF_Status")
                .base("org.tensorflow.internal.c_api.AbstractTF_Status"))
        .put(
            new Info("TF_Buffer")
                .pointerTypes("TF_Buffer")
                .base("org.tensorflow.internal.c_api.AbstractTF_Buffer"))
        .put(
            new Info("TF_Tensor")
                .pointerTypes("TF_Tensor")
                .base("org.tensorflow.internal.c_api.AbstractTF_Tensor"))
        .put(
            new Info("TF_Session")
                .pointerTypes("TF_Session")
                .base("org.tensorflow.internal.c_api.AbstractTF_Session"))
        .put(
            new Info("TF_SessionOptions")
                .pointerTypes("TF_SessionOptions")
                .base("org.tensorflow.internal.c_api.AbstractTF_SessionOptions"))
        .put(
            new Info("TF_Graph")
                .pointerTypes("TF_Graph")
                .base("org.tensorflow.internal.c_api.AbstractTF_Graph")
                .purify())
        .put(new Info("tensorflow::Graph").javaNames("NativeGraphPointer"))
        .put(
            new Info("TF_Graph::graph")
                .javaText("public native @MemberGetter @ByRef NativeGraphPointer graph();"))
        .put(
            new Info(
                    "TF_Graph::refiner",
                    "TF_Graph::mu",
                    "TF_Graph::sessions",
                    "TF_Graph::delete_requested")
                .skip())
        .put(
            new Info("std::unordered_map<tensorflow::string,tensorflow::Node*>")
                .pointerTypes("NameMap")
                .define()
                .javaText("public native long erase(@StdString BytePointer key);"))
        .put(
            new Info("TF_Function")
                .pointerTypes("TF_Function")
                .base("org.tensorflow.internal.c_api.AbstractTF_Function"))
        .put(
            new Info("TF_ImportGraphDefOptions")
                .pointerTypes("TF_ImportGraphDefOptions")
                .base("org.tensorflow.internal.c_api.AbstractTF_ImportGraphDefOptions"))
        .put(
            new Info(
                    "TF_WhileParams",
                    "TFE_MonitoringCounterCell",
                    "TFE_MonitoringSamplerCell",
                    "TFE_MonitoringCounter0",
                    "TFE_MonitoringCounter1",
                    "TFE_MonitoringCounter2",
                    "TFE_MonitoringIntGaugeCell",
                    "TFE_MonitoringStringGaugeCell",
                    "TFE_MonitoringBoolGaugeCell",
                    "TFE_MonitoringIntGauge0",
                    "TFE_MonitoringIntGauge1",
                    "TFE_MonitoringIntGauge2",
                    "TFE_MonitoringStringGauge0",
                    "TFE_MonitoringStringGauge1",
                    "TFE_MonitoringStringGauge2",
                    "TFE_MonitoringBoolGauge0",
                    "TFE_MonitoringBoolGauge1",
                    "TFE_MonitoringBoolGauge2",
                    "TFE_MonitoringSampler0",
                    "TFE_MonitoringSampler1",
                    "TFE_MonitoringSampler2")
                .purify())
        .put(new Info("TF_Operation").purify())
        .put(
            new Info("TF_Operation::node")
                .javaText("public native @MemberGetter @ByRef Node node();"))
        .put(
            new Info("TFE_MonitoringCounterCell::cell")
                .javaText("public native @MemberGetter @ByRef CounterCell cell();"))
        .put(
            new Info("TFE_MonitoringSamplerCell::cell")
                .javaText("public native @MemberGetter @ByRef SamplerCell cell();"))
        .put(
            new Info("TFE_MonitoringIntGaugeCell::cell")
                .javaText("public native @MemberGetter @ByRef IntGaugeCell cell();"))
        .put(
            new Info("TFE_MonitoringStringGaugeCell::cell")
                .javaText("public native @MemberGetter @ByRef StringGaugeCell cell();"))
        .put(
            new Info("TFE_MonitoringBoolGaugeCell::cell")
                .javaText("public native @MemberGetter @ByRef BoolGaugeCell cell();"))
        .put(
            new Info("TFE_Context")
                .pointerTypes("TFE_Context")
                .base("org.tensorflow.internal.c_api.AbstractTFE_Context"))
        .put(
            new Info("TFE_ContextOptions")
                .pointerTypes("TFE_ContextOptions")
                .base("org.tensorflow.internal.c_api.AbstractTFE_ContextOptions"))
        .put(
            new Info("TFE_Context::context")
                .javaText("@MemberGetter public native @ByRef EagerContext context();"))
        .put(
            new Info("TFE_Op")
                .pointerTypes("TFE_Op")
                .base("org.tensorflow.internal.c_api.AbstractTFE_Op"))
        .put(
            new Info("TFE_Op::operation")
                .javaText("@MemberGetter public native @ByRef EagerOperation operation();"))
        .put(
            new Info("TFE_TensorHandle")
                .pointerTypes("TFE_TensorHandle")
                .base("org.tensorflow.internal.c_api.AbstractTFE_TensorHandle"))
        .put(new Info("SP_Stream").cast().pointerTypes("Pointer"))
        .put(
            new Info(
                    "TF_ShapeInferenceContextDimValueKnown",
                    "TFE_NewTensorHandle(const tensorflow::Tensor&, TF_Status*)",
                    "TF_InitKernel")
                .skip())
        .put(new Info("TF_OperationDescription").pointerTypes("TF_OperationDescription").purify())
        .put(new Info("tensorflow::Scope").javaNames("TF_Scope"))
        .put(new Info("tensorflow::NodeBuilder").pointerTypes("NodeBuilder"))
        .put(
            new Info("string", "tensorflow::string")
                .annotations("@StdString")
                .valueTypes("BytePointer", "String")
                .pointerTypes("BytePointer"))
        .put(new Info("absl::Span", "tensorflow::gtl::ArraySlice").annotations("@Span"))
        .put(
            new Info("std::vector<tensorflow::Output>").pointerTypes("NativeOutputVector").define())
        .put(new Info("tensorflow::Output").javaNames("NativeOutput"))
        .put(new Info("tensorflow::Operation").javaNames("NativeOperation"))
        .put(new Info("tensorflow::Status").javaNames("NativeStatus").purify())
        .put(
            new Info("tensorflow::int32")
                .cast()
                .valueTypes("int")
                .pointerTypes("IntPointer", "IntBuffer", "int[]"))
        .put(
            new Info(
                    "tensorflow::CompositeOpScopes",
                    "tensorflow::Input",
                    "tensorflow::InputList",
                    "tensorflow::OutputHash",
                    "tensorflow::StackFrame",
                    "tensorflow::StatusGroup",
                    "tensorflow::internal::TF_StatusDeleter",
                    "tensorflow::GraphDef",
                    "tensorflow::Scope::graph_as_shared_ptr",
                    "tensorflow::Scope::ToGraphDef",
                    "tensorflow::Scope::ToGraph",
                    "tensorflow::Scope::DoShapeInference",
                    "tensorflow::Scope::DisabledShapeInferenceScope",
                    "tensorflow::Scope::control_deps",
                    "tensorflow::Scope::WithKernelLabel",
                    "tensorflow::Scope::ClearColocation",
                    "tensorflow::Scope::ColocateWith",
                    "tensorflow::Scope::ColocateWith",
                    "tensorflow::Scope::WithXlaCluster",
                    "tensorflow::Scope::WithAssignedDevice",
                    "tensorflow::Scope::status",
                    "tensorflow::Scope::UpdateStatus",
                    "tensorflow::Status::code",
                    "tensorflow::CreateOutputWithScope",
                    "TF_OperationDescription::colocation_constraints",
                    "tensorflow::Operation::num_inputs",
                    "tensorflow::Operation::input_type",
                    "tensorflow::Operation::input",
                    "tensorflow::Operation::num_outputs",
                    "tensorflow::Operation::output_type",
                    "tensorflow::Operation::output",
                    "tensorflow::Operation::hash",
                    "tensorflow::Output::hash",
                    "tensorflow::Output::type",
                    "tensorflow::Status::GetAllPayloads",
                    "tensorflow::Status::ReplaceAllPayloads",
                    "tensorflow::Status::ErasePayload",
                    "tensorflow::Status::SetPayload",
                    "tensorflow::Status::GetPayload",
                    "tensorflow::Node::SetStackTrace",
                    "tensorflow::Node::GetStackTrace")
                .skip());
  }

  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.PARAMETER})
  @Cast({"absl::Span", "&"})
  @Adapter("SpanAdapter")
  public @interface Span {

    String value() default "";
  }
}
