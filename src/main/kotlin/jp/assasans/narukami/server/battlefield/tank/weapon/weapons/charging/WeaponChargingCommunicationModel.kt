package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.charging

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(5364948468178137841)
class WeaponChargingCommunicationModelCC(
) : IModelConstructor

@ProtocolEvent(5729258564735115120)
data class WeaponChargingCommunicationModelHandleChargingFinishEvent(
    val durationMs: Int,
) : IClientEvent

@ProtocolEvent(410241468043754693)
class WeaponChargingCommunicationModelHandleChargingStartEvent : IClientEvent

@ProtocolEvent(1954691809746631312)
data class WeaponChargingCommunicationModelFinishChargingEvent(
    val clientTime: Int,
) : IServerEvent

@ProtocolEvent(6089828626317647609)
data class WeaponChargingCommunicationModelStartChargingEvent(
    val clientTime: Int,
) : IServerEvent