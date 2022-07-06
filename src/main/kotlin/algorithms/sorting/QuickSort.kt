package algorithms.sorting

class QuickSort : Sort {
    override fun <T : Comparable<T>> invoke(target: List<T>): List<T> {

        if (target.isEmpty()) return emptyList()

        val pivot = target.last()
        val left = mutableListOf<T>()
        val right = mutableListOf<T>()

        for (i in 0 until target.size - 1) {
            val current = target[i]
            if (current > pivot) {
                right.add(current)
            } else {
                left.add(current)
            }
        }

        return invoke(left) + pivot + invoke(right)
    }
}