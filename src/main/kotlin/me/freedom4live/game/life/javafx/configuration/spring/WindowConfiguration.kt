package me.freedom4live.game.life.javafx.configuration.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "window")
@Component
class WindowConfiguration {

    lateinit var title: String

    lateinit var height: Integer

    lateinit var weight: Integer

}