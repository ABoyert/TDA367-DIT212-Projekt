package tda367.paybike.model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class RequestTest {
    @Test
    public void createRequest(){
        String sendingTestID = "sending-id-0";
        String targetTestID = "target-id-0";
        LocalDateTime fromDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,15,30);
        LocalDateTime toDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,16,30);
        double testPrice = 25.00;
        Request.Answer status = Request.Answer.UNANSWERED;
        Request testReq = new Request(sendingTestID, targetTestID, fromDateTime, toDateTime,testPrice,status);
        assert testReq.getSendingUserId().equals(sendingTestID) && testReq.getTargetRentableId().equals(targetTestID) &&
                testReq.getFromDateTime().equals(fromDateTime) && testReq.getToDateTime().equals(toDateTime) &&
                testReq.getPrice() == testPrice && testReq.getAnswer() == status;
    }

    @Test
    public void createRequestWithID(){
        String sendingTestID = "sending-id-0";
        String targetTestID = "target-id-0";
        LocalDateTime fromDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,15,30);
        LocalDateTime toDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,16,30);
        double testPrice = 25.00;
        Request.Answer status = Request.Answer.UNANSWERED;
        String reqID = "req-id-0";
        Request testReq = new Request(sendingTestID, targetTestID, fromDateTime, toDateTime,testPrice,status,reqID);
        assert testReq.getSendingUserId().equals(sendingTestID) && testReq.getTargetRentableId().equals(targetTestID) &&
                testReq.getFromDateTime().equals(fromDateTime) && testReq.getToDateTime().equals(toDateTime) &&
                testReq.getPrice() == testPrice && testReq.getAnswer() == status && testReq.getId().equals(reqID);
    }


    @Test
    public void setAndGetAnswer() {
        Request testReq = new Request(null,null,null,null,0,Request.Answer.UNANSWERED);
        testReq.setAnswer(Request.Answer.DENIED);
        assert testReq.getAnswer() == Request.Answer.DENIED;
        testReq.setAnswer(Request.Answer.ACCEPTED);
        assert testReq.getAnswer() == Request.Answer.ACCEPTED;

    }

    @Test
    public void equals() {
        Request testReq = new Request("tester",null,null,null,0,Request.Answer.UNANSWERED,"01");
        Request testReq2 = new Request("failtest",null,null,null,0,Request.Answer.ACCEPTED,"02");
        Request testReq3= new Request("tester",null,null,null,0,Request.Answer.UNANSWERED,"01");

        assert !testReq.equals(testReq2)&&testReq.equals(testReq3);
    }


    @Test
    public void testtoString() {
        String sendingTestID = "sending-id-0";
        String targetTestID = "target-id-0";
        LocalDateTime fromDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,15,30);
        LocalDateTime toDateTime = LocalDateTime.of(2019, Month.FEBRUARY,16,16,30);
        double testPrice = 25.00;
        Request.Answer status = Request.Answer.UNANSWERED;
        Request testReq = new Request(sendingTestID, targetTestID, fromDateTime, toDateTime,testPrice,status);
        String theString = "Request Id: " + testReq.getId() + "\nBike: " + testReq.getTargetRentableId() +
                "\nSending user: " + testReq.getSendingUserId() + "\nFrom: " + testReq.getFromDateTime().toString()
                + "\nTo: " + testReq.getToDateTime().toString();

        assert testReq.toString().equals(theString);

    }
}