package io.mattmusc.domain.player.api.dto

data class CreatePlayerDto(
		var name: String,
		var age: Int,
		var country: String? = null,
		var countryId: Long
)