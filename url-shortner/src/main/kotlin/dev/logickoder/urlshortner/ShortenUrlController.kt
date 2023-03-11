package dev.logickoder.urlshortner

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URL
import java.util.*


@RestController
@RequestMapping("/api/shorturl")
class ShortenUrlController(private val repository: UrlRepository) {

    private fun isValidURL(url: String): Boolean = runCatching {
        URL(url).toURI()
        true
    }.getOrDefault(false)

    @GetMapping("/{shortUrl}")
    fun getUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        return when (val url = repository.findUrlByShort(shortUrl)?.original) {
            null -> ResponseEntity(null, HttpStatus.NOT_FOUND)
            else -> ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, url).build()
        }
    }

    @PostMapping
    fun shortenUrl(body: UrlForm): ResponseEntity<Url> {
        if (!isValidURL(body.url)) return ResponseEntity(null, HttpStatus.BAD_REQUEST)

        var url = repository.findByIdOrNull(body.url)

        val status = if (url == null) HttpStatus.CREATED else HttpStatus.OK
        if (url == null) {
            url = Url(original = body.url, short = UUID.randomUUID().toString())
            repository.save(url)
        }

        return ResponseEntity(url, status)
    }

    data class UrlForm(val url: String)
}