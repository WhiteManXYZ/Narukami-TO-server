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

import jp.assasans.narukami.server.battlefield.LightEffectItem
import jp.assasans.narukami.server.battlefield.LightingSFXEntity
import jp.assasans.narukami.server.battlefield.tank.weapon.*
import jp.assasans.narukami.server.battlefield.tank.weapon.laserpointer.LaserPointerModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.railgun.RailgunShootSFXModel
import jp.assasans.narukami.server.battlefield.tank.weapon.rocketlauncher.RocketLauncherModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.rocketlauncher.RocketLauncherSFXModel
import jp.assasans.narukami.server.battlefield.tank.weapon.splash.SplashModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.weapons.charging.WeaponChargingCommunicationModelCC
import jp.assasans.narukami.server.battlefield.tank.weapon.weapons.common.shell.ShellWeaponCommunicationModelCC
import jp.assasans.narukami.server.core.IGameObject
import jp.assasans.narukami.server.core.addModel
import jp.assasans.narukami.server.core.getComponent
import jp.assasans.narukami.server.garage.item.ModificationComponent
import jp.assasans.narukami.server.res.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object TerminatorTemplate : WeaponTemplate(), KoinComponent {
    private val gameResourceRepository: RemoteGameResourceRepository by inject()

    override fun create(id: Long, marketItem: IGameObject) = super.create(id, marketItem).apply {
        val modification = marketItem.getComponent<ModificationComponent>().modification.toString()

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
        addModel(DiscreteShotModelCC(reloadMsec = 1000))
        addModel(
            WeaponVerticalAnglesModelCC(
                angleDown = 0.2f,
                angleUp = 0.2f
            )
        )
        addModel(
            TerminatorModelCC(
                primaryCC = RailgunModelCC(
                    chargingTimeMsec = 1000,
                    weakeningCoeff = 0.5f,
                ),
                secondaryCC = RocketLauncherModelCC(
                    angularVelocity = 10f,
                    boostPhaseDuration = 600,
                    maxSpeed = 100f,
                    minSpeed = 30f,
                    salvoAimingGracePeriod = 1000,
                    salvoAimingTime = 1000,
                    salvoReloadTime = 1000,
                    salvoSize = 36,
                    shellRadius = 10f,
                    shotRange = 900f,
                    timeBetweenShotsOfSalvo = 300,
                ),
                secondaryKickback = 1.0f
            )
        )
        addModel(
            TerminatorSFXModel(
                openedSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                closedSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                servoSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
            )
        )
        addModel(
            RocketLauncherSFXModel(
                aimingCompleteSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                aimingSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                explosionMarkTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                explosionTexture = gameResourceRepository.get(
                    "tank.weapon.terminator.sfx.sphere",
                    mapOf("gen" to "2.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                leftHitSounds = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                leftShotSounds = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                lightingSFXEntity = LightingSFXEntity(
                    "rocket" to listOf(
                        LightEffectItem(
                            attenuationBegin = 50f,
                            attenuationEnd = 300f,
                            color = "0xff8844",
                            intensity = 1.5f,
                            time = 0,
                        ),
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xff8844",
                            intensity = 0f,
                            time = 400,
                        ),
                    ),
                    "explosion" to listOf(
                        LightEffectItem(
                            attenuationBegin = 100f,
                            attenuationEnd = 600f,
                            color = "0xffaa33",
                            intensity = 2.5f,
                            time = 0,
                        ),
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xffaa33",
                            intensity = 0f,
                            time = 400,
                        ),
                    ),
                ),
                rightHitSounds = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                rightShotSounds = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                rocketFlameTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                rocketFlightSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                rocketSmokeTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                rocketTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                targetLostSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
            )
        )
        addModel(
            RailgunShootSFXModel(
                chargingPart1 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.1",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                chargingPart2 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.2",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                chargingPart3 = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.charge.3",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
                hitMarkTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.hit-mark",
                    mapOf("gen" to "1.0"),
                    TextureRes,
                    Eager
                ),
                lightingSFXEntity = LightingSFXEntity(
                    "charge" to listOf(
                        LightEffectItem(
                            attenuationBegin = 200f,
                            attenuationEnd = 200f,
                            color = "0xff00ff",
                            intensity = 1.7f,
                            time = 0
                        ),
                        LightEffectItem(
                            attenuationBegin = 200f,
                            attenuationEnd = 800f,
                            color = "0xff00ff",
                            intensity = 1.3f,
                            time = 600
                        ),
                    ),
                    "hit" to listOf(
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xff00ff",
                            intensity = 2f,
                            time = 0
                        ),
                        LightEffectItem(
                            attenuationBegin = 200f,
                            attenuationEnd = 600f,
                            color = "0xff00ff",
                            intensity = 2f,
                            time = 100
                        ),
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xff00ff",
                            intensity = 0f,
                            time = 500
                        ),
                    ),
                    "rail" to listOf(
                        LightEffectItem(
                            attenuationBegin = 200f,
                            attenuationEnd = 1000f,
                            color = "0xff00ff",
                            intensity = 1.5f,
                            time = 0
                        ),
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xff00ff",
                            intensity = 0f,
                            time = 500
                        ),
                    ),
                    "shot" to listOf(
                        LightEffectItem(
                            attenuationBegin = 100f,
                            attenuationEnd = 600f,
                            color = "0xff00ff",
                            intensity = 1.7f,
                            time = 0
                        ),
                        LightEffectItem(
                            attenuationBegin = 1f,
                            attenuationEnd = 2f,
                            color = "0xff00ff",
                            intensity = 0f,
                            time = 300
                        ),
                    ),
                ),
                powTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.pow",
                    mapOf("gen" to "1.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                ringsTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.rings",
                    mapOf("gen" to "1.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                shotSound = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.shot",
                    mapOf("gen" to "1.0"),
                    SoundRes,
                    Eager
                ),
                smokeImage = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.smoke",
                    mapOf("gen" to "1.0"),
                    TextureRes,
                    Eager
                ),
                sphereTexture = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.sphere",
                    mapOf("gen" to "1.0"),
                    MultiframeTextureRes,
                    Eager
                ),
                trailImage = gameResourceRepository.get(
                    "tank.weapon.railgun.sfx.trail",
                    mapOf("gen" to "1.0", "modification" to modification),
                    TextureRes,
                    Eager
                ),
            )
        )
        addModel(
            LaserPointerModelCC(
                fadeInTimeMs = 500,
                laserPointerBlueColor = "0x30a0ff",
                laserPointerRedColor = "0xff3030",
                locallyVisible = true,
            )
        )
        addModel(
            WeaponChargingCommunicationModelCC()
        )
        addModel(
            ShellWeaponCommunicationModelCC()
        )
        addModel(
            SplashModelCC()
        )
    }
}
