package org.tensorflow.op.generator.javadoc;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import org.commonmark.internal.util.Escaping;

public class JavaDocWriter {

  private static final Map<String, String> NO_ATTRIBUTES = Collections.emptyMap();

  private final Appendable buffer;
  private char lastChar = 0;

  public JavaDocWriter(Appendable out) {
    if (out == null) {
      throw new NullPointerException("out must not be null");
    }
    this.buffer = out;
  }

  public void raw(String s) {
    append(s);
  }

  public void text(String text) {
    append(escapeHtml(text));
  }

  public void tag(String name) {
    tag(name, NO_ATTRIBUTES);
  }

  public void tag(String name, Map<String, String> attrs) {
    tag(name, attrs, false);
  }

  public void tag(String name, Map<String, String> attrs, boolean voidElement) {
    append("<");
    append(name);
    if (attrs != null && !attrs.isEmpty()) {
      for (Map.Entry<String, String> attrib : attrs.entrySet()) {
        append(" ");
        append(escapeHtml(attrib.getKey()));
        append("=\"");
        append(escapeHtml(attrib.getValue()));
        append("\"");
      }
    }
    if (voidElement) {
      append(" /");
    }

    append(">");
  }

  public void line() {
    if (lastChar != 0 && lastChar != '\n') {
      append("\n");
    }
  }

  protected void append(String s) {
    try {
      buffer.append(s);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    int length = s.length();
    if (length != 0) {
      lastChar = s.charAt(length - 1);
    }
  }

  private String escapeHtml(String s) {
    s = Escaping.escapeHtml(s);
    // handle oddities from tensorflow python markup that don't work well in JavaDoc.
    if (s.contains("@compatibility")) {
      s = s.replace("@", "{@literal @}") + "<br>";
    } else if (s.contains("@end_compatibility")) {
      s = "<br>" + s.replace("@", "{@literal @}");
    } else if (s.contains("@tf.")) {
      s = s.replace("@", "{@literal @}");
    } else if (s.contains("<bytes>")) {
      s = s.replace("<bytes>", "%lt;bytes&gt;");
    }
    return s;
  }
}
