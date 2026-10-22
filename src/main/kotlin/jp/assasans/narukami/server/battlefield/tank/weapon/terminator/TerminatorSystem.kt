/*
 * Narukami TO - a server software reimplementation for a certain browser tank game.
 * Copyright (c) 2025  Daniil Pryima
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package jp.assasans.narukami.server.battlefield.tank.weapon.terminator

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.battlefield.TankNodeV2
import jp.assasans.narukami.server.battlefield.damage.DamageEvent
import jp.assasans.narukami.server.battlefield.tank.DamageType
import jp.assasans.narukami.server.battlefield.tank.TankGroupComponent
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelFireCommandEvent
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelFireEvent
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelStartChargingCommandEvent
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelStartChargingEvent
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunTemplate
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.lobby.communication.remote

@MatchTemplate(TerminatorTemplate::class)
class TerminatorNode : NodeV2()

class TerminatorSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    @OnEventFireV2
    fun charge(
        context: IModelContext,
        event: TerminatorModelPrimaryChargeEvent,
        terminator: TerminatorNode,
        @JoinAll @JoinBy(TankGroupComponent::class) tank: TankNodeV2,
    ) = context {
        val terminatorShared = remote(terminator)

        logger.trace { "charge: $event" }

        TerminatorModelPrimaryRemoteChargeEvent(
            shooter = tank.gameObject,
            barrelIndex = event.barrelIndex
        ).schedule(terminator, terminatorShared - context)
    }

    @OnEventFireV2
    fun firePrimary(
        context: IModelContext,
        event: TerminatorModelPrimaryShotEvent,
        terminator: TerminatorNode,
        @JoinAll @JoinBy(TankGroupComponent::class) tank: TankNodeV2,
    ) = context {
        val terminatorShared = remote(terminator)

        logger.trace { "Fire : $event" }

        TerminatorModelPrimaryRemoteShotEvent(
            shooter = tank.gameObject,
            staticHitPoint = event.staticHitPoint,
            targets = event.targets,
            targetHitPoints = event.targetHitPoints,
            barrelIndex = event.barrelIndex
        ).schedule(terminator, terminatorShared - context)

        if (event.targets != null) {

            for (target in event.targets) {
                val targetObj = context.space.objects.get(target.id)
                if (targetObj != null)
                    DamageEvent(amount = 600f, source = tank.gameObject, type = DamageType.TERMINATOR).schedule(context, targetObj)
                else
                    logger.warn { "Deal damage to unexisting object" }
            }
        }
    }

    @OnEventFireV2
    fun secondaryHide(
        context: IModelContext,
        event: TerminatorModelSecondaryHideEvent,
        terminator: TerminatorNode,
        @JoinAll @JoinBy(TankGroupComponent::class) tank: TankNodeV2,
    ) = context {
        val terminatorShared = remote(terminator)

        logger.trace { "secondary hide: $event" }

        TerminatorModelSecondaryRemoteHideEvent(
            shooter = tank.gameObject,
        ).schedule(terminator, terminatorShared - context)
    }

    @OnEventFireV2
    fun secondaryOpen(
        context: IModelContext,
        event: TerminatorModelSecondaryOpenEvent,
        terminator: TerminatorNode,
        @JoinAll @JoinBy(TankGroupComponent::class) tank: TankNodeV2,
    ) = context {
        val terminatorShared = remote(terminator)

        logger.trace { "secondary open: $event" }

        TerminatorModelSecondaryRemoteOpenEvent(
            shooter = tank.gameObject,
        ).schedule(terminator, terminatorShared - context)
    }
}
