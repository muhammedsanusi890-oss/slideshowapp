package com.example.slideshowapp.data

import com.example.slideshowapp.R
import com.example.slideshowapp.model.ImageItem

/**
 * Repository providing a collection of at least ten [ImageItem] entries.
 */
object ImageRepository {
    fun getImageItems(): List<ImageItem> {
        return listOf(
            ImageItem(
                id = 1,
                imageResId = R.drawable.img_mountain,
                title = "Majestic Mountain Peak",
                description = "A snow-capped mountain peak towering high under a clear, bright sunny sky.",
            ),
            ImageItem(
                id = 2,
                imageResId = R.drawable.img_forest,
                title = "Peaceful Pine Forest",
                description = "Tall green pine trees bathed in warm morning sunlight along a serene trail.",
            ),
            ImageItem(
                id = 3,
                imageResId = R.drawable.img_ocean,
                title = "Golden Sunset Over Ocean",
                description = "Calm ocean waves glistening under a vibrant golden sunset on the horizon.",
            ),
            ImageItem(
                id = 4,
                imageResId = R.drawable.img_night,
                title = "Starry Night Sky",
                description = "A glowing crescent moon illuminating a deep blue sky filled with twinkling stars.",
            ),
            ImageItem(
                id = 5,
                imageResId = R.drawable.img_city,
                title = "Illuminated City Skyline",
                description = "City skyscrapers with brightly lit windows during a beautiful twilight dusk.",
            ),
            ImageItem(
                id = 6,
                imageResId = R.drawable.img_desert,
                title = "Golden Desert Dunes",
                description = "Rolling sand dunes glowing under a warm, radiant desert afternoon sun.",
            ),
            ImageItem(
                id = 7,
                imageResId = R.drawable.img_waterfall,
                title = "Cascading Waterfall Oasis",
                description = "A refreshing waterfall rushing into a clear turquoise basin surrounded by canyon rocks.",
            ),
            ImageItem(
                id = 8,
                imageResId = R.drawable.img_autumn,
                title = "Autumn Woodland Path",
                description = "A quiet forest path blanketed in vibrant orange and golden autumn leaves.",
            ),
            ImageItem(
                id = 9,
                imageResId = R.drawable.img_aurora,
                title = "Northern Lights Aurora",
                description = "Shimmering green and teal aurora borealis waves dancing over arctic mountains.",
            ),
            ImageItem(
                id = 10,
                imageResId = R.drawable.img_island,
                title = "Tropical Island Paradise",
                description = "Pristine sandy beach with swaying palm trees next to crystal clear waters.",
            ),
        )
    }
}
