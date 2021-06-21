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
==============================================================================*/

package org.tensorflow.processor.operator;

import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.javadoc.Javadoc;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

/**
 * A compile-time Processor that aggregates classes annotated with {@code
 * org.tensorflow.op.annotation.Operator} and generates the {@code Ops} convenience API. Please
 * refer to the {@code Operator} annotation for details about the API generated for each annotated
 * class.
 *
 * <p>Note that this processor can only be invoked once, in a single compilation run that includes
 * all the {@code Operator} annotated source classes. The reason is that the {@code Ops} API is an
 * "aggregating" API, and annotation processing does not permit modifying an already generated
 * class.
 */
public abstract class BaseOperatorProcessor<T> extends AbstractProcessor {

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    messager = processingEnv.getMessager();
    filer = processingEnv.getFiler();
    elements = processingEnv.getElementUtils();
    types = processingEnv.getTypeUtils();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    // Nothing needs to be done at the end of all rounds.
    if (roundEnv.processingOver()) {
      return false;
    }

    // Nothing to look at in this round.
    if (annotations.size() == 0) {
      return false;
    }

    // We expect to be registered for exactly one annotation.
    if (annotations.size() != 1) {
      throw new IllegalStateException(
          "Unexpected - multiple annotations registered: " + annotations);
    }
    TypeElement annotation = annotations.iterator().next();
    Set<? extends Element> annotated = roundEnv.getElementsAnnotatedWith(annotation);

    // If there are no annotated elements, claim the annotation but do nothing.
    if (annotated.size() == 0) {
      return true;
    }

    // This processor has to aggregate all op classes in one round, as it generates a single Ops
    // API class which cannot be modified once generated. If we find an annotation after we've
    // generated our code, flag the location of each such class.
    if (hasRun) {
      for (Element e : annotated) {
        error(
            e,
            "The Operator processor has already processed @Operator annotated sources\n"
                + "and written out an Ops API. It cannot process additional @Operator sources.\n"
                + "One reason this can happen is if other annotation processors generate\n"
                + "new @Operator source files.");
      }
      return true;
    }

    // Collect all classes tagged with our annotation.
    Multimap<String, OpMethod> groupedMethods = HashMultimap.create();
    if (!collectOpsMethods(roundEnv, groupedMethods, annotation)) {
      return true;
    }

    // Nothing to do when there are no tagged classes.
    if (groupedMethods.isEmpty()) {
      return true;
    }

    // Validate operator classes and generate Op API.
    writeApi(groupedMethods);

