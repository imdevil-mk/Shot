package com.imdevil.netease.policy.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NeteaseCrypto(val type: CryptoType = CryptoType.WEAPI)

enum class CryptoType {
    WEAPI, EAPI
}