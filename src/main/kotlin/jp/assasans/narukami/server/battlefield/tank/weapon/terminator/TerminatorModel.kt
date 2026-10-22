package jp.assasans.narukami.server.battlefield.tank.weapon.terminator

import jp.assasans.narukami.server.battlefield.Vector3d
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.rocketlauncher.RocketLauncherModelCC
import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(6855550629220038001)
data class TerminatorModelCC(
    val primaryCC: RailgunModelCC,
    val secondaryCC: RocketLauncherModelCC,
    val secondaryKickback: Float,
) : IModelConstructor

@ProtocolEvent(5256326385350796760)
data class TerminatorModelPrimaryRemoteChargeEvent(
    val shooter: IGameObject,
    val barrelIndex: Int,
) : IClientEvent

@ProtocolEvent(2805722386553418492)
data class TerminatorModelPrimaryRemoteDummyEvent(
    val shooter: IGameObject,
    val barrelIndex: Int,
) : IClientEvent

@ProtocolEvent(7050167951547569950)
data class TerminatorModelPrimaryRemoteShotEvent(
    val shooter: IGameObject,
    val staticHitPoint: Vector3d?,
    val targets: List<IGameObject>?,
    val targetHitPoints: List<Vector3d>?,
    val barrelIndex: Int,
) : IClientEvent

@ProtocolEvent(7539316643018047656)
data class TerminatorModelSecondaryRemoteHideEvent(
    val shooter: IGameObject,
) : IClientEvent

@ProtocolEvent(7539316643017832352)
data class TerminatorModelSecondaryRemoteOpenEvent(
    val shooter: IGameObject,
) : IClientEvent

@ProtocolEvent(2574198055537303018)
data class TerminatorModelPrimaryChargeEvent(
    val clientTime: Int,
    val barrelIndex: Int,
) : IServerEvent

@ProtocolEvent(4819159322266251468)
data class TerminatorModelPrimaryDummyShotEvent(
    val clientTime: Int,
    val barrelIndex: Int,
) : IServerEvent

@ProtocolEvent(7291559365196289104)
data class TerminatorModelPrimaryShotEvent(
    val clientTime: Int,
    val staticHitPoint: Vector3d?,
    val targets: List<IGameObject>?,
    val targetHitPoints: List<Vector3d>?,
    val targetIncarnations: List<Short>?,
    val targetPositions: List<Vector3d>?,
    val hitPointsWorld: List<Vector3d>?,
    val barrelIndex: Int,
) : IServerEvent

@ProtocolEvent(4602021999239443306)
data class TerminatorModelSecondaryHideEvent(
    val clientTime: Int,
) : IServerEvent

@ProtocolEvent(4602021999239658610)
data class TerminatorModelSecondaryOpenEvent(
    val clientTime: Int,
) : IServerEvent