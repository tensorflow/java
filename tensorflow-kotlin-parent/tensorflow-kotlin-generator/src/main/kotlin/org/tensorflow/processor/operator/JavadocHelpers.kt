/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow.processor.operator

import com.github.javaparser.javadoc.Javadoc
import com.github.javaparser.javadoc.JavadocBlockTag
import com.github.javaparser.javadoc.description.JavadocDescription
import com.github.javaparser.javadoc.description.JavadocDescriptionElement
import com.github.javaparser.javadoc.description.JavadocInlineTag

private fun JavadocDescription.preParseTransform(): JavadocDescription {
  val transformedElements =
      elements.map {
        if (it is JavadocInlineTag && it.type == JavadocInlineTag.Type.CODE) it.toText()
        else
            it.toText()
                .replace("\r\n", "\n")
                .replace("<pre>", "{@code ")
                .replace("</pre>", "}")
                .replace(Regex("\n?<blockquote>\\s*<blockquote>\\s*<blockquote>\n"), "{@code ")
                .replace(Regex("\n?\\s*</blockquote>\\s*</blockquote>\\s*</blockquote>"), "}")
      }
  return JavadocDescription.parseText(transformedElements.joinToString("").trimIndent())
}

internal fun Javadoc.toKDoc(): String = buildString {
  append(description.toKDoc())
  appendLine()
  appendLine()
  this@toKDoc.blockTags.mapNotNull { it.toKDoc() }.forEach { append(it + "\n") }
}

private inline fun JavadocBlockTag.directToKDoc(mapContent: (String) -> String = { it }) =
    buildString {
  append("@")
  append(this@directToKDoc.tagName)
  append(" ")
  this@directToKDoc.name.ifPresent { append("$it ") }
  append(this@directToKDoc.content.toKDoc().let(mapContent))
}

private fun JavadocBlockTag.toKDoc(): String =
    when (type) {
      JavadocBlockTag.Type.DEPRECATED -> ""
      JavadocBlockTag.Type.SEE ->
          directToKDoc { convertRef(it) } // TODO or does this parse as link?
      JavadocBlockTag.Type.SERIAL -> "Serial: ${content.toKDoc()}"
      JavadocBlockTag.Type.SERIAL_DATA -> "Serial Data: ${content.toKDoc()}"
      JavadocBlockTag.Type.SERIAL_FIELD -> "Serial Field: ${content.toKDoc()}"
      JavadocBlockTag.Type.SINCE -> "Since Java ${content.toKDoc()}"
      JavadocBlockTag.Type.VERSION -> "Version: ${content.toKDoc()}"
      JavadocBlockTag.Type.UNKNOWN ->
          buildString {
            append(this@toKDoc.tagName)
            append(": ")
            this@toKDoc.name.ifPresent { append("$it ") }
            append(this@toKDoc.content.toKDoc())
          }
      else -> directToKDoc()
    }.replace("```", "`")

private fun String.replaceTag(with: String, vararg tags: String) =
    tags.fold(this) { current, tag -> current.replace("<$tag>", with).replace("</$tag>", with) }

// TODO get rid of once KT-46290 is fixed
private fun String.replaceProblematicBrackets() =
    replace(Regex("\\[([^\\]]*.[^\\]*])\\]")) { "&#91;${it.groupValues[1]}&#93;" }

private fun JavadocDescription.toKDoc(): String {
  if (this.isEmpty) return ""
  return preParseTransform()
      .elements
      .joinToString("") { it.toKDoc() }
      .replace("\r\n", "\n")
      .replace("&lt;", "<")
      .replace("&gt;", ">")
      .replaceTag("\n", "p", "br")
      .replaceTag("_", "em", "i")
      .replaceTag("**", "strong", "b")
      .replaceTag("~~", "strike", "del", "s")
      .replace("<blockquote>", "")
      .replace("</blockquote>", "")
      .replace("\\(", "`\\(")
      .replace("\\)", "\\)`")
      .replace(Regex("\n\\s*<a"), "<a")
      .replace("</a>\n", "</a>")
      .replace(Regex("<a href=\"([^\">]+)\">([^<]*)</a>")) {
        "[${it.groupValues[2]}](${it.groupValues[1]})"
      }
}

private fun JavadocDescriptionElement.toKDoc(): String =
    if (this is JavadocInlineTag) this.toKDoc() else this.toText().replaceProblematicBrackets()

private fun convertRef(ref: String) = ref.substringBefore('(').replace("#", ".")

private fun convertLink(link: String): String =
    if (" " in link) {
      val (link, label) = link.split(' ')
      "[$label][${convertRef(link)}]"
    } else {
      "[${convertRef(link)}]"
    }

private val JavadocInlineTag.trimmedContent
  get() = content.trimStart()

private fun makeCodeBlock(content: String): String {
  val stripedContent =
      if (content.startsWith("{@code ")) content.removePrefix("{@code ").removeSuffix("}")
      else content

  val isMultiline = stripedContent.lines().size > 1

  val escapedContent =
      if (isMultiline) stripedContent else stripedContent.replaceProblematicBrackets()

  return if (isMultiline) "```\n$escapedContent\n```" else "`$escapedContent`"
}

internal fun JavadocInlineTag.toKDoc(): String =
    when (type) {
      JavadocInlineTag.Type.CODE -> makeCodeBlock(trimmedContent)
      JavadocInlineTag.Type.DOC_ROOT -> trimmedContent
      JavadocInlineTag.Type.INHERIT_DOC -> trimmedContent
      JavadocInlineTag.Type.LINK -> convertLink(trimmedContent)
      JavadocInlineTag.Type.LINKPLAIN -> convertLink(trimmedContent)
      JavadocInlineTag.Type.LITERAL -> makeCodeBlock(trimmedContent)
      JavadocInlineTag.Type.VALUE -> convertLink(trimmedContent)
      JavadocInlineTag.Type.SYSTEM_PROPERTY -> makeCodeBlock(trimmedContent)
      JavadocInlineTag.Type.UNKNOWN -> trimmedContent
    }
