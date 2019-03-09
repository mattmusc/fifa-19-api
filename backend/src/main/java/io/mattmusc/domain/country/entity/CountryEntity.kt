package io.mattmusc.domain.country.entity

import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.country.api.dto.CreateCountryDto
import io.mattmusc.domain.country.api.dto.UpdateCountryDto
import javax.persistence.*

@Entity
@Table(name = "country")
internal data class CountryEntity(
		@Id @Column(name = "country_id") @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
		val name: String,
		val flagUrl: String? = ""
)
{
	constructor() : this(name = "", flagUrl = "")

	fun toDto(): CountryDto = CountryDto(
			id = this.id!!,
			name = this.name,
			flagUrl = this.flagUrl
	)

	companion object
	{
		fun fromDto(dto: CountryDto): CountryEntity =
				CountryEntity(name = dto.name, flagUrl = dto.flagUrl)

		fun fromDto(dto: CreateCountryDto): CountryEntity =
				CountryEntity(name = dto.name, flagUrl = dto.flagUrl)

		fun fromDto(dto: UpdateCountryDto, savedCountry: CountryEntity): CountryEntity =
				CountryEntity(
						id = savedCountry.id!!,
						name = dto.name,
						flagUrl = dto.flagUrl)
	}
}