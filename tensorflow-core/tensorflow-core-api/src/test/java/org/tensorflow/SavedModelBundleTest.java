/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jdk.nashorn.internal.codegen.FunctionSignature;
import org.junit.jupiter.api.Test;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.Variable;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.proto.framework.TensorInfo;
import org.tensorflow.types.TFloat32;

/** Unit tests for {@link org.tensorflow.SavedModelBundle}. */
public class SavedModelBundleTest {

  private static final float EPSILON = 1e-7f;
  private static final String SAVED_MODEL_PATH;
  static {
    try {
      SAVED_MODEL_PATH = Paths.get(SavedModelBundleTest.class.getResource("/saved_model").toURI()).toString();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void load() {
    try (SavedModelBundle bundle = SavedModelBundle.load(SAVED_MODEL_PATH, "serve")) {
      assertNotNull(bundle.session());
      assertNotNull(bundle.graph());
      assertNotNull(bundle.metaGraphDef());
    }
  }

  @Test
  public void loadNonExistentBundle() {
    try {
      SavedModelBundle bundle = SavedModelBundle.load("__BAD__", "serve");
      bundle.close();
      fail("not expected");
    } catch (TensorFlowException e) {
      // expected exception
      assertTrue(e.getMessage().contains("Could not find SavedModel"));
    }
  }

  @Test
  public void loader() {
    try (SavedModelBundle bundle = SavedModelBundle.loader(SAVED_MODEL_PATH)
        .withTags("serve")
        .withConfigProto(sillyConfigProto())
        .withRunOptions(sillyRunOptions())
        .load()) {
      assertNotNull(bundle.session());
      assertNotNull(bundle.graph());
      assertNotNull(bundle.metaGraphDef());
    }
  }

  @Test
  public void export() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-saved-model-export-test");
    float reducedSum;
    FloatNdArray xValue = StdArrays.ndCopyOf(new float[][]{{0, 1, 2}, {3, 4, 5}});
    Shape xyShape = Shape.of(2, 3L);
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE, Placeholder.shape(xyShape));
      Variable<TFloat32> y = tf
          .variable(tf.random.randomUniform(tf.constant(xyShape), TFloat32.DTYPE));
      ReduceSum<TFloat32> z = tf.reduceSum(tf.math.add(x, y), tf.array(0, 1));
      Init init = tf.init();

      try (Session s = new Session(g)) {
        s.run(init);

        FunctionGraph function = FunctionGraph.builder()
            .input("input", x)
            .output("reducedSum", z)
            .build(s);

        // Call the graph and remember the result of computation for later
        try (Tensor<TFloat32> xTensor = TFloat32.tensorOf(xValue);
             Tensor<TFloat32> zTensor = function.call(xTensor).expect(TFloat32.DTYPE)) {
          reducedSum = zTensor.data().getFloat();
        }
        // Export the model
        SavedModelBundle.exporter(testFolder.toString())
            .withTags("test")
            .function(function)
            .export();
      }
    }
    assertTrue(Files.exists(testFolder.resolve(Paths.get("variables", "variables.index"))));
    assertTrue(Files
        .exists(testFolder.resolve(Paths.get("variables", "variables.data-00000-of-00001"))));
    assertTrue(Files.exists(testFolder.resolve("saved_model.pb")));

    // Reload the model just saved and validate its data
    try (SavedModelBundle savedModel = SavedModelBundle.load(testFolder.toString(), "test")) {
      assertNotNull(savedModel.metaGraphDef());
      assertNotNull(savedModel.metaGraphDef().getSaverDef());
      assertEquals(1, savedModel.metaGraphDef().getSignatureDefCount());
      assertEquals(FunctionGraph.DEFAULT_NAME,
          savedModel.metaGraphDef().getSignatureDefMap().keySet().iterator().next());

      SignatureDef signature = savedModel.metaGraphDef().getSignatureDefMap()
          .get(FunctionGraph.DEFAULT_NAME);
      assertNotNull(signature);
      assertEquals(1, signature.getInputsCount());
      assertEquals(1, signature.getOutputsCount());

      TensorInfo inputInfo = signature.getInputsMap().get("input");
      assertNotNull(inputInfo);
      assertEquals(xyShape.numDimensions(), inputInfo.getTensorShape().getDimCount());
      for (int i = 0; i < xyShape.numDimensions(); ++i) {
        assertEquals(xyShape.size(i), inputInfo.getTensorShape().getDim(i).getSize());
      }

      TensorInfo outputInfo = signature.getOutputsMap().get("reducedSum");
      assertNotNull(outputInfo);
      assertEquals(0, outputInfo.getTensorShape().getDimCount());

      FunctionGraph function = savedModel.function(FunctionGraph.DEFAULT_NAME);
      assertNotNull(function);
      assertEquals(1, function.inputNames().size());
      assertEquals("input", function.inputNames().iterator().next());
      assertEquals(1, function.outputNames().size());
      assertEquals("reducedSum", function.outputNames().iterator().next());

      // Call the saved model function and make sure it returns the same result as before
      try (Tensor<TFloat32> xTensor = TFloat32.tensorOf(xValue);
           Tensor<TFloat32> zTensor = function.call(xTensor).expect(TFloat32.DTYPE)) {
        assertEquals(reducedSum, zTensor.data().getFloat(), EPSILON);
      }
    }
  }

  private static RunOptions sillyRunOptions() {
    return RunOptions.newBuilder()
        .setTraceLevel(RunOptions.TraceLevel.FULL_TRACE)
        .build();
  }

  public static ConfigProto sillyConfigProto() {
    return ConfigProto.newBuilder()
        .setInterOpParallelismThreads(1)
        .setIntraOpParallelismThreads(1)
        .build();
  }
}
