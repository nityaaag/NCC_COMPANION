package com.example.ncc_companion.data

import androidx.lifecycle.ViewModel
import com.example.ncc_companion.model.AttendanceSummary
import com.example.ncc_companion.model.Command
import com.example.ncc_companion.model.EcoActivityWeek
import com.example.ncc_companion.model.EcoTask
import com.example.ncc_companion.model.PdfResource
import com.example.ncc_companion.model.Rank
import com.example.ncc_companion.model.ResourceLink
import com.example.ncc_companion.model.UploadedPdf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NccViewModel(
    private val repository: NccRepository = NccRepository()
) : ViewModel() {

    private val _ranks = MutableStateFlow(repository.getRanks())
    val ranks: StateFlow<List<Rank>> = _ranks.asStateFlow()

    private val _commands = MutableStateFlow(repository.getCommands())
    val commands: StateFlow<List<Command>> = _commands.asStateFlow()

    private val _ecoActivityWeeks = MutableStateFlow(repository.getEcoActivityWeeks())
    val ecoActivityWeeks: StateFlow<List<EcoActivityWeek>> = _ecoActivityWeeks.asStateFlow()

    private val _resourceLinks = MutableStateFlow(repository.getResourceLinks())
    val resourceLinks: StateFlow<List<ResourceLink>> = _resourceLinks.asStateFlow()

    private val _pdfResources = MutableStateFlow(repository.getPdfResources())
    val pdfResources: StateFlow<List<PdfResource>> = _pdfResources.asStateFlow()

    private val _attendance = MutableStateFlow(repository.getCadetAttendance())
    val attendance: StateFlow<AttendanceSummary> = _attendance.asStateFlow()

    private val _uploadedPdfs = MutableStateFlow<List<UploadedPdf>>(emptyList())
    val uploadedPdfs: StateFlow<List<UploadedPdf>> = _uploadedPdfs.asStateFlow()

    val nccSongLyrics: String = repository.getNccSongLyrics()

    fun findRank(rankId: String): Rank? = _ranks.value.firstOrNull { it.id == rankId }

    fun addUploadedPdf(uploadedPdf: UploadedPdf) {
        _uploadedPdfs.value = _uploadedPdfs.value + uploadedPdf
    }

    fun toggleTaskCompletion(taskId: String) {
        val currentWeeks = _ecoActivityWeeks.value.toMutableList()
        val updatedWeeks = currentWeeks.map { week ->
            val updatedTasks = week.tasks.map { task ->
                if (task.id == taskId) {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val newCompletedState = !task.isCompleted
                    task.copy(
                        isCompleted = newCompletedState,
                        completionDate = if (newCompletedState) dateFormat.format(Date()) else null
                    )
                } else {
                    task
                }
            }
            week.copy(tasks = updatedTasks)
        }
        _ecoActivityWeeks.value = updatedWeeks
    }
}
