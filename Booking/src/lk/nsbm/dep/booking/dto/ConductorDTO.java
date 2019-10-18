package lk.nsbm.dep.booking.dto;

public class ConductorDTO {
    private String conId;
    private String conName;
    private String conNic;
    private String conConNum;


    public ConductorDTO() {
    }

    public ConductorDTO(String conId, String conName, String conNic, String conConNum) {
        this.conId = conId;
        this.conName = conName;
        this.conNic = conNic;
        this.conConNum = conConNum;

    }

    public String getConId() {
        return conId;
    }

    public void setConId(String conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConNic() {
        return conNic;
    }

    public void setConNic(String conNic) {
        this.conNic = conNic;
    }

    public String getConConNum() {
        return conConNum;
    }

    public void setConConNum(String conConNum) {
        this.conConNum = conConNum;
    }

    @Override
    public String toString() {
        return "ConductorDTO{" +
                "conId='" + conId + '\'' +
                ", conName='" + conName + '\'' +
                ", conNic='" + conNic + '\'' +
                ", conConNum='" + conConNum + '\'' +
                '}';
    }
}
