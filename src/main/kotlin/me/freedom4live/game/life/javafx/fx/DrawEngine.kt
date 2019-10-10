package me.freedom4live.game.life.javafx.fx

import javafx.stage.Stage
import me.freedom4live.game.life.javafx.engine.Field

interface DrawEngine {

    fun start(stage: Stage)

    fun draw(field: Field)

}