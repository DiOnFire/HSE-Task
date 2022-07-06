package me.dion.hse.traits

import okhttp3.Response
import java.io.Serializable

data class SerializableResponse(val response: Response) : Serializable