package com.samar.busbooking.presentation.response

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.samar.busbooking.R
import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.presentation.common.ScreenAnimation
import com.samar.busbooking.presentation.home.BusHomeActivity
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import com.samar.busbooking.util.common.Constant


class ResponseActivity : ComponentActivity() {

    var response: BookingResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        response = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Constant.Response, BookingResponse::class.java)
        } else {
            intent.getSerializableExtra(Constant.Response) as BookingResponse
        }
        setContent {
            BusBookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenContent()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ScreenContent() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Response")
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ScreenAnimation(
                    modifier = Modifier.aspectRatio(1f),
                    resources = R.raw.success
                )
                response?.data?.let { it ->
                    it.blockKey?.let {
                        Text(text = "Block Key: " + it)
                    }
                    it.refid?.let {
                        Text(text = "Reference Id: " + it)
                    }
                    it.utr?.let {
                        Text(text = "UTR: " + it)
                    }
                    it.ackno?.let {
                        Text(text = "Ack no: " + it)
                    }
                }
            }
        }
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        startActivity(Intent(this@ResponseActivity, BusHomeActivity::class.java))
        finish()
        return super.getOnBackInvokedDispatcher()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ResponseActivity, BusHomeActivity::class.java))
        finish()
    }
}