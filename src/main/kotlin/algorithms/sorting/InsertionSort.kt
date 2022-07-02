package algorithms.sorting

class InsertionSort : Sort {

    override fun <T : Comparable<T>> invoke(target: List<T>): List<T> {
        val list = target.toMutableList()

        list.forEachIndexed { index, current ->
            var j = index - 1
            var i = index
            while (j >= 0) {
                if (list[j] > current) {
                    list.swap(j, i)
                    i = j
                }
                j--
            }
        }

        return list
    }
}