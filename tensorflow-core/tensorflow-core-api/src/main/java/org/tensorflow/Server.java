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
==============================================================================*/

package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_DeleteServer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NewServer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_ServerJoin;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_ServerStart;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_ServerStop;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Server;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.proto.distruntime.ServerDef;

/**
 * An in-process TensorFlow server, for use in distributed training.
 *
 * <p>A {@code Server} instance encapsulates a set of devices and a {@link org.tensorflow.Session}
 * target that can participate in distributed training. A server belongs to a cluster (specified by
 * a {@code ClusterSpec}), and corresponds to a particular task in a named job. The server can
 * communicate with any other server in the same cluster. The server will not serve any requests
 * until {@link #start()} is invoked. The server will stop serving requests once {@link #stop()} or
 * {@link #close()} is invoked. Be aware that {@link #close()} method stops the server if it is
 * running.
 *
 * <p><b>WARNING:</b> A {@code Server} owns resources that <b>must</b> be explicitly freed by
 * invoking {@link #close()}.
 *
 * <p>Instances of a {@code Server} are thread-safe.
 *
 * <p>Using example:
 *
 * <pre>{@code
 * import org.tensorflow.Server;
 * import org.tensorflow.distruntime.ClusterDef;
 * import org.tensorflow.distruntime.JobDef;
 * import org.tensorflow.distruntime.ServerDef;
 *
 * ClusterDef clusterDef = ClusterDef.newBuilder()
 *   .addJob(JobDef.newBuilder()
 *   .setName("worker")
 *   .putTasks(0, "localhost:4321")
 *   .build()
 * ).build();
 *
 * ServerDef serverDef = ServerDef.newBuilder()
 *   .setCluster(clusterDef)
 *   .setJobName("worker")
 *   .setTaskIndex(0)
 *   .setProtocol("grpc")
 * .build();
 *
 * try (Server srv = new Server(serverDef)) {
 *   srv.start();
 *   srv.join();
 * }
 * }</pre>
 */
public final class Server implements AutoCloseable {
  /**
   * Constructs a new instance of server.
   *
   * @param serverDef Server definition specified as a <a
   *     href="https://github.com/tensorflow/tensorflow/blob/master/tensorflow/core/protobuf/tensorflow_server.proto">ServerDef</a>
   *     protocol buffer.
   */
  public Server(ServerDef serverDef) {
    nativeHandle = allocate(serverDef);
  }

  /** Starts an in-process TensorFlow server. */
  public synchronized void start() {
    start(nativeHandle);
  }

  /** Stops an in-process TensorFlow server. */
  public synchronized void stop() {
    stop(nativeHandle);
  }

  /** Blocks until the server has been successfully stopped. */
  public void join() {
    TF_Server handle = null;
    synchronized (this) {
      handle = nativeHandle;
      if (handle != null && !handle.isNull()) {
        numJoining++;
      }
    }
    try {
      join(handle);
    } finally {
      synchronized (this) {
        if (handle != null && !handle.isNull()) {
          numJoining--;
        }
        notifyAll();
      }
    }
  }

  /** Destroy an in-process TensorFlow server, frees memory. */
  @Override
  public synchronized void close() throws InterruptedException {
    stop();
    while (numJoining > 0) {
      wait();
    }
    delete(nativeHandle);
    nativeHandle = null;
  }

  private static void requireHandle(TF_Server handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() has been called on the Server");
    }
  }

  private static TF_Server allocate(ServerDef serverDef) {
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      BytePointer serverDefBytes = new BytePointer(serverDef.toByteArray());
      TF_Server server = TF_NewServer(serverDefBytes, serverDefBytes.capacity(), status);
      status.throwExceptionIfNotOK();
      return server;
    }
  }

  private static void start(TF_Server nativeHandle) {
    requireHandle(nativeHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_ServerStart(nativeHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void stop(TF_Server nativeHandle) {
    requireHandle(nativeHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_ServerStop(nativeHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void join(TF_Server nativeHandle) {
    requireHandle(nativeHandle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_ServerJoin(nativeHandle, status);
      status.throwExceptionIfNotOK();
    }
  }

  private static void delete(TF_Server nativeHandle) {
    requireHandle(nativeHandle);
    TF_DeleteServer(nativeHandle);
  }

  private TF_Server nativeHandle;

  private int numJoining;

  static {
    try {
      // Ensure that TensorFlow native library and classes are ready to be used
      Class.forName("org.tensorflow.TensorFlow");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
