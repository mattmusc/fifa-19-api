package io.mattmusc.domain.player.service

import io.mattmusc.domain.player.api.PlayerService
import io.mattmusc.domain.player.api.dto.CreatePlayerDto
import io.mattmusc.domain.player.api.dto.PlayerDto
import io.mattmusc.domain.player.api.dto.UpdatePlayerDto
import io.mattmusc.domain.player.entity.PlayerEntity
import io.mattmusc.domain.player.repository.PlayerRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
internal open class JpaPlayerService(private val playerRepo: PlayerRepository) : PlayerService
{
	private val log = LoggerFactory.getLogger(JpaPlayerService::class.java)

	override fun retrievePlayer(playerId: Long): PlayerDto?
	{
		log.debug("Retrieving player: $playerId")
		return playerRepo.findById(playerId)
				.map { it.toDto() }
				.orElse(null)
	}

	override fun retrievePlayers(): List<PlayerDto>
	{
		log.debug("Retrieving players")
		return playerRepo.findAll().map { it.toDto() }
	}

	override fun addPlayer(playerDto: CreatePlayerDto): PlayerDto
	{
		log.debug("Adding a new player")
		log.trace("New player data: $playerDto")

		return playerRepo.save(PlayerEntity.fromDto(playerDto)).toDto()
	}

	override fun updatePlayer(id: Long, playerDto: UpdatePlayerDto): PlayerDto?
	{
		log.debug("Updating player with id: $id.")
		log.trace("New player information to update: $playerDto")

		return playerRepo.findById(id)
				.map { playerRepo.save(PlayerEntity.fromDto(playerDto, it)).toDto() }
				.orElse(null)
	}
}