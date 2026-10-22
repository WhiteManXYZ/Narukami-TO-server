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

package jp.assasans.narukami.server.battlefield.replay

import kotlin.coroutines.CoroutineContext
import jp.assasans.narukami.server.core.ISpace
import jp.assasans.narukami.server.net.IChannelKind
import jp.assasans.narukami.server.net.ISocketClient
import jp.assasans.narukami.server.net.SpaceChannel
import jp.assasans.narukami.server.net.session.ISession
import jp.assasans.narukami.server.protocol.IProtocol
import jp.assasans.narukami.server.protocol.Protocol
import jp.assasans.narukami.server.protocol.ProtocolBuffer
import kotlinx.coroutines.Dispatchers

class FakeSocket(val space: ISpace) : ISocketClient {
    override val protocol: IProtocol = Protocol(this)

    override var kind: IChannelKind = SpaceChannel(this, space)

    override var session: ISession? = null

    override fun process(buffer: ProtocolBuffer) {

    }

    override fun send(buffer: ProtocolBuffer) {

    }

    override suspend fun close() {

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default
}