package services;

import dao.exception.DAOException;
import dao.user_dao.UserDAO;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ObjectResponse;
import utils.Response;
import utils.StringUtility;
import utils.UserDatabaseUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServices implements IUserServices {
    private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
    private final UserDAO userDAO;

    public UserServices(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Response createUser(User user) {
        Response response = new Response();
        String maskEmail = StringUtility.maskEmail(user.getEmail());
        try {
            logger.info("Start creating new user with the email - {}", maskEmail);
            int result =  userDAO.save(user);

            if(result > 0){
                response.createResponse(response, true, "User created successfully");
                logger.debug("User created successfully. Email - {}, ID - {}",
                        maskEmail,
                        UserDatabaseUtil.getUserID(user.getEmail()));
            } else {
                response.createResponse(response, false, "User couldn't be created");
                logger.warn("Failed to create user with email {}, user already exist", maskEmail);
            }
        } catch (DAOException e){
            response.setStatus(false);
            response.setMessage("DAO Error - " + e.getMessage());
            logger.error("DAO Error occurred while creating user with email - {}", maskEmail, e);
        }
        return response;
    }

    @Override
    public ObjectResponse<List<User>> getUsers() {
        var response = new ObjectResponse<List<User>>();
        List<User> users;

        try{
            users = userDAO.getAll();

            if(!users.isEmpty()){
                response.createResponse(response, true, "success", users);
            } else {
                response.createResponse(response, false, "No users found", Collections.emptyList());
            }
        } catch (DAOException e) {
            response.createResponse(response, false, "DAO Error - " + e.getMessage());
        }

        return response;
    }

    @Override
    public ObjectResponse<User> getUSerByID(int id) {
        var response = new ObjectResponse<User>();

        try{
            Optional<User> user = userDAO.get(id);

            if(user.isPresent()) {
                response.createResponse(response, true, "success", user.get());
            } else {
                response.createResponse(response, false, "user not found");
            }

        } catch (DAOException e) {
            response.createResponse(response, false, "DAO Error - " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response updateUserEmail(String newEmail, int userID) {
        var response = new Response();

        try {
            int result  = userDAO.update(newEmail, userID);

            if(result > 0){
                response.createResponse(response, true, "User email updated successfully");
            } else {
                response.createResponse(response, false, "Error: invalid user ID");
            }

        } catch (DAOException e) {
            response.createResponse(response, false, "DAO Error: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteUSer(int userID) {
        var response = new Response();

        try{
            int result = userDAO.delete(userID);

            if(result > 0){
                response.createResponse(response, true, "User deleted successfully");
            } else {
                response.createResponse(response, false, "Error: Invalid user ID");
            }
        } catch (DAOException e) {
            response.createResponse(response, false, "DAO Error: " + e.getMessage());
        }

        return response;
    }
}
