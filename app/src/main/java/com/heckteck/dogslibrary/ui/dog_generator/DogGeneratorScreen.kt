package com.heckteck.dogslibrary.ui.dog_generator

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heckteck.dogslibrary.R
import com.heckteck.dogslibrary.ui.components.CoilImage
import com.heckteck.dogslibrary.ui.components.CommonAppBar
import com.heckteck.dogslibrary.ui.components.PrimaryButton
import com.heckteck.dogslibrary.ui.theme.DogsLibraryTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogGeneratorScreenRoot(
    onNavigateBack: () -> Unit,
    viewModel: DogGeneratorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonAppBar(
                title = stringResource(id = R.string.generate_dogs),
                onBackPress = onNavigateBack
            )
        }
    ) { innerPadding ->
        DogGeneratorScreen(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun DogGeneratorScreen(
    state: DogGeneratorState,
    onAction: (DogGeneratorAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val orientation = LocalConfiguration.current.orientation
    val containerHeight = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        300.dp
    } else 200.dp

    val imageTopPadding = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        (screenHeightDp * 0.15).dp
    } else (screenHeightDp * 0.1).dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = imageTopPadding)
                .height(containerHeight)
        ) {
            if (state.imageUrl != null) {
                CoilImage(
                    model = state.imageUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(containerHeight)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryButton(
                label = stringResource(id = R.string.generate),
                isLoading = state.isGenerating,
                enabled = !state.isGenerating,
                onClick = {
                    onAction(DogGeneratorAction.OnGenerateDogImage)
                }
            )

            val errorMessage = state.errorMessage
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DogGeneratorScreenPreview() {
    DogsLibraryTheme {
        DogGeneratorScreen(
            state = DogGeneratorState(),
            onAction = {}
        )
    }
}