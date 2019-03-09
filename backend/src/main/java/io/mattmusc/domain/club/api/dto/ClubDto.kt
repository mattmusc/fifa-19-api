package io.mattmusc.domain.club.api.dto

data class ClubDto(var id: Long,
                   var name: String,
                   var logoUrl: String)

data class CreateClubDto(var name: String,
                         var logoUrl: String)

data class UpdateClubDto(var name: String,
                         var logoUrl: String)
