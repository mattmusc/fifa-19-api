package io.mattmusc.web.resource

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.mattmusc.domain.club.api.dto.ClubDto

@JsonSerialize(using = ClubSerializer::class)
@JsonDeserialize(using = ClubDeserializer::class)
data class ClubResource(
		val name: String,
		val logoUrl: String)
{
	companion object
	{
		fun fromDto(dto: ClubDto): ClubResource = ClubResource(
				name = dto.name,
				logoUrl = dto.logoUrl
		)
	}
}

class ClubSerializer : JsonSerializer<ClubResource>()
{

	override fun serialize(
			value: ClubResource?,
			gen: JsonGenerator?,
			serializers: SerializerProvider?)
	{
		if (gen != null && value != null)
		{
			gen.writeStartObject()
			gen.writeStringField("name", value.name)
			gen.writeStringField("logo", value.logoUrl)
			gen.writeEndObject()
		}
	}

}

class ClubDeserializer : JsonDeserializer<ClubResource?>()
{
	override fun deserialize(
			p: JsonParser?,
			ctxt: DeserializationContext?): ClubResource?
	{
		return if (p != null && ctxt != null)
		{
			val node: JsonNode = p.codec.readTree(p)
			ClubResource(
					name = node.get("name").asText(),
					logoUrl = node.get("logo").asText())
		} else
		{
			null
		}
	}

}