package jp.assasans.narukami.server.battlefield.tank.weapon.splash

import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(2235965200289058033)
class SplashModelCC : IModelConstructor

@ProtocolModel(2235965200289058033)
data class SplashModel(
  val impactForce: Float,
  val minSplashDamagePercent: Float,
  val radiusOfMaxSplashDamage: Float,
  val splashDamageRadius: Float,
) : IModelConstructor