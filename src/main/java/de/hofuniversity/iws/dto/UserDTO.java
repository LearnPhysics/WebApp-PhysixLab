package de.hofuniversity.iws.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


public class UserDTO implements Serializable {

    private boolean detached = false;
    
    private Long id;

    private String userName;

    private String lastName;

    private String firstName;

    private Timestamp birthDate;

    private String city;

    private String userPic;

    private String accountIdentificationString;

    private List<NetworkAccountDTO> networkAccountList;

    private List<GameResultDTO> gameResultList;

    private List<LessonProgressDTO> lessonProgressList;

    public boolean isDetached() {
        return detached;
    }

    public void setDetached(boolean detached) {
        this.detached = detached;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public List<NetworkAccountDTO> getNetworkAccountList() {
        return networkAccountList;
    }

    public void setNetworkAccountList(List<NetworkAccountDTO> networkAccountList) {
        this.networkAccountList = networkAccountList;
    }

    public List<GameResultDTO> getGameResultList() {
        return gameResultList;
    }

    public void setGameResultList(List<GameResultDTO> gameResultList) {
        this.gameResultList = gameResultList;
    }

    public List<LessonProgressDTO> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgressDTO> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }
    public String getAccountIdentificationString() {
        return accountIdentificationString;
    }

    public void setAccountIdentificationString(String accountIdentificationString) {
        this.accountIdentificationString = accountIdentificationString;
    }
}
