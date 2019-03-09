package io.mattmusc.domain.player.api.dto

import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.player.api.PlayerFoot

data class UpdatePlayerDto(
		val name: String,
		var age: Int,
		val photoUrl: String? = null,
		var country: CountryDto,
		var overall: Int? = 0,
		var club: ClubDto? = null,
		var marketValue: Double?,
		var wage: Double?,
		var foot: PlayerFoot? = PlayerFoot.UNKNOWN
)