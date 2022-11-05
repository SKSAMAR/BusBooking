package com.samar.busbooking.presentation.available.component

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.available.AvailableViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableTopScreen(
    viewModel: AvailableViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "${viewModel.fromCityModel?.name} - ${viewModel.toCityModel?.name}",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${viewModel.date?.englishData} ${viewModel.date?.day}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    val activity = context as ComponentActivity
                    activity.onBackPressed()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.surface
        )
    )
}