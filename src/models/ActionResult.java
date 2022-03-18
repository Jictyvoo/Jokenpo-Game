package models;

import utils.PlayResults;

public class ActionResult {
    public final String reason;
    public final PlayResults result;

    public ActionResult(PlayResults result, String reason) {
        this.reason = reason;
        this.result = result;
    }
}
