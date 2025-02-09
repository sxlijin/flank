package flank.corellium.client.console

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.client.request.url

/**
 * Internal factory method for creating initialized [Console] context.
 */
internal suspend fun connectConsole(
    url: String,
    token: String
): Console =
    HttpClient(CIO) {
        install(WebSockets)
    }.webSocketSession {
        url(url)
        header("Authorization", token)
    }.let { session ->
        Console(session)
    }
