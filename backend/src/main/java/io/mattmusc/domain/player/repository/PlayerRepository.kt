package io.mattmusc.domain.player.repository

import io.mattmusc.domain.player.entity.PlayerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
internal interface PlayerRepository : JpaRepository<PlayerEntity, Long>
