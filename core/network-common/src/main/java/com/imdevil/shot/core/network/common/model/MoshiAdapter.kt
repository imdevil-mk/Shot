package com.imdevil.shot.core.network.common.model

import com.squareup.moshi.JsonQualifier

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class MoshiAdapter(val adapterName: String)
