package com.heckteck.dogslibrary.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heckteck.dogslibrary.R
import com.heckteck.dogslibrary.ui.components.PrimaryButton
import com.heckteck.dogslibrary.ui.theme.DogsLibraryTheme

@Composable
fun HomeScreen(
    onClickGenerate: () -> Unit,
    onClickLibrary: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.random_dog_generator),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            PrimaryButton(
                label = stringResource(id = R.string.generate_dogs),
                onClick = onClickGenerate
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                label = stringResource(id = R.string.recently_generated_dogs),
                onClick = onClickLibrary
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DogsLibraryTheme {
        HomeScreen(
            onClickGenerate = {},
            onClickLibrary = {}
        )
    }
}