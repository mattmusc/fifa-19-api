package io.mattmusc.domain.club

import io.mattmusc.domain.club.api.ClubService
import io.mattmusc.domain.club.api.dto.CreateClubDto
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackages = ["io.mattmusc.domain"])
open class ClubIT(
		@Autowired val clubService: ClubService
)
{
	@Test
	fun testInit()
	{
		Assertions.assertNotNull(clubService)
	}

	@Test
	fun testAddClubOk()
	{
		val addedClub = clubService.addClub(CreateClubDto("Ajax", "http://logos.com/clubs/11.png"))
		Assertions.assertNotNull(addedClub)

		val retrievedClub = clubService.retrieveClub(addedClub.id)
		Assertions.assertNotNull(retrievedClub)

		assertThat("Saved Club has id = ${addedClub.id}",
				addedClub.id,
				equalTo(retrievedClub?.id))

		assertThat("Saved Club has name = ${addedClub.name}",
				addedClub.name,
				equalTo(retrievedClub?.name))

		assertThat("Saved Club has logoUrl = ${addedClub.logoUrl}",
				addedClub.logoUrl,
				equalTo(retrievedClub?.logoUrl))
	}
}
