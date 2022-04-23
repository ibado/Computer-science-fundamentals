package problems
class EncodeString {

    fun encodeString(input: String): String {
        if (input.isEmpty()) return ""

        val buffer = StringBuilder()
        var count = 1

        input.forEachIndexed { index, char ->
            if (index == 0) return@forEachIndexed

            val previous = input[index - 1]

            if (char == previous) count += 1
            else {
                buffer.append("$count$previous")
                count = 1
            }

            if (index == input.length - 1) buffer.append("$count$char")
        }

        return buffer.toString()
    }

    fun decodeString(encodedInput: String): String {
        TODO()
    }
}