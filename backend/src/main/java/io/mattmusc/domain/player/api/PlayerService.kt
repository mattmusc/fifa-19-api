package io.mattmusc.domain.player.api

import io.mattmusc.domain.player.api.dto.PlayerDto

interface PlayerService {
    fun retrievePlayer(playerId: Long): PlayerDto?

    fun retrievePlayers(): List<PlayerDto>
}