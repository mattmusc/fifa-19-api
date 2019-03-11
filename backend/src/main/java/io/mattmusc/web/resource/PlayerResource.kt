package io.mattmusc.web.resource

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.mattmusc.domain.player.api.dto.PlayerDto
import org.springframework.hateoas.ResourceSupport

data class PlayerResource
@JsonCreator
constructor(
		@JsonProperty("id") val _id: Long,
		@JsonProperty("name") val name: String,
		@JsonProperty("age") val age: Int,
		@JsonProperty("photoUrl") val photoUrl: String? = "",
		@JsonProperty("clubName") val clubName: String? = "",
		@JsonProperty("clubLogoUrl") val clubLogoUrl: String? = "",
		@JsonProperty("country") val nationality: String,
		@JsonProperty("flagUrl") val flagUrl: String? = "",
		@JsonProperty("overall") val overall: Int?,
		@JsonProperty("marketValue") val marketValue: Double?,
		@JsonProperty("wage") val wage: Double?,
		@JsonProperty("foot") val foot: String)
	: ResourceSupport()
{
	// this is used to add static member
	companion object
	{
		fun fromDto(dto: PlayerDto): PlayerResource =
				PlayerResource(
						_id = dto.id,
						name = dto.name,
						age = dto.age,
						photoUrl = dto.photoUrl,
						clubName = dto.club?.name,
						clubLogoUrl = dto.club?.logoUrl,
						nationality = dto.country.name,
						flagUrl = dto.country.flagUrl,
						overall = dto.overall,
						marketValue = dto.marketValue,
						wage = dto.wage,
						foot = dto.foot.toString()
				)
	}
}