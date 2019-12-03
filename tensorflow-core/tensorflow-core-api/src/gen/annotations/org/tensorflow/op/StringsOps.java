package org.tensorflow.op;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.strings.Join;
import org.tensorflow.op.strings.ReduceJoin;
import org.tensorflow.op.strings.RegexFullMatch;
import org.tensorflow.op.strings.RegexReplace;
import org.tensorflow.op.strings.StringFormat;
import org.tensorflow.op.strings.StringLength;
import org.tensorflow.op.strings.StringSplit;
import org.tensorflow.op.strings.Strip;
import org.tensorflow.op.strings.Substr;
import org.tensorflow.op.strings.ToHashBucket;
import org.tensorflow.op.strings.ToHashBucketFast;
import org.tensorflow.op.strings.ToHashBucketStrong;
import org.tensorflow.op.strings.ToNumber;
import org.tensorflow.op.strings.UnicodeScript;
import org.tensorflow.op.strings.UnicodeTranscode;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code strings} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class StringsOps {
  private final Scope scope;

  StringsOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link Substr} operation
   *
   * @param input Tensor of strings
   * @param pos Scalar defining the position of first character in each substring
   * @param len Scalar defining the number of characters to include in each substring
   * @param options carries optional attributes values
   * @return a new instance of Substr
   * @see org.tensorflow.op.strings.Substr
   */
  public <T extends TNumber> Substr substr(Operand<TString> input, Operand<T> pos, Operand<T> len,
      Substr.Options... options) {
    return Substr.create(scope, input, pos, len, options);
  }

  /**
   * Builds an {@link Join} operation
   *
   * @param inputs A list of string tensors.  The tensors must all have the same shape,
   * @param options carries optional attributes values
   * @return a new instance of Join
   * @see org.tensorflow.op.strings.Join
   */
  public Join join(Iterable<Operand<TString>> inputs, Join.Options... options) {
    return Join.create(scope, inputs, options);
  }

  /**
   * Builds an {@link StringFormat} operation
   *
   * @param inputs The list of tensors to format into the placeholder string.
   * @param options carries optional attributes values
   * @return a new instance of StringFormat
   * @see org.tensorflow.op.strings.StringFormat
   */
  public StringFormat stringFormat(Iterable<Operand<?>> inputs, StringFormat.Options... options) {
    return StringFormat.create(scope, inputs, options);
  }

  /**
   * Builds an {@link ToHashBucketFast} operation
   *
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucketFast
   * @see org.tensorflow.op.strings.ToHashBucketFast
   */
  public ToHashBucketFast toHashBucketFast(Operand<TString> input, Long numBuckets) {
    return ToHashBucketFast.create(scope, input, numBuckets);
  }

  /**
   * Builds an {@link Strip} operation
   *
   * @param input A string `Tensor` of any shape.
   * @return a new instance of Strip
   * @see org.tensorflow.op.strings.Strip
   */
  public Strip strip(Operand<TString> input) {
    return Strip.create(scope, input);
  }

  /**
   * Builds an {@link RegexFullMatch} operation
   *
   * @param input A string tensor of the text to be processed.
   * @param pattern A scalar string tensor containing the regular expression to match the input.
   * @return a new instance of RegexFullMatch
   * @see org.tensorflow.op.strings.RegexFullMatch
   */
  public RegexFullMatch regexFullMatch(Operand<TString> input, Operand<TString> pattern) {
    return RegexFullMatch.create(scope, input, pattern);
  }

  /**
   * Builds an {@link ToNumber} operation
   *
   * @param stringTensor 
   * @return a new instance of ToNumber
   * @see org.tensorflow.op.strings.ToNumber
   */
  public ToNumber<TFloat> toNumber(Operand<TString> stringTensor) {
    return ToNumber.create(scope, stringTensor);
  }

  /**
   * Builds an {@link UnicodeTranscode} operation
   *
   * @param input The text to be processed. Can have any shape.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   * @param outputEncoding The unicode encoding to use in the output. Must be one of
   * @param options carries optional attributes values
   * @return a new instance of UnicodeTranscode
   * @see org.tensorflow.op.strings.UnicodeTranscode
   */
  public UnicodeTranscode unicodeTranscode(Operand<TString> input, String inputEncoding,
      String outputEncoding, UnicodeTranscode.Options... options) {
    return UnicodeTranscode.create(scope, input, inputEncoding, outputEncoding, options);
  }

  /**
   * Builds an {@link RegexReplace} operation
   *
   * @param input The text to be processed.
   * @param pattern The regular expression to be matched in the `input` strings.
   * @param rewrite The rewrite string to be substituted for the `pattern` expression where it is
   * @param options carries optional attributes values
   * @return a new instance of RegexReplace
   * @see org.tensorflow.op.strings.RegexReplace
   */
  public RegexReplace regexReplace(Operand<TString> input, Operand<TString> pattern,
      Operand<TString> rewrite, RegexReplace.Options... options) {
    return RegexReplace.create(scope, input, pattern, rewrite, options);
  }

  /**
   * Builds an {@link ToHashBucket} operation
   *
   * @param stringTensor 
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucket
   * @see org.tensorflow.op.strings.ToHashBucket
   */
  public ToHashBucket toHashBucket(Operand<TString> stringTensor, Long numBuckets) {
    return ToHashBucket.create(scope, stringTensor, numBuckets);
  }

  /**
   * Builds an {@link StringSplit} operation
   *
   * @param input `1-D` string `Tensor`, the strings to split.
   * @param sep `0-D` string `Tensor`, the delimiter character.
   * @param options carries optional attributes values
   * @return a new instance of StringSplit
   * @see org.tensorflow.op.strings.StringSplit
   */
  public StringSplit stringSplit(Operand<TString> input, Operand<TString> sep,
      StringSplit.Options... options) {
    return StringSplit.create(scope, input, sep, options);
  }

  /**
   * Builds an {@link UnicodeScript} operation
   *
   * @param input A Tensor of int32 Unicode code points.
   * @return a new instance of UnicodeScript
   * @see org.tensorflow.op.strings.UnicodeScript
   */
  public UnicodeScript unicodeScript(Operand<TInt32> input) {
    return UnicodeScript.create(scope, input);
  }

  /**
   * Builds an {@link ToHashBucketStrong} operation
   *
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @param key The key used to seed the hash function, passed as a list of two uint64
   * @return a new instance of ToHashBucketStrong
   * @see org.tensorflow.op.strings.ToHashBucketStrong
   */
  public ToHashBucketStrong toHashBucketStrong(Operand<TString> input, Long numBuckets,
      List<Long> key) {
    return ToHashBucketStrong.create(scope, input, numBuckets, key);
  }

  /**
   * Builds an {@link ToNumber} operation
   *
   * @param stringTensor 
   * @param outType The numeric type to interpret each string in `string_tensor` as.
   * @return a new instance of ToNumber
   * @see org.tensorflow.op.strings.ToNumber
   */
  public <T extends TNumber> ToNumber<T> toNumber(Operand<TString> stringTensor,
      DataType<T> outType) {
    return ToNumber.create(scope, stringTensor, outType);
  }

  /**
   * Builds an {@link ReduceJoin} operation
   *
   * @param inputs The input to be joined.  All reduced indices must have non-zero size.
   * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
   * @param options carries optional attributes values
   * @return a new instance of ReduceJoin
   * @see org.tensorflow.op.strings.ReduceJoin
   */
  public ReduceJoin reduceJoin(Operand<TString> inputs, Operand<TInt32> reductionIndices,
      ReduceJoin.Options... options) {
    return ReduceJoin.create(scope, inputs, reductionIndices, options);
  }

  /**
   * Builds an {@link StringLength} operation
   *
   * @param input The string for which to compute the length.
   * @param options carries optional attributes values
   * @return a new instance of StringLength
   * @see org.tensorflow.op.strings.StringLength
   */
  public StringLength stringLength(Operand<TString> input, StringLength.Options... options) {
    return StringLength.create(scope, input, options);
  }
}
