package org.tensorflow.internal.buffer;

import com.google.common.base.Charsets;
import java.nio.ReadOnlyBufferException;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.ndarray.NdArray;

public class StringTensorBuffer extends AbstractDataBuffer<String> {

  @Override
  public long size() {
    return offsets.size();
  }

  @Override
  public String getObject(long index) {
    Validator.getArgs(this, index);
    long offset = offsets.getLong(index);

    // Read string length as a varint from the given offset
    byte b;
    int pos = 0;
    int length = 0;
    do {
      b = data.getByte(offset++);
      length |= (b & 0x7F) << pos++;
    } while ((b & 0x80) != 0);

    // Read string of the given length
    byte[] bytes = new byte[length];
    data.offset(offset).read(bytes);

    return new String(bytes, Charsets.UTF_8);
  }

  @Override
  public DataBuffer<String> setObject(String value, long index) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public boolean isReadOnly() {
    return true;
  }

  @Override
  public DataBuffer<String> copyTo(DataBuffer<String> dst, long size) {
    if (size == size() && dst instanceof StringTensorBuffer) {
      StringTensorBuffer tensorDst = (StringTensorBuffer) dst;
      if (offsets.size() != size || data.size() != size) {
        throw new IllegalArgumentException(
            "Cannot copy string tensor data to another tensor of a different size");
      }
      offsets.copyTo(tensorDst.offsets, size);
      data.copyTo(tensorDst.data, size);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public DataBuffer<String> offset(long index) {
    return new StringTensorBuffer(offsets.offset(index), data.offset(offsets.getLong(index)));
  }

  @Override
  public DataBuffer<String> narrow(long size) {
    return new StringTensorBuffer(offsets.narrow(size), data.narrow(offsets.getLong(size)));
  }

  public void init(NdArray<String> src) {
    DataWriter writer = new DataWriter();
    src.scalars().forEach(s -> writer.writeNext(s.getObject()));
  }

  StringTensorBuffer(LongDataBuffer offsets, ByteDataBuffer data) {
    this.offsets = offsets;
    this.data = data;
  }

  private class DataWriter {
    long count = 0;
    long dataIndex = 0;

    void writeNext(String value) {
      offsets.setLong(dataIndex, count++);

      // Encode string length as a varint first
      int v = value.length();
      while (v >= 0x80) {
        data.setByte((byte) ((v & 0x7F) | 0x80), dataIndex++);
        v >>= 7;
      }
      data.setByte((byte) v, dataIndex++);

      // Then write string data bytes
      byte[] bytes = value.getBytes(Charsets.UTF_8);
      data.offset(dataIndex).write(bytes);
      dataIndex += bytes.length;
    }
  }

  private final LongDataBuffer offsets;
  private final ByteDataBuffer data;
}
