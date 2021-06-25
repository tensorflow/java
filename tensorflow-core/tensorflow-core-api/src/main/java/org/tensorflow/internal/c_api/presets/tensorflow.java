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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.bytedeco.javacpp.ClassProperties;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.LoadEnabled;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Adapter;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.NoException;
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
            compiler = "cpp14",
            define = {"NDEBUG", "UNIQUE_PTR_NAMESPACE std", "SHARED_PTR_NAMESPACE std"},
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
//                "tensorflow/c/experimental/stream_executor/stream_executor.h",
                "tensorflow/c/kernels.h",
                "tensorflow/c/ops.h",
                "tensorflow/c/eager/c_api.h",
                "tensorflow_adapters.h",
//                "tensorflow/core/common_runtime/eager/attr_builder.h",
//                "tensorflow/core/framework/types.pb.h",
//                "tensorflow/core/framework/function.pb.h",
//                "tensorflow/core/framework/tensor_shape.h",
//                "tensorflow/core/framework/types.h",
//                "tensorflow/core/protobuf/error_codes.pb.h",
//                "tensorflow/core/platform/logging.h",
//                "tensorflow/core/platform/macros.h",
//                "tensorflow/core/platform/stringpiece.h",
                "tensorflow/core/platform/stack_frame.h",
                "tensorflow/core/platform/errors.h",
                "tensorflow/core/platform/status.h",
//                "tensorflow/core/platform/refcount.h",
//                "tensorflow/core/platform/types.h",
                "tensorflow/c/tensor_interface.h",
                "tensorflow/c/eager/abstract_tensor_handle.h",
                "tensorflow/c/eager/abstract_function.h",
                "tensorflow/c/eager/abstract_operation.h",
                "tensorflow/c/eager/abstract_context.h",
                "tensorflow/c/eager/tape.h",
                "tensorflow/c/eager/gradients.h",
            },
            link = "tensorflow_cc@.2",
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
                "vcruntime140", "vcruntime140_1", "msvcp140", "concrt140", "vcomp140", "msvcr120", "libiomp5md", "mklml", "tensorflow_framework"
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
@NoException
public class tensorflow implements LoadEnabled, InfoMapper {

