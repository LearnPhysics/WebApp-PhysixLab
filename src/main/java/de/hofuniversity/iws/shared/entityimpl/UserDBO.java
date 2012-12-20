package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import de.hofuniversity.iws.shared.entitys.User;
import javax.persistence.*;

@Entity
@Table
public class UserDBO implements Serializable, GenericEntity, User {

    @Transient
    private boolean detached = false;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String userName;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private Timestamp birthDate;
    @Column
    private String city;
    @Column
    private String userPic;
    @Column
    private String accountIdentificationString;
    @OneToMany(mappedBy = "user")
    private List<NetworkAccountDBO> networkAccountList = new ArrayList<NetworkAccountDBO>();
    @OneToMany(mappedBy = "user")
    private List<GameResultDBO> gameResultList = new ArrayList<GameResultDBO>();
    @OneToMany(mappedBy = "user")
    private List<LessonProgressDBO> lessonProgressList = new ArrayList<LessonProgressDBO>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "DEVOTEE_FRIENDS",
    joinColumns = {
        @JoinColumn(name = "DEVOTEE_ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "FRIEND_ID")})
    private List<UserDBO> friends = new ArrayList<UserDBO>();
    @ManyToMany(mappedBy = "friends")
    private List<UserDBO> devotees = new ArrayList<UserDBO>();

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

    public List<NetworkAccountDBO> getNetworkAccountList() {
        return networkAccountList;
    }

    public void setNetworkAccountList(List<NetworkAccountDBO> networkAccountList) {
        this.networkAccountList = networkAccountList;
    }

    public List<GameResultDBO> getGameResultList() {
        return gameResultList;
    }

    public void setGameResultList(List<GameResultDBO> gameResultList) {
        this.gameResultList = gameResultList;
    }

    public List<LessonProgressDBO> getLessonProgressList() {
        return lessonProgressList;
    }

    public void setLessonProgressList(List<LessonProgressDBO> lessonProgressList) {
        this.lessonProgressList = lessonProgressList;
    }

    public List<UserDBO> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDBO> friends) {
        this.friends = friends;
    }

    public List<UserDBO> getDevotees() {
        return devotees;
    }

    public void setDevotees(List<UserDBO> devotees) {
        this.devotees = devotees;
    }
    
    public List<UserDBO> getBilateralFriends() {
        List<UserDBO> retval = new LinkedList<UserDBO>();
        for(UserDBO devotee : devotees) {
            for(UserDBO friend : friends) {
                if(friend.getId().longValue() == devotee.getId().longValue()) {
                    retval.add(friend);
                    break;
                }
            }
        }
        return retval;
    }
}
