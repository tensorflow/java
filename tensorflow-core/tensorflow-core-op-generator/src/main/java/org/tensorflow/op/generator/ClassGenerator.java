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

import static org.tensorflow.op.generator.GeneratorUtils.javaizeName;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.ApiDef;
import org.tensorflow.proto.framework.ApiDef.Endpoint;
import org.tensorflow.proto.framework.ApiDef.Visibility;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.proto.framework.OpDef;
import org.tensorflow.proto.framework.OpDef.ArgDef;
import org.tensorflow.proto.framework.OpDef.AttrDef;
import org.tensorflow.types.family.TType;

class ClassGenerator {

  public static boolean canGenerateOp(OpDef op, ApiDef apiDef) {
    return apiDef.getVisibility() != Visibility.SKIP
        && !op.getAttrList().stream().anyMatch(x -> x.getType().contains("func"))
        && !op.getName().startsWith("_"); //TODO do I want this?  Some interesting ops like _XlaCompile
  }

  enum RenderMode {
    Default, ListOperand, Operand;
  }

  private final TypeSpec.Builder builder;
  private final OpDef op;
  private final ApiDef apiDef;
  private final TypeResolver resolver;
  private final String fullPackage;
  private final String basePackage;
  private final String group;
  private final String className;
  private final Endpoint endpoint;

  private TypeSpec optionsClass = null;

  private RenderMode mode = RenderMode.Default;

  private final List<AttrDef> attributes = new ArrayList<>();
  private final List<AttrDef> optionalAttributes = new ArrayList<>();
  private final Set<TypeVariableName> typeParams = new LinkedHashSet<>();

  private final Map<ArgDef, ApiDef.Arg> argApis = new HashMap<>();
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
        attributes.add(attr);
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

  public String getJavaName(ArgDef arg) {
    String name = arg.getName();
    String rename = argApis.get(arg).getRenameTo();
    if (!rename.isEmpty()) {
      return javaizeName(rename);
    } else {
      return javaizeName(name);
    }
  }

  public String getJavaName(AttrDef arg) {
    String name = arg.getName();
    String rename = attrApis.get(arg).getRenameTo();
    if (!rename.isEmpty()) {
      return javaizeName(rename);
    } else {
      return javaizeName(name);
    }
  }

  private String fullClassName() {
    return fullPackage + "." + className;
  }

  public void buildClass() {
    builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    builder.superclass(ClassName.get(RawOp.class));

    String summary = parseDocumentation(apiDef.getSummary());
    if (!summary.isEmpty()) {
      builder.addJavadoc("$L", summary + "\n");
    }
    String desc = parseDocumentation(apiDef.getDescription());
    if (!desc.isEmpty()) {
      builder.addJavadoc("$L", desc + "\n");
    }

    if (op.getOutputArgCount() == 1) {
      ArgDef output = op.getOutputArg(0);
      ResolvedType rType = resolver.typeOf(output);
      TypeName type = rType.unwrapArg();
      boolean iterable = rType.iterable;
      TypeName operandTypeParam =
          type instanceof WildcardTypeName ? TypeName.get(TType.class) : type;
      TypeName operandType = ParameterizedTypeName.get(ClassName.get(Operand.class), operandTypeParam);

      if (iterable) {
        mode = RenderMode.ListOperand;
        builder.addSuperinterface(ParameterizedTypeName.get(ClassName.get(Iterable.class), operandType));
      } else {
        mode = RenderMode.Operand;
        builder.addSuperinterface(operandType);
      }
    }

    Set<String> seenGenerics = new HashSet<>();
    for (ArgDef output : op.getOutputArgList()) {
      ResolvedType type = resolver.typeOf(output);
      for (TypeVariableName typeVar : type.findGenerics()) {
        if (seenGenerics.add(typeVar.name)) {
          typeParams.add(typeVar);
          builder.addTypeVariable(typeVar);
          builder.addJavadoc(
              "\n@param <" + typeVar.name + "> data type for {@code " + output
                  .getName() + "} output\n");
        }
      }
    }

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

    if (apiDef.getVisibility() != Visibility.HIDDEN) {
      AnnotationSpec.Builder annotation = AnnotationSpec.builder(Operator.class);
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
    if (mode != RenderMode.Default) {
      buildInterfaceImpl();
    }

    builder
        .addField(FieldSpec.builder(TypeResolver.STRING, "OP_NAME", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
            .initializer("$S", op.getName())
            .build());

    if (op.getOutputArgCount() > 0) {
      for (ArgDef output : op.getOutputArgList()) {
        builder
            .addField(resolver.typeOf(output).listIfIterable().javaType, getJavaName(output), Modifier.PRIVATE);
      }
    }

    buildConstructor();
  }

  public void buildOptionsClass() {
    TypeSpec.Builder optionsBuilder = TypeSpec.classBuilder("Options").addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    optionsBuilder.addJavadoc("$L", "Optional attributes for {@link " + fullClassName() + "}");

    ClassName optionsClassName = ClassName.get(fullPackage, className, "Options");

    for (AttrDef attr : optionalAttributes) {
      ResolvedType type = resolver.typesOf(attr);

      String name = getJavaName(attr);

      ApiDef.Attr apiAttr = attrApis.get(attr);

      if (type.iterable) {
        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().listIfIterable().javaType, name)
                .addJavadoc("@param $L $L\n", name, parseDocumentation(apiAttr.getDescription()))
                .addCode("this.$L = $L;\nreturn this;\n", name, name)
                .build());

        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().arrayIfIterable().javaType, name)
                .addJavadoc("@param $L $L\n", name, parseDocumentation(apiAttr.getDescription()))
                .addCode("this.$L = $T.asList($L);\nreturn this;\n", name, Arrays.class, name)
                .varargs()
                .build());

      } else {
        optionsBuilder.addMethod(
            MethodSpec.methodBuilder(name)
                .returns(optionsClassName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type.classIfGeneric().javaType, name)
                .addJavadoc("@param $L $L\n", name, parseDocumentation(apiAttr.getDescription()))
                .addCode("this.$L = $L;\nreturn this;\n", name, name)
                .build());
      }

