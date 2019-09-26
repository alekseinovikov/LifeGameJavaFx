package me.freedom4live.game.life.javafx.application

import javafx.application.Application
import javafx.stage.Stage
import me.freedom4live.game.life.javafx.application.creator.Engine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class ApplicationRunner : CommandLineRunner, Application() {

    @Autowired
    lateinit var engineInjection: Engine

    companion object {
        lateinit var engine: Engine
    }

    override fun start(primaryStage: Stage?) {
        engine.start(primaryStage!!)
    }

    override fun run(vararg args: String?) {
        engine = engineInjection
        launch(*args)
    }

}