package algorithms.sorting

interface Sort {

    operator fun <T> invoke(target: List<T>): List<T> where T : Comparable<T>
}