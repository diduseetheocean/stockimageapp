package io.github.diduseetheocean.stockimageapp

import java.io.InputStreamReader

object TestUtils {
    fun loadJsonFile(path: String): String {
        val resourceAsStream = javaClass.classLoader?.getResourceAsStream(path)
        val reader = InputStreamReader(resourceAsStream)
        return reader.use { it.readText() }
    }
}