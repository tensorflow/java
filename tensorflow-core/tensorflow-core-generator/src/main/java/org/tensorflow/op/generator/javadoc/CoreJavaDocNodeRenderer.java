package org.tensorflow.op.generator.javadoc;

import com.google.common.base.CaseFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.BulletList;
import org.commonmark.node.Code;
import org.commonmark.node.Document;
import org.commonmark.node.Emphasis;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Link;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.Node;
import org.commonmark.node.OrderedList;
import org.commonmark.node.Paragraph;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.StrongEmphasis;
import org.commonmark.node.Text;
import org.commonmark.node.ThematicBreak;
import org.commonmark.renderer.NodeRenderer;

/**
 * The node renderer that renders all the core nodes (comes last in the order of node renderers).
 */
public class CoreJavaDocNodeRenderer extends AbstractVisitor implements NodeRenderer {

  private static final String[] html5Tags = {
    "<a>",
    "<abbr>",
    "<address>",
    "<area>",
    "<article>",
    "<aside>",
    "<audio>",
    "<b>",
    "<base>",
    "<bdi>",
    "<bdo>",
    "<blockquote>",
    "<body>",
    "<br>",
    "<button>",
    "<canvas>",
    "<caption>",
    "<cite>",
    "<code>",
    "<col>",
    "<colgroup>",
    "<data>",
    "<datalist>",
    "<dd>",
    "<del>",
    "<details>",
    "<dfn>",
    "<dialog>",
    "<div>",
    "<dl>",
    "<dt>",
    "<em>",
    "<embed>",
    "<fieldset>",
    "<figcaption>",
    "<figure>",
    "<footer>",
    "<form>",
    "<h1>",
    "<h2>",
    "<h3>",
    "<h4>",
    "<h5>",
    "<h6>",
    "<head>",
    "<header>",
    "<hr>",
    "<html>",
    "<i>",
    "<iframe>",
    "<img>",
    "<input>",
    "<ins>",
    "<kbd>",
    "<label>",
    "<legend>",
    "<li>",
    "<link>",
    "<main>",
    "<map>",
    "<mark>",
    "<meta>",
    "<meter>",
    "<nav>",
    "<noscript>",
    "<object>",
    "<ol>",
    "<optgroup>",
    "<option>",
    "<output>",
    "<p>",
    "<param>",
    "<picture>",
    "<pre>",
    "<progress>",
    "<q>",
    "<rp>",
    "<rt>",
    "<ruby>",
    "<s>",
    "<samp>",
    "<script>",
    "<section>",
    "<select>",
    "<small>",
    "<source>",
    "<span>",
    "<strong>",
    "<style>",
    "<sub>",
    "<summary>",
    "<sup>",
    "<svg>",
    "<table>",
    "<tbody>",
    "<td>",
    "<template>",
    "<textarea>",
    "<tfoot>",
    "<th>",
    "<thead>",
    "<time>",
    "<title>",
    "<tr>",
    "<track>",
    "<u>",
    "<ul>",
    "<var>",
    "<video>",
    "<wbr>",
  };
  private static final Set<String> allowedHtml5Tags = new HashSet<>(Arrays.asList(html5Tags));
  private static final Map<String, String> urlLinkConversion =
      new HashMap<String, String>() {
        {
          put("../../../api_docs/python/math_ops", "org.tensorflow.op.MathOps");
          put(
              "https://www.tensorflow.org/api_docs/python/tf/tensor_scatter_nd_update",
              "org.tensorflow.op.Ops#tensorScatterNdUpdate");
        }
      };
  protected final JavaDocNodeRendererContext context;
  private final JavaDocWriter writer;
  private boolean firstParagraph;

  public CoreJavaDocNodeRenderer(JavaDocNodeRendererContext context) {
    this.context = context;
    this.writer = context.getWriter();
  }

  @Override
  public Set<Class<? extends Node>> getNodeTypes() {
    return new HashSet<>(
        Arrays.asList(
            Document.class,
            Heading.class,
            Paragraph.class,
            BlockQuote.class,
            BulletList.class,
            FencedCodeBlock.class,
            HtmlBlock.class,
            ThematicBreak.class,
            IndentedCodeBlock.class,
            Link.class,
            ListItem.class,
            OrderedList.class,
            Image.class,
            Emphasis.class,
            StrongEmphasis.class,
            Text.class,
            Code.class,
            HtmlInline.class,
            SoftLineBreak.class,
            HardLineBreak.class));
  }

  @Override
  public void render(Node node) {
    node.accept(this);
  }

