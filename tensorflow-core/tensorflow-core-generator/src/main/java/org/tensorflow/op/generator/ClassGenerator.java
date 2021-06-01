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
package org.tensorflow.op.generator;

import static org.tensorflow.op.generator.GeneratorUtils.javaizeMemberName;
import static org.tensorflow.op.generator.GeneratorUtils.parseDocumentation;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;
import org.tensorflow.Names;
import org.tensorflow.proto.framework.ApiDef;
import org.tensorflow.proto.framework.ApiDef.Endpoint;
import org.tensorflow.proto.framework.ApiDef.Visibility;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpDef.ArgDef;
import org.tensorflow.proto.framework.OpDef.AttrDef;

/**
 * A generator to generate a op class
 */
final class ClassGenerator {

  /**
   * Return true if we can generate the operation class for {@code op}.
   */
  static boolean canGenerateOp(OpDef op, ApiDef apiDef) {
    return apiDef.getVisibility() != Visibility.SKIP
        && !op.getAttrList().stream().anyMatch(x -> x.getType().contains("func"))
        && !op.getName().startsWith("_"); //TODO do I want this?  Some interesting ops like _XlaCompile
  }

  private static final String OP_NAME_FIELD_NAME = "OP_NAME";

  enum RenderMode {
    DEFAULT, LIST_OPERAND, OPERAND;
  }

  /**
   * The in-progress class builder for the top level op class.
   */
  private final TypeSpec.Builder builder;

  /**
   * The op to build.
   */
  private final OpDef op;

  /**
   * The api definition for the current op.
   */
  private final ApiDef apiDef;

  /**
   * A type resolver for the current op.
   */
  private final TypeResolver resolver;

  /**
   * The full package of the class.
   */
  private final String fullPackage;

  /**
   * The base package for this op generation run.
   */
  private final String basePackage;

  /**
   * The group of this op.
   */
  private final String group;

  /**
   * The class name for this op.
   */
  private final String className;

  /**
   * The endpoint being generated in this class.
   */
  private final Endpoint endpoint;

  /**
   * The generated options class, or null if it doesn't have one or {@link #buildOptionsClass()} has not been ran.
   */
  private TypeSpec optionsClass = null;

  /**
   * What type of op this is.
   */
  private RenderMode mode = RenderMode.DEFAULT;

  /**
   * The required attributes of this op.
   */
  private final List<AttrDef> requiredAttributes = new ArrayList<>();

  /**
   * The optional attributes of this op.
   */
  private final List<AttrDef> optionalAttributes = new ArrayList<>();

  /**
   * The class's type parameters, initialized in {@link #buildClass()}.
   */
  private final Set<TypeVariableName> typeParams = new LinkedHashSet<>();

  /**
   * The api defs for the arguments.
   */
  private final Map<ArgDef, ApiDef.Arg> argApis = new HashMap<>();

  /**
   * The api defs for the attributes.
   */
  private final Map<AttrDef, ApiDef.Attr> attrApis = new HashMap<>();

  ClassGenerator(Builder builder, OpDef op, ApiDef apiDef,
      String basePackage, String fullPackage, String group, String className, Endpoint endpoint) {

    this.builder = builder;
    this.op = op;
    this.apiDef = apiDef;
    this.resolver = new TypeResolver(op);
    this.basePackage = basePackage;
    this.fullPackage = fullPackage;
    this.group = group;
    this.className = className;
    this.endpoint = endpoint;

    op.getAttrList().forEach(attr -> {
      if (attr.hasDefaultValue() && !attr.getType().contains("type")) {
        optionalAttributes.add(attr);
      } else {
        requiredAttributes.add(attr);
      }
    });

    for (AttrDef attr : op.getAttrList()) {
      ApiDef.Attr api = apiDef.getAttrList().stream().filter(x -> x.getName().equals(attr.getName())).findFirst().get();
      attrApis.put(attr, api);
    }

    for (ArgDef arg : op.getInputArgList()) {
      ApiDef.Arg api = apiDef.getInArgList().stream().filter(x -> x.getName().equals(arg.getName())).findFirst().get();
      argApis.put(arg, api);
    }
    for (ArgDef arg : op.getOutputArgList()) {
      ApiDef.Arg api = apiDef.getOutArgList().stream().filter(x -> x.getName().equals(arg.getName())).findFirst().get();
      argApis.put(arg, api);
    }
  }

