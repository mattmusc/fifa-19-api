package io.mattmusc.domain.player.api

import io.mattmusc.domain.player.api.dto.CreatePlayerDto
import io.mattmusc.domain.player.api.dto.PlayerDto
import io.mattmusc.domain.player.api.dto.UpdatePlayerDto

interface PlayerService {
    fun retrievePlayer(playerId: Long): PlayerDto?

    fun retrievePlayers(): List<PlayerDto>

    fun addPlayer(playerDto: CreatePlayerDto): PlayerDto

    fun updatePlayer(id: Long, playerDto: UpdatePlayerDto): PlayerDto?
}