package io.mattmusc.domain.player

import io.mattmusc.domain.player.entity.PlayerEntity
import io.mattmusc.domain.player.repository.PlayerRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = [PlayerRepository::class])
@EntityScan(basePackageClasses = [PlayerEntity::class])
@EnableTransactionManagement
internal open class InternalPlayerConfig