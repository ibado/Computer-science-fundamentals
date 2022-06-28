package algorithms.sorting

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BubbleSortTest {

    @Test
    fun test() {
        val bubbleSort = BubbleSort()

        val target = listOf(2, 6, 23, 666, 0, 35)

        val result = bubbleSort(target)

        val expected = listOf(0, 2, 6, 23, 35, 666)

        assertEquals(expected, result)
    }
}