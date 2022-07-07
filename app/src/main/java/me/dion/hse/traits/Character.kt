package me.dion.hse.traits

import com.google.gson.JsonObject
import java.util.*

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: Status,
    val image: String) {

    companion object {
        private fun parseStatus(status: String): Status {
            return when (status.lowercase(Locale.ROOT)) {
                "dead" -> Status.DEAD
                "alive" -> Status.ALIVE
                "unknown" -> Status.UNKNOWN
                else -> throw IllegalArgumentException("Unknown status: $status")
            }
        }

        fun parseFromJson(json: JsonObject): Character {
            return Character(
                    id = json.get("id").asInt,
                    name = json.get("name").asString,
                    species = json.get("species").asString,
                    gender = json.get("gender").asString,
                    status = parseStatus(json.get("status").asString),
                    image = json.get("image").asString)
        }
    }
}