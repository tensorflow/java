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

package org.tensorflow.op.data;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TInt64;

/**
 * Creates a dataset that shuffles elements from `input_dataset` pseudorandomly.
 */
public final class ShuffleDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.ShuffleDataset}
   */
  public static class Options {
    
    /**
     * @param reshuffleEachIteration If true, each iterator over this dataset will be given
     * a different pseudorandomly generated seed, based on a sequence seeded by the
     * `seed` and `seed2` inputs. If false, each iterator will be given the same
     * seed, and repeated iteration over this dataset will yield the exact same
     * sequence of results.
     */
    public Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
      this.reshuffleEachIteration = reshuffleEachIteration;
      return this;
    }
    
    private Boolean reshuffleEachIteration;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ShuffleDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param bufferSize The number of output elements to buffer in an iterator over
   * this dataset. Compare with the `min_after_dequeue` attr when creating a
   * `RandomShuffleQueue`.
   * @param seed A scalar seed for the random number generator. If either `seed` or
   * `seed2` is set to be non-zero, the random number generator is seeded
   * by the given seed.  Otherwise, a random seed is used.
   * @param seed2 A second scalar seed to avoid seed collision.
   * @param outputTypes 
   * @param outputShapes 
   * @param options carries optional attributes values
   * @return a new instance of ShuffleDataset
   */
  public static ShuffleDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2, List<DataType<?>> outputTypes, List<Shape> outputShapes, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ShuffleDataset", scope.makeOpName("ShuffleDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(bufferSize.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(seed2.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    if (options != null) {
      for (Options opts : options) {
        if (opts.reshuffleEachIteration != null) {
          opBuilder.setAttr("reshuffle_each_iteration", opts.reshuffleEachIteration);
        }
      }
    }
    return new ShuffleDataset(opBuilder.build());
  }
  
  /**
   * @param reshuffleEachIteration If true, each iterator over this dataset will be given
   * a different pseudorandomly generated seed, based on a sequence seeded by the
   * `seed` and `seed2` inputs. If false, each iterator will be given the same
   * seed, and repeated iteration over this dataset will yield the exact same
   * sequence of results.
   */
  public static Options reshuffleEachIteration(Boolean reshuffleEachIteration) {
    return new Options().reshuffleEachIteration(reshuffleEachIteration);
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  private ShuffleDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
