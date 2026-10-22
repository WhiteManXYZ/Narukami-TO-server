package jp.assasans.narukami.server.lobby.settings

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.lobby.LobbyNodeV2
import jp.assasans.narukami.server.protocol.IProtocolEnum
import jp.assasans.narukami.server.protocol.ProtocolEnum
import jp.assasans.narukami.server.protocol.ProtocolStruct

@ProtocolStruct
data class ClientStoredSettings(
    val showDamageEnabled: Boolean,
)

@ProtocolEnum
enum class PersonalMessageReceiveMode(override val value: Int) : IProtocolEnum<Int> {
    ALL(0),
    FRIENDS_ONLY(1),
    NONE(2),
}

class SettingsSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    @OnEventFireV2
    @OutOfOrderExecution
    fun showSettings(
        context: IModelContext,
        event: SettingsWindowModelShowSettingsEvent,
        lobby: LobbyNodeV2,
    ) = context {
        SettingsWindowModelOpenSettingsEvent(PersonalMessageReceiveMode.ALL).schedule(lobby)
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun uploadClientSettings(
        context: IModelContext,
        event: SettingsWindowModelUploadClientSettingsEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "uploadClientSettings: showDamageEnabled=${event.settings.showDamageEnabled}" }
        // TODO
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun setReceivePersonalMessages(
        context: IModelContext,
        event: SettingsWindowModelSetReceivePersonalMessagesEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "setReceivePersonalMessages: ${event.mode}" }
        // TODO
    }
}