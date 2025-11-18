import com.example.ncc_companion.model.Command

fun getCommands(): List<Command> = listOf(

    /* -------------------- BASIC DRILL COMMANDS -------------------- */

    Command(
        "cmd_savdhan", "Attention", "Savdhaan",
        "Stand straight, heels together, body steady, no movement."
    ),
    Command("cmd_vishram", "Stand at Ease", "Vishram",
        "Relax stance with hands behind the back."),
    Command("cmd_aram", "Rest", "Aaraam Se",
        "Stand comfortably, slight movement allowed."),

    /* -------------------- TURNING COMMANDS -------------------- */

    Command("cmd_dahine", "Right Turn", "Dahine Mud",
        "Turn 90° to the right, maintaining balance and alignment."),
    Command("cmd_baen", "Left Turn", "Baen Mud",
        "Turn 90° to the left crisply."),
    Command("cmd_about", "About Turn", "Peeche Mud",
        "Turn 180° keeping heels together."),
    Command("cmd_right_dress", "Right Dress", "Dahine Saj",
        "Align the squad to the right."),
    Command("cmd_left_dress", "Left Dress", "Baen Saj",
        "Align the squad to the left."),

    /* -------------------- SALUTING COMMANDS -------------------- */

    Command("cmd_salute", "Hand Salute", "Salami Shastra (Without Rifle)",
        "Raise right hand to the forehead sharply."),
    Command("cmd_rifle_salute", "Rifle Salute", "Tez Chaal Salami Shastra",
        "Salute using rifle in ceremonial drills."),
    Command("cmd_open_order", "Open Order March", "Khuli Line Banaao",
        "March backward or forward to open spacing for salute."),
    Command("cmd_close_order", "Close Order March", "Bandh Line Banaao",
        "Return to closed formation after salute."),

    /* -------------------- MARCHING COMMANDS -------------------- */

    Command("cmd_forward_march", "Forward March", "Dhaaine Kadam Tal",
        "Begin marching with the left foot first."),
    Command("cmd_mark_time", "Mark Time", "Tham Se Kadam Taal",
        "March in place without moving forward."),
    Command("cmd_halt", "Halt", "Dhaine Tham",
        "Stop marching instantly on command."),
    Command("cmd_double_march", "Double March", "Do Chaal",
        "Faster march used during urgent movement."),
    Command("cmd_slow_march", "Slow March", "Dheere Chaal",
        "Slow ceremonial marching used during parades."),

    /* -------------------- RIFLE DRILL COMMANDS -------------------- */

    Command("cmd_shastra_sambhal", "Shoulder Arms", "Shastra Sambhal",
        "Position rifle on the shoulder in ready position."),
    Command("cmd_shastra_arambh", "Order Arms", "Shastra Aram",
        "Place rifle on the ground beside the right foot."),
    Command("cmd_shastra_prastut", "Present Arms", "Shastra Prastut",
        "Rifle salute posture."),
    Command("cmd_shastra_badhaai", "Slope Arms", "Baazu Shastra",
        "Rest rifle on the left shoulder at a slope."),
    Command("cmd_shastra_bail", "Trail Arms", "Trail Shastra",
        "Hold rifle in right hand while marching."),
    Command("cmd_shastra_badan_se", "Fix Bayonet", "Bayonet Lagao",
        "Fix the bayonet to the rifle for ceremonial/field duties."),
    Command("cmd_shastra_nikaalo", "Unfix Bayonet", "Bayonet Hatao",
        "Remove bayonet from rifle."),

    /* -------------------- PARADE & CEREMONIAL COMMANDS -------------------- */

    Command("cmd_parade_attention", "Parade Attention", "Virodh Savdhaan",
        "Parade rest position preparing for inspection."),
    Command("cmd_report", "Fall in", "Line Mein Aa Jao",
        "Cadets assemble in proper formation."),
    Command("cmd_fall_out", "Fall Out", "Line Se Hatao",
        "Cadets may break formation without leaving area."),
    Command("cmd_move_left", "Left Incline", "Baen Ghum",
        "Turn slightly left (45°)."),
    Command("cmd_move_right", "Right Incline", "Dahina Ghum",
        "Turn slightly right (45°)."),
    Command("cmd_file_left", "Left File", "Baen Pankti",
        "Formation moves left in single file."),
    Command("cmd_file_right", "Right File", "Dahine Pankti",
        "Formation moves right in single line."),

    /* -------------------- FLAG & NATIONAL COMMANDS -------------------- */

    Command("cmd_flag_salve", "Salute to National Flag", "Tirange Ko Salam",
        "Formal salute during flag hoisting or lowering."),
    Command("cmd_sanghatan", "Group Attention", "Sangathan Savdhaan",
        "Used during large gatherings and ceremonies."),
    Command("cmd_jhanda_upar", "Flag Up", "Jhanda Uthao",
        "Command given at national functions."),
    Command("cmd_jhanda_niche", "Flag Down", "Jhanda Neeche",
        "Lowering the national flag ceremonially."),

    /* -------------------- NAVY COMMANDS -------------------- */

    Command("cmd_boat_pull", "Pull Oars", "Kashti Kheecho",
        "Used during rowing activities in Naval Wing."),
    Command("cmd_boat_stop", "Oars!", "Oar Sambhal",
        "Stop rowing immediately."),
    Command("cmd_semaphore_start", "Start Semaphore", "Sanket Shuru",
        "Begin flag signaling drill."),

    /* -------------------- AIR WING COMMANDS -------------------- */

    Command("cmd_flight_fallin", "Flight Fall In", "Flight Line Mein",
        "Air Wing formation lineup."),
    Command("cmd_propeller_turn", "Propeller Drill", "Propeller Mud",
        "Used during aero-model handling commands."),
    Command("cmd_glider_hold", "Hold Glider", "Glider Sambhalo",
        "Safety command during glider training."),

    /* -------------------- SPECIAL COMMANDS -------------------- */

    Command("cmd_morale", "Unit War Cry", "Josh",
        "Shout war cry after completion of drills."),
    Command("cmd_shout_ncc", "NCC Song Call", "N-C-C!",
        "Used before singing NCC song or march-past."),
)
