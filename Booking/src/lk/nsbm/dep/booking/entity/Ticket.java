package lk.nsbm.dep.booking.entity;

import java.sql.Date;

public class Ticket implements SuperEntity {
    private String ticketNo;
    private TicketPK ticketPK;
    private Date date;
    private String tripNo;
    private int seatNo;
    private int price;

    public Ticket() {
    }

    public Ticket(String ticketNo, TicketPK ticketPK, Date date, String tripNo, int seatNo, int price) {
        this.ticketNo = ticketNo;
        this.ticketPK = ticketPK;
        this.date = date;
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.price = price;

    }

    public Ticket(String ticketNo, String passId, String busId, Date date, String tripNo, int seatNo, int price) {
        this.ticketNo = ticketNo;
        this.ticketPK = new TicketPK(passId, busId);
        this.date = date;
        this.tripNo = tripNo;
        this.seatNo = seatNo;
        this.price = price;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public TicketPK getTicketPK() {
        return ticketPK;
    }

    public void setTicketPK(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
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
        return "Ticket{" +
                "ticketNo='" + ticketNo + '\'' +
                ", ticketPK=" + ticketPK +
                ", date=" + date +
                ", tripNo='" + tripNo + '\'' +
                ", seatNo=" + seatNo +
                ", price=" + price +
                '}';
    }
}
