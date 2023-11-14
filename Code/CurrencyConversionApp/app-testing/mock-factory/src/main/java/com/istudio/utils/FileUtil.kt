package com.istudio.utils

object FileUtil {

    fun kotlinReadFileWithNewLineFromResources(fileName: String): String {
        return getInputStreamFromResource(fileName)?.bufferedReader()
            .use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    private fun getInputStreamFromResource(fileName: String)
            = javaClass.classLoader?.getResourceAsStream(fileName)
}