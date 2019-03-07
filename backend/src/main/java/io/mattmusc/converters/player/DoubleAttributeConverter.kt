package io.mattmusc.converters.player

import java.math.BigDecimal
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class DoubleAttributeConverter : AttributeConverter<Double, BigDecimal?>
{
	override fun convertToDatabaseColumn(attribute: Double?) =
			if (attribute != null) BigDecimal(attribute) else null

	override fun convertToEntityAttribute(dbData: BigDecimal?) =
			dbData?.toDouble()
}