package jp.assasans.narukami.server.lobby.user

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.lobby.LobbyNodeV2

class UserEmailAndPasswordSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    @OnEventFireV2
    @OutOfOrderExecution
    fun checkPasswordIsSet(
        context: IModelContext,
        event: UserEmailAndPasswordModelCheckPasswordIsSetEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "checkPasswordIsSet" }
        // TODO
        UserEmailAndPasswordModelNotifyPasswordIsSetEvent().schedule(lobby)
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun checkPasswordCorrectness(
        context: IModelContext,
        event: UserEmailAndPasswordModelCheckPasswordCorrectnessEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "checkPasswordCorrectness" }
        // TODO
        UserEmailAndPasswordModelNotifyCorrectPasswordEvent().schedule(lobby)
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun setPassword(
        context: IModelContext,
        event: UserEmailAndPasswordModelSetPasswordEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "setPassword" }
        // TODO: save password
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun updatePassword(
        context: IModelContext,
        event: UserEmailAndPasswordModelUpdatePasswordEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "updatePassword" }
        // TODO: update password
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun updateEmail(
        context: IModelContext,
        event: UserEmailAndPasswordModelUpdateEmailEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "updateEmail: ${event.email}" }
        // TODO: save email
        UserEmailAndPasswordModelEmailConfirmedEvent(event.email).schedule(lobby)
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun sendChangeInstruction(
        context: IModelContext,
        event: UserEmailAndPasswordModelSendPasswordAndEmailChangeInstructionEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "sendPasswordAndEmailChangeInstruction" }
        // TODO
    }
}