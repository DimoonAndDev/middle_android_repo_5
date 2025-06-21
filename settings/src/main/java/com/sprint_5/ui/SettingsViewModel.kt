package com.sprint_5.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprint_5.data_store.SettingContainer
import com.sprint_5.ui.contract.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    fun saveSetting(periodic: Long, delayed: Long) {
        viewModelScope.launch {
            settingsRepository.saveSetting(periodic = periodic, delayed = delayed)
        }
    }

    fun getCurrentSetting(): SettingContainer {
        return settingsRepository.settingData.value
    }
}