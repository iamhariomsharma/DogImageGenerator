package com.heckteck.dogslibrary.ui.dogs_library

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.heckteck.dogslibrary.R
import com.heckteck.dogslibrary.ui.components.CoilImage
import com.heckteck.dogslibrary.ui.components.CommonAppBar
import com.heckteck.dogslibrary.ui.components.PrimaryButton
import com.heckteck.dogslibrary.ui.theme.DogsLibraryTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogLibraryScreenRoot(
    onNavigateBack: () -> Unit,
    viewModel: DogLibraryViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CommonAppBar(
                title = stringResource(id = R.string.recently_generated_dogs),
                onBackPress = onNavigateBack
            )
        }
    ) { innerPadding ->
        DogLibraryScreen(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun DogLibraryScreen(
    state: DogLibraryState,
    onAction: (DogLibraryAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val containerHeight = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        300.dp
    } else 200.dp
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(containerHeight),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.dogsImages) { dogImage ->
                CoilImage(
                    model = dogImage.url,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(containerHeight)
                )
            }
        }

        PrimaryButton(
            label = stringResource(id = R.string.clear_dogs),
            onClick = {
                onAction(DogLibraryAction.OnClearAllDogImages)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DogLibraryScreenPreview() {
    DogsLibraryTheme {
        DogLibraryScreen(
            state = DogLibraryState(),
            onAction = {}
        )
    }
}