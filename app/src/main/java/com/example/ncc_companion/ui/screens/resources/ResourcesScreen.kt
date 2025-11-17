package com.example.ncc_companion.ui.screens.resources

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val pdfPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                )
            }
            val name = queryDisplayName(context.contentResolver, it) ?: "Selected PDF"
            onPdfUploaded(name, it.toString())
        }
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Button(onClick = { pdfPickerLauncher.launch(arrayOf("application/pdf")) }) {
                Icon(imageVector = Icons.Default.Description, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Upload local PDF")
            }
        }
        if (uploadedPdfs.isNotEmpty()) {
            item {
                Text(text = "My Library", style = MaterialTheme.typography.titleMedium)
            }
            items(uploadedPdfs) { pdf ->
                OutlinedCard(onClick = { openPdf(context, Uri.parse(pdf.uri)) }) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = pdf.name, style = MaterialTheme.typography.titleSmall)
                        Text(text = pdf.uri, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
        item {
            Text(text = "Reference PDFs", style = MaterialTheme.typography.titleMedium)
        }
        items(pdfResources) { pdf ->
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = pdf.title, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = pdf.description)
                }
            }
        }
        item {
            Text(text = "Helpful Links", style = MaterialTheme.typography.titleMedium)
        }
        items(resourceLinks) { link ->
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = link.title, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = link.description)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { openUrl(context, link.url) }) {
                        Text(text = "Open")
                    }
                }
            }
        }
    }
}

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
