package com.example.ncc_companion.ui.screens.certificates

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ncc_companion.model.AttendanceSummary

@Composable
fun CertificatesScreen(
    attendance: AttendanceSummary,
) {
    // State for showing prompt + approval bar
    var showExemptionRequested by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(vertical = 16.dp)
    ) {
        AttendanceCard(
            record = attendance,
            onRequestExemption = {
                showExemptionRequested = true
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Snackbar-like success message
        if (showExemptionRequested) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Exemption Request Submitted",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Approval Bar
            LinearProgressIndicator(
                progress = 0.3f, // You can update this dynamically
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Pending Approval",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AttendanceCard(
    record: AttendanceSummary,
    onRequestExemption: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = record.cadetName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = record.attendancePercent.coerceIn(0, 100) / 100f,
                        modifier = Modifier.size(120.dp),
                        strokeWidth = 12.dp
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${record.attendancePercent}%",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Attendance",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    AttendanceMetric("Sessions Attended", record.attended.toString(), true)
                    Spacer(modifier = Modifier.height(12.dp))
                    AttendanceMetric("Sessions Missed", record.missed.toString(), false)
                    Spacer(modifier = Modifier.height(12.dp))
                    AttendanceMetric("Total Sessions", record.totalParades.toString(), null)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = record.attendancePercent.coerceIn(0, 100) / 100f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { onRequestExemption() },
                enabled = record.missed > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Request Exemption")
            }

            if (record.remarks.isNotBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = record.remarks,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
private fun AttendanceMetric(
    label: String,
    value: String,
    isPositive: Boolean?
) {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = when (isPositive) {
                true -> MaterialTheme.colorScheme.primary
                false -> MaterialTheme.colorScheme.error
                null -> MaterialTheme.colorScheme.onSurface
            }
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
