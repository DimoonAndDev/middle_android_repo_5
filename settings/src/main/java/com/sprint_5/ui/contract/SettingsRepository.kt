package com.sprint_5.ui.contract

import com.sprint_5.data_store.SettingContainer
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val settingData: StateFlow<SettingContainer>
    suspend fun saveSetting(periodic: Long, delayed: Long)
    suspend fun readSetting()
}