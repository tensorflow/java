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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;

/**
 * Metadata indicating how the TPU computation should be replicated.
 * This operation holds the metadata common to operations of a {@code tpu.replicate()} computation subgraph.
 */
public final class ReplicateMetadata extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUReplicateMetadata";

  private ReplicateMetadata(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new TPUReplicateMetadata operation.
   *
   * @param scope current scope
   * @param numReplicas Number of replicas of the computation
   * @param options carries optional attribute values
   * @return a new instance of ReplicateMetadata
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReplicateMetadata create(Scope scope, Long numReplicas, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("TPUReplicateMetadata", scope.makeOpName("ReplicateMetadata"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("num_replicas", numReplicas);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numCoresPerReplica != null) {
          opBuilder.setAttr("num_cores_per_replica", opts.numCoresPerReplica);
        }
        if (opts.topology != null) {
          opBuilder.setAttr("topology", opts.topology);
        }
        if (opts.useTpu != null) {
          opBuilder.setAttr("use_tpu", opts.useTpu);
        }
        if (opts.deviceAssignment != null) {
          long[] deviceAssignmentArray = new long[opts.deviceAssignment.size()];
          for (int i = 0 ; i < deviceAssignmentArray.length ; i++) {
            deviceAssignmentArray[i] = opts.deviceAssignment.get(i);
          }
          opBuilder.setAttr("device_assignment", deviceAssignmentArray);
        }
        if (opts.computationShape != null) {
          long[] computationShapeArray = new long[opts.computationShape.size()];
          for (int i = 0 ; i < computationShapeArray.length ; i++) {
            computationShapeArray[i] = opts.computationShape.get(i);
          }
          opBuilder.setAttr("computation_shape", computationShapeArray);
        }
        if (opts.hostComputeCore != null) {
          String[] hostComputeCoreArray = new String[opts.hostComputeCore.size()];
          for (int i = 0 ; i < hostComputeCoreArray.length ; i++) {
            hostComputeCoreArray[i] = opts.hostComputeCore.get(i);
          }
          opBuilder.setAttr("host_compute_core", hostComputeCoreArray);
        }
        if (opts.paddingMap != null) {
          String[] paddingMapArray = new String[opts.paddingMap.size()];
          for (int i = 0 ; i < paddingMapArray.length ; i++) {
            paddingMapArray[i] = opts.paddingMap.get(i);
          }
          opBuilder.setAttr("padding_map", paddingMapArray);
        }
        if (opts.stepMarkerLocation != null) {
          opBuilder.setAttr("step_marker_location", opts.stepMarkerLocation);
        }
        if (opts.allowSoftPlacement != null) {
          opBuilder.setAttr("allow_soft_placement", opts.allowSoftPlacement);
        }
        if (opts.useSpmdForXlaPartitioning != null) {
          opBuilder.setAttr("use_spmd_for_xla_partitioning", opts.useSpmdForXlaPartitioning);
        }
      }
    }
    return new ReplicateMetadata(opBuilder.build());
  }

  /**
   * Sets the numCoresPerReplica option.
   *
   * @param numCoresPerReplica Number of cores per replica. Used for model parallelism.
   * @return this Options instance.
   */
  public static Options numCoresPerReplica(Long numCoresPerReplica) {
    return new Options().numCoresPerReplica(numCoresPerReplica);
  }

  /**
   * Sets the topology option.
   *
   * @param topology TopologyProto indicating the topology of the TPU pod slice.
   * @return this Options instance.
   */
  public static Options topology(String topology) {
    return new Options().topology(topology);
  }

  /**
   * Sets the useTpu option.
   *
   * @param useTpu Whether to place the computation on the TPU.
   * @return this Options instance.
   */
  public static Options useTpu(Boolean useTpu) {
    return new Options().useTpu(useTpu);
  }

  /**
   * Sets the deviceAssignment option.
   *
   * @param deviceAssignment The assignment of devices for the computation.
   * @return this Options instance.
   */
  public static Options deviceAssignment(List<Long> deviceAssignment) {
    return new Options().deviceAssignment(deviceAssignment);
  }

  /**
   * Sets the deviceAssignment option.
   *
   * @param deviceAssignment The assignment of devices for the computation.
   * @return this Options instance.
   */
  public static Options deviceAssignment(Long[] deviceAssignment) {
    return new Options().deviceAssignment(deviceAssignment);
  }

  /**
   * Sets the computationShape option.
   *
   * @param computationShape DEPRECATED. Use num_cores_per_replica instead.
   * @return this Options instance.
   */
  public static Options computationShape(List<Long> computationShape) {
    return new Options().computationShape(computationShape);
  }

  /**
   * Sets the computationShape option.
   *
   * @param computationShape DEPRECATED. Use num_cores_per_replica instead.
   * @return this Options instance.
   */
  public static Options computationShape(Long[] computationShape) {
    return new Options().computationShape(computationShape);
  }

  /**
   * Sets the hostComputeCore option.
   *
   * @param hostComputeCore the hostComputeCore option
   * @return this Options instance.
   */
  public static Options hostComputeCore(List<String> hostComputeCore) {
    return new Options().hostComputeCore(hostComputeCore);
  }

  /**
   * Sets the hostComputeCore option.
   *
   * @param hostComputeCore the hostComputeCore option
   * @return this Options instance.
   */
  public static Options hostComputeCore(String[] hostComputeCore) {
    return new Options().hostComputeCore(hostComputeCore);
  }

  /**
   * Sets the paddingMap option.
   *
   * @param paddingMap the paddingMap option
   * @return this Options instance.
   */
  public static Options paddingMap(List<String> paddingMap) {
    return new Options().paddingMap(paddingMap);
  }

  /**
   * Sets the paddingMap option.
   *
   * @param paddingMap the paddingMap option
   * @return this Options instance.
   */
  public static Options paddingMap(String[] paddingMap) {
    return new Options().paddingMap(paddingMap);
  }

  /**
   * Sets the stepMarkerLocation option.
   *
   * @param stepMarkerLocation the stepMarkerLocation option
   * @return this Options instance.
   */
  public static Options stepMarkerLocation(String stepMarkerLocation) {
    return new Options().stepMarkerLocation(stepMarkerLocation);
  }

  /**
   * Sets the allowSoftPlacement option.
   *
   * @param allowSoftPlacement the allowSoftPlacement option
   * @return this Options instance.
   */
  public static Options allowSoftPlacement(Boolean allowSoftPlacement) {
    return new Options().allowSoftPlacement(allowSoftPlacement);
  }

  /**
   * Sets the useSpmdForXlaPartitioning option.
   *
   * @param useSpmdForXlaPartitioning the useSpmdForXlaPartitioning option
   * @return this Options instance.
   */
  public static Options useSpmdForXlaPartitioning(Boolean useSpmdForXlaPartitioning) {
    return new Options().useSpmdForXlaPartitioning(useSpmdForXlaPartitioning);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.tpu.ReplicateMetadata}
   */
  public static class Options {
    private Long numCoresPerReplica;

    private String topology;

    private Boolean useTpu;

    private List<Long> deviceAssignment;

    private List<Long> computationShape;

    private List<String> hostComputeCore;

    private List<String> paddingMap;

    private String stepMarkerLocation;

    private Boolean allowSoftPlacement;

    private Boolean useSpmdForXlaPartitioning;

    private Options() {
    }

    /**
     * Sets the numCoresPerReplica option.
     *
     * @param numCoresPerReplica Number of cores per replica. Used for model parallelism.
     * @return this Options instance.
     */
    public Options numCoresPerReplica(Long numCoresPerReplica) {
      this.numCoresPerReplica = numCoresPerReplica;
      return this;
    }

    /**
     * Sets the topology option.
     *
     * @param topology TopologyProto indicating the topology of the TPU pod slice.
     * @return this Options instance.
     */
    public Options topology(String topology) {
      this.topology = topology;
      return this;
    }

    /**
     * Sets the useTpu option.
     *
     * @param useTpu Whether to place the computation on the TPU.
     * @return this Options instance.
     */
    public Options useTpu(Boolean useTpu) {
      this.useTpu = useTpu;
      return this;
    }

    /**
     * Sets the deviceAssignment option.
     *
     * @param deviceAssignment The assignment of devices for the computation.
     * @return this Options instance.
     */
    public Options deviceAssignment(List<Long> deviceAssignment) {
      this.deviceAssignment = deviceAssignment;
      return this;
    }

    /**
     * Sets the deviceAssignment option.
     *
     * @param deviceAssignment The assignment of devices for the computation.
     * @return this Options instance.
     */
    public Options deviceAssignment(Long... deviceAssignment) {
      this.deviceAssignment = Arrays.asList(deviceAssignment);
      return this;
    }

    /**
     * Sets the computationShape option.
     *
     * @param computationShape DEPRECATED. Use num_cores_per_replica instead.
     * @return this Options instance.
     */
    public Options computationShape(List<Long> computationShape) {
      this.computationShape = computationShape;
      return this;
    }

    /**
     * Sets the computationShape option.
     *
     * @param computationShape DEPRECATED. Use num_cores_per_replica instead.
     * @return this Options instance.
     */
    public Options computationShape(Long... computationShape) {
      this.computationShape = Arrays.asList(computationShape);
      return this;
    }

    /**
     * Sets the hostComputeCore option.
     *
     * @param hostComputeCore the hostComputeCore option
     * @return this Options instance.
     */
    public Options hostComputeCore(List<String> hostComputeCore) {
      this.hostComputeCore = hostComputeCore;
      return this;
    }

    /**
     * Sets the hostComputeCore option.
     *
     * @param hostComputeCore the hostComputeCore option
     * @return this Options instance.
     */
    public Options hostComputeCore(String... hostComputeCore) {
      this.hostComputeCore = Arrays.asList(hostComputeCore);
      return this;
    }

    /**
     * Sets the paddingMap option.
     *
     * @param paddingMap the paddingMap option
     * @return this Options instance.
     */
    public Options paddingMap(List<String> paddingMap) {
      this.paddingMap = paddingMap;
      return this;
    }

    /**
     * Sets the paddingMap option.
     *
     * @param paddingMap the paddingMap option
     * @return this Options instance.
     */
    public Options paddingMap(String... paddingMap) {
      this.paddingMap = Arrays.asList(paddingMap);
      return this;
    }

    /**
     * Sets the stepMarkerLocation option.
     *
     * @param stepMarkerLocation the stepMarkerLocation option
     * @return this Options instance.
     */
    public Options stepMarkerLocation(String stepMarkerLocation) {
      this.stepMarkerLocation = stepMarkerLocation;
      return this;
    }

    /**
     * Sets the allowSoftPlacement option.
     *
     * @param allowSoftPlacement the allowSoftPlacement option
     * @return this Options instance.
     */
    public Options allowSoftPlacement(Boolean allowSoftPlacement) {
      this.allowSoftPlacement = allowSoftPlacement;
      return this;
    }

    /**
     * Sets the useSpmdForXlaPartitioning option.
     *
     * @param useSpmdForXlaPartitioning the useSpmdForXlaPartitioning option
     * @return this Options instance.
     */
    public Options useSpmdForXlaPartitioning(Boolean useSpmdForXlaPartitioning) {
      this.useSpmdForXlaPartitioning = useSpmdForXlaPartitioning;
      return this;
    }
  }
}
