package io.mattmusc.domain.country.entity

import io.mattmusc.domain.country.api.dto.CountryDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "country")
internal data class CountryEntity(
		@Id @Column(name = "country_id") val id: Long? = null,
		val name: String,
		val flagUrl: String
)
{
	constructor() : this(name = "", flagUrl = "")

	fun toDto(): CountryDto = CountryDto(name = this.name, flagUrl = this.flagUrl)

	companion object
	{
		fun fromDto(dto: CountryDto): CountryEntity =
				CountryEntity(name = dto.name, flagUrl = dto.flagUrl)
	}
}