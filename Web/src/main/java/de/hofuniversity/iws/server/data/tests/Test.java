package de.hofuniversity.iws.server.data.tests;

import java.util.List;

import de.hofuniversity.iws.server.data.handler.*;
import de.hofuniversity.iws.shared.entityimpl.UserDBO;

public class Test {

    public static void main(String[] args) {
        HibernateUtil.isConnectedToDB();
        
        UserDBO joe = new UserDBO();
        joe.setFirstName("Joe");
        UserDBO franz = new UserDBO();
        franz.setFirstName("Franz");
        UserDBO julia = new UserDBO();
        julia.setFirstName("Julia");
        UserDBO kitenge = new UserDBO();
        kitenge.setFirstName("Kitenge");
        
        UserHandler.store(joe);
        UserHandler.store(franz);
        UserHandler.store(julia);
        UserHandler.store(kitenge);
        
        joe.getFriends().add(franz);
        joe.getFriends().add(julia);
        joe.getFriends().add(kitenge);
        
        franz.getFriends().add(julia);
        franz.getFriends().add(kitenge);
        franz.getFriends().add(joe);
        
        julia.getFriends().add(kitenge);
        julia.getFriends().add(joe);
        
        kitenge.getFriends().add(julia);
        
        System.out.println(joe.getFirstName() + " : " + joe.getId());
        for(UserDBO user : joe.getFriends()) {
            System.out.println(user.getId());
        }
        System.out.println(franz.getFirstName() + " : " + franz.getId());
        for(UserDBO user : franz.getFriends()) {
            System.out.println(user.getId());
        }
        System.out.println(julia.getFirstName() + " : " + julia.getId());
        for(UserDBO user : julia.getFriends()) {
            System.out.println(user.getId());
        }
        System.out.println(kitenge.getFirstName() + " : " + kitenge.getId());
        for(UserDBO user : kitenge.getFriends()) {
            System.out.println(user.getId());
        }
        
        
        UserHandler.store(joe);
        UserHandler.store(franz);
        UserHandler.store(julia);
        UserHandler.store(kitenge);
        
        List<UserDBO> peopleILike;
        List<UserDBO> peopleWhoLikeMe;
        List<UserDBO> bilateralFriends;
        
        List<UserDBO> allUsers = UserHandler.getAllUsers();
        
        for(UserDBO user : allUsers) {
            peopleILike = user.getFriends();
            peopleWhoLikeMe = user.getDevotees();
            bilateralFriends = user.getBilateralFriends();
            System.out.println(user.getFirstName() + " : " + user.getId());
            System.out.println("-----");
            for(UserDBO personILike : peopleILike) {
                System.out.println(user.getFirstName() + " likes " + personILike.getFirstName());
            }
            for(UserDBO personWhoLikeMe : peopleWhoLikeMe) {
                System.out.println(user.getFirstName() + " is liked by " + personWhoLikeMe.getFirstName());
            }
            for(UserDBO bi : bilateralFriends) {
                System.out.println(user.getFirstName() + " and " + bi.getFirstName() + " like each other");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}
