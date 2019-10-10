package me.freedom4live.game.life.javafx.configuration

import me.freedom4live.game.life.javafx.configuration.spring.ObjectsConfiguration
import me.freedom4live.game.life.javafx.configuration.spring.WindowConfiguration
import org.springframework.stereotype.Component
import kotlin.math.roundToInt

@Component
class ConfigurationProviderImpl(private val objectsConfiguration: ObjectsConfiguration,
                                private val windowConfiguration: WindowConfiguration) : ConfigurationProvider {

    override fun squareWeight(): Double = objectsConfiguration.squareWeight.toDouble()

    override fun squareHeight(): Double = objectsConfiguration.squareHeight.toDouble()

    override fun squareBorderSize(): Double = objectsConfiguration.squareBorderSize.toDouble()

    override fun windowTitle(): String = windowConfiguration.title

    override fun windowHeight(): Double = windowConfiguration.height.toDouble()

    override fun windowWeight(): Double = windowConfiguration.weight.toDouble()

    override fun horizontalFieldSize(): Int = (windowWeight() / squareWeight()).roundToInt()

    override fun verticalFieldSize(): Int = (windowHeight() / squareHeight()).roundToInt()

}