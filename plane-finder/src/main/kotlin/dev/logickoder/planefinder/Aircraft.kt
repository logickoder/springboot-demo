package dev.logickoder.planefinder


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class Aircraft(
        @JsonProperty("altitude")
        val altitude: Int = 0,
        @JsonProperty("barometer")
        val barometer: Int = 0,
        @JsonProperty("callsign")
        val callSign: String = "",
        @JsonProperty("category")
        val category: String = "",
        @JsonProperty("flightno")
        val flightNo: String = "",
        @JsonProperty("heading")
        val heading: Int = 0,
        @JsonProperty("id")
        val id: Int = 0,
        @JsonProperty("is_adsb")
        val isAdsb: Boolean = false,
        @JsonProperty("is_on_ground")
        val isOnGround: Boolean = false,
        @JsonProperty("lat")
        val lat: Double = 0.0,
        @JsonProperty("lon")
        val lon: Double = 0.0,
        @JsonProperty("polar_bearing")
        val polarBearing: Double = 0.0,
        @JsonProperty("polar_distance")
        val polarDistance: Double = 0.0,
        @JsonProperty("reg")
        val reg: String = "",
        @JsonProperty("route")
        val route: String = "",
        @JsonProperty("selected_altitude")
        val selectedAltitude: Int = 0,
        @JsonProperty("speed")
        val speed: Int = 0,
        @JsonProperty("squawk")
        val squawk: String = "",
        @JsonProperty("type")
        val type: String = "",
        @JsonProperty("vert_rate")
        val vertRate: Int = 0
) {
    @JsonProperty("last_seen_time")
    var lastSeenTime: String? = null
        get() = _lastSeenTime.toString()
        set(value) {
            field = value
            _lastSeenTime = value?.let { Instant.parse(it) } ?: _lastSeenTime
        }
    private var _lastSeenTime: Instant = Instant.ofEpochSecond(0)

    @JsonProperty("pos_update_time")
    var posUpdateTime: String? = null
        get() = _posUpdateTime.toString()
        set(value) {
            field = value
            _posUpdateTime = value?.let { Instant.parse(it) } ?: _posUpdateTime
        }
    private var _posUpdateTime: Instant = Instant.ofEpochSecond(0)

    @JsonProperty("bds40_seen_time")
    var bds40SeenTime: String? = null
        get() = _bds40SeenTime.toString()
        set(value) {
            field = value
            _bds40SeenTime = value?.let { Instant.parse(it) } ?: _bds40SeenTime
        }
    private var _bds40SeenTime: Instant = Instant.ofEpochSecond(0)
}