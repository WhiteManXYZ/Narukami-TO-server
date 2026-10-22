package jp.assasans.narukami.server.lobby.user

import jp.assasans.narukami.server.core.IClientEvent
import jp.assasans.narukami.server.core.IModelConstructor
import jp.assasans.narukami.server.core.IServerEvent
import jp.assasans.narukami.server.protocol.ProtocolEvent
import jp.assasans.narukami.server.protocol.ProtocolModel
import jp.assasans.narukami.server.protocol.ProtocolStruct

@ProtocolStruct
data class CountryInfo(
    val countryCode: String,
    val countryName: String,
)

@ProtocolModel(2080556892932195426)
data class UserCountryModelCC(
    val countries: List<CountryInfo>,
    val defaultCountryCode: String,
    val locationCheckEnabled: Boolean,
) : IModelConstructor

@ProtocolEvent(6980490784847947699)
data class UserCountryModelRequestUserCountryEvent(
    val defaultCountryCode: String
) : IClientEvent

@ProtocolEvent(1855922599480816194)
class UserCountryModelShowPaymentWindowEvent : IClientEvent

@ProtocolEvent(3473864056438530076)
class UserCountryModelCheckUserPaymentCountryEvent : IServerEvent

@ProtocolEvent(6837641584512438278)
data class UserCountryModelSetCurrentPaymentCountryEvent(
    val countryCode: String
) : IServerEvent