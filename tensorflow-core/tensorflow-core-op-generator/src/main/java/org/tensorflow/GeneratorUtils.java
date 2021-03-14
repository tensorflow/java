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
package org.tensorflow;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorUtils {


  public static String javaizeName(String name) {
    StringBuilder result = new StringBuilder();
    boolean capNext = Character.isUpperCase(name.charAt(0));
    for (char c : name.toCharArray()) {
      if (c == '_') {
        capNext = true;
      } else if (capNext) {
        result.append(Character.toUpperCase(c));
        capNext = false;
      } else {
        result.append(c);
      }
    }
    name = result.toString();
    switch (name) {
      case "size":
        return "sizeOutput";
      case "if":
        return "ifOutput";
      case "while":
        return "whileOutput";
      case "for":
        return "forOutput";
      case "case":
        return "caseOutput";
      default:
        return name;
    }
  }

  public static String parseDocumentation(String docs) {
    StringBuilder javadoc = new StringBuilder();
    List<String> markdownExprs = Arrays
        .asList("\n+\\*\\s+", "\n{2,}", "`{3,}\\s*[^\\s\n]*\\s*\n", "`+", "\\*{1,2}\\b", "\\[");
    StringJoiner joiner = new StringJoiner("|", "(", ")");
    markdownExprs.forEach(joiner::add);
    Pattern markupExpr = Pattern.compile(joiner.toString());

    Pattern codeBlock = Pattern.compile("(```\\s*\n*)");

    boolean inList = false;
    String input = docs;
    while (true) {
      Matcher m = markupExpr.matcher(input);
      if (m.find()) {
        MatchResult result = m.toMatchResult();

        String text = input.substring(0, result.start());
        input = input.substring(result.end());
        String markup = result.group();

        javadoc.append(text);

        if (markup.startsWith("\n")) {
          javadoc.append("\n");
          if (markup.contains("*")) {
            javadoc.append(inList ? "<li>\n" : "<ul>\n");
            javadoc.append("<li>\n");
            inList = true;
          } else if (inList) {
            javadoc.append("<li>\n<ul>\n");
            inList = false;
          } else if (!input.startsWith("```")) {
            javadoc.append("<p>\n");
          }
        } else if (markup.startsWith("```")) {
          Matcher cb = codeBlock.matcher(input);
          if (cb.find()) {
            result = cb.toMatchResult();
            text = input.substring(0, result.start());
            input = input.substring(result.end());
            javadoc.append("<pre>{@code\n").append(text).append("}</pre>\n");
          } else {
            javadoc.append(markup);
          }
        } else if (markup.startsWith("`")) {
          Matcher cb = Pattern.compile(markup).matcher(input);
          if (cb.find()) {
            result = cb.toMatchResult();
            text = input.substring(0, result.start());
            input = input.substring(result.end());
            javadoc.append("{@code ").append(text).append("}");
          } else {
            javadoc.append(markup);
          }
        } else if (markup.equals("**")) {
          Matcher cb = Pattern.compile("(\\b\\*{2})").matcher(input);
          if (cb.find()) {
            result = cb.toMatchResult();
            text = input.substring(0, result.start());
            input = input.substring(result.end());
            javadoc.append("<b>").append(text).append("</b>");
          } else {
            javadoc.append(markup);
          }
        } else if (markup.equals("*")) {
          Matcher cb = Pattern.compile("(\\b\\*{1})").matcher(input);
          if (cb.find()) {
            result = cb.toMatchResult();
            text = input.substring(0, result.start());
            input = input.substring(result.end());
            javadoc.append("<i>").append(text).append("</i>");
          } else {
            javadoc.append(markup);
          }
        } else if (markup.startsWith("[")) {
          //TODO this seems incorrect, there's a "](" between link and label
          Matcher cb = Pattern.compile("([^\\[]+)\\]\\((http.+)\\)", Pattern.DOTALL).matcher(input);
          if (cb.find()) {
            result = cb.toMatchResult();
            String label = result.group(1);
            String link = result.group(2);
            if (input.startsWith(label + "](" + link)) {
              input = input.substring(label.length() + link.length() + 2);
              javadoc.append("<a href=\"").append(link).append("\">")
                  .append(parseDocumentation(label)).append("</a>");
            } else {
              javadoc.append(markup);
            }
          } else {
            javadoc.append(markup);
          }
        } else {
          javadoc.append(markup);
        }

      } else {
        javadoc.append(input);
        break;
      }
    }

    return javadoc.toString();
  }
}
