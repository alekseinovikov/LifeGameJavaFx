package me.freedom4live.game.life.javafx.application

import javafx.application.Application
import javafx.stage.Stage
import me.freedom4live.game.life.javafx.engine.GameEngine
import me.freedom4live.game.life.javafx.fx.DrawEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
open class ApplicationRunner : CommandLineRunner, Application() {

    @Autowired
    lateinit var drawEngineInjection: DrawEngine

    @Autowired
    lateinit var gameEngineInjection: GameEngine

    companion object {
        lateinit var drawEngine: DrawEngine
        lateinit var gameEngine: GameEngine
    }

    override fun start(primaryStage: Stage?) {
        drawEngine.start(primaryStage!!)
        gameEngine.run(drawEngine)
    }

    override fun run(vararg args: String?) {
        drawEngine = drawEngineInjection
        gameEngine = gameEngineInjection
        launch(*args)
    }

}