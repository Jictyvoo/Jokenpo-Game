package utils;

public enum PlayChoice {
    PAPER("paper"),
    ROCK("rock"),
    SCISSOR("scissor");

    public final String text;

    PlayChoice(final String value) {
        this.text = value;
    }
}
