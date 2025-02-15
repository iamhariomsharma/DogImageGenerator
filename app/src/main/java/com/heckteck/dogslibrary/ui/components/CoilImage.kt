package com.heckteck.dogslibrary.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.heckteck.dogslibrary.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    model: Any?,
    shape: Shape = RectangleShape,
    placeHolder: Int = R.drawable.ic_image_placeholder,
    error: Int? = R.drawable.ic_image_error,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        model = model,
        contentDescription = null,
        placeholder = painterResource(id = placeHolder),
        modifier = modifier.clip(shape),
        error = error?.let { painterResource(id = it) },
        contentScale = contentScale,
    )
}