  @Override
  public void visit(Document document) {
    // No rendering itself
    firstParagraph = true;
    visitChildren(document);
  }

  @Override
  public void visit(Heading heading) {
    // cannot use <h1>, JavaDoc complains.
    writer.tag("strong");
    visitChildren(heading);
    writer.tag("/strong");
    writer.tag("br");
  }

  @Override
  public void visit(Paragraph paragraph) {
    boolean inTightList = isInTightList(paragraph);
    if (!inTightList) {
      writer.line();
      if (!firstParagraph) {
        writer.tag("p", getAttrs(paragraph, "p"));
      } else {
        firstParagraph = false;
      }
    }
    visitChildren(paragraph);
    if (!inTightList) {
      // writer.tag("/p");
      writer.line();
    }
  }

  @Override
  public void visit(BlockQuote blockQuote) {
    writer.line();
    writer.tag("blockquote", getAttrs(blockQuote, "blockquote"));
    writer.line();
    visitChildren(blockQuote);
    writer.line();
    writer.tag("/blockquote");
    writer.line();
  }

  @Override
  public void visit(BulletList bulletList) {

    renderListBlock(bulletList, "ul", getAttrs(bulletList, "ul"));
  }

  @Override
  public void visit(FencedCodeBlock fencedCodeBlock) {
    String literal = fencedCodeBlock.getLiteral();
    Map<String, String> attributes = new LinkedHashMap<>();
    String info = fencedCodeBlock.getInfo();
    if (info != null && !info.isEmpty()) {
      int space = info.indexOf(" ");
      String language;
      if (space == -1) {
        language = info;
      } else {
        language = info.substring(0, space);
      }
      attributes.put("class", "language-" + language);
    }
    renderCodeBlock(literal, fencedCodeBlock, attributes);
  }

  @Override
  public void visit(HtmlBlock htmlBlock) {
    writer.line();
    if (context.shouldEscapeHtml()) {
      writer.tag("p", getAttrs(htmlBlock, "p"));
      writer.text(htmlBlock.getLiteral());
      writer.tag("/p");
    } else {
      writer.raw(htmlBlock.getLiteral());
    }
    writer.line();
  }

  @Override
  public void visit(ThematicBreak thematicBreak) {
    writer.line();
    writer.tag("hr", getAttrs(thematicBreak, "hr"), true);
    writer.line();
  }

  @Override
  public void visit(IndentedCodeBlock indentedCodeBlock) {
    renderCodeBlock(
        indentedCodeBlock.getLiteral(), indentedCodeBlock, Collections.emptyMap());
  }

