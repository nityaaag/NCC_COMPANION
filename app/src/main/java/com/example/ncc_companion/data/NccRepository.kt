package com.example.ncc_companion.data

import com.example.ncc_companion.R
import com.example.ncc_companion.model.AttendanceSummary
import com.example.ncc_companion.model.Command
import com.example.ncc_companion.model.EcoActivityWeek
import com.example.ncc_companion.model.EcoTask // Required Import
import com.example.ncc_companion.model.PdfResource
import com.example.ncc_companion.model.Rank
import com.example.ncc_companion.model.ResourceLink
import com.example.ncc_companion.model.Wing

class NccRepository {

    /* ========================= COMMANDS ========================= */
    fun getCommands(): List<Command> = listOf(
        Command("cmd_savdhan", "Attention", "Savdhaan", "Stand straight, heels together, body steady, no movement."),
        Command("cmd_vishram", "Stand at Ease", "Vishram", "Relax stance with hands behind the back."),
        Command("cmd_aram", "Rest", "Aaraam Se", "Stand comfortably, slight movement allowed."),
        Command("cmd_dahine", "Right Turn", "Dahine Mud", "Turn 90° to the right, maintaining balance and alignment."),
        Command("cmd_baen", "Left Turn", "Baen Mud", "Turn 90° to the left crisply."),
        Command("cmd_about", "About Turn", "Peeche Mud", "Turn 180° keeping heels together."),
        Command("cmd_right_dress", "Right Dress", "Dahine Saj", "Align the squad to the right."),
        Command("cmd_left_dress", "Left Dress", "Baen Saj", "Align the squad to the left."),
        Command("cmd_salute", "Hand Salute", "Salami Shastra (Without Rifle)", "Raise right hand to the forehead sharply."),
        Command("cmd_rifle_salute", "Rifle Salute", "Tez Chaal Salami Shastra", "Salute using rifle in ceremonial drills."),
        Command("cmd_open_order", "Open Order March", "Khuli Line Banaao", "March backward or forward to open spacing for salute."),
        Command("cmd_close_order", "Close Order March", "Bandh Line Banaao", "Return to closed formation after salute."),
        Command("cmd_forward_march", "Forward March", "Dhaaine Kadam Tal", "Begin marching with the left foot first."),
        Command("cmd_mark_time", "Mark Time", "Tham Se Kadam Taal", "March in place without moving forward."),
        Command("cmd_halt", "Halt", "Dhaine Tham", "Stop marching instantly on command."),
        Command("cmd_double_march", "Double March", "Do Chaal", "Faster march used during urgent movement."),
        Command("cmd_slow_march", "Slow March", "Dheere Chaal", "Slow ceremonial marching used during parades."),
        Command("cmd_shastra_sambhal", "Shoulder Arms", "Shastra Sambhal", "Position rifle on the shoulder in ready position."),
        Command("cmd_shastra_arambh", "Order Arms", "Shastra Aram", "Place rifle on the ground beside the right foot."),
        Command("cmd_shastra_prastut", "Present Arms", "Shastra Prastut", "Rifle salute posture."),
        Command("cmd_shastra_badhaai", "Slope Arms", "Baazu Shastra", "Rest rifle on the left shoulder at a slope."),
        Command("cmd_shastra_bail", "Trail Arms", "Trail Shastra", "Hold rifle in right hand while marching."),
        Command("cmd_shastra_badan_se", "Fix Bayonet", "Bayonet Lagao", "Fix the bayonet to the rifle for ceremonial/field duties."),
        Command("cmd_shastra_nikaalo", "Unfix Bayonet", "Bayonet Hatao", "Remove bayonet from rifle."),
        Command("cmd_parade_attention", "Parade Attention", "Virodh Savdhaan", "Parade rest position preparing for inspection."),
        Command("cmd_report", "Fall in", "Line Mein Aa Jao", "Cadets assemble in proper formation."),
        Command("cmd_fall_out", "Fall Out", "Line Se Hatao", "Cadets may break formation without leaving area."),
        Command("cmd_move_left", "Left Incline", "Baen Ghum", "Turn slightly left (45°)."),
        Command("cmd_move_right", "Right Incline", "Dahina Ghum", "Turn slightly right (45°)."),
        Command("cmd_file_left", "Left File", "Baen Pankti", "Formation moves left in single file."),
        Command("cmd_file_right", "Right File", "Dahine Pankti", "Formation moves right in single line."),
        Command("cmd_flag_salve", "Salute to National Flag", "Tirange Ko Salam", "Formal salute during flag hoisting or lowering."),
        Command("cmd_sanghatan", "Group Attention", "Sangathan Savdhaan", "Used during large gatherings and ceremonies."),
        Command("cmd_jhanda_upar", "Flag Up", "Jhanda Uthao", "Command given at national functions."),
        Command("cmd_jhanda_niche", "Flag Down", "Jhanda Neeche", "Lowering the national flag ceremonially."),
        Command("cmd_boat_pull", "Pull Oars", "Kashti Kheecho", "Used during rowing activities in Naval Wing."),
        Command("cmd_boat_stop", "Oars!", "Oar Sambhal", "Stop rowing immediately."),
        Command("cmd_semaphore_start", "Start Semaphore", "Sanket Shuru", "Begin flag signaling drill."),
        Command("cmd_flight_fallin", "Flight Fall In", "Flight Line Mein", "Air Wing formation lineup."),
        Command("cmd_propeller_turn", "Propeller Drill", "Propeller Mud", "Used during aero-model handling commands."),
        Command("cmd_glider_hold", "Hold Glider", "Glider Sambhalo", "Safety command during glider training."),
        Command("cmd_morale", "Unit War Cry", "Josh", "Shout war cry after completion of drills."),
        Command("cmd_shout_ncc", "NCC Song Call", "N-C-C!", "Used before singing NCC song or march-past."),
    )

