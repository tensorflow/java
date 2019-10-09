package org.tensorflow;

import java.util.HashMap;
import java.util.Map;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TDouble;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUInt8;

/**
 * Utility class for working with {@link DataType} objects.
 */
final class DataTypes {

  /**
   * Find a data type from the type code returned by the native layer (C API).
   *
   * <p>Only data types registered via {@link #register(DataType)} can be resolved.
   *
   * @param nativeCode native code
   * @return data type for this code
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  static DataType<?> fromNativeCode(int nativeCode) {
    DataType<?> dataType = DATA_TYPE_REGISTRY.get(nativeCode);
    if (dataType == null) {
      throw new IllegalArgumentException(
          "DataType " + nativeCode + " is not recognized in Java (version " + TensorFlow.version() + ")");
    }
    return dataType;
  }

  private static final Map<Integer, DataType<?>> DATA_TYPE_REGISTRY = new HashMap<>();

  static {
    register(TBool.DTYPE);
    register(TDouble.DTYPE);
    register(TFloat.DTYPE);
    register(TInt32.DTYPE);
    register(TInt64.DTYPE);
    register(TString.DTYPE);
    register(TUInt8.DTYPE);
  }

  // TODO (karllessard): Right now this method is private but we might want to expose it
  //      to allow user to register custom data types?
  private static void register(DataType<?> dataType) {
    DATA_TYPE_REGISTRY.put(dataType.nativeCode(), dataType);
  }
}
