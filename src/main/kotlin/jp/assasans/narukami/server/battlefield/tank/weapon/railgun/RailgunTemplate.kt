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

package jp.assasans.narukami.server.battlefield.tank.weapon.railgun

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import jp.assasans.narukami.server.battlefield.LightEffectItem
import jp.assasans.narukami.server.battlefield.LightingSFXEntity
import jp.assasans.narukami.server.battlefield.tank.weapon.*
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.core.addModel
import jp.assasans.narukami.server.core.getComponent
import jp.assasans.narukami.server.garage.item.ModificationComponent
import jp.assasans.narukami.server.res.*

private data class RailgunLighting(
    val charge: List<LightEffectItem>,
    val shot: List<LightEffectItem>,
    val hit: List<LightEffectItem>,
    val rail: List<LightEffectItem>,
)

private fun railgunLighting(color: String) = RailgunLighting(
    charge = listOf(
        LightEffectItem(attenuationBegin = 200f, attenuationEnd = 200f, color = color, intensity = 0.7f, time = 0),
        LightEffectItem(attenuationBegin = 200f, attenuationEnd = 800f, color = color, intensity = 0.3f, time = 600),
    ),
    shot = listOf(
        LightEffectItem(attenuationBegin = 100f, attenuationEnd = 600f, color = color, intensity = 0.7f, time = 0),
        LightEffectItem(attenuationBegin = 1f, attenuationEnd = 2f, color = color, intensity = 0f, time = 300),
    ),
    hit = listOf(
        LightEffectItem(attenuationBegin = 200f, attenuationEnd = 600f, color = color, intensity = 0.7f, time = 0),
        LightEffectItem(attenuationBegin = 1f, attenuationEnd = 2f, color = color, intensity = 0f, time = 300),
    ),
    rail = listOf(
        LightEffectItem(attenuationBegin = 100f, attenuationEnd = 500f, color = color, intensity = 0.5f, time = 0),
        LightEffectItem(attenuationBegin = 1f, attenuationEnd = 2f, color = color, intensity = 0f, time = 1800),
    ),
)

private val railgunLightingByMod = mapOf(
    "0" to railgunLighting("0x59C4F9"),
    "1" to railgunLighting("0x50A9FF"),
    "2" to railgunLighting("0xCF7CF9"),
    "3" to railgunLighting("0xFFD059"),
)

object RailgunTemplate : WeaponTemplate(), KoinComponent {
    private val gameResourceRepository: RemoteGameResourceRepository by inject()

    override fun create(id: Long, marketItem: IGameObject) = super.create(id, marketItem).apply {
        val modification = marketItem.getComponent<ModificationComponent>().modification.toString()

        val lighting = railgunLightingByMod[modification] ?: railgunLightingByMod.getValue("0")

        addModel(
            RotatingTurretModelCC(
                turretState = TurretStateCommand(
                    controlInput = 0f,
                    controlType = TurretControlType.ROTATION_DIRECTION,
                    direction = 0f,
                    rotationSpeedNumber = 100
                )
            )
        )
        addModel(DiscreteShotModelCC(reloadMsec = 3000))
        addModel(
            WeaponVerticalAnglesModelCC(
                angleDown = 0.2f,
                angleUp = 0.2f
            )
        )
        addModel(
            RailgunModelCC(
                chargingTimeMsec = 1000,
                weakeningCoeff = 0.5f,
            )
        )
        addModel(
            RailgunShootSFXModel(
                chargingPart1 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "2.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                chargingPart2 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.2",
                    mapOf("gen" to "2.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                chargingPart3 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.3",
                    mapOf("gen" to "2.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                hitMarkTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.hit-mark",
                    mapOf("gen" to "2.0"),
                    TextureRes,
                    Eager
                ),
                lightingSFXEntity = LightingSFXEntity(
                    "charge" to lighting.charge,
                    "hit" to lighting.hit,
                    "rail" to lighting.rail,
                    "shot" to lighting.shot,
                ),
                powTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.pow",
                    mapOf("gen" to "2.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                ringsTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.rings",
                    mapOf("gen" to "2.0", "modification" to modification),
                    MultiframeTextureRes,
                    Eager
                ),
                shotSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "2.0"),
                    SoundRes,
                    Eager
                ),
                smokeImage = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.smoke",
                    mapOf("gen" to "2.0"),
                    TextureRes,
                    Eager
                ),
                sphereTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.sphere",
                    mapOf("gen" to "2.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                trailImage = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.trail",
                    mapOf("gen" to "2.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
            )
        )
    }
}
