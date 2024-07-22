package cl.test.supertienda.tools

import kotlin.random.Random

/**
 * Class for testing utilities
 */
object Utilities {
    /**
     * [Char] [List] used for generating random strings
     */
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    /**
     * Generates a random string for testing
     * @param length Required length for generated string. If not provided, a string with length of
     * 100 wil be generated.
     * @return Randomly generated string
     */
    fun randomString(length: Int = 100) = (1..length)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}