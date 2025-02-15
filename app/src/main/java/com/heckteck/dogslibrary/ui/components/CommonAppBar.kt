@file:OptIn(ExperimentalMaterial3Api::class)

package com.heckteck.dogslibrary.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heckteck.dogslibrary.R
import com.heckteck.dogslibrary.ui.theme.DogsLibraryTheme

@Composable
fun CommonAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackPress: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPress,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(id = R.string.back_icon)
                )
            }
        },
        modifier = modifier.shadow(elevation = 4.dp)
    )
}

@Preview
@Composable
private fun CommonAppBarPreview() {
    DogsLibraryTheme {
        CommonAppBar(
            title = "App Bar Preview",
            onBackPress = {}
        )
    }
}