    /* ========================= RANKS ========================= */
    // Note: Replace R.drawable.placeholder with your actual drawable resources.
    fun getRanks(): List<Rank> = listOf(
        Rank("rank_cdt", "Cadet", "Basic Level", Wing.ARMY, R.drawable.images,
            "The entry-level rank for all who join the NCC. This is the starting point of the journey.",
            "Attend parades, follow commands, learn basic drill and discipline.",
            "No badge at this level.",
            "Successful enrollment in the NCC.",
            "Basic foot drill, introduction to NCC, and institutional training."),
        Rank("rank_lcpl", "Lance Corporal", "Junior NCO", Wing.ARMY, R.drawable.images,
            "The first step into the non-commissioned officer (NCO) hierarchy. A rank given to promising cadets.",
            "Assist in managing a small group of cadets (a file).",
            "A single chevron.",
            "Showing leadership potential, good drill, and passing the 'A' Certificate exam.",
            "Advanced drill, weapon training, and basic leadership."),
        Rank("rank_cpl", "Corporal", "Junior NCO", Wing.ARMY, R.drawable.images,
            "A responsible rank, acting as a link between junior cadets and senior NCOs.",
            "Commanding a squad, ensuring discipline and turnout of junior cadets.",
            "Two chevrons.",
            "Consistent performance, leadership skills, and good command voice.",
            "Squad command, map reading, and field craft."),
        Rank("rank_sgt", "Sergeant", "Senior NCO", Wing.ARMY, R.drawable.images,
            "A key leadership position in the troop, responsible for a platoon.",
            "Managing a platoon, assisting the Company Quartermaster Sergeant.",
            "Three chevrons.",
            "Passing 'B' Certificate exam, strong leadership, and instructional ability.",
            "Platoon command, advanced map reading, and leadership training."),
        Rank("rank_cqms", "Company Quartermaster Sergeant", "Senior NCO", Wing.ARMY, R.drawable.images,
            "Responsible for the stores and supplies of the company.",
            "Managing the company's equipment, clothing, and rations (kote).",
            "Three chevrons with a national emblem (Ashok Chakra).",
            "Experience as a Sergeant, excellent organizational skills.",
            "Logistics management, storekeeping, and staff duties."),
        Rank("rank_csm", "Company Sergeant Major", "Senior NCO", Wing.ARMY, R.drawable.images,
            "The senior-most NCO in a company, responsible for discipline and drill.",
            "Overall supervision of drill, discipline, and administration in the company.",
            "National emblem (Ashok Chakra).",
            "Seniority and exemplary record as a Sergeant.",
            "Advanced leadership, ceremonial drill, and company management."),
        Rank("rank_juo", "Junior Under Officer", "Junior Officer", Wing.ARMY, R.drawable.images,
            "The first rank in the 'Under Officer' cadre, a junior leadership role.",
            "Assisting the Senior Under Officer, commanding a platoon.",
            "Two stars.",
            "Passing 'B' Certificate exam with a high grade, officer-like qualities.",
            "Officer training capsules, command and leadership camps."),
        Rank("rank_suo", "Senior Under Officer", "Senior Officer", Wing.ARMY, R.drawable.images,
            "The highest rank a cadet can achieve at the company/college level. The leader of the unit's cadets.",
            "Leading the entire company/contingent, liaison between cadets and ANOs.",
            "Three stars.",
            "Exemplary service as a JUO, outstanding leadership, and passing the 'C' Certificate exam.",
            "Advanced leadership camps, attachment with regular army units.")
    )

