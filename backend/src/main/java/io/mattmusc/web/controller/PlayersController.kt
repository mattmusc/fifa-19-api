package io.mattmusc.web.controller

import io.mattmusc.domain.player.api.PlayerService
import io.mattmusc.logger
import io.mattmusc.web.PLAYERS_PATH
import io.mattmusc.web.resource.PlayerResource
import org.springframework.hateoas.Resources
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This controller exposes a CRUD api to the Players Resource.
 *
 * @author mattmusc
 */
@RestController
@RequestMapping(
		value = [PLAYERS_PATH],
		produces = [MediaType.APPLICATION_JSON_VALUE])
open class PlayersController(private val playerService: PlayerService)
{
	private val log = logger<PlayersController>()

	@GetMapping
	fun retrievePlayers(): HttpEntity<Resources<PlayerResource>>
	{
		log.debug("Retrieving players")

		val result = playerService.retrievePlayers()
		return ResponseEntity.ok(Resources(result.map { PlayerResource.fromDto(it) }))
	}

	@GetMapping("{id}")
	fun retrievePlayer(@PathVariable("id") playerId: String): HttpEntity<PlayerResource>
	{
		log.debug("Retrieving player: {}", playerId)

		val result = playerService.retrievePlayer(playerId.toLong())
		if (result != null)
		{
			val resource = PlayerResource.fromDto(result)
			return ResponseEntity.ok(resource)
		} else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}
}