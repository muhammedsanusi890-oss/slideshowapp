package com.example.slideshowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshowapp.ui.theme.SlideshowAppTheme

/**
 * Data class representing a slide with an image resource, a title, and a descriptive caption.
 */
data class SlideItem(
    val id: Int,
    @param:DrawableRes val imageResId: Int,
    val title: String,
    val caption: String,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowAppTheme {
                SlideshowApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlideshowApp() {
    // List of 5 slideshow images with captions
    val slides = remember {
        listOf(
            SlideItem(
                id = 1,
                imageResId = R.drawable.img_mountain,
                title = "Majestic Mountain Peak",
                caption = "1. A snow-capped mountain peak towering high under a clear, bright sunny sky.",
            ),
            SlideItem(
                id = 2,
                imageResId = R.drawable.img_forest,
                title = "Peaceful Pine Forest",
                caption = "2. Tall green pine trees bathed in warm morning sunlight along a serene trail.",
            ),
            SlideItem(
                id = 3,
                imageResId = R.drawable.img_ocean,
                title = "Golden Sunset Over Ocean",
                caption = "3. Calm ocean waves glistening under a vibrant golden sunset on the horizon.",
            ),
            SlideItem(
                id = 4,
                imageResId = R.drawable.img_night,
                title = "Starry Night Sky",
                caption = "4. A glowing crescent moon illuminating a deep blue sky filled with twinkling stars.",
            ),
            SlideItem(
                id = 5,
                imageResId = R.drawable.img_city,
                title = "Illuminated City Skyline",
                caption = "5. City skyscrapers with brightly lit windows during a beautiful twilight dusk.",
            ),
        )
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var inputNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current

    val currentSlide = slides[currentIndex]

    fun goToNext() {
        currentIndex = (currentIndex + 1) % slides.size
        errorMessage = null
    }

    fun goToPrevious() {
        currentIndex = if ((currentIndex - 1) < 0) (slides.size - 1) else (currentIndex - 1)
        errorMessage = null
    }

    fun jumpToImage() {
        focusManager.clearFocus()
        val parsedNumber = inputNumber.trim().toIntOrNull()
        if ((parsedNumber != null) && (parsedNumber >= 1) && (parsedNumber <= slides.size)) {
            currentIndex = parsedNumber - 1
            errorMessage = null
            inputNumber = ""
        } else {
            errorMessage = "Please enter a valid picture number between 1 and ${slides.size}"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Image Slideshow",
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Slide Counter Badge
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ) {
                Text(
                    text = "Image ${currentIndex + 1} of ${slides.size}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            // Image Card with Crossfade transition
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Crossfade(
                        targetState = currentSlide,
                        label = "SlideTransition",
                    ) { slide ->
                        Image(
                            painter = painterResource(id = slide.imageResId),
                            contentDescription = slide.title,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }

            // Caption Box
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = currentSlide.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = currentSlide.caption,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            // Previous / Next Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedButton(
                    onClick = { goToPrevious() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = "◄ Back",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { goToNext() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = "Next ►",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            // Jump to Specific Image Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Jump to Picture",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedTextField(
                            value = inputNumber,
                            onValueChange = {
                                inputNumber = it
                                errorMessage = null
                            },
                            label = { Text("Image # (1-${slides.size})") },
                            placeholder = { Text("e.g. 3") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Go,
                            ),
                            keyboardActions = KeyboardActions(
                                onGo = { jumpToImage() },
                            ),
                            isError = errorMessage != null,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { jumpToImage() },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(56.dp),
                        ) {
                            Text(
                                text = "Go",
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }

                    if (errorMessage != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowAppPreview() {
    SlideshowAppTheme {
        SlideshowApp()
    }
}
