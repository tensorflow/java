// Targeted by JavaCPP version 1.5.7: DO NOT EDIT THIS FILE

package org.tensorflow.internal.c_api;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.tensorflow.internal.c_api.global.tensorflow.*;

// #endif

/** \ingroup core
 *  Denotes success or failure of a call in Tensorflow. */
@Name("tensorflow::Status") @NoOffset @Properties(inherit = org.tensorflow.internal.c_api.presets.tensorflow.class)
public class NativeStatus extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public NativeStatus(Pointer p) { super(p); }

  /** Create a success status. */

  /** \brief Create a status with the specified error code and msg as a
   *  human-readable string containing more detailed information. */

  /** \brief Create a status with the specified error code, msg, and stack trace
   *  as a human-readable string containing more detailed information. */
// #ifndef SWIG
// #endif

  /** Copy the specified status. */
  public native @ByRef @Name("operator =") NativeStatus put(@Const @ByRef NativeStatus s);
// #ifndef SWIG
// #endif  // SWIG

  public static native @ByVal NativeStatus OK();

  /** Returns true iff the status indicates success. */
  public native @Cast("bool") boolean ok();

  

  public native @StdString BytePointer error_message();

  public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef NativeStatus x);
  
  ///
  public native @Cast("bool") @Name("operator !=") boolean notEquals(@Const @ByRef NativeStatus x);

  /** \brief If {@code ok()}, stores {@code new_status} into {@code *this}.  If {@code !ok()},
   *  preserves the current status, but may augment with additional
   *  information about {@code new_status}.
   * 
   *  Convenient way of keeping track of the first error encountered.
   *  Instead of:
   *    {@code if (overall_status.ok()) overall_status = new_status}
   *  Use:
   *    {@code overall_status.Update(new_status);} */
  
  ///
  public native void Update(@Const @ByRef NativeStatus new_status);

  /** \brief Return a string representation of this status suitable for
   *  printing. Returns the string {@code "OK"} for success.
   * 
   *  By default, it returns combination of the error code name, the message and
   *  any associated payload messages. This string is designed simply to be
   *  human readable and its exact format should not be load bearing. Do not
   *  depend on the exact format of the result of {@code ToString()} which is subject
   *  to change. */
  public native @StdString BytePointer ToString();

  // Ignores any errors. This method does nothing except potentially suppress
  // complaints from any tools that are checking that errors are not dropped on
  // the floor.
  public native void IgnoreError();

  //----------------------------------------------------------------------------
  // Payload Management APIs (Cloned from absl::Status)
  //----------------------------------------------------------------------------
  // A payload may be attached to a status to provide additional context to an
  // error that may not be satisfied by an existing `tensorflow::error::Code`.
  // Typically, this payload serves one of several purposes:
  //
  //   * It may provide more fine-grained semantic information about the error
  //     to facilitate actionable remedies.
  //   * It may provide human-readable contexual information that is more
  //     appropriate to display to an end user.
  //
  // A payload consists of a [key,value] pair, where the key is a string
  // referring to a unique "type URL" and the value is an object of type
  // `absl::Cord` to hold the contextual data.
  //
  // The "type URL" should be unique and follow the format of a URL
  // (https://en.wikipedia.org/wiki/URL) and, ideally, provide some
  // documentation or schema on how to interpret its associated data. For
  // example, the default type URL for a protobuf message type is
  // "type.googleapis.com/packagename.messagename". Other custom wire formats
  // should define the format of type URL in a similar practice so as to
  // minimize the chance of conflict between type URLs.
  // Users should ensure that the type URL can be mapped to a concrete
  // C++ type if they want to deserialize the payload and read it effectively.
  //
  // To attach a payload to a status object, call `Status::SetPayload()`,
  // passing it the type URL and an `absl::Cord` of associated data. Similarly,
  // to extract the payload from a status, call `Status::GetPayload()`. You
  // may attach multiple payloads (with differing type URLs) to any given
  // status object, provided that the status is currently exhibiting an error
  // code (i.e. is not OK).
  // TODO(b/197552541): Use absl::Cord for payload value type.

  // The Payload-related APIs are cloned from absl::Status.
  //
  // Returns the payload of a status given its unique `type_url` key, if
  // present.
  

  // Sets the payload for a non-ok status using a `type_url` key, overwriting
  // any existing payload for that `type_url`.
  //
  // This function does nothing if the Status is ok.
  

  // Erases the payload corresponding to the `type_url` key.  Returns `true` if
  // the payload was present.
  

  // Iterates over the stored payloads and calls the
  // `visitor(type_key, payload)` callable for each one.
  //
  // The order of calls to `visitor()` is not specified and may change at
  // any time and any mutation on the same Status object during visitation is
  // forbidden and could result in undefined behavior.
  
}
