package com.heckteck.dogslibrary.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.heckteck.dogslibrary.R
import com.heckteck.dogslibrary.ui.components.PrimaryButton

@Composable
fun HomeScreen(
    onClickGenerate: () -> Unit,
    onClickLibrary: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(id = R.string.random_dog_generator),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        PrimaryButton(
            label = stringResource(id = R.string.generate_dogs),
            onClick = onClickGenerate
        )

        PrimaryButton(
            label = stringResource(id = R.string.recently_generated_dogs),
            onClick = onClickLibrary
        )
    }
}