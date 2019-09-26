package me.freedom4live.game.life.javafx.objects

abstract class Coordinates(val x: Double, val y: Double)

abstract class BaseObject(var coordinates: Coordinates) {
    abstract fun draw()
}

abstract class Square(coordinates: Coordinates, val height: Double, val weight: Double) : BaseObject(coordinates)