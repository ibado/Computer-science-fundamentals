package algorithms.sorting

fun <T : Comparable<T>> MutableList<T>.swap(aIndex: Int, bIndex: Int) {
    val a = this[aIndex]
    val b = this[bIndex]
    this[aIndex] = b
    this[bIndex] = a
}