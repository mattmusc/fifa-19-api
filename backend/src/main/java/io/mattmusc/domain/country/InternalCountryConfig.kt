package io.mattmusc.domain.country

import io.mattmusc.domain.country.entity.CountryEntity
import io.mattmusc.domain.country.repository.CountryRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = [CountryRepository::class])
@EntityScan(basePackageClasses = [CountryEntity::class])
@EnableTransactionManagement
internal open class InternalCountryConfig