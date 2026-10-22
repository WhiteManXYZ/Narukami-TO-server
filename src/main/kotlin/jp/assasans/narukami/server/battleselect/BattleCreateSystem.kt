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

package jp.assasans.narukami.server.battleselect

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.battlefield.BattleMapTemplate
import jp.assasans.narukami.server.battlefield.BattleUserNode
import jp.assasans.narukami.server.battlefield.BattlefieldModelCC
import jp.assasans.narukami.server.battlefield.BattlefieldTemplate
import jp.assasans.narukami.server.battlefield.UserGroupComponent
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.core.impl.GameObjectIdSource
import jp.assasans.narukami.server.core.impl.Space
import jp.assasans.narukami.server.dispatcher.DispatcherLoadObjectsManagedEvent
import jp.assasans.narukami.server.lobby.UserNode
import jp.assasans.narukami.server.lobby.communication.remote
import jp.assasans.narukami.server.net.session.userNotNull
import jp.assasans.narukami.server.net.sessionNotNull
import org.koin.core.component.inject
import kotlin.getValue

class BattleCreateSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    private val spaces: IRegistry<ISpace> by inject()

    @OnEventFireV2
    fun checkName(
        context: IModelContext,
        event: BattleCreateModelCheckBattleNameForForbiddenWordsEvent,
        battleCreate: BattleCreateNodeV2,
    ) = context {
        val filteredName = event.battleName.replace("2112", "2019")
        BattleCreateModelSetFilteredBattleNameEvent(filteredName).schedule(battleCreate)
    }

    @OnEventFireV2
    fun create(
        context: IModelContext,
        event: BattleCreateModelCreateBattleEvent,
        battleCreate: BattleCreateNodeV2,
    ) = context {
        // TODO user rank/ban status checking

        val mapInfo = space.objects.get(event.params.mapId)
        if (mapInfo == null) {
            logger.warn { "Unknown mapId: ${event.params.mapId}" }
            return@context
        }

        val battleId = Space.freeId()
        // TODO use correct template for selected mode
        val battleInfoObject = DMBattleInfoTemplate.create(battleId, mapInfo, event.params)
        battleInfoObject.addComponent(BattleInfoGroupComponent(battleInfoObject))

        spaces.add(Space(battleId).apply {
            battleInfoObject.addComponent(BattleSpaceComponent(this))

            objects.remove(rootObject)

            val battleMapObject =
                BattleMapTemplate.instantiate(GameObjectIdSource.transientId("Map:${mapInfo.id}"))
            battleMapObject.addAllComponents(mapInfo.allComponents.values)
            battleMapObject.addComponent(BattleInfoGroupComponent(battleInfoObject))

            objects.add(battleMapObject)

            val battlefieldObject = BattlefieldTemplate.create(id, battleMapObject)
            replaceRootObject(battlefieldObject)
        })

        val lobbySpace = spaces.get(Space.stableId("lobby"))
            ?: throw IllegalStateException("Lobby space not found")

        lobbySpace.objects.add(battleInfoObject)

        val lobbyChannel = context.requireSpaceChannel.sessionNotNull.spaces.get(Space.stableId("lobby"))
            ?: throw IllegalStateException("No lobby channel")

        AddBattleEvent(battleInfoObject).schedule(lobbyChannel, battleInfoObject)
    }
}
