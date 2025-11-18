package com.example.ncc_companion.ui.screens.certificates

import androidx.lifecycle.ViewModel
import com.example.ncc_companion.data.NccRepository
import com.example.ncc_companion.model.AttendanceSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CertificatesViewModel(
    private val repository: NccRepository = NccRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CertificatesUiState(
            attendanceSummary = repository.getCadetAttendance()
        )
    )
    val uiState: StateFlow<CertificatesUiState> = _uiState.asStateFlow()
}

data class CertificatesUiState(
    val attendanceSummary: AttendanceSummary
)
