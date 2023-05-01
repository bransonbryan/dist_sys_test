package com.branson.bryan.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val id: Int?, val src: String, val dest: String, val body: Body)