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
package org.tensorflow.processor.operator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File
import java.io.IOException
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.type.ArrayType
import javax.lang.model.util.ElementFilter
import com.squareup.javapoet.ClassName as JavaClassName

val JavaClassName.kotlin get() = ClassName(this.packageName(), this.simpleNames())

class KotlinOpsProcessor : BaseOperatorProcessor<TypeSpec>() {
    private val T_KOTLIN_OPS = ClassName("org.tensorflow.op.kotlin", "KotlinOps")
    private val T_KOTLIN_OPS_BASE = ClassName("org.tensorflow.op.kotlin", "OpsBase")
    private val PACKAGE = "org.tensorflow.op.kotlin"
    private val T_OPERAND = ClassName("org.tensorflow", "Operand")
    private val T_CLASS = ClassName("java.lang", "Class")

    private lateinit var sourceDir: File

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        val kotlinDir = File(processingEnv.options["kapt.kotlin.generated"] ?: error("Kotlin source dir not specified"))
        val projectDir = kotlinDir.parentFile.parentFile.parentFile.parentFile
        require(projectDir.name == "tensorflow-core-kotlin-api") { "Could not find project directory.  Found $projectDir" }
        sourceDir = File(projectDir, "src/gen/annotations")
        sourceDir.mkdirs()
    }

    override fun write(spec: TypeSpec) {
        try {
            val text = buildString {
                FileSpec.builder(PACKAGE, spec.name ?: error("Type spec has no name"))
                    .indent("    ")
                    .addComment(LICENSE)
                    .addComment("\nThis class has been generated, DO NOT EDIT!\n")
                    .addType(spec)
                    .build()
                    .writeTo(this)
            }
                .replace("import java.(lang|util).[\\w.*]+\r?\n".toRegex(), "")
                .replace("java.lang.", "")
                .replace("java.util.List", "List")
                .replace("\t", "    ")

            val packageFile = File(sourceDir, PACKAGE.replace(".", "/"))
            packageFile.mkdirs()

            File(packageFile, spec.name!! + ".kt").writeText(text)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    private val OpsSpec.parents: List<OpsSpec> get() = this.parent?.let { listOf(it) + it.parents }.orEmpty()

    /**
     * @see adjustType
     */
    private fun adjustSingleType(type: TypeName, isVararg: Boolean): TypeName {
        if (type == T_OPERAND)
            return T_OPERAND.parameterizedBy(STAR)

        if (type is ParameterizedTypeName && !isVararg) {
            if (type.rawType == ARRAY) {
                when (type.typeArguments.single()) {
                    BOOLEAN -> return BOOLEAN_ARRAY
                    BYTE -> return BYTE_ARRAY
                    SHORT -> return SHORT_ARRAY
                    INT -> return INT_ARRAY
                    LONG -> return LONG_ARRAY
                    CHAR -> return CHAR_ARRAY
                    FLOAT -> return FLOAT_ARRAY
                    DOUBLE -> return DOUBLE_ARRAY
                    else -> {
                    }
                }
            }
        }

        return type
    }

    /**
     * Adjust types to their Kotlin counterparts.
     * Currently only changes Operand to Operand<*> and primitive arrays to their Kotlin counterparts.
     * Changes should be made to [adjustSingleType], this is a helper for parameterized types.
     */
    private fun adjustType(type: TypeName, isVararg: Boolean = false): TypeName {
        val adjusted = adjustSingleType(type, isVararg)
        if (adjusted is ParameterizedTypeName) {
            val newArgs = adjusted.typeArguments.map { adjustType(it) }
            return adjusted.rawType.parameterizedBy(newArgs)
        }
        return adjusted
    }

    private fun adjustJavadocLine(line: String): String {
        var line = line
        if (line.startsWith("@param")) {
            line = line.replace("```", "`") // https://youtrack.jetbrains.com/issue/KT-43787

            val parts = line.split(" ").toMutableList()
            if (parts[1].startsWith("<") && parts[1].endsWith(">")) {
                parts[1] = parts[1].substring(1, parts[1].length - 1)
            }
            line = parts.joinToString(" ")
        }
        return line
    }

    private fun adjustJavadoc(text: String): String {
        return text
            .replace("[", "&#91;")
            .replace("<p>", "")
            .replace("\\{@link([^@]+)\\}".toRegex()) {
                "[${it.groupValues[1]}]"
            }
            .replace("\\{@code([^@]+)\\}".toRegex()) {
                val code = it.groupValues[1].replace("&#91;", "[")
                if ("\n" in code)
                    "```$code```\n"
                else
                    "```$code```"
            }
            .replace("<pre>", "")
            .replace("</pre>", "")
            .split("\n")
            .joinToString("\n") { adjustJavadocLine(it) }
    }

    private fun List<OpMethod>.toKotlin(javaOpsClass: ClassName): List<FunSpec> {
        val methods = map { it.toKotlin(javaOpsClass) }.toMutableList()
        methods += methods.mapNotNull { makeCopyWithReified(it) }

        val duplicates = methods.filter { it.annotations.any { it.typeName == JvmName::class.asTypeName() } }.mapNotNull { orig ->
            val others = methods.minus(orig).filter {
                it.name == orig.name &&
                        it.parameters.map { it.name to it.type } == orig.parameters.map { it.name to it.type }
            }
            if (others.isEmpty()) {
                null
            } else {
                setOf(orig) + others
            }
        }.toSet()

        duplicates.forEach {
            val original = it.single { it.annotations.none { it.typeName == JvmName::class.asTypeName() } }
            var i = 0
            it.minus(original).forEach {
                val idx = methods.indexOf(it)
                methods[idx] = it.toBuilder(it.name + "Typed" + if (i == 0) "" else "$i").build()
                i++
            }
        }
        return methods
    }

    private fun OpMethod.toKotlin(javaOpsClass: ClassName): FunSpec {
        val builder = FunSpec.builder(name)
            .returns(adjustType(endpointMethod.returnType.asTypeName()))

        if (deprecated)
            builder.addAnnotation(AnnotationSpec.builder(Deprecated::class).addMember("message = Op is Deprecated").build())

        val typeParameters = endpointMethod.typeParameters.map { it.asTypeVariableName() }.toMutableList()

        val parameters = endpointMethod.parameters.filter {
            com.squareup.javapoet.TypeName.get(it.asType()) != T_SCOPE
        }.map { ParameterSpec.get(it) }

        val optionsParameter = parameters.singleOrNull {
            if (endpointMethod.isVarArgs && "Array<" in it.type.toString())
                ((it.type as? ParameterizedTypeName)?.typeArguments?.singleOrNull() as? ClassName)?.simpleName == "Options"
            else
                false
        }

        builder.addTypeVariables(typeParameters)

        val typeParamNames = typeParameters.map { it.name }.toSet()

        builder.addParameters(
            parameters.filter { it != optionsParameter }.map {
                var param = it
                if (param.name in typeParamNames)
                    param = param.toBuilder(param.name + "_").build()

                if (endpointMethod.isVarArgs && "Array<" in param.type.toString())
                    param =
                        param.toBuilder(type = (param.type as ParameterizedTypeName).typeArguments.single()).addModifiers(KModifier.VARARG).build()

                param.toBuilder(type = adjustType(param.type, KModifier.VARARG in param.modifiers)).build()
            })

        val optionsClass = if (optionsParameter != null) {
            val paramElement = endpointMethod.parameters.single { it.simpleName.contentEquals(optionsParameter.name) }
            val type = paramElement.asType()?.let {
                if (it is ArrayType)
                    it.componentType
                else
                    it
            }
            types.asElement(type) as TypeElement
        } else
            null

        val opClassSpec = (optionsClass?.enclosingElement as TypeElement?)?.asClassName()

        val optionParams = if (optionsClass != null)
            ElementFilter.methodsIn(optionsClass.enclosedElements).map {
                ParameterSpec.builder(it.simpleName.toString(), it.parameters.single().asType().asTypeName().copy(nullable = true))
                    .addKdoc("%L", adjustJavadoc(parseJavadoc(it).toText()).trim().removePrefix("@param ${it.simpleName} "))
                    .defaultValue("null").build()
            }.toSet()
        else
            emptySet()

        if (optionParams.isNotEmpty())
            builder.addParameters(optionParams)

        builder.addStatement(
            buildString {
                append("return java.$name")
                if (typeParamNames.isNotEmpty())
                    append("<${typeParamNames.joinToString(", ")}>")

                append("(")

                val paramStrings = builder.parameters.filter { it !in optionParams }.map {
                    val name = if (it.name == "var") "`var`" else it.name

                    if (KModifier.VARARG in it.modifiers)
                        "*${name}"
                    else
                        name
                }.plus(
                    if (optionParams.isNotEmpty())
                        listOf(
                            "*listOfNotNull(${
                                optionParams.joinToString(",\n", "\n", "\n") {
                                    "\t${it.name}?.let{ ${opClassSpec!!.canonicalName}.${it.name}(it) }"
                                }
                            }).toTypedArray()"
                        )
                    else
                        emptyList()
                )

                append(
                    paramStrings.joinToString(",\n", "\n", "\n").prependIndent("\t")
                )

                append(")")
            }
        )

        val javadoc = buildOpMethodJavadoc(opClass, endpointMethod, describeByClass)
        javadoc.addBlockTag("see", "${javaOpsClass.canonicalName}.$name")


        builder.addKdoc("%L", adjustJavadoc(javadoc.toText()))

        return builder.build()
    }

    private fun makeCopyWithReified(method: FunSpec): FunSpec? {

        val dataTypeParameters = method.parameters.mapNotNull { param ->
            param.type.let {
                if (it is ParameterizedTypeName && it.rawType == T_CLASS && it.typeArguments.singleOrNull() in method.typeVariables)
                    param to it.typeArguments.single() as TypeVariableName
                else
                    null
            }
        }.toMap()
        val builder = method.toBuilder()

        if (dataTypeParameters.isEmpty())
            return null

        dataTypeParameters.values.forEach {
            val i = builder.typeVariables.indexOf(it)
            builder.typeVariables[i] = builder.typeVariables[i].copy(reified = true)
        }
        if (dataTypeParameters.isNotEmpty()) {
            builder.addModifiers(KModifier.INLINE)
            builder.addAnnotation(AnnotationSpec.builder(JvmName::class).addMember("\"${method.name}Reified\"").build())
        }

        val paramString = builder.parameters.joinToString {
            if (it in dataTypeParameters)
                dataTypeParameters[it]!!.name + "::class.java"
            else {
                val name = if (it.name == "var") "`var`" else it.name

                if (KModifier.VARARG in it.modifiers)
                    "*${name}"
                else
                    name
            }
        }

        builder.parameters.removeAll(dataTypeParameters.keys)

        builder.clearBody()

        builder.addStatement(
            "return ${method.name}<${builder.typeVariables.joinToString(", ") { it.name }}>($paramString)"
        )
        return builder.build()
    }

    override fun buildGroupClass(spec: OpsSpec): TypeSpec {

        val builder = TypeSpec.classBuilder(spec.className.kotlin)
            .addKdoc(
                """
        An API for building `%L` operations as [Op][%T]s
        
        @see %T
        
        """.trimIndent(),
                spec.groupName,
                T_OP.kotlin,
                T_OPS.kotlin
            )

        builder.primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("ops", T_KOTLIN_OPS)
                .build()
        )

        val accessorName = (listOf(spec.fieldName) + spec.parents.mapNotNull { it.fieldName }).reversed().joinToString(".")

        builder.addProperty(
            PropertySpec.builder("java", spec.className.kotlin)
                .initializer("ops.java.$accessorName")
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("ops", T_KOTLIN_OPS)
                .initializer("ops")
                .addKdoc("Get the parent [" + T_KOTLIN_OPS.simpleName + "] object.")
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("scope", T_SCOPE.kotlin)
                .initializer("ops.scope")
                .addKdoc("Returns the current [scope][%T] of this API\n", T_SCOPE.kotlin)
                .build()
        )

        addGroupFields(builder, spec.subGroups, false)

        builder.addFunctions(spec.methods.toKotlin(spec.className.kotlin))

        return builder.build()
    }

    override fun buildTopClass(spec: OpsSpec): TypeSpec {
        val builder = TypeSpec.classBuilder(T_KOTLIN_OPS)
            .addKdoc(
                """
        An API for building operations as [Op][%T]s
        
        @see %T
        
        """.trimIndent(),
                T_OP.kotlin,
                T_OPS.kotlin
            )

        builder.primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("java", T_OPS.kotlin)
                .build()
        )
        builder.addProperty(
            PropertySpec.builder("java", T_OPS.kotlin)
                .initializer("java")
                .addKdoc("Returns the java counterpart of this API\n")
                .build()
        )
        builder.addProperty(
            PropertySpec.builder("scope", T_SCOPE.kotlin)
                .initializer("java.scope()")
                .addKdoc("Returns the current [scope][%T] of this API\n", T_SCOPE.kotlin)
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("ops", T_KOTLIN_OPS)
                .initializer("this")
                .addKdoc("Get the [" + T_KOTLIN_OPS.simpleName + "] object.")
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("tf", T_KOTLIN_OPS)
                .initializer("this")
                .addModifiers(KModifier.OVERRIDE)
                .addKdoc("Get the [ " + T_KOTLIN_OPS.simpleName + "] object.")
                .build()
        )

        builder.superclass(T_KOTLIN_OPS_BASE)

        addGroupFields(builder, spec.subGroups, true)

        builder.addFunctions(spec.methods.toKotlin(T_OPS.kotlin))


        return builder.build()
    }

    private fun addGroupFields(
        classBuilder: TypeSpec.Builder,
        groups: List<OpsSpec>,
        isTopClass: Boolean
    ) = groups.forEach {
        val kotlinGroup = ClassName(it.className.packageName() + ".kotlin", it.className.simpleNames())
        classBuilder.addProperty(
            PropertySpec.builder(it.fieldName, kotlinGroup)
                .initializer("%T(${if (isTopClass) "this" else "ops"})", kotlinGroup)
                .build()
        )
    }
}