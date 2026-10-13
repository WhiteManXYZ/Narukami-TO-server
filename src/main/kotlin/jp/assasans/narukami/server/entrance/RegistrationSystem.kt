package jp.assasans.narukami.server.entrance;

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.battlefield.UserGroupComponent
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.core.impl.Space
import jp.assasans.narukami.server.dispatcher.DispatcherOpenSpaceEvent
import jp.assasans.narukami.server.extensions.roundToNearest
import jp.assasans.narukami.server.lobby.CrystalsComponent
import jp.assasans.narukami.server.lobby.ScoreComponent
import jp.assasans.narukami.server.lobby.UsernameComponent
import jp.assasans.narukami.server.lobby.user.UserTemplate
import jp.assasans.narukami.server.net.sessionNotNull
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import kotlin.random.Random

class RegistrationSystem : AbstractSystem(), KoinComponent {
    private val logger = KotlinLogging.logger { }

    @OnEventFireV2
    @OutOfOrderExecution
    suspend fun registerUser(
        context: IModelContext,
        event: RegistrationModelRegisterUser,
        entrance: EntranceNodeV2,
        @JoinAll dispatcher: DispatcherNodeV2,
    ) = context {
        logger.info { "Registration: ${event.username} password ${event.password}" }

        // TODO database serialization

        // Create a user object and assign it to the session.
        // The user object is destroyed immediately when the session is closed,
        // without a grace period like in battles.
        val id = makeStablePersistentId("GameObject:User:${event.username}")
        val transientId = makeStableTransientId("GameObject:User:${event.username}:${Clock.System.now().toEpochMilliseconds()}")
        // XXX: We need to have a transient ID for the user object because game developers are [stupid].
        //  They require the tank object to have an ID of user, and considering that we cannot have two
        //  objects with the same ID in the same space, this makes it impossible to have a user object
        //  and a tank object in the battle space. This behavior deviates from the official server, which
        //  somehow uses the same ID for both user and tank objects. A possible solution is to have
        //  a separate "client ID" for game objects.
        val userObject = UserTemplate.instantiate(transientId).apply {
            addComponent(UserGroupComponent(id))
            addComponent(UsernameComponent(event.username))
            addComponent(ScoreComponent(0))
            addComponent(CrystalsComponent(1_500))
        }
        requireSpaceChannel.sessionNotNull.user = userObject

        val channel = DispatcherOpenSpaceEvent(Space.stableId("lobby")).schedule(dispatcher).await()
        channel.space.objects.add(userObject)

        // Close the entrance space channel to trigger loading screen on the client
        requireSpaceChannel.close()
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun checkUsernameAvailable(
        context: IModelContext,
        event: RegistrationModelCheckUsernameEvent,
        entrance: EntranceNodeV2,
    ) = context {
        logger.info { "Check username: ${event.username}" }

        //TODO username availability checking
        RegistrationModelUsernameFreeEvent().schedule(entrance)
    }
}
