package lk.nsbm.dep.booking.entity;

public class TicketPK {
    private String passId;
    private String busId;

    public TicketPK() {
    }

    public TicketPK(String passId, String busId) {
        this.passId = passId;
        this.busId = busId;
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

    @Override
    public String toString() {
        return "TicketPK{" +
                "passId='" + passId + '\'' +
                ", busId='" + busId + '\'' +
                '}';
    }
}
