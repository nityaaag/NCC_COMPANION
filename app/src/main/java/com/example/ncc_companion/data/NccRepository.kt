package com.example.ncc_companion.data

import com.example.ncc_companion.R
import com.example.ncc_companion.model.AttendanceSummary
import com.example.ncc_companion.model.Command
import com.example.ncc_companion.model.EcoActivityWeek
import com.example.ncc_companion.model.EcoTask
import com.example.ncc_companion.model.PdfResource
import com.example.ncc_companion.model.Rank
import com.example.ncc_companion.model.ResourceLink
import com.example.ncc_companion.model.Wing

class NccRepository {

    fun getCadetAttendance(): AttendanceSummary = AttendanceSummary(
        id = "current_cadet",
        cadetName = "Your Attendance",
        totalParades = 48,
        attended = 44,
        excused = 2,
        remarks = "Keep up the excellent attendance record!"
    )

    fun getRanks(): List<Rank> = listOf(
        Rank(
            id = "army_lcpl",
            title = "Lance Corporal",
            level = "Junior Non-Commissioned Officer",
            description = "First step of leadership for NCC cadets in the Army wing.",
            wing = Wing.ARMY,
            imageRes = R.drawable.ic_rank_army
        ),
        Rank(
            id = "army_cpl",
            title = "Corporal",
            level = "Junior Non-Commissioned Officer",
            description = "Responsible for section level discipline and training.",
            wing = Wing.ARMY,
            imageRes = R.drawable.ic_rank_army
        ),
        Rank(
            id = "navy_po",
            title = "Petty Officer",
            level = "Sailor Supervisor",
            description = "Key link between cadet divisions and naval instructors.",
            wing = Wing.NAVY,
            imageRes = R.drawable.ic_rank_navy
        ),
        Rank(
            id = "navy_cpo",
            title = "Chief Petty Officer",
            level = "Senior Cadet",
            description = "Leads sea training schedules and ceremonial duties.",
            wing = Wing.NAVY,
            imageRes = R.drawable.ic_rank_navy
        ),
        Rank(
            id = "air_uwo",
            title = "Under Officer",
            level = "Senior Air Cadet",
            description = "Handles parade formations and flying training roll calls.",
            wing = Wing.AIR,
            imageRes = R.drawable.ic_rank_air
        ),
        Rank(
            id = "air_cwo",
            title = "Cadet Warrant Officer",
            level = "Top Cadet Rank",
            description = "Primary advisor to ANOs for air wing operations.",
            wing = Wing.AIR,
            imageRes = R.drawable.ic_rank_air
        )
    )

    fun getCommands(): List<Command> = listOf(
        Command("cmd_savdhan", "Attention", "Savdhaan", "Prepare body and mind in a steady stance."),
        Command("cmd_vishram", "Stand at Ease", "Vishram", "Relax while keeping discipline between drills."),
        Command("cmd_dahine", "Right Turn", "Dahine Mud", "Execute a crisp quarter turn to the right."),
        Command("cmd_baen", "Left Turn", "Baen Mud", "Rotate 90 degrees left with heels locked."),
        Command("cmd_about", "About Turn", "Daayein Ghoom", "Swift 180-degree turn maintaining formation."),
        Command("cmd_salute", "Salute", "Salami Shastra", "Ceremonial salute with rifle or bare hand as ordered.")
    )

