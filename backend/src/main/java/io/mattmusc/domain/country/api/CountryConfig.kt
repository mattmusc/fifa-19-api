package io.mattmusc.domain.country.api

import io.mattmusc.domain.club.InternalClubConfig
import io.mattmusc.domain.country.InternalCountryConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [InternalCountryConfig::class])
open class CountryConfig