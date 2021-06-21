/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.processor.operator;

import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.lang.model.element.Modifier;
import org.tensorflow.Names;

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
public final class OperatorProcessor extends BaseOperatorProcessor<TypeSpec> {

  @Override
  protected void write(TypeSpec spec) {
    try {
      JavaFile.builder("org.tensorflow.op", spec)
          .addFileComment(LICENSE)
          .addFileComment("\nThis class has been generated, DO NOT EDIT!\n")
          .skipJavaLangImports(true)
          .build()
          .writeTo(filer);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  @Override
  protected TypeSpec buildGroupClass(OpsSpec spec) {
    // System.out.println("Generating " + spec.className + " class");

    MethodSpec.Builder ctorBuilder =
        MethodSpec.constructorBuilder()
            .addParameter(Names.Ops, "ops")
            .addStatement("this.scope = ops.scope()")
            .addStatement("this.ops = ops");

    TypeSpec.Builder builder =
        TypeSpec.classBuilder(spec.className)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc(
                "An API for building {@code $L} operations as {@link $T Op}s\n\n"
                    + "@see {@link $T}\n",
                spec.groupName,
                Names.Op,
                Names.Ops)
            .addMethods(spec.javaMethods());

    MethodSpec.Builder opsBuilder =
        MethodSpec.methodBuilder("ops")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .returns(Names.Ops)
            .addJavadoc("Get the parent {@link " + Names.Ops.simpleName() + "} object.")
            .addStatement("return ops");

    builder.addMethod(opsBuilder.build());

    addGroupFields(builder, ctorBuilder, spec.subGroups, false);

    builder.addMethod(ctorBuilder.build());

    builder.addField(
        FieldSpec.builder(Names.Scope, "scope")
            .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
            .build());

    builder.addField(
        FieldSpec.builder(Names.Ops, "ops").addModifiers(Modifier.PRIVATE, Modifier.FINAL).build());

    return builder.build();
  }

  @Override
  protected TypeSpec buildTopClass(OpsSpec spec) {
    // System.out.println("Generating " + spec.className + " class");

    MethodSpec.Builder ctorBuilder =
        MethodSpec.constructorBuilder()
            .addParameter(Names.Scope, "scope")
            .addModifiers(Modifier.PRIVATE)
            .addStatement("this.scope = scope", Names.Scope);

    TypeSpec.Builder opsBuilder =
        TypeSpec.classBuilder("Ops")
            .addSuperinterface(Names.WithOps)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addJavadoc(
                "An API for building operations as {@link $T Op}s\n<p>\n"
                    + "Any operation wrapper found in the classpath properly annotated as an"
                    + "{@link $T @Operator} is exposed\n"
                    + "by this API or one of its subgroup.\n<p>Example usage:\n<pre>{@code\n"
                    + "try (Graph g = new Graph()) {\n"
                    + "  Ops tf = Ops.create(g);\n"
                    + "  // Operations are typed classes with convenience\n"
                    + "  // builders in Ops.\n"
                    + "  Constant<TInt32> three = tf.constant(3);\n"
                    + "  // Single-result operations implement the Operand\n"
                    + "  // interface, so this works too.\n"
                    + "  Operand<TInt32> four = tf.constant(4);\n"
                    + "  // Most builders are found within a group, and accept\n"
                    + "  // Operand types as operands\n"
                    + "  Operand<TInt32> nine = tf.math.add(four, tf.constant(5));\n"
                    + "  // Multi-result operations however offer methods to\n"
                    + "  // select a particular result for use.\n"
                    + "  Operand<TInt32> result = \n"
                    + "      tf.math.add(tf.unique(s, a).y(), b);\n"
                    + "  // Optional attributes\n"
                    + "  tf.linalg.matMul(a, b, MatMul.transposeA(true));\n"
                    + "  // Naming operators\n"
                    + "  tf.withName(\"foo\").constant(5); // name \"foo\"\n"
                    + "  // Names can exist in a hierarchy\n"
                    + "  Ops sub = tf.withSubScope(\"sub\");\n"
                    + "  sub.withName(\"bar\").constant(4); // \"sub/bar\"\n"
                    + "}\n"
                    + "}</pre>\n",
                Names.Op,
                Names.Operator)
            .addMethods(spec.javaMethods());

    addGroupFields(opsBuilder, ctorBuilder, spec.subGroups, true);

    opsBuilder.addMethod(ctorBuilder.build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("tf")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .returns(Names.Ops)
            .addStatement("return this")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("withSubScope")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .addParameter(Names.String, "childScopeName")
            .returns(Names.Ops)
            .addStatement("return new $T(scope.withSubScope(childScopeName))", Names.Ops)
            .addJavadoc("{@inheritDoc}")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("withName")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .addParameter(Names.String, "opName")
            .returns(Names.Ops)
            .addStatement("return new Ops(scope.withName(opName))")
            .addJavadoc("{@inheritDoc}")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("withDevice")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .addParameter(Names.DeviceSpec, "deviceSpec")
            .returns(Names.Ops)
            .addStatement("return new Ops(scope.withDevice(deviceSpec))")
            .addJavadoc("{@inheritDoc}")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("withControlDependencies")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .addParameter(Names.IterableOp, "controls")
            .returns(Names.Ops)
            .addStatement("return new Ops(scope.withControlDependencies(controls))")
            .addJavadoc("{@inheritDoc}")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("withControlDependencies")
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Override.class)
            .addParameter(ArrayTypeName.of(Names.Op), "controls")
            .varargs()
            .returns(Names.Ops)
            .addStatement(
                "return withControlDependencies($T.asList(controls))", ClassName.get(Arrays.class))
            .addJavadoc("{@inheritDoc}")
            .build());

    opsBuilder.addField(
        FieldSpec.builder(Names.Scope, "scope")
            .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("scope")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .returns(Names.Scope)
            .addStatement("return scope")
            .addJavadoc("Returns the current {@link $T scope} of this API\n", Names.Scope)
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("create")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(Names.ExecutionEnvironment, "env")
            .returns(Names.Ops)
            .addStatement("return new Ops(env.baseScope())", Names.Scope)
            .addJavadoc(
                "Creates an API for building operations in the provided execution environment\n")
            .build());

    opsBuilder.addMethod(
        MethodSpec.methodBuilder("create")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(Names.Ops)
            .addStatement("return create($T.getDefault())", Names.EagerSession)
            .addJavadoc(
                "Creates an API for building operations in the default eager execution environment\n\n"
                    + "<p>Invoking this method is equivalent to {@code Ops.create(EagerSession.getDefault())}.\n")
            .build());

    return opsBuilder.build();
  }

  private static void addGroupFields(
      TypeSpec.Builder classBuilder,
      MethodSpec.Builder ctorBuilder,
      List<OpsSpec> groups,
      boolean isTopClass) {
    groups.forEach(
        group -> {
          System.out.println(
              "Adding field in " + classBuilder.build().name + ": " + group.fieldName);
          classBuilder.addField(
              FieldSpec.builder(group.className, group.fieldName)
                  .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                  .build());
          ctorBuilder
              .addStatement(
                  "$L = new $T(" + (isTopClass ? "this" : "ops") + ")",
                  group.fieldName,
                  group.className)
              .build();
        });
  }
}
