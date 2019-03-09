package io.mattmusc.domain.country.service

import io.mattmusc.domain.country.api.CountryService
import io.mattmusc.domain.country.api.dto.CountryDto
import io.mattmusc.domain.country.api.dto.CreateCountryDto
import io.mattmusc.domain.country.api.dto.UpdateCountryDto
import io.mattmusc.domain.country.entity.CountryEntity
import io.mattmusc.domain.country.repository.CountryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
internal open class JpaCountryService(private val countryRepo: CountryRepository) : CountryService
{
	private val log = LoggerFactory.getLogger(JpaCountryService::class.java)

	override fun retrieveCountry(countryId: Long): CountryDto?
	{
		log.debug("Retrieving club: {}", countryId)
		return countryRepo.findById(countryId)
				.map { it.toDto() }
				.orElse(null)
	}

	override fun retrieveCountries(): List<CountryDto>
	{
		log.debug("Retrieving clubs")
		return countryRepo.findAll().map { it.toDto() }
	}

	override fun addCountry(countryDto: CreateCountryDto): CountryDto
	{
		log.debug("Adding a new country")
		log.trace("New country data: $countryDto")

		return countryRepo.save(CountryEntity.fromDto(countryDto)).toDto()
	}

	override fun updateCountry(id: Long, countryDto: UpdateCountryDto): CountryDto?
	{
		log.debug("Updating country with id: $id.")
		log.trace("New country information to update: $countryDto")

		return countryRepo.findById(id)
				.map { countryRepo.save(CountryEntity.fromDto(countryDto, it)).toDto() }
				.orElse(null)
	}
}