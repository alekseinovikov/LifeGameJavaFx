package me.freedom4live.game.life.javafx.engine.impl

import me.freedom4live.game.life.javafx.configuration.ConfigurationProvider
import me.freedom4live.game.life.javafx.engine.Cell
import me.freedom4live.game.life.javafx.engine.Field
import me.freedom4live.game.life.javafx.engine.GameEngine
import me.freedom4live.game.life.javafx.fx.DrawEngine
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

@Component
class GameEngineImpl(private val configurationProvider: ConfigurationProvider) : GameEngine {

    private lateinit var currentState: Field
    private lateinit var timer: Timer

    override fun stop() {
        timer.cancel()
        timer.purge()
    }

    override fun run(drawEngine: DrawEngine) {
        initFirstStep()
        scheduleTimer(drawEngine)
    }

    private fun initFirstStep() {
        val ySize = configurationProvider.horizontalFieldSize()
        val xSize = configurationProvider.verticalFieldSize()

        val cells = ArrayList<ArrayList<Cell>>(ySize)
        for (y in 0..ySize) {
            val xCells = ArrayList<Cell>(xSize)
            cells.add(xCells)
            for (x in 0..xSize) {
                val newCell = Cell.create(getRandomCellAliveValue())

                xCells.add(newCell)
            }
        }

        currentState = Field.create(cells)
    }

    private fun getRandomCellAliveValue(): Boolean {
        val percentage = configurationProvider.initCellAliveProbabilityPercents()
        val randomValue = Random.nextInt(100)
        return randomValue < percentage
    }

    private fun scheduleTimer(drawEngine: DrawEngine) {
        val task = object : TimerTask() {
            override fun run() {
                processStep(drawEngine)
            }
        }

        val timer = Timer()
        val delay = configurationProvider.frameTimerMilliSeconds().toLong()
        val period = configurationProvider.frameTimerMilliSeconds().toLong()

        timer.scheduleAtFixedRate(task, delay, period)

        this.timer = timer
    }

    private fun processStep(drawEngine: DrawEngine) {
        calculateNextStep()

        drawEngine.draw(currentState)
    }

    private fun calculateNextStep() {
        val yCells = currentState.getCells()
        for (y in 0..yCells.size-1) {
            val xCells = yCells[y]
            for (x in 0..xCells.size-1) {

            }
        }
    }

}