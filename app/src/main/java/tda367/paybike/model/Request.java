package tda367.paybike.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class Request {

    private final String sendingUserId;
    private final String targetRentableId;
    private String requestId;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;
    private double price;
    private String id;
    /* The reason for having both answered and accepted, is to be able to
     tell apart a request that has not yet been answered with one that has been answered.
     A request which has accepted = false and answered = false has not been answered and therefore
     accepted = false should not be interpreted as declined. Whereas accepted = false and answered = true
     means the request has been declined by the owner.*/
    public enum Answer {
        ACCEPTED,
        UNANSWERED,
        DENIED
    }
    private Answer answer;

    public Request(String sendingUserID, String targetRentableID,
                   LocalDateTime fromDateTime, LocalDateTime toDateTime, double price, Answer answer) {
        this.sendingUserId = sendingUserID;
        this.targetRentableId = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.price = price;
        this.answer = answer;
    }

    /* Constructor with ID */
    public Request(String sendingUserID, String targetRentableID,
                   LocalDateTime fromDateTime, LocalDateTime toDateTime, double price, Answer answer, String id) {
        this.sendingUserId = sendingUserID;
        this.targetRentableId = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.price = price;
        this.id = id;
        this.answer = answer;
    }

    public String getSendingUserId() {
        return sendingUserId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
            this.answer = answer;
    }

    public String getTargetRentableId() {
        return targetRentableId;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return answer == request.getAnswer() &&
                Objects.equals(sendingUserId, request.sendingUserId) &&
                Objects.equals(targetRentableId, request.targetRentableId) &&
                Objects.equals(fromDateTime, request.fromDateTime) &&
                Objects.equals(toDateTime, request.toDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendingUserId, targetRentableId, fromDateTime, toDateTime, answer);
    }

    @Override
    public String toString() {
        return "Request Id: " + requestId + "\nBike: " + targetRentableId +
                "\nSending user: " + sendingUserId + "\nFrom: " + fromDateTime.toString()
                + "\nTo: " + toDateTime.toString();
    }
}
