/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.op.generator.javadoc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.commonmark.Extension;
import org.commonmark.internal.renderer.NodeRendererMap;
import org.commonmark.internal.util.Escaping;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.DefaultUrlSanitizer;
import org.commonmark.renderer.html.UrlSanitizer;

/**
 * Renders a tree of nodes to JavaDoc format.
 *
 * <p>Start with the {@link #builder} method to configure the renderer. Example:
 *
 * <pre><code>
 * JavaDocRenderer renderer = JavaDocRenderer.builder().escapeHtml(true).build();
 * renderer.render(node);
 * </code></pre>
 */
public class JavaDocRenderer implements Renderer {

  private final String softbreak;
  private final boolean escapeJavaDoc;
  private final boolean sanitizeUrls;
  private final UrlSanitizer urlSanitizer;
  private final boolean percentEncodeUrls;
  private final List<AttributeProviderFactory> attributeProviderFactories;
  private final List<JavaDocNodeRendererFactory> nodeRendererFactories;

  private JavaDocRenderer(Builder builder) {
    this.softbreak = builder.softbreak;
    this.escapeJavaDoc = builder.escapeJavaDoc;
    this.sanitizeUrls = builder.sanitizeUrls;
    this.percentEncodeUrls = builder.percentEncodeUrls;
    this.urlSanitizer = builder.urlSanitizer;
    this.attributeProviderFactories = new ArrayList<>(builder.attributeProviderFactories);

    this.nodeRendererFactories = new ArrayList<>(builder.nodeRendererFactories.size() + 1);
    this.nodeRendererFactories.addAll(builder.nodeRendererFactories);
    // Add as last. This means clients can override the rendering of core nodes if they want.
    this.nodeRendererFactories.add(
        new JavaDocNodeRendererFactory() {
          @Override
          public NodeRenderer create(JavaDocNodeRendererContext context) {
            return new CoreJavaDocNodeRenderer(context);
          }
        });
  }

  /**
   * Create a new builder for configuring an {@link JavaDocRenderer}.
   *
   * @return a builder
   */
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public void render(Node node, Appendable output) {
    if (node == null) {
      throw new NullPointerException("node must not be null");
    }
    RendererContext context = new RendererContext(new JavaDocWriter(output));
    context.render(node);
  }

  @Override
  public String render(Node node) {
    if (node == null) {
      throw new NullPointerException("node must not be null");
    }
    StringBuilder sb = new StringBuilder();
    render(node, sb);
    return sb.toString();
  }

  /**
   * Extension for {@link JavaDocRenderer}.
   */
  public interface JavaDocRendererExtension extends Extension {

    void extend(Builder rendererBuilder);
  }

  /**
   * Builder for configuring an {@link JavaDocRenderer}. See methods for default configuration.
   */
  public static class Builder {

    private final List<AttributeProviderFactory> attributeProviderFactories = new ArrayList<>();
    private final List<JavaDocNodeRendererFactory> nodeRendererFactories = new ArrayList<>();
    private String softbreak = "\n";
    private boolean escapeJavaDoc = false;
    private boolean sanitizeUrls = false;
    private UrlSanitizer urlSanitizer = new DefaultUrlSanitizer();
    private boolean percentEncodeUrls = false;

    /**
     * @return the configured {@link JavaDocRenderer}
     */
    public JavaDocRenderer build() {
      return new JavaDocRenderer(this);
    }

    /**
     * The HTML to use for rendering a softbreak, defaults to {@code "\n"} (meaning the rendered result doesn't have a
     * line break).
     *
     * <p>Set it to {@code "<br>"} (or {@code "<br />"} to make them hard breaks.
     *
     * <p>Set it to {@code " "} to ignore line wrapping in the source.
     *
     * @param softbreak HTML for softbreak
     * @return {@code this}
     */
    public Builder softbreak(String softbreak) {
      this.softbreak = softbreak;
      return this;
    }

    /**
     * Whether {@link HtmlInline} and {@link HtmlBlock} should be escaped, defaults to {@code false}.
     *
     * <p>Note that {@link HtmlInline} is only a tag itself, not the text between an opening tag and
     * a closing tag. So markup in the text will be parsed as normal and is not affected by this option.
     *
     * @param escapeJavaDoc true for escaping, false for preserving raw HTML
     * @return {@code this}
     */
    public Builder escapeJavaDoc(boolean escapeJavaDoc) {
      this.escapeJavaDoc = escapeJavaDoc;
      return this;
    }

    /**
     * Whether {@link Image} src and {@link Link} href should be sanitized, defaults to {@code false}.
     *
     * @param sanitizeUrls true for sanitization, false for preserving raw attribute
     * @return {@code this}
     * @since 0.14.0
     */
    public Builder sanitizeUrls(boolean sanitizeUrls) {
      this.sanitizeUrls = sanitizeUrls;
      return this;
    }

