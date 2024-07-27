package com.yoesuv.formvalidationcompose.ui.route

import kotlinx.serialization.Serializable

sealed class AppRoute {
    @Serializable data object Login: AppRoute()
    @Serializable data object Register: AppRoute()
    @Serializable data object Home: AppRoute()
}