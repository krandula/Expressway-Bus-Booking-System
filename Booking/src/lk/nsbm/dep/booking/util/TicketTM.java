package lk.nsbm.dep.booking.util;

import java.sql.Date;

public class TicketTM {
    private String ticketNo;
    private String passId;
    private String busId;
    private Date date;
    private String tripNo;
    private int seatNo;
    private int price;

    public TicketTM() {
    }

    public TicketTM(String passId, String busId, Date date, String tripNo, int seatNo, int price) {
        this.ticketNo = ticketNo;
        this.passId = passId;
        this.busId = busId;
        this.date = date;
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.price = price;
    }

    public TicketTM(String ticketNo, String passId, String busId, java.util.Date date, String tripNo, int seatNo, int price) {
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getPassId() {
        return passId;
    }

    public void setPassId(String pssId) {
        this.passId = pssId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTripNo() {
        return tripNo;
    }

    public void setTripNo(String tripNo) {
        this.tripNo = tripNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketTM{" +
                "ticketNo='" + ticketNo + '\'' +
                ", pssId='" + passId + '\'' +
                ", busId='" + busId + '\'' +
                ", date=" + date +
                ", tripNo='" + tripNo + '\'' +
                ", seatNo=" + seatNo +
                ", price=" + price +
                '}';
    }
}
