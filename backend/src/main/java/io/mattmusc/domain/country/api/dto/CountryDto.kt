package io.mattmusc.domain.country.api.dto

data class CountryDto(val name: String, val flagUrl: String) {
	companion object {
		fun empty() = CountryDto(name = "", flagUrl = "")
	}
}