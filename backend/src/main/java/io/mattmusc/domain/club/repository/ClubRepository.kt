package io.mattmusc.domain.club.repository

import io.mattmusc.domain.club.entity.ClubEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
internal interface ClubRepository : JpaRepository<ClubEntity, Long>
