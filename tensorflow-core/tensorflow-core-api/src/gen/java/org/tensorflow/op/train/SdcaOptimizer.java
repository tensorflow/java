/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.train;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;

/**
 * Distributed version of Stochastic Dual Coordinate Ascent (SDCA) optimizer for
 * linear models with L1 + L2 regularization. As global optimization objective is
 * strongly-convex, the optimizer optimizes the dual objective at each step. The
 * optimizer applies each update one example at a time. Examples are sampled
 * uniformly, and the optimizer is learning rate free and enjoys linear convergence
 * rate.
 * <p> <a href="http://arxiv.org/pdf/1211.2717v1.pdf">Proximal Stochastic Dual Coordinate Ascent</a> .<br>
 * Shai Shalev-Shwartz, Tong Zhang. 2012
 * <p>$$Loss Objective = \sum f_{i} (wx_{i}) + (l2 / 2) * |w|^2 + l1 * |w|$$
 * <p> <a href="http://arxiv.org/abs/1502.03508">Adding vs. Averaging in Distributed Primal-Dual Optimization</a> .<br>
 * Chenxin Ma, Virginia Smith, Martin Jaggi, Michael I. Jordan,
 * Peter Richtarik, Martin Takac. 2015
 * <p> <a href="https://arxiv.org/abs/1502.08053">Stochastic Dual Coordinate Ascent with Adaptive Probabilities</a> .<br>
 * Dominik Csiba, Zheng Qu, Peter Richtarik. 2015
 */
