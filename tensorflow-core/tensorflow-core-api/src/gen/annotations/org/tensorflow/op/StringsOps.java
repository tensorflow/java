// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.Operand;
import org.tensorflow.op.strings.Join;
import org.tensorflow.op.strings.Lower;
import org.tensorflow.op.strings.ReduceJoin;
import org.tensorflow.op.strings.RegexFullMatch;
import org.tensorflow.op.strings.RegexReplace;
import org.tensorflow.op.strings.StaticRegexFullMatch;
import org.tensorflow.op.strings.StaticRegexReplace;
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
import org.tensorflow.op.strings.UnicodeDecode;
import org.tensorflow.op.strings.UnicodeDecodeWithOffsets;
import org.tensorflow.op.strings.UnicodeEncode;
import org.tensorflow.op.strings.UnicodeScript;
import org.tensorflow.op.strings.UnicodeTranscode;
import org.tensorflow.op.strings.UnsortedSegmentJoin;
import org.tensorflow.op.strings.Upper;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code strings} operations as {@link Op Op}s
 *
 * @see Ops
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
   *  with the given separator (default is an empty separator).
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>s = [&quot;hello&quot;, &quot;world&quot;, &quot;tensorflow&quot;]
   *  tf.strings.join(s, &quot; &quot;)
   *  &lt;tf.Tensor: shape=(), dtype=string, numpy=b'hello world tensorflow'&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param inputs A list of string tensors.  The tensors must all have the same shape,
   *  or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
   *  of non-scalar inputs.
   * @param options carries optional attribute values
   * @return a new instance of Join
   */
  public Join join(Iterable<Operand<TString>> inputs, Join.Options... options) {
    return Join.create(scope, inputs, options);
  }

  /**
   * Converts all uppercase characters into their respective lowercase replacements.
   *  Example:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.lower(&quot;CamelCase string and ALL CAPS&quot;)
   *  &lt;tf.Tensor: shape=(), dtype=string, numpy=b'camelcase string and all caps'&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input The input to be lower-cased.
   * @param options carries optional attribute values
   * @return a new instance of Lower
   */
  public Lower lower(Operand<TString> input, Lower.Options... options) {
    return Lower.create(scope, input, options);
  }

  /**
   * Joins a string Tensor across the given dimensions.
   *  Computes the string join across dimensions in the given string Tensor of shape
   *  {@code [\\(d_0, d_1, ..., d_{n-1}\\)]}.  Returns a new Tensor created by joining the input
   *  strings with the given separator (default: empty string).  Negative indices are
   *  counted backwards from the end, with {@code -1} being equivalent to {@code n - 1}.  If
   *  indices are not specified, joins across all dimensions beginning from {@code n - 1}
   *  through {@code 0}.
   *  <p>For example:
   *  <pre>
   *  # tensor `a` is [[&quot;a&quot;, &quot;b&quot;], [&quot;c&quot;, &quot;d&quot;]]
   *  tf.reduce_join(a, 0) ==&gt; [&quot;ac&quot;, &quot;bd&quot;]
   *  tf.reduce_join(a, 1) ==&gt; [&quot;ab&quot;, &quot;cd&quot;]
   *  tf.reduce_join(a, -2) = tf.reduce_join(a, 0) ==&gt; [&quot;ac&quot;, &quot;bd&quot;]
   *  tf.reduce_join(a, -1) = tf.reduce_join(a, 1) ==&gt; [&quot;ab&quot;, &quot;cd&quot;]
   *  tf.reduce_join(a, 0, keep_dims=True) ==&gt; [[&quot;ac&quot;, &quot;bd&quot;]]
   *  tf.reduce_join(a, 1, keep_dims=True) ==&gt; [[&quot;ab&quot;], [&quot;cd&quot;]]
   *  tf.reduce_join(a, 0, separator=&quot;.&quot;) ==&gt; [&quot;a.c&quot;, &quot;b.d&quot;]
   *  tf.reduce_join(a, [0, 1]) ==&gt; &quot;acbd&quot;
   *  tf.reduce_join(a, [1, 0]) ==&gt; &quot;abcd&quot;
   *  tf.reduce_join(a, []) ==&gt; [[&quot;a&quot;, &quot;b&quot;], [&quot;c&quot;, &quot;d&quot;]]
   *  tf.reduce_join(a) = tf.reduce_join(a, [1, 0]) ==&gt; &quot;abcd&quot;
   *  </pre>
   *
   * @param inputs The input to be joined.  All reduced indices must have non-zero size.
   * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
   *  order specified.  Omitting {@code reduction_indices} is equivalent to passing
   *  {@code [n-1, n-2, ..., 0]}.  Negative indices from {@code -n} to {@code -1} are supported.
   * @param options carries optional attribute values
   * @return a new instance of ReduceJoin
   */
  public ReduceJoin reduceJoin(Operand<TString> inputs, Operand<TInt32> reductionIndices,
      ReduceJoin.Options... options) {
    return ReduceJoin.create(scope, inputs, reductionIndices, options);
  }

  /**
   * Check if the input matches the regex pattern.
   *  The input is a string tensor of any shape. The pattern is a scalar
   *  string tensor which is applied to every element of the input tensor.
   *  The boolean values (True or False) of the output tensor indicate
   *  if the input matches the regex pattern provided.
   *  <p>The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.regex_full_match([&quot;TF lib&quot;, &quot;lib TF&quot;], &quot;.*lib$&quot;)
   *  &lt;tf.Tensor: shape=(2,), dtype=bool, numpy=array([ True, False])&gt;
   *  tf.strings.regex_full_match([&quot;TF lib&quot;, &quot;lib TF&quot;], &quot;.*TF$&quot;)
   *  &lt;tf.Tensor: shape=(2,), dtype=bool, numpy=array([False,  True])&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input A string tensor of the text to be processed.
   * @param pattern A scalar string tensor containing the regular expression to match the input.
   * @return a new instance of RegexFullMatch
   */
  public RegexFullMatch regexFullMatch(Operand<TString> input, Operand<TString> pattern) {
    return RegexFullMatch.create(scope, input, pattern);
  }

  /**
   * Replaces matches of the {@code pattern} regular expression in {@code input} with the
   *  replacement string provided in {@code rewrite}.
   *  It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *
   * @param input The text to be processed.
   * @param pattern The regular expression to be matched in the {@code input} strings.
   * @param rewrite The rewrite string to be substituted for the {@code pattern} expression where it is
   *  matched in the {@code input} strings.
   * @param options carries optional attribute values
   * @return a new instance of RegexReplace
   */
  public RegexReplace regexReplace(Operand<TString> input, Operand<TString> pattern,
      Operand<TString> rewrite, RegexReplace.Options... options) {
    return RegexReplace.create(scope, input, pattern, rewrite, options);
  }

  /**
   * Check if the input matches the regex pattern.
   *  The input is a string tensor of any shape. The pattern is the
   *  regular expression to be matched with every element of the input tensor.
   *  The boolean values (True or False) of the output tensor indicate
   *  if the input matches the regex pattern provided.
   *  <p>The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *
   * @param input A string tensor of the text to be processed.
   * @param pattern The regular expression to match the input.
   * @return a new instance of StaticRegexFullMatch
   */
  public StaticRegexFullMatch staticRegexFullMatch(Operand<TString> input, String pattern) {
    return StaticRegexFullMatch.create(scope, input, pattern);
  }

  /**
   * Replaces the match of pattern in input with rewrite.
   *  It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
   *
   * @param input The text to be processed.
   * @param pattern The regular expression to match the input.
   * @param rewrite The rewrite to be applied to the matched expression.
   * @param options carries optional attribute values
   * @return a new instance of StaticRegexReplace
   */
  public StaticRegexReplace staticRegexReplace(Operand<TString> input, String pattern,
      String rewrite, StaticRegexReplace.Options... options) {
    return StaticRegexReplace.create(scope, input, pattern, rewrite, options);
  }

  /**
   * Formats a string template using a list of tensors.
   *  Formats a string template using a list of tensors, pretty-printing tensor summaries.
   *
   * @param inputs The list of tensors to format into the placeholder string.
   * @param options carries optional attribute values
   * @return a new instance of StringFormat
   */
  public StringFormat stringFormat(Iterable<Operand<?>> inputs, StringFormat.Options... options) {
    return StringFormat.create(scope, inputs, options);
  }

  /**
   * String lengths of {@code input}.
   *  Computes the length of each string given in the input tensor.
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>strings = tf.constant(['Hello','TensorFlow', '\U0001F642'])
   *  tf.strings.length(strings).numpy() # default counts bytes
   *  array([ 5, 10, 4], dtype=int32)
   *  tf.strings.length(strings, unit=&quot;UTF8_CHAR&quot;).numpy()
   *  array([ 5, 10, 1], dtype=int32)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input The strings for which to compute the length for each element.
   * @param options carries optional attribute values
   * @return a new instance of StringLength
   */
  public StringLength stringLength(Operand<TString> input, StringLength.Options... options) {
    return StringLength.create(scope, input, options);
  }

  /**
   * Creates ngrams from ragged string data.
   *  This op accepts a ragged tensor with 1 ragged dimension containing only
   *  strings and outputs a ragged tensor with 1 ragged dimension containing ngrams
   *  of that string, joined along the innermost axis.
   *
   * @param data The values tensor of the ragged string tensor to make ngrams out of. Must be a
   *  1D string tensor.
   * @param dataSplits The splits tensor of the ragged string tensor to make ngrams out of.
   * @param separator The string to append between elements of the token. Use &quot;&quot; for no separator.
   * @param ngramWidths The sizes of the ngrams to create.
   * @param leftPad The string to use to pad the left side of the ngram sequence. Only used if
   *  pad_width != 0.
   * @param rightPad The string to use to pad the right side of the ngram sequence. Only used if
   *  pad_width != 0.
   * @param padWidth The number of padding elements to add to each side of each
   *  sequence. Note that padding will never be greater than 'ngram_widths'-1
   *  regardless of this value. If {@code pad_width=-1}, then add {@code max(ngram_widths)-1}
   *  elements.
   * @param preserveShortSequences The value of the preserveShortSequences attribute
   * @param <T> data type for {@code StringNGrams} output and operands
   * @return a new instance of StringNGrams
   */
  public <T extends TNumber> StringNGrams<T> stringNGrams(Operand<TString> data,
      Operand<T> dataSplits, String separator, List<Long> ngramWidths, String leftPad,
      String rightPad, Long padWidth, Boolean preserveShortSequences) {
    return StringNGrams.create(scope, data, dataSplits, separator, ngramWidths, leftPad, rightPad, padWidth, preserveShortSequences);
  }

  /**
   * Split elements of {@code source} based on {@code sep} into a {@code SparseTensor}.
   *  Let N be the size of source (typically N will be the batch size). Split each
   *  element of {@code source} based on {@code sep} and return a {@code SparseTensor}
   *  containing the split tokens. Empty tokens are ignored.
   *  <p>For example, N = 2, source[0] is 'hello world' and source[1] is 'a b c',
   *  then the output will be
   *  <pre>
   *  st.indices = [0, 0;
   *                0, 1;
   *                1, 0;
   *                1, 1;
   *                1, 2]
   *  st.shape = [2, 3]
   *  st.values = ['hello', 'world', 'a', 'b', 'c']
   *  </pre>
   *  <p>If {@code sep} is given, consecutive delimiters are not grouped together and are
   *  deemed to delimit empty strings. For example, source of {@code "1<>2<><>3"} and
   *  sep of {@code "<>"} returns {@code ["1", "2", "", "3"]}. If {@code sep} is None or an empty
   *  string, consecutive whitespace are regarded as a single separator, and the
   *  result will contain no empty strings at the startor end if the string has
   *  leading or trailing whitespace.
   *  <p>Note that the above mentioned behavior matches python's str.split.
   *
   * @param input {@code 1-D} string {@code Tensor}, the strings to split.
   * @param sep {@code 0-D} string {@code Tensor}, the delimiter character.
   * @param options carries optional attribute values
   * @return a new instance of StringSplit
   */
  public StringSplit stringSplit(Operand<TString> input, Operand<TString> sep,
      StringSplit.Options... options) {
    return StringSplit.create(scope, input, sep, options);
  }

  /**
   * Strip leading and trailing whitespaces from the Tensor.
   *  Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.strip([&quot;\nTensorFlow&quot;, &quot;     The python library    &quot;]).numpy()
   *  array([b'TensorFlow', b'The python library'], dtype=object)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input A string {@code Tensor} of any shape.
   * @return a new instance of Strip
   */
  public Strip strip(Operand<TString> input) {
    return Strip.create(scope, input);
  }

  /**
   * Return substrings from {@code Tensor} of strings.
   *  For each string in the input {@code Tensor}, creates a substring starting at index
   *  {@code pos} with a total length of {@code len}.
   *  <p>If {@code len} defines a substring that would extend beyond the length of the input
   *  string, or if {@code len} is negative, then as many characters as possible are used.
   *  <p>A negative {@code pos} indicates distance within the string backwards from the end.
   *  <p>If {@code pos} specifies an index which is out of range for any of the input strings,
   *  then an {@code InvalidArgumentError} is thrown.
   *  <p>{@code pos} and {@code len} must have the same shape, otherwise a {@code ValueError} is thrown on
   *  Op creation.
   *  <p><em>NOTE</em>: {@code strings.Substr} supports broadcasting up to two dimensions. More about
   *  broadcasting
   *   <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a> 
   *  <hr />
   *  <p>Examples
   *  <p>Using scalar {@code pos} and {@code len}:
   *  <pre>
   *  input = [b'Hello', b'World']
   *  position = 1
   *  length = 3
   *
   *  output = [b'ell', b'orl']
   *  </pre>
   *  <p>Using {@code pos} and {@code len} with same shape as {@code input}:
   *  <pre>
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
   *  </pre>
   *  <p>Broadcasting {@code pos} and {@code len} onto {@code input}:
   *  <pre>
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
   *  </pre>
   *  <p>Broadcasting {@code input} onto {@code pos} and {@code len}:
   *  <pre>
   *  input = b'thirteen'
   *  position = [1, 5, 7]
   *  length =   [3, 2, 1]
   *
   *  output = [b'hir', b'ee', b'n']
   *  </pre>
   *  <p>Raises:
   *  <ul>
   *  <li>{@code ValueError}: If the first argument cannot be converted to a
   *  Tensor of {@code dtype string}.</li>
   *  <li>{@code InvalidArgumentError}: If indices are out of range.</li>
   *  <li>{@code ValueError}: If {@code pos} and {@code len} are not the same shape.</li>
   *  </ul>
   *
   * @param input Tensor of strings
   * @param pos Scalar defining the position of first character in each substring
   * @param len Scalar defining the number of characters to include in each substring
   * @param options carries optional attribute values
   * @param <T> data type for {@code Substr} output and operands
   * @return a new instance of Substr
   */
  public <T extends TNumber> Substr substr(Operand<TString> input, Operand<T> pos, Operand<T> len,
      Substr.Options... options) {
    return Substr.create(scope, input, pos, len, options);
  }

  /**
   * Converts each string in the input Tensor to its hash mod by a number of buckets.
   *  The hash function is deterministic on the content of the string within the
   *  process.
   *  <p>Note that the hash function may change from time to time.
   *  This functionality will be deprecated and it's recommended to use
   *  {@code tf.string_to_hash_bucket_fast()} or {@code tf.string_to_hash_bucket_strong()}.
   *
   * @param stringTensor The stringTensor value
   * @param numBuckets The number of buckets.
   * @return a new instance of ToHashBucket
   */
  public ToHashBucket toHashBucket(Operand<TString> stringTensor, Long numBuckets) {
    return ToHashBucket.create(scope, stringTensor, numBuckets);
  }

  /**
   * Converts each string in the input Tensor to its hash mod by a number of buckets.
   *  The hash function is deterministic on the content of the string within the
   *  process and will never change. However, it is not suitable for cryptography.
   *  This function may be used when CPU time is scarce and inputs are trusted or
   *  unimportant. There is a risk of adversaries constructing inputs that all hash
   *  to the same bucket. To prevent this problem, use a strong hash function with
   *  {@code tf.string_to_hash_bucket_strong}.
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.to_hash_bucket_fast([&quot;Hello&quot;, &quot;TensorFlow&quot;, &quot;2.x&quot;], 3).numpy()
   *  array([0, 2, 2])
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
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
   *  The hash function is deterministic on the content of the string within the
   *  process. The hash function is a keyed hash function, where attribute {@code key}
   *  defines the key of the hash function. {@code key} is an array of 2 elements.
   *  <p>A strong hash is important when inputs may be malicious, e.g. URLs with
   *  additional components. Adversaries could try to make their inputs hash to the
   *  same bucket for a denial-of-service attack or to skew the results. A strong
   *  hash can be used to make it difficult to find inputs with a skewed hash value
   *  distribution over buckets. This requires that the hash function is
   *  seeded by a high-entropy (random) &quot;key&quot; unknown to the adversary.
   *  <p>The additional robustness comes at a cost of roughly 4x higher compute
   *  time than {@code tf.string_to_hash_bucket_fast}.
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.to_hash_bucket_strong([&quot;Hello&quot;, &quot;TF&quot;], 3, [1, 2]).numpy()
   *  array([2, 0])
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
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
   *  (Note that int32 overflow results in an error while float overflow
   *  results in a rounded value.)
   *  <p>Example:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>strings = [&quot;5.0&quot;, &quot;3.0&quot;, &quot;7.0&quot;]
   *  tf.strings.to_number(strings)
   *  &lt;tf.Tensor: shape=(3,), dtype=float32, numpy=array([5., 3., 7.], dtype=float32)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param stringTensor The stringTensor value
   * @return a new instance of ToNumber, with default output types
   */
  public ToNumber<TFloat32> toNumber(Operand<TString> stringTensor) {
    return ToNumber.create(scope, stringTensor);
  }

  /**
   * Converts each string in the input Tensor to the specified numeric type.
   *  (Note that int32 overflow results in an error while float overflow
   *  results in a rounded value.)
   *  <p>Example:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>strings = [&quot;5.0&quot;, &quot;3.0&quot;, &quot;7.0&quot;]
   *  tf.strings.to_number(strings)
   *  &lt;tf.Tensor: shape=(3,), dtype=float32, numpy=array([5., 3., 7.], dtype=float32)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param stringTensor The stringTensor value
   * @param outType The numeric type to interpret each string in {@code string_tensor} as.
   * @param <T> data type for {@code StringToNumber} output and operands
   * @return a new instance of ToNumber
   */
  public <T extends TNumber> ToNumber<T> toNumber(Operand<TString> stringTensor, Class<T> outType) {
    return ToNumber.create(scope, stringTensor, outType);
  }

  /**
   * Decodes each string in {@code input} into a sequence of Unicode code points.
   *  The character codepoints for all strings are returned using a single vector
   *  {@code char_values}, with strings expanded to characters in row-major order.
   *  <p>The {@code row_splits} tensor indicates where the codepoints for
   *  each input string begin and end within the {@code char_values} tensor.
   *  In particular, the values for the {@code i}th
   *  string (in row-major order) are stored in the slice
   *  {@code [row_splits[i]:row_splits[i+1]]}. Thus:
   *  <ul>
   *  <li>{@code char_values[row_splits[i]+j]} is the Unicode codepoint for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code row_splits[i+1] - row_splits[i]} is the number of characters in the {@code i}th
   *  string (in row-major order).</li>
   *  </ul>
   *
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   *  to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeDecode, with default output types
   */
  public UnicodeDecode<TInt64> unicodeDecode(Operand<TString> input, String inputEncoding,
      UnicodeDecode.Options... options) {
    return UnicodeDecode.create(scope, input, inputEncoding, options);
  }

  /**
   * Decodes each string in {@code input} into a sequence of Unicode code points.
   *  The character codepoints for all strings are returned using a single vector
   *  {@code char_values}, with strings expanded to characters in row-major order.
   *  <p>The {@code row_splits} tensor indicates where the codepoints for
   *  each input string begin and end within the {@code char_values} tensor.
   *  In particular, the values for the {@code i}th
   *  string (in row-major order) are stored in the slice
   *  {@code [row_splits[i]:row_splits[i+1]]}. Thus:
   *  <ul>
   *  <li>{@code char_values[row_splits[i]+j]} is the Unicode codepoint for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code row_splits[i+1] - row_splits[i]} is the number of characters in the {@code i}th
   *  string (in row-major order).</li>
   *  </ul>
   *
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   *  to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param Tsplits The value of the Tsplits attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code UnicodeDecode} output and operands
   * @return a new instance of UnicodeDecode
   */
  public <T extends TNumber> UnicodeDecode<T> unicodeDecode(Operand<TString> input,
      String inputEncoding, Class<T> Tsplits, UnicodeDecode.Options... options) {
    return UnicodeDecode.create(scope, input, inputEncoding, Tsplits, options);
  }

  /**
   * Decodes each string in {@code input} into a sequence of Unicode code points.
   *  The character codepoints for all strings are returned using a single vector
   *  {@code char_values}, with strings expanded to characters in row-major order.
   *  Similarly, the character start byte offsets are returned using a single vector
   *  {@code char_to_byte_starts}, with strings expanded in row-major order.
   *  <p>The {@code row_splits} tensor indicates where the codepoints and start offsets for
   *  each input string begin and end within the {@code char_values} and
   *  {@code char_to_byte_starts} tensors.  In particular, the values for the {@code i}th
   *  string (in row-major order) are stored in the slice
   *  {@code [row_splits[i]:row_splits[i+1]]}. Thus:
   *  <ul>
   *  <li>{@code char_values[row_splits[i]+j]} is the Unicode codepoint for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code char_to_bytes_starts[row_splits[i]+j]} is the start byte offset for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code row_splits[i+1] - row_splits[i]} is the number of characters in the {@code i}th
   *  string (in row-major order).</li>
   *  </ul>
   *
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   *  to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeDecodeWithOffsets, with default output types
   */
  public UnicodeDecodeWithOffsets<TInt64> unicodeDecodeWithOffsets(Operand<TString> input,
      String inputEncoding, UnicodeDecodeWithOffsets.Options... options) {
    return UnicodeDecodeWithOffsets.create(scope, input, inputEncoding, options);
  }

  /**
   * Decodes each string in {@code input} into a sequence of Unicode code points.
   *  The character codepoints for all strings are returned using a single vector
   *  {@code char_values}, with strings expanded to characters in row-major order.
   *  Similarly, the character start byte offsets are returned using a single vector
   *  {@code char_to_byte_starts}, with strings expanded in row-major order.
   *  <p>The {@code row_splits} tensor indicates where the codepoints and start offsets for
   *  each input string begin and end within the {@code char_values} and
   *  {@code char_to_byte_starts} tensors.  In particular, the values for the {@code i}th
   *  string (in row-major order) are stored in the slice
   *  {@code [row_splits[i]:row_splits[i+1]]}. Thus:
   *  <ul>
   *  <li>{@code char_values[row_splits[i]+j]} is the Unicode codepoint for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code char_to_bytes_starts[row_splits[i]+j]} is the start byte offset for the {@code j}th
   *  character in the {@code i}th string (in row-major order).</li>
   *  <li>{@code row_splits[i+1] - row_splits[i]} is the number of characters in the {@code i}th
   *  string (in row-major order).</li>
   *  </ul>
   *
   * @param input The text to be decoded. Can have any shape. Note that the output is flattened
   *  to a vector of char values.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param Tsplits The value of the Tsplits attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code UnicodeDecodeWithOffsets} output and operands
   * @return a new instance of UnicodeDecodeWithOffsets
   */
  public <T extends TNumber> UnicodeDecodeWithOffsets<T> unicodeDecodeWithOffsets(
      Operand<TString> input, String inputEncoding, Class<T> Tsplits,
      UnicodeDecodeWithOffsets.Options... options) {
    return UnicodeDecodeWithOffsets.create(scope, input, inputEncoding, Tsplits, options);
  }

  /**
   * Encode a tensor of ints into unicode strings.
   *  Returns a vector of strings, where {@code output[i]} is constructed by encoding the
   *  Unicode codepoints in {@code input_values[input_splits[i]:input_splits[i+1]]}
   *  using {@code output_encoding}.
   *  <hr />
   *  <p>Example:
   *  <pre>
   *  input_values = [72, 101, 108, 108, 111, 87, 111, 114, 108, 100]
   *  input_splits = [0, 5, 10]
   *  output_encoding = 'UTF-8'
   *
   *  output = ['Hello', 'World']
   *  </pre>
   *
   * @param inputValues A 1D tensor containing the unicode codepoints that should be encoded.
   * @param inputSplits A 1D tensor specifying how the unicode codepoints should be split into strings.
   *  In particular, {@code output[i]} is constructed by encoding the codepoints in the
   *  slice {@code input_values[input_splits[i]:input_splits[i+1]]}.
   * @param outputEncoding Unicode encoding of the output strings. Valid encodings are: {@code "UTF-8", "UTF-16-BE", and "UTF-32-BE"}.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeEncode
   */
  public UnicodeEncode unicodeEncode(Operand<TInt32> inputValues,
      Operand<? extends TNumber> inputSplits, String outputEncoding,
      UnicodeEncode.Options... options) {
    return UnicodeEncode.create(scope, inputValues, inputSplits, outputEncoding, options);
  }

  /**
   * Determine the script codes of a given tensor of Unicode integer code points.
   *  This operation converts Unicode code points to script codes corresponding to
   *  each code point. Script codes correspond to International Components for
   *  Unicode (ICU) UScriptCode values.
   *  <p>See
   *   <a href="http://icu-project.org/apiref/icu4c/uscript_8h.html">ICU project docs</a> 
   *  for more details on script codes.
   *  <p>For an example, see the unicode strings guide on [unicode scripts]
   *  (https://www.tensorflow.org/tutorials/load_data/unicode#representing_unicode).
   *  <p>Returns -1 (USCRIPT_INVALID_CODE) for invalid codepoints. Output shape will
   *  match input shape.
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.unicode_script([1, 31, 38])
   *  &lt;tf.Tensor: shape=(3,), dtype=int32, numpy=array([0, 0, 0], dtype=int32)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input A Tensor of int32 Unicode code points.
   * @return a new instance of UnicodeScript
   */
  public UnicodeScript unicodeScript(Operand<TInt32> input) {
    return UnicodeScript.create(scope, input);
  }

  /**
   * Transcode the input text from a source encoding to a destination encoding.
   *  The input is a string tensor of any shape. The output is a string tensor of
   *  the same shape containing the transcoded strings. Output strings are always
   *  valid unicode. If the input contains invalid encoding positions, the
   *  {@code errors} attribute sets the policy for how to deal with them. If the default
   *  error-handling policy is used, invalid formatting will be substituted in the
   *  output by the {@code replacement_char}. If the errors policy is to {@code ignore}, any
   *  invalid encoding positions in the input are skipped and not included in the
   *  output. If it set to {@code strict} then any invalid formatting will result in an
   *  InvalidArgument error.
   *  <p>This operation can be used with {@code output_encoding = input_encoding} to enforce
   *  correct formatting for inputs even if they are already in the desired encoding.
   *  <p>If the input is prefixed by a Byte Order Mark needed to determine encoding
   *  (e.g. if the encoding is UTF-16 and the BOM indicates big-endian), then that
   *  BOM will be consumed and not emitted into the output. If the input encoding
   *  is marked with an explicit endianness (e.g. UTF-16-BE), then the BOM is
   *  interpreted as a non-breaking-space and is preserved in the output (including
   *  always for UTF-8).
   *  <p>The end result is that if the input is marked as an explicit endianness the
   *  transcoding is faithful to all codepoints in the source. If it is not marked
   *  with an explicit endianness, the BOM is not considered part of the string itself
   *  but as metadata, and so is not preserved in the output.
   *  <p>Examples:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.unicode_transcode([&quot;Hello&quot;, &quot;TensorFlow&quot;, &quot;2.x&quot;], &quot;UTF-8&quot;, &quot;UTF-16-BE&quot;)
   *  &lt;tf.Tensor: shape=(3,), dtype=string, numpy=
   *  array([b'\x00H\x00e\x00l\x00l\x00o',
   *  b'\x00T\x00e\x00n\x00s\x00o\x00r\x00F\x00l\x00o\x00w',
   *  b'\x002\x00.\x00x'], dtype=object)&gt;
   *  tf.strings.unicode_transcode([&quot;A&quot;, &quot;B&quot;, &quot;C&quot;], &quot;US ASCII&quot;, &quot;UTF-8&quot;).numpy()
   *  array([b'A', b'B', b'C'], dtype=object)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input The text to be processed. Can have any shape.
   * @param inputEncoding Text encoding of the input strings. This is any of the encodings supported
   *  by ICU ucnv algorithmic converters. Examples: {@code "UTF-16", "US ASCII", "UTF-8"}.
   * @param outputEncoding The unicode encoding to use in the output. Must be one of
   *  {@code "UTF-8", "UTF-16-BE", "UTF-32-BE"}. Multi-byte encodings will be big-endian.
   * @param options carries optional attribute values
   * @return a new instance of UnicodeTranscode
   */
  public UnicodeTranscode unicodeTranscode(Operand<TString> input, String inputEncoding,
      String outputEncoding, UnicodeTranscode.Options... options) {
    return UnicodeTranscode.create(scope, input, inputEncoding, outputEncoding, options);
  }

  /**
   * The UnsortedSegmentJoin operation
   *
   * @param inputs The inputs value
   * @param segmentIds The segmentIds value
   * @param numSegments The numSegments value
   * @param options carries optional attribute values
   * @return a new instance of UnsortedSegmentJoin
   */
  public UnsortedSegmentJoin unsortedSegmentJoin(Operand<TString> inputs,
      Operand<? extends TNumber> segmentIds, Operand<? extends TNumber> numSegments,
      UnsortedSegmentJoin.Options... options) {
    return UnsortedSegmentJoin.create(scope, inputs, segmentIds, numSegments, options);
  }

  /**
   * Converts all lowercase characters into their respective uppercase replacements.
   *  Example:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.strings.upper(&quot;CamelCase string and ALL CAPS&quot;)
   *  &lt;tf.Tensor: shape=(), dtype=string, numpy=b'CAMELCASE STRING AND ALL CAPS'&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param input The input to be upper-cased.
   * @param options carries optional attribute values
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
