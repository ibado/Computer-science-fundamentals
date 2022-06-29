package datastructures

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LinkedListTest {

    private lateinit var linkedList: LinkedList<Int>

    @BeforeEach
    fun setUp() {
        linkedList = LinkedList()
    }

    @Test
    fun `add should add`() {
        linkedList.add(1)
        linkedList.add(2)
        linkedList.add(3)

        assertEquals("[1 -> 2 -> 3]", linkedList.toString())
    }

    @Test
    fun `removeLast should remove the last`() {
        linkedList.add(1)
        linkedList.add(2)
        linkedList.add(3)
        linkedList.removeLast()

        assertEquals("[1 -> 2]", linkedList.toString())
    }

    @Test
    fun `reverse should reverse a 2 element list`() {
        linkedList.add(1)
        linkedList.add(2)

        linkedList.reverse()

        assertEquals("[2 -> 1]", linkedList.toString())
    }

    @Test
    fun `reverse should reverse a 4 element list`() {
        linkedList.add(1)
        linkedList.add(2)
        linkedList.add(3)
        linkedList.add(4)

        linkedList.reverse()

        assertEquals("[4 -> 3 -> 2 -> 1]", linkedList.toString())
    }
}