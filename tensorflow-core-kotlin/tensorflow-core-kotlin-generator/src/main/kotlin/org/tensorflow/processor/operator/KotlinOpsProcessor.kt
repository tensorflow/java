package org.tensorflow.processor.operator

import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import com.squareup.kotlinpoet.TypeSpec
import org.tensorflow.processor.operator.BaseOperatorProcessor

class KotlinOpsProcessor: BaseOperatorProcessor<TypeSpec>() {
    override fun write(spec: TypeSpec?) {
        TODO("Not yet implemented")
    }

    override fun buildGroupClass(spec: OpsSpec?): TypeSpec {
        TODO("Not yet implemented")
    }

    override fun buildTopClass(spec: OpsSpec?): TypeSpec {
        TODO("Not yet implemented")
    }
}