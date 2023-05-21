package datastructures

import kotlin.math.abs

class HashMap<K, V> : MutableMap<K, V> {

    companion object {
        private const val DEFAULT_SIZE = 10
    }

    private var storage: Array<LinkedList<Entry<K, V>>> = newStorage(DEFAULT_SIZE)

    private fun hash(key: K): Int {
        return abs(key.hashCode() % (storage.size - 1))
    }

    private data class Entry<K, V> (
        override val key: K,
        override var value: V
    ) : MutableMap.MutableEntry<K, V> {

        override fun setValue(newValue: V): V {
            val previous = value
            value = newValue
            return previous
        }
    }

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
        storage = newStorage(oldStorage.size * 2)
        for (bucket in oldStorage) {
            for (entry in bucket) {
                storage[hash(entry.key)].add(entry)
            }
        }
    }

    private fun newStorage(size: Int): Array<LinkedList<Entry<K, V>>> = Array(size) { LinkedList() }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = storage.flatMapTo(mutableSetOf()) { it }

    override val keys: MutableSet<K>
        get() = storage.flatMapTo(mutableSetOf()) {
            it.map { entries -> entries.key }
        }

    override var size: Int = 0
        private set


    @Suppress("UNCHECKED_CAST")
    override val values: MutableCollection<V>
        get() = storage.flatMap {
            it.map { entries -> entries.value }
        } as MutableCollection<V>

    override fun clear() {
        storage = newStorage(DEFAULT_SIZE)
        size = 0
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

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

    override fun putAll(from: Map<out K, V>) {
        for (e in from) put(e.key, e.value)
    }

    override fun get(key: K): V? =
        storage[hash(key)]
            .find { it.key == key }
            ?.value

    override fun containsValue(value: V): Boolean {
        for (bucket in storage) {
            if (bucket.any { it.value == value }) return true
        }

        return false
    }

    override fun containsKey(key: K): Boolean {
        return storage[hash(key)].any { it.key == key }
    }
}