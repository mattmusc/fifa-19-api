package io.mattmusc.domain.country.api

import io.mattmusc.domain.country.api.dto.CountryDto

interface CountryService {
	fun retrieveCountry(countryId: Long): CountryDto?

	fun retrieveCountries(): List<CountryDto>
}