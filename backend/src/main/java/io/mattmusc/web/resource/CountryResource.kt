package io.mattmusc.web.resource

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.mattmusc.domain.country.api.dto.CountryDto

@JsonSerialize(using = CountrySerializer::class)
@JsonDeserialize(using = CountryDeserializer::class)
data class CountryResource(
		val name: String,
		val flagUrl: String)
{
	companion object
	{
		fun fromDto(dto: CountryDto): CountryResource = CountryResource(
				name = dto.name,
				flagUrl = dto.flagUrl
		)
	}
}

class CountrySerializer : JsonSerializer<CountryResource>()
{

	override fun serialize(
			value: CountryResource?,
			gen: JsonGenerator?,
			serializers: SerializerProvider?)
	{
		if (gen != null && value != null)
		{
			gen.writeStartObject()
			gen.writeStringField("name", value.name)
			gen.writeStringField("flag", value.flagUrl)
			gen.writeEndObject()
		}
	}

}

class CountryDeserializer : JsonDeserializer<CountryResource?>()
{
	override fun deserialize(
			p: JsonParser?,
			ctxt: DeserializationContext?): CountryResource?
	{
		if (p != null && ctxt != null)
		{
			val node: JsonNode = p.codec.readTree(p)
			return CountryResource(
					name = node.get("name").asText(),
					flagUrl = node.get("flag").asText())
		} else
		{
			return null
		}
	}

}