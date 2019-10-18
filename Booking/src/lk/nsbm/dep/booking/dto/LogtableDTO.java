package lk.nsbm.dep.booking.dto;

public class LogtableDTO {
    private String userID;
    private String userName;
    private String pasWord;

    public LogtableDTO() {
    }

    public LogtableDTO(String userID, String userName, String pasWord) {
        this.userID = userID;
        this.userName = userName;
        this.pasWord = pasWord;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasWord() {
        return pasWord;
    }

    public void setPasWord(String pasWord) {
        this.pasWord = pasWord;
    }

    @Override
    public String toString() {
        return "LogtableDTO{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", pasWord='" + pasWord + '\'' +
                '}';
    }
}
