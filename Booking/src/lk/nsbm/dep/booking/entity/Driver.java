package lk.nsbm.dep.booking.entity;

public class Driver implements SuperEntity {
    private String drvId;
    private String drvName;
    private String drvNic;
    private String drvConNum;

    public Driver() {
    }

    public Driver(String drvId, String drvName, String drvNic, String drvConNum) {
        this.drvId = drvId;
        this.drvName = drvName;
        this.drvNic = drvNic;
        this.drvConNum = drvConNum;
    }

    public String getDrvId() {
        return drvId;
    }

    public void setDrvId(String drvId) {
        this.drvId = drvId;
    }

    public String getDrvName() {
        return drvName;
    }

    public void setDrvName(String drvName) {
        this.drvName = drvName;
    }

    public String getDrvNic() {
        return drvNic;
    }

    public void setDrvNic(String drvNic) {
        this.drvNic = drvNic;
    }

    public String getDrvConNum() {
        return drvConNum;
    }

    public void setDrvConNum(String drvConNum) {
        this.drvConNum = drvConNum;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "drvId='" + drvId + '\'' +
                ", drvName='" + drvName + '\'' +
                ", drvNic='" + drvNic + '\'' +
                ", drvConNum='" + drvConNum + '\'' +
                '}';
    }
}
