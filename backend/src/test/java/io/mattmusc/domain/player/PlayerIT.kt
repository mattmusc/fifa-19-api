package io.mattmusc.domain.player

import io.mattmusc.domain.country.api.CountryService
import io.mattmusc.domain.country.api.dto.CreateCountryDto
import io.mattmusc.domain.player.api.PlayerService
import io.mattmusc.domain.player.api.dto.CreatePlayerDto
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackages = ["io.mattmusc.domain"])
open class PlayerIT(
		@Autowired val playerService: PlayerService,
		@Autowired val countryService: CountryService
)
{
	@Test
	fun testInit()
	{
		Assertions.assertNotNull(playerService)
		Assertions.assertTrue(playerService.retrievePlayers().isEmpty())
	}

	@Test
	fun testCreateOk()
	{
		val countryName = "Argentina"
		val addedCountry = countryService.addCountry(CreateCountryDto(name = countryName))
		val retrievedCountry = countryService.retrieveCountry(addedCountry.id)

		Assertions.assertNotNull(addedCountry)
		assertThat("The country has name '$countryName'", retrievedCountry?.name, equalTo(countryName))

		val playerName = "Matteo"
		val playerAge = 26
		val addedPlayer = playerService.addPlayer(CreatePlayerDto(name = playerName, countryId = addedCountry.id, age = playerAge))
		val retrievedPlayer = playerService.retrievePlayer(addedPlayer.id)

		Assertions.assertNotNull(addedPlayer)
		assertThat("The saved player name is '$playerName'", retrievedPlayer?.name, equalTo(playerName))
		assertThat("The saved player age is '$playerAge'", retrievedPlayer?.age, equalTo(playerAge))
		assertThat("The saved player country is '$countryName' with id ${retrievedPlayer?.country?.id}",
				retrievedPlayer?.country?.id, equalTo(retrievedCountry?.id))
	}
}