    /**
     * {@link UrlSanitizer} used to filter URL's if {@link #sanitizeUrls} is true.
     *
     * @param urlSanitizer Filterer used to filter {@link Image} src and {@link Link}.
     * @return {@code this}
     * @since 0.14.0
     */
    public Builder urlSanitizer(UrlSanitizer urlSanitizer) {
      this.urlSanitizer = urlSanitizer;
      return this;
    }

    /**
     * Whether URLs of link or images should be percent-encoded, defaults to {@code false}.
     *
     * <p>If enabled, the following is done:
     *
     * <ul>
     *   <li>Existing percent-encoded parts are preserved (e.g. "%20" is kept as "%20")
     *   <li>Reserved characters such as "/" are preserved, except for "[" and "]" (see encodeURI in
     *       JS)
     *   <li>Unreserved characters such as "a" are preserved
     *   <li>Other characters such umlauts are percent-encoded
     * </ul>
     *
     * @param percentEncodeUrls true to percent-encode, false for leaving as-is
     * @return {@code this}
     */
    public Builder percentEncodeUrls(boolean percentEncodeUrls) {
      this.percentEncodeUrls = percentEncodeUrls;
      return this;
    }

    /**
     * Add a factory for an attribute provider for adding/changing HTML attributes to the rendered tags.
     *
     * @param attributeProviderFactory the attribute provider factory to add
     * @return {@code this}
     */
    public Builder attributeProviderFactory(AttributeProviderFactory attributeProviderFactory) {
      if (attributeProviderFactory == null) {
        throw new NullPointerException("attributeProviderFactory must not be null");
      }
      this.attributeProviderFactories.add(attributeProviderFactory);
      return this;
    }

    /**
     * Add a factory for instantiating a node renderer (done when rendering). This allows to override the rendering of
     * node types or define rendering for custom node types.
     *
     * <p>If multiple node renderers for the same node type are created, the one from the factory
     * that was added first "wins". (This is how the rendering for core node types can be overridden; the default
     * rendering comes last.)
     *
     * @param nodeRendererFactory the factory for creating a node renderer
     * @return {@code this}
     */
    public Builder nodeRendererFactory(JavaDocNodeRendererFactory nodeRendererFactory) {
      if (nodeRendererFactory == null) {
        throw new NullPointerException("nodeRendererFactory must not be null");
      }
      this.nodeRendererFactories.add(nodeRendererFactory);
      return this;
    }

    /**
     * @param extensions extensions to use on this HTML renderer
     * @return {@code this}
     */
    public Builder extensions(Iterable<? extends Extension> extensions) {
      if (extensions == null) {
        throw new NullPointerException("extensions must not be null");
      }
      for (Extension extension : extensions) {
        if (extension instanceof JavaDocRendererExtension) {
          JavaDocRendererExtension htmlRendererExtension = (JavaDocRendererExtension) extension;
          htmlRendererExtension.extend(this);
        }
      }
      return this;
    }
  }

  private class RendererContext implements JavaDocNodeRendererContext, AttributeProviderContext {

    private final JavaDocWriter htmlWriter;
    private final List<AttributeProvider> attributeProviders;
    private final NodeRendererMap nodeRendererMap = new NodeRendererMap();

    private RendererContext(JavaDocWriter htmlWriter) {
      this.htmlWriter = htmlWriter;

      attributeProviders = new ArrayList<>(attributeProviderFactories.size());
      for (AttributeProviderFactory attributeProviderFactory : attributeProviderFactories) {
        attributeProviders.add(attributeProviderFactory.create(this));
      }

      // The first node renderer for a node type "wins".
      for (int i = nodeRendererFactories.size() - 1; i >= 0; i--) {
        JavaDocNodeRendererFactory nodeRendererFactory = nodeRendererFactories.get(i);
        NodeRenderer nodeRenderer = nodeRendererFactory.create(this);
        nodeRendererMap.add(nodeRenderer);
      }
    }

    @Override
    public boolean shouldEscapeHtml() {
      return escapeJavaDoc;
    }

    @Override
    public boolean shouldSanitizeUrls() {
      return sanitizeUrls;
    }

    @Override
    public UrlSanitizer urlSanitizer() {
      return urlSanitizer;
    }

    @Override
    public String encodeUrl(String url) {
      if (percentEncodeUrls) {
        return Escaping.percentEncodeUrl(url);
      } else {
        return url;
      }
    }

    @Override
    public Map<String, String> extendAttributes(
        Node node, String tagName, Map<String, String> attributes) {
      Map<String, String> attrs = new LinkedHashMap<>(attributes);
      setCustomAttributes(node, tagName, attrs);
      return attrs;
    }

    @Override
    public JavaDocWriter getWriter() {
      return htmlWriter;
    }

    @Override
    public String getSoftbreak() {
      return softbreak;
    }

    @Override
    public void render(Node node) {
      nodeRendererMap.render(node);
    }

    private void setCustomAttributes(Node node, String tagName, Map<String, String> attrs) {
      for (AttributeProvider attributeProvider : attributeProviders) {
        attributeProvider.setAttributes(node, tagName, attrs);
      }
    }
  }
}
