package io.mattmusc.domain.club.entity

import io.mattmusc.domain.club.api.dto.ClubDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "club")
internal data class ClubEntity(
		@Id @Column(name = "club_id") val id: Long? = null,
		val name: String,
		val logoUrl: String
)
{
	constructor() : this(name = "", logoUrl = "")

	fun toDto(): ClubDto = ClubDto(name = this.name, logoUrl = this.logoUrl)

	companion object
	{
		fun fromDto(dto: ClubDto?): ClubEntity =
				ClubEntity(name = dto?.name ?: "", logoUrl = dto?.logoUrl ?: "")
	}
}