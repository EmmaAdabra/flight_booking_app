package controllers;

import models.Role;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.IUserServices;
import utils.ObjectResponse;
import utils.Response;
import utils.StringUtility;
import utils.UserDatabaseUtil;

import java.util.List;

public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserServices userServices;

    public UserController(IUserServices userServices) {
        this.userServices = userServices;
    }


    public void registerUser(){
//        test user
        String email = "billgate@gmail.com";
        var newUser = new User(
                "Bill",
                "Gate",
                email,
                Role.USER
        );

        logger.info("Registering user with email - {}", StringUtility.maskEmail(email));
        Response createUserResponse = userServices.createUser(newUser);

        if(createUserResponse.getStatus()){
            System.out.println("User added to database");
            logger.info("Created new user with ID - {}", UserDatabaseUtil.getUserID(email));
        } else {
            System.out.println(createUserResponse.getMessage());
            logger.error("new user with email - {} could not be created", StringUtility.maskEmail(email));
        }
    }


    public void fetchUser(){
//        test ID
        int userID = 9;

        var getUSerByIDResponse = new ObjectResponse<User>();

        logger.info("Requesting to fetch user with ID - {}", userID);
        getUSerByIDResponse = userServices.getUSerByID(userID);

        if(getUSerByIDResponse.getStatus()){
            logger.info("Successfully fetched user with ID - {}", userID);
            System.out.println(getUSerByIDResponse.getObjectData());
        } else {
            logger.warn("Failed to fetch user with ID - {}", userID);
            System.out.println(getUSerByIDResponse.getMessage());
        }
    }

    public void printAllUsers(){
        logger.info("Printing records of all registered user");
        var response = userServices.getUsers();
        List<User> registeredUsers = response.getObjectData();

        if(!registeredUsers.isEmpty()) {
            for(var user : registeredUsers){
                System.out.println(user);
            }
            logger.info("Registered printed successfully");
        } else if(registeredUsers.isEmpty()) {
            logger.warn("No register user found");
            System.out.println("No register user found");
        } else {
            logger.error("Error occurred trying to print registered users");
            System.out.println("Database error - " + response.getMessage());
        }
    }

    public void changeUserEmail(){
        int userID = 1;
        String newEmail = "adb@gmail.com";
        logger.info("changing email for user with ID - {}", userID);
        var updateUserEmailResponse = userServices.updateUserEmail(newEmail, userID);

        if(updateUserEmailResponse.getStatus()){
            logger.info("Email changed successfully for user with ID - {}", userID);
            System.out.println(updateUserEmailResponse.getMessage());
        } else {
            logger.warn("Email could not be change for user with ID - {}", userID);
            System.out.println(updateUserEmailResponse.getMessage());
        }
    }

    public void removeUser(){
        int userID = 7;

        logger.info("Removing user with ID - {}", userID);
        Response deleteUSerResponse = userServices.deleteUSer(userID);

        if(deleteUSerResponse.getStatus()){
            logger.info("User with with ID - {} have been removed", userID);
            System.out.println(deleteUSerResponse.getMessage());
        } else {
            logger.warn("Could not remove user with ID - {}", userID);
            System.out.println(deleteUSerResponse.getMessage());
        }
    }
}