    fun getEcoActivityWeeks(): List<EcoActivityWeek> = listOf(
        EcoActivityWeek(
            weekNumber = "Week 1",
            startDate = "2025-01-06",
            endDate = "2025-01-12",
            tasks = listOf(
                EcoTask(
                    id = "task_1_1",
                    title = "Tree Plantation Drive",
                    description = "Plant 10 saplings in designated area and document with photos",
                    week = "Week 1",
                    assignedDate = "2025-01-06",
                    dueDate = "2025-01-12",
                    isCompleted = true,
                    completionDate = "2025-01-10"
                ),
                EcoTask(
                    id = "task_1_2",
                    title = "Waste Segregation Workshop",
                    description = "Organize and conduct waste segregation awareness session",
                    week = "Week 1",
                    assignedDate = "2025-01-06",
                    dueDate = "2025-01-12",
                    isCompleted = true,
                    completionDate = "2025-01-11"
                ),
                EcoTask(
                    id = "task_1_3",
                    title = "Clean-up Campaign",
                    description = "Participate in local area clean-up drive for 2 hours",
                    week = "Week 1",
                    assignedDate = "2025-01-06",
                    dueDate = "2025-01-12",
                    isCompleted = false
                )
            )
        ),
        EcoActivityWeek(
            weekNumber = "Week 2",
            startDate = "2025-01-13",
            endDate = "2025-01-19",
            tasks = listOf(
                EcoTask(
                    id = "task_2_1",
                    title = "Water Conservation Survey",
                    description = "Survey 20 households for water usage patterns and suggest improvements",
                    week = "Week 2",
                    assignedDate = "2025-01-13",
                    dueDate = "2025-01-19",
                    isCompleted = false
                ),
                EcoTask(
                    id = "task_2_2",
                    title = "Composting Setup",
                    description = "Set up composting unit at home and maintain for one week",
                    week = "Week 2",
                    assignedDate = "2025-01-13",
                    dueDate = "2025-01-19",
                    isCompleted = false
                ),
                EcoTask(
                    id = "task_2_3",
                    title = "Energy Audit",
                    description = "Conduct energy audit of your home and submit report",
                    week = "Week 2",
                    assignedDate = "2025-01-13",
                    dueDate = "2025-01-19",
                    isCompleted = false
                )
            )
        ),
        EcoActivityWeek(
            weekNumber = "Week 3",
            startDate = "2025-01-20",
            endDate = "2025-01-26",
            tasks = listOf(
                EcoTask(
                    id = "task_3_1",
                    title = "Recycling Drive",
                    description = "Collect and recycle 5kg of plastic waste from neighborhood",
                    week = "Week 3",
                    assignedDate = "2025-01-20",
                    dueDate = "2025-01-26",
                    isCompleted = false
                ),
                EcoTask(
                    id = "task_3_2",
                    title = "Awareness Poster",
                    description = "Create and display eco-friendly awareness poster in community",
                    week = "Week 3",
                    assignedDate = "2025-01-20",
                    dueDate = "2025-01-26",
                    isCompleted = false
                )
            )
        )
    )

    fun getResourceLinks(): List<ResourceLink> = listOf(
        ResourceLink(
            id = "link_official",
            title = "NCC Official Website",
            description = "Latest circulars, policy letters, and announcements.",
            url = "https://indiancc.nic.in"
        ),
        ResourceLink(
            id = "link_dgncc",
            title = "DG NCC Handbook",
            description = "Reference for instructors and cadets.",
            url = "https://www.dg-ncc.gov.in"
        ),
        ResourceLink(
            id = "link_youtube",
            title = "NCC YouTube Channel",
            description = "Video lessons on drill, yoga, and best practices.",
            url = "https://www.youtube.com/@dgncc"
        )
    )

    fun getPdfResources(): List<PdfResource> = listOf(
        PdfResource(
            id = "pdf_handbook",
            title = "Cadet Pocket Book",
            description = "Essential reference for day-to-day parade queries."
        ),
        PdfResource(
            id = "pdf_drill",
            title = "Drill Manual",
            description = "Commands, formations, and ceremonial guard help."
        ),
        PdfResource(
            id = "pdf_map",
            title = "Map Reading Notes",
            description = "Quick refresh before camps and tests."
        )
    )

    fun getNccSongLyrics(): String = """
        Hum Sab Bharatiya Hain, Hum Sab Bharatiya Hain
        Apni Manzil Ek Hai, Ha, Ha, Ha, Ek Hai
        Hum Sab Bharatiya Hain

        Kashmir Ki Dharti Rani Hai, Sartaj Himalaya Hai
        Sadiyon Se Humne Isko Apne Khoon Se Pala Hai
        Desh Ki Raksha Ki Khatir Hum Shamshir Utha Lenge
        Hum Sab Bharatiya Hain
    """.trimIndent()
}
