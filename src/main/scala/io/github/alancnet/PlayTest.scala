package io.github.alancnet

import java.io.File

import play.api.{Environment, ApplicationLoader}
import play.api.mvc.{RequestHeader, Result}
import play.core.{SourceMapper, WebCommands, HandleWebCommandSupport, BuildLink}

object PlayTest {
  class Dummy{}
  def main(args:Array[String]) = {
    def startWebServer = {
      val environment = new Environment(
        new File("."),
        classOf[Dummy].getClassLoader,
        play.api.Mode.Dev
      )
      val sourceMapper: Option[SourceMapper] = None
      val webCommands = new WebCommands {
        override def addHandler(handler: HandleWebCommandSupport): Unit = {}

        override def handleWebCommand(request: RequestHeader, buildLink: BuildLink, path: File): Option[Result] = None
      }

      val context = play.api.ApplicationLoader.createContext(environment)
      val application = ApplicationLoader(context).load(context)

      play.api.Play.start(application)


    }

    startWebServer

  }
}
