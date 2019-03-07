package io.mattmusc.domain.country.service

import io.mattmusc.domain.country.api.CountryService
import io.mattmusc.domain.country.api.dto.CountryDto
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
}