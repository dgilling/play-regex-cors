package com.github.dgilling.regexcors

import com.google.inject.{AbstractModule, Singleton}
import play.api.{Configuration, Environment}
import play.filters.cors.{CORSFilterProvider, CORSFilter, CORSConfig}

@Singleton
class RegExCORSModule(environment: Environment,
                      configuration: Configuration) extends AbstractModule {

                        def configure() = {

                          bind(classOf[CORSConfig]).toProvider(classOf[RegExCORSConfigProvider])
                          bind(classOf[CORSFilter]).toProvider(classOf[CORSFilterProvider])
                        }
                      }