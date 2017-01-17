package com.github.dgilling.regexcors

import akka.stream.Materializer
import play.api.Logger
import play.api.http.{DefaultHttpErrorHandler, HttpErrorHandler}
import play.api.mvc.{Result, RequestHeader, Filter}
import play.core.j.JavaHttpErrorHandlerAdapter
import play.filters.cors.{CORSFilter, AbstractCORSPolicy, CORSConfig}

import scala.concurrent.Future

/**
  * Created by derric on 1/16/17.
  */
class RegExCORSFilter(
                  override protected val corsConfig: CORSConfig = CORSConfig(),
                  override protected val errorHandler: HttpErrorHandler = DefaultHttpErrorHandler,
                  private val pathPrefixes: Seq[String] = Seq("/"),
                  private val ignoredPathPrefixes: Seq[String] = Seq.empty[String])(override implicit val mat: Materializer)
  extends CORSFilter {

  // Java constructor
  def this(corsConfig: CORSConfig, errorHandler: play.http.HttpErrorHandler, pathPrefixes: java.util.List[String])(mat: Materializer) = {
    this(corsConfig, new JavaHttpErrorHandlerAdapter(errorHandler), Seq(pathPrefixes.toArray.asInstanceOf[Array[String]]: _*))(mat)
  }

  override protected val logger = Logger(classOf[CORSFilter])

  override def apply(f: RequestHeader => Future[Result])(request: RequestHeader): Future[Result] = {
    if (pathPrefixes.exists(request.path.startsWith) &&
      !ignoredPathPrefixes.exists(request.path.startsWith)) {
      filterRequest(f, request)
    } else {
      f(request)
    }
  }
}

object RegExCORSFilter {

  val RequestTag = "CORS_REQUEST"

  def apply(corsConfig: CORSConfig = CORSConfig(), errorHandler: HttpErrorHandler = DefaultHttpErrorHandler,
            pathPrefixes: Seq[String] = Seq("/"))(implicit mat: Materializer) =
    new CORSFilter(corsConfig, errorHandler, pathPrefixes)

}