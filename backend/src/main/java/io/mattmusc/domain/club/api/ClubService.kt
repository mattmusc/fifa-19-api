package io.mattmusc.domain.club.api

import io.mattmusc.domain.club.api.dto.ClubDto

interface ClubService {
	fun retrieveClub(countryId: Long): ClubDto?

	fun retrieveClubs(): List<ClubDto>
}