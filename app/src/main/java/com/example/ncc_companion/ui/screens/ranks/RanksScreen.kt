package com.example.ncc_companion.ui.screens.ranks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ncc_companion.model.Rank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RanksScreen(ranks: List<Rank>) {
    var selectedRank by remember { mutableStateOf<Rank?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(ranks) { rank ->
            RankCard(rank = rank) { selectedRank = rank }
        }
    }

    selectedRank?.let { rank ->
        ModalBottomSheet(
            onDismissRequest = { selectedRank = null },
            sheetState = sheetState
        ) {
            RankDetail(rank = rank)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RankCard(rank: Rank, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = rank.imageRes),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = rank.title, fontWeight = FontWeight.Bold)
                Text(text = rank.level, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = rank.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun RankDetail(rank: Rank) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = rank.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Wing: ${rank.wing.displayName}")
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(id = rank.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = rank.description)
    }
}
