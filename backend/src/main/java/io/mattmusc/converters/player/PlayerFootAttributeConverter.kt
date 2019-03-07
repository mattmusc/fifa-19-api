package io.mattmusc.converters.player

import io.mattmusc.domain.player.api.PlayerFoot
import javax.persistence.AttributeConverter

class PlayerFootAttributeConverter : AttributeConverter<PlayerFoot, String>
{
	override fun convertToDatabaseColumn(p0: PlayerFoot?): String
	{
		return when (p0)
		{
			PlayerFoot.LEFT -> "L"
			PlayerFoot.RIGHT -> "R"
			PlayerFoot.UNKNOWN -> ""
			else -> ""
		}
	}

	override fun convertToEntityAttribute(p0: String?): PlayerFoot
	{
		return when (p0)
		{
			"L" -> PlayerFoot.LEFT
			"R" -> PlayerFoot.RIGHT
			"" -> PlayerFoot.UNKNOWN
			else -> PlayerFoot.UNKNOWN
		}
	}

}