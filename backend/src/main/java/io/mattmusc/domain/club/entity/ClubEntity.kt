package io.mattmusc.domain.club.entity

import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.club.api.dto.CreateClubDto
import io.mattmusc.domain.club.api.dto.UpdateClubDto
import javax.persistence.*

@Entity
@Table(name = "club")
internal data class ClubEntity(
		@Id @Column(name = "club_id") @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
		val name: String,
		val logoUrl: String
)
{
	constructor() : this(name = "", logoUrl = "")

	fun toDto(): ClubDto = ClubDto(
			id = this.id!!,
			name = this.name,
			logoUrl = this.logoUrl)

	companion object
	{
		fun fromDto(dto: ClubDto?): ClubEntity =
				ClubEntity(name = dto?.name ?: "", logoUrl = dto?.logoUrl ?: "")

		fun fromDto(dto: CreateClubDto): ClubEntity =
				ClubEntity(name = dto.name, logoUrl = dto.logoUrl)

		fun fromDto(dto: UpdateClubDto, savedClub: ClubEntity): ClubEntity =
				ClubEntity(
						id = savedClub.id,
						name = dto.name,
						logoUrl = dto.logoUrl)
	}
}