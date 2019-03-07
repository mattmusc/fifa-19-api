package io.mattmusc.converters

import io.mattmusc.converters.player.DoubleAttributeConverter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class DoubleAttributeConverterTest
{
	private val converter = DoubleAttributeConverter()

	@Test
	fun convertToDatabaseColumn()
	{
		Assertions.assertNull(converter.convertToDatabaseColumn(null))

		val num2convert = converter.convertToDatabaseColumn(5.0)
		Assertions.assertNotNull(num2convert)
		Assertions.assertEquals(BigDecimal(5.0), num2convert)
	}

	@Test
	fun convertToEntityAttribute()
	{
		Assertions.assertNull(converter.convertToEntityAttribute(null))

		val num2convert = converter.convertToEntityAttribute(BigDecimal(5.0))
		Assertions.assertNotNull(num2convert)
		Assertions.assertEquals(5.0, num2convert)
	}
}