package com.example.slideshowapp.model

import androidx.annotation.DrawableRes

/**
 * Data class representing an item in the image gallery list.
 *
 * @property id Unique identifier for the item.
 * @property imageResId Drawable resource identifier for the image.
 * @property title Short descriptive title for the image.
 * @property description Detailed description of the image content.
 */
data class ImageItem(
    val id: Int,
    @param:DrawableRes val imageResId: Int,
    val title: String,
    val description: String,
)
