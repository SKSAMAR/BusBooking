package com.samar.busbooking.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.util.common.ConnectionLiveData
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun<T> BaseScaffold(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable ()->Unit = {},
    parentPadding: Dp = 0.dp,
    connectionLiveData: ConnectionLiveData = ConnectionLiveData(context),
    state: State<ScreenState<T>>,
    content: @Composable ColumnScope.() -> Unit,
){

    val isNetworkAvailable = connectionLiveData.observeAsState( false)
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(parentPadding)
                    .weight(1f),
                content = {
                    BasicScreenState(state = state, modifier = modifier,  content = content)
                }
            )
            AnimatedVisibility(
                visible = !isNetworkAvailable.value,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.sdp),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.sdp),
                        text = "No Internet Available",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.textSdp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}