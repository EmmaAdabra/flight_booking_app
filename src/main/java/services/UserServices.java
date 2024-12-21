package services;

import dao.UserDAO;
import models.User;
import utils.ObjectResponse;
import utils.Response;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServices implements IUserServices {
    private final UserDAO userDAO;

    public UserServices(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Response createUser(User user) {
        Response response = new Response();
        try {
            int result =  userDAO.save(user);

            if(result > 0){
                response.createResponse(response, true, "User created successfully");
            } else {
                response.createResponse(response, false, "User couldn't be created");
            }
        } catch (SQLException e){
            response.setStatus(false);
            response.setMessage("Database  error: " + e.getMessage());
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
                response.createResponse(response, false, "No users found");
            }
        } catch (SQLException e) {
            response.createResponse(response, false, "Database  error: " + e.getMessage());
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

        } catch (SQLException e) {
            response.createResponse(response, false, "Database error: " + e.getMessage());
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
                response.createResponse(response, false, "invalid user ID");
            }

        } catch (SQLException e) {
            response.createResponse(response, false, "Database error: " + e.getMessage());
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
                response.createResponse(response, false, "Invalid user ID");
            }
        } catch (SQLException e) {
            response.createResponse(response, false, "Database error: " + e.getMessage());
        }

        return response;
    }
}
