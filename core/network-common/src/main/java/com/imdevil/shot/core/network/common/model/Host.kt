package com.imdevil.shot.core.network.common.model

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Host(val host: String)
