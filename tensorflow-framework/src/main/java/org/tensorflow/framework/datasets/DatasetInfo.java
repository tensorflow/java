package org.tensorflow.framework.datasets;

import com.google.gson.annotations.Expose;
import org.tensorflow.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

import java.util.Arrays;

public class DatasetInfo {
  @Expose private String name;
  @Expose private String citation;
  @Expose private String description;
  @Expose private long downloadSize;

  @Expose private Schema schema;
  @Expose private SplitInfo[] splits;

  @Expose private String version;

  @Expose private SupervisedKeys supervisedKeys;

  public String getName() {
    return name;
  }

  public String getCitation() {
    return citation;
  }

  public String getDescription() {
    return description;
  }

  public long getDownloadSize() {
    return downloadSize;
  }

  public Schema getSchema() {
    return schema;
  }

  public SplitInfo[] getSplits() {
    return splits;
  }

  public SupervisedKeys getSupervisedKeys() {
    return supervisedKeys;
  }

  public Version getVersion() {
    String[] split = version.split("\\.");
    return new Version(
        Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
  }

  public static class Schema {
    @Expose private Feature[] feature;

    public Feature[] getFeatures() {
      return feature;
    }

    static class Feature {
      @Expose private String name;
      @Expose private Shape shape;
      @Expose private String type;

      public String getName() {
        return name;
      }

      public DataType<?> getDtype() {
        switch (type) {
          case "FLOAT":
            return TFloat32.DTYPE;
          case "DOUBLE":
            return TFloat64.DTYPE;
          case "INT":
            return TInt32.DTYPE;
          case "LONG":
            return TInt64.DTYPE;
        }
      }

      public org.tensorflow.tools.Shape getShape() {
        long[] dims = Arrays.stream(shape.dim).map(d -> d.size).mapToLong(x -> x).toArray();
        return org.tensorflow.tools.Shape.of(dims);
      }

      private static class Shape {
        @Expose private Dim[] dim;

        private static class Dim {
          @Expose private long size;
        }
      }
    }
  }

  public static class SplitInfo {
    @Expose private String name;
    @Expose private long numShards;
    @Expose private long[] shardLengths;
    @Expose private long numBytes;
    @Expose private Statics statistics;

    public String getName() {
      return name;
    }

    public long getNumShards() {
      return numShards;
    }

    public long[] getShardLengths() {
      return shardLengths;
    }

    public long getNumBytes() {
      return numBytes;
    }

    public Statics getStatistics() {
      return statistics;
    }

    static class Statics {
      @Expose private Features[] features;

      public Features[] getFeatures() {
        return features;
      }

      static class Features {
        @Expose private String name;

        public String getName() {
          return name;
        }

        static class NumStats {
          @Expose private CommonStats commonStats;

          public CommonStats getCommonStats() {
            return commonStats;
          }

          static class CommonStats {
            @Expose private long numNonMissing;

            public long getNumNonMissing() {
              return numNonMissing;
            }
          }
        }
      }
    }
  }

  public static class SupervisedKeys {
    @Expose private String input;
    @Expose private String output;

    public String getInput() {
      return input;
    }

    public String getOutput() {
      return output;
    }
  }
}
