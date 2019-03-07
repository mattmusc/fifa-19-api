package io.mattmusc.domain.player.api

import io.mattmusc.domain.player.InternalPlayerConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [InternalPlayerConfig::class])
open class PlayerConfig