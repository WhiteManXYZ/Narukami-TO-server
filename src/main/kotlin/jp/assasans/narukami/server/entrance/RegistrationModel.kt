package jp.assasans.narukami.server.entrance;

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent

@ProtocolEvent(1968867162405923123)
data class RegistrationModelCheckUsernameEvent(
        val username: String
) : IServerEvent

@ProtocolEvent(1968866752316155064)
data class RegistrationModelRegisterUser(
    val username: String,
    val password: String,
    val registeredUrl: String,
    val remember: Boolean,
    val realName: String,
    val idNumber: String
) : IServerEvent

@ProtocolEvent(1247179769701225356)
class RegistrationModelUsernameFreeEvent : IClientEvent

@ProtocolEvent(1247179769701109529)
class RegistrationModelUsernameBusyEvent(
    val suggestedNicknames: List<String>
) : IClientEvent

@ProtocolEvent(4345588892358775163)
class RegistrationModelUsernameIncorrectEvent : IClientEvent
