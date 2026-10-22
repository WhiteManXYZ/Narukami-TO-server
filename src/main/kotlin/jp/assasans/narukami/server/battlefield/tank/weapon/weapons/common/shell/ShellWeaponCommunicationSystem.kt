package jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.shell

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.battlefield.TankNodeV2
import jp.assasans.narukami.server.battlefield.damage.DamageEvent
import jp.assasans.narukami.server.battlefield.tank.DamageType
import jp.assasans.narukami.server.battlefield.tank.TankGroupComponent
import jp.assasans.narukami.server.battlefield.tank.weapon.terminator.TerminatorNode
import jp.assasans.narukami.server.core.AbstractSystem
import jp.assasans.narukami.server.core.IModelContext
import jp.assasans.narukami.server.core.JoinAll
import jp.assasans.narukami.server.core.JoinBy
import jp.assasans.narukami.server.core.NodeV2
import jp.assasans.narukami.server.core.OnEventFireV2
import jp.assasans.narukami.server.core.invoke
import jp.assasans.narukami.server.core.schedule
import jp.assasans.narukami.server.core.space
import jp.assasans.narukami.server.entrance.DispatcherNodeV2
import jp.assasans.narukami.server.lobby.communication.remote

class ShellWeaponCommunicationSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    private fun IModelContext.broadcastShot(weapon: NodeV2, event: ShellWeaponCommunicationModelTryToShootEvent) {
        val shared = remote(weapon)
        ShellWeaponCommunicationModelShotEvent(
            barrelIndex = event.barrelIndex,
            shotId = event.shotId,
            direction = event.direction,
        ).schedule(weapon, shared - this)
    }

    private fun IModelContext.broadcastDummyShot(weapon: NodeV2, event: ShellWeaponCommunicationModelTryToDummyShootEvent) {
        val shared = remote(weapon)
        ShellWeaponCommunicationModelDummyShotEvent(
            barrelIndex = event.barrelIndex,
        ).schedule(weapon, shared - this)
    }

    private fun IModelContext.broadcastShotWithTarget(
        weapon: NodeV2,
        event: ShellWeaponCommunicationModelTryToShootWithTargetEvent,
    ) {
        val shared = remote(weapon)
        ShellWeaponCommunicationModelShotWithTargetEvent(
            barrelIndex = event.barrelIndex,
            shotId = event.shotId,
            direction = event.direction,
            target = event.targetPosition,
            localTargetPoint = event.localTargetPoint,
        ).schedule(weapon, shared - this)
    }

    @OnEventFireV2
    fun tryToDummyShootTerminator(
        context: IModelContext,
        event: ShellWeaponCommunicationModelTryToDummyShootEvent,
        weapon: TerminatorNode,
    ) = context { broadcastDummyShot(weapon, event)}

    @OnEventFireV2
    fun tryToShootTerminator(
        context: IModelContext,
        event: ShellWeaponCommunicationModelTryToShootEvent,
        weapon: TerminatorNode,
    ) = context { broadcastShot(weapon, event) }

    @OnEventFireV2
    fun tryToShootWithTargetTerminator(
        context: IModelContext,
        event: ShellWeaponCommunicationModelTryToShootWithTargetEvent,
        weapon: TerminatorNode,
    ) = context { broadcastShotWithTarget(weapon, event) }

    @OnEventFireV2
    fun tryToHitTerminator(
        context: IModelContext,
        event: ShellWeaponCommunicationModelTryToHitEvent,
        weapon: TerminatorNode,
        @JoinAll @JoinBy(TankGroupComponent::class) tank: TankNodeV2,
    ) = context {
        val targets = event.shellHit.targets
        for (target in targets) {
            val targetObj = context.space.objects.get(target.target.id)
            if (targetObj != null) {
                DamageEvent(
                    amount = 350f,
                    source = tank.gameObject,
                    type = DamageType.TERMINATOR,
                ).schedule(context, targetObj)
            } else {
                logger.warn { "TryToHit: unexisting target ${target.target.id}" }
            }
        }
    }
}