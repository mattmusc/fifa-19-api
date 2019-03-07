package io.mattmusc.domain.country.repository

import io.mattmusc.domain.country.entity.CountryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
internal interface CountryRepository : JpaRepository<CountryEntity, Long>
