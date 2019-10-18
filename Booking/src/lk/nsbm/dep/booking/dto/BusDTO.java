package lk.nsbm.dep.booking.dto;

public class BusDTO {
    private String busId;
    private String busNo;
    private int seat;
    private String route;
    private String drvID;
    private String conId;

    public BusDTO() {
    }

    public BusDTO(String busId, String busNo, int seat, String route, String drvID, String conId) {
        this.busId = busId;
        this.busNo = busNo;
        this.seat = seat;
        this.route = route;
        this.drvID = drvID;
        this.conId = conId;
    }

    public BusDTO(String text, String text1, String text2, String id, String id1, String id2) {
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDrvID() {
        return drvID;
    }

    public void setDrvID(String drvID) {
        this.drvID = drvID;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId='" + busId + '\'' +
                ", busNo='" + busNo + '\'' +
                ", seat=" + seat +
                ", route=" + route +
                ", drvID='" + drvID + '\'' +
                ", conId='" + conId + '\'' +
                '}';
    }
}
