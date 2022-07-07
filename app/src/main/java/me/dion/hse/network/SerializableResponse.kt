package me.dion.hse.network

import okhttp3.Response
import java.io.Serializable

data class SerializableResponse(val response: Response) : Serializable