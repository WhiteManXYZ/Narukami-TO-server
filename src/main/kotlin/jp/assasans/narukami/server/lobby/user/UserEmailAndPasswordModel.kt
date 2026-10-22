package jp.assasans.narukami.server.lobby.user

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(3179861497615625536)
data class UserEmailAndPasswordModelCC(
    val email: String,
    val emailConfirmed: Boolean,
) : IModelConstructor

@ProtocolEvent(2403434114154051813)
data class UserEmailAndPasswordModelCheckPasswordCorrectnessEvent(
    val password: String
) : IServerEvent

@ProtocolEvent(5335095270106426446)
class UserEmailAndPasswordModelCheckPasswordIsSetEvent : IServerEvent

@ProtocolEvent(3447449571984665145)
class UserEmailAndPasswordModelSendPasswordAndEmailChangeInstructionEvent : IServerEvent

@ProtocolEvent(6119462266823657248)
data class UserEmailAndPasswordModelSetPasswordEvent(
    val password: String
) : IServerEvent

@ProtocolEvent(6117545336529119210)
data class UserEmailAndPasswordModelUpdateEmailEvent(
    val email: String
) : IServerEvent

@ProtocolEvent(6038328003893289953)
data class UserEmailAndPasswordModelUpdatePasswordEvent(
    val oldPassword: String,
    val newPassword: String,
) : IServerEvent

@ProtocolEvent(3190244820038756769)
data class UserEmailAndPasswordModelActivateMessageEvent(
    val message: String
) : IClientEvent

@ProtocolEvent(579242823008667022)
class UserEmailAndPasswordModelEmailAlreadyUsedEvent : IClientEvent

@ProtocolEvent(2611173935532140280)
data class UserEmailAndPasswordModelEmailConfirmedEvent(
    val email: String
) : IClientEvent

@ProtocolEvent(8891234703158239948)
class UserEmailAndPasswordModelIncorrectEmailEvent : IClientEvent

@ProtocolEvent(2747789592087152345)
class UserEmailAndPasswordModelNotifyCorrectPasswordEvent : IClientEvent

@ProtocolEvent(176110661876294882)
class UserEmailAndPasswordModelNotifyIncorrectPasswordEvent : IClientEvent

@ProtocolEvent(93011926352432722)
class UserEmailAndPasswordModelNotifyPasswordIsNotSetEvent : IClientEvent

@ProtocolEvent(3017384138137697921)
class UserEmailAndPasswordModelNotifyPasswordIsSetEvent : IClientEvent

@ProtocolEvent(6252816260867053980)
class UserEmailAndPasswordModelPasswordChangedEvent : IClientEvent

@ProtocolEvent(7764478571911377711)
class UserEmailAndPasswordModelUpdatePasswordErrorEvent : IClientEvent