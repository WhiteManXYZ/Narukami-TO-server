package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.shell

import jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.TargetPosition
import jp.assasans.narukami.server.protocol.ProtocolStruct

@ProtocolStruct
data class ShellHit(
    val shotId: Int,
    val states: List<ShellState>,
    val targets: List<TargetPosition>,
)