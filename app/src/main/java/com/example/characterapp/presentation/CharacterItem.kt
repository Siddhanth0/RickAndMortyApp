package com.example.characterapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.characterapp.domain.Character
import com.example.characterapp.ui.theme.BeerAppTheme

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(4.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(IntrinsicSize.Max)
            .padding(16.dp)
        ) {
            AsyncImage(model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .weight(1f),
                alignment = Alignment.Center)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(3f).fillMaxHeight(), verticalArrangement = Arrangement.Center){
                Text(
                    text = character.name,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = character.status,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = character.species,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    BeerAppTheme {
        CharacterItem(
            character = Character(
                id = 1,
                name = "Beer",
                species = "This is a cool beer",
                status = "07/2023",
                imageUrl = "null"
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}