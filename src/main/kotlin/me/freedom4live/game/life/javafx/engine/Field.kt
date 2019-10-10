package me.freedom4live.game.life.javafx.engine

interface Cell {
    fun isAlive(): Boolean
}

interface Field {
    fun getCells(): List<List<Cell>>
}