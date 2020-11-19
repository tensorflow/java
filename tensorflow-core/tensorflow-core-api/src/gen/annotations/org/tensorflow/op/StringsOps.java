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
package org.tensorflow.op;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.strings.Join;
import org.tensorflow.op.strings.Lower;
import org.tensorflow.op.strings.ReduceJoin;
import org.tensorflow.op.strings.RegexFullMatch;
import org.tensorflow.op.strings.RegexReplace;
import org.tensorflow.op.strings.StringFormat;
import org.tensorflow.op.strings.StringLength;
import org.tensorflow.op.strings.StringNGrams;
import org.tensorflow.op.strings.StringSplit;
import org.tensorflow.op.strings.Strip;
import org.tensorflow.op.strings.Substr;
import org.tensorflow.op.strings.ToHashBucket;
import org.tensorflow.op.strings.ToHashBucketFast;
import org.tensorflow.op.strings.ToHashBucketStrong;
import org.tensorflow.op.strings.ToNumber;
import org.tensorflow.op.strings.UnicodeScript;
import org.tensorflow.op.strings.UnicodeTranscode;
import org.tensorflow.op.strings.UnsortedSegmentJoin;
import org.tensorflow.op.strings.Upper;
import org.tensorflow.types.TFloat32;
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

  private final Ops ops;

  StringsOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Joins the strings in the given list of string tensors into one tensor;
   *  <p>
   *  with the given separator (default is an empty separator).
   *  <p>
   *  Examples:
   *  <p>
   *  >>> s = ["hello", "world", "tensorflow"]
   *  >>> tf.strings.join(s, " ")
   *  <tf.Tensor: shape=(), dtype=string, numpy=b'hello world tensorflow'>
   *
   * @param inputs A list of string tensors.  The tensors must all have the same shape,
   *  or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
   *  of non-scalar inputs.
   * @param options carries optional attributes values
   * @return a new instance of Join
   */
  public Join join(Iterable<Operand<TString>> inputs, Join.Options... options) {
    return Join.create(scope, inputs, options);
  }

  /**
   * Converts all uppercase characters into their respective lowercase replacements.
   *  <p>
   *  Example:
   *  <p>
   *  >>> tf.strings.lower("CamelCase string and ALL CAPS")
   *  <tf.Tensor: shape=(), dtype=string, numpy=b'camelcase string and all caps'>
   *
   * @param input
   * @param options carries optional attributes values
   * @return a new instance of Lower
   */
  public Lower lower(Operand<TString> input, Lower.Options... options) {
    return Lower.create(scope, input, options);
  }

  /**
   * Joins a string Tensor across the given dimensions.
   *  <p>
   *  Computes the string join across dimensions in the given string Tensor of shape
   *  `[\\(d_0, d_1, ..., d_{n-1}\\)]`.  Returns a new Tensor created by joining the input
   *  strings with the given separator (default: empty string).  Negative indices are
   *  counted backwards from the end, with `-1` being equivalent to `n - 1`.  If
   *  indices are not specified, joins across all dimensions beginning from `n - 1`
   *  through `0`.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor `a` is [["a", "b"], ["c", "d"]]
   *  tf.reduce_join(a, 0) ==> ["ac", "bd"]
   *  tf.reduce_join(a, 1) ==> ["ab", "cd"]
   *  tf.reduce_join(a, -2) = tf.reduce_join(a, 0) ==> ["ac", "bd"]
   *  tf.reduce_join(a, -1) = tf.reduce_join(a, 1) ==> ["ab", "cd"]
   *  tf.reduce_join(a, 0, keep_dims=True) ==> [["ac", "bd"]]
   *  tf.reduce_join(a, 1, keep_dims=True) ==> [["ab"], ["cd"]]
   *  tf.reduce_join(a, 0, separator=".") ==> ["a.c", "b.d"]
   *  tf.reduce_join(a, [0, 1]) ==> "acbd"
   *  tf.reduce_join(a, [1, 0]) ==> "abcd"
   *  tf.reduce_join(a, []) ==> [["a", "b"], ["c", "d"]]
   *  tf.reduce_join(a) = tf.reduce_join(a, [1, 0]) ==> "abcd"
   *  }</pre>
   *
   * @param inputs The input to be joined.  All reduced indices must have non-zero size.
   * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
   *  order specified.  Omitting `reduction_indices` is equivalent to passing
   *  `[n-1, n-2, ..., 0]`.  Negative indices from `-n` to `-1` are supported.
   * @param options carries optional attributes values
   * @return a new instance of ReduceJoin
   */
  public ReduceJoin reduceJoin(Operand<TString> inputs, Operand<TInt32> reductionIndices,
      ReduceJoin.Options... options) {
    return ReduceJoin.create(scope, inputs, reductionIndices, options);
  }

  /**
   * Check if the input matches the regex pattern.
   *  <p>
   *  The input is a string tensor of any shape. The pattern is a scalar
   *  string tensor which is applied to every element of the input tensor.
   *  The boolean values (True or False) of the output tensor indicate
   *  if the input matches the regex pattern provided.
   *  <p>
   *  The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.regex_full_match(["TF lib", "lib TF"], ".*lib$")
   *  <tf.Tensor: shape=(2,), dtype=bool, numpy=array([ True, False])>
   *  >>> tf.strings.regex_full_match(["TF lib", "lib TF"], ".*TF$")
   *  <tf.Tensor: shape=(2,), dtype=bool, numpy=array([False,  True])>
   *
   * @param input A string tensor of the text to be processed.
   * @param pattern A scalar string tensor containing the regular expression to match the input.
   * @return a new instance of RegexFullMatch
   */
  public RegexFullMatch regexFullMatch(Operand<TString> input, Operand<TString> pattern) {
    return RegexFullMatch.create(scope, input, pattern);
  }

  /**
   * Replaces matches of the `pattern` regular expression in `input` with the
   *  replacement string provided in `rewrite`.
   *  <p>
   *  It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *
   * @param input The text to be processed.
   * @param pattern The regular expression to be matched in the `input` strings.
   * @param rewrite The rewrite string to be substituted for the `pattern` expression where it is
   *  matched in the `input` strings.
   * @param options carries optional attributes values
   * @return a new instance of RegexReplace
   */
  public RegexReplace regexReplace(Operand<TString> input, Operand<TString> pattern,
      Operand<TString> rewrite, RegexReplace.Options... options) {
    return RegexReplace.create(scope, input, pattern, rewrite, options);
  }

  /**
   * Formats a string template using a list of tensors.
   *  <p>
   *  Formats a string template using a list of tensors, pretty-printing tensor summaries.
   *
   * @param inputs The list of tensors to format into the placeholder string.
   * @param options carries optional attributes values
   * @return a new instance of StringFormat
   */
  public StringFormat stringFormat(Iterable<Operand<?>> inputs, StringFormat.Options... options) {
    return StringFormat.create(scope, inputs, options);
  }

  /**
   * String lengths of `input`.
   *  <p>
   *  Computes the length of each string given in the input tensor.
   *  <p>
   *  >>> strings = tf.constant(['Hello','TensorFlow', '\U0001F642'])
   *  >>> tf.strings.length(strings).numpy() # default counts bytes
   *  array([ 5, 10, 4], dtype=int32)
   *  >>> tf.strings.length(strings, unit="UTF8_CHAR").numpy()
   *  array([ 5, 10, 1], dtype=int32)
   *
   * @param input The strings for which to compute the length for each element.
   * @param options carries optional attributes values
   * @return a new instance of StringLength
   */
  public StringLength stringLength(Operand<TString> input, StringLength.Options... options) {
    return StringLength.create(scope, input, options);
  }

  /**
   * Creates ngrams from ragged string data.
   *  <p>
   *  This op accepts a ragged tensor with 1 ragged dimension containing only
   *  strings and outputs a ragged tensor with 1 ragged dimension containing ngrams
   *  of that string, joined along the innermost axis.
   *
   * @param <T> data type for {@code ngramsSplits()} output
   * @param data The values tensor of the ragged string tensor to make ngrams out of. Must be a
   *  1D string tensor.
   * @param dataSplits The splits tensor of the ragged string tensor to make ngrams out of.
   * @param separator The string to append between elements of the token. Use "" for no separator.
   * @param ngramWidths The sizes of the ngrams to create.
   * @param leftPad The string to use to pad the left side of the ngram sequence. Only used if
   *  pad_width != 0.
   * @param rightPad The string to use to pad the right side of the ngram sequence. Only used if
   *  pad_width != 0.
   * @param padWidth The number of padding elements to add to each side of each
   *  sequence. Note that padding will never be greater than 'ngram_widths'-1
   *  regardless of this value. If `pad_width=-1`, then add `max(ngram_widths)-1`
   *  elements.
   * @param preserveShortSequences
   * @return a new instance of StringNGrams
   */
  public <T extends TNumber> StringNGrams<T> stringNGrams(Operand<TString> data,
      Operand<T> dataSplits, String separator, List<Long> ngramWidths, String leftPad,
      String rightPad, Long padWidth, Boolean preserveShortSequences) {
    return StringNGrams.create(scope, data, dataSplits, separator, ngramWidths, leftPad, rightPad, padWidth, preserveShortSequences);
  }

  /**
   * Split elements of `source` based on `sep` into a `SparseTensor`.
   *  <p>
   *  Let N be the size of source (typically N will be the batch size). Split each
   *  element of `source` based on `sep` and return a `SparseTensor`
   *  containing the split tokens. Empty tokens are ignored.
   *  <p>
   *  For example, N = 2, source[0] is 'hello world' and source[1] is 'a b c',
   *  then the output will be
   *  <pre>{@code
   *  st.indices = [0, 0;
   *                0, 1;
   *                1, 0;
   *                1, 1;
   *                1, 2]
   *  st.shape = [2, 3]
   *  st.values = ['hello', 'world', 'a', 'b', 'c']
   *  }</pre>
   *  If `sep` is given, consecutive delimiters are not grouped together and are
   *  deemed to delimit empty strings. For example, source of `"1<>2<><>3"` and
   *  sep of `"<>"` returns `["1", "2", "", "3"]`. If `sep` is None or an empty
   *  string, consecutive whitespace are regarded as a single separator, and the
   *  result will contain no empty strings at the startor end if the string has
   *  leading or trailing whitespace.
   *  <p>
   *  Note that the above mentioned behavior matches python's str.split.
   *
   * @param input `1-D` string `Tensor`, the strings to split.
   * @param sep `0-D` string `Tensor`, the delimiter character.
   * @param options carries optional attributes values
   * @return a new instance of StringSplit
   */
  public StringSplit stringSplit(Operand<TString> input, Operand<TString> sep,
      StringSplit.Options... options) {
    return StringSplit.create(scope, input, sep, options);
  }

  /**
   * Strip leading and trailing whitespaces from the Tensor.
   *
   * @param input A string `Tensor` of any shape.
   * @return a new instance of Strip
   */
  public Strip strip(Operand<TString> input) {
    return Strip.create(scope, input);
  }

  /**
   * Return substrings from `Tensor` of strings.
   *  <p>
   *  For each string in the input `Tensor`, creates a substring starting at index
   *  `pos` with a total length of `len`.
   *  <p>
   *  If `len` defines a substring that would extend beyond the length of the input
   *  string, or if `len` is negative, then as many characters as possible are used.
   *  <p>
   *  A negative `pos` indicates distance within the string backwards from the end.
   *  <p>
   *  If `pos` specifies an index which is out of range for any of the input strings,
   *  then an `InvalidArgumentError` is thrown.
   *  <p>
   *  `pos` and `len` must have the same shape, otherwise a `ValueError` is thrown on
   *  Op creation.
   *  <p>
   *  <i>NOTE</i>: `strings.Substr` supports broadcasting up to two dimensions. More about
   *  broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <p>
   *  ---
   *  <p>
   *  Examples
   *  <p>
   *  Using scalar `pos` and `len`:
   *  <pre>{@code
   *  input = [b'Hello', b'World']
   *  position = 1
   *  length = 3
   *
   *  output = [b'ell', b'orl']
   *  }</pre>
   *  Using `pos` and `len` with same shape as `input`:
   *  <pre>{@code
   *  input = [[b'ten', b'eleven', b'twelve'],
   *           [b'thirteen', b'fourteen', b'fifteen'],
   *           [b'sixteen', b'seventeen', b'eighteen']]
   *  position = [[1, 2, 3],
   *              [1, 2, 3],
   *              [1, 2, 3]]
   *  length =   [[2, 3, 4],
   *              [4, 3, 2],
   *              [5, 5, 5]]
   *
   *  output = [[b'en', b'eve', b'lve'],
   *            [b'hirt', b'urt', b'te'],
   *            [b'ixtee', b'vente', b'hteen']]
   *  }</pre>
   *  Broadcasting `pos` and `len` onto `input`:
   *  <pre>{@code
   *  input = [[b'ten', b'eleven', b'twelve'],
   *           [b'thirteen', b'fourteen', b'fifteen'],
   *           [b'sixteen', b'seventeen', b'eighteen'],
   *           [b'nineteen', b'twenty', b'twentyone']]
   *  position = [1, 2, 3]
   *  length =   [1, 2, 3]
   *
   *  output = [[b'e', b'ev', b'lve'],
   *            [b'h', b'ur', b'tee'],
   *            [b'i', b've', b'hte'],
   *            [b'i', b'en', b'nty']]
   *  }</pre>
   *  Broadcasting `input` onto `pos` and `len`:
   *  <pre>{@code
   *  input = b'thirteen'
   *  position = [1, 5, 7]
   *  length =   [3, 2, 1]
   *
   *  output = [b'hir', b'ee', b'n']
   *  }</pre>
   *  Raises:
   *  <p>
   * `ValueError`: If the first argument cannot be converted to a
   *       Tensor of `dtype string`.
   * `InvalidArgumentError`: If indices are out of range.
   * `ValueError`: If `pos` and `len` are not the same shape.
   *
   * @param input Tensor of strings
   * @param pos Scalar defining the position of first character in each substring
   * @param len Scalar defining the number of characters to include in each substring
   * @param options carries optional attributes values
   * @return a new instance of Substr
   */
  public <T extends TNumber> Substr substr(Operand<TString> input, Operand<T> pos, Operand<T> len,
      Substr.Options... options) {
    return Substr.create(scope, input, pos, len, options);
  }

  /**
   * Converts each string in the input Tensor to its hash mod by a number of buckets.
   *  <p>
   *  The hash function is deterministic on the content of the string within the
   *  process.
   *  <p>
   *  Note that the hash function may change from time to time.
   *  This functionality will be deprecated and it's recommended to use
   *  `tf.string_to_hash_bucket_fast()` or `tf.string_to_hash_bucket_strong()`.
   *
   * @param stringTensor
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucket
   */
  public ToHashBucket toHashBucket(Operand<TString> stringTensor, Long numBuckets) {
    return ToHashBucket.create(scope, stringTensor, numBuckets);
  }

  /**
   * Converts each string in the input Tensor to its hash mod by a number of buckets.
   *  <p>
   *  The hash function is deterministic on the content of the string within the
   *  process and will never change. However, it is not suitable for cryptography.
   *  This function may be used when CPU time is scarce and inputs are trusted or
   *  unimportant. There is a risk of adversaries constructing inputs that all hash
   *  to the same bucket. To prevent this problem, use a strong hash function with
   *  `tf.string_to_hash_bucket_strong`.
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.to_hash_bucket_fast(["Hello", "TensorFlow", "2.x"], 3).numpy()
   *  array([0, 2, 2])
   *
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucketFast
   */
  public ToHashBucketFast toHashBucketFast(Operand<TString> input, Long numBuckets) {
    return ToHashBucketFast.create(scope, input, numBuckets);
  }

  /**
   * Converts each string in the input Tensor to its hash mod by a number of buckets.
   *  <p>
   *  The hash function is deterministic on the content of the string within the
   *  process. The hash function is a keyed hash function, where attribute `key`
   *  defines the key of the hash function. `key` is an array of 2 elements.
   *  <p>
   *  A strong hash is important when inputs may be malicious, e.g. URLs with
   *  additional components. Adversaries could try to make their inputs hash to the
   *  same bucket for a denial-of-service attack or to skew the results. A strong
   *  hash can be used to make it difficult to find inputs with a skewed hash value
   *  distribution over buckets. This requires that the hash function is
   *  seeded by a high-entropy (random) "key" unknown to the adversary.
   *  <p>
   *  The additional robustness comes at a cost of roughly 4x higher compute
   *  time than `tf.string_to_hash_bucket_fast`.
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.to_hash_bucket_strong(["Hello", "TF"], 3, [1, 2]).numpy()
   *  array([2, 0])
   *
   * @param input The strings to assign a hash bucket.
   * @param numBuckets The number of buckets.
   * @param key The key used to seed the hash function, passed as a list of two uint64
   *  elements.
   * @return a new instance of ToHashBucketStrong
   */
  public ToHashBucketStrong toHashBucketStrong(Operand<TString> input, Long numBuckets,
      List<Long> key) {
    return ToHashBucketStrong.create(scope, input, numBuckets, key);
  }

  /**
   * Converts each string in the input Tensor to the specified numeric type.
   *  <p>
   *  (Note that int32 overflow results in an error while float overflow
   *  results in a rounded value.)
   *  <p>
   *  Example:
   *  <p>
   *  >>> strings = ["5.0", "3.0", "7.0"]
   *  >>> tf.strings.to_number(strings)
   *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array([5., 3., 7.], dtype=float32)>
   *
   * @param <T> data type for {@code output()} output
   * @param stringTensor
   * @return a new instance of ToNumber
   */
  public ToNumber<TFloat32> toNumber(Operand<TString> stringTensor) {
    return ToNumber.create(scope, stringTensor);
  }

  /**
   * Converts each string in the input Tensor to the specified numeric type.
   *  <p>
   *  (Note that int32 overflow results in an error while float overflow
   *  results in a rounded value.)
   *  <p>
   *  Example:
   *  <p>
   *  >>> strings = ["5.0", "3.0", "7.0"]
   *  >>> tf.strings.to_number(strings)
   *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array([5., 3., 7.], dtype=float32)>
   *
   * @param <T> data type for {@code output()} output
   * @param stringTensor
   * @param outType The numeric type to interpret each string in `string_tensor` as.
   * @return a new instance of ToNumber
   */
  public <T extends TNumber> ToNumber<T> toNumber(Operand<TString> stringTensor,
      DataType<T> outType) {
    return ToNumber.create(scope, stringTensor, outType);
  }

  /**
   * Determine the script codes of a given tensor of Unicode integer code points.
   *  <p>
   *  This operation converts Unicode code points to script codes corresponding to
   *  each code point. Script codes correspond to International Components for
   *  Unicode (ICU) UScriptCode values. See http://icu-project.org/apiref/icu4c/uscript_8h.html.
   *  Returns -1 (USCRIPT_INVALID_CODE) for invalid codepoints. Output shape will
   *  match input shape.
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.unicode_script([1, 31, 38])
   *  <tf.Tensor: shape=(3,), dtype=int32, numpy=array([0, 0, 0], dtype=int32)>
   *
   * @param input A Tensor of int32 Unicode code points.
   * @return a new instance of UnicodeScript
   */
  public UnicodeScript unicodeScript(Operand<TInt32> input) {
    return UnicodeScript.create(scope, input);
  }

  /**
   * Transcode the input text from a source encoding to a destination encoding.
   *  <p>
   *  The input is a string tensor of any shape. The output is a string tensor of
   *  the same shape containing the transcoded strings. Output strings are always
   *  valid unicode. If the input contains invalid encoding positions, the
   *  `errors` attribute sets the policy for how to deal with them. If the default
   *  error-handling policy is used, invalid formatting will be substituted in the
   *  output by the `replacement_char`. If the errors policy is to `ignore`, any
   *  invalid encoding positions in the input are skipped and not included in the
   *  output. If it set to `strict` then any invalid formatting will result in an
   *  InvalidArgument error.
   *  <p>
   *  This operation can be used with `output_encoding = input_encoding` to enforce
   *  correct formatting for inputs even if they are already in the desired encoding.
   *  <p>
   *  If the input is prefixed by a Byte Order Mark needed to determine encoding
   *  (e.g. if the encoding is UTF-16 and the BOM indicates big-endian), then that
   *  BOM will be consumed and not emitted into the output. If the input encoding
   *  is marked with an explicit endianness (e.g. UTF-16-BE), then the BOM is
   *  interpreted as a non-breaking-space and is preserved in the output (including
   *  always for UTF-8).
   *  <p>
   *  The end result is that if the input is marked as an explicit endianness the
   *  transcoding is faithful to all codepoints in the source. If it is not marked
   *  with an explicit endianness, the BOM is not considered part of the string itself
   *  but as metadata, and so is not preserved in the output.
   *  <p>
   *  Examples:
   *  <p>
   *  >>> tf.strings.unicode_transcode(["Hello", "TensorFlow", "2.x"], "UTF-8", "UTF-16-BE")
   *  <tf.Tensor: shape=(3,), dtype=string, numpy=
   *  array([b'\x00H\x00e\x00l\x00l\x00o',
   *         b'\x00T\x00e\x00n\x00s\x00o\x00r\x00F\x00l\x00o\x00w',
   *         b'\x002\x00.\x00x'], dtype=object)>
   *  >>> tf.strings.unicode_transcode(["A", "B", "C"], "US ASCII", "UTF-8").numpy()
   *  array([b'A', b'B', b'C'], dtype=object)
   *
   * @param input The text to be processed. Can have any shape.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: `"UTF-16", "US ASCII", "UTF-8"`.
   * @param outputEncoding The unicode encoding to use in the output. Must be one of
   *  `"UTF-8", "UTF-16-BE", "UTF-32-BE"`. Multi-byte encodings will be big-endian.
   * @param options carries optional attributes values
   * @return a new instance of UnicodeTranscode
   */
  public UnicodeTranscode unicodeTranscode(Operand<TString> input, String inputEncoding,
      String outputEncoding, UnicodeTranscode.Options... options) {
    return UnicodeTranscode.create(scope, input, inputEncoding, outputEncoding, options);
  }

  /**
   * Joins the elements of `inputs` based on `segment_ids`.
   *  <p>
   *  Computes the string join along segments of a tensor.
   *  Given `segment_ids` with rank `N` and `data` with rank `N+M`:
   *  <p>
   *      `output[i, k1...kM] = strings.join([data[j1...jN, k1...kM])`
   *  <p>
   *  where the join is over all [j1...jN] such that segment_ids[j1...jN] = i.
   *  Strings are joined in row-major order.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  inputs = [['Y', 'q', 'c'], ['Y', '6', '6'], ['p', 'G', 'a']]
   *  output_array = string_ops.unsorted_segment_join(inputs=inputs,
   *                                                  segment_ids=[1, 0, 1],
   *                                                  num_segments=2,
   *                                                  separator=':'))
   *  # output_array ==> [['Y', '6', '6'], ['Y:p', 'q:G', 'c:a']]
   *
   *
   *  inputs = ['this', 'is', 'a', 'test']
   *  output_array = string_ops.unsorted_segment_join(inputs=inputs,
   *                                                  segment_ids=[0, 0, 0, 0],
   *                                                  num_segments=1,
   *                                                  separator=':'))
   *  # output_array ==> ['this:is:a:test']
   *  }</pre>
   *
   * @param inputs The input to be joined.
   * @param segmentIds A tensor whose shape is a prefix of data.shape.  Negative segment ids are not
   *  supported.
   * @param numSegments A scalar.
   * @param options carries optional attributes values
   * @return a new instance of UnsortedSegmentJoin
   */
  public <T extends TNumber, U extends TNumber> UnsortedSegmentJoin unsortedSegmentJoin(
      Operand<TString> inputs, Operand<T> segmentIds, Operand<U> numSegments,
      UnsortedSegmentJoin.Options... options) {
    return UnsortedSegmentJoin.create(scope, inputs, segmentIds, numSegments, options);
  }

  /**
   * Converts all lowercase characters into their respective uppercase replacements.
   *  <p>
   *  Example:
   *  <p>
   *  >>> tf.strings.upper("CamelCase string and ALL CAPS")
   *  <tf.Tensor: shape=(), dtype=string, numpy=b'CAMELCASE STRING AND ALL CAPS'>
   *
   * @param input
   * @param options carries optional attributes values
   * @return a new instance of Upper
   */
  public Upper upper(Operand<TString> input, Upper.Options... options) {
    return Upper.create(scope, input, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
