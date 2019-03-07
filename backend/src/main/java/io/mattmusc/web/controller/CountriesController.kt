package io.mattmusc.web.controller

import io.mattmusc.domain.country.api.CountryService
import io.mattmusc.logger
import io.mattmusc.web.COUNTRIES_PATH
import io.mattmusc.web.resource.CountryResource
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
 * This controller exposes a CRUD api to the Countries Resource.
 *
 * @author mattmusc
 */
@RestController
@RequestMapping(
		value = [COUNTRIES_PATH],
		produces = [MediaType.APPLICATION_JSON_VALUE])
open class CountriesController(private val countryService: CountryService)
{
	private val log = logger<CountriesController>()

	@GetMapping
	fun retrieveCountries(): HttpEntity<Resources<CountryResource>>
	{
		log.debug("Retrieving countries")

		val result = countryService.retrieveCountries()
		return ResponseEntity.ok(Resources(result.map { CountryResource.fromDto(it) }))
	}

	@GetMapping("{id}")
	fun retrieveCountry(@PathVariable("id") clubId: String): HttpEntity<CountryResource>
	{
		log.debug("Retrieving country: {}", clubId)

		val result = countryService.retrieveCountry(clubId.toLong())
		if (result != null)
		{
			val resource = CountryResource.fromDto(result)
			return ResponseEntity.ok(resource)
		} else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}
}