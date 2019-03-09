package io.mattmusc.web.resource

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.mattmusc.domain.player.api.dto.PlayerDto
import org.springframework.hateoas.ResourceSupport

data class PlayerResource
@JsonCreator
constructor(
		@JsonProperty("id") val _id: String,
		@JsonProperty("name") val name: String,
		@JsonProperty("age") val age: Int,
		@JsonProperty("clubName") val clubName: String? = "",
		@JsonProperty("clubLogoUrl") val clubLogoUrl: String? = "",
		@JsonProperty("nationality") val nationality: String,
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
						_id = dto.id.toString(),
						name = dto.name,
						age = dto.age,
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