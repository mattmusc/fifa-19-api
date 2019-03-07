package io.mattmusc.domain.player.api.dto

import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.player.api.PlayerFoot
import java.time.LocalDateTime

data class PlayerDto(
		var id: Long,
		var name: String,
		val age: Int,
		val photoUrl: String? = null,
		var country: CountryDto,
		val overall: Int,
		var club: ClubDto,
		var marketValue: Double,
		var wage: Double,
		var foot: PlayerFoot,
		var updatedAt: LocalDateTime,
		var createdAt: LocalDateTime)