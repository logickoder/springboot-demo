package dev.logickoder.urlshortner

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface UrlRepository : CrudRepository<Url, String> {
    @Query(value = "SELECT * FROM Url url WHERE url.short = :short LIMIT 1", nativeQuery = true)
    fun findUrlByShort(@Param("short") short: String): Url?
}