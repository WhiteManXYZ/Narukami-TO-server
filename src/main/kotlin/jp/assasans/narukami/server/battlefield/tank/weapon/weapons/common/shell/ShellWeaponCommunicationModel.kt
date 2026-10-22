package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.shell

import jp.assasans.narukami.server.battlefield.Vector3d
import jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.TargetPosition
import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(1240318892254773555)
class ShellWeaponCommunicationModelCC : IModelConstructor

@ProtocolEvent(6084140936135221501)
data class ShellWeaponCommunicationModelTryToDummyShootEvent(
    val clientTime: Int,
    val barrelIndex: Byte,
) : IServerEvent

@ProtocolEvent(2196643676780835911)
data class ShellWeaponCommunicationModelTryToShootEvent(
    val clientTime: Int,
    val barrelIndex: Byte,
    val shotId: Int,
    val direction: Vector3d,
) : IServerEvent

@ProtocolEvent(8425098034993629552)
data class ShellWeaponCommunicationModelTryToShootWithTargetEvent(
    val clientTime: Int,
    val barrelIndex: Byte,
    val shotId: Int,
    val direction: Vector3d,
    val targetPosition: TargetPosition,
    val localTargetPoint: Vector3d,
) : IServerEvent

@ProtocolEvent(5780090124727748179)
data class ShellWeaponCommunicationModelTryToHitEvent(
    val clientTime: Int,
    val shellHit: ShellHit,
) : IServerEvent

@ProtocolEvent(1168439784383392489)
data class ShellWeaponCommunicationModelShotEvent(
    val barrelIndex: Byte,
    val shotId: Int,
    val direction: Vector3d,
) : IClientEvent

@ProtocolEvent(2637790242235689519)
data class ShellWeaponCommunicationModelDummyShotEvent(
    val barrelIndex: Byte,
) : IClientEvent

@ProtocolEvent(8433478043499666834)
data class ShellWeaponCommunicationModelShotWithTargetEvent(
    val barrelIndex: Byte,
    val shotId: Int,
    val direction: Vector3d,
    val target: TargetPosition,
    val localTargetPoint: Vector3d,
) : IClientEvent