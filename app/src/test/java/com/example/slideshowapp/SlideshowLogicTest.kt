package com.example.slideshowapp

import org.junit.Assert.assertEquals
import org.junit.Test

class SlideshowLogicTest {

    private val totalSlides = 5

    private fun getNextIndex(current: Int, total: Int): Int {
        return (current + 1) % total
    }

    private fun getPreviousIndex(current: Int, total: Int): Int {
        return if ((current - 1) < 0) (total - 1) else (current - 1)
    }

    private fun parseJumpIndex(input: String, total: Int): Int? {
        val parsed = input.trim().toIntOrNull()
        return if ((parsed != null) && (parsed >= 1) && (parsed <= total)) {
            parsed - 1
        } else {
            null
        }
    }

    @Test
    fun testNextNavigation() {
        assertEquals(1, getNextIndex(0, totalSlides))
        assertEquals(2, getNextIndex(1, totalSlides))
        assertEquals(3, getNextIndex(2, totalSlides))
        assertEquals(4, getNextIndex(3, totalSlides))
        // Wraps from last (4) to first (0)
        assertEquals(0, getNextIndex(4, totalSlides))
    }

    @Test
    fun testPreviousNavigation() {
        // Wraps from first (0) to last (4)
        assertEquals(4, getPreviousIndex(0, totalSlides))
        assertEquals(0, getPreviousIndex(1, totalSlides))
        assertEquals(1, getPreviousIndex(2, totalSlides))
        assertEquals(2, getPreviousIndex(3, totalSlides))
        assertEquals(3, getPreviousIndex(4, totalSlides))
    }

    @Test
    fun testValidJumpToImage() {
        assertEquals(0, parseJumpIndex("1", totalSlides))
        assertEquals(2, parseJumpIndex("3", totalSlides))
        assertEquals(4, parseJumpIndex("5", totalSlides))
    }

    @Test
    fun testInvalidJumpToImage() {
        assertEquals(null, parseJumpIndex("0", totalSlides))
        assertEquals(null, parseJumpIndex("6", totalSlides))
        assertEquals(null, parseJumpIndex("-1", totalSlides))
        assertEquals(null, parseJumpIndex("abc", totalSlides))
        assertEquals(null, parseJumpIndex("", totalSlides))
    }
}
