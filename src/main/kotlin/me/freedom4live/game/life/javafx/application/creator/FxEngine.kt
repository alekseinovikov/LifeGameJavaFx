package me.freedom4live.game.life.javafx.application.creator

import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.Stage
import me.freedom4live.game.life.javafx.configuration.ConfigurationProvider
import org.springframework.stereotype.Component
import kotlin.math.roundToInt

@Component
class FxEngine(private val configurationProvider: ConfigurationProvider) : Engine {

    override fun start(stage: Stage) {
        val group = Group()
        val rootScene = createAndConfigureRootScene(group)
        configureAndRunMainStage(stage, rootScene)
    }

    private fun createAndConfigureRootScene(group: Group): Scene {
        return Scene(group, windowWeight, windowHeight)
    }

    private fun configureAndRunMainStage(stage: Stage, scene: Scene) {
        stage.scene = scene
        stage.title = windowTitle

        stage.show()
    }

    private fun createSquares(group: Group) {
        var x = 0
        var y = 0

        for (w in 0..weightSquareCount) {
            for (h in 0..heightSquareCount) {
                group.

                y += squareHeight.toInt()
            }

            x += squareWeight.toInt()
        }
    }

    private val heightSquareCount: Int
        get() = (windowHeight / squareHeight).roundToInt()

    private val weightSquareCount: Int
        get() = (windowWeight / squareWeight).roundToInt()

    private val windowTitle: String
        get() = configurationProvider.windowTitle()

    private val windowHeight: Double
        get() = configurationProvider.windowHeight()

    private val windowWeight: Double
        get() = configurationProvider.windowWeight()

    private val squareHeight: Double
        get() = configurationProvider.squareHeight()

    private val squareWeight: Double
        get() = configurationProvider.squareWeight()

}