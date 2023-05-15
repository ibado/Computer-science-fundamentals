package datastructures

import kotlin.math.abs

class HashMap<K, V> : MutableMap<K, V> {

    companion object {
        private const val DEFAULT_SIZE = 10
    }

    private var storage: Array<LinkedList<Entry<K, V>>> =
        Array(DEFAULT_SIZE) { LinkedList() }

    private fun hash(key: K): Int {
        return abs(key.hashCode() % (storage.size - 1))
    }

    private data class Entry<K, V> (
        override val key: K,
        override var value: V
    ) : MutableMap.MutableEntry<K, V> {

        /**
         * Changes the value associated with the key of this entry.
         *
         * @return the previous value corresponding to the key.
         */
        override fun setValue(newValue: V): V {
            val previous = value
            value = newValue
            return previous
        }
    }

    /**
     * Associates the specified [value] with the specified [key] in the map.
     *
     * @return the previous value associated with the key, or `null` if the key was not present in the map.
     */
    override fun put(key: K, value: V): V? {
        if (size > storage.size - 2) {
            resizeStorage()
        }
        var previous: V? = null
        val index = hash(key)
        storage[index]
            .find { it.key == key }
            ?.run { previous = this.setValue(value) }
            ?: storage[index].add(Entry(key, value)).also { size++ }

        return previous
    }

    private fun resizeStorage() {
        val oldStorage = storage
        storage = Array(oldStorage.size * 2) { LinkedList() }
        for (bucket in oldStorage) {
            for (entry in bucket) {
                storage[hash(entry.key)].add(entry)
            }
        }
    }

    /**
     * Returns a [MutableSet] of all key/value pairs in this map.
     */
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = storage.flatMapTo(mutableSetOf()) { it }

    /**
     * Returns a [MutableSet] of all keys in this map.
     */
    override val keys: MutableSet<K>
        get() = storage.flatMapTo(mutableSetOf()) {
            it.map { entries -> entries.key }
        }

    /**
     * Returns the number of key/value pairs in the map.
     */
    override var size: Int = 0
        private set

    /**
     * Returns a [MutableCollection] of all values in this map. Note that this collection may contain duplicate values.
     */
    @Suppress("UNCHECKED_CAST")
    override val values: MutableCollection<V>
        get() = storage.flatMap {
            it.map { entries -> entries.value }
        } as MutableCollection<V>

    /**
     * Removes all elements from this map.
     */
    override fun clear() {
        storage = Array(DEFAULT_SIZE) { LinkedList() }
        size = 0
    }

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean {
        return size == 0
    }

    /**
     * Removes the specified key and its corresponding value from this map.
     *
     * @return the previous value associated with the key, or `null` if the key was not present in the map.
     */
    override fun remove(key: K): V? {
        return storage[hash(key)].let { bucket ->
            bucket.find { it.key == key }
                ?.let {
                    bucket.remove(it)
                    size--
                    it.value
                }
        }
    }

    /**
     * Updates this map with key/value pairs from the specified map [from].
     */
    override fun putAll(from: Map<out K, V>) {
        for (e in from) put(e.key, e.value)
    }

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     */
    override fun get(key: K): V? =
        storage[hash(key)]
            .find { it.key == key }
            ?.value

    /**
     * Returns `true` if the map maps one or more keys to the specified [value].
     */
    override fun containsValue(value: V): Boolean {
        for (bucket in storage) {
            if (bucket.any { it.value == value }) return true
        }

        return false
    }

    /**
     * Returns `true` if the map contains the specified [key].
     */
    override fun containsKey(key: K): Boolean {
        return storage[hash(key)].any { it.key == key }
    }
}