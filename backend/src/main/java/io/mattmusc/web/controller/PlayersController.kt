package io.mattmusc.web.controller

import io.mattmusc.domain.player.api.PlayerService
import io.mattmusc.domain.player.api.dto.CreatePlayerDto
import io.mattmusc.domain.player.api.dto.UpdatePlayerDto
import io.mattmusc.logger
import io.mattmusc.web.PLAYERS_PATH
import io.mattmusc.web.resource.PlayerResource
import org.springframework.hateoas.Resources
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

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
		return if (result == null)
		{
			ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		} else
		{
			val resource = PlayerResource.fromDto(result)
			ResponseEntity.ok(resource)
		}
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	fun addPoi(@RequestBody player: CreatePlayerDto, uriBuilder: UriComponentsBuilder): HttpEntity<PlayerResource>
	{
		log.debug("Request to add a player")

		val result = playerService.addPlayer(player)
		val resource = PlayerResource.fromDto(result)
		return ResponseEntity
				.created(uriBuilder.path("$PLAYERS_PATH/{id}").buildAndExpand(result.id).toUri())
				.body(resource)
	}

	@PutMapping("{id}")
	fun updatePlayer(@PathVariable("id") playerId: String, @RequestBody city: UpdatePlayerDto): HttpEntity<PlayerResource>
	{
		log.debug("Request to update player: {}", playerId)

		val result = playerService.updatePlayer(playerId.toLong(), city)
		return if (result == null)
		{
			ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		} else
		{
			val resource = PlayerResource.fromDto(result)
			ResponseEntity.ok(resource)
		}
	}
}