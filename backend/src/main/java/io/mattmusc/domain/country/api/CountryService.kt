package io.mattmusc.domain.country.api

import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.country.api.dto.CreateCountryDto
import io.mattmusc.domain.country.api.dto.UpdateCountryDto

interface CountryService
{
	fun retrieveCountry(countryId: Long): CountryDto?

	fun retrieveCountries(): List<CountryDto>

	fun addCountry(countryDto: CreateCountryDto): CountryDto

	fun updateCountry(id: Long, countryDto: UpdateCountryDto): CountryDto?
}