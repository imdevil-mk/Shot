package com.imdevil.netease.policy.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NeteaseHost(val host: String)
