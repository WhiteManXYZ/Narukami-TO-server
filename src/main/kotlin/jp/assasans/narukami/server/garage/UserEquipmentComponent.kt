package jp.assasans.narukami.server.garage

import jp.assasans.narukami.server.core.IComponent
import jp.assasans.narukami.server.core.IGameObject

data class UserEquipmentComponent(
    var hullId: Long? = null,
    var weaponId: Long? = null,
    var paintId: Long? = null,
) : IComponent