    /* ========================= ECO ACTIVITIES (FIXED) ========================= */
    fun getEcoActivityWeeks(): List<EcoActivityWeek> = listOf(
        EcoActivityWeek("eco_week1", "Week 1", "Cleanliness and Waste Management", listOf(
            EcoTask(
                id = "eco_1_1",
                title = "Campus clean-up drive.",
                description = "",
                week = "Week 1",
                assignedDate = "", // Fixed: Null cannot be a value of a non-null type String.
                dueDate = "" // Fixed: Null cannot be a value of a non-null type String.
            ),
            EcoTask(
                id = "eco_1_2",
                title = "Workshop on waste segregation.",
                description = "",
                week = "Week 1",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_1_3",
                title = "Poster making on 'Say No to Plastic'.",
                description = "",
                week = "Week 1",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            )
        )),
        EcoActivityWeek("eco_week2", "Week 2", "Water Conservation", listOf(
            EcoTask(
                id = "eco_2_1",
                title = "Awareness rally on 'Save Water'.",
                description = "",
                week = "Week 2",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_2_2",
                title = "Checking and repairing leaking taps in the institution.",
                description = "",
                week = "Week 2",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_2_3",
                title = "Session on rainwater harvesting.",
                description = "",
                week = "Week 2",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            )
        )),
        EcoActivityWeek("eco_week3", "Week 3", "Tree Plantation", listOf(
            EcoTask(
                id = "eco_3_1",
                title = "Mass tree plantation drive.",
                description = "",
                week = "Week 3",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_3_2",
                title = "Adopting a tree and taking care of it.",
                description = "",
                week = "Week 3",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_3_3",
                title = "Talk by a forestry expert.",
                description = "",
                week = "Week 3",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            )
        )),
        EcoActivityWeek("eco_week4", "Week 4", "Energy Conservation", listOf(
            EcoTask(
                id = "eco_4_1",
                title = "Pledge to switch off lights when not in use.",
                description = "",
                week = "Week 4",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_4_2",
                title = "Creating 'Save Energy' stickers for classrooms.",
                description = "",
                week = "Week 4",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            ),
            EcoTask(
                id = "eco_4_3",
                title = "Debate on renewable energy sources.",
                description = "",
                week = "Week 4",
                assignedDate = "", // Fixed
                dueDate = "" // Fixed
            )
        ))
    )

    /* ========================= RESOURCE LINKS ========================= */
    fun getResourceLinks(): List<ResourceLink> = listOf(
        ResourceLink("link_official", "Official NCC Website", "https://indiancc.nic.in/",
            "The official portal for the National Cadet Corps of India."),
        ResourceLink("link_dg_ncc", "DG NCC Training App", "https://play.google.com/store/apps/details?id=com.dgncc.training",
            "Official NCC app for training materials, videos, and FAQs."),
        ResourceLink("link_join_army", "Join Indian Army", "https://joinindianarmy.nic.in/",
            "Official website for recruitment into the Indian Army, including NCC special entry schemes.")
    )

    /* ========================= PDF RESOURCES (FIXED) ========================= */
    fun getPdfResources(): List<PdfResource> = listOf(
        PdfResource("pdf_drill", "Drill Manual (Army)", "army_drill_manual.pdf"),
        PdfResource("pdf_common", "Common Subjects Handbook", "common_subjects_handbook.pdf"),
        PdfResource("pdf_special","Specialized Subjects (Army)", "army_specialized_subjects.pdf")
    )

    /* ========================= ATTENDANCE ========================= */
    fun getCadetAttendance(): AttendanceSummary {
        // In a real app, this would be fetched from a database or API
        return AttendanceSummary(
            id = "cadet_123",
            cadetName = "Cadet Name",
            attended = 18,
            totalParades = 24
        )
    }

    /* ========================= NCC SONG ========================= */
    fun getNccSongLyrics(): String {
        return """
            Hum Sab Bhartiya Hain, Hum Sab Bhartiya Hain
            Apni Manzil Ek Hai,
            Ha, Ha, Ha, Ek Hai,
            Ho, Ho, Ho, Ek Hai.
            Hum Sab Bhartiya Hain.

            Kashmir Ki Dharti Rani Hai,
            Sartaj Himalaya Hai,
            Saadiyon Se Humne Isko Apne Khoon Se Pala Hai
            Desh Ki Raksha Ki Khatir, Hum Shamshir Utha Lenge,
            Hum Shamshir Utha Lenge.

            Bikhre Bikhre Taare Hain Hum, Lekin Jhilmil Ek Hai,
            Ha, Ha, Ha, Ek Hai,
            Ho, Ho, Ho, Ek Hai.
            Hum Sab Bhartiya Hain.

            Mandir Gurudware Bhi Hain Yahan,
            Aur Masjid Bhi Hai Yahan,
            Girija Ka Hai Ghariyaal Kahin,
            Mullah Ki Kahin Hai Ajaan.

            Ek Hi Apna Ram Hai, Ek Hi Allah Taala Hai,
            Ek Hi Allah Taala Hai, Rang Birange Deepak Hain Hum,
            Lekin Jagmag Ek Hai, Ha, Ha, Ha, Ek Hai, Ho, Ho, Ho, Ek Hai.
            Hum Sab Bhartiya Hain, Hum Sab Bhartiya Hain.
        """.trimIndent()
    }
}