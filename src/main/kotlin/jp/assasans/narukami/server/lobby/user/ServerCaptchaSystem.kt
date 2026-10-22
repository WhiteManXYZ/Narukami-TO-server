package jp.assasans.narukami.server.lobby.user

import io.github.oshai.kotlinlogging.KotlinLogging
import jp.assasans.narukami.server.core.*
import jp.assasans.narukami.server.lobby.LobbyNodeV2

class ServerCaptchaSystem : AbstractSystem() {
    private val logger = KotlinLogging.logger { }

    @OnEventFireV2
    @OutOfOrderExecution
    fun checkCaptcha(
        context: IModelContext,
        event: CaptchaModelCheckCaptchaEvent,
        lobby: LobbyNodeV2,
    ) = context {
        // TODO captcha validation
        CaptchaModelCaptchaCorrectEvent(event.location).schedule(lobby)
    }

    @OnEventFireV2
    @OutOfOrderExecution
    fun getNewCaptcha(
        context: IModelContext,
        event: CaptchaModelGetNewCaptchaEvent,
        lobby: LobbyNodeV2,
    ) = context {
        logger.info { "getNewCaptcha: loc=${event.location} (ignored)" }
        // TODO
    }
}