package com.example.ncc_companion.ui.screens.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*














import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ncc_companion.R
import com.example.ncc_companion.model.EcoActivityWeek
import com.example.ncc_companion.ui.nav.NccDestination

private data class HomeAction(
    val title: String,
    val caption: String,
    val route: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    featuredEcoWeeks: List<EcoActivityWeek>,
    onNavigate: (String) -> Unit
) {
    val actions = remember {
        listOf(
            HomeAction("Ranks", "Badges of honour", NccDestination.Ranks.route, Icons.Default.Flag),
            HomeAction("Commands", "Drill in Hindi & English", NccDestination.Commands.route, Icons.Default.Star),
            HomeAction("Song", "Sing with pride", NccDestination.Song.route, Icons.Default.LibraryMusic),
            HomeAction("Attendance", "Track your sessions", NccDestination.Certificates.route, Icons.Default.School),
            HomeAction("Eco Activity", "Weekly tasks", NccDestination.Camps.route, Icons.Default.Category),
            HomeAction("Resources", "Links & PDFs", NccDestination.Resources.route, Icons.Default.Assignment)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(horizontal = 20.dp)
            .padding(bottom = 16.dp)
    ) {

        // Top Profile Avatar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .shadow(6.dp, CircleShape)
                        .clickable { onNavigate("profile") }
                )
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item { HeroCard() }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        // Action Buttons Grid
        items(actions.chunked(2)) { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowItems.forEach { action ->
                    ActionCard(
                        modifier = Modifier.weight(1f),
                        action = action,
                        onClick = { onNavigate(action.route) }
                    )
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        // ECO Activity Section
        item {
            Text(
                text = "Recent Eco Activities",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }

        items(featuredEcoWeeks) { week ->
            ElevatedCard(
                onClick = { onNavigate(NccDestination.Camps.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = week.weekNumber, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${week.startDate} - ${week.endDate}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = week.completionPercent.coerceIn(0, 100) / 100f,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${week.completedTasks}/${week.totalTasks} tasks completed",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(40.dp)) }
    }
}

@Composable
private fun HeroCard() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0D47A1),
                            Color(0xFFD32F2F),
                            Color(0xFF0D47A1)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .shadow(12.dp, CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = "Welcome to NCC Companion",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Your complete NCC journey in one place",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActionCard(
    modifier: Modifier = Modifier,
    action: HomeAction,
    onClick: () -> Unit
) {
    val scale = remember { Animatable(1f) }

    ElevatedCard(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        scale.animateTo(0.95f, tween(80))
                        tryAwaitRelease()
                        scale.animateTo(1f, tween(80))
                        onClick()
                    }
                )
            },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ncc_logo),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = action.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = action.caption,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
