package io.mattmusc.domain.club

import io.mattmusc.domain.club.api.ClubService
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
}