    @Override public void init(ClassProperties properties) {
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
        String[] libs = {"cudart", "cublasLt", "cublas", "cufft", "curand", "cusolver", "cusparse", "cudnn", "nccl", "nvrtc", "myelin", "nvinfer",
                         "cudnn_ops_infer", "cudnn_ops_train", "cudnn_adv_infer", "cudnn_adv_train", "cudnn_cnn_infer", "cudnn_cnn_train"};
        for (String lib : libs) {
            if (platform.startsWith("linux")) {
                lib += lib.startsWith("cudnn") ? "@.8"
                     : lib.equals("nccl") ? "@.2"
                     : lib.equals("myelin") ? "@.1"
                     : lib.equals("nvinfer") ? "@.7"
                     : lib.equals("cufft") || lib.equals("curand") || lib.equals("cusolver") ? "@.10"
                     : lib.equals("cudart") ? "@.11.0"
                     : lib.equals("nvrtc") ? "@.11.0"
                     : "@.11";
            } else if (platform.startsWith("windows")) {
                lib += lib.startsWith("cudnn") ? "64_8"
                     : lib.equals("nccl") ? "64_2"
                     : lib.equals("myelin") ? "64_1"
                     : lib.equals("nvinfer") ? "64_7"
                     : lib.equals("cufft") || lib.equals("curand") || lib.equals("cusolver") ? "64_10"
                     : lib.equals("cudart") ? "64_110"
                     : lib.equals("nvrtc") ? "64_110_0"
                     : "64_11";
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

    public void map(InfoMap infoMap) {
        infoMap.put(new Info("TF_CAPI_EXPORT", "TF_Bool", "TF_MUST_USE_RESULT").cppTypes().annotations())
               .put(new Info("TF_Buffer::data").javaText("public native @Const Pointer data(); public native TF_Buffer data(Pointer data);"))
               .put(new Info("TF_Status").pointerTypes("TF_Status").base("org.tensorflow.internal.c_api.AbstractTF_Status"))
               .put(new Info("TF_Buffer").pointerTypes("TF_Buffer").base("org.tensorflow.internal.c_api.AbstractTF_Buffer"))
               .put(new Info("TF_Tensor").pointerTypes("TF_Tensor").base("org.tensorflow.internal.c_api.AbstractTF_Tensor"))
               .put(new Info("TF_Session").pointerTypes("TF_Session").base("org.tensorflow.internal.c_api.AbstractTF_Session"))
               .put(new Info("TF_SessionOptions").pointerTypes("TF_SessionOptions").base("org.tensorflow.internal.c_api.AbstractTF_SessionOptions"))
               .put(new Info("TF_Graph").pointerTypes("TF_Graph").base("org.tensorflow.internal.c_api.AbstractTF_Graph"))
               .put(new Info("TF_Graph::graph").javaText("public native @MemberGetter @ByRef Graph graph();"))
               .put(new Info("TF_Graph::refiner").javaText("public native @MemberGetter @ByRef ShapeRefiner refiner();"))
               .put(new Info("TF_Function").pointerTypes("TF_Function").base("org.tensorflow.internal.c_api.AbstractTF_Function"))
               .put(new Info("TF_ImportGraphDefOptions").pointerTypes("TF_ImportGraphDefOptions").base("org.tensorflow.internal.c_api.AbstractTF_ImportGraphDefOptions"))
               .put(new Info("TF_Operation", "TF_WhileParams", "TFE_MonitoringCounterCell", "TFE_MonitoringSamplerCell",
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
               .put(new Info("TFE_Context").pointerTypes("TFE_Context").base("org.tensorflow.internal.c_api.AbstractTFE_Context"))
               .put(new Info("TFE_ContextOptions").pointerTypes("TFE_ContextOptions").base("org.tensorflow.internal.c_api.AbstractTFE_ContextOptions"))
               .put(new Info("TFE_Context::context").javaText("@MemberGetter public native @ByRef EagerContext context();"))
               .put(new Info("TFE_Op").pointerTypes("TFE_Op").base("org.tensorflow.internal.c_api.AbstractTFE_Op"))
               .put(new Info("TFE_Op::operation").javaText("@MemberGetter public native @ByRef EagerOperation operation();"))
               .put(new Info("TFE_TensorHandle").pointerTypes("TFE_TensorHandle").base("org.tensorflow.internal.c_api.AbstractTFE_TensorHandle"))
               .put(new Info("TF_ShapeInferenceContextDimValueKnown", "TFE_NewTensorHandle(const tensorflow::Tensor&, TF_Status*)",
                             "TF_InitKernel", "tensorflow::errors::internal::PrepareForStrCat").skip())

               .put(new Info("absl::Span", "tensorflow::gtl::ArraySlice").annotations("@Span"))
               .put(new Info("std::unordered_set<int>").pointerTypes("IntSet").define())
               .put(new Info("std::unordered_map<std::string,std::string>").pointerTypes("StringStringMap").define())
               .put(new Info("std::unordered_map<tensorflow::string,std::unordered_set<int> >").pointerTypes("StringIntSetMap").define())
               .put(new Info("std::unordered_map<tensorflow::int64,tensorflow::int64>").pointerTypes("TensorTape").define())
               .put(new Info("const std::unordered_map<tensorflow::int64,tensorflow::gradients::TapeTensor>").pointerTypes("LongTapeTensorMap").define())
               .put(new Info("std::unordered_map<tensorflow::int64,tensorflow::eager::OpTapeEntry<tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor> >").pointerTypes("OpTape").define())
               .put(new Info("std::vector<tensorflow::StackFrame>").valueTypes("@StdMove StackFrameVector").pointerTypes("StackFrameVector").define())
               .put(new Info("tensorflow::error::Code", "tensorflow::DataType").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("tensorflow::AbstractTensorHandle::AbstractTensorHandleKind",
                             "tensorflow::AbstractFunction::AbstractFunctionKind",
                             "tensorflow::AbstractOperation::AbstractOperationKind",
                             "tensorflow::AbstractContext::AbstractContextKind").valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("tensorflow::int64").cast().valueTypes("long").pointerTypes("LongPointer", "LongBuffer", "long[]"))
               .put(new Info("tensorflow::string", "absl::string_view", "tensorflow::StringPiece").annotations("@StdString").valueTypes("BytePointer", "String").pointerTypes("BytePointer"))
               .put(new Info("SP_Stream", "tensorflow::AttrBuilder", "tensorflow::FunctionDef", "tensorflow::core::RefCounted", "tensorflow::PartialTensorShape").cast().pointerTypes("Pointer"))

               .put(new Info("tensorflow::Status::GetPayload").javaText(
                       "public native @ByVal @Cast(\"tensorflow::StringPiece*\") PointerPointer GetPayload(@StdString BytePointer type_url);\n"
                     + "public native @ByVal @Cast(\"tensorflow::StringPiece*\") PointerPointer GetPayload(@StdString String type_url);"))

               .put(new Info("tensorflow::AbstractOperation::AddInputList").javaText(
                       "public native @ByVal Status AddInputList(\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> inputs);"))
               .put(new Info("tensorflow::AbstractOperation::Execute").javaText(
                       "public native @ByVal Status Execute(@Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> retvals,\n"
                     + "                         IntPointer num_retvals);"))
               .put(new Info("tensorflow::AbstractOperation::SetAttrFunctionList").javaText(
                       "public native @ByVal Status SetAttrFunctionList(\n"
                     + "      @Cast(\"const char*\") BytePointer attr_name, @Cast(\"const tensorflow::AbstractOperation**\") @Span PointerPointer<AbstractOperation> values);"))

               .put(new Info("tensorflow::gradients::ForwardOperation").immutable())
               .put(new Info("tensorflow::gradients::GradientFunction").virtualize())
               .put(new Info("tensorflow::gradients::GradientFunction::Compute").javaText(
                       "@Virtual(true) public native @ByVal Status Compute(AbstractContext ctx,\n"
                     + "                         @Cast({\"tensorflow::AbstractTensorHandle* const*\", \"absl::Span<tensorflow::AbstractTensorHandle* const>\"}) @Span PointerPointer<AbstractTensorHandle> grad_outputs,\n"
                     + "                         @Cast({\"tensorflow::AbstractTensorHandle**\", \"absl::Span<tensorflow::AbstractTensorHandle*>\"}) @Span PointerPointer<AbstractTensorHandle> grad_inputs);"))

               .put(new Info("tensorflow::eager::OpTapeEntry<tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>").annotations("@NoOffset").valueTypes("@ByVal OpEntry").pointerTypes("OpEntry"))
               .put(new Info("tensorflow::eager::OpTapeEntry<tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>::backward_function_deleter").javaText(
                       "public native @ByRef @Cast(\"std::function<void(tensorflow::gradients::GradientFunction*)>*\") Pointer backward_function_deleter(); public native OpEntry backward_function_deleter(Pointer setter);\n"
                     + "public native @MemberSetter OpEntry backward_function_deleter(GradientFunctionDeleter setter);"))

               .put(new Info("tensorflow::eager::VSpace<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>").pointerTypes("TapeVSpace"))
               .put(new Info("tensorflow::eager::VSpace<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>::AggregateGradients",
                             "tensorflow::gradients::TapeVSpace::AggregateGradients").javaText(
                       "public native AbstractTensorHandle AggregateGradients(\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> gradient_tensors);"))
               .put(new Info("tensorflow::eager::VSpace<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>::CallBackwardFunction",
                             "tensorflow::gradients::TapeVSpace::CallBackwardFunction").javaText(
                       "public native @ByVal Status CallBackwardFunction(\n"
                     + "      @StdString String op_type, GradientFunction backward_function,\n"
                     + "      @Cast(\"tensorflow::int64*\") @StdVector LongPointer unneeded_gradients,\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> output_gradients,\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> result);"))
               .put(new Info("tensorflow::eager::VSpace<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>::BuildOnesLike",
                             "tensorflow::gradients::TapeVSpace::BuildOnesLike").javaText(
                       "public native @ByVal Status BuildOnesLike(@Const @ByRef TapeTensor t,\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") PointerPointer<AbstractTensorHandle> result);"))

               .put(new Info("tensorflow::eager::GradientTape<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>").pointerTypes("Tape"))
               .put(new Info("tensorflow::eager::GradientTape<tensorflow::AbstractTensorHandle,tensorflow::gradients::GradientFunction,tensorflow::gradients::TapeTensor>::ComputeGradient").javaText(
                       "public native @ByVal Status ComputeGradient(\n"
                     + "      @Const @ByRef TapeVSpace vspace,\n"
                     + "      @Cast(\"tensorflow::int64*\") @Span LongPointer target_tensor_ids,\n"
                     + "      @Cast(\"tensorflow::int64*\") @Span LongPointer source_tensor_ids,\n"
                     + "      @Const @ByRef LongTapeTensorMap sources_that_are_targets,\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> output_gradients,\n"
                     + "      @Cast(\"tensorflow::AbstractTensorHandle**\") @Span PointerPointer<AbstractTensorHandle> result, @Cast(\"bool\") boolean build_default_zeros_grads/*=true*/);"))

               .put(new Info("std::function<tensorflow::gradients::GradientFunction*(const ForwardOperation&op)>").pointerTypes("GradientFunctionFactory"))
               .put(new Info("std::function<tensorflow::gradients::GradientFunction*()>").pointerTypes("GradientFunctionGetter"))
               .put(new Info("std::function<void(BackwardFunction*)>").pointerTypes("GradientFunctionDeleter"));
    }

    @Documented @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.PARAMETER})
    @Cast({"absl::Span", "&"}) @Adapter("SpanAdapter")
    public @interface Span { String value() default ""; }

    public static class GradientFunctionFactory extends FunctionPointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public    GradientFunctionFactory(Pointer p) { super(p); }
        protected GradientFunctionFactory() { allocate(); }
        private native void allocate();
        public native @Cast("tensorflow::gradients::GradientFunction*") Pointer call(@ByRef @Cast("const tensorflow::gradients::ForwardOperation*") Pointer op);
    }

    public static class GradientFunctionGetter extends FunctionPointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public    GradientFunctionGetter(Pointer p) { super(p); }
        protected GradientFunctionGetter() { allocate(); }
        private native void allocate();
        public native @Cast("tensorflow::gradients::GradientFunction*") Pointer call();
    }

    public static class GradientFunctionDeleter extends FunctionPointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public    GradientFunctionDeleter(Pointer p) { super(p); }
        protected GradientFunctionDeleter() { allocate(); }
        private native void allocate();
        public native void call(@Cast("tensorflow::gradients::GradientFunction*") Pointer op);
    }
}
