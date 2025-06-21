package com.sprint_5.di

import com.sprint_5.data_store.SettingsRepositoryImpl
import com.sprint_5.ui.SettingsViewModel
import com.sprint_5.ui.contract.SettingsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl(androidApplication()) }
    viewModel { SettingsViewModel(get()) }
}