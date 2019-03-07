package io.mattmusc.domain.club.api.dto

data class ClubDto(val name: String, val logoUrl: String) {
	companion object {
		fun empty() = ClubDto(name = "", logoUrl = "")
	}
}