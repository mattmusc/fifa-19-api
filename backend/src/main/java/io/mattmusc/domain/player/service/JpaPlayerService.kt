package io.mattmusc.domain.player.service

import io.mattmusc.domain.player.api.PlayerService
import io.mattmusc.domain.player.api.dto.PlayerDto
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
		log.debug("Retrieving player: {}", playerId)
		return playerRepo.findById(playerId)
				.map { it.toDto() }
				.orElse(null)
	}

	override fun retrievePlayers(): List<PlayerDto>
	{
		log.debug("Retrieving Points of Interest")
		return playerRepo.findAll().map { it.toDto() }
	}
}