package dev.logickoder.filemetadata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FileMetadataApplication

fun main(args: Array<String>) {
	runApplication<FileMetadataApplication>(*args)
}
