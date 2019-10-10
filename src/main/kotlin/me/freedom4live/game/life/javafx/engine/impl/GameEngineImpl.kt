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
        val newCells = ArrayList<List<Cell>>()
        val currentCells = currentState.getCells()

        val yCells = currentState.getCells()
        for (y in yCells.indices) {
            val xCells = yCells[y]

            val newXCells = ArrayList<Cell>()
            for (x in xCells.indices) {
                val isAlive = checkAliveCell(currentCells, x, y)

                newXCells.add(Cell.create(isAlive))
            }

            newCells.add(newXCells)
        }

        currentState = Field.create(newCells)
    }

    private fun checkAliveCell(currentCells: List<List<Cell>>, x: Int, y: Int): Boolean {
        val liveNeighborsCount = countAliveNeughbors(currentCells, x, y)
        return if (currentCells[y][x].isAlive()) checkForAlive(liveNeighborsCount) else checkForDead(liveNeighborsCount)
    }

    private fun checkForDead(liveNeighborsCount: Int): Boolean {
        return liveNeighborsCount == 3
    }

    private fun checkForAlive(liveNeighborsCount: Int): Boolean {
        return liveNeighborsCount == 2 || liveNeighborsCount == 3
    }

    private fun countAliveNeughbors(currentCells: List<List<Cell>>, x: Int, y: Int): Int {
        var counter = 0

        if (x > 0 && y > 0 && currentCells[y - 1][x - 1].isAlive()) counter++ // -1,-1
        if (y > 0 && currentCells[y - 1][x].isAlive()) counter++ // 0,-1
        if (y > 0 && x < currentCells[y - 1].size - 1 && currentCells[y - 1][x + 1].isAlive()) counter++ // +1,-1

        if (x > 0 && currentCells[y][x - 1].isAlive()) counter++ //-1,0
        if (x < currentCells[y].size - 1 && currentCells[y][x + 1].isAlive()) counter++ //+1,0

        if (x > 0 && y < currentCells.size - 1 && currentCells[y + 1][x - 1].isAlive()) counter++ //-1,+1
        if (y < currentCells.size - 1 && currentCells[y + 1][x].isAlive()) counter++ //0,+1
        if (y < currentCells.size - 1 && x < currentCells[y + 1].size - 1 && currentCells[y + 1][x + 1].isAlive()) counter++ //+1, +1

        return counter
    }

}