package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.shell

import jp.assasans.narukami.server.battlefield.Vector3d
import jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.TargetPosition
import jp.assasans.narukami.server.protocol.ProtocolStruct

@ProtocolStruct
data class ShellState(
    val clientTime: Int,
    val direction: Vector3d,
    val position: Vector3d,
)