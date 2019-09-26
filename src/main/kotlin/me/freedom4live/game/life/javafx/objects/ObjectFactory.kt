package me.freedom4live.game.life.javafx.objects

interface ObjectFactory {

    fun createSquare(coordinates: Coordinates): Square

}