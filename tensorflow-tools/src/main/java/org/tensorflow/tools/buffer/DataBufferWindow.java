package org.tensorflow.tools.buffer;

/**
 * A mutable container for viewing partially a {@link DataBuffer}.
 *
 * <p>Data buffer windows have a fixed size and can be {@link DataBufferWindow#slideTo(long) "slide"}
 * across a buffer at different offsets to provide different views of the data without allocating
 * a new buffer instance, like {@link #offset(long)} does. This improves overall performances when
 * this operation is repeated frequently. For example:
 *
 * <pre>{@code
 * IntDataBuffer bufferA = DataBuffers.ofInts(1024);
 * // ... init buffer data
 * IntDataBuffer bufferB = DataBuffers.ofInts(1, 2, 3, 4);
 *
 * // Return the index of the first occurrence of bufferB in bufferA using a sliding window
 * DataBufferWindow<IntDataBuffer> windowA = bufferA.window(4);
 * for (int i = 0; i < bufferA.size() - bufferB.size(); ++i) {
 *     if (windowA.slideTo(i).buffer().equals(bufferB)) {
 *         return i;
 *     }
 * }
 * }</pre>>
 *
 * <p>{@code DataBufferWindow} instances are stateful and not thread-safe.
 *
 * @param <B> the type of buffer being viewed
 */
public interface DataBufferWindow<B extends DataBuffer<?>> {

  /**
   * Returns the current offset of this window in the underlying buffer.
   */
  long offset();

  /**
   * Returns the size of this buffer window.
   */
  long size();

  /**
   * Moves the window at the given position in the underlying buffer.
   *
   * <p>The size of the window remains the same and its offset is reset to {@code index}, so that
   * accessing the value of {@link #buffer()} at index {@code x} will return the value at
   * {@code index + x} in the underlying buffer.
   *
   * @param index new offset for this window
   * @return this instance
   * @throws IndexOutOfBoundsException if index is beyond the underlying buffer limits
   */
  DataBufferWindow<B> slideTo(long index);

  /**
   * Moves the window of {@code step} elements in the underlying buffer.
   *
   * <p>The size of the window remains the same and its offset is reset to {@code offset() + step}.
   * If {@code step} is positive, then the window will slide forward. If it is negative, it will
   * slide backward.
   *
   * @param step value to add to the current offset of this window
   * @return this instance
   * @throws IndexOutOfBoundsException if the resulting offset goes beyond the underlying buffer limits
   */
  DataBufferWindow<B> slideOf(long step);

  /**
   * Returns a buffer presenting the data currently viewed by this window.
   *
   * <p>The same instance is always returned for a given window, even after sliding it at
   * different offsets in the underlying buffer. For example:
   *
   * <pre>{@code
   * IntDataBuffer buffer = DataBuffers.of(0, 1, 2, 3);
   * DataBufferWindow<IntDataBuffer> window = buffer.window(0, 2);
   *
   * IntDataBuffer windowBuffer = window.buffer();
   * assertEquals(0, windowBuffer.getInt(0));
   * assertEquals(1, windowBuffer.getInt(1));
   *
   * window.slideTo(2);
   * assertEquals(2, windowBuffer.getInt(0));
   * assertEquals(3, windowBuffer.getInt(1));
   * assertSame(windowBuffer, window.buffer());
   * }</pre>
   *
   * @return this window's buffer
   */
  B buffer();
}
