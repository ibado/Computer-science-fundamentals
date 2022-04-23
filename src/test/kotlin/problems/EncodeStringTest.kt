package problems

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EncodeStringTest {

    private lateinit var encodeString: EncodeString

    @BeforeEach
    fun setUp() {
        encodeString = EncodeString()
    }

    @Test
    fun `encode string aabbcc`() {
        val actualOutput = encodeString.encodeString("aabbcc")
        assertEquals("2a2b2c", actualOutput)
    }

    @Test
    fun `encode string aabbcca`() {
        val actualOutput = encodeString.encodeString("aabbcca")
        assertEquals("2a2b2c1a", actualOutput)
    }
}