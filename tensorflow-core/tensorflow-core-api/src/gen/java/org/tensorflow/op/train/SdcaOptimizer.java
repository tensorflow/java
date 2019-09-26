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
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;

/**
 * Distributed version of Stochastic Dual Coordinate Ascent (SDCA) optimizer for
 * <p>
 * linear models with L1 + L2 regularization. As global optimization objective is
 * strongly-convex, the optimizer optimizes the dual objective at each step. The
 * optimizer applies each update one example at a time. Examples are sampled
 * uniformly, and the optimizer is learning rate free and enjoys linear convergence
 * rate.
 * <p>
 * [Proximal Stochastic Dual Coordinate Ascent](http://arxiv.org/pdf/1211.2717v1.pdf).<br>
 * Shai Shalev-Shwartz, Tong Zhang. 2012
 * <p>
 * $$Loss Objective = \sum f_{i} (wx_{i}) + (l2 / 2) * |w|^2 + l1 * |w|$$
 * <p>
 * [Adding vs. Averaging in Distributed Primal-Dual Optimization](http://arxiv.org/abs/1502.03508).<br>
 * Chenxin Ma, Virginia Smith, Martin Jaggi, Michael I. Jordan,
 * Peter Richtarik, Martin Takac. 2015
 * <p>
 * [Stochastic Dual Coordinate Ascent with Adaptive Probabilities](https://arxiv.org/abs/1502.08053).<br>
 * Dominik Csiba, Zheng Qu, Peter Richtarik. 2015
 */
public final class SdcaOptimizer extends PrimitiveOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.SdcaOptimizer}
   */
  public static class Options {
    
    /**
     * @param adaptive Whether to use Adaptive SDCA for the inner loop.
     */
    public Options adaptive(Boolean adaptive) {
      this.adaptive = adaptive;
      return this;
    }
    
    private Boolean adaptive;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SdcaOptimizer operation.
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
   * @param options carries optional attributes values
   * @return a new instance of SdcaOptimizer
   */
  public static SdcaOptimizer create(Scope scope, Iterable<Operand<Long>> sparseExampleIndices, Iterable<Operand<Long>> sparseFeatureIndices, Iterable<Operand<Float>> sparseFeatureValues, Iterable<Operand<Float>> denseFeatures, Operand<Float> exampleWeights, Operand<Float> exampleLabels, Iterable<Operand<Long>> sparseIndices, Iterable<Operand<Float>> sparseWeights, Iterable<Operand<Float>> denseWeights, Operand<Float> exampleStateData, String lossType, Float l1, Float l2, Long numLossPartitions, Long numInnerIterations, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SdcaOptimizerV2", scope.makeOpName("SdcaOptimizer"));
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
    opBuilder = scope.applyControlDependencies(opBuilder);
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
   * @param adaptive Whether to use Adaptive SDCA for the inner loop.
   */
  public static Options adaptive(Boolean adaptive) {
    return new Options().adaptive(adaptive);
  }
  
  /**
   * a list of vectors containing the updated example state
   * data.
   */
  public Output<Float> outExampleStateData() {
    return outExampleStateData;
  }
  
  /**
   * a list of vectors where each value is the delta
   * weights associated with a sparse feature group.
   */
  public List<Output<Float>> outDeltaSparseWeights() {
    return outDeltaSparseWeights;
  }
  
  /**
   * a list of vectors where the values are the delta
   * weights associated with a dense feature group.
   */
  public List<Output<Float>> outDeltaDenseWeights() {
    return outDeltaDenseWeights;
  }
  
  private Output<Float> outExampleStateData;
  private List<Output<Float>> outDeltaSparseWeights;
  private List<Output<Float>> outDeltaDenseWeights;
  
  @SuppressWarnings("unchecked")
  private SdcaOptimizer(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outExampleStateData = operation.output(outputIdx++);
    int outDeltaSparseWeightsLength = operation.outputListLength("out_delta_sparse_weights");
    outDeltaSparseWeights = Arrays.asList((Output<Float>[])operation.outputList(outputIdx, outDeltaSparseWeightsLength));
    outputIdx += outDeltaSparseWeightsLength;
    int outDeltaDenseWeightsLength = operation.outputListLength("out_delta_dense_weights");
    outDeltaDenseWeights = Arrays.asList((Output<Float>[])operation.outputList(outputIdx, outDeltaDenseWeightsLength));
    outputIdx += outDeltaDenseWeightsLength;
  }
}
