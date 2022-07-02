package algorithms.sorting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SortingTest {

    @Test
    fun bubbleSort() {
        testSort(BubbleSort())
    }

    @Test
    fun insertionSort() {
        testSort(InsertionSort())
    }

    @Test
    fun mergeSort() {
        testSort(MergeSort())
    }

    private fun testSort(sort: Sort) {
        val target = listOf(2, 6, 23, 666, 0, 35)

        val result = sort(target)

        val expected = listOf(0, 2, 6, 23, 35, 666)

        assertEquals(expected, result)
    }
}