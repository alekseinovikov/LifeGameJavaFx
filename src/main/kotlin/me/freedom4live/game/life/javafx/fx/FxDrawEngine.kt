package me.freedom4live.game.life.javafx.fx

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import me.freedom4live.game.life.javafx.configuration.ConfigurationProvider
import me.freedom4live.game.life.javafx.engine.Field
import org.springframework.stereotype.Component

@Component
class FxDrawEngine(private val configurationProvider: ConfigurationProvider) : DrawEngine {

    lateinit var squareGroup: Group

    private val WHITE = Color.WHITE
    private val BLACK = Color.BLACK

    override fun start(stage: Stage) {
        val group = Group()
        val rootScene = createAndConfigureRootScene(group)
        configureAndRunMainStage(stage, rootScene)

        squareGroup = group
    }

    override fun draw(field: Field) {
        squareGroup.children.clear()

        var y = 0
        var x = 0
        val squareList = ArrayList<Rectangle>()
        field.getCells().forEach { cellList ->
            cellList.forEach { cell ->
                val square = Rectangle(x.toDouble(), y.toDouble(), squareWeight, squareHeight)

                if (cell.isAlive()) square.fill = BLACK else square.fill = WHITE

                squareList.add(square)

                x += squareWidthOffset.toInt()
            }

            x = 0
            y += squareHeightOffset.toInt()
        }

        squareGroup.children.addAll(squareList)
    }

    private fun createAndConfigureRootScene(group: Group): Scene {
        return Scene(group, windowWeight, windowHeight)
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

    private val squareHeight: Double
        get() = configurationProvider.squareHeight() - configurationProvider.squareBorderSize()

    private val squareWidthOffset: Double
        get() = configurationProvider.squareWeight()

    private val squareHeightOffset: Double
        get() = configurationProvider.squareHeight()

    private val squareWeight: Double
        get() = configurationProvider.squareWeight() - configurationProvider.squareBorderSize()

}