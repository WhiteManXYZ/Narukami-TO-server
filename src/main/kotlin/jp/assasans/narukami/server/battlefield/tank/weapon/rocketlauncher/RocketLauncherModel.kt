package jp.assasans.narukami.server.battlefield.tank.weapon.rocketlauncher

import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(4045733714401632272)
data class RocketLauncherModelCC(
    val angularVelocity: Float,
    val boostPhaseDuration: Int,
    val maxSpeed: Float,
    val minSpeed: Float,
    val salvoAimingGracePeriod: Int,
    val salvoAimingTime: Int,
    val salvoReloadTime: Int,
    val salvoSize: Int,
    val shellRadius: Float,
    val shotRange: Float,
    val timeBetweenShotsOfSalvo: Int,
) : IModelConstructor