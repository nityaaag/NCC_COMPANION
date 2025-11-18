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

// NOTE: Ensure NccRepository is a real class, not an annotation.
class NccViewModel(
    private val repository: NccRepository = NccRepository()
) : ViewModel() {

    /* --------------------- RANKS --------------------- */
    // Fixed: Explicitly typed as List<Rank> and assumes repository returns a List
    private val _ranks = MutableStateFlow<List<Rank>>(repository.getRanks())
    val ranks: StateFlow<List<Rank>> = _ranks.asStateFlow()

    /* --------------------- COMMANDS --------------------- */
    private val _commands = MutableStateFlow<List<Command>>(repository.getCommands())
    val commands: StateFlow<List<Command>> = _commands.asStateFlow()

    /* --------------------- ECO ACTIVITY WEEKS --------------------- */
    private val _ecoActivityWeeks = MutableStateFlow<List<EcoActivityWeek>>(repository.getEcoActivityWeeks())
    val ecoActivityWeeks: StateFlow<List<EcoActivityWeek>> = _ecoActivityWeeks.asStateFlow()

    /* --------------------- USEFUL LINKS --------------------- */
    private val _resourceLinks = MutableStateFlow<List<ResourceLink>>(repository.getResourceLinks())
    val resourceLinks: StateFlow<List<ResourceLink>> = _resourceLinks.asStateFlow()

    /* --------------------- PDF RESOURCES --------------------- */
    private val _pdfResources = MutableStateFlow<List<PdfResource>>(repository.getPdfResources())
    val pdfResources: StateFlow<List<PdfResource>> = _pdfResources.asStateFlow()

    /* --------------------- ATTENDANCE --------------------- */
    private val _attendance = MutableStateFlow<AttendanceSummary>(repository.getCadetAttendance())
    val attendance: StateFlow<AttendanceSummary> = _attendance.asStateFlow()

    /* --------------------- UPLOADED PDF LIBRARY --------------------- */
    private val _uploadedPdfs = MutableStateFlow<List<UploadedPdf>>(emptyList())
    val uploadedPdfs: StateFlow<List<UploadedPdf>> = _uploadedPdfs.asStateFlow()

    /* --------------------- NCC SONG LYRICS --------------------- */
    val nccSongLyrics: String = repository.getNccSongLyrics()

    /* ========================= FUNCTIONS ========================= */

    // Find rank by ID
    fun findRank(rankId: String): Rank? =
        _ranks.value.firstOrNull { it.id == rankId }

    // Add uploaded PDF to user's library
    fun addUploadedPdf(uploadedPdf: UploadedPdf) {
        _uploadedPdfs.value = _uploadedPdfs.value + uploadedPdf
    }

    // Toggle eco-task completion
    fun toggleTaskCompletion(taskId: String) {
        val updated = _ecoActivityWeeks.value.map { week ->
            val updatedTasks = week.tasks.map { task ->
                if (task.id == taskId) {
                    val now = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val newState = !task.isCompleted

                    // Note: Ensure EcoTask is a 'data class' for .copy() to work
                    task.copy(
                        isCompleted = newState,
                        completionDate = if (newState) now.format(Date()) else null
                    )
                } else task
            }
            // Note: Ensure EcoActivityWeek is a 'data class' for .copy() to work
            week.copy(tasks = updatedTasks)
        }

        _ecoActivityWeeks.value = updated
    }
}