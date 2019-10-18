package lk.nsbm.dep.booking.dto;

public class PassengerDTO {
    private String passId;
    private String passName;
    private String passNic;
    private String passConNum;

    public PassengerDTO() {
    }

    public PassengerDTO(String passId, String passName, String passNic, String passConNum) {
        this.passId = passId;
        this.passName = passName;
        this.passNic = passNic;
        this.passConNum = passConNum;
    }

    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public String getPassNic() {
        return passNic;
    }

    public void setPassNic(String passNic) {
        this.passNic = passNic;
    }

    public String getPassConNum() {
        return passConNum;
    }

    public void setPassConNum(String passConNum) {
        this.passConNum = passConNum;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passId='" + passId + '\'' +
                ", passName='" + passName + '\'' +
                ", passNic='" + passNic + '\'' +
                ", passConNum='" + passConNum + '\'' +
                '}';
    }
}
