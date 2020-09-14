/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.optimizers.schedules;;

/**
 * A LearningRateSchedule that uses a piecewise constant decay schedule.
 * <p>
 * <p>The function computes the piecewise constant
  when passed the current optimizer step. This can be useful for changing the
  learning rate value across different invocations of optimizer functions.
 * <p>
 * <p>Example: use a learning rate that's 1.0 for the first 100001 steps, 0.5
    for the next 10000 steps, and 0.1 for any additional steps.
 */
public class PiecewiseConstantDecay implements LearningRateSchedule  {
    private float[] boundaries;
    private float[] values;
    
    private int lastIndex = 0;
    
    /**
     * Create an PiecewiseConstantDecay
     *
     * @param boundaries An array of with strictly increasing entries
     * @param values An array that specifies the
        values for the intervals defined by <code>boundaries</code>. It should have one
        more element than <code>boundaries</code>.
     * @throws java.lang.IllegalArgumentException if the the length of values does not have 1 more element than boundaries.
     */
    public PiecewiseConstantDecay(float[] boundaries, float[] values) {
        if(boundaries.length != values.length - 1) {
            throw new IllegalArgumentException("The length of boundaries should be 1 less than the length of values");
        }
        this.boundaries = boundaries;
        this.values = values;
    }


    @Override
    public float call(int step) {
        if(lastIndex < boundaries.length && step > boundaries[lastIndex])
                lastIndex++;
        return values[lastIndex];
    }
    
}
