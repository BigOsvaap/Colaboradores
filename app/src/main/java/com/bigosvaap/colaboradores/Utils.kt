package com.bigosvaap.colaboradores

import java.io.File
import java.util.zip.ZipFile

object Utils {

     fun unZipFile(filePath: String, fileName: String) {
        ZipFile("$filePath/$fileName").use { zip ->
            zip.entries().asSequence().forEach { entry ->
                zip.getInputStream(entry).use { input ->
                    File("$filePath/${entry.name}").outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }
        }
    }

}