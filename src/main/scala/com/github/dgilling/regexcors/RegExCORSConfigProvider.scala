package com.github.dgilling.regexcors

import javax.inject.{Inject, Provider}
import play.api.Configuration
import play.filters.cors.CORSConfig

class RegExCORSConfigProvider @Inject()(configuration: Configuration) extends Provider[CORSConfig] {
  lazy val get = RegExCORSConfig.fromConfiguration(configuration)
}
