package algorithms.sorting

class BubbleSort : Sort {

    override fun <T : Comparable<T>> invoke(target: List<T>): List<T> {
        val list = target.toMutableList()
        while (true) {
            var swapped = false
            for (i in 0 until (list.size - 1)) {
                val current = list[i]
                val next = list[i + 1]
                if (current > next) {
                    list.swap(i, i + 1)
                    swapped = true
                }
            }
            if (!swapped) return list
        }
    }
}
