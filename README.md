# play-regex-cors

[![Release](https://jitpack.io/v/dgilling/play-regex-cors.svg)](https://jitpack.io/#dgilling/play-regex-cors)

Drop-in replacement for the built-in CORS Filter that's bundled with Play Framework 2.X
The only difference is that this filter supports __regex__ for the allowedOrigins.
Useful if you need to decide CORS dynamically

## Usage

Add dependency to your build.sbt

```scala
    resolvers += "jitpack" at "https://jitpack.io"

	libraryDependencies += "com.github.dgilling" % "play-regex-cors" % "0.1.0"	    
```

In your application.conf:

1. Disable the built-in CORS Module and add this module:

```yaml
play.modules.disabled += "play.filters.cors.CORSModule"
play.modules.enabled += "com.github.dgilling.regexcors.RegExCORSModule"

```

2. Define your CORS settings:

```yaml

play.filters {

  # CORS filter configuration
  cors {

    # The path prefixes to filter.
    pathPrefixes = ["/"]

    # The allowed origins. If null, all origins are allowed.
    allowedOrigins = [
      """https://.*\.example\.com""",
      """http://localhost(:\d*)?"""
    ]

    # The allowed HTTP methods. If null, all methods are allowed
    allowedHttpMethods = null

    # The allowed HTTP headers. If null, all headers are allowed.
    allowedHttpHeaders = null

    # The exposed headers
    exposedHeaders = ["Content-Length", "Date"]

    # Whether to support credentials
    supportsCredentials = false

    # The maximum amount of time the CORS meta data should be cached by the client
    preflightMaxAge = 3 days
  }
 }

```

## License
[MIT](LICENSE)
