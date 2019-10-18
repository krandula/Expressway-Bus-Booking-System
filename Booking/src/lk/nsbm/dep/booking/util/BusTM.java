package lk.nsbm.dep.booking.util;

public class BusTM {
    private String busId;
    private String busNo;
    private int seat;
    private String route;
    private String drvId;
    private String conId;

    public BusTM() {
    }

    public BusTM(String busId, String busNo, int seat, String route, String drvId, String conId) {
        this.busId = busId;
        this.busNo = busNo;
        this.seat = seat;
        this.route = route;
        this.drvId = drvId;
        this.conId = conId;
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

    public String getDrvId() {
        return drvId;
    }

    public void setDrvId(String drvId) {
        this.drvId = drvId;
    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    @Override
    public String toString() {
        return "BusTM{" +
                "busId='" + busId + '\'' +
                ", busNo='" + busNo + '\'' +
                ", seat='" + seat + '\'' +
                ", route='" + route + '\'' +
                ", drvID='" + drvId + '\'' +
                ", conId='" + conId + '\'' +
                '}';
    }
}
