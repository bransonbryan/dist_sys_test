package com.branson.bryan.node

import com.branson.bryan.model.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception
import kotlin.system.exitProcess

class Node(
    private var nodes: List<String> = listOf<String>(),
    private var nodeId: String = "",
    private val json: Json,
    private var idCounter: Int = 1
) {

    fun listen(): Unit {
        try {
            while (true) {
              val input = readln()
              val message: Message = json.decodeFromString(input)
                when(message.body) {
                    is Init -> handleInit(message, message.body)
                    is InitOk -> TODO()
                    is Echo -> handleEcho(message, message.body)
                    is EchoOk -> TODO()
                }
            }
        } catch (e: Exception) {
            System.err.println("Caught exception while handling messages + ${e.message}")
            exitProcess(1)
        }
    }

    private fun handleInit(message: Message, init: Init): Unit {
        this.nodes = init.nodeIds
        this.nodeId = init.nodeId

        val reply = InitOk(msgId = idCounter, inReplyTo = init.msgId)
        this.idCounter += 1
        val replyMessage = Message(id = this.idCounter, src = message.dest, dest = message.src, body = reply)
        System.err.println("Built init reply $replyMessage")
        this.idCounter += 1

        send(replyMessage)
    }

    private fun handleEcho(message: Message, echo: Echo): Unit {
        val reply = EchoOk(msgId = this.idCounter, inReplyTo = echo.msgId, echo = echo.echo)
        this.idCounter += 1
        val replyMessage = Message(id = this.idCounter, src = message.dest, dest = message.src, body = reply)
        this.idCounter += 1
        send(replyMessage)
    }

    private fun send(message: Message) {
        val jsonString = json.encodeToString(message)
        System.err.println("Sending message $jsonString")
        println(jsonString)
    }
}