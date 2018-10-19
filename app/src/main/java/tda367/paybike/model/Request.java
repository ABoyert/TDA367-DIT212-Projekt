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
    /* The reason for having both answered and accepted, is to be able to
     tell apart a request that has not yet been answered with one that has been answered.
     A request which has accepted = false and answered = false has not been answered and therefore
     accepted = false should not be interpreted as declined. Whereas accepted = false and answered = true
     means the request has been declined by the owner.*/
    private boolean accepted;
    private boolean answered;
    private String id;

    private void checkDateTime(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {
        LocalDateTime now = LocalDateTime.now();
        if (from.isBefore(now)) {
            throw new IllegalArgumentException("Request date cannot be in the past");
        } else if (to.isBefore(from)) {
            throw new IllegalArgumentException("Request to-date needs to be after from-date");
        }
    }

    public Request(String sendingUserID, String targetRentableID,
                   LocalDateTime fromDateTime, LocalDateTime toDateTime, double price) {
        checkDateTime(fromDateTime, toDateTime);
        this.sendingUserId = sendingUserID;
        this.targetRentableId = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.price = price;
        accepted = false;
        answered = false;
    }

    /* Constructor with ID */
    public Request(String sendingUserID, String targetRentableID,
                   LocalDateTime fromDateTime, LocalDateTime toDateTime, double price, String id) {
        checkDateTime(fromDateTime, toDateTime);
        this.sendingUserId = sendingUserID;
        this.targetRentableId = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.price = price;
        this.id = id;
        accepted = false;
        answered = false;
    }

    public String getSendingUserId() {
        return sendingUserId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
            this.accepted = accepted;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
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
        return accepted == request.accepted &&
                Objects.equals(sendingUserId, request.sendingUserId) &&
                Objects.equals(targetRentableId, request.targetRentableId) &&
                Objects.equals(fromDateTime, request.fromDateTime) &&
                Objects.equals(toDateTime, request.toDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendingUserId, targetRentableId, fromDateTime, toDateTime, accepted);
    }

    @Override
    public String toString() {
        return "Request Id: " + requestId + "\nBike: " + targetRentableId +
                "\nSending user: " + sendingUserId + "\nFrom: " + fromDateTime.toString()
                + "\nTo: " + toDateTime.toString();
    }
}
