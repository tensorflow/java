// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.`data`.experimental.DataServiceDataset
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString

/**
 * An API for building {@code data.experimental} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class DataExperimentalOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.DataExperimentalOps = ops.java.data.experimental

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun dataServiceDataset(
		datasetId: Operand<TInt64>,
		processingMode: Operand<TString>,
		address: Operand<TString>,
		protocol: Operand<TString>,
		jobName: Operand<TString>,
		maxOutstandingRequests: Operand<TInt64>,
		iterationCounter: Operand<*>,
		outputTypes: List<DataType<*>>,
		outputShapes: List<Shape>,
		taskRefreshIntervalHintMs: Long? = null
	): DataServiceDataset = java.dataServiceDataset(	
		datasetId,
		processingMode,
		address,
		protocol,
		jobName,
		maxOutstandingRequests,
		iterationCounter,
		outputTypes,
		outputShapes,
		*listOfNotNull(
			taskRefreshIntervalHintMs?.let{
			org.tensorflow.op.data.experimental.DataServiceDataset.taskRefreshIntervalHintMs(it) }
		).toTypedArray()
		)
}