      optionsBuilder.addField(type.classIfGeneric().listIfIterable().javaType, name, Modifier.PRIVATE);
    }

    optionsBuilder.addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build());

    optionsClass = optionsBuilder.build();
    builder.addType(optionsClass);
  }

  private void writeSetAttr(CodeBlock.Builder body, AttrDef attr, ResolvedType type, boolean optional) {
    String varName = optional ? "opts." + getJavaName(attr) : getJavaName(attr);
    if (type == null) {
      type = resolver.typesOf(attr);
    }

    if (type.jniType.equals(ClassName.get(DataType.class))) {
      body.addStatement("opBuilder.setAttr($S, $T.$L($L))", attr.getName(),
          Operands.class, type.iterable ? "toDataTypes" : "toDataType", varName);
    } else {
      if (type.iterable) {
        String arrayName = javaizeName(attr.getName()) + "Array";
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

  public void buildFactoryMethods() {
    MethodSpec.Builder factoryBuilder = MethodSpec.methodBuilder("create")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    TypeName returnType = ClassName.get(fullPackage, className);
    if (!typeParams.isEmpty()) {
      returnType = ParameterizedTypeName.get((ClassName) returnType, typeParams.toArray(new TypeName[0]));
    }
    factoryBuilder.returns(returnType);

    factoryBuilder.addAnnotation(
        AnnotationSpec.builder(org.tensorflow.op.annotation.Endpoint.class).addMember("describeByClass", "true")
            .build());

    factoryBuilder.addJavadoc("Factory method to create a class wrapping a new $L operation.\n", op.getName());

    CodeBlock.Builder body = CodeBlock.builder();

    factoryBuilder
        .addParameter(ParameterSpec.builder(ClassName.get(Scope.class), "scope").addJavadoc("current scope\n").build());

    Set<TypeVariableName> typeVars = new LinkedHashSet<>(typeParams);

    body.addStatement("$T opBuilder = scope.env().opBuilder($S, scope.makeOpName($S))",
        OperationBuilder.class, op.getName(),
        className);

    for (ArgDef input : op.getInputArgList()) {
      ApiDef.Arg argDef = argApis.get(input);
      ResolvedType type = resolver.typeOf(input);
      String name = getJavaName(input);

      ParameterSpec.Builder param = ParameterSpec.builder(type.iterableIfIterable().javaType, name)
          .addJavadoc("$L\n", argDef.getDescription());
      factoryBuilder.addParameter(param.build());

      typeVars.addAll(type.findGenerics());

      if (type.iterable) {
        body.addStatement("opBuilder.addInputList($T.asOutputs($L))", Operands.class, name);
      } else {
        body.addStatement("opBuilder.addInput($L.asOutput())", name);
      }

    }

    body.addStatement("opBuilder = scope.apply(opBuilder)");

    Map<AttrDef, TypeName> defaultTypes = new HashMap<>();
    Map<String, TypeName> defaultTypeVars = new HashMap<>();
    for (AttrDef attr : attributes) {
      if (resolver.partOfInput(attr.getName())) {
        continue;
      }

      ResolvedType type = resolver.typesOf(attr);
      ApiDef.Attr apiAttr = attrApis.get(attr);

      ParameterSpec.Builder builder = ParameterSpec
          .builder(type.classIfGeneric().listIfIterable().javaType, getJavaName(attr))
          .addJavadoc("$L\n", apiAttr.getDescription());

      typeVars.addAll(type.findGenerics());

      factoryBuilder.addParameter(builder.build());

      if (attr.hasDefaultValue() && type.shouldWrapInClass()) {
        TypeName defaultType = TypeResolver.forDataType(attr.getDefaultValue().getType());
        if (!(defaultType instanceof WildcardTypeName) && defaultType != ClassName.get(TType.class)) {
          defaultTypes.put(attr, defaultType);
          defaultTypeVars.put(((TypeVariableName) type.javaType).name, defaultType);
        }
      }

      writeSetAttr(body, attr, type, false);

    }

    if (optionsClass != null) {
      factoryBuilder.addParameter(
          ParameterSpec.builder(ArrayTypeName.of(ClassName.get(fullPackage, className, "Options")), "options")
              .addJavadoc("$L\n", "carries optional attribute values\n").build());
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
    factoryBuilder.addJavadoc("\n@return a new instance of $L", className);
    factoryBuilder.addTypeVariables(typeVars);

    MethodSpec method = factoryBuilder.build();
    builder.addMethod(method);

    if (!defaultTypes.isEmpty()) {
      buildSecondaryFactory(defaultTypes, defaultTypeVars, method);
    }
  }

  public void buildSecondaryFactory(Map<AttrDef, TypeName> defaultTypes, Map<String, TypeName> defaultTypeVars,
      MethodSpec main) {
    MethodSpec.Builder factoryBuilder = MethodSpec.methodBuilder(main.name)
        .addModifiers(main.modifiers)
        .returns(ParameterizedTypeName.get(ClassName.get(fullPackage, className), typeParams.stream()
            .map(x -> defaultTypeVars.getOrDefault(x.name, x)).toArray(TypeName[]::new)));
    factoryBuilder.addAnnotations(main.annotations);

    factoryBuilder
        .addJavadoc("Factory method to create a class wrapping a new $L operation, with the default output types.\n",
            op.getName());

    CodeBlock.Builder body = CodeBlock.builder();
    body.add("return create(");

    Set<TypeVariableName> typeVars = new LinkedHashSet<>();

    boolean first = true;
    for (ParameterSpec param : main.parameters) {
      if (!first) {
        body.add(", ");
      }

      AttrDef attr = op.getAttrList().stream().filter(x -> getJavaName(x).equals(param.name)).findFirst()
          .orElse(null);
      if (attr != null && resolver.typesOf(attr).shouldWrapInClass() && defaultTypes.containsKey(attr)) {
        body.add("$T.class", defaultTypes.get(attr));
      } else {
        factoryBuilder.addParameter(param);
        typeVars.addAll(new ResolvedType(param.type).findGenerics());
        body.add("$L", param.name);
      }
      first = false;
    }

    body.add(");");

    factoryBuilder.addJavadoc("\n@return a new instance of $L, with default output types", className);
    factoryBuilder.addCode(body.build());
    factoryBuilder.addTypeVariables(typeVars);

    builder.addMethod(factoryBuilder.build());
  }

  public void buildGettersAndSetters() {
    if (optionsClass != null) {
      optionsClass.methodSpecs.stream().filter(x -> !x.isConstructor()).forEach(method -> {
        String argName = method.parameters.get(0).name;
        builder.addMethod(MethodSpec.methodBuilder(method.name)
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
      builder.addMethod(MethodSpec.methodBuilder(name)
          .addModifiers(Modifier.PUBLIC)
          .returns(resolver.typeOf(output).listIfIterable().javaType)
          .addJavadoc("$L", argDef.getDescription())
          .addCode("return $L;", name)
          .build());
    }

  }

  public void buildInterfaceImpl() {
    ArgDef output = op.getOutputArg(0);
    TypeName type = resolver.typeOf(output).unwrapArg();

    boolean uncheckedCast = type instanceof WildcardTypeName;
    TypeName outputTType = uncheckedCast ? TypeName.get(TType.class) : type;

    if (mode == RenderMode.Operand) {
      TypeName outputType = ParameterizedTypeName.get(ClassName.get(Output.class), outputTType);
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
      TypeName operandType = ParameterizedTypeName.get(ClassName.get(Operand.class), outputTType);
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

  public void buildConstructor() {
    MethodSpec.Builder ctor = MethodSpec.constructorBuilder();

    ctor.addParameter(ClassName.get(Operation.class), "operation");

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
