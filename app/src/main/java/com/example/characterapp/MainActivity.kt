package com.example.characterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.characterapp.presentation.CharacterScreen
import com.example.characterapp.presentation.CharacterViewModel
import com.example.characterapp.ui.theme.BeerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerAppTheme {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    val viewModel = hiltViewModel<CharacterViewModel>()
                    val characters = viewModel.characterPagingFlow.collectAsLazyPagingItems()
                    CharacterScreen(characters = characters)
                }
            }
        }
    }
}
