package jp.assasans.narukami.server.lobby.user

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.IProtocolEnum
import jp.assasans.narukami.server.protocol.ProtocolEnum
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolEnum
enum class CaptchaLocation(override val value: Int) : IProtocolEnum<Int> {
    LOGIN_FORM(0),
    REGISTER_FORM(1),
    CLIENT_STARTUP(2),
    RESTORE_PASSWORD_FORM(3),
    EMAIL_CHANGE_HASH(4),
    ACCOUNT_SETTINGS_FORM(5),
}

@ProtocolModel(2108103923322474513)
data class ServerCaptchaModelCC(
    val stateWithCaptcha: List<CaptchaLocation>,
) : IModelConstructor

@ProtocolEvent(4998460206979194964)
data class CaptchaModelCaptchaCorrectEvent(
    val state: CaptchaLocation,
) : IClientEvent

@ProtocolEvent(756296912352966419)
data class CaptchaModelCaptchaFailedEvent(
    val state: CaptchaLocation,
    val newCaptcha: List<Int>,
) : IClientEvent

@ProtocolEvent(6032454100111503495)
data class CaptchaModelShowCaptchaEvent(
    val state: CaptchaLocation,
    val captcha: List<Int>,
) : IClientEvent

@ProtocolEvent(3312562673813486434)
data class CaptchaModelCheckCaptchaEvent(
    val location: CaptchaLocation,
    val answer: String,
) : IServerEvent

@ProtocolEvent(7369561336714029596)
data class CaptchaModelGetNewCaptchaEvent(
    val location: CaptchaLocation,
) : IServerEvent