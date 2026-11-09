package com.example.slideshowapp

import com.example.slideshowapp.data.ImageRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageRepositoryTest {

    @Test
    fun testRepositoryHasAtLeastTenItems() {
        val items = ImageRepository.getImageItems()
        assertTrue("Repository should have at least 10 items", items.size >= 10)
    }

    @Test
    fun testRepositoryDataIntegrity() {
        val items = ImageRepository.getImageItems()
        items.forEach { item ->
            assertTrue("Item ID should be positive", item.id > 0)
            assertTrue("Item title should not be blank", item.title.isNotBlank())
            assertTrue("Item description should not be blank", item.description.isNotBlank())
            assertTrue("Item image resource should be valid", item.imageResId != 0)
        }
    }

    @Test
    fun testUniqueItemIds() {
        val items = ImageRepository.getImageItems()
        val ids = items.map { it.id }
        assertEquals("All item IDs should be unique", ids.size, ids.toSet().size)
    }
}
