package io.mattmusc.domain.club.service

import io.mattmusc.domain.club.api.ClubService
import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.club.repository.ClubRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
internal open class JpaClubService(private val clubRepo: ClubRepository) : ClubService
{
	private val log = LoggerFactory.getLogger(JpaClubService::class.java)

	override fun retrieveClub(countryId: Long): ClubDto?
	{
		log.debug("Retrieving club: {}", countryId)
		return clubRepo.findById(countryId)
				.map { it.toDto() }
				.orElse(null)
	}

	override fun retrieveClubs(): List<ClubDto>
	{
		log.debug("Retrieving clubs")
		return clubRepo.findAll().map { it.toDto() }
	}
}