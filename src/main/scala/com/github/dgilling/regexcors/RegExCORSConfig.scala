package com.github.dgilling.regexcors

import play.api.Configuration
import play.filters.cors.CORSConfig
import play.filters.cors.CORSConfig.Origins

object RegExCORSConfig {

  def fromConfiguration(conf: Configuration): CORSConfig = {
    val baseCORSConfig = CORSConfig.fromConfiguration(conf)
    val allowedOrigins = conf.getStringSeq("play.filters.cors.allowedOrigins").get

    CORSConfig(
      allowedOrigins = Origins.Matching(x => allowedOrigins.exists(a => a.r.findFirstIn(x).nonEmpty)),
      isHttpMethodAllowed = baseCORSConfig.isHttpMethodAllowed,
      isHttpHeaderAllowed = baseCORSConfig.isHttpHeaderAllowed,
      exposedHeaders = baseCORSConfig.exposedHeaders,
      supportsCredentials = baseCORSConfig.supportsCredentials,
      preflightMaxAge = baseCORSConfig.preflightMaxAge)
  }
}