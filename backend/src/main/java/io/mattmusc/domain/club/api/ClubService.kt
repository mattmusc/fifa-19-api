package io.mattmusc.domain.club.api

import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.club.api.dto.CreateClubDto
import io.mattmusc.domain.club.api.dto.UpdateClubDto

interface ClubService
{
	fun retrieveClub(countryId: Long): ClubDto?

	fun retrieveClubs(): List<ClubDto>

	fun addClub(createClubDto: CreateClubDto): ClubDto

	fun updateClub(id: Long, updateClubDto: UpdateClubDto): ClubDto?
}