package jp.assasans.narukami.server.battlefield.tank.weapon.laserpointer

import jp.assasans.narukami.server.battlefield.Vector3d
import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel

@ProtocolModel(7266201540452328992)
data class LaserPointerModelCC(
    val fadeInTimeMs: Int,
    val laserPointerBlueColor: String,
    val laserPointerRedColor: String,
    val locallyVisible: Boolean,
) : IModelConstructor

@ProtocolEvent(8964402760194876659)
data class LaserPointerModelAimRemoteAtTankEvent(
    val target: IGameObject,
    val localSpotPosition: Vector3d,
) : IClientEvent

@ProtocolEvent(4538073797885146589)
class LaserPointerModelHideRemoteEvent : IClientEvent

@ProtocolEvent(7474691314934075141)
data class LaserPointerModelUpdateRemoteDirectionEvent(
    val projectionOnVerticalAxis: Float
) : IClientEvent

@ProtocolEvent(1624555868970627451)
data class LaserPointerModelAimAtTankEvent(
    val target: IGameObject,
    val localSpotPosition: Vector3d,
) : IServerEvent

@ProtocolEvent(8285800812700182079)
class LaserPointerModelHideEvent : IServerEvent

@ProtocolEvent(3608950371274975129)
data class LaserPointerModelUpdateDirectionEvent(
    val projectionOnVerticalAxis: Float
) : IServerEvent