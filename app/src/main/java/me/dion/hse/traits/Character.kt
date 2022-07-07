package me.dion.hse.traits

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.Serializable
import java.util.*

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: Status,
    val image: String) : Serializable {

    companion object {
        private fun parseStatus(status: String): Status {
            return when (status.lowercase(Locale.ROOT)) {
                "dead" -> Status.DEAD
                "alive" -> Status.ALIVE
                "unknown" -> Status.UNKNOWN
                else -> throw IllegalArgumentException("Unknown status: $status")
            }
        }

        private fun parseFromJson(json: JsonObject): Character {
            return Character(
                    id = json.get("id").asInt,
                    name = json.get("name").asString,
                    species = json.get("species").asString,
                    gender = json.get("gender").asString,
                    status = parseStatus(json.get("status").asString),
                    image = json.get("image").asString)
        }

        fun parseJsonArray(json: JsonArray): MutableList<Character> {
            val characters = mutableListOf<Character>()
            for (i in 0 until json.size()) {
                characters.add(parseFromJson(json.get(i).asJsonObject))
            }
            return characters
        }
    }
}