package com.example.ncc_companion.ui.screens.ranks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ncc_companion.model.Rank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RanksScreen(
    ranks: List<Rank>
) {
    var selectedRank by remember { mutableStateOf<Rank?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF003D5C),
                        Color(0xFF015C55),
                        Color(0xFF0D9488)
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "NCC Ranks",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Text(
            text = "Know your position, leadership and growth in NCC.",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(0.8f)),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ranks) { rank ->
                RankCard(rank = rank) {
                    selectedRank = rank
                }
            }
        }
    }

    if (selectedRank != null) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { selectedRank = null },
            containerColor = Color(0xFFF2F2F2)
        ) {
            RankDetail(rank = selectedRank!!)
        }
    }
}

/* ---------------------------------------------------------
 *                   RANK CARD (SUMMARY)
 * --------------------------------------------------------- */
@Composable
private fun RankCard(
    rank: Rank,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = rank.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF003D5C)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = rank.level,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color(0xFF026873)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = rank.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF4A4A4A)
                    ),
                    maxLines = 3
                )
            }
        }
    }
}

/* ---------------------------------------------------------
 *                   RANK DETAIL (BOTTOM SHEET)
 * --------------------------------------------------------- */
@Composable
private fun RankDetail(rank: Rank) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
    ) {

        Text(
            text = rank.title,
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color(0xFF0B3C5D),
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = rank.level,
            color = Color(0xFF007F8C),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(18.dp))

        DetailBlock("Description", rank.description)
        DetailBlock("Responsibilities", rank.responsibilities)
        DetailBlock("Badge Meaning", rank.badgeMeaning)
        DetailBlock("Promotion Criteria", rank.promotionCriteria)
        DetailBlock("Training & Skills", rank.training)
    }
}

/* ---------------------------------------------------------
 *                REUSABLE DETAIL BLOCK
 * --------------------------------------------------------- */
@Composable
private fun DetailBlock(
    title: String,
    text: String
) {
    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF003D5C)
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFE5F5F7)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF084C61)
                ),
                modifier = Modifier.padding(14.dp)
            )
        }
    }
}
