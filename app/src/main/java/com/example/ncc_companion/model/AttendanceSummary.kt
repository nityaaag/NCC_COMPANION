package com.example.ncc_companion.model

data class AttendanceSummary(
    val id: String,
    val cadetName: String,
    val totalParades: Int,
    val attended: Int,
    val excused: Int = 0,
    val remarks: String = ""
) {
    val missed: Int get() = (totalParades - attended).coerceAtLeast(0)
    val attendancePercent: Int get() = if (totalParades == 0) 0 else (attended * 100) / totalParades
}
