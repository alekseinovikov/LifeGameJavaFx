package me.freedom4live.game.life.javafx.configuration.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "timing")
@Component
class TimingConfiguration {

    lateinit var frameSchedule: Integer

}