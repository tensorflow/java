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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Identity;
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
  private static final String SAVED_MODEL_PY_PATH;

  static {
    try {
      SAVED_MODEL_PATH = Paths.get(SavedModelBundleTest.class.getResource("/saved_model").toURI()).toString();
      SAVED_MODEL_PY_PATH = Paths.get(SavedModelBundleTest.class.getResource("/saved_model_using_python/model").toURI()).toString();
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
  public void exportFunctionWithVariables() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-saved-model-export-test");
    float reducedSum;
    FloatNdArray xValue = StdArrays.ndCopyOf(new float[][]{{0, 1, 2}, {3, 4, 5}});
    Shape xyShape = Shape.of(2, 3L);
    try (ConcreteFunction f = ConcreteFunction.create(tf -> buildGraphWithVariables(tf, xyShape))) {
      // Init variable state by running the Init operation directly
      f.session().run(Init.DEFAULT_NAME);

      // Call the graph and remember the result of computation for later
      try (Tensor<TFloat32> xTensor = TFloat32.tensorOf(xValue);
          Tensor<TFloat32> zTensor = f.call(xTensor).expect(TFloat32.DTYPE)) {
        reducedSum = zTensor.data().getFloat();
      }
      // Save/export the model (which is a single function in this case)
      f.save(testFolder.toString());
    }
    assertTrue(Files.exists(testFolder.resolve(Paths.get("variables", "variables.index"))));
    assertTrue(Files
        .exists(testFolder.resolve(Paths.get("variables", "variables.data-00000-of-00001"))));
    assertTrue(Files.exists(testFolder.resolve("saved_model.pb")));

    // Reload the model just saved and validate its data
    try (SavedModelBundle savedModel =
        SavedModelBundle.load(testFolder.toString(), SavedModelBundle.DEFAULT_TAG)) {
      assertNotNull(savedModel.metaGraphDef());
      assertNotNull(savedModel.metaGraphDef().getSaverDef());
      assertEquals(1, savedModel.metaGraphDef().getSignatureDefCount());
      assertEquals(Signature.DEFAULT_KEY,
          savedModel.metaGraphDef().getSignatureDefMap().keySet().iterator().next());

      ConcreteFunction function = savedModel.function(Signature.DEFAULT_KEY);
      assertNotNull(function);

      Signature signature = function.signature();
      assertNotNull(signature);
      assertEquals(1, signature.inputNames().size());
      assertEquals("input", signature.inputNames().iterator().next());
      assertEquals(1, signature.outputNames().size());
      assertEquals("reducedSum", signature.outputNames().iterator().next());

      SignatureDef signatureDef = signature.asSignatureDef();
      assertEquals(1, signatureDef.getInputsCount());
      assertEquals(1, signatureDef.getOutputsCount());

      TensorInfo inputInfo = signatureDef.getInputsMap().get("input");
      assertNotNull(inputInfo);
      assertEquals(xyShape.numDimensions(), inputInfo.getTensorShape().getDimCount());
      for (int i = 0; i < xyShape.numDimensions(); ++i) {
        assertEquals(xyShape.size(i), inputInfo.getTensorShape().getDim(i).getSize());
      }

      TensorInfo outputInfo = signatureDef.getOutputsMap().get("reducedSum");
      assertNotNull(outputInfo);
      assertEquals(0, outputInfo.getTensorShape().getDimCount());

      try (Tensor<TFloat32> xTensor = TFloat32.tensorOf(xValue)) {
        // Call the saved model function and make sure it returns the same result as before
        try (Tensor<TFloat32> zTensor = function.call(xTensor).expect(TFloat32.DTYPE)) {
          assertEquals(reducedSum, zTensor.data().getFloat(), EPSILON);
        }
        // Now call the same function directly from the model
        try (Tensor<TFloat32> zTensor =
            savedModel.call(Collections.singletonMap("input", xTensor)).get("reducedSum").expect(TFloat32.DTYPE)) {
          assertEquals(reducedSum, zTensor.data().getFloat(), EPSILON);
        }
      }
    }
  }

  @Test
  public void exportMultipleFunctions() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-saved-model-export-test");
    float reducedSum;
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Signature f1Signature = buildGraphWithVariables(tf, Shape.of(1, 1));
      Signature f2Signature = buildIdentityGraph(tf, "identity");
      try (Session s = new Session(g);
          ConcreteFunction f1 = ConcreteFunction.create(f1Signature, s);
          ConcreteFunction f2 = ConcreteFunction.create(f2Signature, s)) {
        f1.session().run(Init.DEFAULT_NAME);
        try (Tensor<TFloat32> x = TFloat32.tensorOf(StdArrays.ndCopyOf(new float[]{2, 2}));
            Tensor<TFloat32> t = f1.call(x).expect(TFloat32.DTYPE)) {
          reducedSum = t.data().getFloat();
        }
        SavedModelBundle.exporter(testFolder.toString())
            .withFunction(f1)
            .withFunction(f2)
            .export();
      }
    }
    try (SavedModelBundle model = SavedModelBundle.load(testFolder.toString())) {
      assertEquals(2, model.signatures().size());
      ConcreteFunction f1 = model.function(Signature.DEFAULT_KEY);
      assertNotNull(f1);
      try (Tensor<TFloat32> x = TFloat32.tensorOf(StdArrays.ndCopyOf(new float[]{2, 2}));
          Tensor<TFloat32> t = f1.call(x).expect(TFloat32.DTYPE)) {
        assertEquals(reducedSum, t.data().getFloat(), EPSILON);
      }
      ConcreteFunction f2 = model.function("identity");
      assertNotNull(f2);
      try (Tensor<TFloat32> x = TFloat32.scalarOf(10.0f);
          Tensor<TFloat32> t = f2.call(x).expect(TFloat32.DTYPE)) {
        assertEquals(10.0f, t.data().getFloat(), 0.0f);
      }
      try {
        model.function("NoSuchFunction");
        fail();
      } catch (IllegalArgumentException e) {
        // as expected
      }
    }
  }

  @Test
  public void cannotExportMultipleFunctionsWithDifferentSessions() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-saved-model-export-test");
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Signature f1Signature = buildGraphWithVariables(tf, Shape.of(1, 1));
      Signature f2Signature = buildIdentityGraph(tf, "identity");
      try (ConcreteFunction f1 = ConcreteFunction.create(f1Signature, g);
          ConcreteFunction f2 = ConcreteFunction.create(f2Signature, g)) {
        f1.session().run(Init.DEFAULT_NAME);
        try {
          SavedModelBundle.exporter(testFolder.toString())
              .withFunction(f1)
              .withFunction(f2)
              .export();
          fail();
        } catch (UnsupportedOperationException e) {
          // as expected
        }
      }
    }
  }

  @Test
  public void cannotExportMultipleFunctionsWithSameSignatureKey() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-saved-model-export-test");
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Signature f1Signature = buildGraphWithVariables(tf, Shape.of(1, 1));
      Signature f2Signature = buildIdentityGraph(tf, Signature.DEFAULT_KEY);
      try (Session s = new Session(g);
          ConcreteFunction f1 = ConcreteFunction.create(f1Signature, s);
          ConcreteFunction f2 = ConcreteFunction.create(f2Signature, s)) {
        f1.session().run(Init.DEFAULT_NAME);
        try {
          SavedModelBundle.exporter(testFolder.toString())
              .withFunction(f1)
              .withFunction(f2)
              .export();
          fail();
        } catch (IllegalArgumentException e) {
          // as expected
        }
      }
    }
  }

  @Test
  public void cannotExportOrImportInvalidTags() {
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.loader("/").withTags(null)
    );
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.loader("/").withTags(new String[]{"tag", null})
    );
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.loader("/").withTags(new String[]{"tag", ""})
    );
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.exporter("/").withTags(null)
    );
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.exporter("/").withTags(new String[]{"tag", null})
    );
    assertThrows(IllegalArgumentException.class, () ->
        SavedModelBundle.exporter("/").withTags(new String[]{"tag", ""})
    );
  }

  @Test
  public void pythonTfFunction() {
    // ConcreteFunctions on models saved using python
    try (SavedModelBundle bundle = SavedModelBundle.load(SAVED_MODEL_PY_PATH, "serve")) {
      /*
       * Test model was created in python
       *   Signature name used for saving 'add', argument names 'a' and 'b'
       */
      ConcreteFunction add = bundle.function("add");
      Map<String, Tensor<?>> args = new HashMap();
      try (Tensor<TFloat32> a = TFloat32.scalarOf(10.0f);
           Tensor<TFloat32> b = TFloat32.scalarOf(15.5f)) {
        args.put("a", a);
        args.put("b", b);
        Map<String, Tensor<?>> result = add.call(args);
        assertEquals(result.size(), 1);
        try (Tensor<TFloat32> c = result.values().iterator().next().expect(TFloat32.DTYPE)) {
          assertEquals(25.5f, c.data().getFloat());
        }
      }
    }
  }

  private static Signature buildGraphWithVariables(Ops tf, Shape xShape) {
    Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE, Placeholder.shape(xShape));
    Variable<TFloat32> y = tf
        .variable(tf.random.randomUniform(tf.constant(xShape), TFloat32.DTYPE));
    ReduceSum<TFloat32> z = tf.reduceSum(tf.math.add(x, y), tf.array(0, 1));
    Init init = tf.init();
    return Signature.builder().input("input", x).output("reducedSum", z).build();
  }

  private static Signature buildIdentityGraph(Ops tf, String signatureKey) {
    Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    Identity<TFloat32> xprime = tf.identity(x);
    return Signature.builder().key(signatureKey).input("x", x).output("x", xprime).build();
  }

  private static RunOptions sillyRunOptions() {
    return RunOptions.newBuilder()
        .setTraceLevel(RunOptions.TraceLevel.FULL_TRACE)
        .build();
  }

  private static ConfigProto sillyConfigProto() {
    return ConfigProto.newBuilder()
        .setInterOpParallelismThreads(1)
        .setIntraOpParallelismThreads(1)
        .build();
  }
}
