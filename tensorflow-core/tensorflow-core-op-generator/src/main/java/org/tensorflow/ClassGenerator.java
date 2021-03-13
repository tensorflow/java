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
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Modifier;
import org.tensorflow.TypeResolver.ResolvedType;
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
    return apiDef.getVisibility() != Visibility.SKIP || op.getAttrList().stream().anyMatch(x -> x.getType().contains("func"));
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

  private List<AttrDef> attributes = new ArrayList<>();
  private List<AttrDef> optionalAttributes = new ArrayList<>();

  private Map<ArgDef, ResolvedType> argTypes = new HashMap<>();

  public static String javaizeName(String name) {
    return name;
  }

  public static String parseDocumentation(String docs) {
    return docs; //TODO implement
  }

  private String fullClassName() {
    return fullPackage + "." + className;
  }

  ClassGenerator(Builder builder, OpDef op, ApiDef apiDef, TypeResolver resolver,
      String basePackage, String fullPackage, String group, String className, Endpoint endpoint) {

    this.builder = builder;
    this.op = op;
    this.apiDef = apiDef;
    this.resolver = resolver;
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

    calculateTypes();
  }

  public void calculateTypes() {
    Map<String, Integer> genericCounts = new HashMap<>();

    ArrayList<ArgDef> args = new ArrayList<>(op.getInputArgCount() + op.getOutputArgCount());
    Set<ArgDef> outputs = new HashSet<>(op.getOutputArgList());
    args.addAll(op.getInputArgList());
    args.addAll(op.getOutputArgList());

    for (ArgDef arg : args) {
      ResolvedType type = resolver.typeOf(arg);
      argTypes.put(arg, type);
      if (type.javaType instanceof TypeVariableName) {
        String name = ((TypeVariableName) type.javaType).name;

        int incr = outputs.contains(arg) ? 2 : 1;

        genericCounts.put(name, genericCounts.getOrDefault(name, 0) + incr);
      }
    }

    for (Map.Entry<ArgDef, ResolvedType> entry : argTypes.entrySet()) {
      if (entry.getValue().javaType instanceof TypeVariableName) {
        TypeVariableName type = (TypeVariableName) entry.getValue().javaType;
        if (genericCounts.get(type.name) <= 1 && type.bounds.size() == 1) {
          entry.setValue(new ResolvedType(WildcardTypeName.subtypeOf(type.bounds.get(0)), entry.getValue().iterable));
        }
      }
      ClassName baseClass;
      if (outputs.contains(entry.getKey())) {
        baseClass = ClassName.get(Output.class);
      } else {
        baseClass = ClassName.get(Operand.class);
      }

      entry.setValue(new ResolvedType(ParameterizedTypeName.get(baseClass, entry.getValue().javaType), entry.getValue().iterable));
    }

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
      if (!summary.isEmpty()) {
        builder.addJavadoc("<p>\n");
      }
      builder.addJavadoc("$L", desc + "\n");
    }

    if (op.getOutputArgCount() == 1) {
      ArgDef output = op.getOutputArg(0);
      ResolvedType rType = argTypes.get(output);
      TypeName type = ((ParameterizedTypeName) rType.javaType).typeArguments.get(0);
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
      TypeName type = argTypes.get(output).javaType;
      TypeVariableName typeVar = null;

      if (type instanceof TypeVariableName) {
        typeVar = (TypeVariableName) type;
      } else if (type instanceof ParameterizedTypeName) {
        ParameterizedTypeName paramType = (ParameterizedTypeName) type;
        if (paramType.rawType.equals(ClassName.get(Operand.class)) && paramType.typeArguments
            .get(0) instanceof TypeVariableName) {
          typeVar = (TypeVariableName) paramType.typeArguments.get(0);
        }
      }
      if (typeVar != null) {
        if (seenGenerics.add(typeVar.name)) {
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
        builder.addField(argTypes.get(output).listIfIterable().javaType, javaizeName(output.getName()), Modifier.PRIVATE);
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

      String name = javaizeName(attr.getName());

      ApiDef.Attr apiAttr = apiDef.getAttrList().stream().filter(x -> x.getName().equals(attr.getName())).findFirst()
          .get();

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
                .addCode("this.$L = $T.asList($L);\nreturn this;\n", name, ClassName.get(Arrays.class), name)
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
    String varName = optional ? "opts." + javaizeName(attr.getName()) : javaizeName(attr.getName());
    if (type == null) {
      type = resolver.typesOf(attr);
    }

    if (type.jniType.equals(ClassName.get(DataType.class))) {
      body.addStatement("opBuilder.setAttr($S, Operands.$L($L))", attr.getName(),
          type.iterable ? "toDataTypes" : "toDataType", varName);
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
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(ClassName.get(fullPackage, className));
    factoryBuilder.addAnnotation(
        AnnotationSpec.builder(org.tensorflow.op.annotation.Endpoint.class).addMember("describeByClass", "true")
            .build());

    factoryBuilder.addJavadoc("Factory method to create a class wrapping a new $L operation.", op.getName());

    CodeBlock.Builder body = CodeBlock.builder();

    factoryBuilder
        .addParameter(ParameterSpec.builder(ClassName.get(Scope.class), "scope").addJavadoc("current scope\n").build());

    body.addStatement("$T opBuilder = scope.env().opBuilder($S, scope.makeOpName($S))",
        ClassName.get(OperationBuilder.class), op.getName(),
        className);

    for (ArgDef input : op.getInputArgList()) {
      ApiDef.Arg argDef = apiDef.getInArgList().stream().filter(x -> x.getName().equals(input.getName())).findFirst()
          .get();
      ResolvedType rType = argTypes.get(input);
      TypeName type = rType.javaType;
      String name = javaizeName(input.getName());

      ParameterSpec.Builder param = ParameterSpec.builder(type, name)
          .addJavadoc("$L\n", argDef.getDescription());
      factoryBuilder.addParameter(param.build());

      if (rType.iterable) {
        body.addStatement("opBuilder.addInputList(Operands.asOutputs($L))", name);
      } else {
        body.addStatement("opBuilder.addInput($L.asOutput())", name);
      }

    }

    body.addStatement("opBuilder = scope.apply(opBuilder)");

    Map<String, TypeName> defaultTypes = new HashMap<>();
    for (AttrDef attr : attributes) {
      if (resolver.visited(attr.getName())) {
        continue;
      }

      ResolvedType type = resolver.typesOf(attr);
      ApiDef.Attr apiAttr = apiDef.getAttrList().stream().filter(x -> x.getName().equals(attr.getName())).findFirst()
          .get();

      ParameterSpec.Builder builder = ParameterSpec
          .builder(type.classIfGeneric().listIfIterable().javaType, javaizeName(attr.getName()))
          .addJavadoc("$L\n", apiAttr.getDescription());

      factoryBuilder.addParameter(builder.build());

      if (attr.hasDefaultValue() && type.javaType instanceof TypeVariableName) {
        TypeName defaultType = TypeResolver.forDataType(attr.getDefaultValue().getType());
        if (defaultType != ClassName.get(TType.class)) {
          defaultTypes.put(((TypeVariableName) type.javaType).name, defaultType);
        }
      }

      writeSetAttr(body, attr, type, false);

    }

    if (optionsClass != null) {
      factoryBuilder.addParameter(
          ParameterSpec.builder(ArrayTypeName.of(ClassName.get(fullPackage, className, "Options")), "options")
              .addJavadoc("$L\n", "carries optional attribute values").build());
      factoryBuilder.varargs();

      body.beginControlFlow("if (options != null)");

      body.beginControlFlow("for (Options opts : options)");
      for (AttrDef attr : optionalAttributes) {
        String name = javaizeName(attr.getName());
        body.beginControlFlow("if (opts.$L != null)", name);

        writeSetAttr(body, attr, null, true);

        body.endControlFlow();
      }
      body.endControlFlow();

      body.endControlFlow();

    }

    body.addStatement("return new $L(opBuilder.build())", className);

    factoryBuilder.addCode(body.build());
    factoryBuilder.addJavadoc("@return a new instance of $L\n", className);

    builder.addMethod(factoryBuilder.build());

    if (!defaultTypes.isEmpty()) {
      buildSecondaryFactory(defaultTypes);
    }
  }

  public void buildSecondaryFactory(Map<String, TypeName> defaultTypes) {

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
      String name = javaizeName(output.getName());
      ApiDef.Arg argDef = apiDef.getOutArgList().stream().filter(x -> x.getName().equals(output.getName())).findFirst()
          .get();
      builder.addMethod(MethodSpec.methodBuilder(name)
          .addModifiers(Modifier.PUBLIC)
          .returns(argTypes.get(output).listIfIterable().javaType)
          .addJavadoc("$L", argDef.getDescription())
          .addCode("return $L;", name)
          .build());
    }

  }

  public void buildInterfaceImpl() {
    ArgDef output = op.getOutputArg(0);
    TypeName type = ((ParameterizedTypeName) argTypes.get(output).javaType).typeArguments.get(0);

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
        asOutput.addCode("return ($T) $L;", outputType, javaizeName(output.getName()));
      } else {
        asOutput.addCode("return $L;", javaizeName(output.getName()));
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

      iterator.addCode("return ($T) $L.iterator();",ClassName.get(Iterator.class), javaizeName(output.getName()));

      builder.addMethod(iterator.build());
    }
  }

  public void buildConstructor() {
    MethodSpec.Builder ctor = MethodSpec.constructorBuilder();

    ctor.addParameter(ClassName.get(Operation.class), "operation");

    for (ArgDef output : op.getOutputArgList()) {
      ResolvedType type = argTypes.get(output);
      if (type.iterable || type.javaType instanceof WildcardTypeName) {
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
        ResolvedType type = argTypes.get(output);
        boolean iterable = type.iterable;
        if (iterable) {
          String lengthVar = javaizeName(output.getName()) + "Length";

          body.addStatement("int $L = operation.outputListLength($S)", lengthVar, output.getName());

          if (type.javaType instanceof WildcardTypeName) {
            body.addStatement("$L = $T.asList(($T) operation.outputList(outputIdx, $L))",
                javaizeName(output.getName()),
                ClassName.get(Arrays.class),
                ArrayTypeName.of(type.javaType),
                lengthVar);
          } else {
            body.addStatement("$L = $T.asList(operation.outputList(outputIdx, $L))",
                javaizeName(output.getName()),
                ClassName.get(Arrays.class),
                lengthVar);
          }
          body.addStatement("outputIdx += $L", lengthVar);

        } else {
          body.addStatement("$L = operation.output(outputIdx++)", javaizeName(output.getName()));
        }
      }
    }

    ctor.addCode(body.build());
    builder.addMethod(ctor.build());
  }

}
