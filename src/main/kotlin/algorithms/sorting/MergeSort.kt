package algorithms.sorting

class MergeSort : Sort {

    override fun <T : Comparable<T>> invoke(target: List<T>): List<T> {
        val list = target.toMutableList()

        if (list.size == 1) return list

        val mid = list.size / 2
        val left = this(list.subList(0, mid))
        val right = this(list.subList(mid, list.size))

        return merge(left, right)
    }

    private fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
        val l1 = left.toMutableList()
        val l2 = right.toMutableList()
        val result = mutableListOf<T>()
        while (l1.isNotEmpty() || l2.isNotEmpty()) {
            val l1Element = l1.firstOrNull()
            val l2Element = l2.firstOrNull()
            when {
                l1Element == null -> l2.moveElementTo(l2Element!!, result)
                l2Element == null -> l1.moveElementTo(l1Element, result)
                l1Element < l2Element -> l1.moveElementTo(l1Element, result)
                else -> l2.moveElementTo(l2Element, result)
            }
        }
        return result
    }

    private fun <T> MutableList<T>.moveElementTo(element: T, to: MutableList<T>) {
        this.remove(element)
        to.add(element)
    }
}