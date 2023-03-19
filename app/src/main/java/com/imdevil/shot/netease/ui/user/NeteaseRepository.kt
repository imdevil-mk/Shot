package com.imdevil.shot.netease.ui.user

import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NeteaseRepository @Inject constructor(
    private val neteaseDataSource: NeteaseDataSource,
    private val externalScope: CoroutineScope,
) {

}