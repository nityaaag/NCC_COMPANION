package com.example.ncc_companion.model

data class EcoTask(
    val id: String,
    val title: String,
    val description: String,
    val week: String,
    val assignedDate: String,
    val dueDate: String,
    val isCompleted: Boolean = false,
    val completionDate: String? = null
)

data class EcoActivityWeek(
    val weekNumber: String,
    val startDate: String,
    val endDate: String,
    val tasks: List<EcoTask>
) {
    val completedTasks: Int get() = tasks.count { it.isCompleted }
    val totalTasks: Int get() = tasks.size
    val completionPercent: Int get() = if (totalTasks == 0) 0 else (completedTasks * 100) / totalTasks
}

