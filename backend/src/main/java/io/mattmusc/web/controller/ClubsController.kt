package io.mattmusc.web.controller

import io.mattmusc.domain.club.api.ClubService
import io.mattmusc.logger
import io.mattmusc.web.CLUBS_PATH
import io.mattmusc.web.resource.ClubResource
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
 * This controller exposes a CRUD api to the Clubs Resource.
 *
 * @author mattmusc
 */
@RestController
@RequestMapping(
		value = [CLUBS_PATH],
		produces = [MediaType.APPLICATION_JSON_VALUE])
open class ClubsController(private val clubService: ClubService)
{
	private val log = logger<ClubsController>()

	@GetMapping
	fun retrieveClubs(): HttpEntity<Resources<ClubResource>>
	{
		log.debug("Retrieving clubs")

		val result = clubService.retrieveClubs()
		return ResponseEntity.ok(Resources(result.map { ClubResource.fromDto(it) }))
	}

	@GetMapping("{id}")
	fun retrieveClub(@PathVariable("id") clubId: String): HttpEntity<ClubResource>
	{
		log.debug("Retrieving club: {}", clubId)

		val result = clubService.retrieveClub(clubId.toLong())
		if (result != null)
		{
			val resource = ClubResource.fromDto(result)
			return ResponseEntity.ok(resource)
		} else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}
}