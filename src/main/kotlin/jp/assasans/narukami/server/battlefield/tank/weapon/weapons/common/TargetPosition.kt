package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common

import jp.assasans.narukami.server.battlefield.Vector3d
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.protocol.ProtocolStruct

@ProtocolStruct
data class TargetPosition(
    val orientation: Vector3d,
    val position: Vector3d,
    val target: IGameObject,
    val turretAngle: Float,
)