package controllers;

import models.Role;
import models.User;
import services.IUserServices;
import utils.ObjectResponse;
import utils.Response;

import java.util.List;

public class UserController {
    private final IUserServices userServices;

    public UserController(IUserServices userServices) {
        this.userServices = userServices;
    }


    public void createUser(){
//        test user
        var newUser = new User(
                "Nsisong",
                "Umanah",
                "nsisong@gmail.com",
                Role.USER
        );

        Response createUserResponse = userServices.createUser(newUser);

        if(createUserResponse.getStatus()){
            System.out.println("User added to database");
        } else {
            System.out.println(createUserResponse.getMessage());
        }
    }


    public void fetchUser(){
//        test ID
        int userID = 9;

        var getUSerByIDResponse = new ObjectResponse<User>();
        getUSerByIDResponse = userServices.getUSerByID(userID);

        if(getUSerByIDResponse.getStatus()){
            System.out.println(getUSerByIDResponse.getObjectData());
        } else {
            System.out.println(getUSerByIDResponse.getMessage());
        }
    }

    public void printAllUsers(){
        var response = userServices.getUsers();
        List<User> registeredUsers = response.getObjectData();

        if(!registeredUsers.isEmpty()) {
            for(var user : registeredUsers){
                System.out.println(user);
            }
        } else {
            System.out.println("Error Occurred: " + response.getMessage());
        }
    }

    public void changeUserEmail(){
        int userID = 1;
        String newEmail = "adb@gmail.com";

        var updateUserEmailResponse = userServices.updateUserEmail(newEmail, userID);

        if(updateUserEmailResponse.getStatus()){
            System.out.println(updateUserEmailResponse.getMessage());
        } else {
            System.out.println(updateUserEmailResponse.getMessage());
        }
    }

    public void removeUser(){
        int userID = 7;

        Response deleteUSerResponse = userServices.deleteUSer(userID);

        if(deleteUSerResponse.getStatus()){
            System.out.println(deleteUSerResponse.getMessage());
        } else {
            System.out.println(deleteUSerResponse.getMessage());
        }
    }
}
