package me.freedom4live.game.life.javafx.engine

interface Cell {
    fun isAlive(): Boolean

    companion object {
        fun create(alive: Boolean) = CellImpl(alive)
    }
}

interface Field {
    fun getCells(): List<List<Cell>>

    companion object {
        fun create(cells: List<List<Cell>>) = FieldImpl(cells)
    }
}

class CellImpl(private val alive: Boolean) : Cell {
    override fun isAlive() = alive
}

class FieldImpl(private val cells: List<List<Cell>>) : Field {
    override fun getCells(): List<List<Cell>> = cells
}