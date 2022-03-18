package controllers

import models.ActionResult
import utils.CircleList
import utils.PlayChoice
import utils.PlayResults

class JokenpoUsecase {
    private val possiblePlays: CircleList<PlayChoice> = CircleList(PlayChoice.PAPER)

    init {
        possiblePlays.add(PlayChoice.ROCK)
        possiblePlays.add(PlayChoice.SCISSOR)
    }

    fun fromNumber(number: Int): PlayChoice {
        val choices = PlayChoice.values()
        return if (choices.size <= number) {
            choices[0]
        } else choices[number]
    }

    fun execute(playerChoice: PlayChoice, computerChoice: PlayChoice?): ActionResult {
        while (possiblePlays.data !== playerChoice) possiblePlays.next()
        return when (computerChoice) {
            playerChoice -> {
                ActionResult(
                    PlayResults.DRAW,
                    "Empate, ambos selecionaram " + playerChoice.name
                )
            }
            possiblePlays.inNext() -> {
                ActionResult(
                    PlayResults.LOSE,
                    computerChoice!!.name + " ganha de " + playerChoice.name
                )
            }
            else -> {
                ActionResult(
                    PlayResults.WIN,
                    playerChoice.name + " ganha de " + computerChoice!!.name
                )
            }
        }
    }
}
