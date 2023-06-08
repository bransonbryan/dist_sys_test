package com.branson.bryan.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

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

@SerialName("generate")
@Serializable
data class Generate(
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null
) : Body()

@SerialName("generate_ok")
@Serializable
data class GenerateOk(
    @SerialName("msg_id") override val msgId: Int? = null,
    @SerialName("in_reply_to") override val inReplyTo: Int? = null,
    @SerialName("id") @Serializable(with = UUIDSerializer::class) val id: UUID
) : Body()

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }

}