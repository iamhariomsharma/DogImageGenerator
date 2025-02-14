package com.heckteck.dogslibrary.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Home: Screens
    @Serializable
    data object GenerateDogs: Screens
    @Serializable
    data object Library: Screens
}