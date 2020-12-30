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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Locks a mutex resource.  The output is the lock.  So long as the lock tensor
 * <p>
 * is alive, any other request to use `MutexLock` with this mutex will wait.
 * <p>
 * This is particularly useful for creating a critical section when used in
 * conjunction with `MutexLockIdentity`:
 * <pre>{@code
 * mutex = mutex_v2(
 *   shared_name=handle_name, container=container, name=name)
 * 
 * def execute_in_critical_section(fn, *args, **kwargs):
 *   lock = gen_resource_variable_ops.mutex_lock(mutex)
 * 
 *   with ops.control_dependencies([lock]):
 *     r = fn(*args, **kwargs)
 * 
 *   with ops.control_dependencies(nest.flatten(r)):
 *     with ops.colocate_with(mutex):
 *       ensure_lock_exists = mutex_lock_identity(lock)
 * 
 *     # Make sure that if any element of r is accessed, all of
 *     # them are executed together.
 *     r = nest.map_structure(tf.identity, r)
 * 
 *   with ops.control_dependencies([ensure_lock_exists]):
 *     return nest.map_structure(tf.identity, r)
 * }</pre>
 * While `fn` is running in the critical section, no other functions which wish to
 * use this critical section may run.
 * <p>
 * Often the use case is that two executions of the same graph, in parallel,
 * wish to run `fn`; and we wish to ensure that only one of them executes
 * at a time.  This is especially important if `fn` modifies one or more
 * variables at a time.
 * <p>
 * It is also useful if two separate functions must share a resource, but we
 * wish to ensure the usage is exclusive.
 */
@Operator
public final class MutexLock extends RawOp implements Operand<TType> {
  
  /**
   * Factory method to create a class wrapping a new MutexLock operation.
   * 
   * @param scope current scope
   * @param mutex The mutex resource to lock.
   * @return a new instance of MutexLock
   */
  @Endpoint(describeByClass = true)
  public static MutexLock create(Scope scope, Operand<?> mutex) {
    OperationBuilder opBuilder = scope.env().opBuilder("MutexLock", scope.makeOpName("MutexLock"));
    opBuilder.addInput(mutex.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new MutexLock(opBuilder.build());
  }
  
  /**
   * A tensor that keeps a shared pointer to a lock on the mutex;
   * when the Tensor is destroyed, the use count on the shared pointer is decreased
   * by 1.  When it reaches 0, the lock is released.
   */
  public Output<?> mutexLock() {
    return mutexLock;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) mutexLock;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MutexLock";
  
  private Output<?> mutexLock;
  
  private MutexLock(Operation operation) {
    super(operation);
    int outputIdx = 0;
    mutexLock = operation.output(outputIdx++);
  }
}
