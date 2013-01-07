package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.*;

import de.hofuniversity.iws.shared.dto.User;
import javax.persistence.*;

@Entity
@Table
public class UserDBO implements Serializable, GenericEntity {

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
    @ManyToMany(mappedBy = "friends", cascade = {CascadeType.ALL})
    private List<UserDBO> devotees = new ArrayList<UserDBO>();

    @Override
    public boolean isDetached() {
        return detached;
    }

    @Override
    public void setDetached(boolean detached) {
        this.detached = detached;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        for (UserDBO devotee : devotees) {
            for (UserDBO friend : friends) {
                if (friend.getId().longValue() == devotee.getId().longValue()) {
                    retval.add(friend);
                    break;
                }
            }
        }
        return retval;
    }

    public boolean samePerson(UserDBO user) {
        for (NetworkAccountDBO networkAccountDBO : getNetworkAccountList()) {
            if (user.getNetworkAccountList().contains(networkAccountDBO)) {
                return true;
            }
        }
        return false;
    }
    
    public User getDTO()
    {
        return new User(getUserName(), getLastName(), getFirstName(), getCity(), getUserPic(), getBirthDate());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserDBO)) {
            return false;
        }

        final UserDBO uDBO = (UserDBO) other;

        if (!uDBO.getUserName().equals(getUserName())) {
            return false;
        }
        if (!uDBO.getBirthDate().equals(getBirthDate())) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = getUserName().hashCode();
        result = 29 * result + getBirthDate().getNanos();
        return result;
    }
}