    hasRun = true;
    return true;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton("org.tensorflow.op.annotation.Operator");
  }

  protected static class OpsSpec {
    protected static final Comparator<OpMethod> PARAMETER_SPEC_COMPARATOR =
        (o1, o2) -> {
          if (o1.javaMethod.parameters.size() > o2.javaMethod.parameters.size()) {
            return 1;
          }
          if (o1.javaMethod.parameters.size() < o2.javaMethod.parameters.size()) {
            return -1;
          }
          List<ParameterSpec> firstParams = o1.javaMethod.parameters;
          List<ParameterSpec> secondParams = o2.javaMethod.parameters;
          for (int i = 0; i < firstParams.size(); i++) {
            ParameterSpec first = firstParams.get(i);
            ParameterSpec second = secondParams.get(i);
            int compare = first.name.compareTo(second.name);
            if (compare != 0) {
              return compare;
            }
          }
          return 0;
        };
    protected static final Comparator<OpMethod> METHOD_SPEC_COMPARATOR =
        Comparator.comparing((OpMethod m) -> m.name).thenComparing(PARAMETER_SPEC_COMPARATOR);

    public final @Nullable OpsSpec parent;
    public final String groupName;
    public final String fieldName;
    public final ClassName className;
    public final List<OpMethod> methods;
    public final List<OpsSpec> subGroups = new ArrayList<>();

    OpsSpec(
        OpsSpec parent,
        String groupName,
        String fieldName,
        ClassName className,
        Collection<OpMethod> methods) {
      this.parent = parent;
      this.groupName = groupName;
      this.fieldName = fieldName;
      this.className = className;
      this.methods = new ArrayList<>(methods);
      this.methods.sort(METHOD_SPEC_COMPARATOR);
    }

    Iterable<MethodSpec> javaMethods() {
      return methods.stream().map(x -> x.javaMethod).collect(Collectors.toList());
    }
  }

  protected static final class OpMethod {
    final String name;
    final TypeElement opClass;
    final ExecutableElement endpointMethod;
    final boolean describeByClass;
    final boolean deprecated;
    final MethodSpec javaMethod;

    public OpMethod(
        String name,
        TypeElement opClass,
        ExecutableElement endpointMethod,
        boolean describeByClass,
        boolean deprecated,
        MethodSpec javaMethod) {
      this.name = name;
      this.opClass = opClass;
      this.endpointMethod = endpointMethod;
      this.describeByClass = describeByClass;
      this.deprecated = deprecated;
      this.javaMethod = javaMethod;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof OpMethod)) {
        return false;
      }

      OpMethod opMethod = (OpMethod) o;

      return javaMethod.equals(opMethod.javaMethod);
    }

    @Override
    public int hashCode() {
      return javaMethod.hashCode();
    }
  }

  protected static final Pattern JAVADOC_TAG_PATTERN =
      Pattern.compile("@(?:param|return|throws|exception|see|deprecated)\\s+.*");
  protected static final ClassName T_OP = ClassName.get("org.tensorflow.op", "Op");
  protected static final ClassName T_OPS = ClassName.get("org.tensorflow.op", "Ops");
  protected static final TypeName T_ITERABLE_OP =
      ParameterizedTypeName.get(ClassName.get(Iterable.class), T_OP);
  protected static final ClassName T_OPERATOR =
      ClassName.get("org.tensorflow.op.annotation", "Operator");
  protected static final ClassName T_ENDPOINT =
      ClassName.get("org.tensorflow.op.annotation", "Endpoint");
  protected static final ClassName T_SCOPE = ClassName.get("org.tensorflow.op", "Scope");
  protected static final ClassName T_EXEC_ENV =
      ClassName.get("org.tensorflow", "ExecutionEnvironment");
  protected static final ClassName T_EAGER_SESSION =
      ClassName.get("org.tensorflow", "EagerSession");
  protected static final ClassName T_STRING = ClassName.get(String.class);

  protected static final String LICENSE =
      "Copyright 2020 The TensorFlow Authors. All Rights Reserved.\n"
          + "\n"
          + "Licensed under the Apache License, Version 2.0 (the \"License\");\n"
          + "you may not use this file except in compliance with the License.\n"
          + "You may obtain a copy of the License at\n"
          + "\n"
          + "    http://www.apache.org/licenses/LICENSE-2.0\n"
          + "\n"
          + "Unless required by applicable law or agreed to in writing, software\n"
          + "distributed under the License is distributed on an \"AS IS\" BASIS,\n"
          + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
          + "See the License for the specific language governing permissions and\n"
          + "limitations under the License.\n"
          + "==============================================================================\n";

  protected Filer filer;
  protected Messager messager;
  protected Elements elements;
  protected Types types;
  protected boolean hasRun = false;

  protected void error(Element e, String message, Object... args) {
    if (args != null && args.length > 0) {
      message = String.format(message, args);
    }
    messager.printMessage(Kind.ERROR, message, e);
  }

  protected abstract void write(T spec);

  protected void writeApi(Multimap<String, OpMethod> groupedMethods) {
    // Build tree of *Ops classes that needs to be generated by this processor. The 'Ops' class
    // resides at the root of the tree while other classes are nodes.
    OpsSpec ops = new OpsSpec(null, null, null, T_OPS, groupedMethods.removeAll(""));
    Collection<OpsSpec> groupOps = collectGroupOps(ops, groupedMethods);

    write(buildTopClass(ops));
    groupOps.forEach(g -> write(buildGroupClass(g)));
  }

  protected boolean collectOpsMethods(
      RoundEnvironment roundEnv,
      Multimap<String, OpMethod> groupedMethods,
      TypeElement annotation) {
    boolean result = true;
    for (Element e : roundEnv.getElementsAnnotatedWith(annotation)) {
      // @Operator can only apply to types, so e must be a TypeElement.
      if (!(e instanceof TypeElement)) {
        error(
            e,
            "@Operator can only be applied to classes, but this is a %s",
            e.getKind().toString());
        result = false;
        continue;
      }
      collectOpMethods(groupedMethods, (TypeElement) e, annotation);
    }
    return result;
  }

  protected void collectOpMethods(
      Multimap<String, OpMethod> groupedMethods, TypeElement opClass, TypeElement annotation) {
    boolean opClassDeprecated = opClass.getAnnotation(Deprecated.class) != null;
    AnnotationMirror operatorAnnot = getAnnotationMirror(opClass, annotation.getQualifiedName());
    if (operatorAnnot == null) {
      throw new IllegalArgumentException(
          "Annotation "
              + annotation.getSimpleName()
              + " not present on element "
              + opClass.getSimpleName());
    }
    String opGroup = getAnnotationElementValueAsString("group", operatorAnnot);
    String opName = getAnnotationElementValueAsString("name", operatorAnnot);
    if (Strings.isNullOrEmpty(opName)) {
      opName =
          CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, ClassName.get(opClass).simpleName());
    }
    // Build an endpoint for each method annotated with @Endpoint, which takes in parameter a scope
    // and, optionally, a list of arguments
    for (ExecutableElement opMethod : ElementFilter.methodsIn(opClass.getEnclosedElements())) {
      AnnotationMirror endpointAnnot =
          getAnnotationMirror(opMethod, elements.getName(T_ENDPOINT.toString()));
      if (endpointAnnot != null) {
        if (!opMethod.getModifiers().containsAll(Arrays.asList(Modifier.STATIC, Modifier.PUBLIC))) {
          throw new IllegalArgumentException(
              "Endpoint " + opMethod + " of class " + opClass + " must be static and public");
        }
        if (opMethod.getParameters().isEmpty()
            || !((TypeElement) types.asElement(opMethod.getParameters().get(0).asType()))
                .getQualifiedName()
                .equals(elements.getName(T_SCOPE.toString()))) {
          throw new IllegalArgumentException(
              "Endpoint "
                  + opMethod
                  + " of class "
                  + opClass
                  + " must take an instance of "
                  + T_SCOPE
                  + " as its first parameter");
        }
        String endpointGroup = getAnnotationElementValueAsString("group", endpointAnnot);
        if (endpointGroup.isEmpty()) {
          endpointGroup = opGroup;
        }
        String endpointName = getAnnotationElementValueAsString("name", endpointAnnot);
        if (endpointName.isEmpty()) {
          endpointName = opName;
        }
        boolean describeByClass =
            getAnnotationElementValueAsBoolean("describeByClass", endpointAnnot, false);
        boolean deprecated = opMethod.getAnnotation(Deprecated.class) != null || opClassDeprecated;
        OpMethod method =
            buildOpMethod(endpointName, opClass, opMethod, describeByClass, deprecated);
        groupedMethods.put(endpointGroup, method);
      }
    }
  }

  protected OpMethod buildOpMethod(
      String methodName,
      TypeElement opClass,
      ExecutableElement endpointMethod,
      boolean describeByClass,
      boolean deprecated) {
    MethodSpec.Builder builder =
        MethodSpec.methodBuilder(methodName)
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.get(endpointMethod.getReturnType()))
            .varargs(endpointMethod.isVarArgs())
            .addJavadoc(
                "$L", buildOpMethodJavadoc(opClass, endpointMethod, describeByClass).toText());

    if (deprecated) {
      builder.addAnnotation(Deprecated.class);
    }
    for (TypeParameterElement tp : endpointMethod.getTypeParameters()) {
      TypeVariableName tvn = TypeVariableName.get((TypeVariable) tp.asType());
      builder.addTypeVariable(tvn);
    }
    for (TypeMirror thrownType : endpointMethod.getThrownTypes()) {
      builder.addException(TypeName.get(thrownType));
    }
    StringBuilder call = new StringBuilder();
    if (!NoType.class.isAssignableFrom(endpointMethod.getReturnType().getClass())) {
      call.append("return ");
    }
    call.append("$T.").append(endpointMethod.getSimpleName()).append("(scope");
    boolean first = true;
    for (VariableElement param : endpointMethod.getParameters()) {
      ParameterSpec p = ParameterSpec.get(param);
      if (first) {
        first = false;
        continue;
      }
      call.append(", ");
      call.append(p.name);
      builder.addParameter(p);
    }
    call.append(")");
    builder.addStatement(call.toString(), ClassName.get(opClass));
    return new OpMethod(
        methodName, opClass, endpointMethod, describeByClass, deprecated, builder.build());
  }

  protected Javadoc buildOpMethodJavadoc(
      TypeElement opClass, ExecutableElement endpointMethod, boolean copyClassDescription) {
    Javadoc methodJavadoc = parseJavadoc(endpointMethod);

    Javadoc javadoc;

    if (!copyClassDescription) {
      javadoc = new Javadoc(methodJavadoc.getDescription());
    } else {
      javadoc = parseJavadoc(opClass);
    }

    // Copy all endpoint method tags to the description, except for the `scope` parameter which
    // will be inferred by the Ops class
    methodJavadoc
        .getBlockTags()
        .forEach(
            t -> {
              if (!(t.getTagName().equals("param")
                  && t.getName().map(s -> s.equals("scope")).orElse(false))) {
                javadoc.addBlockTag(t);
              }
            });

    return javadoc;
  }

  protected static Collection<OpsSpec> collectGroupOps(
      OpsSpec ops, Multimap<String, OpMethod> groupedMethods) {
    Map<String, OpsSpec> groups = new HashMap<>();

    // The `group` label added in the `@Operator` annotation has the same syntax as a package name,
    // which (in most
    // case) consists of a simple label but could also be a deeper tree, like `linalg.sparse`. In
    // this case,
    // the `LinalgSparseOps` group should be added as the `sparse` field of the `LinalgOps` group,
    // and the latter
    // should be added as the `linalg` field of the `Ops` root class.
    groupedMethods
        .keys()
        .forEach(
            group -> {
              OpsSpec parentClass = ops;
              int startPos = 0;
              do {
                int delimiterPos = group.indexOf('.', startPos);
                String groupName = delimiterPos < 0 ? group : group.substring(0, delimiterPos);
                OpsSpec groupOps = groups.get(groupName);

                // Create spec for this group if we have not encountered it yet in our iteration
                if (groupOps == null) {
                  String fieldName =
                      delimiterPos < 0
                          ? group.substring(startPos)
                          : group.substring(startPos, delimiterPos);
                  ClassName className =
                      ClassName.get(
                          "org.tensorflow.op",
                          CaseFormat.LOWER_UNDERSCORE.to(
                                  CaseFormat.UPPER_CAMEL, groupName.replace('.', '_'))
                              + "Ops");
                  groupOps =
                      new OpsSpec(
                          parentClass,
                          groupName,
                          fieldName,
                          className,
                          groupedMethods.get(groupName));
                  parentClass.subGroups.add(groupOps);
                  groups.put(groupName, groupOps);
                }
                parentClass = groupOps;
                startPos = delimiterPos + 1;
              } while (startPos > 0);
            });

    return groups.values();
  }

  protected abstract T buildGroupClass(OpsSpec spec);

  protected abstract T buildTopClass(OpsSpec spec);

  protected static AnnotationMirror getAnnotationMirror(Element element, Name annotationName) {
    for (AnnotationMirror am : element.getAnnotationMirrors()) {
      if (((TypeElement) am.getAnnotationType().asElement())
          .getQualifiedName()
          .equals(annotationName)) {
        return am;
      }
    }
    return null;
  }

  protected static AnnotationValue getAnnotationElementValue(
      String elementName, AnnotationMirror am) {
    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
        am.getElementValues().entrySet()) {
      if (entry.getKey().getSimpleName().contentEquals(elementName)) {
        return entry.getValue();
      }
    }
    return null;
  }

  protected static String getAnnotationElementValueAsString(
      String elementName, AnnotationMirror am) {
    AnnotationValue value = getAnnotationElementValue(elementName, am);
    return value != null ? value.getValue().toString() : "";
  }

  protected static boolean getAnnotationElementValueAsBoolean(
      String elementName, AnnotationMirror am, boolean defaultValue) {
    AnnotationValue value = getAnnotationElementValue(elementName, am);
    return value != null ? Boolean.parseBoolean(value.toString()) : defaultValue;
  }

  protected Javadoc parseJavadoc(Element element) {
    String docComment = elements.getDocComment(element);
    JavadocComment javadocComment;
    if (docComment != null) {
      javadocComment = new JavadocComment(docComment);
    } else {
      javadocComment = new JavadocComment();
    }
    return javadocComment.parse();
  }
}
