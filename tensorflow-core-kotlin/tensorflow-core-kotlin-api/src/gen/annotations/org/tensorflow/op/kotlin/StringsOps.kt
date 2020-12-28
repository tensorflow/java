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
package org.tensorflow.op.kotlin

import kotlin.jvm.JvmName
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.strings.Join
import org.tensorflow.op.strings.Lower
import org.tensorflow.op.strings.ReduceJoin
import org.tensorflow.op.strings.RegexFullMatch
import org.tensorflow.op.strings.RegexReplace
import org.tensorflow.op.strings.StringFormat
import org.tensorflow.op.strings.StringLength
import org.tensorflow.op.strings.StringNGrams
import org.tensorflow.op.strings.StringSplit
import org.tensorflow.op.strings.Strip
import org.tensorflow.op.strings.Substr
import org.tensorflow.op.strings.ToHashBucket
import org.tensorflow.op.strings.ToHashBucketFast
import org.tensorflow.op.strings.ToHashBucketStrong
import org.tensorflow.op.strings.ToNumber
import org.tensorflow.op.strings.UnicodeScript
import org.tensorflow.op.strings.UnicodeTranscode
import org.tensorflow.op.strings.UnsortedSegmentJoin
import org.tensorflow.op.strings.Upper
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber

/**
 * An API for building `strings` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class StringsOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.StringsOps = ops.java.strings

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Joins the strings in the given list of string tensors into one tensor;
     *  
     *  with the given separator (default is an empty separator).
     *  
     *  Examples:
     *  
     *  >>> s = &#91;"hello", "world", "tensorflow"]
     *  >>> tf.strings.join(s, " ")
     *  <tf.Tensor: shape=(), dtype=string, numpy=b'hello world tensorflow'>
     * 
     * @param inputs A list of string tensors.  The tensors must all have the same shape,
     *  or be scalars.  Scalars may be mixed in; these will be broadcast to the shape
     *  of non-scalar inputs.
     * @param options carries optional attributes values
     * @return a new instance of Join
     * @see org.tensorflow.op.StringsOps.join
     * @param separator string, an optional join separator.
     */
    public fun join(inputs: Iterable<Operand<TString>>, separator: String? = null): Join =
            java.join(    
        inputs,
        *listOfNotNull(
            separator?.let{ org.tensorflow.op.strings.Join.separator(it) }
        ).toTypedArray()
        )

    /**
     * Converts all uppercase characters into their respective lowercase replacements.
     *  
     *  Example:
     *  
     *  >>> tf.strings.lower("CamelCase string and ALL CAPS")
     *  <tf.Tensor: shape=(), dtype=string, numpy=b'camelcase string and all caps'>
     * 
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of Lower
     * @see org.tensorflow.op.StringsOps.lower
     * @param encoding @param encoding
     */
    public fun lower(input: Operand<TString>, encoding: String? = null): Lower = java.lower(    
        input,
        *listOfNotNull(
            encoding?.let{ org.tensorflow.op.strings.Lower.encoding(it) }
        ).toTypedArray()
        )

    /**
     * Joins a string Tensor across the given dimensions.
     *  
     *  Computes the string join across dimensions in the given string Tensor of shape
     *  `&#91;\\(d_0, d_1, ..., d_{n-1}\\)]`.  Returns a new Tensor created by joining the input
     *  strings with the given separator (default: empty string).  Negative indices are
     *  counted backwards from the end, with `-1` being equivalent to `n - 1`.  If
     *  indices are not specified, joins across all dimensions beginning from `n - 1`
     *  through `0`.
     *  
     *  For example:
     *  ```
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
     *  ```
     * 
     * 
     * @param inputs The input to be joined.  All reduced indices must have non-zero size.
     * @param reductionIndices The dimensions to reduce over.  Dimensions are reduced in the
     *  order specified.  Omitting `reduction_indices` is equivalent to passing
     *  `&#91;n-1, n-2, ..., 0]`.  Negative indices from `-n` to `-1` are supported.
     * @param options carries optional attributes values
     * @return a new instance of ReduceJoin
     * @see org.tensorflow.op.StringsOps.reduceJoin
     * @param keepDims If `True`, retain reduced dimensions with length `1`.
     * @param separator The separator to use when joining.
     */
    public fun reduceJoin(
        inputs: Operand<TString>,
        reductionIndices: Operand<TInt32>,
        keepDims: Boolean? = null,
        separator: String? = null
    ): ReduceJoin = java.reduceJoin(    
        inputs,
        reductionIndices,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.strings.ReduceJoin.keepDims(it) },
            separator?.let{ org.tensorflow.op.strings.ReduceJoin.separator(it) }
        ).toTypedArray()
        )

    /**
     * Check if the input matches the regex pattern.
     *  
     *  The input is a string tensor of any shape. The pattern is a scalar
     *  string tensor which is applied to every element of the input tensor.
     *  The boolean values (True or False) of the output tensor indicate
     *  if the input matches the regex pattern provided.
     *  
     *  The pattern follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
     *  
     *  Examples:
     *  
     *  >>> tf.strings.regex_full_match(&#91;"TF lib", "lib TF"], ".*lib$")
     *  <tf.Tensor: shape=(2,), dtype=bool, numpy=array(&#91; True, False])>
     *  >>> tf.strings.regex_full_match(&#91;"TF lib", "lib TF"], ".*TF$")
     *  <tf.Tensor: shape=(2,), dtype=bool, numpy=array(&#91;False,  True])>
     * 
     * @param input A string tensor of the text to be processed.
     * @param pattern A scalar string tensor containing the regular expression to match the input.
     * @return a new instance of RegexFullMatch
     * @see org.tensorflow.op.StringsOps.regexFullMatch
     */
    public fun regexFullMatch(input: Operand<TString>, pattern: Operand<TString>): RegexFullMatch =
            java.regexFullMatch(    
        input,
        pattern
        )

    /**
     * Replaces matches of the `pattern` regular expression in `input` with the
     *  replacement string provided in `rewrite`.
     *  
     *  It follows the re2 syntax (https://github.com/google/re2/wiki/Syntax)
     * 
     * @param input The text to be processed.
     * @param pattern The regular expression to be matched in the `input` strings.
     * @param rewrite The rewrite string to be substituted for the `pattern` expression where it is
     *  matched in the `input` strings.
     * @param options carries optional attributes values
     * @return a new instance of RegexReplace
     * @see org.tensorflow.op.StringsOps.regexReplace
     * @param replaceGlobal If True, the replacement is global (that is, all matches of the
     * `pattern` regular
     *  expression in each input string are rewritten), otherwise the `rewrite`
     *  substitution is only made for the first `pattern` match.
     */
    public fun regexReplace(
        input: Operand<TString>,
        pattern: Operand<TString>,
        rewrite: Operand<TString>,
        replaceGlobal: Boolean? = null
    ): RegexReplace = java.regexReplace(    
        input,
        pattern,
        rewrite,
        *listOfNotNull(
            replaceGlobal?.let{ org.tensorflow.op.strings.RegexReplace.replaceGlobal(it) }
        ).toTypedArray()
        )

    /**
     * Formats a string template using a list of tensors.
     *  
     *  Formats a string template using a list of tensors, pretty-printing tensor summaries.
     * 
     * @param inputs The list of tensors to format into the placeholder string.
     * @param options carries optional attributes values
     * @return a new instance of StringFormat
     * @see org.tensorflow.op.StringsOps.stringFormat
     * @param template A string, the template to format tensor summaries into.
     * @param placeholder A string, at each placeholder in the template a subsequent tensor summary
     * will be inserted.
     * @param summarize When formatting the tensor summaries print the first and last summarize
     * entries of each tensor dimension.
     */
    public fun stringFormat(
        inputs: Iterable<Operand<*>>,
        template: String? = null,
        placeholder: String? = null,
        summarize: Long? = null
    ): StringFormat = java.stringFormat(    
        inputs,
        *listOfNotNull(
            template?.let{ org.tensorflow.op.strings.StringFormat.template(it) },
            placeholder?.let{ org.tensorflow.op.strings.StringFormat.placeholder(it) },
            summarize?.let{ org.tensorflow.op.strings.StringFormat.summarize(it) }
        ).toTypedArray()
        )

    /**
     * String lengths of `input`.
     *  
     *  Computes the length of each string given in the input tensor.
     *  
     *  >>> strings = tf.constant(&#91;'Hello','TensorFlow', '\U0001F642'])
     *  >>> tf.strings.length(strings).numpy() # default counts bytes
     *  array(&#91; 5, 10, 4], dtype=int32)
     *  >>> tf.strings.length(strings, unit="UTF8_CHAR").numpy()
     *  array(&#91; 5, 10, 1], dtype=int32)
     * 
     * @param input The strings for which to compute the length for each element.
     * @param options carries optional attributes values
     * @return a new instance of StringLength
     * @see org.tensorflow.op.StringsOps.stringLength
     * @param unit The unit that is counted to compute string length.  One of: `"BYTE"` (for
     *  the number of bytes in each string) or `"UTF8_CHAR"` (for the number of UTF-8
     *  encoded Unicode code points in each string).  Results are undefined
     *  if `unit=UTF8_CHAR` and the `input` strings do not contain structurally
     *  valid UTF-8.
     */
    public fun stringLength(input: Operand<TString>, unit: String? = null): StringLength =
            java.stringLength(    
        input,
        *listOfNotNull(
            unit?.let{ org.tensorflow.op.strings.StringLength.unit(it) }
        ).toTypedArray()
        )

    /**
     * Creates ngrams from ragged string data.
     *  
     *  This op accepts a ragged tensor with 1 ragged dimension containing only
     *  strings and outputs a ragged tensor with 1 ragged dimension containing ngrams
     *  of that string, joined along the innermost axis.
     * 
     * @param T data type for ` ngramsSplits()` output
     * @param data The values tensor of the ragged string tensor to make ngrams out of. Must be a
     *  1D string tensor.
     * @param dataSplits The splits tensor of the ragged string tensor to make ngrams out of.
     * @param separator The string to append between elements of the token. Use "" for no
     * separator.
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
     * @see org.tensorflow.op.StringsOps.stringNGrams
     */
    public fun <T : TNumber> stringNGrams(
        `data`: Operand<TString>,
        dataSplits: Operand<T>,
        separator: String,
        ngramWidths: List<Long>,
        leftPad: String,
        rightPad: String,
        padWidth: Long,
        preserveShortSequences: Boolean
    ): StringNGrams<T> = java.stringNGrams<T>(    
        data,
        dataSplits,
        separator,
        ngramWidths,
        leftPad,
        rightPad,
        padWidth,
        preserveShortSequences
        )

    /**
     * Split elements of `source` based on `sep` into a `SparseTensor`.
     *  
     *  Let N be the size of source (typically N will be the batch size). Split each
     *  element of `source` based on `sep` and return a `SparseTensor`
     *  containing the split tokens. Empty tokens are ignored.
     *  
     *  For example, N = 2, source&#91;0] is 'hello world' and source&#91;1] is 'a b c',
     *  then the output will be
     *  ```
     *  st.indices = [0, 0;
     *                0, 1;
     *                1, 0;
     *                1, 1;
     *                1, 2]
     *  st.shape = [2, 3]
     *  st.values = ['hello', 'world', 'a', 'b', 'c']
     *  ```
     * 
     *  If `sep` is given, consecutive delimiters are not grouped together and are
     *  deemed to delimit empty strings. For example, source of `"1<>2<><>3"` and
     *  sep of `"<>"` returns `&#91;"1", "2", "", "3"]`. If `sep` is None or an empty
     *  string, consecutive whitespace are regarded as a single separator, and the
     *  result will contain no empty strings at the startor end if the string has
     *  leading or trailing whitespace.
     *  
     *  Note that the above mentioned behavior matches python's str.split.
     * 
     * @param input `1-D` string `Tensor`, the strings to split.
     * @param sep `0-D` string `Tensor`, the delimiter character.
     * @param options carries optional attributes values
     * @return a new instance of StringSplit
     * @see org.tensorflow.op.StringsOps.stringSplit
     * @param maxsplit An `int`. If `maxsplit > 0`, limit of the split of the result.
     */
    public fun stringSplit(
        input: Operand<TString>,
        sep: Operand<TString>,
        maxsplit: Long? = null
    ): StringSplit = java.stringSplit(    
        input,
        sep,
        *listOfNotNull(
            maxsplit?.let{ org.tensorflow.op.strings.StringSplit.maxsplit(it) }
        ).toTypedArray()
        )

    /**
     * Strip leading and trailing whitespaces from the Tensor.
     * 
     * @param input A string `Tensor` of any shape.
     * @return a new instance of Strip
     * @see org.tensorflow.op.StringsOps.strip
     */
    public fun strip(input: Operand<TString>): Strip = java.strip(    
        input
        )

    /**
     * Return substrings from `Tensor` of strings.
     *  
     *  For each string in the input `Tensor`, creates a substring starting at index
     *  `pos` with a total length of `len`.
     *  
     *  If `len` defines a substring that would extend beyond the length of the input
     *  string, or if `len` is negative, then as many characters as possible are used.
     *  
     *  A negative `pos` indicates distance within the string backwards from the end.
     *  
     *  If `pos` specifies an index which is out of range for any of the input strings,
     *  then an `InvalidArgumentError` is thrown.
     *  
     *  `pos` and `len` must have the same shape, otherwise a `ValueError` is thrown on
     *  Op creation.
     *  
     *  <i>NOTE</i>: `strings.Substr` supports broadcasting up to two dimensions. More about
     *  broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *  
     *  ---
     *  
     *  Examples
     *  
     *  Using scalar `pos` and `len`:
     *  ```
     *  input = [b'Hello', b'World']
     *  position = 1
     *  length = 3
     * 
     *  output = [b'ell', b'orl']
     *  ```
     * 
     *  Using `pos` and `len` with same shape as `input`:
     *  ```
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
     *  ```
     * 
     *  Broadcasting `pos` and `len` onto `input`:
     *  ```
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
     *  ```
     * 
     *  Broadcasting `input` onto `pos` and `len`:
     *  ```
     *  input = b'thirteen'
     *  position = [1, 5, 7]
     *  length =   [3, 2, 1]
     * 
     *  output = [b'hir', b'ee', b'n']
     *  ```
     * 
     *  Raises:
     *  
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
     * @see org.tensorflow.op.StringsOps.substr
     * @param unit The unit that is used to create the substring.  One of: `"BYTE"` (for
     *  defining position and length by bytes) or `"UTF8_CHAR"` (for the UTF-8
     *  encoded Unicode code points).  The default is `"BYTE"`. Results are undefined if
     *  `unit=UTF8_CHAR` and the `input` strings do not contain structurally valid
     *  UTF-8.
     */
    public fun <T : TNumber> substr(
        input: Operand<TString>,
        pos: Operand<T>,
        len: Operand<T>,
        unit: String? = null
    ): Substr = java.substr<T>(    
        input,
        pos,
        len,
        *listOfNotNull(
            unit?.let{ org.tensorflow.op.strings.Substr.unit(it) }
        ).toTypedArray()
        )

    /**
     * Converts each string in the input Tensor to its hash mod by a number of buckets.
     *  
     *  The hash function is deterministic on the content of the string within the
     *  process.
     *  
     *  Note that the hash function may change from time to time.
     *  This functionality will be deprecated and it's recommended to use
     *  `tf.string_to_hash_bucket_fast()` or `tf.string_to_hash_bucket_strong()`.
     * 
     * @param stringTensor
     * @param numBuckets The number of buckets.
     * @return a new instance of ToHashBucket
     * @see org.tensorflow.op.StringsOps.toHashBucket
     */
    public fun toHashBucket(stringTensor: Operand<TString>, numBuckets: Long): ToHashBucket =
            java.toHashBucket(    
        stringTensor,
        numBuckets
        )

    /**
     * Converts each string in the input Tensor to its hash mod by a number of buckets.
     *  
     *  The hash function is deterministic on the content of the string within the
     *  process and will never change. However, it is not suitable for cryptography.
     *  This function may be used when CPU time is scarce and inputs are trusted or
     *  unimportant. There is a risk of adversaries constructing inputs that all hash
     *  to the same bucket. To prevent this problem, use a strong hash function with
     *  `tf.string_to_hash_bucket_strong`.
     *  
     *  Examples:
     *  
     *  >>> tf.strings.to_hash_bucket_fast(&#91;"Hello", "TensorFlow", "2.x"], 3).numpy()
     *  array(&#91;0, 2, 2])
     * 
     * @param input The strings to assign a hash bucket.
     * @param numBuckets The number of buckets.
     * @return a new instance of ToHashBucketFast
     * @see org.tensorflow.op.StringsOps.toHashBucketFast
     */
    public fun toHashBucketFast(input: Operand<TString>, numBuckets: Long): ToHashBucketFast =
            java.toHashBucketFast(    
        input,
        numBuckets
        )

    /**
     * Converts each string in the input Tensor to its hash mod by a number of buckets.
     *  
     *  The hash function is deterministic on the content of the string within the
     *  process. The hash function is a keyed hash function, where attribute `key`
     *  defines the key of the hash function. `key` is an array of 2 elements.
     *  
     *  A strong hash is important when inputs may be malicious, e.g. URLs with
     *  additional components. Adversaries could try to make their inputs hash to the
     *  same bucket for a denial-of-service attack or to skew the results. A strong
     *  hash can be used to make it difficult to find inputs with a skewed hash value
     *  distribution over buckets. This requires that the hash function is
     *  seeded by a high-entropy (random) "key" unknown to the adversary.
     *  
     *  The additional robustness comes at a cost of roughly 4x higher compute
     *  time than `tf.string_to_hash_bucket_fast`.
     *  
     *  Examples:
     *  
     *  >>> tf.strings.to_hash_bucket_strong(&#91;"Hello", "TF"], 3, &#91;1, 2]).numpy()
     *  array(&#91;2, 0])
     * 
     * @param input The strings to assign a hash bucket.
     * @param numBuckets The number of buckets.
     * @param key The key used to seed the hash function, passed as a list of two uint64
     *  elements.
     * @return a new instance of ToHashBucketStrong
     * @see org.tensorflow.op.StringsOps.toHashBucketStrong
     */
    public fun toHashBucketStrong(
        input: Operand<TString>,
        numBuckets: Long,
        key: List<Long>
    ): ToHashBucketStrong = java.toHashBucketStrong(    
        input,
        numBuckets,
        key
        )

    /**
     * Converts each string in the input Tensor to the specified numeric type.
     *  
     *  (Note that int32 overflow results in an error while float overflow
     *  results in a rounded value.)
     *  
     *  Example:
     *  
     *  >>> strings = &#91;"5.0", "3.0", "7.0"]
     *  >>> tf.strings.to_number(strings)
     *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array(&#91;5., 3., 7.], dtype=float32)>
     * 
     * @param T data type for ` output()` output
     * @param stringTensor
     * @return a new instance of ToNumber
     * @see org.tensorflow.op.StringsOps.toNumber
     */
    public fun toNumber(stringTensor: Operand<TString>): ToNumber<TFloat32> = java.toNumber(    
        stringTensor
        )

    /**
     * Converts each string in the input Tensor to the specified numeric type.
     *  
     *  (Note that int32 overflow results in an error while float overflow
     *  results in a rounded value.)
     *  
     *  Example:
     *  
     *  >>> strings = &#91;"5.0", "3.0", "7.0"]
     *  >>> tf.strings.to_number(strings)
     *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array(&#91;5., 3., 7.], dtype=float32)>
     * 
     * @param T data type for ` output()` output
     * @param stringTensor
     * @param outType The numeric type to interpret each string in `string_tensor` as.
     * @return a new instance of ToNumber
     * @see org.tensorflow.op.StringsOps.toNumber
     */
    public fun <T : TNumber> toNumber(stringTensor: Operand<TString>, outType: Class<T>):
            ToNumber<T> = java.toNumber<T>(    
        stringTensor,
        outType
        )

    /**
     * Determine the script codes of a given tensor of Unicode integer code points.
     *  
     *  This operation converts Unicode code points to script codes corresponding to
     *  each code point. Script codes correspond to International Components for
     *  Unicode (ICU) UScriptCode values. See http://icu-project.org/apiref/icu4c/uscript_8h.html.
     *  Returns -1 (USCRIPT_INVALID_CODE) for invalid codepoints. Output shape will
     *  match input shape.
     *  
     *  Examples:
     *  
     *  >>> tf.strings.unicode_script(&#91;1, 31, 38])
     *  <tf.Tensor: shape=(3,), dtype=int32, numpy=array(&#91;0, 0, 0], dtype=int32)>
     * 
     * @param input A Tensor of int32 Unicode code points.
     * @return a new instance of UnicodeScript
     * @see org.tensorflow.op.StringsOps.unicodeScript
     */
    public fun unicodeScript(input: Operand<TInt32>): UnicodeScript = java.unicodeScript(    
        input
        )

    /**
     * Transcode the input text from a source encoding to a destination encoding.
     *  
     *  The input is a string tensor of any shape. The output is a string tensor of
     *  the same shape containing the transcoded strings. Output strings are always
     *  valid unicode. If the input contains invalid encoding positions, the
     *  `errors` attribute sets the policy for how to deal with them. If the default
     *  error-handling policy is used, invalid formatting will be substituted in the
     *  output by the `replacement_char`. If the errors policy is to `ignore`, any
     *  invalid encoding positions in the input are skipped and not included in the
     *  output. If it set to `strict` then any invalid formatting will result in an
     *  InvalidArgument error.
     *  
     *  This operation can be used with `output_encoding = input_encoding` to enforce
     *  correct formatting for inputs even if they are already in the desired encoding.
     *  
     *  If the input is prefixed by a Byte Order Mark needed to determine encoding
     *  (e.g. if the encoding is UTF-16 and the BOM indicates big-endian), then that
     *  BOM will be consumed and not emitted into the output. If the input encoding
     *  is marked with an explicit endianness (e.g. UTF-16-BE), then the BOM is
     *  interpreted as a non-breaking-space and is preserved in the output (including
     *  always for UTF-8).
     *  
     *  The end result is that if the input is marked as an explicit endianness the
     *  transcoding is faithful to all codepoints in the source. If it is not marked
     *  with an explicit endianness, the BOM is not considered part of the string itself
     *  but as metadata, and so is not preserved in the output.
     *  
     *  Examples:
     *  
     *  >>> tf.strings.unicode_transcode(&#91;"Hello", "TensorFlow", "2.x"], "UTF-8", "UTF-16-BE")
     *  <tf.Tensor: shape=(3,), dtype=string, numpy=
     *  array(&#91;b'\x00H\x00e\x00l\x00l\x00o',
     *         b'\x00T\x00e\x00n\x00s\x00o\x00r\x00F\x00l\x00o\x00w',
     *         b'\x002\x00.\x00x'], dtype=object)>
     *  >>> tf.strings.unicode_transcode(&#91;"A", "B", "C"], "US ASCII", "UTF-8").numpy()
     *  array(&#91;b'A', b'B', b'C'], dtype=object)
     * 
     * @param input The text to be processed. Can have any shape.
     * @param inputEncoding Text encoding of the input strings. This is any of the encodings
     * supported
     *  by ICU ucnv algorithmic converters. Examples: `"UTF-16", "US ASCII", "UTF-8"`.
     * @param outputEncoding The unicode encoding to use in the output. Must be one of
     *  `"UTF-8", "UTF-16-BE", "UTF-32-BE"`. Multi-byte encodings will be big-endian.
     * @param options carries optional attributes values
     * @return a new instance of UnicodeTranscode
     * @see org.tensorflow.op.StringsOps.unicodeTranscode
     * @param errors Error handling policy when there is invalid formatting found in the input.
     *  The value of 'strict' will cause the operation to produce a InvalidArgument
     *  error on any invalid input formatting. A value of 'replace' (the default) will
     *  cause the operation to replace any invalid formatting in the input with the
     *  `replacement_char` codepoint. A value of 'ignore' will cause the operation to
     *  skip any invalid formatting in the input and produce no corresponding output
     *  character.
     * @param replacementChar The replacement character codepoint to be used in place of any
     * invalid
     *  formatting in the input when `errors='replace'`. Any valid unicode codepoint may
     *  be used. The default value is the default unicode replacement character is
     *  0xFFFD or U+65533.)
     *  
     *  Note that for UTF-8, passing a replacement character expressible in 1 byte, such
     *  as ' ', will preserve string alignment to the source since invalid bytes will be
     *  replaced with a 1-byte replacement. For UTF-16-BE and UTF-16-LE, any 1 or 2 byte
     *  replacement character will preserve byte alignment to the source.
     * @param replaceControlCharacters Whether to replace the C0 control characters (00-1F) with
     * the
     *  `replacement_char`. Default is false.
     */
    public fun unicodeTranscode(
        input: Operand<TString>,
        inputEncoding: String,
        outputEncoding: String,
        errors: String? = null,
        replacementChar: Long? = null,
        replaceControlCharacters: Boolean? = null
    ): UnicodeTranscode = java.unicodeTranscode(    
        input,
        inputEncoding,
        outputEncoding,
        *listOfNotNull(
            errors?.let{ org.tensorflow.op.strings.UnicodeTranscode.errors(it) },
            replacementChar?.let{ org.tensorflow.op.strings.UnicodeTranscode.replacementChar(it) },
            replaceControlCharacters?.let{
            org.tensorflow.op.strings.UnicodeTranscode.replaceControlCharacters(it) }
        ).toTypedArray()
        )

    /**
     * Joins the elements of `inputs` based on `segment_ids`.
     *  
     *  Computes the string join along segments of a tensor.
     *  Given `segment_ids` with rank `N` and `data` with rank `N+M`:
     *  
     *      `output&#91;i, k1...kM] = strings.join(&#91;data&#91;j1...jN, k1...kM])`
     *  
     *  where the join is over all &#91;j1...jN] such that segment_ids&#91;j1...jN] = i.
     *  Strings are joined in row-major order.
     *  
     *  For example:
     *  ```
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
     *  ```
     * 
     * 
     * @param inputs The input to be joined.
     * @param segmentIds A tensor whose shape is a prefix of data.shape.  Negative segment ids are
     * not
     *  supported.
     * @param numSegments A scalar.
     * @param options carries optional attributes values
     * @return a new instance of UnsortedSegmentJoin
     * @see org.tensorflow.op.StringsOps.unsortedSegmentJoin
     * @param separator The separator to use when joining.
     */
    public fun <T : TNumber, U : TNumber> unsortedSegmentJoin(
        inputs: Operand<TString>,
        segmentIds: Operand<T>,
        numSegments: Operand<U>,
        separator: String? = null
    ): UnsortedSegmentJoin = java.unsortedSegmentJoin<T, U>(    
        inputs,
        segmentIds,
        numSegments,
        *listOfNotNull(
            separator?.let{ org.tensorflow.op.strings.UnsortedSegmentJoin.separator(it) }
        ).toTypedArray()
        )

    /**
     * Converts all lowercase characters into their respective uppercase replacements.
     *  
     *  Example:
     *  
     *  >>> tf.strings.upper("CamelCase string and ALL CAPS")
     *  <tf.Tensor: shape=(), dtype=string, numpy=b'CAMELCASE STRING AND ALL CAPS'>
     * 
     * @param input
     * @param options carries optional attributes values
     * @return a new instance of Upper
     * @see org.tensorflow.op.StringsOps.upper
     * @param encoding @param encoding
     */
    public fun upper(input: Operand<TString>, encoding: String? = null): Upper = java.upper(    
        input,
        *listOfNotNull(
            encoding?.let{ org.tensorflow.op.strings.Upper.encoding(it) }
        ).toTypedArray()
        )

    /**
     * Converts each string in the input Tensor to the specified numeric type.
     *  
     *  (Note that int32 overflow results in an error while float overflow
     *  results in a rounded value.)
     *  
     *  Example:
     *  
     *  >>> strings = &#91;"5.0", "3.0", "7.0"]
     *  >>> tf.strings.to_number(strings)
     *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array(&#91;5., 3., 7.], dtype=float32)>
     * 
     * @param T data type for ` output()` output
     * @param stringTensor
     * @param outType The numeric type to interpret each string in `string_tensor` as.
     * @return a new instance of ToNumber
     * @see org.tensorflow.op.StringsOps.toNumber
     */
    @JvmName("toNumberReified")
    public inline fun <reified T : TNumber> toNumberTyped(stringTensor: Operand<TString>):
            ToNumber<T> = toNumber<T>(stringTensor, T::class.java)
}
