package com.branson.bryan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
sealed class Body {
    abstract val msgId: Int?
    abstract val inReplyTo: Int?
}

@SerialName("echo")
@Serializable
data class Echo(
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null,
    @SerialName("echo") val echo: String): Body()

@SerialName("echo_ok")
@Serializable
data class EchoOk(
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null,
    @SerialName("echo") val echo: String): Body()

@SerialName("init")
@Serializable
data class Init (
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null,
    @SerialName("node_id") val nodeId: String,
    @SerialName("node_ids") val nodeIds: List<String>): Body()

@SerialName("init_ok")
@Serializable
data class InitOk (
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null): Body()