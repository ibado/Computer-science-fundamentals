package datastructures

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HashMapTest {

    private lateinit var map: HashMap<String, String>

    @BeforeEach
    fun setUp() {
        map = HashMap()
    }

    @Test
    fun `add a value works`() {
        map["key"] = "value"

        assertEquals("value", map["key"])
    }

    @Test
    fun `add another map works`() {
        map["key"] = "value"
        map.putAll(mapOf("key2" to "value2", "key3" to "value3"))

        assertEquals("value", map["key"])
        assertEquals("value2", map["key2"])
        assertEquals("value3", map["key3"])
        assertEquals(3, map.size)
    }

    @Test
    fun `size should work`() {
        map["key1"] = "str1"
        map["key2"] = "str2"
        map["key3"] = "str3"
        map["key4"] = "str4"
        map["key5"] = "str5"
        map["key6"] = "str6"
        map["key7"] = "str7"
        map["key8"] = "str8"
        map["key9"] = "str9"
        map["key10"] = "str10"
        map["key11"] = "str11"
        map.remove("key11")

        assertEquals(10, map.size)
    }

    @Test
    fun `clear should set size to 0`() {
        map["key1"] = "str1"
        map["key2"] = "str2"
        map["key3"] = "str3"
        map["key4"] = "str4"
        map["key5"] = "str5"
        map["key6"] = "str6"
        map["key7"] = "str7"

        map.clear()

        assertEquals(0, map.size)
    }

    @Test
    fun `get values should work`() {
        map["key1"] = "str1"
        map["key2"] = "str2"
        map["key3"] = "str3"

        assertEquals(listOf("str1", "str2", "str3"), map.values)
    }

    @Test
    fun `get keys should work`() {
        map["key1"] = "str1"
        map["key2"] = "str2"
        map["key3"] = "str3"

        assertEquals(setOf("key1", "key2", "key3"), map.keys)
    }

    @Test
    fun `get entries should work`() {
        map["key1"] = "str1"
        map["key2"] = "str2"
        map["key3"] = "str3"

        val expected = listOf(
            "key1" to "str1",
            "key2" to "str2",
            "key3" to "str3"
        )
        val actual = map.entries.map { it.key to it.value }

        assertEquals(expected, actual)
    }
}