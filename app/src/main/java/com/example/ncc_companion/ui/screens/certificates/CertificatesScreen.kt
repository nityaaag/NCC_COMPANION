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
    attendance: AttendanceSummary
) {
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

        Spacer(modifier = Modifier.height(20.dp))

        /* ---------------------------------------------------
         *   Success Prompt + Approval Progress Bar
         * --------------------------------------------------- */
        if (showExemptionRequested) {

            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(
                    text = "Exemption Request Submitted",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = 0.35f,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Pending Approval...",
                style = MaterialTheme.typography.labelLarge,
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
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            /* ---------------------------------------------------
             *                Cadet Title
             * --------------------------------------------------- */
            Text(
                text = record.cadetName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            /* ---------------------------------------------------
             *        Circular Attendance Progress
             * --------------------------------------------------- */
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = record.attendancePercent.coerceIn(0, 100) / 100f,
                        modifier = Modifier.size(130.dp),
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.primary
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
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    AttendanceMetric("Sessions Attended", "${record.attended}", true)
                    Spacer(modifier = Modifier.height(12.dp))
                    AttendanceMetric("Sessions Missed", "${record.missed}", false)
                    Spacer(modifier = Modifier.height(12.dp))
                    AttendanceMetric("Total Sessions", "${record.totalParades}", null)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = record.attendancePercent / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            /* ---------------------------------------------------
             *                Request Exemption Button
             * --------------------------------------------------- */
            Button(
                onClick = { onRequestExemption() },
                enabled = record.missed > 0,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Request Exemption",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (record.remarks.isNotBlank()) {
                Spacer(modifier = Modifier.height(14.dp))

                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = record.remarks,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
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
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = when (isPositive) {
                true -> MaterialTheme.colorScheme.primary
                false -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onSurface
            }
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
