package lk.nsbm.dep.booking.dto;

import java.util.Date;

public class TicketDTO {
    private String ticketNo;
    private String passId;
    private String busId;
    private Date date;
    private String TripNo;
    private int seatNo;
    private int price;

    public TicketDTO() {
    }

    public TicketDTO(String ticketNo, String passId, String busId, Date date, String tripNo, int seatNo, int price) {
        this.ticketNo = ticketNo;
        this.passId = passId;
        this.busId = busId;
        this.date = date;
        TripNo = tripNo;
        this.seatNo = seatNo;
        this.price = price;
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

    public void setPassId(String passId) {
        this.passId = passId;
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
        return TripNo;
    }

    public void setTripNo(String tripNo) {
        TripNo = tripNo;
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
        return "TicketDTO{" +
                "ticketNo='" + ticketNo + '\'' +
                ", passId='" + passId + '\'' +
                ", busId='" + busId + '\'' +
                ", date=" + date +
                ", TripNo='" + TripNo + '\'' +
                ", seatNo=" + seatNo +
                ", price=" + price +
                '}';
    }
}
