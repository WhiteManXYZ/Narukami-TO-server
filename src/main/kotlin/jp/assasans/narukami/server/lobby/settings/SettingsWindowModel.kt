package jp.assasans.narukami.server.lobby.settings

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(6137464772801974220)
class SettingsWindowModelCC : IModelConstructor

@ProtocolEvent(3551451542640774318)
data class SettingsWindowModelOpenSettingsEvent(
    val mode: PersonalMessageReceiveMode
) : IClientEvent

@ProtocolEvent(3279690199657104873)
class SettingsWindowModelShowSettingsEvent : IServerEvent

@ProtocolEvent(6896599104430193688)
data class SettingsWindowModelUploadClientSettingsEvent(
    val settings: ClientStoredSettings
) : IServerEvent

@ProtocolEvent(7698979133362054614)
data class SettingsWindowModelSetReceivePersonalMessagesEvent(
    val mode: PersonalMessageReceiveMode
) : IServerEvent