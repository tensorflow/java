/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow.op.generator;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.tensorflow.op.generator.javadoc.JavaDocRenderer;
import org.tensorflow.proto.framework.OpDef.ArgDef;

/** Utilities for op generation */
final class GeneratorUtils {

  private static final Parser parser = Parser.builder().build();

  /**
   * Convert a Python style name to a Java style name.
   *
   * <p>Does snake_case -> camelCase and handles keywords.
   *
   * <p>Not valid for class names, meant for fields and methods.
   *
   * <p>Generally you should use {@link ClassGenerator#getJavaName(ArgDef)}.
   */
  static String javaizeMemberName(String name) {
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

  /**
   * Get the name of the Ops method, or null to not specify one (the decapitalized class name will
   * be used).
   */
  static String getOpMethodName(String className) {
    switch (className) {
      case "If":
        return "ifOp";
      case "While":
        return "whileOp";
      case "For":
        return "forOp";
      case "Case":
        return "caseOp";
      default:
        return null;
    }
  }

  /** Convert markdown descriptions to JavaDocs. */
  static String parseDocumentation(String docs) {
    Node document = parser.parse(docs);
    JavaDocRenderer renderer = JavaDocRenderer.builder().build();
    return renderer.render(document).trim();
  }
}
