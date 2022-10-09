package com.samar.busbooking.presentation.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.common.BaseScaffold

@Composable
fun HomeScreen(
     viewModel: HomeViewModel = viewModel()
){
     BaseScaffold(state = viewModel.state){

     }
}