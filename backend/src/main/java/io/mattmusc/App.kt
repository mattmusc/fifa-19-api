package io.mattmusc

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class App

fun main(args: Array<String>)
{
	runApplication<App>(*args)
}

inline fun <reified T> logger(): Logger
{
	return LoggerFactory.getLogger(T::class.java)
}