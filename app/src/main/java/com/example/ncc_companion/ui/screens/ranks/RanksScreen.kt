import com.example.ncc_companion.model.Rank
import com.example.ncc_companion.model.Wing

val ranks = listOf(

    Rank(
        id = "a1",
        title = "Cadet",
        level = "Entry Level",
        wing = Wing.ARMY,
        description = "Basic NCC cadet undergoing training.",
        responsibilities = "Attend parades, learn drills, participate in activities.",
        badgeMeaning = "No insignia. You are in training.",
        promotionCriteria = "Good attendance and discipline.",
        training = "Drill, map reading, first aid.",
        imageRes = TODO()
    ),

    Rank(
        id = "a2",
        title = "Lance Corporal (LCpl)",
        level = "Junior Rank",
        wing = Wing.ARMY,
        description = "First leadership position.",
        responsibilities = "Lead small groups, maintain discipline.",
        badgeMeaning = "One Chevron represents junior leadership.",
        promotionCriteria = "High attendance + drill evaluation.",
        training = "Advanced drill, voice command.",
        imageRes = TODO()
    ),

    Rank(
        id = "a3",
        title = "Corporal (Cpl)",
        level = "Intermediate Rank",
        wing = Wing.ARMY,
        description = "Helps in managing junior cadets.",
        responsibilities = "Training support, supervising squads.",
        badgeMeaning = "Two Chevrons represent responsibility.",
        promotionCriteria = "Good performance as LCpl.",
        training = "Leadership & weapon training basics.",
        imageRes = TODO()
    ),

    Rank(
        id = "a4",
        title = "Sergeant",
        level = "Senior Rank",
        wing = Wing.ARMY,
        description = "One of the senior-most ranks.",
        responsibilities = "Lead parades, maintain discipline.",
        badgeMeaning = "Three Chevrons indicate seniority.",
        promotionCriteria = "Excellent merit & discipline.",
        training = "Advanced leadership, drill mastery.",
        imageRes = TODO()
    ),

    Rank(
        id = "a5",
        title = "JUO",
        level = "Junior Under Officer",
        wing = Wing.ARMY,
        description = "Second-highest cadet leader.",
        responsibilities = "Assist SUO in parade & unit duties.",
        badgeMeaning = "Stripe with NCC emblem.",
        promotionCriteria = "Best performance + recommendation.",
        training = "Leadership + national camp exposure.",
        imageRes = TODO()
    ),

    Rank(
        id = "a6",
        title = "SUO",
        level = "Senior Under Officer",
        wing = Wing.ARMY,
        description = "Highest NCC cadet leader.",
        responsibilities = "Lead entire parade, commands unit.",
        badgeMeaning = "Gold stripe with NCC emblem.",
        promotionCriteria = "Top cadet of the institution.",
        training = "Highest-level NCC training.",
        imageRes = TODO()
    ),

    /* Navy Wing */

    Rank(
        id = "n1",
        title = "Leading Cadet",
        level = "Mid Rank",
        wing = Wing.NAVY,
        description = "Trained in basic seamanship.",
        responsibilities = "Team leading & marine discipline.",
        badgeMeaning = "Anchor insignia.",
        promotionCriteria = "Good merit.",
        training = "Boat pulling, knotting, semaphore.",
        imageRes = TODO()
    ),

    Rank(
        id = "n2",
        title = "Cadet Captain",
        level = "Senior Rank",
        wing = Wing.NAVY,
        description = "Head of naval cadets.",
        responsibilities = "Lead naval parades & drills.",
        badgeMeaning = "Crossed anchors symbol.",
        promotionCriteria = "Best naval cadet.",
        training = "Advanced naval training.",
        imageRes = TODO()
    ),

    /* Air Wing */

    Rank(
        id = "f1",
        title = "Senior Cadet",
        level = "Mid Rank",
        wing = Wing.AIR,
        description = "Experienced air wing cadet.",
        responsibilities = "Assist in flight & aero activities.",
        badgeMeaning = "Propeller insignia.",
        promotionCriteria = "Strong performance.",
        training = "Aviation basics, gliding.",
        imageRes = TODO()
    ),

    Rank(
        id = "f2",
        title = "Cadet Warrant Officer",
        level = "Senior Rank",
        wing = Wing.AIR,
        description = "Highest flying cadet rank.",
        responsibilities = "Lead air wing training.",
        badgeMeaning = "Propeller with wings.",
        promotionCriteria = "Best flying cadet.",
        training = "Advanced aviation + microlight flying.",
        imageRes = TODO()
    )
)
