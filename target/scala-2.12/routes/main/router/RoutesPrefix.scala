
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/rdgary/Projects/example/play-scala-starter-example/conf/routes
// @DATE:Wed Jul 19 14:47:10 CEST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
