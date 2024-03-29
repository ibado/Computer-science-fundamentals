package datastructures

class LinkedList<T> : AbstractList<T>() {

    private var head: Node<T>? = null

    override var size = 0
    private set

    override fun get(index: Int): T {
        var curr = head
        for (element in 0 until index) curr = curr?.next
        return curr?.value ?: throw IndexOutOfBoundsException()
    }

    fun add(newValue: T) {
        val newNode = Node(newValue)
        if (head == null) head = newNode
        else {
            var current = head
            while (current?.next != null) current = current.next
            current!!.next = newNode
        }
        size++
    }

    fun remove(element: T): Boolean {
        var prev: Node<T>? = null
        var curr = head
        while (curr != null) {
            if (curr.value == element) {
                if (prev == null) head = curr.next
                else prev.next = curr.next
                size--
                return true
            } else {
                prev = curr
                curr = curr.next
            }
        }

        return false
    }

    fun reverse() {
        if (head == null || head?.next == null) return
        var p1 = head
        var p2 = head?.next
        var p3 = p2?.next

        do {
            p2?.next = p1
            if (p1!!.next == p2) p1.next = null

            p1 = p2
            p2 = p3
            p3 = p3?.next
        } while (p2 != null)

        head = p1
    }

    fun removeLast() {
        if (head == null) return
        var previous = head
        var current = head?.next
        if (current == null) {
            head = null
        } else {
            while(current?.next != null) {
                previous = current
                current = current.next
            }
            previous!!.next = null
        }
        size--
    }

    override fun toString(): String {
        if (head == null) return "[]"
        var current = head
        val result = StringBuilder("[")
        while(current?.next != null) {
            result.append("${current.value} -> ")
            current = current.next
        }
        result.append("${current!!.value}]")
        return result.toString()
    }

    private class Node<T>(var value: T, var next: Node<T>? = null)
}
