package jp.assasans.narukami.server.battlefield.tank.weapon.laserpointer

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.battlefield.tank.weapon.terminator.TerminatorNode
import jp.assasans.narukami.server.battlefield.tank.weapon.terminator.TerminatorTemplate
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.lobby.communication.remote

abstract class LaserWeaponNode : NodeV2()

class LaserPointerSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    private fun IModelContext.sendAimRemote(weapon: NodeV2, event: LaserPointerModelAimAtTankEvent) {
        val shared = remote(weapon)
        LaserPointerModelAimRemoteAtTankEvent(
            target = event.target,
            localSpotPosition = event.localSpotPosition
        ).schedule(weapon, shared - this)
    }

    private fun IModelContext.sendUpdateRemote(weapon: NodeV2, event: LaserPointerModelUpdateDirectionEvent) {
        val shared = remote(weapon)
        LaserPointerModelUpdateRemoteDirectionEvent(
            projectionOnVerticalAxis = event.projectionOnVerticalAxis
        ).schedule(weapon, shared - this)
    }

    private fun IModelContext.sendHideRemote(weapon: NodeV2) {
        val shared = remote(weapon)
        LaserPointerModelHideRemoteEvent().schedule(weapon, shared - this)
    }

    @OnEventFireV2
    fun aimAtTankTerminator(
        context: IModelContext,
        event: LaserPointerModelAimAtTankEvent,
        weapon: TerminatorNode,
    ) = context { sendAimRemote(weapon, event) }

    @OnEventFireV2
    fun updateDirectionTerminator(
        context: IModelContext,
        event: LaserPointerModelUpdateDirectionEvent,
        weapon: TerminatorNode,
    ) = context { sendUpdateRemote(weapon, event) }

    @OnEventFireV2
    fun hideTerminator(
        context: IModelContext,
        event: LaserPointerModelHideEvent,
        weapon: TerminatorNode,
    ) = context { sendHideRemote(weapon) }
}