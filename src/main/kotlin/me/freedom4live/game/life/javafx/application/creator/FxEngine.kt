package me.freedom4live.game.life.javafx.application.creator

import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import me.freedom4live.game.life.javafx.configuration.ConfigurationProvider
import org.springframework.stereotype.Component

@Component
class FxEngine(private val configurationProvider: ConfigurationProvider) : Engine {

    override fun start(mainStage: Stage) {
        val rootScene = createAndConfigureRootScene()
        configureAndRunMainStage(mainStage, rootScene)
    }

    private fun createAndConfigureRootScene(): Scene {
        val rootPane = StackPane()
        return Scene(rootPane, windowWeight, windowHeight)
    }

    private fun configureAndRunMainStage(stage: Stage, scene: Scene) {
        stage.scene = scene
        stage.title = windowTitle

        stage.show()
    }

    private val windowTitle: String
        get() = configurationProvider.windowTitle()

    private val windowHeight: Double
        get() = configurationProvider.windowHeight()

    private val windowWeight: Double
        get() = configurationProvider.windowWeight()

}