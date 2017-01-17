package com.github.dgilling.regexcors

import javax.inject.{Inject, Provider}

import akka.stream.Materializer
import play.api.{PlayConfig, Configuration}
import play.api.http.HttpErrorHandler
import play.filters.cors.{CORSFilter, CORSConfig}

class RegExCORSFilterProvider @Inject() (configuration: Configuration, errorHandler: HttpErrorHandler, corsConfig: CORSConfig,
                                         materializer: Materializer) extends Provider[CORSFilter] {
  lazy val get = {
    val pathPrefixes = configuration.getStringSeq("play.filters.cors.pathPrefixes").getOrElse(Seq("/"))
    val ignoredPathPrefixes = configuration.getStringSeq("play.filters.cors.ignoredPathPrefixes").getOrElse(Seq.empty[String])
    new RegExCORSFilter(corsConfig, errorHandler, pathPrefixes, ignoredPathPrefixes)(materializer)
  }
}