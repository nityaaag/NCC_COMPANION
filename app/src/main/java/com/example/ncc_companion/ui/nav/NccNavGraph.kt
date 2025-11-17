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
    var exemptionRequested by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(vertical = 16.dp)
    ) {
        AttendanceCard(
            record = attendance,
            exemptionRequested = exemptionRequested,
            onRequestExemption = {
                exemptionRequested = true
            }
        )
    }
}

@Composable
private fun AttendanceCard(
    record: AttendanceSummary,
    exemptionRequested: Boolean,
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
                        Text(text = "Attendance")
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

            // Request Exemption Button
            Button(
                onClick = onRequestExemption,
                enabled = !exemptionRequested && record.missed > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (exemptionRequested) "Exemption Requested" else "Request Exemption")
            }

            // Show prompt + approval progress bar after click
            if (exemptionRequested) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Your exemption request has been submitted.",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
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
private fun AttendanceMetric(label: String, value: String, isPositive: Boolean?) {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
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
