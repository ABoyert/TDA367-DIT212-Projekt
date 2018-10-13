package tda367.paybike.model;

import java.time.LocalDateTime;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class Request {

    private final String sendingUserID;
    private final String targetRentableID;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;
    private boolean accepted;

    public Request(String sendingUserID, String targetRentableID,
                   LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.sendingUserID = sendingUserID;
        this.accepted = false;
        this.targetRentableID = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public String getSendingUserID() {
        return sendingUserID;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getTargetRentableID() {
        return targetRentableID;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }
}
