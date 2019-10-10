package me.freedom4live.game.life.javafx.configuration

interface ConfigurationProvider {

    fun squareWeight(): Double

    fun squareHeight(): Double

    fun squareBorderSize(): Double

    fun windowTitle(): String

    fun windowHeight(): Double

    fun windowWeight(): Double

    fun horizontalFieldSize(): Int

    fun verticalFieldSize(): Int

}