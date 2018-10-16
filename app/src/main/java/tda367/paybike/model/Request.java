package tda367.paybike.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private boolean accepted;

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
        this.accepted = false;
        this.targetRentableId = targetRentableID;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.price = price;
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
