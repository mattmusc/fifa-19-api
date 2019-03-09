package io.mattmusc.domain.country.api.dto

data class CountryDto(
		var id: Long,
		val name: String,
		val flagUrl: String? = "")

data class CreateCountryDto(
		var name: String,
		var flagUrl: String? = ""
)

data class UpdateCountryDto(
		var name: String,
		var flagUrl: String? = ""
)