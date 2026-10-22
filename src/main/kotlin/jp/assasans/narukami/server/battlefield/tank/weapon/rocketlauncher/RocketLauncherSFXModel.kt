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
 * but WITHOUT ANY WARRANTY without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package jp.assasans.narukami.server.battlefield.tank.weapon.rocketlauncher

import jp.assasans.narukami.server.battlefield.LightingSFXEntity
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.protocol.ProtocolModel
import jp.assasans.narukami.server.res.Eager
import jp.assasans.narukami.server.res.MultiframeTextureRes
import jp.assasans.narukami.server.res.Resource
import jp.assasans.narukami.server.res.SoundRes
import jp.assasans.narukami.server.res.TextureRes

@ProtocolModel(6596471917520283782)
data class RocketLauncherSFXModel(
    val aimingCompleteSound: Resource<SoundRes, Eager>,
    val aimingSound: Resource<SoundRes, Eager>,
    val explosionMarkTexture: Resource<TextureRes, Eager>,
    val explosionTexture: Resource<MultiframeTextureRes, Eager>,
    val leftHitSounds: Resource<SoundRes, Eager>,
    val leftShotSounds: Resource<SoundRes, Eager>,
    val lightingSFXEntity: LightingSFXEntity,
    val rightHitSounds: Resource<SoundRes, Eager>,
    val rightShotSounds: Resource<SoundRes, Eager>,
    val rocketFlameTexture: Resource<TextureRes, Eager>,
    val rocketFlightSound: Resource<SoundRes, Eager>,
    val rocketSmokeTexture: Resource<TextureRes, Eager>,
    val rocketTexture: Resource<TextureRes, Eager>,
    val targetLostSound: Resource<SoundRes, Eager>,
) : IModelConstructor
