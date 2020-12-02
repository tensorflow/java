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
    private val PACKAGE = "org.tensorflow.op.kotlin"
    private val T_OPERAND = ClassName("org.tensorflow", "Operand")

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
                    .indent("\t")
                    .addComment(LICENSE)
                    .addComment("\nThis class has been generated, DO NOT EDIT!\n")
                    .addType(spec)
                    .build()
                    .writeTo(this)
            }
                .replace("import java.(lang|util).[\\w.*]+\r?\n".toRegex(), "")
                .replace("java.lang.", "")
                .replace("java.util.List", "List")

            val packageFile = File(sourceDir, PACKAGE.replace(".", "/"))
            packageFile.mkdirs()

            File(packageFile, spec.name!! + ".kt").writeText(text)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    private val OpsSpec.parents: List<OpsSpec> get() = this.parent?.let { listOf(it) + it.parents }.orEmpty()

    fun adjustSingleType(type: TypeName, isVararg: Boolean): TypeName {
        if (type == T_OPERAND)
            return T_OPERAND.parameterizedBy(STAR)

        if (type is ParameterizedTypeName && !isVararg) {
            if (type.rawType == ARRAY) {
                val elementType = type.typeArguments.single()
                when (elementType) {
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

    fun adjustType(type: TypeName, isVararg: Boolean = false): TypeName {
        val adjusted = adjustSingleType(type, isVararg)
        if (adjusted is ParameterizedTypeName) {
            val newArgs = adjusted.typeArguments.map { adjustType(it) }
            return adjusted.rawType.parameterizedBy(newArgs)
        }
        return adjusted
    }

    private fun OpMethod.toKotlin(): FunSpec {
        val builder = FunSpec.builder(name)
            .returns(adjustType(endpointMethod.returnType.asTypeName()))

        if (deprecated)
            builder.addAnnotation(AnnotationSpec.builder(Deprecated::class).addMember("message = Op is Deprecated").build())

        builder.addTypeVariables(endpointMethod.typeParameters.map { it.asTypeVariableName() })

        val typeParamNames = builder.typeVariables.map { it.name }.toSet()

        val parameters = endpointMethod.parameters.filter {
            com.squareup.javapoet.TypeName.get(it.asType()) != T_SCOPE
        }.map { ParameterSpec.get(it) }

        val optionsParameter = parameters.singleOrNull {
            if (endpointMethod.isVarArgs && "Array<" in it.type.toString())
                ((it.type as? ParameterizedTypeName)?.typeArguments?.singleOrNull() as? ClassName)?.simpleName == "Options"
            else
                false
        }

        builder.addParameters(
            parameters.filter { it != optionsParameter }.map {
                it
                    .run {
                        if (name in typeParamNames)
                            this.toBuilder(name + "_").build()
                        else
                            this
                    }.run {
                        if (endpointMethod.isVarArgs && "Array<" in type.toString())
                            toBuilder(type = (type as ParameterizedTypeName).typeArguments.single()).addModifiers(KModifier.VARARG).build()
                        else
                            this
                    }.run {
                        toBuilder(type = adjustType(type, KModifier.VARARG in modifiers)).build()
                    }
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

        //TODO Javadocs/KDocs

        return builder.build()
    }

    override fun buildGroupClass(spec: OpsSpec): TypeSpec {

        val builder = TypeSpec.classBuilder(spec.className.kotlin)
            .addKdoc(
                """
        An API for building {@code %L} operations as {@link %T Op}s
        
        @see {@link %T}
        
        """.trimIndent(),
                spec.groupName,
                T_OP.kotlin,
                T_OPS.kotlin
            )

        builder.primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter("ops", T_KOTLIN_OPS)
//            .addStatement("this.ops = ops")
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
                .addKdoc("Get the parent {@link " + T_KOTLIN_OPS.simpleName + "} object.")
//                .setter(FunSpec.setterBuilder().addModifiers(KModifier.PRIVATE).build())
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("scope", T_SCOPE.kotlin)
                .initializer("ops.scope")
                .addKdoc("Returns the current {@link %T scope} of this API\n", T_SCOPE.kotlin)
                .build()
        )

        addGroupFields(builder, spec.subGroups, false)

        builder.addFunctions(spec.methods.map { it.toKotlin() })

        return builder.build()
    }

    override fun buildTopClass(spec: OpsSpec): TypeSpec {
        val builder = TypeSpec.classBuilder(T_KOTLIN_OPS)
            .addKdoc(
                """
        An API for building operations as {@link %T Op}s
        
        @see {@link %T}
        
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
                .addKdoc("Returns the java counterpart of this API\n", T_SCOPE.kotlin)
                .build()
        )
        builder.addProperty(
            PropertySpec.builder("scope", T_SCOPE.kotlin)
                .initializer("java.scope()")
                .addKdoc("Returns the current {@link %T scope} of this API\n", T_SCOPE.kotlin)
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("ops", T_KOTLIN_OPS)
                .initializer("this")
                .addKdoc("Get the {@link " + T_OPS.simpleName() + "} object.")
                .build()
        )

        builder.addProperty(
            PropertySpec.builder("tf", T_KOTLIN_OPS)
                .initializer("this")
                .addKdoc("Get the {@link " + T_OPS.simpleName() + "} object.")
                .build()
        )

        addGroupFields(builder, spec.subGroups, true)

        builder.addFunctions(spec.methods.map { it.toKotlin() })


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