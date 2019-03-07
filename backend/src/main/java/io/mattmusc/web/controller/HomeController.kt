package io.mattmusc.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
open class MainController
{
	@GetMapping
	fun index(): String = "redirect:/swagger-ui.html"
}

@Controller
@RequestMapping("/api")
open class HomeController
{
	@GetMapping
	fun index(): String = "redirect:/swagger-ui.html"
}