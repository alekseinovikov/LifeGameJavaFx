package me.freedom4live.game.life.javafx.engine.impl

import me.freedom4live.game.life.javafx.engine.Cell
import me.freedom4live.game.life.javafx.engine.Field
import me.freedom4live.game.life.javafx.engine.GameEngine
import me.freedom4live.game.life.javafx.fx.DrawEngine
import org.springframework.stereotype.Component

@Component
class GameEngineImpl : GameEngine {

    override fun run(drawEngine: DrawEngine) {
        val cell = CellImpl(true)
        val deadCell = CellImpl(false)
        val fieldToDraw = object : Field {
            override fun getCells(): List<List<Cell>> {
                return listOf(listOf(deadCell, cell), listOf(cell, deadCell))
            }
        }

        drawEngine.draw(fieldToDraw)
    }

}

class CellImpl(private val alive: Boolean) : Cell {
    override fun isAlive(): Boolean {
        return alive
    }
}