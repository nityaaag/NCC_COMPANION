package com.example.ncc_companion.ui.screens.resources

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ncc_companion.model.PdfResource
import com.example.ncc_companion.model.ResourceLink
import com.example.ncc_companion.model.UploadedPdf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    pdfResources: List<PdfResource>,
    resourceLinks: List<ResourceLink>,
    uploadedPdfs: List<UploadedPdf>,
    onPdfUploaded: (name: String, uri: String) -> Unit
) {
    val context = LocalContext.current

    // PDF Picker Launcher
    val pdfPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
            val name = queryDisplayName(context.contentResolver, it) ?: "PDF File"
            onPdfUploaded(name, it.toString())
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        /* ---------------------- UPLOAD BUTTON ---------------------- */

        item {
            ElevatedCard(
                onClick = { pdfPickerLauncher.launch(arrayOf("application/pdf")) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Upload PDF from Device",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        /* ---------------------- MY LIBRARY SECTION ---------------------- */

        if (uploadedPdfs.isNotEmpty()) {

            item {
                SectionHeading("My Library")
            }

            items(uploadedPdfs) { pdf ->
                ResourceCard(
                    title = pdf.name,
                    subtitle = "Local PDF",
                    onClick = { openPdf(context, Uri.parse(pdf.uri)) }
                )
            }
        }

        /* ---------------------- REFERENCE PDFs ---------------------- */

        item {
            SectionHeading("Reference PDFs")
        }

        items(pdfResources) { pdf ->
            ResourceCard(
                title = pdf.title,
                subtitle = pdf.description,
                onClick = {
                    // You can enhance PDF loading here later
                }
            )
        }

        /* ---------------------- HELPFUL LINKS ---------------------- */

        item {
            SectionHeading("Helpful Links")
        }

        items(resourceLinks) { link ->
            ResourceCard(
                title = link.title,
                subtitle = link.description,
                buttonText = "Open Link",
                onClick = { openUrl(context, link.url) }
            )
        }
    }
}

/* ---------------------------------------------------------
   REUSABLE COMPONENTS
--------------------------------------------------------- */

@Composable
private fun SectionHeading(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResourceCard(
    title: String,
    subtitle: String,
    buttonText: String = "Open",
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(buttonText)
            }
        }
    }
}

/* ---------------------------------------------------------
   HELPERS
--------------------------------------------------------- */

private fun queryDisplayName(contentResolver: ContentResolver, uri: Uri): String? {
    val cursor = contentResolver.query(uri, null, null, null, null) ?: return null
    return cursor.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst() && nameIndex >= 0) it.getString(nameIndex) else null
    }
}

private fun openPdf(context: Context, uri: Uri) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }
}

private fun openUrl(context: Context, url: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}
