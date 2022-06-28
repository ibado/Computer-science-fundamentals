package algorithms.sorting

class BubbleSort : Sort {

    override fun <T : Comparable<T>> invoke(target: List<T>): List<T> {
        val list = target.toMutableList()
        while (true) {
            var swapped = false
            list.forEachIndexed { i, element ->
                if (i == list.size - 1) return@forEachIndexed
                val next = list[i + 1]
                if (element > next) {
                    list.swap(i, i + 1)
                    swapped = true
                }
            }
            if (!swapped) return list
        }
    }
}
