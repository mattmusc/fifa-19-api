package io.mattmusc.domain.player.entity

import io.mattmusc.converters.player.DoubleAttributeConverter
import io.mattmusc.converters.player.PlayerFootAttributeConverter
import io.mattmusc.domain.club.entity.ClubEntity
import io.mattmusc.domain.country.entity.CountryEntity
import io.mattmusc.domain.player.api.PlayerFoot
import io.mattmusc.domain.player.api.dto.PlayerDto
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "player")
internal data class PlayerEntity(
		@Id @Column(name = "player_id") val id: Long? = null,
		val name: String,
		val age: Int,
		val photoUrl: String? = null,
		@ManyToOne @JoinColumn(name = "id_country", referencedColumnName = "country_id") val countryEntity: CountryEntity,
		val overall: Int,
		@ManyToOne @JoinColumn(name = "id_club", referencedColumnName = "club_id") val clubEntity: ClubEntity,
		@Convert(converter = DoubleAttributeConverter::class) val marketValue: Double,
		@Convert(converter = DoubleAttributeConverter::class) val wage: Double,
		@Convert(converter = PlayerFootAttributeConverter::class) val foot: PlayerFoot,
		@Column(name = "updated_at") val updatedAt: LocalDateTime = LocalDateTime.now(),
		@Column(name = "created_at") val createdAt: LocalDateTime = LocalDateTime.now())
{
	constructor() : this(name = "",
			age = 0,
			countryEntity = CountryEntity(name = "", flagUrl = ""),
			overall = 0,
			clubEntity = ClubEntity(name = "", logoUrl = ""),
			marketValue = 0.0,
			wage = 0.0,
			foot = PlayerFoot.UNKNOWN)

	fun toDto(): PlayerDto = PlayerDto(
			id = this.id!!,
			name = this.name,
			age = this.age,
			photoUrl = this.photoUrl,
			country = this.countryEntity.toDto(),
			overall = this.overall,
			club = this.clubEntity.toDto(),
			marketValue = this.marketValue,
			wage = this.wage,
			foot = this.foot,
			updatedAt = this.updatedAt,
			createdAt = this.createdAt
	)

	companion object
	{
		fun fromDto(dto: PlayerDto) = PlayerEntity(
				id = dto.id,
				name = dto.name,
				age = dto.age,
				photoUrl = dto.photoUrl,
				countryEntity = CountryEntity.fromDto(dto.country),
				overall = dto.overall,
				clubEntity = ClubEntity.fromDto(dto.club),
				marketValue = dto.marketValue,
				wage = dto.wage,
				foot = dto.foot,
				updatedAt = dto.updatedAt,
				createdAt = dto.createdAt)
	}

}