package me.freedom4live.game.life.javafx.engine

import me.freedom4live.game.life.javafx.fx.DrawEngine

interface GameEngine {
    fun run(drawEngine: DrawEngine)
}