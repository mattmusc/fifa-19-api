package io.mattmusc.domain.player.api.dto

import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.player.api.PlayerFoot
import java.time.LocalDateTime

data class PlayerDto(
		var id: Long,
		var name: String,
		var age: Int,
		val photoUrl: String? = null,
		var country: CountryDto,
		var overall: Int? = 0,
		var club: ClubDto? = null,
		var marketValue: Double? = 0.0,
		var wage: Double? = 0.0,
		var foot: PlayerFoot? = PlayerFoot.UNKNOWN,
		var updatedAt: LocalDateTime,
		var createdAt: LocalDateTime)