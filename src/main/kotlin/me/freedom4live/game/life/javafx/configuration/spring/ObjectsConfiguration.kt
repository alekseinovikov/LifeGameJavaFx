package me.freedom4live.game.life.javafx.configuration.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "objects")
@Component
class ObjectsConfiguration {

    lateinit var squareWeight: Integer

    lateinit var squareHeight: Integer

    lateinit var squareBorderSize: Integer

}