package me.freedom4live.game.life.javafx.fx

import me.freedom4live.game.life.javafx.configuration.ConfigurationProvider
import me.freedom4live.game.life.javafx.objects.Coordinates
import me.freedom4live.game.life.javafx.objects.ObjectFactory
import me.freedom4live.game.life.javafx.objects.Square
import org.springframework.stereotype.Component


@Component
class FxFactory(private val configurationProvider: ConfigurationProvider) : ObjectFactory {

    override fun createSquare(coordinates: Coordinates): Square = FxSquare(coordinates, configurationProvider.squareHeight(), configurationProvider.squareWeight())

}