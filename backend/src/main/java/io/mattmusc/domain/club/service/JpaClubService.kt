package io.mattmusc.domain.club.service

import io.mattmusc.domain.club.api.ClubService
import io.mattmusc.domain.club.api.dto.ClubDto
import io.mattmusc.domain.club.api.dto.CreateClubDto
import io.mattmusc.domain.club.api.dto.UpdateClubDto
import io.mattmusc.domain.club.entity.ClubEntity
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

	override fun addClub(createClubDto: CreateClubDto): ClubDto
	{
		log.debug("Adding a new club")
		log.trace("New club data: $createClubDto")

		return clubRepo.save(ClubEntity.fromDto(createClubDto)).toDto()
	}

	override fun updateClub(id: Long, updateClubDto: UpdateClubDto): ClubDto?
	{
		log.debug("Updating club with id: $id.")
		log.trace("New club information to update: $updateClubDto")

		return clubRepo.findById(id)
				.map { clubRepo.save(ClubEntity.fromDto(updateClubDto, it)).toDto() }
				.orElse(null)
	}
}