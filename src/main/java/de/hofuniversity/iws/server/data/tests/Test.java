package de.hofuniversity.iws.server.data.tests;

import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.data.handler.HibernateUtil;
import de.hofuniversity.iws.server.data.handler.UserHandler;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        HibernateUtil.isConnectedToDB();
        
        User joe = new User();
        joe.setFirstName("Joe");
        User franz = new User();
        franz.setFirstName("Franz");
        User julia = new User();
        julia.setFirstName("Julia");
        User kitenge = new User();
        kitenge.setFirstName("Kitenge");
        
        joe.getFriends().add(franz);
        joe.getFriends().add(julia);
        joe.getFriends().add(kitenge);
        
        franz.getFriends().add(julia);
        franz.getFriends().add(kitenge);
        franz.getFriends().add(joe);
        
        julia.getFriends().add(kitenge);
        julia.getFriends().add(joe);
        
        kitenge.getFriends().add(julia);
        
        UserHandler.store(joe);
        UserHandler.store(franz);
        UserHandler.store(julia);
        UserHandler.store(kitenge);
        
        List<User> peopleILike;
        List<User> peopleWhoLikeMe;
        List<User> bilateralFriends;
        
        List<User> allUsers = UserHandler.getAllUsers();
        
        for(User user : allUsers) {
            peopleILike = user.getFriends();
            peopleWhoLikeMe = user.getDevotees();
            bilateralFriends = user.getBilateralFriends();
            System.out.println(user.getFirstName());
            System.out.println("-----");
            for(User personILike : peopleILike) {
                System.out.println(user.getFirstName() + " likes " + personILike.getFirstName());
            }
            for(User personWhoLikeMe : peopleWhoLikeMe) {
                System.out.println(user.getFirstName() + " is liked by " + personWhoLikeMe.getFirstName());
            }
            for(User bi : bilateralFriends) {
                System.out.println(user.getFirstName() + " and " + bi.getFirstName() + " like each other");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}
