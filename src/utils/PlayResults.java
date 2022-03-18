package utils;

public enum PlayResults {
    DRAW("draw"),
    WIN("win"),
    LOSE("lose");

    public final String text;

    PlayResults(String value) {
        this.text = value;
    }
}
