package com.branson.bryan

import com.branson.bryan.model.*
import com.branson.bryan.node.Node
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val serializers: SerializersModule = SerializersModule {
    polymorphic(Body::class) {
        subclass(Init::class, Init.serializer())
        subclass(InitOk::class, InitOk.serializer())
        subclass(Echo::class, Echo.serializer())
        subclass(EchoOk::class, EchoOk.serializer())
    }
}

private val json = Json {
    serializersModule = serializers
}


private val node: Node = Node(json = json)

fun main(args: Array<String>) {
    node.listen()
}
