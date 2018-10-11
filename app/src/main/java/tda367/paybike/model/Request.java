package tda367.paybike.model;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class Request {

    private String receivingUserID;
    private String sendingUserID;
    private String targetRentableID;
    private boolean accepted;

    public Request(String receivingUserID, String sendingUserID, String targetRentableID) {
        this.receivingUserID = receivingUserID;
        this.sendingUserID = sendingUserID;
        this.accepted = false;
        this.targetRentableID = targetRentableID;
    }

    public String getReceivingUserID() {
        return receivingUserID;
    }

    public void setReceivingUserID(String receivingUserID) {
        this.receivingUserID = receivingUserID;
    }

    public String getSendingUserID() {
        return sendingUserID;
    }

    public void setSendingUserID(String sendingUserID) {
        this.sendingUserID = sendingUserID;
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

    public void setTargetRentableID(String targetRentableID) {
        this.targetRentableID = targetRentableID;
    }
}
