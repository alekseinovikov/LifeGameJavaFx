package me.freedom4live.game.life.javafx.application.creator

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.shape.Rectangle
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
        createSquares(group)
        //testSquares(group)
    }

    private fun createAndConfigureRootScene(group: Group): Scene {
        return Scene(group, windowWeight, windowHeight)
    }

    private fun configureAndRunMainStage(stage: Stage, scene: Scene) {
        stage.scene = scene
        stage.title = windowTitle

        stage.show()
    }

    private fun testSquares(group: Group) {
        val r1 = Rectangle(10.0, 10.0, 10.0, 10.0)
        val r2 = Rectangle(100.0, 100.0, 10.0, 10.0)

        group.children.add(r1)
        group.children.add(r2)
    }

    private fun createSquares(group: Group) {
        var x = 0
        var y = 0

        for (w in 0..weightSquareCount) {
            for (h in 0..heightSquareCount) {
                val rectangle = Rectangle(x.toDouble(), y.toDouble(), squareWeight-1, squareHeight-1)

                group.children.add(rectangle)
                y += squareHeight.toInt()
            }

            x += squareWeight.toInt()
            y = 0
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