  /**
   * Get the Java variable name for an argument.
   */
  private String getJavaName(ArgDef arg) {
    String name = arg.getName();
    String rename = argApis.get(arg).getRenameTo();
    if (!rename.isEmpty()) {
      return javaizeMemberName(rename);
    } else {
      return javaizeMemberName(name);
    }
  }

  /**
   * Get the Java variable name for an attribute.
   */
  private String getJavaName(AttrDef arg) {
    String name = arg.getName();
    String rename = attrApis.get(arg).getRenameTo();
    if (!rename.isEmpty()) {
      return javaizeMemberName(rename);
    } else {
      return javaizeMemberName(name);
    }
  }

  /**
   * Get the fully qualified name of the class being generated.
   */
  private String fullClassName() {
    return fullPackage + "." + className;
  }

  /**
   * Build the class.
   */
  void buildClass() {
    builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    builder.superclass(Names.RawOp);

    // add class javadocs
    String summary = parseDocumentation(apiDef.getSummary());
    if (!summary.isEmpty()) {
      builder.addJavadoc("$L", summary + "\n");
    } else {
      builder.addJavadoc("The $L operation\n", apiDef.getGraphOpName());
    }
    String desc = parseDocumentation(apiDef.getDescription());
    if (!desc.isEmpty()) {
      builder.addJavadoc("$L", desc + "\n");
    }

    // add superinterface and set mode
    if (op.getOutputArgCount() == 1) {
      ArgDef output = op.getOutputArg(0);
      ResolvedType rType = resolver.typeOf(output);
      TypeName type = rType.unwrapArg();
      boolean iterable = rType.iterable;
      TypeName operandTypeParam =
          type instanceof WildcardTypeName ? Names.TType : type;
      TypeName operandType = ParameterizedTypeName.get(Names.Operand, operandTypeParam);

      if (iterable) {
        mode = RenderMode.LIST_OPERAND;
        builder.addSuperinterface(ParameterizedTypeName.get(ClassName.get(Iterable.class), operandType));
      } else {
        mode = RenderMode.OPERAND;
        builder.addSuperinterface(operandType);
      }
    }

    // add and store type variables
    Set<String> seenGenerics = new HashSet<>();
    for (ArgDef output : op.getOutputArgList()) {
      ResolvedType type = resolver.typeOf(output);
      for (TypeVariableName typeVar : type.findGenerics()) {
        if (seenGenerics.add(typeVar.name)) {
          typeParams.add(typeVar);
          builder.addTypeVariable(typeVar);
          builder.addJavadoc(
              "\n@param <$L> data type for {@code $L} output\n", typeVar.name, output.getName());
        }
      }
    }

    // add deprecated if necessary
    if (endpoint.getDeprecated()) {
      builder.addAnnotation(Deprecated.class);
      Endpoint first = apiDef.getEndpoint(0);
      String explanation;
      if (!first.getDeprecated()) {
        explanation = "use {@link " + basePackage + "." + first.getName() + "} instead";
      } else {
        explanation = op.getDeprecation().getExplanation();
      }
      builder.addJavadoc("\n@deprecated $L", explanation);
    }

    // add the Operator annotation
    if (apiDef.getVisibility() != Visibility.HIDDEN) {
      AnnotationSpec.Builder annotation = AnnotationSpec.builder(Names.Operator);
      if (!group.equals("core")) {
        annotation.addMember("group", "$S", group);
      }
      builder.addAnnotation(annotation.build());
    }

    if (!optionalAttributes.isEmpty()) {
      buildOptionsClass();
    }

    buildFactoryMethods();
    buildGettersAndSetters();
    if (mode != RenderMode.DEFAULT) {
      buildInterfaceImpl();
    }

    // add op name field
    builder
        .addField(FieldSpec.builder(TypeResolver.STRING, OP_NAME_FIELD_NAME, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
            .addJavadoc("$L", "The name of this op, as known by TensorFlow core engine")
            .initializer("$S", op.getName())
            .build());

    // add output fields
    if (op.getOutputArgCount() > 0) {
      for (ArgDef output : op.getOutputArgList()) {
        builder
            .addField(resolver.typeOf(output).listIfIterable().javaType, getJavaName(output), Modifier.PRIVATE);
      }
    }

    buildConstructor();
  }

  /**
   * Add a nested class for Options
   */
  private void buildOptionsClass() {

    if (optionalAttributes.isEmpty()) {
      return;
    }

    TypeSpec.Builder optionsBuilder = TypeSpec.classBuilder("Options").addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    optionsBuilder.addJavadoc("$L", "Optional attributes for {@link " + fullClassName() + "}");

    ClassName optionsClassName = ClassName.get(fullPackage, className, "Options");

    for (AttrDef attr : optionalAttributes) {
      ResolvedType type = resolver.typeOf(attr);

      String name = getJavaName(attr);

      ApiDef.Attr apiAttr = attrApis.get(attr);

      String description =
          apiAttr.getDescription().isEmpty()
              ? String.format("the %s option", name)
              : apiAttr.getDescription();

      // add the setter method, adding one with varargs and one with List if the attribute is
      // iterable
      if (type.iterable) {
        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().listIfIterable().javaType, name)
                .addJavadoc("Sets the $L option.\n", name)
                .addJavadoc("\n@param $L $L", name, parseDocumentation(description))
                .addJavadoc("\n@return this Options instance.")
                .addCode("this.$L = $L;\nreturn this;\n", name, name)
                .build());

        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().arrayIfIterable().javaType, name)
                .addJavadoc("Sets the $L option.\n", name)
                .addJavadoc("\n@param $L $L", name, parseDocumentation(description))
                .addJavadoc("\n@return this Options instance.")
                .addCode("this.$L = $T.asList($L);\nreturn this;\n", name, Arrays.class, name)
                .varargs()
                .build());

      } else {
        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().javaType, name)
                .addJavadoc("Sets the $L option.\n", name)
                .addJavadoc("\n@param $L $L", name, parseDocumentation(description))
                .addJavadoc("\n@return this Options instance.")
                .addCode("this.$L = $L;\nreturn this;\n", name, name)
                .build());
      }

      // add the field
      optionsBuilder.addField(type.classIfGeneric().listIfIterable().javaType, name, Modifier.PRIVATE);
    }

    // add a private constructor
    optionsBuilder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build());

    optionsClass = optionsBuilder.build();
    builder.addType(optionsClass);
  }

  /**
   * Write statements to set an attribute in an OperationBuilder.  Meant to be used in {@link #buildFactoryMethods()}
   *
   * @param body the body to write to
   * @param attr the attribute to set
   * @param type the type of the attribute, or null to get it ourselves
   * @param optional whether the attribute is optional
   */
  private void writeSetAttr(CodeBlock.Builder body, AttrDef attr, ResolvedType type, boolean optional) {
    String varName = optional ? "opts." + getJavaName(attr) : getJavaName(attr);
    if (type == null) {
      type = resolver.typeOf(attr);
    }

    if (type.jniType.equals(ClassName.get(DataType.class))) {
      body.addStatement("opBuilder.setAttr($S, $T.$L($L))", attr.getName(),
          Names.Operands, type.iterable ? "toDataTypes" : "toDataType", varName);
    } else {
      if (type.iterable) {
        String arrayName = javaizeMemberName(attr.getName()) + "Array";
        body.addStatement("$T[] $L = new $T[$L.size()]", type.jniType, arrayName, type.jniType, varName);

        body.beginControlFlow("for (int i = 0 ; i < $L.length ; i++)", arrayName);

        body.addStatement("$L[i] = $L.get(i)", arrayName, varName);

        body.endControlFlow();

        body.addStatement("opBuilder.setAttr($S, $L)", attr.getName(), arrayName);
      } else {
        body.addStatement("opBuilder.setAttr($S, $L)", attr.getName(), varName);
      }
    }
  }

  /**
   * Add the {@code create} factory methods.
   */
  private void buildFactoryMethods() {
    MethodSpec.Builder factoryBuilder = MethodSpec.methodBuilder("create")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    // the main creator will inherit any class type params
    TypeName returnType = ClassName.get(fullPackage, className);
    if (!typeParams.isEmpty()) {
      returnType = ParameterizedTypeName.get((ClassName) returnType, typeParams.toArray(new TypeName[0]));
    }
    factoryBuilder.returns(returnType);

    factoryBuilder.addAnnotation(
        AnnotationSpec.builder(Names.Endpoint).addMember("describeByClass", "true")
            .build());

    factoryBuilder.addJavadoc("Factory method to create a class wrapping a new $L operation.\n", op.getName());

    // we're going to build the body as add arguments
    CodeBlock.Builder body = CodeBlock.builder();

    Map<String, CodeBlock> paramTags = new LinkedHashMap<>();

    factoryBuilder
        .addParameter(ParameterSpec.builder(Names.Scope, "scope").build());
    paramTags.put("scope", CodeBlock.of("current scope"));

    Set<TypeVariableName> typeVars = new LinkedHashSet<>(typeParams);

    body.addStatement("$T opBuilder = scope.env().opBuilder($L, scope.makeOpName($S))",
        Names.OperationBuilder,
        OP_NAME_FIELD_NAME,
        className);

    // add the inputs as parameters, and add them to the op builder
    for (ArgDef input : op.getInputArgList()) {
      ApiDef.Arg argDef = argApis.get(input);
      ResolvedType type = resolver.typeOf(input);
      String name = getJavaName(input);

      ParameterSpec.Builder param = ParameterSpec.builder(type.iterableIfIterable().javaType, name);
      String description =
          argDef.getDescription().isEmpty()
              ? String.format("the %s value", name)
              : argDef.getDescription();
      paramTags.put(name, CodeBlock.of("$L", parseDocumentation(description)));
      factoryBuilder.addParameter(param.build());

      typeVars.addAll(type.findGenerics());

      if (type.iterable) {
        body.addStatement("opBuilder.addInputList($T.asOutputs($L))", Names.Operands, name);
      } else {
        body.addStatement("opBuilder.addInput($L.asOutput())", name);
      }

    }

    body.addStatement("opBuilder = scope.apply(opBuilder)");

    // add the required attribute params, and build the default type maps for use in the secondary factory
    Map<AttrDef, TypeName> defaultTypes = new HashMap<>();
    Map<String, TypeName> defaultTypeVars = new HashMap<>();
    for (AttrDef attr : requiredAttributes) {
      if (resolver.partOfInput(attr.getName())) {
        continue;
      }

      ResolvedType type = resolver.typeOf(attr);
      ApiDef.Attr apiAttr = attrApis.get(attr);

      ParameterSpec.Builder builder = ParameterSpec
          .builder(type.classIfGeneric().listIfIterable().javaType, getJavaName(attr));

      String javaName = getJavaName(attr);
      String description =
          apiAttr.getDescription().isEmpty()
              ? String.format("the value of the %s property", javaName)
              : apiAttr.getDescription();
      paramTags.put(javaName, CodeBlock.of("$L", parseDocumentation(description)));

      typeVars.addAll(type.findGenerics());

      factoryBuilder.addParameter(builder.build());

      // we only add defaults for type variable arguments
      if (attr.hasDefaultValue() && type.shouldWrapInClass()) {
        TypeName defaultType = TypeResolver.forDataType(attr.getDefaultValue().getType());
        if (!(defaultType instanceof WildcardTypeName) && defaultType != Names.TType) {
          defaultTypes.put(attr, defaultType);
          defaultTypeVars.put(((TypeVariableName) type.javaType).name, defaultType);
        }
      }

      writeSetAttr(body, attr, type, false);
    }

    // add optional attributes
    if (optionsClass != null) {
      factoryBuilder.addParameter(
          ParameterSpec.builder(ArrayTypeName.of(ClassName.get(fullPackage, className, "Options")), "options").build());
      paramTags.put("options", CodeBlock.of("$L", "carries optional attribute values"));
      factoryBuilder.varargs();

      body.beginControlFlow("if (options != null)");

      body.beginControlFlow("for (Options opts : options)");
      for (AttrDef attr : optionalAttributes) {
        String name = getJavaName(attr);
        body.beginControlFlow("if (opts.$L != null)", name);

        writeSetAttr(body, attr, null, true);

        body.endControlFlow();
      }
      body.endControlFlow();

      body.endControlFlow();

    }

    body.addStatement("return new $L(opBuilder.build())", typeParams.isEmpty() ? className : (className + "<>"));

    factoryBuilder.addCode(body.build());
    paramTags.forEach(
        (param, doc) -> {
          String description = doc.toString();
          if (description.isEmpty() || description.equals("\n")) {
            factoryBuilder.addJavadoc(
                "\n@param $L the $L property", param, param);
          } else {
            factoryBuilder.addJavadoc("\n@param $L $L", param, doc);
          }
        });
    for (TypeVariableName typeVar : typeVars) {
      factoryBuilder.addJavadoc(
          "\n@param <"
              + typeVar.name
              + "> data type for {@code "
              + op.getName()
              + "} output and operands");
    }

    factoryBuilder.addTypeVariables(typeVars);
    factoryBuilder.addJavadoc("\n@return a new instance of $L\n", className);
    MethodSpec method = factoryBuilder.build();
    builder.addMethod(method);

    if (!defaultTypes.isEmpty()) {
      buildSecondaryFactory(defaultTypes, defaultTypeVars, method, paramTags);
    }
  }

  /**
   * Add a secondary factory method with the provided default type maps
   */
  private void buildSecondaryFactory(Map<AttrDef, TypeName> defaultTypes, Map<String, TypeName> defaultTypeVars,
      MethodSpec mainFactory, Map<String, CodeBlock> paramTags) {
    MethodSpec.Builder factoryBuilder = MethodSpec.methodBuilder(mainFactory.name)
        .addModifiers(mainFactory.modifiers)
        .returns(ParameterizedTypeName.get(ClassName.get(fullPackage, className), typeParams.stream()
            .map(x -> defaultTypeVars.getOrDefault(x.name, x)).toArray(TypeName[]::new)));
    factoryBuilder.addAnnotations(mainFactory.annotations);

    factoryBuilder
        .addJavadoc("Factory method to create a class wrapping a new $L operation, with the default output types.\n",
            op.getName());

    CodeBlock.Builder body = CodeBlock.builder();
    body.add("return create(");

    Set<TypeVariableName> typeVars = new LinkedHashSet<>();

    // we want to add all of the main factory's parameters except for those with defaults
    // we just pass them through
    boolean first = true;
    for (ParameterSpec param : mainFactory.parameters) {
      if (!first) {
        body.add(", ");
      }

      AttrDef attr = op.getAttrList().stream().filter(x -> getJavaName(x).equals(param.name)).findFirst()
          .orElse(null);
      if (attr != null && resolver.typeOf(attr).shouldWrapInClass() && defaultTypes.containsKey(attr)) {
        body.add("$T.class", defaultTypes.get(attr));
      } else {
        factoryBuilder.addParameter(param);
        factoryBuilder.addJavadoc("\n@param $L $L", param.name, paramTags.get(param.name));
        typeVars.addAll(new ResolvedType(param.type).findGenerics());
        body.add("$L", param.name);
      }
      first = false;
    }

    body.add(");");

    for (TypeVariableName typeVar : typeVars) {
      factoryBuilder.addJavadoc(
          "\n@param <"
              + typeVar.name
              + "> data type for {@code "
              + op.getName()
              + "} output and operands");
    }

    factoryBuilder.addJavadoc("\n@return a new instance of $L, with default output types", className);
    factoryBuilder.addCode(body.build());
    factoryBuilder.addTypeVariables(typeVars);

    builder.addMethod(factoryBuilder.build());
  }

  /**
   * Add getters for the outputs and setters/Options creators for the optional attributes.
   *
   * <p>Needs to be called after {@link #buildOptionsClass()}
   */
  private void buildGettersAndSetters() {
    if (optionsClass != null) {
      optionsClass.methodSpecs.stream()
          .filter(x -> !x.isConstructor())
          .forEach(
              method -> {
                String argName = method.parameters.get(0).name;
                builder.addMethod(
                    MethodSpec.methodBuilder(method.name)
                        .addParameter(method.parameters.get(0))
                        .addJavadoc(method.javadoc)
                        .returns(ClassName.get(fullPackage, className, "Options"))
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addCode("return new Options().$L($L);", method.name, argName)
                        .build());
              });
    }

    for (ArgDef output : op.getOutputArgList()) {
      String name = getJavaName(output);
      ApiDef.Arg argDef = argApis.get(output);
      builder.addMethod(
          MethodSpec.methodBuilder(name)
              .addModifiers(Modifier.PUBLIC)
              .returns(resolver.typeOf(output).listIfIterable().javaType)
              .addJavadoc("Gets $L.\n", name)
              .addJavadoc("$L", parseDocumentation(argDef.getDescription()))
              .addJavadoc("\n@return $L.", name)
              .addCode("return $L;", name)
              .build());
    }

  }

  /**
   * Add interface implementation methods if this is a single output or list output op.
   */
  private void buildInterfaceImpl() {
    ArgDef output = op.getOutputArg(0);
    TypeName type = resolver.typeOf(output).unwrapArg();

    boolean uncheckedCast = type instanceof WildcardTypeName;
    TypeName outputTType = uncheckedCast ? Names.TType : type;

    if (mode == RenderMode.OPERAND) {
      TypeName outputType = ParameterizedTypeName.get(Names.Output, outputTType);
      MethodSpec.Builder asOutput = MethodSpec.methodBuilder("asOutput")
          .addModifiers(Modifier.PUBLIC)
          .returns(outputType)
          .addAnnotation(Override.class);

      if (uncheckedCast) {
        asOutput.addAnnotation(
            AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unchecked").build());
        asOutput.addCode("return ($T) $L;", outputType, getJavaName(output));
      } else {
        asOutput.addCode("return $L;", getJavaName(output));
      }

      builder.addMethod(asOutput
          .build());
    } else {
      TypeName operandType = ParameterizedTypeName.get(Names.Operand, outputTType);
      TypeName returnType = ParameterizedTypeName.get(ClassName.get(Iterator.class), operandType);

      MethodSpec.Builder iterator = MethodSpec.methodBuilder("iterator")
          .addModifiers(Modifier.PUBLIC)
          .returns(returnType)
          .addAnnotation(Override.class)
          .addAnnotation(
              AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "{$S, $S}", "rawtypes", "unchecked")
                  .build());

      iterator.addCode("return ($T) $L.iterator();", Iterator.class, getJavaName(output));

      builder.addMethod(iterator.build());
    }
  }

  /**
   * Add a constructor to get the outputs from an operation
   */
  private void buildConstructor() {
    MethodSpec.Builder ctor = MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE);

    ctor.addParameter(Names.Operation, "operation");

    for (ArgDef output : op.getOutputArgList()) {
      ResolvedType type = resolver.typeOf(output);
      if (type.iterable || type.unwrapArg() instanceof WildcardTypeName) {
        ctor.addAnnotation(
            AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unchecked").build());
        break;
      }
    }
    CodeBlock.Builder body = CodeBlock.builder();
    body.addStatement("super(operation)");

    if (op.getOutputArgCount() > 0) {
      body.addStatement("int outputIdx = 0");
      for (ArgDef output : op.getOutputArgList()) {
        ResolvedType type = resolver.typeOf(output);
        boolean iterable = type.iterable;
        if (iterable) {
          String lengthVar = getJavaName(output) + "Length";

          body.addStatement("int $L = operation.outputListLength($S)", lengthVar, output.getName());

          if (type.unwrapArg() instanceof WildcardTypeName) {
            body.addStatement("$L = $T.asList(operation.outputList(outputIdx, $L))",
                getJavaName(output),
                Arrays.class,
                lengthVar);
          } else {
            body.addStatement("$L = $T.asList(($T) operation.outputList(outputIdx, $L))",
                getJavaName(output),
                Arrays.class,
                ArrayTypeName.of(type.javaType),
                lengthVar);
          }
          body.addStatement("outputIdx += $L", lengthVar);

        } else {
          body.addStatement("$L = operation.output(outputIdx++)", getJavaName(output));
        }
      }
    }

    ctor.addCode(body.build());
    builder.addMethod(ctor.build());
  }

}
