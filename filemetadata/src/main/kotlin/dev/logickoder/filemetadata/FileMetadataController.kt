package dev.logickoder.filemetadata

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@RestController
object FileMetadataController {

    @PostMapping("/api/fileanalyse")
    fun analyzeFile(
            @RequestParam("upfile") file: MultipartFile,
            redirectAttributes: RedirectAttributes
    ): FileMetadata {
        return FileMetadata(
            name = file.originalFilename,
            type = file.contentType,
            size = file.size,
        )
    }
}