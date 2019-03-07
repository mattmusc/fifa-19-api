package io.mattmusc.domain.club

import io.mattmusc.domain.club.entity.ClubEntity
import io.mattmusc.domain.club.repository.ClubRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = [ClubRepository::class])
@EntityScan(basePackageClasses = [ClubEntity::class])
@EnableTransactionManagement
internal open class InternalClubConfig