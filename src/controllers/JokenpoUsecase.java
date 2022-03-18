package controllers;

import models.ActionResult;
import utils.CircleList;
import utils.PlayChoice;
import utils.PlayResults;

public class JokenpoUsecase {

    final CircleList<PlayChoice> possiblePlays;

    public JokenpoUsecase() {

        possiblePlays = new CircleList<>(PlayChoice.PAPER);
        possiblePlays.add(PlayChoice.ROCK);
        possiblePlays.add(PlayChoice.SCISSOR);
    }

    public PlayChoice fromNumber(int number) {
        final PlayChoice[] choices = PlayChoice.values();
        if (choices.length <= number) {
            return choices[0];
        }
        return choices[number];
    }

    public ActionResult execute(final PlayChoice playerChoice, final PlayChoice computerChoice) {
        while (possiblePlays.getData() != playerChoice)
            possiblePlays.next();

        if (computerChoice == playerChoice) {
            return new ActionResult(
                    PlayResults.DRAW,
                    "Empate, ambos selecionaram " + playerChoice.name()
            );
        } else if (computerChoice == possiblePlays.inNext()) {
            return new ActionResult(
                    PlayResults.LOSE,
                    computerChoice.name() + " ganha de " + playerChoice.name()
            );
        } else {
            return new ActionResult(
                    PlayResults.WIN,
                    playerChoice.name() + " ganha de " + computerChoice.name()
            );
        }
    }
}
