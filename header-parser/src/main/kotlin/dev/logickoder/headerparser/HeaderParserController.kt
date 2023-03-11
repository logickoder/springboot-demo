package dev.logickoder.headerparser

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
object HeaderParserController {

    @GetMapping("/api/whoami")
    fun whoAmI(request: ServerHttpRequest): Map<String, String?> {
        return mapOf(
            "ipaddress" to request.remoteAddress?.toString(),
            "language" to request.headers["accept-language"]?.firstOrNull(),
            "software" to request.headers["user-agent"]?.firstOrNull(),
        )
    }
}