  @Override
  public void visit(Link link) {

    String url = link.getDestination();

    if (url.contains("api_docs/python")) {
      int startIndex = 0;
      int endIndex = url.indexOf("#");
      String opClass;
      String method;
      if (endIndex == -1) {
        String key = url.substring(startIndex);
        opClass = urlLinkConversion.get(key);
        if (opClass == null) {
          handleNormalURL(link);
          return;
        }
      } else {
        String key = url.substring(startIndex, endIndex);
        opClass = urlLinkConversion.get(key);
        if (opClass == null) {
          handleNormalURL(link);
          return;
        }
        method = url.substring(endIndex + 1);
        if (method.contains("_")) {
          method = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, method);
        }
        if (Character.isUpperCase(method.charAt(0))) {
          // change method first char to lower_case.
          char[] c = method.toCharArray();
          c[0] = Character.toLowerCase(c[0]);
          method = new String(c);
        }
        opClass = String.format("%s#%s", opClass, method);
      }
      writer.append(" ");
      visitChildren(link);
      writer.append(String.format(" {@link %s} ", opClass));

    } else {
      handleNormalURL(link);
    }
  }

  private void handleNormalURL(Link link) {
    Map<String, String> attrs = new LinkedHashMap<>();
    String url = link.getDestination();
    if (context.shouldSanitizeUrls()) {
      url = context.urlSanitizer().sanitizeLinkUrl(url);
      attrs.put("rel", "nofollow");
    }
    writer.append(" ");
    url = context.encodeUrl(url);
    attrs.put("href", url);
    if (link.getTitle() != null) {
      attrs.put("title", link.getTitle());
    }
    writer.tag("a", getAttrs(link, "a", attrs));
    visitChildren(link);
    writer.tag("/a");
    writer.append(" ");
  }

  @Override
  public void visit(ListItem listItem) {
    writer.tag("li", getAttrs(listItem, "li"));
    visitChildren(listItem);
    writer.tag("/li");
    writer.line();
  }

  @Override
  public void visit(OrderedList orderedList) {
    int start = orderedList.getStartNumber();
    Map<String, String> attrs = new LinkedHashMap<>();
    if (start != 1) {
      attrs.put("start", String.valueOf(start));
    }
    renderListBlock(orderedList, "ol", getAttrs(orderedList, "ol", attrs));
  }

  @Override
  public void visit(Image image) {
    String url = image.getDestination();

    AltTextVisitor altTextVisitor = new AltTextVisitor();
    image.accept(altTextVisitor);
    String altText = altTextVisitor.getAltText();

    Map<String, String> attrs = new LinkedHashMap<>();
    if (context.shouldSanitizeUrls()) {
      url = context.urlSanitizer().sanitizeImageUrl(url);
    }

    attrs.put("src", context.encodeUrl(url));
    attrs.put("alt", altText);
    if (image.getTitle() != null) {
      attrs.put("title", image.getTitle());
    }

    writer.tag("img", getAttrs(image, "img", attrs), true);
  }

  @Override
  public void visit(Emphasis emphasis) {
    writer.tag("em", getAttrs(emphasis, "em"));
    visitChildren(emphasis);
    writer.tag("/em");
  }

  @Override
  public void visit(StrongEmphasis strongEmphasis) {
    writer.tag("strong", getAttrs(strongEmphasis, "strong"));
    visitChildren(strongEmphasis);
    writer.tag("/strong");
  }

  @Override
  public void visit(Text text) {

    writer.text(text.getLiteral());
  }

  @Override
  public void visit(Code code) {
    writer.text("{@code ");
    // writer.tag("code", getAttrs(code, "code"));
    writer.raw(code.getLiteral());
    writer.text("}");
    // writer.tag("/code");
  }

  @Override
  public void visit(HtmlInline htmlInline) {
    String text = htmlInline.getLiteral();
    // handle non- JavaDoc html, e.g. <bytes>
    String tag = text.replace("\\", "");
    if (!allowedHtml5Tags.contains(tag.toLowerCase())) {
      text = text.replace("<", "&lt;").replace(">", "&gt;");
      writer.raw(text);
    } else if (context.shouldEscapeHtml()) {
      writer.text(text);
    } else {
      writer.raw(text);
    }
  }

  @Override
  public void visit(SoftLineBreak softLineBreak) {
    writer.raw(context.getSoftbreak());
  }

  @Override
  public void visit(HardLineBreak hardLineBreak) {
    writer.tag("br", getAttrs(hardLineBreak, "br"), true);
    writer.line();
  }

  @Override
  protected void visitChildren(Node parent) {
    Node node = parent.getFirstChild();
    while (node != null) {
      Node next = node.getNext();
      context.render(node);
      node = next;
    }
  }

  @SuppressWarnings("unused") // "attributes"
  private void renderCodeBlock(String literal, Node node, Map<String, String> attributes) {
    writer.line();
    // skip empty <pre> block
    if (!literal.isEmpty()) {
      writer.tag("pre", getAttrs(node, "pre"));
      writer.line();
      writer.text(literal);
      writer.line();
      writer.tag("/pre");
      writer.line();
    }
  }

  private void renderListBlock(
      ListBlock listBlock, String tagName, Map<String, String> attributes) {
    writer.line();
    writer.tag(tagName, attributes);
    writer.line();
    visitChildren(listBlock);
    writer.line();
    writer.tag('/' + tagName);
    writer.line();
  }

  private boolean isInTightList(Paragraph paragraph) {
    Node parent = paragraph.getParent();
    if (parent != null) {
      Node gramps = parent.getParent();
      if (gramps instanceof ListBlock) {
        ListBlock list = (ListBlock) gramps;
        return list.isTight();
      }
    }
    return false;
  }

  private Map<String, String> getAttrs(Node node, String tagName) {
    return getAttrs(node, tagName, Collections.emptyMap());
  }

  private Map<String, String> getAttrs(
      Node node, String tagName, Map<String, String> defaultAttributes) {
    return context.extendAttributes(node, tagName, defaultAttributes);
  }

  private static class AltTextVisitor extends AbstractVisitor {

    private final StringBuilder sb = new StringBuilder();

    String getAltText() {
      return sb.toString();
    }

    @Override
    public void visit(Text text) {
      sb.append(text.getLiteral());
    }

    @Override
    public void visit(SoftLineBreak softLineBreak) {
      sb.append('\n');
    }

    @Override
    public void visit(HardLineBreak hardLineBreak) {
      sb.append('\n');
    }
  }
}