@OpMetadata(
    opType = SdcaOptimizer.OP_NAME,
    inputsClass = SdcaOptimizer.Inputs.class
)
public final class SdcaOptimizer extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SdcaOptimizerV2";

  private Output<TFloat32> outExampleStateData;

  private List<Output<TFloat32>> outDeltaSparseWeights;

  private List<Output<TFloat32>> outDeltaDenseWeights;

  @SuppressWarnings("unchecked")
  public SdcaOptimizer(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outExampleStateData = operation.output(outputIdx++);
    int outDeltaSparseWeightsLength = operation.outputListLength("out_delta_sparse_weights");
    outDeltaSparseWeights = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, outDeltaSparseWeightsLength));
    outputIdx += outDeltaSparseWeightsLength;
    int outDeltaDenseWeightsLength = operation.outputListLength("out_delta_dense_weights");
    outDeltaDenseWeights = Arrays.asList((Output<TFloat32>[]) operation.outputList(outputIdx, outDeltaDenseWeightsLength));
    outputIdx += outDeltaDenseWeightsLength;
  }

  /**
   * Factory method to create a class wrapping a new SdcaOptimizerV2 operation.
   *
   * @param scope current scope
   * @param sparseExampleIndices a list of vectors which contain example indices.
   * @param sparseFeatureIndices a list of vectors which contain feature indices.
   * @param sparseFeatureValues a list of vectors which contains feature value
   * associated with each feature group.
   * @param denseFeatures a list of matrices which contains the dense feature values.
   * @param exampleWeights a vector which contains the weight associated with each
   * example.
   * @param exampleLabels a vector which contains the label/target associated with each
   * example.
   * @param sparseIndices a list of vectors where each value is the indices which has
   * corresponding weights in sparse_weights. This field maybe omitted for the
   * dense approach.
   * @param sparseWeights a list of vectors where each value is the weight associated with
   * a sparse feature group.
   * @param denseWeights a list of vectors where the values are the weights associated
   * with a dense feature group.
   * @param exampleStateData a list of vectors containing the example state data.
   * @param lossType Type of the primal loss. Currently SdcaSolver supports logistic,
   * squared and hinge losses.
   * @param l1 Symmetric l1 regularization strength.
   * @param l2 Symmetric l2 regularization strength.
   * @param numLossPartitions Number of partitions of the global loss function.
   * @param numInnerIterations Number of iterations per mini-batch.
   * @param options carries optional attribute values
   * @return a new instance of SdcaOptimizer
   */
  @Endpoint(
      describeByClass = true
  )
  public static SdcaOptimizer create(Scope scope, Iterable<Operand<TInt64>> sparseExampleIndices,
      Iterable<Operand<TInt64>> sparseFeatureIndices,
      Iterable<Operand<TFloat32>> sparseFeatureValues, Iterable<Operand<TFloat32>> denseFeatures,
      Operand<TFloat32> exampleWeights, Operand<TFloat32> exampleLabels,
      Iterable<Operand<TInt64>> sparseIndices, Iterable<Operand<TFloat32>> sparseWeights,
      Iterable<Operand<TFloat32>> denseWeights, Operand<TFloat32> exampleStateData, String lossType,
      Float l1, Float l2, Long numLossPartitions, Long numInnerIterations, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SdcaOptimizer");
    opBuilder.addInputList(Operands.asOutputs(sparseExampleIndices));
    opBuilder.addInputList(Operands.asOutputs(sparseFeatureIndices));
    opBuilder.addInputList(Operands.asOutputs(sparseFeatureValues));
    opBuilder.addInputList(Operands.asOutputs(denseFeatures));
    opBuilder.addInput(exampleWeights.asOutput());
    opBuilder.addInput(exampleLabels.asOutput());
    opBuilder.addInputList(Operands.asOutputs(sparseIndices));
    opBuilder.addInputList(Operands.asOutputs(sparseWeights));
    opBuilder.addInputList(Operands.asOutputs(denseWeights));
    opBuilder.addInput(exampleStateData.asOutput());
    opBuilder.setAttr("loss_type", lossType);
    opBuilder.setAttr("l1", l1);
    opBuilder.setAttr("l2", l2);
    opBuilder.setAttr("num_loss_partitions", numLossPartitions);
    opBuilder.setAttr("num_inner_iterations", numInnerIterations);
    if (options != null) {
      for (Options opts : options) {
        if (opts.adaptive != null) {
          opBuilder.setAttr("adaptive", opts.adaptive);
        }
      }
    }
    return new SdcaOptimizer(opBuilder.build());
  }

  /**
   * Sets the adaptive option.
   *
   * @param adaptive Whether to use Adaptive SDCA for the inner loop.
   * @return this Options instance.
   */
  public static Options adaptive(Boolean adaptive) {
    return new Options().adaptive(adaptive);
  }

  /**
   * Gets outExampleStateData.
   * a list of vectors containing the updated example state
   * data.
   * @return outExampleStateData.
   */
  public Output<TFloat32> outExampleStateData() {
    return outExampleStateData;
  }

  /**
   * Gets outDeltaSparseWeights.
   * a list of vectors where each value is the delta
   * weights associated with a sparse feature group.
   * @return outDeltaSparseWeights.
   */
  public List<Output<TFloat32>> outDeltaSparseWeights() {
    return outDeltaSparseWeights;
  }

  /**
   * Gets outDeltaDenseWeights.
   * a list of vectors where the values are the delta
   * weights associated with a dense feature group.
   * @return outDeltaDenseWeights.
   */
  public List<Output<TFloat32>> outDeltaDenseWeights() {
    return outDeltaDenseWeights;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.SdcaOptimizer}
   */
  public static class Options {
    private Boolean adaptive;

    private Options() {
    }

    /**
     * Sets the adaptive option.
     *
     * @param adaptive Whether to use Adaptive SDCA for the inner loop.
     * @return this Options instance.
     */
    public Options adaptive(Boolean adaptive) {
      this.adaptive = adaptive;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SdcaOptimizer.class
  )
  public static class Inputs extends RawOpInputs<SdcaOptimizer> {
    /**
     * a list of vectors which contain example indices.
     */
    public final Iterable<Operand<TInt64>> sparseExampleIndices;

    /**
     * a list of vectors which contain feature indices.
     */
    public final Iterable<Operand<TInt64>> sparseFeatureIndices;

    /**
     * a list of vectors which contains feature value
     * associated with each feature group.
     */
    public final Iterable<Operand<TFloat32>> sparseFeatureValues;

    /**
     * a list of matrices which contains the dense feature values.
     */
    public final Iterable<Operand<TFloat32>> denseFeatures;

    /**
     * a vector which contains the weight associated with each
     * example.
     */
    public final Operand<TFloat32> exampleWeights;

    /**
     * a vector which contains the label/target associated with each
     * example.
     */
    public final Operand<TFloat32> exampleLabels;

    /**
     * a list of vectors where each value is the indices which has
     * corresponding weights in sparse_weights. This field maybe omitted for the
     * dense approach.
     */
    public final Iterable<Operand<TInt64>> sparseIndices;

    /**
     * a list of vectors where each value is the weight associated with
     * a sparse feature group.
     */
    public final Iterable<Operand<TFloat32>> sparseWeights;

    /**
     * a list of vectors where the values are the weights associated
     * with a dense feature group.
     */
    public final Iterable<Operand<TFloat32>> denseWeights;

    /**
     * a list of vectors containing the example state data.
     */
    public final Operand<TFloat32> exampleStateData;

    /**
     * Type of the primal loss. Currently SdcaSolver supports logistic,
     * squared and hinge losses.
     */
    public final String lossType;

    /**
     * Whether to use Adaptive SDCA for the inner loop.
     */
    public final boolean adaptive;

    /**
     * Symmetric l1 regularization strength.
     */
    public final float l1;

    /**
     * Symmetric l2 regularization strength.
     */
    public final float l2;

    /**
     * Number of partitions of the global loss function.
     */
    public final long numLossPartitions;

    /**
     * Number of iterations per mini-batch.
     */
    public final long numInnerIterations;

    public Inputs(GraphOperation op) {
      super(new SdcaOptimizer(op), op, Arrays.asList("loss_type", "adaptive", "l1", "l2", "num_loss_partitions", "num_inner_iterations"));
      int inputIndex = 0;
      int sparseExampleIndicesLength = op.inputListLength("sparse_example_indices");
      sparseExampleIndices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, sparseExampleIndicesLength));
      inputIndex += sparseExampleIndicesLength;
      int sparseFeatureIndicesLength = op.inputListLength("sparse_feature_indices");
      sparseFeatureIndices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, sparseFeatureIndicesLength));
      inputIndex += sparseFeatureIndicesLength;
      int sparseFeatureValuesLength = op.inputListLength("sparse_feature_values");
      sparseFeatureValues = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, sparseFeatureValuesLength));
      inputIndex += sparseFeatureValuesLength;
      int denseFeaturesLength = op.inputListLength("dense_features");
      denseFeatures = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, denseFeaturesLength));
      inputIndex += denseFeaturesLength;
      exampleWeights = (Operand<TFloat32>) op.input(inputIndex++);
      exampleLabels = (Operand<TFloat32>) op.input(inputIndex++);
      int sparseIndicesLength = op.inputListLength("sparse_indices");
      sparseIndices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, sparseIndicesLength));
      inputIndex += sparseIndicesLength;
      int sparseWeightsLength = op.inputListLength("sparse_weights");
      sparseWeights = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, sparseWeightsLength));
      inputIndex += sparseWeightsLength;
      int denseWeightsLength = op.inputListLength("dense_weights");
      denseWeights = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, denseWeightsLength));
      inputIndex += denseWeightsLength;
      exampleStateData = (Operand<TFloat32>) op.input(inputIndex++);
      lossType = op.attributes().getAttrString("loss_type");
      adaptive = op.attributes().getAttrBool("adaptive");
      l1 = op.attributes().getAttrFloat("l1");
      l2 = op.attributes().getAttrFloat("l2");
      numLossPartitions = op.attributes().getAttrInt("num_loss_partitions");
      numInnerIterations = op.attributes().getAttrInt("num_inner_iterations");
    }
  }
}
