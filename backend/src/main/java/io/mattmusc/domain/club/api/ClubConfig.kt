package io.mattmusc.domain.club.api

import io.mattmusc.domain.club.InternalClubConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [InternalClubConfig::class])
open class ClubConfig